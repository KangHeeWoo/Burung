<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/rent.css?ver=7" />
<script src="${pageContext.request.contextPath }/js/rent.js?ver=2"></script>
<div id="date" align="center">
	<form method="post" action="${pageContext.request.contextPath }/rent.do" name="frm"
	onsubmit="return checkDate()">
	<input type="hidden" name="cmd" value="rentlist">
	<h2>대여일</h2>
		<input type="date" name="sDate" value="${sDate }"> 
		<input type="time" name="sTime" value="${sTime }" step="3600">
		<h2>반납일</h2>
		<input type="date" name="eDate" value="${eDate }">
		<input type="time" name="eTime" value="${eTime }" step="3600">
		<select name="model">
			<c:choose>
				<c:when test="${model == '' }">
					<option value="" selected="selected">전체</option>			
				</c:when>
				<c:otherwise>
					<option value="">전체</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${model == '718' }">
					<option value="718" selected="selected">718</option>			
				</c:when>
				<c:otherwise>
					<option value="718">718</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${model == '911' }">
					<option value="911"  selected="selected">911</option>		
				</c:when>
				<c:otherwise>
					<option value="911">911</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${model == 'Panamera' }">
					<option value="Panamera" selected="selected">Panamera</option>			
				</c:when>
				<c:otherwise>
					<option value="Panamera">Panamera</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${model == 'Macan' }">
					<option value="Macan" selected="selected">Macan</option>			
				</c:when>
				<c:otherwise>
					<option value="Macan">Macan</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${model == 'Cayenne' }">
					<option value="Cayenne" selected="selected">Cayenne</option>			
				</c:when>
				<c:otherwise>
					<option value="Cayenne">Cayenne</option>
				</c:otherwise>
			</c:choose>		
		</select>
		<input id="listSearch" type="submit" value="검색">
	</form>
</div>
<div id="rentList" align="center">
	<c:forEach var="car" items="${list }">
		<div align="center">
			<a href="
			${pageContext.request.contextPath }/rent.do?cmd=rentDetail
			&sDate=${sDate}&sTime=${sTime}
			&eDate=${eDate}&eTime=${eTime}
			&cName=${car}">
			<img alt="${car }" src="${pageContext.request.contextPath }/img/${car}_rent.png">
			${car }
			</a>
		</div>
	</c:forEach>
</div>