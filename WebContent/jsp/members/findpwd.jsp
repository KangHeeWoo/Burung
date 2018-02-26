<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <script type="text/javascript">
	
	var xhr=null;
	
	function getResult(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		var id=document.getElementById("id").value;
		var email=document.getElementById("email").value;
		
		var url = "<%=request.getContextPath()%>/members.do?cmd=findpwd&id="+id+"&email="+email;
		
		xhr.open("get",url,true);
		xhr.send();
	}
	
function callback(){
		if(xhr.readyState==4 && xhr.status==200){
			
			
			var result = xhr.responseText;
			var data = eval('(' + result + ')');
			
			var pwd = data.pwd;
		
			var div=document.getElementById("result"); // 결과 출력하기 위한 객체
			
			if(pwd != null){
				
				div.innerHTML="비밀번호:" + pwd;
			}else{
				div.innerHTML="해당 정보가 존재하지 않습니다.";
			}
		}
	}
</script>
</head>
<body>
<h2> [ 회원 비밀번호 찾기 ] </h2>
아이디 <input type="text" id="id" ><br>
이메일 <input type="text" id="email" ><br>
<input type="button" value="PWD 찾기" onclick="getResult()">
<div id="result"></div>