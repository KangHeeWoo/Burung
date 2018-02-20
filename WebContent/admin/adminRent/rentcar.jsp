<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	vertical-align: middle;
	color: #fff;
	background: #ce4869;
}

table.type11 td {
	width: 155px;
	padding: 10px;
	vertical-align: middle;
	border-bottom: 1px solid #ccc;
	background: #eee;
}

img {
	width: 120px;
	height: 60px;
	vertical-align: middle;
}
</style>
</head>
<body>
<h2>렌트차량</h2>
<div>
<table class="type11">
	<tr>
		<th>렌트차량번호</th>
		<th>차량이미지</th>
		<th>렌트차량명</th>
		<th>렌트차량모델</th>
		<th>시간당금액</th>
		<th>상태</th>
	</tr>
	<c:forEach var="rent" items="${rlist }">
	<tr>
		<td>${rent.renNum }</td>
		<td><img src="<%=request.getContextPath() %>/admin/img/${rent.rcarName}_sub.PNG"></td>
		<td>${rent.rcarName }</td>
		<td>${rent.rcarModel }</td>
		<td>${rent.timePay }</td>
		<td>${rent.state }</td>
	</tr>
	</c:forEach>
</table>
</div>
<!-- 렌트차량 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${rstartPage>3 }">
		<a href="<%=request.getContextPath()%>/semi/rent.do?rpagenum=${rstartPage-1}&cmd=rentlist">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${rstartPage }" end="${rendPage }">
		<c:choose>
			<c:when test="${rpagenum2==i }">
				<a href="<%=request.getContextPath()%>/semi/rent.do?cmd=rentlist&rpagenum=${i}">
					<span>${i }</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/semi/rent.do?cmd=rentlist&rpagenum=${i}">
					<span>${i }</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>
</body>
</html>