<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>신규차량등록</h2>
<form action="<%=request.getContextPath() %>/semi/sales.do?cmd=insertOk" method="post" enctype="multipart/form-data">
차량이름<br>
<input type="text" name="scarName"><br>
차량모델<br>
<input type="text" name="scarModel"><br>
수량<br>
<input type="text" name="salCnt"><br>
차량가격<br>
<input type="text" name="scarPrice"><br><br>
메인이미지<input type="file" name="main"><br>
서브이미지<input type="file" name="sub"><br><br>
<input type="submit" value="등록">
</form>
</body>
</html>