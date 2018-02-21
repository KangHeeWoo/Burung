<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/rent.css?ver=17" />
<script src="${pageContext.request.contextPath }/js/rent.js?ver=2"></script>
<div id="rentinfo">
	<div id="rentdate" align="center">
		<div align="center">
			<h3>대여일</h3>
			<h4>${sDate } &nbsp ${sTime }</h4>
		</div>
		<img src="${pageContext.request.contextPath }/img/right_arrow.png" id="arrow">
		<div align="center">
			<h3>반납일</h3>
			<h4>${eDate } &nbsp ${eTime }</h4>
		</div>
	</div>
	<h3>${cName }</h3>
	<img src="${pageContext.request.contextPath }/img/${cName}_rent.png" id="carimg">
	<div>
	<span>· 대여 금액 :</span><span class="price" id="cPrice">${price }원</span><br><br>
	<span>· 보험 금액 :</span><span class="price" id="iPrice">0원</span><br><br>
	<hr><br>
	<span>· 전체 금액 :</span><span class="price" id="tPrice">${price }원</span><br>
	</div>
</div>
<div id="rdetail">
	<form>
		<h3>자차 손해면책 제도</h3>
		<hr><br>
		<input type="radio" name="insu" value="0" checked="checked">
		<span>보험 미적용(0원)</span>
		<input type="radio" name="insu" value="40000">
		<span>고객부담 면제(40000원)</span>
		<input type="radio" name="insu" value="20000">
		<span>5만원(20000원)</span>
		<input type="radio" name="insu" value="22000">
		<span>30만원(22000원)</span>
		
		<br><br><br>
		<h3>자차 손해면책 제도</h3>
		<hr><br>
	</form>
	<br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br>
</div>