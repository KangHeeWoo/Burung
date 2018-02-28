<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	
	var xhr=null;
	
	function getResult(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		var name=document.getElementById("name").value;
		var email=document.getElementById("email").value;
		
		var url = "<%=request.getContextPath()%>/members.do?cmd=findid&name="+name+"&email="+email;
		
		xhr.open("get",url,true);
		xhr.send();
	}
	
	
function callback(){
		if(xhr.readyState==4 && xhr.status==200){
			
			
			var result = xhr.responseText;
			var data = eval('(' + result + ')');
			
			var id = data.id;
			
			var div=document.getElementById("result"); // 결과 출력하기 위한 객체
			
			if(id != null){
				
				div.innerHTML="아이디:" + id;
			}else{
				div.innerHTML="해당 정보가 존재하지 않습니다.";
			}
		}
	}
</script>
</head>
<body>
<h2> [ 회원 아이디 찾기 ] </h2>
이름 <input type="text" id="name" ><br>
이메일 <input type="text" id="email" ><br>
<input type="button" value="ID 찾기" onclick="getResult()">
<div id="result"></div>