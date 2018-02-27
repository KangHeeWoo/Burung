<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/board/reviewinsert.css?ver=15" >
	
	<c:set var="cnt" value="0"/>
	
	<c:if test="${param.cnt!=null}">
		<c:set var="cnt" value="${param.cnt }"/>
	</c:if>
	<c:if test="${cnt>6 }">
		<c:set var="cnt" value="5"/>
	</c:if>
	
	
	<c:if test="${param.writer==null }">
		<c:set var="writer" value=""/>
		<c:set var="title" value=""/>
		<c:set var="content" value=""/>
	</c:if>

<br>
<h3 id="mainfontsize" align="center">&lt; 리뷰 게시판 &gt;</h3>
<form method="post" action="${pageContext.request.contextPath }/review.do?cmd=reviewinsertOk"  onsubmit="return setData()"  enctype="multipart/form-data">
<div id="setfont" align="center">

<table >
	<tr>
		<td>점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;수</td>
		<td><input type="number" min="1" max="5" name="revscore" value="${param.revscore }" id="score"  ></td>
	</tr>
	<tr>
		<td>작&nbsp;&nbsp;&nbsp;성&nbsp;&nbsp;자</td>
		<td><input type="text" name="memid" value="${sessionScope.id }"  readonly="readonly" id="id">
		<input type="hidden"  name="memnum" value="${memnum }">
		</td>
	</tr>
	<tr>
		<!-- 구매 렌트에 따라 다른 차종으로 불러와야함 1.구매 2.렌트 -->
	<c:choose>
		<c:when test="${param.carname==null}">
			<td>차&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;종</td>
			<td><input type="text" name="carname" value="${carname }"  readonly="readonly" id="carname"></td>
	</c:when>
	<c:otherwise>
		<td>차&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;종</td>
		<td><input type="text" name="carname" value="${param.carname }"  readonly="readonly" id="carname"></td>
	</c:otherwise>
	</c:choose>
	</tr>
	<tr>
		<td>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</td>
		<td><input type="text" name="title"  id="title"></td>
	</tr>
		<tr>
		<td>내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용</td>
		<td><textarea name="content" rows="20" cols="100" id="content"></textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td><input type="text" name="cnt" value="${cnt }" id="file">
			<input type="button" value="확인" onclick="filescore()">
		</td>
	</tr>
</table>

</div>
<div id="div">
	
</div>
<input type="submit" value="등록">
</form>
<script type="text/javascript">

	function filescore(){
		var cnt=document.getElementsByName("cnt")[0].value;
		//table만들기
		var table=document.createElement("table");
	
		
		for(var i=0;i<cnt;i++){			
			var tr=document.createElement("tr");
			var td=document.createElement("td");
			var td2=document.createElement("td");
			var input=document.createElement("input");
			td.innerHTML="파일"+(i+1);
			input.type="file";
			input.name="file"+i;
			td2.appendChild(input);
			tr.appendChild(td);
			tr.appendChild(td2);
			table.appendChild(tr);
			tr.style.height="20px";
			tr.style.fontSize="15px";
			tr.style.fontFamily="jeju";
		}
		

		

		var div=document.getElementById("div");
		div.innerHTML="";
		div.appendChild(table);
	}

</script>














