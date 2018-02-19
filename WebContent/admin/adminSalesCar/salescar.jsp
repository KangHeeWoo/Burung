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
    vertical-align: middle;
    color: #fff;
    background: #ce4869 ;
}
table.type11 td {
    width: 155px;
    padding: 10px;
    vertical-align: middle;
    border-bottom: 1px solid #ccc;
    background: #eee;
}
 img{width:120px;height: 60px;vertical-align:middle;} 

</style>

</head>
<body>
<span style="margin-left: 90%;"><button onclick="location.href='<%=request.getContextPath()%>/semi/sales.do?cmd=carinsert'">신규차량등록</button></span>
<!-- 
<input type="button" value="차량등록" onclick="location.href='<%=request.getContextPath()%>/semi/sales.do?cmd=carinsert'">
 -->
<form>
<h2>등록된차량</h2>
<table class="type11">
	<tr>
		<th style="text-align: center">등록번호</th>
		<th>차량이미지</th>
		<th>차량이름</th>
		<th>차량모델</th>
		<th>수량</th>
		<th>가격</th>
	</tr>
	<c:forEach var="car" items="${list }">
	<tr>
		<td style="text-align: center;">${car.salNum }</td>
		<td style="text-align: center;"><img src="<%=request.getContextPath() %>/admin/img/${car.scarName}_sub.PNG"></td>
		<td style="text-align: left"><a href="#">${car.scarName }</a></td>
		<td style="text-align: left">${car.scarModel }</td>
		<td style="text-align: left">${car.salCnt }</td>
		<td style="text-align: left">${car.scarPrice }</td>
	</tr>
	</c:forEach>
</table>
</form>
<!-- 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${startPage>3 }">
		<a href="<%=request.getContextPath()%>/semi/sales.do?pageNum=${startPage-1}&cmd=carlist">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="<%=request.getContextPath() %>/semi/sales.do?pageNum=${i}&cmd=carlist">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/semi/sales.do?pageNum=${i}&cmd=carlist">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
	<c:when test="${pageCount>endPage }">
	<a href="<%=request.getContextPath()%>/semi/sales.do?pageNum=${endPage+1}&cmd=carlist">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
	</c:choose>
</div>
<form action="">
<h2>최근 판매된 차량</h2>
<div>
<table class="type11">
	<tr>
		<th>구매번호</th>
		<th>구매가격</th>
		<th>구매상태</th>
		<th>구매날짜</th>
		<th>구매차종</th>
	</tr>
	<c:forEach var="sale" items="${saleslist }">
	<tr>
		<td>${sale.sListNum }</td>
		<td>${sale.salPrice }</td>
		<td>${sale.salState }</td>
		<td>${sale.salDate }</td>
		<td>${sale.sCarModel }</td>
	</tr>
	</c:forEach>
</table>
</div>
</form>
<!-- 최근판매내역 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${lstartPage>3 }">
		<a href="<%=request.getContextPath()%>/semi/list.do?pageNum=${lstartPage-1}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${lstartPage }" end="${lendPage }">
		<c:choose>
			<c:when test="${lpageNum==i }">
				<a href="<%=request.getContextPath() %>/semi/sales.do?lpageNum=${i}&cmd=carlist">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/semi/sales.do?lpageNum=${i}&cmd=carlist">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
	<c:when test="${lpageCount>lendPage }">
	<a href="<%=request.getContextPath()%>/semi/sales.do?lpageNum=${lendPage+1}">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
	</c:choose>
</div>
</body>
</html>