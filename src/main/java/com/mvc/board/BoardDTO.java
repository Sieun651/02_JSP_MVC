package com.mvc.board;

import java.sql.Timestamp;

public class BoardDTO {
	
//	BOARD_ID	NUMBER(4,0)
//	WRITER	NVARCHAR2(50 CHAR)
//	TITLE	NVARCHAR2(50 CHAR)
//	CONTENT	NVARCHAR2(500 CHAR)
//	BOARD_DATE	TIMESTAMP(6)
//	HIT	NUMBER(4,0)
	
	private int boardID;
	private String writer;
	private String title;
	private String content;
	private Timestamp boardDate;
	private int hit;
	
	// new 마크 활성화
	private boolean newMark;
	
	// 기본 생성자 : 필수
	public BoardDTO() {
		
	}
	
	// 초기화 생성자 : 선택
	public BoardDTO(int boardID, String writer, String title, String content, Timestamp boardDate, int hit) {
		super();
		this.boardID = boardID;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.boardDate = boardDate;
		this.hit = hit;
	}

	public int getBoardID() {
		return boardID;
	}

	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Timestamp boardDate) {
		this.boardDate = boardDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	public boolean isNewMark() {
		return newMark;
	}
	
	public void setNewMark(boolean newMark) {
		this.newMark = newMark;
	}
	
	
}






