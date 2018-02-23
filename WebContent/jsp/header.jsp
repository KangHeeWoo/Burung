<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/header.css?ver=1">
<script type="text/javascript">
	function goMain(e){
		var width = document.body.clientWidth;
		var x = e.clientX;
		
		if(x >= width * 0.4 && x <= width*0.7){
			location.href = document.getElementById("path").firstChild.nodeValue;
		}
	}
</script>
<div id="top" onclick="goMain(event)">
	<span style="display: none;" id="path">${pageContext.request.contextPath }/sales.do?cmd=main</span>
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<span><a href="${pageContext.request.contextPath }/members.do?cmd=loginpage">로그인</a> &nbsp&nbsp <a href="${pageContext.request.contextPath }/members.do?cmd=insert">회원가입</a></span>
		</c:when>
		<c:otherwise>
			<span><a href="${pageContext.request.contextPath }/members.do?cmd=logout">로그아웃</a> &nbsp&nbsp <a href="${pageContext.request.contextPath }/members.do?cmd=listpage">마이페이지</a></span>
		</c:otherwise>
	</c:choose>
</div>
<hr>
<ul id="menu">
	<li><a href="#">회사</a> |&nbsp
		<ul>
			<li><a href="${pageContext.request.contextPath }/company.do?cmd=introCompany">소개</a></li>
			<li><a href="${pageContext.request.contextPath }/company.do?cmd=historyCompany">연혁</a></li>
			<li><a href="${pageContext.request.contextPath }/company.do?cmd=mapCompany">오시는길</a></li>
		</ul></li>
	<li><a href="${pageContext.request.contextPath }/sales.do?cmd=choiceName&model=718">구매</a> |&nbsp
		<ul>
			<li><a href="${pageContext.request.contextPath }/sales.do?cmd=choiceName&model=718">718</a></li>
			<li><a href="${pageContext.request.contextPath }/sales.do?cmd=choiceName&model=911">911</a></li>
			<li><a href="${pageContext.request.contextPath }/sales.do?cmd=choiceName&model=Panamera">Panamera</a></li>
			<li><a href="${pageContext.request.contextPath }/sales.do?cmd=choiceName&model=Macan">Macan</a></li>
			<li><a href="${pageContext.request.contextPath }/sales.do?cmd=choiceName&model=Cayenne">Cayenne</a></li>
		</ul></li>
	<li><a href="${pageContext.request.contextPath }/rent.do?cmd=rentlist">렌트</a> |&nbsp</li>
	<li><a href="#">게시판</a>
		<ul>
			<li><a href="${pageContext.request.contextPath }/notice.do?cmd=noticelist">공지사항</a></li>
			<li><a href="${pageContext.request.contextPath }/review.do?cmd=reviewlist">후기 게시판</a></li>
			<li><a href="${pageContext.request.contextPath }/board.do?cmd=boardlist">자유 게시판</a></li>
		</ul></li>
</ul>
<hr>