<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/board/boardlist.css?ver=19" >
<br>
<div align="center" id="setfont">
<h1 align="center">&lt;자유게시판&gt;</h1><br><br>
<h4 align="right"><a href="board.do?cmd=boardinsert">글 등록</a></h4><br>
	<table class="type09">
		<tr>
			<th class="cols"  id="id">아이디</th><th class="cols" id="title" >제목</th><th class="cols"  id="regd" align="center">입력일</th><th class="cols" id="hit" align="center">조회수</th>
		</tr>
		<c:forEach var="board" items="${listAll }">
		<tr onclick="boarddetail(${board.boanum},'${board.memid}')" onmouseover="fontborder(event)" onmouseout="fontout(event)">
			<td scope="row">${board.memid }</td>
			<td scope="row">${board.boatitle }</td>
			<td scope="row"  align="center">${board.boaRegd }</td>
			<td scope="row"  align="center">${board.boahit }</td>
		</tr>
		</c:forEach>
	</table><br>
	<!-- 페이징 처리 -->
	
	
	<!-- 페이지 이동시 검색조건, 검색어 갖고 이동할 수 있도록 로직 처리 필요 -->
	<!-- 페이지 번호 클릭시 페이지 이동이 아니라 javascript로 이동, 검색조건이 있는지 확인하고 이에 따라 if 처리 -->
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<!-- 수정 필요 -->
				<a href="javascript:callPage(${startPage-1 })">[ 이전 ]</a>
			
			</c:when>
			<c:otherwise>[이전]</c:otherwise>
		</c:choose>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<span style="color:black;">${i } </span>
				</c:when>
				<c:otherwise>
					<!-- 수정 필요 -->
					<span style="color:gray;"><a href="javascript:callPage(${i})">${i }</a></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<!-- 수정 필요 -->
				<a href="javascript:callPage(${endPage + 1 })">[ 다음 ]</a>
				
			</c:when>
			<c:otherwise>[ 다음 ]</c:otherwise>
		</c:choose>
	</div>
	<br>
	<select id="search">
		<option value="s0">==선택하세요==</option>
		<option value="boatitle">제목</option>
		<option value="memid">작성자</option>
	</select>
	<input type="text" id="searchValue" value="${searchValue}">
	<input type="button" value="검색" onclick="schClick(1)">
	<input type="hidden" id="choice" value="${search }">
</div>

<script type="text/javascript">
	var search=document.getElementById("search");
	var searchValue=document.getElementById("searchValue");
	var choice=document.getElementById("choice");
	
	function callPage(num){
		var url="<%=request.getContextPath()%>/board.do?cmd=boardlist&pageNum="+num;
		
		if(searchValue.value!=""){
			url +="&search="+search.value+"&searchValue="+searchValue.value;
		}
		location.href=url;
	}
	
	function schClick(num){
		if(search.value == "s0"){
			alert("검색 조건을 선택해 주세요");
		}else{
			var url="board.do?cmd=boardlist&pageNum="+num;
			if(searchValue.value !=""){
				url+="&search="+search.value+"&searchValue="+searchValue.value;
			}
			location.href=url;
		}
	}
	window.onload=function(){
		for(var i=0;i<search.length;i++){
			console.log(choice.value);
			console.log(search[i].value);
			if(search[i].value==choice.value){
				search[i].selected="selected";
			}
		}
	}
	
	function boarddetail(boanum,memid){
		location.href="board.do?cmd=boardDetail&boanum="+boanum+"&memid="+memid;
	}
	
	function fontborder(event){
		event.target.parentElement.style.fontWeight="bold";
		
	}
	function fontout(event){
		event.target.parentElement.style.fontWeight="normal";
		
	}
</script>