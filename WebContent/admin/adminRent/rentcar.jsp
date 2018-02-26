<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!--  나중에 다운받아서 로컬에넣으세여 -->

<!-- modal Start -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/admin/css/remodal.css">
<script src="<%=request.getContextPath() %>/admin/js/remodal.js"></script>
<!-- modal End -->
<style type="text/css">
table.type11 {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
}

table.type11 th {
	width: 155px;
	padding: 10px;
	font-weight: bold;
	vertical-align: middle;
	color: #fff;
	background: #ce4869;
}

table.type11 td {
	width: 155px;
	padding: 10px;
	vertical-align: middle;
	border-bottom: 1px solid #ccc;
	background: #eee;
}

img {
	width: 120px;
	height: 60px;
	vertical-align: middle;
}
</style>
<script type="text/javascript">
	function addCar(){
		window.location ='#modal';
		console.log("asd");
	}
	function showAdd(){
		window.open("<%=request.getContextPath()%>/semi/sales.do?cmd=carinsert","add","width=250,height=500,left=100,top=50");
	}
	
	$(document).ready(function(){
		 $('.remodal-confirm').click(function(){
			 if(confirm("등록하시겠습니까?")){
				 $('form[name="test"]').submit();
			 }
		 })
	})
	
</script>
</head>
<body>
<div id="insModal" class="remodal" data-remodal-id="modal" style="max-width:800px;">
	<div class="modalTop" style="margin-bottom:10px;">
		<span class="modalTitle">신규렌트차량등록</span>
	</div>
	<form name="test" action="<%=request.getContextPath() %>/semi/rent.do?cmd=insert" method="post" enctype="multipart/form-data">
	<div>
		차량이름<br>
		<input type="text" name="rcarName"><br>
		차량모델<br>
		<input type="text" name="rcarModel"><br>
		시간당금액<br>
		<input type="text" name="timePay"><br><br>
		메인이미지<input type="file" name="main"><br>
		서브이미지<input type="file" name="sub"><br><br>
	</div>
		<div style="margin:10px;">
			<button  data-remodal-action="confirm" class="remodal-confirm">확인</button>
			<button data-remodal-action="cancel" class="remodal-cancel">닫기</button>
		</div>
	</form>
</div>
<span style="margin-left: 90%;"><button onclick="addCar()">신규렌트차량등록</button></span>
<h2>렌트차량</h2>
<div>
<table class="type11">
	<tr>
		<th>렌트차량번호</th>
		<th>차량이미지</th>
		<th>렌트차량명</th>
		<th>렌트차량모델</th>
		<th>시간당금액</th>
		<th>상태</th>
	</tr>
	<c:forEach var="rent" items="${rlist }">
	<tr>
		<td>${rent.renNum }</td>
		<td><img src="<%=request.getContextPath() %>/admin/img/${rent.rcarName}_sub.PNG"></td>
		<td>${rent.rcarName }</td>
		<td>${rent.rcarModel }</td>
		<td>${rent.timePay }</td>
		<c:choose>
			<c:when test="${rent.state=='정상' }">
				<td>
					<select>
						<option value="nomal">정상</option>
						<option value="fault">불량</option>
					</select>
					<input type="button" value="확인" onclick="location.href='<%=request.getContextPath()%>/semi/rent.do?cmd=state&rpagenum=${param.rpagenum }&rlpagenum=${param.rlpagenum }&state=1&rennum=${rent.renNum }'">
				</td>
			</c:when>
			<c:otherwise>
				<td>
					<select>
						<option value="fault">불량</option>
						<option value="nomal">정상</option>
					</select>
					<input type="button" value="확인" onclick="location.href='<%=request.getContextPath()%>/semi/rent.do?cmd=state&rpagenum=${param.rpagenum }&rlpagenum=${param.rlpagenum }&state=2&rennum=${rent.renNum }'">
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
	</c:forEach>
</table>
</div>
<!-- 렌트차량 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${rstartPage>5 }">
		<a href="<%=request.getContextPath()%>/semi/rent.do?rpagenum=${rstartPage-1}&cmd=rentlist&rlpagenum=${rlpagenum2}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${rstartPage }" end="${rendPage }">
		<c:choose>
			<c:when test="${rpagenum2==i }">
				<a href="<%=request.getContextPath()%>/semi/rent.do?cmd=rentlist&rpagenum=${i}&rlpagenum=${rlpagenum2}">
					<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/semi/rent.do?cmd=rentlist&rpagenum=${i}&rlpagenum=${rlpagenum2}">
					<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${rpageCount>rendPage }">
		<a href="<%=request.getContextPath()%>/semi/rent.do?rpagenum=${rendPage-1}&cmd=rentlist&rlpagenum=${rlpagenum2}">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>
<h2>최근 렌트된 차량</h2>
<div>
<table class="type11">
	<tr>
		<th>렌트번호</th>
		<th>회원번호</th>
		<th>시작일</th>
		<th>반납일</th>
		<th>렌트가격</th>
		<th>렌트상태</th>
		<th>렌트차량</th>
	</tr>
	<c:forEach var="rentlist" items="${rentlist }">
	<tr>
		<td>${rentlist.rListNum }</td>
		<td>${rentlist.memNum }</td>
		<td>${rentlist.rStartDate }</td>
		<td>${rentlist.rEndDate }</td>
		<td>${rentlist.rTotPrice }</td>
		<c:choose>
			<c:when test="${rentlist.renState=='렌트대기' }">
				<td>
				<form action="<%=request.getContextPath()%>/semi/rent.do">
				<select name="sel">
					<option value="1">렌트대기</option>
					<option value="2">렌트중</option>
					<option value="3">반납</option>
				</select>
				<c:choose>
				 		<c:when test="${param.rpagenum==null }">
				 		<input type="hidden" name="rpagenum" value="1">
				 		</c:when>
				 		<c:otherwise>
					 	<input type="hidden" name="rpagenum" value="${param.rpagenum }">			 		
				 		</c:otherwise>
				 	</c:choose>
				 	<c:choose>
				 		<c:when test="${param.rlpagenum==null }">
				 			<input type="hidden" name="rlpagenum" value="1">
				 		</c:when>
				 		<c:otherwise>
				 			<input type="hidden" name="rlpagenum" value="${param.rlpagenum }">
				 		</c:otherwise>
				 	</c:choose>
					<input type="hidden" name="rlistnum" value="${rentlist.rListNum }">
					<input type="hidden" name="cmd" value="renstate">
				<input type="submit" value="확인">
				</form>
				</td>
			</c:when>
			<c:when test="${rentlist.renState=='렌트중' }">
				<td>
				<form action="<%=request.getContextPath()%>/semi/rent.do">
				<select name="sel">
					<option value="2">렌트중</option>
					<option value="1">렌트대기</option>
					<option value="3">반납</option>
				</select>
				<!--
				 <input type="button" value="확인" onclick="renState(${rentlist.rListNum})">					 
				 -->
				 	<c:choose>
				 		<c:when test="${param.rpagenum==null }">
				 		<input type="hidden" name="rpagenum" value="1">
				 		</c:when>
				 		<c:otherwise>
					 	<input type="hidden" name="rpagenum" value="${param.rpagenum }">			 		
				 		</c:otherwise>
				 	</c:choose>
				 	<c:choose>
				 		<c:when test="${param.rlpagenum==null }">
				 			<input type="hidden" name="rlpagenum" value="1">
				 		</c:when>
				 		<c:otherwise>
				 			<input type="hidden" name="rlpagenum" value="${param.rlpagenum }">
				 		</c:otherwise>
				 	</c:choose>
					<input type="hidden" name="rlistnum" value="${rentlist.rListNum }">
					<input type="hidden" name="cmd" value="renstate">
				<input type="submit" value="확인">
				</form>
				</td>
			</c:when>
			<c:otherwise>
				<td>
				<form action="<%=request.getContextPath()%>/semi/rent.do">
				<select name="sel">
					<option value="3">반납</option>
					<option value="2">렌트중</option>
					<option value="1">렌트대기</option>
				</select>
				<c:choose>
				 		<c:when test="${param.rpagenum==null }">
				 		<input type="hidden" name="rpagenum" value="1">
				 		</c:when>
				 		<c:otherwise>
					 	<input type="hidden" name="rpagenum" value="${param.rpagenum }">			 		
				 		</c:otherwise>
				 	</c:choose>
				 	<c:choose>
				 		<c:when test="${param.rlpagenum==null }">
				 			<input type="hidden" name="rlpagenum" value="1">
				 		</c:when>
				 		<c:otherwise>
				 			<input type="hidden" name="rlpagenum" value="${param.rlpagenum }">
				 		</c:otherwise>
				 	</c:choose>
					<input type="hidden" name="rlistnum" value="${rentlist.rListNum }">
					<input type="hidden" name="cmd" value="renstate">
				<input type="submit" value="확인">
				</form>
				</td>
			</c:otherwise>
		</c:choose>
		<td>${rentlist.rCarName }
		</td>
	</tr>
	</c:forEach>
</table>
</div>
<!-- 최근 렌트된 차량 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${rlstartPage>5 }">
		<a href="<%=request.getContextPath()%>/semi/rent.do?rlpagenum=${rlstartPage-1}&cmd=rentlist&rpagenum=${rpagenum2}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${rlstartPage }" end="${rlendPage }">
		<c:choose>
			<c:when test="${rlpagenum2==i }">
				<a href="<%=request.getContextPath()%>/semi/rent.do?cmd=rentlist&rlpagenum=${i}&rpagenum=${rpagenum2}">
					<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/semi/rent.do?cmd=rentlist&rlpagenum=${i}&rpagenum=${rpagenum2}">
					<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${rlpageCount>rlendPage }">
		<a href="<%=request.getContextPath()%>/semi/rent.do?rlpagenum=${rlendPage-1}&cmd=rentlist&rpagenum=${rpagenum2}">[다음]</a>
		</c:when>
		<c:otherwise>
			[다음]
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>