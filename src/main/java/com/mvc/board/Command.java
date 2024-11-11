package com.mvc.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	// 컨트롤러에서 받은 요청을 분석해 어떤 처리가 필요한지에 따라서
	// 각 처리에 필요한 Command 객체를 실행시켜 로직을 처리하게 하고
	// 필요한 데이터를 가지고 컨트롤러로 보내준다.
	
	public String execute(HttpServletRequest request, HttpServletResponse response);
	
}
