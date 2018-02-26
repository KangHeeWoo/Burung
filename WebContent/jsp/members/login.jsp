<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/members/login.css?ver=1">



<script type="text/javascript">
	function findIdpopup() {

		var pop = "${pageContext.request.contextPath }/jsp/members/findid.jsp"
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";

		window.open(pop, "", popOption);
	}

	function findPwdpopup() {

		var pop = "${pageContext.request.contextPath }/jsp/members/findpwd.jsp"
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";

		window.open(pop, "", popOption);
	}
</script>





<div class="container" align="center">
	<div class="row" >
		<div class="col-md-offset-5 col-md-3">
		
			<div class="form-login" >
				<h2>[ Login ]</h2>
				
				<form method="post"	action="${pageContext.request.contextPath }/members.do?cmd=login"><br> 
				
					<input type="text" name="memId" value="${param.memId }" placeholder="UserName" class="form-control input-sm chat-input"><br><br>
						
					<input type="password" name="memPwd" value="${param.memPwd }" placeholder="Password" class="form-control input-sm chat-input"><br><br>

					<div class="wrapper">
						<span class="group-btn">
						
						<input type="submit" value="Sign in" class="btn btn-primary btn-md"> <br></span>
						<br>
						<div>${errMsg }</div>

					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<ul>
	<li><a href="javascript:findIdpopup()">아이디 찾기</a></li>
	<li><a href="javascript:findPwdpopup()">비밀번호 찾기</a></li>
</ul>