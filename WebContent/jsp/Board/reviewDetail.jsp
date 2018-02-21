<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>디테일페이지다</h1>
<c:forEach var="car" items="${recarimglist }">
	<img src="<%=request.getContextPath() %>/jsp/Board/img/${car}">

</c:forEach>