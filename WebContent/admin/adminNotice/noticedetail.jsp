<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.bbs-table {
width: 100%;
margin: 0.7em 0 0 0;
}
.bbs-table th {
	color: #92B91C;
	border-top: 1px solid #92B91C;
	border-bottom: 1px solid #92B91C;
}
.bbs-table td {
	padding-top: 3px;
	padding-bottom: 3px;
	border-bottom: 1px solid silver;
	line-height: 1.45;
}
.bbs-table td a {
	color: #555;
	text-decoration: none;
}
.bbs-table td a:hover {
	color:#555;
	text-decoration: underline;
}
#article-content {
    font-size: 0.9em;
    color: #333;
}
.attach-file {
    font-size: 11px;
    line-height: 1.3;
}
#date-writer-hit {
    display: block;
    margin: 0;
    padding: 0;
    font-size: 11px;
    color: #555;
    text-align: right;
}
</style>
</head>
<body>
<table class="bbs-table">
<tr>
    <th style="width: 47px;text-align: left;vertical-align: top;font-size: 1em;">TITLE</th>
    <th style="text-align: left;color: #555;font-size: 1em;">${notTitle }</th>
  
</tr>
</table>
<div id="detail">
    <div id="date-writer-hit">${notRegd } by 홍길동 hit ${notHit }</div>
    <div id="article-content">
    ${notContent }
    </div>
</div>
</body>
</html>