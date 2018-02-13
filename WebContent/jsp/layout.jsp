<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/layout.css">
</head>
<body>
<c:choose>
	<c:when test="${param.spage == null || param.spage == '' }">
		<c:set var="spage" value="main.jsp"/> 
	</c:when>
	<c:otherwise>
		<c:set var="spage" value="${param.spage }"/> 
	</c:otherwise>
</c:choose>
<div id="wrap">
	<div id="header">
		<jsp:include page="header.jsp"/>
	</div>
	<div id="content">
		<jsp:include page="${spage }"/>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
</html>