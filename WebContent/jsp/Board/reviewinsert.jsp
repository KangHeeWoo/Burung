<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
<form method="post" action="${pageContext.request.contextPath }/review.do?cmd=reviewinsertOk"  onsubmit="return setData()"  enctype="multipart/form-data">

	<table border="1" width="500">
		<tr>
			<td>점수</td>
			<td><input type="number" min="1" max="5" name="revscore" value="${param.revscore }"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="memid" value="${sessionScope.id }"  readonly="readonly">
					<input type="hidden"  name="memnum" value="${memnum }">
			</td>
		</tr>
		<tr>
		<!-- 구매 렌트에 따라 다른 차종으로 불러와야함 1.구매 2.렌트 -->
		<c:choose>
			<c:when test="${param.carname==null}">
				<td>차종</td>
				<td><input type="text" name="carname" value="${carname }"  readonly="readonly"></td>
			</c:when>
			<c:otherwise>
				<td>차종</td>
				<td><input type="text" name="carname" value="${param.carname }"  readonly="readonly"></td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" ></td>
		</tr>
			<tr>
			<td>내용</td>
			<td><textarea name="content" rows="5" cols="50"></textarea></td>
		</tr>
			<tr>
			<td>첨부파일갯수</td>
			<td><input type="text" name="cnt" value="${cnt }">
				<input type="button" value="확인" onclick="filescore()">
			</td>
		</tr>
	</table>
	<div id="div">
	
	</div>

	
</form>
<html>
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
			td.innerHTML="첨부파일"+(i+1);
			input.type="file";
			input.name="file"+i;
			td2.appendChild(input);
			tr.appendChild(td);
			tr.appendChild(td2);
			table.appendChild(tr);
		}
		
		var tr2=document.createElement("tr");
		var td3=document.createElement("td");
		var input2=document.createElement("input");
		td3.colspan=2;
		input2.type="submit";
		input2.value="등록";
		td3.appendChild(input2);
		tr2.appendChild(td3);
		table.appendChild(tr2);
		
		var div=document.getElementById("div");
		div.innerHTML="";
		div.appendChild(table);
	}

</script>
</html>














