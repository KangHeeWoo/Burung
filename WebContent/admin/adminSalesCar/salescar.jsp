<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

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
    background: #ce4869 ;
}
table.type11 td {
    width: 155px;
    padding: 10px;
    vertical-align: middle;
    border-bottom: 1px solid #ccc;
    background: #eee;
}
 img{width:120px;height: 60px;vertical-align:middle;} 
	#caradd{
	position: absolute;
	top: 300px;
	left: 700px;
	background-color: white;
	}
</style>
<script type="text/javascript">
	function addCar(){
		window.location ='#modal';
		console.log("asd");
	}

	
	$(document).ready(function(){
		 $('#con2').click(function(){
			 if(confirm("등록하시겠습니까?")){
				 $('form[name="test"]').submit();
			 }
		 })
	})
	function upCar(car,model,price){
		window.location ='#modal1';
		$('#scarName').val(car);
		$('#scarModel').val(model);
		$('#scarPrice').val(price);
	}
	
	$(document).ready(function(){
		 $('#con').click(function(){
			 if(confirm("등록하시겠습니까?")){
				 $('form[name="test1"]').submit();
			 }
		 })
	})
</script>
</head>
<body>
<div id="upModal" class="remodal" data-remodal-id="modal1" style="max-width:800px;">
	<div class="modalTop" style="margin-bottom:10px;">
		<span class="modalTitle">차량추가</span>
	</div>
	<form name="test1" action="<%=request.getContextPath() %>/semi/sales.do?cmd=update" method="post">
	<div>
		차량이름<br>
		<input type="text" name="scarName1" id="scarName"><br>
		차량모델<br>
		<input type="text" name="scarModel1" id="scarModel"><br>
		수량<br>
		<input type="text" name="salCnt1"><br>
		차량가격<br>
		<input type="text" name="scarPrice1" id="scarPrice"><br><br>
	</div>
		<div style="margin:10px;">
			<button  data-remodal-action="confirm" id="con" class="remodal-confirm">확인</button>
			<button data-remodal-action="cancel" class="remodal-cancel">닫기</button>
		</div>
	</form>
</div>


<div id="insModal" class="remodal" data-remodal-id="modal" style="max-width:800px;">
	<div class="modalTop" style="margin-bottom:10px;">
		<span class="modalTitle">신규차량등록</span>
	</div>
	<form name="test" action="<%=request.getContextPath() %>/semi/sales.do?cmd=insertOk" method="post" enctype="multipart/form-data">
	<div>
		차량이름<br>
		<input type="text" name="scarName"><br>
		차량모델<br>
		<input type="text" name="scarModel"><br>
		수량<br>
		<input type="text" name="salCnt"><br>
		차량가격<br>
		<input type="text" name="scarPrice"><br><br>
		메인이미지<input type="file" name="main"><br>
		서브이미지<input type="file" name="sub"><br><br>
	</div>
		<div style="margin:10px;">
			<button  data-remodal-action="confirm" id="con2" class="remodal-confirm">확인</button>
			<button data-remodal-action="cancel" class="remodal-cancel">닫기</button>
		</div>
	</form>
</div>


<span style="margin-left: 90%;"><button onclick="addCar()">신규차량등록</button></span>
<!-- 
<span style="margin-left: 90%;"><button onclick="location.href='<%=request.getContextPath()%>/semi/sales.do?cmd=carinsert'">신규차량등록</button></span>
 -->
<!-- 
<input type="button" value="차량등록" onclick="location.href='<%=request.getContextPath()%>/semi/sales.do?cmd=carinsert'">
 -->

<div id="caradd" style="display: none">
<h2>신규차량등록</h2>
<form action="<%=request.getContextPath() %>/semi/sales.do?cmd=insertOk" method="get" enctype="multipart/form-data">
차량이름<br>
<input type="text" name="scarName"><br>
차량모델<br>
<input type="text" name="scarModel"><br>
수량<br>
<input type="text" name="salCnt"><br>
차량가격<br>
<input type="text" name="scarPrice"><br><br>
메인이미지<input type="file" name="main"><br>
서브이미지<input type="file" name="sub"><br><br>
<input type="submit" value="등록">
</form>
 </div>
 
<form>
<h2>등록된차량</h2>
<table class="type11">
	<tr>
		<th style="text-align: center">등록번호</th>
		<th>차량이미지</th>
		<th>차량이름</th>
		<th>차량모델</th>
		<th>수량</th>
		<th>가격</th>
		<th>입고</th>
	</tr>
	<c:forEach var="car" items="${list }">
	<tr>
		<td style="text-align: center;">${car.salNum }</td>
		<td style="text-align: center;"><img src="<%=request.getContextPath() %>/admin/img/${car.scarName}_sub.PNG"></td>
		<td style="text-align: left"><a href="#">${car.scarName }</a></td>
		<td style="text-align: left">${car.scarModel }</td>
		<td style="text-align: left">${car.salCnt }</td>
		<td style="text-align: left">${car.scarPrice }</td>
		<td style="text-align: center;"><input type="button" value="입고" onclick="upCar('${car.scarName}','${car.scarModel }','${car.scarPrice}')"></td>
	</tr>
	</c:forEach>
</table>
</form>
<!-- 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${startPage>5 }">
		<a href="<%=request.getContextPath()%>/semi/sales.do?pageNum=${startPage-1}&cmd=carlist">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="<%=request.getContextPath() %>/semi/sales.do?pageNum=${i}&cmd=carlist&lpageNum=${lpageNum}">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/semi/sales.do?pageNum=${i}&cmd=carlist&lpageNum=${lpageNum}">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
	<c:when test="${pageCount>endPage }">
	<a href="<%=request.getContextPath()%>/semi/sales.do?pageNum=${endPage+1}&cmd=carlist">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
	</c:choose>
</div>

<h2>최근 판매된 차량</h2>
<div>
<table class="type11">
	<tr>
		<th>구매번호</th>
		<th>구매가격</th>
		<th>구매상태</th>
		<th>구매날짜</th>
		<th>구매차종</th>
		<th>인수완료</th>
	</tr>
	<c:forEach var="sale" items="${saleslist }">
	<tr>
		<td>${sale.sListNum }</td>
		<td>${sale.salPrice }</td>
		<td>${sale.salState }</td>
		<td>${sale.salDate }</td>
		<td>${sale.sCarModel }</td>
		<c:choose>
			<c:when test="${sale.salState=='인수완료' }">
					<td><button>인수확인</button></td>
			</c:when>
			<c:otherwise>
				<td><button onclick="location.href='<%=request.getContextPath()%>/semi/sales.do?cmd=stateChange&slistNum=${sale.sListNum }'">인수확인</button></td>
			</c:otherwise>
		</c:choose>
	</tr>
	</c:forEach>
</table>
</div>

<!-- 최근판매내역 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${lstartPage>3 }">
		<a href="<%=request.getContextPath()%>/semi/list.do?pageNum=${lstartPage-1}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${lstartPage }" end="${lendPage }">
		<c:choose>
			<c:when test="${lpageNum==i }">
				<a href="<%=request.getContextPath() %>/semi/sales.do?lpageNum=${i}&cmd=carlist&pageNum=${pageNum}">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/semi/sales.do?lpageNum=${i}&cmd=carlist&pageNum=${pageNum}">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
	<c:when test="${lpageCount>lendPage }">
	<a href="<%=request.getContextPath()%>/semi/sales.do?lpageNum=${lendPage+1}">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음] 
	</c:otherwise>
	</c:choose>
</div>
</body>
</html>