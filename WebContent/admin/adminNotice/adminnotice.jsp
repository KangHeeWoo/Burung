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
	#caradd{
	position: absolute;
	top: 300px;
	left: 700px;
	background-color: white;
	}
	
.btn.red {
    box-shadow: 0px 4px 0px #E04342;
}
.btn.red, .btn-two.red {
    background-color: #fa5a5a;
}

a[class*="btn"] {
    text-decoration: none;
}
.btn {
    position: relative;
    border: 0;
    padding: 15px 25px;
    display: inline-block;
    text-align: center;
    color: white;
    left: 550px;
}
.btn.small, .btn-two.small, .btn-gradient.small, .btn-effect.small {
    padding: 8px 18px;
    font-size: 14px;
}
.btn, .btn-two {
    margin: 9px;
}
* {
    box-sizing: border-box;
}
</style>
</head>
<body>
<h2>공지사항</h2>
<div>
<table class="type11">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>조회수</th>
		<th>등록일</th>
	</tr>
	<c:forEach var="notice" items="${list}">
		<tr>
			<td>${notice.notNum }</td>
			<td><a href="<%=request.getContextPath()%>/semi/notice.do?cmd=detail&notNum=${notice.notNum}">${notice.notTitle }</a></td>
			<td>${notice.memName }</td>
			<td>${notice.notHit }</td>
			<td>${notice.notRegd }</td>
		</tr>
	</c:forEach>
</table>
<a href="<%=request.getContextPath() %>/semi/notice.do?cmd=write" class="btn red small">글쓰기</a>
</div>
<!-- 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${startPage>4 }">
			<a href="<%=request.getContextPath()%>/semi/notice.do?cmd=noticelist&pagenum=${startPage-1}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
		<c:when test="${pagenum==i }">
			<a href="<%=request.getContextPath() %>/semi/notice.do?pagenum=${i}&cmd=noticelist">
			<span style="color: blue">[${i }]</span>
			</a>
		</c:when>
		<c:otherwise>
			<a href="<%=request.getContextPath() %>/semi/notice.do?pagenum=${i}&cmd=noticelist">
			<span style="color: gray">[${i }]</span>
			</a>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${endPage<pageCount }">
			<a href="<%=request.getContextPath()%>/semi/notice.do?cmd=noticelist&pagenum=${endPage+1}">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>