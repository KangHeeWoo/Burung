<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">자유게시판</h1><br><br>
<h5 align="right"><a href="board.do?cmd=boardinsert">글 등록</a></h5>
<div align="center">
	<table  width="500" border="1" cellspacing="0" cellpadding="3" bordercolor="#999999" style='border-collapse:collapse'>
		<tr>
			<th>회원아이디</th><th>제목</th><th>입력일</th><th>조회수</th>
		</tr>
		<c:forEach var="board" items="${listAll }">
		<tr>
			<td>${board.memid }</td>
			<td><a href="board.do?cmd=boardDetail&boanum=${board.boanum }&memid=${board.memid}">${board.boatitle }</a></td>
			<td>${board.boaRegd }</td>
			<td>${board.boahit }</td>
		</tr>
		</c:forEach>
	</table><br>
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="board.do?cmd=boardlist&pageNum=${startPage-1 }">[ 이전 ]</a>
			</c:when>
			<c:otherwise>[이전]</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<span style="color:black;">${i } | </span>
				</c:when>
				<c:otherwise>
					<a href="board.do?cmd=boardlist&pageNum=${i }"><span style="color:gray;">${i } | </span></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="board.do?cmd=boardlist&pageNum=${endPage+1 }">[ 다음 ]</a>
			</c:when>
			<c:otherwise>[ 다음 ]</c:otherwise>
		</c:choose>
	</div>
	<br>
	<select name="search">
		<option value="s0">==선택하세요==</option>
		<option value="stitle">제목</option>
		<option value="swriter">작성자</option>
	</select>
	<input type="text" name="searchValue">
	<input type="button" value="검색" onclick="schClick()">
</div>
</body>
</html>