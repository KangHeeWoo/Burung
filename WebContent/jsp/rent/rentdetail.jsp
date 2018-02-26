<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/rent.css?ver=25" />
<script src="${pageContext.request.contextPath }/js/rent.js?ver=6"></script>
<div id="rentinfo">
	<div id="rentdate" align="center">
		<div align="center">
			<h3>대여일</h3>
			<h4>${sDate }</h4>
		</div>
		<img src="${pageContext.request.contextPath }/img/right_arrow.png" id="arrow">
		<div align="center">
			<h3>반납일</h3>
			<h4>${eDate }</h4>
		</div>
	</div>
	<br>
	<h3>${cName }</h3>
	<img src="${pageContext.request.contextPath }/img/${cName}_rent.png" id="carimg">
	<br><br>
	<div>
	<span>· 대여 금액 :</span><span class="price" id="cPrice">${price }원</span><br><br>
	<span>· 보험 금액 :</span><span class="price" id="iPrice">0원</span><br><br>
	<span>· 옵션 금액 :</span><span class="price" id="oPrice">0원</span><br><br>
	<hr><br>
	<span>· 전체 금액 :</span><span class="price" id="tPrice">${price }원</span><br>
	</div>
</div>
<div id="rdetail">
	<form name="frm" method="post" action="${pageContext.request.contextPath }/rent.do"
	onsubmit="return rent()">
		<input type="hidden" name="id" value="${sessionScope.id }">
		<input type="hidden" name="cmd" value="rent">
		<input type="hidden" name="cName" value="${cName }">
		<input type="hidden" name="sDate" value="${sDate }">
		<input type="hidden" name="eDate" value="${eDate }">
		<input type="hidden" name="price" value="${price }">
		<h3>자차 손해면책 제도</h3>
		<br><hr><br>
		<input type="radio" name="insu" value="0" checked="checked" onclick="setPrice()">
		<span>보험 미적용(0원)</span>
		<input type="radio" name="insu" value="40000" onclick="setPrice()">
		<span>고객부담 면제(40000원)</span>
		<input type="radio" name="insu" value="20000" onclick="setPrice()">
		<span>5만원(20000원)</span>
		<input type="radio" name="insu" value="22000" onclick="setPrice()">
		<span>30만원(22000원)</span>
		
		<br><br><br>
		<h3>기타 옵션</h3>
		<br><hr><br>
		<input type="checkbox" name="option" value="0" checked="checked" disabled="disabled" onclick="setPrice()">
		<span>네비게이션(0원)</span>
		<input type="checkbox" name="option" value="2000" onclick="setPrice()">
		<span>베이비 시트(2000원)</span>
		<input type="checkbox" name="option" value="5000" onclick="setPrice()">
		<span>애완동물 시트(5000원)</span>
		
		<br><br><br>
		<h3>기본 약관</h3>
		<br><hr><br>
		<fieldset id="terms">
		<h4>Burung Burung 서비스 약관</h4>
		<h5>최종 수정 날짜 : 2018년 02월 22일</h5><br>
		Burung Burung에 오신 것을 환영합니다.<br>
		Burung Burung 제품 및 서비스(‘서비스’)를 이용해 주셔서 감사합니다. 서비스는 1600 Amphitheatre Parkway, Mountain View, CA 94043, United States에 소재한 Burung Burung LLC(‘Burung Burung’)에서 제공합니다.<br><br>		
		서비스를 이용함으로써 귀하는 본 약관에 동의하게 되므로 본 약관을 주의 깊게 읽어보시기 바랍니다.<br><br>		
		Burung Burung 서비스는 매우 다양하므로 때로 추가약관 또는 제품 요구사항(연령 요건 포함)이 적용될 수 있습니다. 추가약관은 관련 서비스에 대하여 제공되며, 귀하가 해당 서비스를 이용하는 경우 이 추가약관은 귀하와 Burung Burung 간 계약의 일부가 됩니다.<br><br>
		
		<h4>Burung Burung 서비스 이용</h4>
		귀하는 서비스 내에서 적용되는 모든 정책을 준수해야 합니다.<br><br>
		Burung Burung는 취소/환불을 제공하지 않습니다.<br><br>
		Burung Burung 서비스의 오용을 삼가시기 바랍니다. 예를 들어 서비스를 방해하거나 Burung Burung이 제공하는 인터페이스 및 안내사항 이외의 다른 방법을 사용하여 액세스를 시도하지 않아야 합니다. 귀하는 관련 수출 및 재수출 통제 법규 및 규정 등 오직 법률상 허용되는 범위에서만 Burung Burung 서비스를 이용할 수 있습니다. 귀하가 Burung Burung 약관이나 정책을 준수하지 않거나 Burung Burung이 부정행위 혐의를 조사하고 있는 경우, Burung Burung 서비스 제공이 일시 중지 또는 중단될 수 있습니다.<br><br>		
		Burung Burung 서비스를 사용한다고 해서 Burung Burung 서비스 또는 액세스하는 콘텐츠의 지적재산권을 소유하게 되는 것은 아닙니다. 콘텐츠 소유자로부터 허가를 받거나 달리 법률에 따라 허용되는 경우를 제외하고, Burung Burung 서비스의 콘텐츠를 사용할 수 없습니다. 본 약관은 귀하에게 Burung Burung 서비스에 사용된 브랜드나 로고를 사용할 권리를 부여하지 않습니다. Burung Burung 서비스에 또는 Burung Burung 서비스와 함께 게재된 어떠한 법적 고지도 삭제하거나 감추거나 변경하지 마십시오.<br><br>		
		Burung Burung 서비스는 Burung Burung이 소유하지 않은 일부 콘텐츠를 표시할 수 있습니다. 그러한 콘텐츠에 대해서는 제공한 주체가 단독으로 책임지게 됩니다. Burung Burung은 콘텐츠의 위법성 여부 또는 Burung Burung 정책 위반 여부를 결정하기 위해 검토할 수 있으며, 콘텐츠가 Burung Burung 정책 또는 법률에 위반된다고 합리적으로 판단하는 경우 이를 삭제하거나 게시를 거부할 수 있습니다. 그렇다고 반드시 콘텐츠를 검토한다는 의미는 아니므로, 콘텐츠를 검토할 것이라고 간주하지 마십시오.<br><br>		
		서비스 이용과 관련하여 Burung Burung은 귀하에게 서비스 고지, 관리 메시지 및 기타 정보를 발송할 수 있습니다. 귀하는 메시지 수신을 거부할 수 있습니다.<br><br>		
		일부 Burung Burung 서비스는 휴대기기에서 사용할 수 있습니다. 트래픽 또는 보안 관련 법규 준수를 방해하거나 막는 방식으로 서비스를 사용해서는 안 됩니다.<br><br>		
		</fieldset>
		<br>
		<input type="checkbox" name="selTerms" required="required"><span>약관에 동의합니다.</span>
		<br>
		<input id="optSub" type="submit" value="렌트하기">
	</form>
</div>