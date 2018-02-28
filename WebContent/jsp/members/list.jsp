<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/members/list.css?ver=3">

<div class="container">
	<div class="row" >
		<div class="col-md-offset-5 col-md-3">
		
			<div class="form-login" >
<h2> &lt; 마이페이지 &gt; </h2><br>
<h3>1) 상세정보</h3>
<table  border="1" width="1000" class="Mylist">
	<tr>
		<th>아이디</th>
		<th>주소</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>생년월일</th>
		<th>회원명</th>
	</tr>

	<tr>
		<td>${members.memId }</td>
		<td>${members.memAddr }</td>
		<td>${members.memPhone }</td>
		<td>${members.memEmail }</td>
		<td>${members.memBirth }</td>
		<td>${members.memName }</td>
	</tr>
</table>
<br>
 
<h3> 2) 렌트 내역</h3>
<table border="1" width="700" class="rentList">
	<tr>
		<th>렌트내역번호</th>
		<th>대여시간</th>
		<th>반납시간</th>
		<th>총 대여료</th>
		<th>렌트상태</th>
		<th>렌트차량번호</th>
		<th>후기</th>
	</tr>
	<c:forEach var = "rent" items="${rentlist }">
	<tr>
		<td>${rent.rListNum }</td>
		<td>${rent.rStartDate }</td>
		<td>${rent.rEndDate }</td>
		<td>${rent.rTotPrice }</td>
		<td>${rent.renState }</td>
		<td>${rent.rCarName }</td>
		<td><a href="<%=request.getContextPath()%>/review.do?cmd=reviewinsert&num=2&rlistnum=${rent.rListNum}">후기</a></td>
	</tr>	
	</c:forEach>
</table>
<br>
<h3>3) 구매 내역</h3>
<table border="1" width="500" class="salesList">
	<tr>
		<th>구매내역번호</th>
		<th>구매가격</th>
		<th>인수상태</th>
		<th>구매차량번호</th>
		<th>후기</th>
	</tr>
	<c:forEach var="sale" items="${salelist}">
		<tr>
			<td>${sale.sListNum }</td>
			<td>${sale.salPrice }</td>
			<td>${sale.salState }</td>
			<td>${sale.sCarModel }</td>
			<td><a href="<%=request.getContextPath()%>/review.do?cmd=reviewinsert&num=1&slistnum=${sale.sListNum}">후기</a></td>
		</tr>
	</c:forEach>
</table>

<br>
<br>
<a href="members.do?cmd=updatepage">[ 회원 정보 수정 ]</a>
</div></div></div></div>
