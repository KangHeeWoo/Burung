<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>회원가입</h1>
<form method = "post" action = "<%=request.getContextPath()%>/members.do?cmd=insertOk">
	아이디<input type= "text" name="memId"><br>
       비밀번호<input type="password" name = "memPwd"><br>
	주소<input type="text" name="memAddr"><br>
	전화번호<input type="text" name ="memPhone"><br>
	이메일<input type="text" name="memEmail"><br>
	생년월일<input type="text" name = "memBirth"><br>
	이름<input type="text" name="memName"><br> 
<input type="submit" value="회원가입">  
</form>