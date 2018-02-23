<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>공지사항</h2>
<h4 align="right">
	<a href="${pageContext.request.contextPath}/board.do?cmd=noticelist">글목록</a>
</h4>

<table border="solid 1px">
	<tr>
		<td>작성자</td>
		<td>${param.memName}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${detail.notTitle }</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${detail.notRegd }</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${detail.notHit }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea cols="50" rows="20" name="boacontent"
				readonly="readonly">${detail.notContent }</textarea></td>
	</tr>

</table>
<input type="hidden" id="num" value="${detail.notNum }">
<input type="hidden" id="memid" value="${param.memName}">


<div id="pageing">
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