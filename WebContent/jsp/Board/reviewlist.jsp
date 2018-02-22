<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--

 var checked = document.querySelectorAll("#checks input[type='checkbox']:checked");
for(var i=0; i<checked.length; i++){
    str += checked[i].value + " ";
}

 -->
 

<h1 align="center">후기게시판</h1><br><br>


<form name="checkbox" method="post" onsubmit="return chBox();" style="width: 800px">
<!--  carlistFont 스타일-->
	<span id="carlistFont">&lt;차종&gt;</span><br>
	<input name="ch_box" type="checkbox" value="0" checked="checked"/>전체<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="1"/>718<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="2"/>911<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="3"/>paramera<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="4"/>marcan<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
	<input name="ch_box" type="checkbox" value="5"/>cayenne<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
</form>
<div align="right">
<select id="searchBy" onchange="orderby(1)">
		<option value="revnum">==정렬==</option>
		<option value="revhit">조횟수</option>
		<option value="revscore">후기점수</option>
</select>
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

<h5 align="right"><a href="${pageContext.request.contextPath }/review.do?cmd=loginOk">글 등록</a></h5>


<div align="center">
	<table border="1" >
		<tr>
			<th>회원아이디</th><th>차종</th><th>제목</th><th>입력일</th><th>조회수</th><th>평점</th>
		</tr>
		
		<!-- ---------------------------------------------------------------------------------- -->
		
		
		<c:forEach var="review" items="${listAll }">
		<tr onclick="reviewdetail(${review.revNum})">
			<td>${review.memId }</td>
			<td>${review.carname }</td>
			<%--<td><a href="board.do?cmd=boardDetail&boanum=${board.boanum }&memid=${board.memid}">${board.boatitle }</a></td>--%>
			<td>${review.revTitle }</td>
			<td>${review.revRegd}</td>
			<td>${review.revHit}</td>
			<td><c:forEach var="score" begin="1" end="${review.revScore }">★</c:forEach></td>
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





<script type="text/javascript">
	var search=document.getElementById("search");
	var searchValue=document.getElementById("searchValue");
	var choice=document.getElementById("choice");
	
	var choiceBy=document.getElementById("choiceBy");
	var searchBy=document.getElementById("searchBy");
	
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
		
		for(var i=0;i<searchBy.length;i++){
			console.log(choiceBy.value)
			console.log(searchBy[i].value);
			if(searchBy[i].value==choiceBy.value){
				searchBy[i].selected="selected";
			}
		}
	}
	
	function reviewdetail(revnum){
		location.href="review.do?cmd=reviewdetaile&revnum="+revnum;
	}
</script>