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
	
	
<form method="post" action="${pageContext.request.contextPath }/jsp/layout.jsp?spage=Board/reviewinsert.jsp">
	<table border="1" width="500">
		<tr>
			<td>점수</td>
			<td><input type="number" min="1" max="5" name="revscore" value="${param.revscore }"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="memid" value="${sessionScope.id }"  readonly="readonly">
			<input type="hidden"  name="memnum" value="${memnum }"></td>
		</tr>
		<tr>
		<!-- 구매 렌트에 따라 다른 차종으로 불러와야함 1.구매 2.렌트 -->
		<c:choose>
			<c:when test="${num==1 }">
				<td>차종</td>
				<td><input type="text" name="scarname" value="${scarname }"  readonly="readonly"></td>
			</c:when>
			<c:otherwise>
				<td>차종</td>
				<td><input type="text" name="rcarname" value="${rcarname }"  readonly="readonly"></td>
			</c:otherwise>
		</c:choose>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="${param.title }"></td>
		</tr>
			<tr>
			<td>내용</td>
			<td><textarea name="content" rows="5" cols="50">${param.content}</textarea></td>
		</tr>
			<tr>
			<td>첨부파일갯수</td>
			<td><input type="text" name="cnt" value="${cnt }">
				<input type="submit" value="확인">
			</td>
		</tr>
	</table>
</form>

<form method="post" action="${pageContext.request.contextPath }/jsp/Board/reviewinsert.jsp" enctype="multipart/form-data">
	<input type="hidden" name="writer" value="${sessionScope.id }">
	<input type="hidden" name="title" value="${param.title }">
	<input type="hidden" name="content" value="${param.content}">	
	<table width="500">
	<c:forEach var="i" begin="1" end="${cnt }" >
	
	<tr>
		<td>첨부파일${i }</td>
		<td><input type="file" name="file${i }"></td>
	</tr>
	
	</c:forEach>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="전송">
		</td> 
	</tr>
	</table>
</form>















