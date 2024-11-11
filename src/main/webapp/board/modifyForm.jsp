<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>

</head>
<body>
	
	<%--
		수정 폼 만들기 (제목, 내용) 
		글번호, 작성자, 조회수는 출력
		<input type="hidden" name="boardID" value="${contentView.boardID }">
		<input type="hidden" name="hit" value="${contentView.hit }">
		<input type="hidden" name="writer" value="${contentView.writer }">
		<input type="hidden" name="title" value="${contentView.title }">
		<input type="hidden" name="content" value="${contentView.content }">
	 --%>
	<br><hr>
	<h2>글 수정 폼</h2>
	<hr><br>
	
	<form action="/JSP_MVC/modify.do" method="post">
		
		<input type="hidden" name="boardID" value="${param.boardID}">
		
		<table border="1">
		
			<tr>
				<th>글번호</th>
				<td>${param.boardID}</td>
			</tr>
			
			<tr>
				<th>조회수</th>
				<td>${param.hit}</td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td>${param.writer }</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${param.title}" ></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea name="content" cols="23" rows="5" >${param.content}</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="글 수 정">
				</td>
			</tr>
			
		</table>
	
	</form>	
</body>
</html>