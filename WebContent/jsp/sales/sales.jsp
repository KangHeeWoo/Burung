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
		<c:choose>
			<c:when test="${param.model == '718' }">
				<c:set var="name" value="718 Cayman Models"/>
			</c:when>
			<c:when test="${param.model == '911' }">
				<c:set var="name" value="911 Carrera Models"/>
			</c:when>
			<c:when test="${param.model == 'Panamera' }">
				<c:set var="name" value="Panamera Models"/>
			</c:when>
			<c:when test="${param.model == 'Cayenne' }">
				<c:set var="name" value="Macan Models"/>
			</c:when>
			<c:when test="${param.model == 'Macan' }">
				<c:set var="name" value="Cayenne Models"/>
			</c:when>
		</c:choose>
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
		<form name="frm" method="post" onsubmit="return buy()" action="${pageContext.request.contextPath }/sales.do?cmd=buy">
			<input type="hidden" name="id" value="${sessionScope.id }">
			<input type="hidden" name="name" value="${name }">
			<h3>옵션 선택</h3>
			<br><hr><br>
			<span class="type">Color</span>
			<span><input type="radio" name="color" value="white : 100000" checked="checked"> White</span>
			<span><input type="radio" name="color" value="black : 200000"> Black</span>
			<span><input type="radio" name="color" value="red : 500000"> Red</span>
			<br>
			
			<span class="type">Wheel</span>
			<span><input type="radio" name="wheel" value="Steel Wheel : 100000" checked="checked"> Steel Wheel</span>
			<span><input type="radio" name="wheel" value="Alloy Wheel : 300000"> Alloy Wheel</span>
			<span><input type="radio" name="wheel" value="Flow Forming Wheel : 600000"> Flow Forming Wheel</span>
			<br>
			
			<span class="type">Seet</span>
			<span><input type="radio" name="seet" value="Corrected Grain : 2000000" checked="checked"> Corrected Grain</span>
			<span><input type="radio" name="seet" value="Full Grain : 2500000"> Full Grain</span>
			<span><input type="radio" name="seet" value="Split Leather : 5000000"> Split Leather</span>
			<br>
			
			<span class="type">Light</span>
			<span><input type="radio" name="light" value="HID : 200000" checked="checked"> HID</span>
			<span><input type="radio" name="light" value="Hollogen : 500000"> Hollogen</span>
			<span><input type="radio" name="light" value="LED : 600000"> LED</span>
			<br>
			
			<span class="type">Audio</span>
			<span><input type="radio" name="audio" value="JBL : 1500000" checked="checked"> JBL</span>
			<span><input type="radio" name="audio" value="BOSS : 2200000"> BOSS</span>
			<span><input type="radio" name="audio" value="B&O : 1800000"> B&O</span>
			<br>
			<br><hr><br>
			<input id="optSub" type="submit" value="구매하기">
		</form>
	</div>
</div>

