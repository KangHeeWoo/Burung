<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/admin/css/bootstrap.css">
<script src="<%=request.getContextPath() %>/admin/js/bootstrap.js"></script>
<script type="text/javascript">
	function reset(){
		var title=document.getElementsByName("notTitle")[0];
		var content=document.getElementsByName("notContent")[0];
		
		title.innerHTML="";
		content.innerHTML="";
	}
</script>
</head>
<body>
<div class="container">
<table class="table table-bordered">
    <thead>
        <caption>공지사항 수정</caption>
    </thead>
    <tbody>
        <form action="<%=request.getContextPath() %>/semi/notice.do?cmd=updateOk" method="post">
            <tr>
                <th>제목: </th>
                <td><input type="text" placeholder="제목을 입력하세요. " name="notTitle" class="form-control" value="${notTitle }"/></td>
            </tr>
            <tr>
                <th>내용: </th>
                <td><textarea cols="10" rows="10" placeholder="내용을 입력하세요. " name="notContent" class="form-control">${notContent }</textarea></td>
            </tr>
            <tr>
                <td colspan="2">
               		<input type="hidden" value="${notNum }" name="notNum">
                    <input type="submit" value="등록" class="pull-right"/>
                    <input type="button" value="reset" class="pull-left" onclick="reset()"/>
                    <input type="button" value="글 목록으로... " class="pull-right" onclick="location.href='<%=request.getContextPath()%>/semi/notice.do?cmd=noticelist'"/>
                </td>
            </tr>
        </form>
    </tbody>
</table>
</div>
</body>
</html>