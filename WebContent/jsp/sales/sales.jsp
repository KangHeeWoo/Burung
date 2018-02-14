<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sales.css"/>
<script src="../js/sales.js"></script> 
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
<div id="sales">
	<ul id="modelList"></ul>
	<img id="mainImg">
	<hr>
	<img id="subImg">
	<div id="selOpt">
		<form method="post" action="${pageContext.request.contextPath }/sales.do?cmd=buy">
			<input type="hidden" name="name" value="${name }">
			<h3>옵션 선택</h3>
			<br><hr><br>
			<span class="type">Color</span>
			<span><input type="radio" name="color" value="white" checked="checked"> White</span>
			<span><input type="radio" name="color" value="black"> Black</span>
			<span><input type="radio" name="color" value="red"> Red</span>
			<br>
			
			<span class="type">Wheel</span>
			<span><input type="radio" name="wheel" value="Steel Wheel" checked="checked"> Steel Wheel</span>
			<span><input type="radio" name="wheel" value="Alloy Wheel"> Alloy Wheel</span>
			<span><input type="radio" name="wheel" value="Flow Forming Wheel"> Flow Forming Wheel</span>
			<br>
			
			<span class="type">Seet</span>
			<span><input type="radio" name="seet" value="Corrected Grain" checked="checked"> Corrected Grain</span>
			<span><input type="radio" name="seet" value="Full Grain"> Full Grain</span>
			<span><input type="radio" name="seet" value="Split Leather"> Split Leather</span>
			<br>
			
			<span class="type">Light</span>
			<span><input type="radio" name="light" value="HID" checked="checked"> HID</span>
			<span><input type="radio" name="light" value="Hollogen"> Hollogen</span>
			<span><input type="radio" name="light" value="LED"> LED</span>
			<br>
			
			<span class="type">Audio</span>
			<span><input type="radio" name="audio" value="JBL" checked="checked"> JBL</span>
			<span><input type="radio" name="audio" value="BOSS"> BOSS</span>
			<span><input type="radio" name="audio" value="B&O"> B&O</span>
			<br>
			<br><hr><br>
			<input id="optSub" type="submit" value="구매하기">
		</form>
	</div>
</div>