<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sales.css"/>
<script src="../js/sales.js"></script> 
<div id="sales">
	<ul id="modelList">
		<li><a href="../sales.do?model=718&name=718 Cayman Models">718 Cayman Models</a></li>
		<li><a href="../sales.do?model=718&name=718 Boxster Models">718 Boxster Models</a></li>
		<li><a href="../sales.do?model=718&name=718 GTS Models">718 GTS Models</a></li>
	</ul>
	<img id="mainImg">
</div>
<c:choose>
	<c:when test="${param.name != null && param.name != ''}">
		<c:set var="name" value="${param.name }"/>
	</c:when>
	<c:otherwise>
		<c:set var="name" value="718 Cayman Models"/>
	</c:otherwise>
</c:choose>
<span style="display: none;" id="model">${name }</span>