<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script
	src="<%=request.getContextPath()%>/admin/js/billboard.pkgd.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/admin/css/billboard.min.pkgd.css">

</head>
<body>
	<input type="date" name="sDate" id="sDate">
	<input type="date" name="eDate" id="eDate">
	<input type="button" id="showbtn" value="확인">
	<h2>차량렌트통계</h2>
	<div id="BarChart"></div>
</body>
<script type="text/javascript">
$(document).ready(function(){
$.ajax({
	 url : '<%=request.getContextPath()%>/semi/statistics.do?cmd=start',
	 type : 'POST',
	 data : { },
	 dataType : 'json',
	 success : function(data){
		 console.log(data);
	 	 var dataArr = new Array();
	 	 for(var i =0 ; i < data.length ; i++){
	 		 var row = data[i];
	 		 var smallArr = new Array();
	 		 smallArr.push(row.rcarName);
	 		 smallArr.push(row.cnt);
	 		 
	 		 dataArr.push(smallArr)
	 	 }
	 	 console.log(dataArr);
	 	 
		 bb.generate({
		  data: {
			    columns: dataArr,
			    type: "bar"
			  },
			  bar: {
			    width: {
			      ratio: 0.3
			    }
			  },
			  bindto: "#BarChart"
			})
		},
		error : function(data){
		  alert("에러 : " + data)
		} 
	});	
})

$('#showbtn').click(function(){
	$.ajax({
		 url : '<%=request.getContextPath()%>/semi/statistics.do?cmd=show',
		 type : 'POST',
		 data : {'sDate':$('#sDate').val(),'eDate':$('#eDate').val() },
		 dataType : 'json',
		 success : function(data){
			 console.log(data);
		 	 var dataArr = new Array();
		 	 for(var i =0 ; i < data.length ; i++){
		 		 var row = data[i];
		 		 var smallArr = new Array();
		 		 smallArr.push(row.rcarName);
		 		 smallArr.push(row.cnt);
		 		 
		 		 dataArr.push(smallArr)
		 	 }
		 	 console.log(dataArr);
			 bb.generate({
			  data: {
				    columns: dataArr,
				    type: "bar"
				  },
				  bar: {
				    width: {
				      ratio: 0.3
				    }
				  },
				  bindto: "#BarChart"
				})
			},
			error : function(data){
			  alert("에러 : " + data)
			} 
		});	
})


</script>
</html>