<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src = "<%=request.getContextPath() %>/admin/js/billboard.pkgd.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/admin/css/billboard.min.pkgd.css">

</head>
<body>
<div id="BarChart"></div>
</body>
<script type="text/javascript">
var chart = bb.generate({
	  data: {
	    columns: [
		["data1", 30, 200, 100, 400, 150, 250],
		["data2", 130, 100, 140, 200, 150, 50]
	    ],
	    type: "bar"
	  },
	  bar: {
	    width: {
	      ratio: 0.5
	    }
	  },
	  bindto: "#BarChart"
	});
	
$.ajax({
	 url : "<%= request.getContextPath() %>/semi/statistics.do",
	 type : 'POST',
	 data : { },
	 dataType : 'json',
	 success : function(data){
		 for(var i=0; i < data.length ; i++){
		     var row = data[i];
		     console.log( "차이름 : " + row.rcarName + " & cnt? : " + row.cnt );
		 }
	 },
	 error : function(data){
	  alert("ajax를 호출하는데 에러가 발생하였습니다 : " + data)
	 } 
	})

</script>
</html>