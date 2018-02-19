<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
	.comm{width:400px;height: 100px;border:1px solid #aaa;padding: 2px;margin-top: 3px;}
</style>


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
<div id="pageing">
</div>
<!--  댓글 입력 -->

<div >
	<textarea rows="5" cols="30"  id="comment"></textarea>
	<input type="button" value="댓글"   onclick="addBoardComm()">
</div>


<script type="text/javascript">
	window.onload = getcommlist;
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
				//입력된 댓글 지우기
				document.getElementById("comment").value="";
				//댓글 목록 불러오기
				getcommlist();
			}else{
				alert("로그인이 필요합니다.");
			}
		}
	}
	
	//댓글 리스트
	var xhr1=null;
	function getcommlist(){
		xhr1=new XMLHttpRequest();
		xhr1.onreadystatechange=commlist;
		xhr1.open("get", "<%=request.getContextPath()%>/boardcomment.do?cmd=commlist&boanum="+ document.getElementById("num").value, true);
		console.log(document.getElementById("num").value);
		xhr1.send();
	}
	
	function commlist(){
		if(xhr1.readyState==4 && xhr1.status==200){
			//기존 댓글 리스트 지우기
			var comments=document.getElementById("comments");
			comments.innerHTML="";	
			
			var xml1=xhr1.responseXML;
			var id=xml1.getElementsByTagName("id");
			var comcon=xml1.getElementsByTagName("comcon");
			var comregd=xml1.getElementsByTagName("comregd");
			var bcomnum=xml1.getElementsByTagName("bcomnum");
			
			
			for(var i=0;i<id.length;i++){
				var div=document.createElement("div");
				var n=bcomnum[i].firstChild.nodeValue;
				
				var wId = id[i].firstChild.nodeValue ;
				var html="아이디"+ wId +"<br>"+
		         "댓글:" + comcon[i].firstChild.nodeValue +"<br>" +
		         "날짜:" + comregd[i].firstChild.nodeValue +"<br>";
				
		         //세션아이디와 비교해 삭제여부
		         var id1 = "<%=session.getAttribute("id")%>";
		         if(id1==wId){
		         	html += "<a href='javascript:remove(" + n +")'>삭제</a>";
		         }
		         div.innerHTML=html;
		         div.className="comm";
				 comments.appendChild(div);
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//페이징
			
	
			
			//////////////////////////////////////////////////////////////////////////////////////////
		}
	}
	
	var xhr2=null;
	function remove(n){
		xhr2=new XMLHttpRequest();
		xhr2.onreadystatechange=deletecom;
		xhr2.open('get','<%=request.getContextPath()%>/boardcomment.do?cmd=commdelete&bcomnum='+n,true);
		xhr2.send();
	}
	function deletecom(){
		if(xhr2.readyState==4 && xhr2.status==200){
			var xml=xhr2.responseXML;
			var code=xml.getElementsByTagName("code")[0].firstChild.nodeValue;
			if(code=='success'){
				getcommlist();
			}else{
				alert("삭제실패");
			}
		}	
	}
	
	
</script>