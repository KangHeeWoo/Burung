<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/members/updata.css?ver=3">


<script type="text/javascript">
    
	
var xhr=null;

var check = false;

function idcheck(){
	
	var memId = document.getElementsByName("memId")[0].value;
	var reg_exp = new RegExp("^[a-z][a-z0-9]{5,20}$","g"); // 5~20자의 영문 소문자, 숫자만 사용 가능합니다.
	var match = reg_exp.exec(memId);
	var idcheck=document.getElementById("idcheck");
	
	if(match == null){ 
			idcheck.innerHTML="5~20자의 영문 소문자, 숫자만 사용 가능합니다.";
	}else{
			idcheck.innerHTML="";
			
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		var url = '<%=request.getContextPath()%>/members.do?cmd=findId&memId=' + memId; <%-- 경로 지정 + 해당 파라미터값 전달 --%>
		xhr.open('get',url,true);
		xhr.send();
  	}
}
function callback(){
	if(xhr.readyState==4 && xhr.status==200){
		var result=xhr.responseText;
		var json=eval('(' + result + ')');
		var idcheck=document.getElementById("idcheck");
		
		if(json.using==true){
			idcheck.innerHTML="현재 사용 중인 아이디입니다.";
			check = false;
		}else{
			idcheck.innerHTML="사용 가능한 아이디입니다.";
			check = true;
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
	
	var reg_exp = new RegExp("^[a-zA-Z0-9]{4,12}$","g");
	var match = reg_exp.exec(pwd1.value);
	
	if(match == null){
		same1.innerHTML="4~12자 영문 대 소문자, 숫자를 사용하세요.";
		check = false;	
	}else { 
		same1.innerHTML="사용 가능한 비밀번호 입니다.";
		check = true;
	}
}
function passwordCheck2() {
	
	if(pwd1.value == pwd2.value){
		same2.innerHTML = "비밀번호가 일치합니다.";
		check = true;
	}else{
		same2.innerHTML = "비밀번호가 일치하지 않습니다.";
		check = false;
	}
}	

function addrInquiry(){
	
	xhr=new XMLHttpRequest();
	var addr1 = document.getElementById('addr2').value;
	
	if(addr1 == null || addr1 == '') return ;
	
	xhr.onreadystatechange=callback2;
	var url = '<%=request.getContextPath()%>/members.do?cmd=addrInquiry&addr1='	+ addr1;
		xhr.open('get', url, true);
		xhr.send();
	}
	
	function callback2() {

		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = xhr.responseText;
			var json = eval('(' + result + ')');

			var addr2 = document.getElementById("addr2");
			var Inquiry = document.getElementById("Inquiry");

			if (json.addr1 != null) {
				document.getElementById("addr3").value = json.addr1;
				check = true;
			} else {
				Inquiry.innerHTML = "해당 주소가 없습니다.";
				check = false;
			}
		}
	}
function email_change() {
		var email = frm.email.value;
		frm.email2.value = email;
	}

function insertCheck (){
		return check;	
	}


</script>

<div class="container" align="center">
	<div class="row" >
		<div class="col-md-offset-5 col-md-3">
			<div class="form-login" >

<h2>[ 회원수정하기 ]</h2><br>
	<form method="post"	action="<%=request.getContextPath()%>/members.do?cmd=updateOk">

	<table class="updateForm">
	<tr>
	<td>아이디</td>
	<td><input type="text" name="memId" onkeyup="idcheck()" id="memId" placeholder="아이디"  value="${sessionScope.id}" readonly="readonly"> 
		<span id="idcheck"></span>
	</td>
	</tr>
	
	<tr>
	<td>비밀번호</td>
	<td><input type="password" name="memPwd" id="pwd1" onkeyup="passwordCheck1()"placeholder="비밀번호" value="" required> 
		<span id="same1"></span> 
	</td>
	</tr>
	
	<tr>
	<td>비밀번호 확인</td>
	<td><input type="password" name="memPwd2" id="pwd2" onkeyup="passwordCheck2()" placeholder="비밀번호 재확인" required>
		<span id="same2"></span> 
	</td>
	</tr>
	
	<tr>
	<td>주소 검색</td>
	<td><input type="text" id="addr4" name="addr4" value="${Addr}">
	</td>
	</tr>
	
	<tr>
	<td>검색된 주소</td>
	<td><input type="text" name="memId" onkeyup="idcheck()" id="memId" placeholder="아이디"  value="${sessionScope.id}" readonly="readonly"> 
		<span id="idcheck" style="font-size: 10px; color: red"></span>
	</td>
	</tr>
	
	<tr>
	<td>상세 주소</td>
	<td><input type="text" name="memId" onkeyup="idcheck()" id="memId" placeholder="아이디"  value="${sessionScope.id}" readonly="readonly"> 
		<span id="idcheck" style="font-size: 10px; color: red"></span>
	</td>
	</tr>
	
	<tr>
	<td>전화 번호</td>
	<td> <input type="text" name="phone1" size="5" maxlength="4" value="${phone1}"> - 
 <input type="text" name="phone2" size="5" maxlength="4" value="${phone2}"> - 
 <input type="text" name="phone3" size="5" maxlength="4" value="${phone3}">
	</td>
	</tr>
	
	<tr>
	<td>이메일</td>
	<td><input type="text" name="email1" onfocus="this.value=''" value="${Email}" id="email1">

		@ <input type="text" name="email2" value="${domain}" id="email2">

	</td>
	</tr>
	
	<tr>
	<td>생년월일</td>
	<td><input type = "date" name="birth" value="${Birth }">
	</td>
	</tr>
	
	<tr>
	<td>이름</td>
	<td><input
		type="text" name="memName" value="${Name }">
	</td>
	</tr>
	</table>
<br>
<input type="submit" value="수정완료">
</form>
</div>
</div>
</div>
</div>
	
	
