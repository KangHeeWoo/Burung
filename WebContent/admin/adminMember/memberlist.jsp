<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table.type11 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    margin: 20px 10px;
}
table.type11 th {
    width: 155px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
    background: #ce4869 ;
}
table.type11 td {
    width: 155px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #eee;
}
</style>
</head>
<body>
<form>
<table class="type11">
	<tr>
		<th style="text-align: center">회원번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>전화번호</th>
	</tr>
	<c:forEach var="user" items="${list }">
	<tr>
		<td style="text-align: center;">${user.memNum }</td>
		<td style="text-align: left"><a href="<%=request.getContextPath()%>/semi/list.do?cmd=memberdetail&memNum=${user.memNum}">${user.memId }</a></td>
		<td style="text-align: left">${user.memName }</td>
		<td style="text-align: left">${user.memPhone }</td>
	</tr>
	</c:forEach>
</table>
</form>
</body>
</html>