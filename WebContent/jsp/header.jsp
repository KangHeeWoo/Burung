<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/header.css">
<div id="top">
	<!-- 
	<img alt="a" src="../img/logo.PNG">
	 -->
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<span><a href="#">로그인</a> &nbsp&nbsp <a href="#">회원가입</a></span>
		</c:when>
		<c:otherwise>
			<span><a href="#">로그아웃</a> &nbsp&nbsp <a href="#">마이페이지</a></span>
		</c:otherwise>
	</c:choose>
</div>
<hr>
<ul id="menu">
	<li><a href="#">회사</a> |&nbsp
		<ul>
			<li><a href="#">소개</a></li>
			<li><a href="#">연혁</a></li>
			<li><a href="#">오시는길</a></li>
		</ul></li>
	<li><a href="../menu.do?cmd=sales">구매</a> |&nbsp</li>
	<li><a href="../menu.do?cmd=rent">렌트</a> |&nbsp</li>
	<li><a href="#">게시판</a>
		<ul>
			<li><a href="#">공지</a></li>
			<li><a href="#">후기</a></li>
			<li><a href="#">자유</a></li>
		</ul></li>
</ul>
<hr>