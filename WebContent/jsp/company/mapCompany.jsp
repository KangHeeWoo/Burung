<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="../css/introCompany.css?ver=178"/>
<script type="text/javascript">

function xy(event){
	var x = event.clientX;
	var y = event.clientY;
	var div = document.getElementById("mapCompany");
	var popup = document.getElementById("popup");
	//popup.style.left = (x+5)+"px";
	//popup.style.top = (y+225)+"px";
	//alert("x좌표:"+ x + "y좌표:" + y);
	//var bounce = element.classList.remove("bounce");
	//element.offsetWidth = element.offsetWidth;
	//element.classList.add("bounce");
}


	var xhr = null;
	function showPopup1(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = popup1;
		xhr.open('get','../company.do?cmd=popup1&offnum=2',true);
		xhr.send();
	}
	function showPopup2(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = popup1;
		xhr.open('get','../company.do?cmd=popup1&offnum=4',true);
		xhr.send();
	}
	function showPopup3(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = popup1;
		xhr.open('get','../company.do?cmd=popup1&offnum=6',true);
		xhr.send();
	}
	function showPopup4(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = popup1;
		xhr.open('get','../company.do?cmd=popup1&offnum=8',true);
		xhr.send();
	}
	function showPopup5(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = popup1;
		xhr.open('get','../company.do?cmd=popup1&offnum=10',true);
		xhr.send();
	}
		
	function popup1(){
		if(xhr.readyState==4 && xhr.status==200){
			
			var xml = xhr.responseXML;
			var name = xml.getElementsByTagName("name")[0].firstChild.nodeValue;
			var addr = xml.getElementsByTagName("addr")[0].firstChild.nodeValue;
			var tel = xml.getElementsByTagName("tel")[0].firstChild.nodeValue;
			var email = xml.getElementsByTagName("email")[0].firstChild.nodeValue;
			var info = xml.getElementsByTagName("info")[0].firstChild.nodeValue;
			
			var popup = document.getElementById("popup");
			popup.style.display = "block"; // 팝업창 화면에 보이기
			//alert("나와라");
			
			popup.innerHTML = name + "<br>" + "위치 : " + addr + "<br>" +
			"전화번호 : " + tel + "<br>" + "이메일 : " + email + "<br>" + "매장정보 : " + info + "<br>" +
			"<a href=javascript:outPopup()>닫기</a>";
			
			//var por = document.getElementById("por");
			//por.style.display = "block";
		}	
	}
	function outPopup(){
		var popup=document.getElementById("popup");
		popup.style.display="none";
		var por = document.getElementById("por");
		por.style.display = "none";
	}

</script>

<img id = "company" src = "../img/company.png">

<div id = "mapCompany" onclick="xy(event)">
	<div id = "map">
		<img src = "../img/mapCompany.png">	
	</div>
	<div id = "mark1">
		<img src = "../img/mark3.png" onclick="showPopup1()" >
	</div>
	<div id = "mark2">
		<img src = "../img/mark3.png" onclick="showPopup2()">
	</div>
	<div id = "mark3">
		<img src = "../img/mark3.png" onclick="showPopup3()">
	</div>
	<div id = "mark4">	
		<img src = "../img/mark3.png" onclick="showPopup4()">
	</div>
	<div id = "mark5">
		<img src = "../img/mark3.png" onclick="showPopup5()">
	</div>
	<div id = "popup"></div>
</div>

		
		
		
		
		
		
		
		