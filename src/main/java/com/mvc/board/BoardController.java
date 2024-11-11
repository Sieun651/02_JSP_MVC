package com.mvc.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// .do : jsp에서 사용하는 가상의 주소
// 사용자는 파일의 실제경로를 알 수 없으므로 보안에 도움이 된다.

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("BoardController 입니다.");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null; // jsp 페이지 지정
		Command command = null;
		// Model 역할을 수행하는 Command 객체 지정
		
		// http://localhost:8282/JSP_MVC/list.do
		
		String uri = request.getRequestURI();
		// uri : /JSP_MVC/list.do
		//System.out.println(uri);
		
		String conPath = request.getContextPath();
		// conPath : /JSP_MVC
		//System.out.println(conPath);
		
		String result = uri.substring(conPath.length());
		// result : /list.do
		
		// 게시판 목록페이지 요청 : /list.do
		// 게시글 작성 폼 페이지 요청 : /writeForm.do
		// 게시글 등록 : /write.do
		// 제목 클릭시 해당 내용페이지 요청 : /contentView.do
		// 수정 폼 페이지 요청 : /modifyForm.do
		// 게시글 수정 요청 : /modify.do
		// 게시글 삭제 요청 : /delete.do
		
		if(result.equals("/list.do")) { // 게시판 목록페이지 요청
			
			command = new ListCommand();
			viewPage = command.execute(request, response);
			
		}else if(result.equals("/writeForm.do")) {// 게시글 작성 폼 페이지 요청
			
			viewPage = "board/writeForm.jsp";
			
		}else if(result.equals("/write.do")) {// 게시글 등록 요청
			
			
			command = new WriteCommand();
			viewPage = command.execute(request, response);
			
		}else if(result.equals("/contentView.do")) {// 제목 클릭시 해당 내용페이지 요청
			
			command = new ContentViewCommand();
			viewPage = command.execute(request, response);
			
		}else if(result.equals("/modifyForm.do")) {// 수정 폼 페이지 요청 
			
			viewPage = "board/modifyForm.jsp";
			
		}else if(result.equals("/modify.do")) {// 게시글 수정 요청
			
			command = new ModifyCommand();
			viewPage = command.execute(request, response);
			
		}else if(result.equals("/delete.do")) {// 게시글 삭제 요청
			
			command = new DeleteCommand();
			viewPage = command.execute(request, response);
			
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		// RequestDispatcher : 서블릿의 포워딩(forward) 기능의 객체
		
		dispatcher.forward(request, response);
		// jsp 페이지로 포워딩
		
		
	}

}
