<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		
		

<%-- function email_change(){

	
	
		if(document.join.email.options[document.join.email.selectedIndex].value == '0'){

		 document.join.email2.disabled = true;

		 document.join.email2.value = "";

		}

		if(document.join.email.options[document.join.email.selectedIndex].value == '9'){

		 document.join.email2.disabled = false;

		 document.join.email2.value = "";

		 document.join.email2.focus();

		} else{

		 document.join.email2.disabled = true;

		 document.join.email2.value = document.join.email.options[document.join.email.selectedIndex].value;

		}  --%>
function email_change() {
		var email = frm.email.value;
		frm.email2.value = email;
	}

function insertCheck (){
		return check;	
	}


</script>
<h1>[ 회원가입 ]</h1>
<form method="post" name="frm" onsubmit = "return insertCheck()"
	action="<%=request.getContextPath()%>/members.do?cmd=insertOk">
	
	
아이디 : <input type="text" name="memId" onkeyup="idcheck()" id="memId"
		placeholder="아이디"> <span id="idcheck"
		style="font-size: 10px; color: red"></span> <br> 
		
		
		
비밀번호 : <input type="password" name="memPwd" id="pwd1" onkeyup="passwordCheck1()"placeholder="비밀번호"> 
		<span id="same1" style="font-size: 10px; color: red"></span> <br> 
비밀번호 확인 : <input type="password" name="memPwd2" id="pwd2" onkeyup="passwordCheck2()" placeholder="비밀번호 재확인">
		<span id="same2" style="font-size: 10px; color: red"></span> <br>
		
		
		
주소 입력 : <input type="text" id="addr2" placeholder="'구'로 입력해주세요.">
		<input	type="button" name="memAddr" id="addr1" onclick="addrInquiry()"	value="구 검색">
		<span id="Inquiry" style="font-size: 10px; color: red"></span> <br>
		검색결과 : <input type="text" id="addr3" name="addr3"><br> 
		상세주소 : <input type="text" id="addr4" name="addr4" placeholder="나머지 상세 주소"><br>
      	<%-- 주소입력란에 입력한 주소값과 버튼클릭란(DB저장되있는 주소값)을  비교하여 검색결과란에 출력 --%>

	
 전화번호 : <select name="phone1">
       <option value="010">010</option>
       <option value="011">011</option>
       <option value="016">016</option>
       <option value="017">017</option>
       <option value="019">019</option>
     </select>
     - <input type="text" name="phone2" size="5" maxlength="4"> - <input type="text" name="phone3" size="5" maxlength="4" ><br>
	
	

이메일 : <input type="text" name="email1" onfocus="this.value=''">

		@ <input type="text" id="email2">

		<select name="email" onchange="email_change()">

		    <option value="">직접입력</option>
		
		<option value="naver.com" >naver.com</option>
		<option value="hanmail.net">hanmail.net</o0ption>
		<option value="hotmail.com">hotmail.com</option>
		<option value="nate.com">nate.com</option>
		<option value="yahoo.co.kr">yahoo.co.kr</option>
		<option value="empas.com">empas.com</option>
		<option value="dreamwiz.com">dreamwiz.com</option>
		<option value="freechal.com">freechal.com</option>
		<option value="lycos.co.kr">lycos.co.kr</option>
		<option value="korea.com">korea.com</option>
		<option value="gmail.com">gmail.com</option>
		<option value="hanmir.com">hanmir.com</option>
		<option value="paran.com">paran.com</option>

		   </select><br>
<!-- 

이메일 : <input type="text" name="email1"> 
	@ <input type="text" name="email1">
	
	
	<select style="width: 100px; margin-right: 10px" name="selectEmail"	id="selectEmail">
		<option value="1">직접입력</option>
		<option value="naver.com" >naver.com</option>
		<option value="hanmail.net">hanmail.net</option>
		<option value="hotmail.com">hotmail.com</option>
		<option value="nate.com">nate.com</option>
		<option value="yahoo.co.kr">yahoo.co.kr</option>
		<option value="empas.com">empas.com</option>
		<option value="dreamwiz.com">dreamwiz.com</option>
		<option value="freechal.com">freechal.com</option>
		<option value="lycos.co.kr">lycos.co.kr</option>
		<option value="korea.com">korea.com</option>
		<option value="gmail.com">gmail.com</option>
		<option value="hanmir.com">hanmir.com</option>
		<option value="paran.com">paran.com</option>
	</select><br>
	
 -->
생년월일 : <select name="Birth1">
       <option value="2013">2013</option>
       <option value="2012">2012</option>
       <option value="2011">2011</option>
       <option value="2010">2010</option>
       <option value="2009">2009</option>
       <option value="2008">2008</option>
       <option value="2007">2007</option>
       <option value="2006">2006</option>
       <option value="2005">2005</option>
       <option value="2004">2004</option>
       <option value="2003">2003</option>
       <option value="2002">2002</option>
       <option value="2001">2001</option>
       <option value="2000">2000</option>
     </select>년&nbsp;
     <select name="Birth2">
       <option value="1">1</option>
       <option value="2">2</option>
       <option value="3">3</option>
       <option value="4">4</option>
       <option value="5">5</option>
       <option value="6">6</option>
       <option value="7">7</option>
       <option value="8">8</option>
       <option value="9">9</option>
       <option value="10">10</option>
       <option value="11">11</option>
       <option value="12">12</option>
     </select>월
     <select name="Birth3">
       <option value="1">1</option>
       <option value="2">2</option>
       <option value="3">3</option>
       <option value="4">4</option>
       <option value="5">5</option>
       <option value="6">6</option>
       <option value="7">7</option>
       <option value="8">8</option>
       <option value="9">9</option>
       <option value="10">10</option>
       <option value="11">11</option>
       <option value="12">12</option>
       <option value="13">13</option>
       <option value="14">14</option>
       <option value="15">15</option>
       <option value="16">16</option>
       <option value="17">17</option>
       <option value="18">18</option>
       <option value="19">19</option>
       <option value="20">20</option>
       <option value="21">21</option>
       <option value="22">22</option>
       <option value="23">23</option>
       <option value="24">24</option>
       <option value="25">25</option>
       <option value="26">26</option>
       <option value="27">27</option>
       <option value="28">28</option>
       <option value="29">29</option>
       <option value="30">30</option>
       <option value="31">31</option>
     </select>일<br>
	

이름<input
		type="text" name="memName" placeholder="가입자명"><br><br>
		
		
귀하는 귀신을 믿습니까? : <br><br> 믿습니다.<input type="radio" name="receive"
		value="동의한다">   앞으로 믿겠습니다. <input type="radio" name="receive"
		value="거절한다"> <br>
	<br> <input type="submit" value="회원가입">
</form>