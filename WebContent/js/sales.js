var model 	= null;
var mName 	= null;
var modelList 	= null;
var mainImg	= null;
var subImg 	= null;

var xhr = null;

window.onload = function(){
	model	= document.getElementById("model").firstChild.nodeValue;
	mName	= document.getElementById("mName").firstChild.nodeValue;
	modelList	= document.getElementById("modelList");
	mainImg	= document.getElementById("mainImg");
	subImg	= document.getElementById("subImg");
	
	mainImg.src = "../img/718_Cayman_Models_main.jpg";
	subImg.src = "../img/718_Cayman_Models_sub.PNG";
	
	loadData();
}

function loadData(){
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange=getData;
	xhr.open('get', '../sales.do?cmd=loadData&model='
			+ model + "&mName=" + mName, true);
	xhr.send();
}

function getData(){
	if(xhr.readyState == 4 && xhr.status == 200){
		
	}
}