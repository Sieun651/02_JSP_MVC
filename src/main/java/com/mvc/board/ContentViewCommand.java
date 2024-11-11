package com.mvc.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentViewCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 넘어온 boardID 받은 후 정수로 변환 
		// DAO 객체 생성 후 getContent 메소드 호출하여 DTO 객체 얻기
		// DTO 객체를 request에 바인딩하기
		
		String boardID = request.getParameter("boardID");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = dao.getContent(Integer.parseInt(boardID));
		
		request.setAttribute("contentView", dto);
		
		return "board/contentView.jsp";
	}

}
