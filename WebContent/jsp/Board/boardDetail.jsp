<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>자유게시판</h2>
<h4 align="right">
	<a href="${pageContext.request.contextPath}/board.do?cmd=boardlist">글목록</a>
</h4>

<table border="solid 1px">
	<tr>
		<td>작성자</td>
		<td>${param.memid}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${listdetail.boaTitle }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea cols="50" rows="20" name="boacontent"
				readonly="readonly">${listdetail.boaContent }</textarea></td>
	</tr>

</table>
<input type="hidden" id="num" value="${listdetail.boaNum }">
<input type="hidden" id="memid" value="${param.memid}">

	<c:if test="${sessionScope.id== param.memid}">
		<div id="updatebnt" align="center">
			<a href="javascript:boardupdate()">수정</a>
			<a href="javascript:boarddelete()">삭제</a>
		</div>
	</c:if>
	
<!--  댓글 리스트 -->
<div id="comments">
</div>
<!--  댓글 입력 -->

<div >
	<textarea rows="5" cols="30"  id="comment"></textarea>
	<input type="button" value="댓글"   onclick="addBoardComm()">
</div>


<script type="text/javascript">
	
	function boarddelete() {
		var con = confirm("해당 게시물을 삭제하시겠습니까?");
		var num = document.getElementById("num").value;
		console.log(con);
		if (con == true) {
			location.href="<%=request.getContextPath()%>/board.do?cmd=boarddelete&boanum=" + num;
			//num 파라미터 전달하기
		}
	}
	
	function boardupdate() {
		var memid=document.getElementById("memid").value;
		var con = confirm("해당 게시물을 수정하시겠습니까?");
		var num = document.getElementById("num").value;
		console.log(con);
		if (con == true) {
			location.href="<%=request.getContextPath()%>/board.do?cmd=boardupdate&memid="+memid+"&boanum=" + num;
		}
	}
	
	//댓글달기
	var xhr=null;
	function addBoardComm(){
		var comment=document.getElementById("comment").value;
		var boanum=document.getElementById("num").value;
		
		
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=insertComm;
		xhr.open("post", "<%=request.getContextPath()%>/boardcomment.do?cmd=comminsert", true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var param="id=<%=session.getAttribute("id")%>&comment="+comment+"&boanum="+boanum;
		xhr.send(param);
	}
	function insertComm(){
		if (xhr.readyState == 4 && xhr.status == 200) {
			var xml=xhr.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=="success"){
				alert("댓글등록");
			}else{
				alert("등록실패");
			}
		}
	}
	
	
	
	
</script>