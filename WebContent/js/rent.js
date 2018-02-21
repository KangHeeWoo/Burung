function checkDate(){
	var sDate = frm.sDate.value;
	var sTime = frm.sTime.value;
	var eDate = frm.eDate.value;
	var eTime = frm.eTime.value;
	
	if(new Date(sDate + " " + sTime) <= new Date()){
		alert('대여일은 현재 날짜보다 빠를 수 없습니다.');
		return false;
	}
	
	if(new Date(sDate + " " + sTime) >= new Date(eDate + " " + eTime)){
		alert('반납일은 대여일보다 빠를 수 없습니다.');
		return false;
	}
}

function setPrice(){
	var insu = frm.insu;
	var option = frm.option;
	var tPrice = parseInt(document.getElementById("cPrice").firstChild.nodeValue.replace("원", ""));
	var oPrice = 0;
	
	tPrice += parseInt(insu.value);
	
	for(var i=0;i<option.length;i++){
		if(option[i].checked == true){
			tPrice += parseInt(option[i].value);
			oPrice += parseInt(option[i].value);
		}
	}
	
	document.getElementById("iPrice").firstChild.nodeValue = insu.value + "원";
	document.getElementById("oPrice").firstChild.nodeValue = oPrice + "원";
	document.getElementById("tPrice").firstChild.nodeValue = tPrice + "원";
}

function rent(){
	var id = frm.id.value;

	if(id == '' || id == null){
		alert('로그인 후 이용해주시기 바랍니다.');
		return false;
	}
	
	var con = confirm('해당 옵션으로 렌트하시겠습니까?');
	
	if(con == true){
		alert('렌트가 완료되었습니다');
		return true;
	}else{
		return false;
	}		
}