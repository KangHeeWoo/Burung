<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>
	/css/board/boarddetail.css?ver=25" >

<div align="center" id="detail">

<br>
<h4 align="right">
	<a href="${pageContext.request.contextPath}/board.do?cmd=boardlist">글목록</a>
</h4>

<table id="table">
	<tr>
		<td class="title" id="title">&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;목</td>
		<td id="content">${listdetail.boaTitle }</td>
	</tr>
	<tr><td colspan="2"><hr></td></tr>
	<tr><td colspan="2"><div align="right" id="name">입력일 _ ${listdetail.boaRegd}&nbsp;&nbsp;/&nbsp;&nbsp;작성자 _  ${param.memid}</div></td></tr>
	<tr><td colspan="2"></td></tr>
	<tr>
		<td colspan="2"><div align="center"><textarea cols="100" rows="20" name="boacontent"
				readonly="readonly">${listdetail.boaContent }</textarea></div></td>
	</tr>

</table>
<input type="hidden" id="num" value="${listdetail.boaNum }">
<input type="hidden" id="memid" value="${param.memid}">
<br><br><br>
	<c:if test="${sessionScope.id== param.memid}">
		<div id="updatebnt" align="center">
			<a href="javascript:boardupdate()">수정 | </a>
			<a href="javascript:boarddelete()">삭제</a>
		</div>
		<br><br><br>
	</c:if>
	<hr width="750px">
	
<!--  댓글 리스트 -->
<div id="comments">
</div>
<div id="pageing">
</div>
<!--  댓글 입력 -->

<div >
<table><tr><td id="ccco">
	<textarea rows="5" cols="97"  id="comment"  placeholder="30자 이내로 작성해주세요."></textarea></td>
	<td><input type="button" value="등록"   onclick="addBoardComm()" id="bnt"></td></tr>
</table>
</div>

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
		if(comment.length>=30){
			alert("댓글 사이즈를 준수해 주십시오");
			document.getElementById("comment").value="";
			return;
			
		}
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
				//alert("댓글등록");
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
				
				var ddiv=document.createElement("div");
				var span=document.createElement("span");
				
				var wId = id[i].firstChild.nodeValue ;
				var html="아이디: "+ wId +"<br>"+
		          comcon[i].firstChild.nodeValue +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		          span.innerHTML="날짜:" + comregd[i].firstChild.nodeValue +"&nbsp;&nbsp;&nbsp;&nbsp;";
				span.className="Date";
		         //세션아이디와 비교해 삭제여부
		         var id1 = "<%=session.getAttribute("id")%>";
		         div.innerHTML=html;
		         if(id1==wId){
		         	span.innerHTML += "<a href='javascript:remove(" + n +")'>삭제</a>";
		         }
		         ddiv.appendChild(span);
		         div.appendChild(ddiv);
		         ddiv.className="div";
		         div.className="comm";
		         div.align="left";
				 comments.appendChild(div);
				 comments.innerHTML+="<hr width='750px'>"
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