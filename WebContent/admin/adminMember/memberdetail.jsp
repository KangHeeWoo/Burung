<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
<div>
<table width="500" border="1" cellspacing="0" cellpadding="3" bordercolor="#999999" style="border-collapse:collapse;">
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>전화번호</th>
		
	</tr>
	<c:forEach var="user" items="${list }">
	<tr>
		<td>${user.memNum }</td>
		<td><a href="<%=request.getContextPath()%>/semi/list.do?cmd=memberdetail&memNum=${user.memNum}">${user.memId }</a></td>
		<td>${user.memName }</td>
		<td>${user.memPhone }</td>
	</tr>
	</c:forEach>
</table>
</div>
</form>
<form action="">
<div>
<table>
</table>
</div>
</form>

<form action="">
<div>
<table>
</table>
</div>
</form>
</body>
</html>