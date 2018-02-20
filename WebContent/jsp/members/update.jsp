<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<h1>회원수정하기</h1>
	<form method="post"	action="<%=request.getContextPath()%>/members.do?cmd=updateOk">
   <!-- 아이디 <input type="text" name="memId" value="${requestScope.user.memId }"><br>  -->
		아이디 <input type="text" name="memId" value="${sessionScope.id}"><br>
		비밀번호 <input type="password" name="memPwd" value="*****"><br>

   		주소 <input type="text" name="memAddr" value="${sessionScope.memAddr }"><br>
   		전화번호 <input type="text" name="memPhone" value="${sessionScope.memPhone }"><br>
		이메일 <input type="text" name="memEmail" value="${sessionScope.memEmail }"><br>
		생년월일 <input type="text" name="memBirth" value="${sessionScope.memBirth }"><br>
		이름 <input type="text" name="memName" value="${sessionScope.memName }"><br>
		
   <!-- 주소 <input type="text" name="memAddr" value="${user.memAddr }"><br>	
		전화번호 <input type="text" name="memPhone" value="${user.memPhone }"><br>
		이메일 <input type="text" name="memEmail" value="${user.memEmail }"><br>
		생년월일 <input type="text" name="memBirth" value="${user.memBirth }"><br>
		이름 <input type="text" name="memName" value="${user.memName }"><br> -->
		<input type="submit" value="수정완료">
	</form>