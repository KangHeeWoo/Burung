<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/board/reviewlist.css?ver=8" >
<!--

 var checked = document.querySelectorAll("#checks input[type='checkbox']:checked");
for(var i=0; i<checked.length; i++){
    str += checked[i].value + " ";
}

 -->
 
<br>
<div align="center" id="setfont">
<h1 align="center">&lt;후기게시판&gt;</h1><br>

<!-- 세션 아이디의 마이페이지 : 물어보아요!!!!!!-->
<h4 align="right"><a href="members.do?cmd=listpage">글 등록</a></h4>

<form name="checkbox" method="post"  style="width: 800px">
<!--  carlistFont 스타일-->
	<input type="hidden" name="cheCar" value="${param.cheCar }">
	<input name="ch_box" type="checkbox" value="0" onclick="carAll(1)" />전체<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="718" onclick="carsearch(1)"/>718<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="911" onclick="carsearch(1)"/>911<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="panamera" onclick="carsearch(1)"/>panamera<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="macan" onclick="carsearch(1)"/>macan<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="cayenne" onclick="carsearch(1)"/>cayenne<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
</form>
<div align="right">
<select id="searchBy" onchange="orderby(1)">
		<option value="revnum">==정렬==</option>
		<option value="revhit">조횟수</option>
		<option value="revscore">후기점수</option>
</select><br>
<c:choose>
	<c:when test="${param.searchBy != null || param.searchBy != ''}">
		<c:set var="searchBy" value="${param.searchBy }"/>
	</c:when>
	<c:otherwise>
		<c:set var="searchBy" value="revnum"/>
	</c:otherwise>
</c:choose>
<input type="hidden" id="choiceBy" value="${searchBy }">
</div>
<br>

<div align="center">
	<table class="type09">
		<tr>
			<th class="cols" id="id">아이디</th><th  class="cols" id="car">차종</th><th  class="cols" id="title">제목</th><th  class="cols" id="regd">입력일</th><th  class="cols" id="hit">조회수</th><th  class="cols" id="score">평점</th>
		</tr>
		

		
		
		<c:forEach var="review" items="${listAll }">
		<tr onclick="reviewdetail(${review.revNum})" onmouseover="fontborder(event)" onmouseout="fontout(event)">
			<td  class="row">${review.memId }</td>
			<td class="row">${review.carname }</td>
			<%--<td><a href="board.do?cmd=boardDetail&boanum=${board.boanum }&memid=${board.memid}">${board.boatitle }</a></td>--%>
			<td class="row">${review.revTitle }</td>
			<td class="row" >${review.revRegd}</td>
			<td class="row" >&nbsp;&nbsp;&nbsp;&nbsp;${review.revHit}</td>
			<td class="row"><c:forEach var="score" begin="1" end="${review.revScore }">★</c:forEach></td>
		</tr>
		</c:forEach>
	</table><br>

	<!-- 페이징 처리 -->
	
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="<%=request.getContextPath()%>/review.do?cmd=reviewlist&pageNum=${startPage-1 }">[ 이전 ]</a>
			</c:when>
			<c:otherwise>[이전]</c:otherwise>
		</c:choose>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<span style="color:black;">${i } </span>
				</c:when>
				<c:otherwise>
					<span style="color:gray;"><a href="<%=request.getContextPath()%>/review.do?cmd=reviewlist&pageNum=${i }">${i } </a></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="<%=request.getContextPath()%>/review.do?cmd=reviewlist&pageNum=${endPage+1 }">[ 다음 ]</a>
			</c:when>
			<c:otherwise>[ 다음 ]</c:otherwise>
		</c:choose>
	</div>
	<br>
	<select id="search">
		<option value="s0">==선택하세요==</option>
		<option value="revtitle">제목</option>
		<option value="memid">작성자</option>
		<option value="carname">차종</option>
	</select>
	<input type="text" id="searchValue" value="${searchValue}">
	<input type="button" value="검색" onclick="schClick(1)">
	<input type="hidden" id="choice" value="${search }">
</div>


</div>




<script type="text/javascript">
	var search=document.getElementById("search");
	var searchValue=document.getElementById("searchValue");
	var choice=document.getElementById("choice");
	
	var choiceBy=document.getElementById("choiceBy");
	var searchBy=document.getElementById("searchBy");
	var cheCar = "";
	
	function orderby(num){
		var url="review.do?cmd=reviewlist&pageNum="+num;
		if(searchBy.value!=""){
			url+="&searchBy="+searchBy.value;
		}
		if(searchValue.value !=""){
			url+="&search="+search.value+"&searchValue="+searchValue.value;
		}
		location.href=url;
	}
	
	//나중에 검색조건들어올 자리
	function schClick(num){
		var url="review.do?cmd=reviewlist&pageNum="+num;
		if(searchBy.value!=""){
			url+="&searchBy="+searchBy.value;
		}
		if(searchValue.value !=""){
			url+="&search="+search.value+"&searchValue="+searchValue.value;
		}
		location.href=url;
	}
	
	window.onload=function(){
		for(var i=0;i<search.length;i++){
			if(search[i].value==choice.value){
				search[i].selected="selected";
			}
		}
		
		for(var i=0;i<searchBy.length;i++){
			if(searchBy[i].value==choiceBy.value){
				searchBy[i].selected="selected";
			}
		}
		
		var cheValue = checkbox.cheCar.value;
		var ch_box = checkbox.ch_box;
		
		console.log(cheValue);
		
		if(cheValue == 'All' || cheValue == '' || cheValue == null){
			for(var i=0;i<ch_box.length;i++){
				ch_box[i].checked = "checked";
			}
		}else{
			for(var i=0;i<ch_box.length;i++){
				if(cheValue.indexOf(ch_box[i].value) != -1){
					ch_box[i].checked = "checked";
				}
			}
		}
	}
	
	function reviewdetail(revnum){
		location.href="review.do?cmd=reviewdetaile&revnum="+revnum;
	}
	
	
	function fontborder(event){
		event.target.parentElement.style.fontWeight="bold";
		
	}
	function fontout(event){
		event.target.parentElement.style.fontWeight="normal";
		
	}
	
	
	//전체 체크박스
	function carAll(num){
		var ch_box=document.getElementsByName("ch_box");
		cheCar = "All";
		
		if(ch_box[0].checked == true){
			for(var i=1;i<ch_box.length;i++){
				ch_box[i].checked = true;
			}
		}else{
			for(var i=1;i<ch_box.length;i++){
				ch_box[i].checked = false;
			}
		}
		var url="review.do?cmd=reviewlist&pageNum="+num+"&cheCar="+cheCar;
		//console.log(cheCar);
	}
	
	//체크박스 
	function carsearch(num){
		var ch_box=document.getElementsByName("ch_box");
		var cnt = 0;
		var valCnt = 0;
		cheCar = "";
		
		for(var i=1;i<ch_box.length;i++){
			if(ch_box[i].checked == true){
				cnt++;
			}	
		}
		
		if(cnt == 5) ch_box[0].checked = true;
		else ch_box[0].checked = false;
		
		
		if(ch_box[0].checked == true || cnt==0){
			cheCar = "All";
		}else{
			for(var i=1;i<ch_box.length;i++){
				if(ch_box[i].checked == true){					
					cheCar += ch_box[i].value;
					if(++valCnt != cnt){
						cheCar += ":";
					}
				}
			}
		}
		var url="review.do?cmd=reviewlist&pageNum="+num+"&cheCar="+cheCar;
		
		location.href=url;
		//console.log(cheCar);
	}
</script>