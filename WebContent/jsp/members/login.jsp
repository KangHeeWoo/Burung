<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>< 로그인 ></h1>
<form method="post" action="${pageContext.request.contextPath }/members.do?cmd=login">
	<br> 아이디<input type="text" name="memId" value="${param.memId }"><br>
	비밀번호 <input type="password" name="memPwd" value="${param.memPwd }"><br>
	<div>${errMsg }</div>
	<input type="submit" value="로그인">
</form>