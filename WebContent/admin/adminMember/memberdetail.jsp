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
<div>
<h2>1.회원정보</h2>
<table class="type11">
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>이메일</th>
		<th>생년월일</th>
	</tr>
	<c:forEach var="user" items="${detail }">
	<tr>
		<td>${user.memNum }</td>
		<td>${user.memId }</td>
		<td>${user.memName }</td>
		<td>${user.memPhone }</td>
		<td>${user.memAddr }</td>
		<td>${user.memEmail }</td>
		<td>${user.memBirth }</td>
	</tr>
	</c:forEach>
</table>
</div>
</form>

<form action="">
<div>
<h2>2.렌트내역</h2>
<table class="type11">
	<tr>
		<th>렌트번호</th>
		<th>시작날짜</th>
		<th>반납날짜</th>
		<th>총가격</th>
		<th>렌트상태</th>
	</tr>
	<c:forEach var="rent" items="${rentlist }">
	<tr>
		<td>${rent.rListNum }</td>
		<td>${rent.rStartDate }</td>
		<td>${rent.rEndDate }</td>
		<td>${rent.rTotPrice }</td>
		<td>${rent.renState }</td>
	</tr>
	</c:forEach>
</table>
</div>
</form>

<form action="">

<div>
<h2>3.구매내역</h2>
<table class="type11">
	<tr>
		<th>구매번호</th>
		<th>구매가격</th>
		<th>구매상태</th>
	</tr>
	<c:forEach var="sale" items="${salelist }">
	<tr>
		<td>${sale.sListNum }</td>
		<td>${sale.salPrice }</td>
		<td>${sale.salState }</td>
	</tr>
	</c:forEach>
</table>
</div>
</form>
</body>
</html>