<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

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
</style>

</head>
<body>
	
	<br><hr>
	<h2><a href="/JSP_MVC/list.do">MVC 게시판</a></h2>
	<hr><br>
	
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var="dto" items="${list}">
			
			<tr>
				<td>${dto.boardID }</td>
				<td>${dto.writer }</td>
				<td>
					<a href="/JSP_MVC/contentView.do?boardID=${dto.boardID }&page=${paging.currentPage}">
						${dto.title } &nbsp;
						<c:if test="${dto.newMark == true}">
							<span style="color:orange">[new]</span>
						</c:if>
					</a>
				</td>
				<td>
					<fmt:formatDate value="${dto.boardDate }" pattern="yyyy년 MM월 dd일" />
				</td>
				<td>${dto.hit }</td>
			</tr>
		
		</c:forEach>
		
		<tr>
			<td colspan="5">
				<a href="/JSP_MVC/writeForm.do">글 작 성</a>
			</td>
		</tr>
		
	</table>
		
	<c:if test="${paging.prev}">
		<a href="/JSP_MVC/list.do?page=${paging.startPage-1}">[이전]</a>
	</c:if>
	
	<c:forEach var="pageNum" begin="${paging.startPage}" end="${paging.endPage}" >
		
		<a href="/JSP_MVC/list.do?page=${pageNum}"
			style="${pageNum == paging.currentPage ? 'color:red' : 'color:blue'}">
			${pageNum}&nbsp;
		</a>
		
	</c:forEach>
	
	 <c:if test="${paging.next}">
		<a href="/JSP_MVC/list.do?page=${paging.endPage+1}">[다음]</a>
	</c:if>
	 
	 
</body>
</html>




