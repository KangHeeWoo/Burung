<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>게시판 수정</h2>
<h4 align="right">
</h4>
<form method="post" action="${pageContext.request.contextPath}/board.do?cmd=boardupdateOk">
<table border="solid 1px">
	<tr>
		<td>작성자</td>
		<td>${param.memid}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" value="${listdetail.boaTitle}" name="boatitle"></td>
		
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea cols="50" rows="20" name="boacontent" name="boacontent">${listdetail.boaContent }</textarea></td>
	</tr>
</table>
	<input type="hidden" value="${listdetail.boaNum }" name="boanum">
	<h1>${listdetail.boaNum }</h1>
	<input type="submit" value="수정">
</form>

	