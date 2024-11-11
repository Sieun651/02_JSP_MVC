package com.mvc.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 넘어온 데이터 받기
		// 2. BoardDTO 객체 생성 후 넘어온 파라미터값을 DTO객체의 프로퍼티값으로 바인딩하기
		// 3. BoardDAO 객체 생성 후 boardInsert 메소드 호출하여 insert 하기
		
		// writer title content
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int result = dao.boardInsert(dto);
		
		String msg = null;
		String url = null;
		
		if(result == 1) {
			msg = "글이 등록 되었습니다.";
			url = "/JSP_MVC/list.do";
		}else {
			msg = "글이 등록 되지 않았습니다.";
			url = "/JSP_MVC/writeForm.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "board/message.jsp";
	}

}
