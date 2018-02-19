window.onload = function(){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	
	var sdate = document.getElementById("sdate");
	var edate = document.getElementById("edate");
	
	if(m < 10) m = "0" + m;
	
	sdate.defaultValue = y + "-" + m + "-" + d;
	edate.defaultValue = y + "-" + m + "-" + (d+1);
}