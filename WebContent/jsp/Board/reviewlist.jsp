<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 align="center">후기게시판</h1><br><br>


//등록시 마이페이지로 이동

<h5 align="right"><a href="#">글 등록</a></h5>


<div align="center">
	<table  width="500" border="1" cellspacing="0" cellpadding="3" bordercolor="#999999" style='border-collapse:collapse'>
		<tr>
			<th>회원아이디</th><th>차종</th><th>제목</th><th>입력일</th><th>조회수</th>
		</tr>
		
		<!-- ---------------------------------------------------------------------------------- -->
		
		
		<c:forEach var="review" items="${listAll }">
		<tr>
			<td>${review.memid }</td>
			<td>${review.carname }</td>
			<!--  <td><a href="board.do?cmd=boardDetail&boanum=${board.boanum }&memid=${board.memid}">${board.boatitle }</a></td>-->
			
			<td>${review.revtitle }</td>
			<td>${review.revregd}</td>
			<td>${review.revhit}</td>
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
					<span style="color:black;">${i } | </span>
				</c:when>
				<c:otherwise>
					<span style="color:gray;"><a href="<%=request.getContextPath()%>/review.do?cmd=reviewlist&pageNum=${i }">${i } | </a></span>
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
</script>