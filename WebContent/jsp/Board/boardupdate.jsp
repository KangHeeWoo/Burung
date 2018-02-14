<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>자유게시판</h2>
<h4 align="right">
	<a href="${pageContext.request.contextPath}/board.do?cmd=boardlist">글목록</a>
</h4>
<form method="post" action="">
<table border="solid 1px">
	<tr>
		<td>작성자</td>
		<td>${param.memid}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" value="${listdetail.boaTitle }"></td>
		
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea cols="50" rows="20" name="boacontent">${listdetail.boaContent }</textarea></td>
	</tr>
</table>
	<input type="submit" value="수정">
</form>

	