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