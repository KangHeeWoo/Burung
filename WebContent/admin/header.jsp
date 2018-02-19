<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/header.css">
<div id="top">
	<!-- 
	<img alt="a" src="../img/logo.PNG">
	 -->
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<span><a href="#">로그인</a> &nbsp;&nbsp; <a href="#">회원가입</a></span>
		</c:when>
		<c:otherwise>
			<span><a href="#">로그아웃</a> &nbsp;&nbsp; <a href="#">마이페이지</a></span>
		</c:otherwise>
	</c:choose>
</div>
<hr>
<ul id="menu">
	<li><a href="<%=request.getContextPath() %>/semi/list.do?cmd=memberlist">회원관리</a> &nbsp;
	<li><a href="<%=request.getContextPath() %>/semi/sales.do?cmd=carlist">구매관리</a> &nbsp;</li>
	<li><a href="">렌트관리</a> &nbsp;</li>
	<li><a href="#">공지사항관리</a>
</ul>
<hr>