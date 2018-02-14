<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/introCompany.css">
<script type="text/javascript">
	function xy(event){
		var x = event.clientX;
		var y = event.clientY;
		var div = document.getElementById("mapCompany");
		alert("x좌표:"+ x + "y좌표:" + y);
	}
	function mark1(){
		var mark1 = document.getElementById("mark1");
		mark1.style.display = "block";
	}
	
	
	
	
</script>

</head>
<body>
<div id = mapCompany onclick="xy(event)">
	<img id = "company" src = "../img/company.png">
	<div id = mapWrap>
		<div id = map>
			<img src = "../img/mapCompany.png">	
		</div>
		<div id = "map1">
			<img id = "mark1" src = "../img/mark3.png" onclick="mark1()">
		</div>
		<div id = "map2">
			<img id = "mark2" src = "../img/mark3.png" onclick="mark2()">
		</div>
		<div id = "map3">
			<img id = "mark3" src = "../img/mark3.png" onclick="mark3()">
		</div>
		<div id = "map4">
			<img id = "mark4" src = "../img/mark3.png" onclick="mark4()">
		</div>
		<div id = "map5">
			<img id = "mark5" src = "../img/mark3.png" onclick="mark5()">
		</div>
	</div>
</div>
</body>
</html>