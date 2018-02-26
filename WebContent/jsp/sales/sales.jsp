<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/sales.css?ver=8" />
<script src="${pageContext.request.contextPath }/js/sales.js?ver=8"></script>
<c:choose>
	<c:when test="${param.name != null && param.name != ''}">
		<c:set var="name" value="${param.name }" />
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${param.model == '718' }">
				<c:set var="name" value="718 Cayman Models" />
			</c:when>
			<c:when test="${param.model == '911' }">
				<c:set var="name" value="911 Carrera Models" />
			</c:when>
			<c:when test="${param.model == 'Panamera' }">
				<c:set var="name" value="Panamera Models" />
			</c:when>
			<c:when test="${param.model == 'Cayenne' }">
				<c:set var="name" value="Macan Models" />
			</c:when>
			<c:when test="${param.model == 'Macan' }">
				<c:set var="name" value="Cayenne Models" />
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>
<span style="display: none;" id="model">${param.model }</span>
<span style="display: none;" id="mName">${name }</span>
<span style="display: none;" id="path">${pageContext.request.contextPath }</span>
<div id="sales">
	<ul id="modelList"></ul>
	<img id="mainImg">
	<hr><br>
	<img id="subImg">
	<div id="selOpt">
		<form name="frm" method="post" onsubmit="return buy()"
			action="${pageContext.request.contextPath }/sales.do?cmd=buy">
			<input type="hidden" name="id" value="${sessionScope.id }"> <input
				type="hidden" name="name" value="${name }">
			<h3>옵션 선택</h3>
			<br>
			<hr>
			<br> <span class="type">Color</span> <span><input
				type="radio" name="color" value="White : 100000" checked="checked"
				onclick="showOpt()"> White</span> <span><input type="radio"
				name="color" value="Black : 200000" onclick="showOpt()">
				Black</span> <span><input type="radio" name="color"
				value="Red : 500000" onclick="showOpt()"> Red</span> <br> <span
				class="type">Wheel</span> <span><input type="radio"
				name="wheel" value="Steel Wheel : 100000" checked="checked"
				onclick="showOpt()"> Steel Wheel</span> <span><input
				type="radio" name="wheel" value="Alloy Wheel : 300000"
				onclick="showOpt()"> Alloy Wheel</span> <span><input
				type="radio" name="wheel" value="Flow Forming Wheel : 600000"
				onclick="showOpt()"> Flow Forming Wheel</span> <br> <span
				class="type">Seet</span> <span><input type="radio"
				name="seet" value="Corrected Grain : 2000000" checked="checked"
				onclick="showOpt()"> Corrected Grain</span> <span><input
				type="radio" name="seet" value="Full Grain : 2500000"
				onclick="showOpt()"> Full Grain</span> <span><input
				type="radio" name="seet" value="Split Leather : 5000000"
				onclick="showOpt()"> Split Leather</span> <br> <span
				class="type">Light</span> <span><input type="radio"
				name="light" value="HID : 200000" checked="checked"
				onclick="showOpt()"> HID</span> <span><input type="radio"
				name="light" value="Hollogen : 500000" onclick="showOpt()">
				Hollogen</span> <span><input type="radio" name="light"
				value="LED : 600000" onclick="showOpt()"> LED</span> <br> <span
				class="type">Audio</span> <span><input type="radio"
				name="audio" value="JBL : 1500000" checked="checked"
				onclick="showOpt()"> JBL</span> <span><input type="radio"
				name="audio" value="BOSS : 2200000" onclick="showOpt()">
				BOSS</span> <span><input type="radio" name="audio"
				value="B&O : 1800000" onclick="showOpt()"> B&O</span> <br> <br>
			<hr>
			<p id="selectOpt"></p>
			<br> <br> <input id="optSub" type="submit" value="구매하기">
		</form>
	</div>
</div>
<div id="cook">
	<h2>최근본상품</h2>		
		<c:forEach var = "cooks" items = "${requestScope.cooks }">
			<c:choose>
				<c:when test = "${cooks == null }">
					상품없음<br>
				</c:when>
				<c:otherwise>
					<img class="ad" src="/Burung/img/${cooks }_rent.png"><br>
				</c:otherwise>
			</c:choose>
		</c:forEach>
</div>






