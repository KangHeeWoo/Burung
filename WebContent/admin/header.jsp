<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/header.css">
<div id="top" onclick="goMain(event)">
	<span style="display: none;" id="path">${pageContext.request.contextPath }/sales.do?cmd=main</span>
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<span><a href="${pageContext.request.contextPath }/members.do?cmd=loginpage">로그인</a> &nbsp&nbsp <a href="${pageContext.request.contextPath }/members.do?cmd=insert">회원가입</a></span>
		</c:when>
		<c:otherwise>
			<span><a href="${pageContext.request.contextPath }/members.do?cmd=logout">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>
<hr>
<ul id="menu">
	<li><a href="<%=request.getContextPath() %>/semi/list.do?cmd=memberlist">회원관리</a> &nbsp;
	<li><a href="<%=request.getContextPath() %>/semi/sales.do?cmd=carlist">구매관리</a> &nbsp;</li>
	<li><a href="<%=request.getContextPath() %>/semi/rent.do?cmd=rentlist">렌트관리</a> &nbsp;</li>
	<li><a href="<%=request.getContextPath() %>/semi/notice.do?cmd=noticelist">공지사항관리</a>
	<li><a href="<%=request.getContextPath() %>/admin/layout.jsp?spage=adminstatistics/statistics.jsp">매출통계</a>
</ul>
<hr>