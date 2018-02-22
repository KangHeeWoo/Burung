<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

var xhr=null;
function idcheck(){
	var memId=document.getElementsByName("memId")[0].value;
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=callback;
	var url = '<%=request.getContextPath()%>/members.do?cmd=findId&memId=' + memId; <%-- 경로 지정 + 해당 파라미터값 전달 --%>
	xhr.open('get',url,true);
	xhr.send();
}
function callback(){
	if(xhr.readyState==4 && xhr.status==200){
		var result=xhr.responseText;
		var json=eval('(' + result + ')');
		var idcheck=document.getElementById("idcheck");
		if(json.using==true){
			idcheck.innerHTML="현재 사용 중인 아이디입니다.";
		}else{
			idcheck.innerHTML="사용 가능한 아이디입니다.";
		}
	}
}
var pwd1 = null;
var pwd2 = null;
var same1= null;
var same2= null;

window.onload = function () { // 윈도우 시작시 실행
	pwd1 = document.getElementById("pwd1");
	pwd2 = document.getElementById("pwd2");
	same1=document.getElementById("same1");
	same2=document.getElementById("same2");	
}

function passwordCheck1() {
	
	if(pwd1.length > 4 && pwd1.length < 10 ){
		same1.innerHTML="사용 가능한 비밀번호 입니다.";
	}else {
		same1.innerHTML="4글자 이상 10글자 이하로 입력해주세요.";
	}
}
function passwordCheck2() {
	
	if(pwd1.value == pwd2.value){
		same2.innerHTML = "비밀번호가 일치합니다.";
	}else{
		same2.innerHTML = "비밀번호가 일치하지 않습니다.";
	}
}	

function addrInquiry(){
	
	xhr=new XMLHttpRequest();
	var addr1 = document.getElementById('addr2').value;
	
	xhr.onreadystatechange=callback2;
	var url = '<%= request.getContextPath()%>/members.do?cmd=addrInquiry&addr1=' + addr1;
	xhr.open('get',url,true);
	xhr.send();
}
function callback2(){
		
	if(xhr.readyState==4 && xhr.status==200){
		var result=xhr.responseText;
		var json=eval('(' + result + ')');
		
		var addr2 = document.getElementById("addr2");
		var Inquiry = document.getElementById("Inquiry");
		
		if(json.addr1 != null){
			document.getElementById("addr3").value = json.addr1 ; 
		}else{
			Inquiry.innerHTML="해당 주소가 없습니다.";
		}
	} 
}


</script>
<h1> [ 회원가입  ] </h1>
<form method = "post" action = "<%=request.getContextPath()%>/members.do?cmd=insertOk">
	아이디<input type= "text" name="memId" onkeyup ="idcheck()">
	<span id = "idcheck" style="font-size:10px; color :red"></span>
	<br>
         비밀번호<input type="password" name = "memPwd" id ="pwd1" onkeyup = "passwordCheck1()">
            <span id = "same1" style="font-size:10px; color :red"></span>
            <br>
         비밀번호 확인<input type = "password" name = "memPwd2" id="pwd2" onkeyup = "passwordCheck2()">
         <span id = "same2" style="font-size:10px; color :red"></span> <br><br><br>
         
	주소 입력란<input type = "text" id="addr2"><br>
	버튼 클릭<input type="button" name="memAddr" id="addr1" onclick ="addrInquiry()" value= "주소검색"> 
	<span id = "Inquiry" style="font-size:10px; color :red"></span> <br>
	
	검색결과<input type ="text" id ="addr3" name="addr3"><br>
	상세주소<input type ="text" id ="addr4" name="addr4"><br><br><br>
	<%-- 주소입력란에 입력한 주소값과 버튼클릭란(DB저장되있는 주소값)을  비교하여 검색결과란에 출력 --%>
	
	
	
	전화번호<input type="text" name ="memPhone"><br>
	이메일<input type="text" name="memEmail"><br>
	생년월일<input type="text" name = "memBirth"><br>
	이름<input type="text" name="memName"><br> 
<input type="submit" value="회원가입">  
</form>