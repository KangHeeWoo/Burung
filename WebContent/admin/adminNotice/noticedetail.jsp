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
    height: 400px;
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
#view-menu {
    height: 22px;
}
.fl {
    float: left;
}
.fr {
    float: right;
}
</style>
<script type="text/javascript">
</script>
</head>
<body>
<div class="view-menu">
 <div class="fr">
        <input type="button" value="수정" id="update" onclick="location.href='<%=request.getContextPath()%>/semi/notice.do?cmd=update&notNum=${param.notNum }'"/>
        <input type="button" value="삭제" id="delete"  onclick="location.href='<%=request.getContextPath()%>/semi/notice.do?cmd=delete&notNum=${param.notNum }'"/>
        <input type="button" value="목록" onclick="location.href='<%=request.getContextPath()%>/semi/notice.do?cmd=noticelist'"/>
 </div>
 </div>
<table class="bbs-table">
<tr>
    <th style="width: 47px;text-align: left;vertical-align: top;font-size: 1em;">TITLE</th>
    <th style="text-align: left;color: #555;font-size: 1em;">${notTitle }</th>
  
</tr>
</table>
<div id="detail">
    <div id="date-writer-hit">${notRegd } by ${memName} hit ${notHit }</div>
    <div id="article-content">
    ${notContent }
    </div>
</div>
</body>
</html>