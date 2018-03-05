<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>
	/css/board/boarddetail.css?ver=26" >
<div align="center"><h3>게시판 수정</h3></div>

<div align="center" id="detail">
<form method="post" action="${pageContext.request.contextPath}/board.do?cmd=boardupdateOk">
<table id="table">
	<tr>
		<td class="title">작성자</td>
		<td>${param.memid}</td>
	</tr>
	<tr>
		<td class="title" id="title">제&nbsp;&nbsp;&nbsp;목</td>
		<td  id="content"><input type="text" value="${listdetail.boaTitle}" name="boatitle"></td>
	</tr>
	<tr><td colspan="2"><hr></td></tr>
	<tr><td colspan="2"><div align="right" id="name">입력일 _ ${listdetail.boaRegd}&nbsp;&nbsp;/&nbsp;&nbsp;작성자 _  ${param.memid}</div></td></tr>
	<tr><td colspan="2"></td></tr>
	<tr>
		<td colspan="2"><div align="center"><textarea cols="100" rows="20" name="boacontent"
				>${listdetail.boaContent }</textarea></div></td>
	</tr>
</table>
	<input type="hidden" value="${listdetail.boaNum }" name="boanum">
	<input type="submit" value="수정">
</form>
</div>
	