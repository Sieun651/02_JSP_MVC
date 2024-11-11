<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	*{
		text-align: center;
		margin: 0 auto;
	}
	
	form{
		display: inline;
	}
	
</style>

</head>
<body>
		
	<br><hr>
	<h2><a href="/JSP_MVC/list.do?page=${param.page}">게시판 목록으로 이동</a></h2>
	<hr><br>
	
	<h2>${contentView.title } 의 내용보기</h2>
	<br>
	
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${contentView.boardID }</td>
		</tr>
		
		<tr>
			<th>조회수</th>
			<td>${contentView.hit }</td>
		</tr>
		
		<tr>
			<th>작성일</th>
			<td>
				<fmt:formatDate value="${contentView.boardDate }" pattern="yyyy년 MM월 dd일" />
			</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>${contentView.writer }</td>
		</tr>
		
		<tr>
			<th>제목</th>
			<td>${contentView.title }</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>${contentView.content }</td>
		</tr>
		
		<tr>
			<td colspan="2">
			
				<form action="/JSP_MVC/modifyForm.do" method="post" >
					
					<input type="hidden" name="boardID" value="${contentView.boardID }">
					<input type="hidden" name="hit" value="${contentView.hit }">
					<input type="hidden" name="writer" value="${contentView.writer }">
					<input type="hidden" name="title" value="${contentView.title }">
					<input type="hidden" name="content" value="${contentView.content }">
					
					<input type="submit" value="글 수 정">
				
				</form>
				
				&nbsp;&nbsp;&nbsp;
				
				<form action="/JSP_MVC/delete.do" method="post">
					<input type="hidden" name="boardID" value="${contentView.boardID }">
					<input type="submit" value="글 삭 제">
				
				</form>
				
				
			</td>
		</tr>
		
	</table>
	
</body>
</html>












