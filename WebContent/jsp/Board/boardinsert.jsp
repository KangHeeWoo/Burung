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
<h2>작성하기</h2>
<form method="post" action="${pageContext.request.contextPath}/board.do?cmd=boardinsertOk">
<input type="hidden" value="${sessionScope.id }">
<table>
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer" value="${sessionScope.id }" readonly="readonly"></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" name="boatitle"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea cols="50" rows="20" name="boacontent"></textarea></td>
	</tr>
	<tr>
			<td colspan="2" align="center">
			<input type="submit" value="저장"></td>
	</tr>
</table>
</form>
</body>
</html>