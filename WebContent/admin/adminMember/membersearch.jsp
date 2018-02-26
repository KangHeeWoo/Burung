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
<h2>회원목록</h2>
<table class="type11">
	<tr>
		<th style="text-align: center">회원번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>전화번호</th>
	</tr>
	<c:forEach var="user" items="${list }">
	<c:choose>
	<c:when test="${user.memNum=='0' }">
	</c:when>
	<c:otherwise>
		<tr>
			<td style="text-align: center;">${user.memNum }</td>
			<td style="text-align: left"><a href="<%=request.getContextPath()%>/semi/list.do?cmd=memberdetail&memNum=${user.memNum}">${user.memId }</a></td>
			<td style="text-align: left">${user.memName }</td>
			<td style="text-align: left">${user.memPhone }</td>
		</tr>	
	</c:otherwise>
	</c:choose>
	</c:forEach>
</table>
</form>
<!-- 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${startPage>5 }">
		<a href="<%=request.getContextPath()%>/semi/search.do?spageNum=${startPage-1}&search=${search}&find=${find}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${spageNum==i }">
				<a href="<%=request.getContextPath() %>/semi/search.do?spageNum=${i}&search=${search}&find=${find}">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/semi/search.do?spageNum=${i}&search=${search}&find=${find}">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
	<c:when test="${pageCount>endPage }">
	<a href="<%=request.getContextPath()%>/semi/search.do?spageNum=${endPage+1}&search=${search}&find=${find}">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
	</c:choose>
</div>
<div>
<form action="<%=request.getContextPath()%>/semi/search.do">
<select name="find" id="type">
	<option value="1">회원이름</option>
	<option value="2">회원아이디</option>
</select>
<input type="text" name="search" value="${search }">
<input type="submit" value="검색">
<input type="hidden" id="choicefind" value="${find }">
</form>
</div>
</body>
<script type="text/javascript">
var type=document.getElementById("type");
var choicefind=document.getElementById("choicefind");
window.onload= function(){
	for(var i=0;i<type.length;i++){
		if(type[i].value==choicefind.value){
			type[i].selected="selected";
		}
	}
}
</script>
</html>