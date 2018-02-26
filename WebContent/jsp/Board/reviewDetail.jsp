<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>
	/css/board/reviewdetail.css?ver=4" >

<div align="center" id="detail">

<br>
<h4 align="right">
	<a href="${pageContext.request.contextPath}/review.do?cmd=reviewlist">글목록</a>
</h4>

<table  id="table">
	<tr>
		<td  class="title" id="title">&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;목</td>
		<td id="content">${reviewdetail.revTitle }</td>
	</tr>
	<tr>
		<td class="title" >&nbsp;&nbsp;차&nbsp;&nbsp;&nbsp;종</td>
		<td>${reviewdetail.carName }
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div class="title"  id="score">점&nbsp;&nbsp;&nbsp;수</div>&nbsp;&nbsp;&nbsp;&nbsp;${reviewdetail.revScore }
		</td>
	</tr>
	
	<tr><td colspan="2"><hr></td></tr>
	
	<tr><td colspan="2"><div align="right" id="name">입력일 _ ${reviewdetail.revRegd}&nbsp;&nbsp;/&nbsp;&nbsp;작성자 _  ${reviewdetail.memId}</div></td></tr>
	<tr>
		<td colspan="2"><div align="center"><textarea cols="100" rows="20" name="boacontent"
				readonly="readonly">${reviewdetail.revContent }</textarea></div></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><c:forEach var="car" items="${recarimglist }">
				<img id="img" src="<%=request.getContextPath() %>/jsp/Board/img/${car}" ><br><br>
				</c:forEach></td>
	</tr>

</table>
<input type="hidden" id="num" value="${reviewdetail.revNum }">
<input type="hidden" id="memid" value="${reviewdetail.memId}">

	<c:if test="${sessionScope.id==reviewdetail.memId}">
		<div id="updatebnt" align="center">
		
		
			<!-- <a href="javascript:reviewupdate()">수정</a> -->
			
			<a href="javascript:reviewdelete()">삭제</a>
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
<br><br>
<div >
<table><tr><td id="ccco">
	<textarea rows="5" cols="97"  id="comment"  placeholder="30자 이내로 작성해주세요."></textarea></td>
	<td><input type="button" value="등록"   onclick="addBoardComm()" id="bnt"></td></tr>
</table>
</div>


</div>

<script type="text/javascript">
	window.onload = getcommlist;
	function reviewdelete() {
		var con = confirm("해당 게시물을 삭제하시겠습니까?");
		var num = document.getElementById("num").value;
		console.log(con);
		if (con == true) {
			location.href="<%=request.getContextPath()%>/review.do?cmd=reviewdelete&revnum=" + num;
			//num 파라미터 전달하기
		}
	}
	
	//function reviewupdate() {
	//	var memid=document.getElementById("memid").value;
	//	var con = confirm("해당 게시물을 수정하시겠습니까?");
	//	var num = document.getElementById("num").value;
	//	console.log(con);
	//	if (con == true) {
	<%--		location.href="<%=request.getContextPath()%>/review.do?cmd=reviewupdate&memid="+memid+"&revnum=" + num;--%>
	//	}
	//}
	
	//댓글달기
	var xhr=null;
	function addReviewComm(){
		var comment=document.getElementById("comment").value;
		var revnum=document.getElementById("num").value;
		if(comment.length>=30){
			alert("댓글 사이즈를 준수해 주십시오");
			document.getElementById("comment").value="";
			return;
			
		}
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=insertComm;
		xhr.open("post", "<%=request.getContextPath()%>/reviewcomment.do?cmd=comminsert", true);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var param="id=<%=session.getAttribute("id")%>&comment="+comment+"&revnum="+revnum;
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
		xhr1.open("get", "<%=request.getContextPath()%>/reviewcomment.do?cmd=commlist&revnum="+ document.getElementById("num").value, true);
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
			var rcomnum=xml1.getElementsByTagName("rcomnum");
			
			
			for(var i=0;i<id.length;i++){
				var div=document.createElement("div");
				var n=rcomnum[i].firstChild.nodeValue;
				
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
		xhr2.open('get','<%=request.getContextPath()%>/reviewcomment.do?cmd=commdelete&rcomnum='+n,true);
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