<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
    vertical-align: top;
    color: #fff;
    background: #ce4869 ;
}
table.type11 td {
    width: 155px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #eee;
}
</style>
<script type="text/javascript">
	var xhr=null
	function showoption(slistnum){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		xhr.open('get','/Burung/semi/option.do?slistnum='+slistnum,true);
		xhr.send();
	}
	function callback(){
		if(xhr.readyState==4 && xhr.status==200){
			var result=xhr.responseText;
			var json=JSON.parse(result);
			var div=document.getElementById("saledetail");
			var thnum=["구매번호", "컬러", "휠", "시트", "라이트", "오디오"];
			var tr=document.createElement("tr");
			div.innerHTML="";
			for(var i=0;i<thnum.length;i++){
				var th=document.createElement("th");
				th.innerHTML=thnum[i];
				tr.appendChild(th);
			}
			var tr1=document.createElement("tr");
			var p=document.createElement("p");
			for(var j=0;j<1;j++){
				p.innerHTML="<h2>4.구매차량 옵션내역</h2>";
				var td=document.createElement("td");
				td.innerHTML=json.list[0].sListNum;
				tr1.appendChild(td);
				for(var i=0;i<json.list.length;i++){
					var td=document.createElement("td");
					//alert(json.list[i].optType);
					if(json.list[i].optType=='color'){
						td.innerHTML=json.list[i].optInfo;						
					}else if(json.list[i].optType=='wheel'){
						td.innerHTML=json.list[i].optInfo;						
					}else if(json.list[i].optType=='seet'){
						td.innerHTML=json.list[i].optInfo;						
					}else if(json.list[i].optType=='light'){
						td.innerHTML=json.list[i].optInfo;					
					}else{
						td.innerHTML=json.list[i].optInfo;					
					}
					tr1.appendChild(td);
				}
			}
			
			var table=document.createElement("table");
			table.className="type11";
			table.appendChild(tr);
			table.appendChild(tr1);
			saledetail.appendChild(p);
			saledetail.appendChild(table);
		}
	}
</script>
</head>
<body>
<form>
<div>
<h2>1.회원정보</h2>
<table class="type11">
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>이메일</th>
		<th>생년월일</th>
	</tr>
	<c:forEach var="user" items="${detail }">
	<tr>
		<td>${user.memNum }</td>
		<td>${user.memId }</td>
		<td>${user.memName }</td>
		<td>${user.memPhone }</td>
		<td>${user.memAddr }</td>
		<td>${user.memEmail }</td>
		<td>${user.memBirth }</td>
	</tr>
	</c:forEach>
</table>
</div>
</form>

<form>
<div>
<h2>2.렌트내역</h2>
<table class="type11">
	<tr>
		<th>렌트번호</th>
		<th>시작날짜</th>
		<th>반납날짜</th>
		<th>총가격</th>
		<th>렌트상태</th>
		<th>렌트차종</th>
	</tr>
	<c:forEach var="rent" items="${rentlist }">
	<tr>
		<td>${rent.rListNum }</td>
		<td>${rent.rStartDate }</td>
		<td>${rent.rEndDate }</td>
		<td>${rent.rTotPrice }</td>
		<td>${rent.renState }</td>
		<td>${rent.rCarName }</td>
	</tr>
	</c:forEach>
</table>
</div>
</form>
<!-- 렌트내역 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${rstartPage>5 }">
		<a href="<%=request.getContextPath()%>/semi/list.do?rpageNum=${rstartPage-1}&cmd=memberdetail&memNum=${memNum}&pageNum=${pageNum}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${rstartPage }" end="${rendPage }">
		<c:choose>
			<c:when test="${rpageNum==i }">
				<a href="<%=request.getContextPath() %>/semi/list.do?rpageNum=${i}&cmd=memberdetail&memNum=${memNum}&pageNum=${pageNum}">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/semi/list.do?rpageNum=${i}&cmd=memberdetail&memNum=${memNum}&pageNum=${pageNum}">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
	<c:when test="${rpageCount>rendPage }">
	<a href="<%=request.getContextPath()%>/semi/list.do?rpageNum=${rendPage+1}&cmd=memberdetail&memNum=${memNum}&pageNum=${pageNum}">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
	</c:choose>
</div>
<form>
<div>
<h2>3.구매내역</h2>
<table class="type11">
	<tr>
		<th>구매번호</th>
		<th>구매가격</th>
		<th>구매상태</th>
		<th>구매날짜</th>
		<th>구매차종</th>
	</tr>
	<c:forEach var="sale" items="${salelist }">
	<tr>
		<td>${sale.sListNum }</td>
		<td>${sale.salPrice }</td>
		<td>${sale.salState }</td>
		<td>${sale.salDate }</td>
		<td><a href="javascript:showoption(${sale.sListNum })">${sale.sCarModel }</a></td>
	</tr>
	</c:forEach>
</table>
</div>
</form>
<!-- 구매내역 페이징처리 -->
<div>
	<c:choose>
		<c:when test="${sstartPage>5 }">
		<a href="<%=request.getContextPath()%>/semi/list.do?pageNum=${sstartPage-1}">[이전]</a>
		</c:when>
		<c:otherwise>
			[이전]
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${sstartPage }" end="${sendPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="<%=request.getContextPath() %>/semi/list.do?pageNum=${i}&cmd=memberdetail&memNum=${memNum}&rpageNum=${rpageNum}">
				<span style="color:blue">[${i }]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/semi/list.do?pageNum=${i}&cmd=memberdetail&memNum=${memNum}&rpageNum=${rpageNum}">
				<span style="color:gray">[${i }]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
	<c:when test="${spageCount>sendPage }">
	<a href="<%=request.getContextPath()%>/semi/list.do?pageNum=${sendPage+1}">[다음]</a>
	</c:when>
	<c:otherwise>
		[다음]
	</c:otherwise>
	</c:choose>
</div>
<form>
<div id="saledetail">
</div>
</form>
</body>
</html>