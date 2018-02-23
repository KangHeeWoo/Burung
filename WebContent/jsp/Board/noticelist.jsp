<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/board/noticelist.css?ver=3" >
<br>
<div align="center" id="setfont">
<h1 align="center">&lt;공지사항&gt;</h1><br>
	<table class="type09">
		<tr>
			<th class="cols" id="title" >제목</th><th class="cols"  id="regd" align="center">입력일</th><th class="cols" id="hit" align="center">조회수</th>
		</tr>
		<c:forEach var="notice" items="${listAll }">
		<tr onclick="noticedetail(${notice.notNum},'${notice.memName}')" onmouseover="fontborder(event)" onmouseout="fontout(event)">
			<td class="row">${notice.notTitle }</td>
			<td class="row"  align="center">${notice.notRegd }</td>
			<td class="row"  align="center">${notice.notHit }</td>
		</tr>
		</c:forEach>
	</table><br>
	<!-- 페이징 처리 -->
	
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="<%=request.getContextPath()%>/notice.do?cmd=noticelist&pageNum=${startPage-1 }">[ 이전 ]</a>
			</c:when>
			<c:otherwise>[이전]</c:otherwise>
		</c:choose>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<span style="color:black;">${i } </span>
				</c:when>
				<c:otherwise>
					<span style="color:gray;"><a href="<%=request.getContextPath()%>/notice.do?cmd=noticelist&pageNum=${i }">${i } </a></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="<%=request.getContextPath()%>/notice.do?cmd=noticelist&pageNum=${endPage+1 }">[ 다음 ]</a>
			</c:when>
			<c:otherwise>[ 다음 ]</c:otherwise>
		</c:choose>
	</div>
	<br>
</div>





<script type="text/javascript">
	
	
	function noticedetail(notNum,memName){
		location.href="notice.do?cmd=noticeDetail&notNum="+notNum+"&memName="+memName;
	}
	
	function fontborder(event){
		event.target.parentElement.style.fontWeight="bold";
		event.target.parentElement.style.color="#21396d";
		
	}
	function fontout(event){
		event.target.parentElement.style.fontWeight="normal";
		event.target.parentElement.style.color="black";
		
	}
</script>