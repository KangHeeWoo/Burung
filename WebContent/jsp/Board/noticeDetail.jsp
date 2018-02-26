<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>
	/css/board/noticedetail.css?ver=1s" >
<div align="center" id="detail">

<h4 align="right">
	<a href="${pageContext.request.contextPath}/notice.do?cmd=noticelist">글목록</a>
</h4>

<table id="table">
	<tr>
		<td class="title" id="title">&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;목</td>
		<td id="content">${detail.notTitle }</td>
	</tr>
	<tr><td colspan="2"><hr></td></tr>
	<tr><td colspan="2"><div align="right" id="name">입력일 _ ${detail.notRegd}&nbsp;&nbsp;/&nbsp;&nbsp;작성자 _  ${param.memName}
</div></td></tr>
	<tr><td colspan="2"></td></tr>
	<tr>
		<td colspan="2"><div align="center"><textarea cols="100" rows="20" name="boacontent"
				readonly="readonly">${detail.notContent }</textarea></div></td>
	</tr>

</table>
<input type="hidden" id="num" value="${detail.notNum }">
<input type="hidden" id="memid" value="${param.memName}">


<div id="pageing">
</div>

</div>

<script type="text/javascript">
	window.onload = getcommlist;
	function boarddelete() {
		var con = confirm("해당 게시물을 삭제하시겠습니까?");
		var num = document.getElementById("num").value;
		console.log(con);
		if (con == true) {
			location.href="<%=request.getContextPath()%>/notice.do?cmd=noticedelete&notnum=" + num;
			//num 파라미터 전달하기
		}
	}
	
	function boardupdate() {
		var memName=document.getElementById("memName").value;
		var con = confirm("해당 게시물을 수정하시겠습니까?");
		var num = document.getElementById("num").value;
		console.log(con);
		if (con == true) {
			location.href="<%=request.getContextPath()%>/notice.do?cmd=noticeupdate&memName="+memName+"&notNum=" + num;
		}
	}
	
</script>