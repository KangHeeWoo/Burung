<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h1>나의 상세정보</h1>
<table border="1" width="500">
	<tr>
		<th>아이디</th>
		<th>주소</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>생년월일</th>
		<th>회원명</th>
	</tr>
	<tr>
		<td>${members.memId }</td>
		<td>${members.memAddr }</td>
		<td>${members.memPhone }</td>
		<td>${members.memEmail }</td>
		<td>${members.memBirth }</td>
		<td>${members.memName }</td>
	</tr>
</table>
<br>
<a href="members.do?cmd=update">회원정보 수정</a>