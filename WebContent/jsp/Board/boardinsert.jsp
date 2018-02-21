<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/board/boardinsert.css?ver=2" >
<br>
<h3 id="mainfontsize" align="center">&lt; 자유 게시판 &gt;</h3>
<div id="setfont" align="center">
<form method="post" action="${pageContext.request.contextPath}/board.do?cmd=boardinsertOk">
<input type="hidden" value="${sessionScope.id }">
<table>
	<tr>
		<td>작성자</td>
		<td>&nbsp;&nbsp;<input type="text" name="writer"  id="writer" value="${sessionScope.id }" readonly="readonly"></td>
	</tr>
	
	<tr>
		<td>제&nbsp;&nbsp;&nbsp;목</td>
		<td>&nbsp;&nbsp;<input type="text" name="boatitle" id="boatitle"></td>
	</tr>
	<tr>
		<td>내&nbsp;&nbsp;&nbsp;용</td>
		<td>&nbsp;&nbsp;<textarea cols="100" rows="20" name="boacontent"></textarea></td>
	</tr>
	<tr>
			<td colspan="2" align="center">
			<input type="submit" value="저장"></td>
	</tr>
</table>
</form>
</div>