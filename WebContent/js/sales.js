var model 	= null;
var mName 	= null;
var modelList 	= null;
var mainImg	= null;
var subImg 	= null;
var price = null;

var xhr = null;

window.onload = function(){
	model	= document.getElementById("model").firstChild.nodeValue;
	mName	= document.getElementById("mName").firstChild.nodeValue;
	modelList	= document.getElementById("modelList");
	mainImg	= document.getElementById("mainImg");
	subImg	= document.getElementById("subImg");
	
	loadData();
	showOpt();
}

function loadData(){
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange=getData;
	xhr.open('get', '../sales.do?cmd=loadData&model='
			+ model + '&mName=' + mName, true);
	xhr.send();
}

function getData(){
	if(xhr.readyState == 4 && xhr.status == 200){
		var data = eval('(' + xhr.responseText + ')');

		for(var i=0;i<data.list.length;i++){
			var li = document.createElement("li");
			var a = document.createElement("a");
			a.innerHTML = data.list[i];
			a.href = "../sales.do?cmd=choiceName&model=" + model + "&name=" + data.list[i];
			if(data.name == data.list[i]) a.style.fontWeight = "bold";
			li.appendChild(a);
			modelList.appendChild(li);
		}
		
		mainImg.src = "../img/" + data.mainImg;
		subImg.src = "../img/" + data.subImg;
		price = data.price;
	}
}
function buy(){
	var id = frm.id.value;

	if(id == '' || id == null){
		alert('로그인 후 이용해주시기 바랍니다.');
		return false;
	}else{
		var color = frm.color.value.split(":");
		var wheel = frm.wheel.value.split(":");
		var seet = frm.seet.value.split(":");
		var light = frm.light.value.split(":");
		var audio = frm.audio.value.split(":");
		var tot = parseInt(color[1], 10) + parseInt(wheel[1], 10) + parseInt(seet[1], 10) + parseInt(light[1], 10) + parseInt(audio[1], 10) + price;
		
		var msg = "선택하신 차량 >> " + mName
		+ "\n\tPrice >> " + price
		+ "\n\tColor >> " + color[0]
		+ "\n\tWheel >> " + wheel[0]
		+ "\n\tSeet  >> " + seet[0]
		+ "\n\tLight >> " + light[0]
		+ "\n\tAudio >> " + audio[0]
		+ "\n\t총액 >> " + tot
		+ "\n구매하시겠습니까?";
		var con = confirm(msg);
		
		if(con == true){
			alert('구매가 완료되었습니다');
			return true;
		}else{
			return false;
		}		
	}
}

function showOpt(){
	var color = frm.color.value;
	var wheel = frm.wheel.value;
	var seet = frm.seet.value;
	var light = frm.light.value;
	var audio = frm.audio.value;
	
	var selectOpt = document.getElementById("selectOpt");
	selectOpt.innerHTML = color + " | " + wheel + " | "  + seet + " | "  + light + " | " + audio;
}