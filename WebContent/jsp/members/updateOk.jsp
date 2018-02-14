<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${requestScope.result=='success' }">
		<h1>회원정보가 수정 되었습니다.</h1>
		<ul>
			<li><a href="/jsp/layout.jsp">초기화면으로 이동</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<h1>회원 정보가 수정 되지 않았습니다.</h1>
	</c:otherwise>
</c:choose>