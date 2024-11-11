package com.mvc.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// boardID, title, content 받아서 DTO 객체생성후 DTO 객체의 프로퍼티 값으로 저장
		// DAO 객체 생성 후 boardUpdate 메소드 호출하기
		
		String boardID = request.getParameter("boardID");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		dto.setBoardID(Integer.parseInt(boardID));
		dto.setTitle(title);
		dto.setContent(content);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int result = dao.boardUpdate(dto);
		
		String msg = null;
		String url = null;
		
		if(result == 1) {
			msg = "글이 수정되었습니다.";
			url = "/JSP_MVC/list.do";
		}else {
			msg = "글이 수정되지 않았습니다.";
			url = "/JSP_MVC/list.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "board/message.jsp";
	}

}
