<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope.memId }">
		<a href="<c:url value='/members.do?cmd=login'/>">회원로그인</a>
	</c:when>
	<c:otherwise>
		 [${memId }님 반갑습니다.]
		<a href="/members.do?cmd=listpage">상세정보</a>
		<a href="<c:url value='/members.do?cmd=logout'/>">로그아웃</a>
	</c:otherwise>
</c:choose>
</body>
</html>











