<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sales.css"/>
<script src="../js/sales.js"></script> 
<div id="sales">
	<ul id="modelList"></ul>
	<img id="mainImg">
	<hr>
	<img id="subImg">
</div>
<c:choose>
	<c:when test="${param.name != null && param.name != ''}">
		<c:set var="name" value="${param.name }"/>
	</c:when>
	<c:otherwise>
		<c:set var="name" value="718 Cayman Models"/>
	</c:otherwise>
</c:choose>
<span style="display: none;" id="model">${param.model }</span>
<span style="display: none;" id="mName">${name }</span>