package com.mvc.board;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String page = request.getParameter("page");
		
		int currentPage = 0;
		
		if(page == null || page == "") {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(page);
		}
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int totalPosts = dao.getTotalPosts();
		
		Paging paging = new Paging(totalPosts, currentPage);
		
		// 한페이지당 글의 갯수는 10개
		// 현재 페이지가 1페이지라면 글의 시작번호는 1번
		// 현재 페이지가 2페이지라면 글의 시작번호는 11번
		int postStart = (currentPage -1) * 10 + 1;
		
		int postEnd = currentPage * 10;
				
		List<BoardDTO> list = dao.boardList(postStart, postEnd);
		
		for(BoardDTO dto : list) {
			
			long nowTime = System.currentTimeMillis();
			Timestamp boardDate = dto.getBoardDate();
			
			long boardTime = boardDate.getTime();
			
			if((nowTime - boardTime) < (1000 * 60 * 60 * 24)) {
				
				dto.setNewMark(true);
				
			}
			
		}
		
		
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		
		return "board/list.jsp";
	}

}
