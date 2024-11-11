package com.mvc.board;

import java.sql.*;
import javax.sql.*;
import java.util.*;
import javax.naming.*;

public class BoardDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	private BoardDAO() {
		
		try {
			
			Context context = new InitialContext();
			
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private void close(Connection conn) {
		
		try {
			
			if(conn != null) {
				conn.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void close(PreparedStatement pstmt) {
		
		try {
			
			if(pstmt != null) {
				pstmt.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void close(ResultSet rs) {
		
		try {
			
			if(rs != null) {
				rs.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 페이징 처리를 위해 총 게시물 수를 얻는 메소드
	public int getTotalPosts() {
		
		int result = 0;
		
		String query = "SELECT COUNT(*) AS TOTAL FROM BOARD";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getInt("TOTAL");
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(pstmt);
			close(conn);
			
		}
		
		return result;
	}
	
	
	
	// 1. 게시판 목록을 만들기 위해 모든 데이터를 얻어오는 기능
	public ArrayList<BoardDTO> boardList(int postStart, int postEnd){
		
		ArrayList<BoardDTO> list = new ArrayList<>();
		
		String query = "SELECT * FROM"
				+ "    ("
				+ "    SELECT ROWNUM NUM, B.* FROM"
				+ "        ("
				+ "        SELECT * FROM BOARD ORDER BY BOARD_ID DESC"
				+ "        ) B"
				+ "    )"
				+ "WHERE NUM BETWEEN ? AND ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, postStart);
			pstmt.setInt(2, postEnd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
//				BOARD_ID	NUMBER(4,0)
//				WRITER	NVARCHAR2(50 CHAR)
//				TITLE	NVARCHAR2(50 CHAR)
//				CONTENT	NVARCHAR2(500 CHAR)
//				BOARD_DATE	TIMESTAMP(6)
//				HIT	NUMBER(4,0)
				
				int boardID = rs.getInt("BOARD_ID");
				String writer = rs.getString("WRITER");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Timestamp boardDate = rs.getTimestamp("BOARD_DATE");
				int hit = rs.getInt("HIT");
				
				list.add(new BoardDTO(boardID, writer, title, content, boardDate, hit));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(pstmt);
			close(conn);
			
		}
		
		return list;
	}
	
	// 2. 글 작성 폼에서 글 등록시 데이터를 매개변수로 받아서 DB에 데이터를 넣는 기능
	public int boardInsert(BoardDTO dto) {
		int result = 0;
//		BOARD_ID	NUMBER(4,0)
//		WRITER	NVARCHAR2(50 CHAR)
//		TITLE	NVARCHAR2(50 CHAR)
//		CONTENT	NVARCHAR2(500 CHAR)
//		BOARD_DATE	TIMESTAMP(6)
//		HIT	NUMBER(4,0)
		String query = "INSERT INTO BOARD(BOARD_ID, WRITER, TITLE, CONTENT) "
					+ "VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?)";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	// 3. 목록에서 제목클릭시 해당 내용페이지로 가서 해당하는 글을 보여주기 위해 데이터를 얻어오는 기능
	public BoardDTO getContent(int boardID) {
		
		upHit(boardID); // 조회수 1증가
		
		BoardDTO dto = null;
		
		String query = "SELECT * FROM BOARD WHERE BOARD_ID = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				boardID = rs.getInt("BOARD_ID");
				String writer = rs.getString("WRITER");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Timestamp boardDate = rs.getTimestamp("BOARD_DATE");
				int hit = rs.getInt("HIT");
				
				dto = new BoardDTO(boardID, writer, title, content, boardDate, hit);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(pstmt);
			close(conn);
			
		}
		
		return dto;
	}
	
	// 4. 조회수를 하나 증가시켜주는 기능
	private void upHit(int boardID) {
		
		String query = "UPDATE BOARD SET HIT = HIT + 1 WHERE BOARD_ID = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardID);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
	}
	
	// 5. 글 수정폼에서 넘어온 데이터를 매개변수로 받아서 해당글을 수정해주는 기능
	//    title, content, boardID
	public int boardUpdate(BoardDTO dto) {
		int result = 0;
		
		String query = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_ID = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getBoardID());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}
	
	// 6. 글번호를 매개변수로 받아서 해당글을 삭제해주는 기능
	public int boardDelete(int boardID) {
		int result = 0;
		
		String query = "DELETE FROM BOARD WHERE BOARD_ID = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardID);
			
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return result;
	}

}




