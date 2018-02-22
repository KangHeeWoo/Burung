<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/board/boardlist.css?ver=19" >
<br>
<div align="center" id="setfont">
<h1 align="center">&lt;자유게시판&gt;</h1>
<h4 align="right"><a href="board.do?cmd=boardinsert">글 등록</a></h4>
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
	
	<div>
		<c:choose>
			<c:when test="${startPage>10 }">
				<a href="<%=request.getContextPath()%>/board.do?cmd=boardlist&pageNum=${startPage-1 }">[ 이전 ]</a>
			</c:when>
			<c:otherwise>[이전]</c:otherwise>
		</c:choose>
		
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum==i }">
					<span style="color:black;">${i } </span>
				</c:when>
				<c:otherwise>
					<span style="color:gray;"><a href="<%=request.getContextPath()%>/board.do?cmd=boardlist&pageNum=${i }">${i } </a></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${endPage<pageCount }">
				<a href="<%=request.getContextPath()%>/board.do?cmd=boardlist&pageNum=${endPage+1 }">[ 다음 ]</a>
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
	
	function schClick(num){
		var url="board.do?cmd=boardlist&pageNum="+num;
		if(searchValue.value !=""){
			url+="&search="+search.value+"&searchValue="+searchValue.value;
		}
		location.href=url;
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