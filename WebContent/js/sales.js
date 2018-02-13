var model 	= null;
var mName 	= null;
var modelList 	= null;
var mainImg	= null;
var subImg 	= null;

var xhr = null;

window.onload = function(){
	model	= document.getElementById("model");
	mName	= document.getElementById("mName");
	//name = document.getElementsByTagName("span")[1];
	modelList	= document.getElementById("modelList");
	mainImg	= document.getElementById("mainImg");
	subImg	= document.getElementById("subImg");
	
	mainImg.src = "../img/718_Cayman_Models_main.jpg";
	subImg.src = "../img/718_Cayman_Models_sub.PNG";
	
	console.log(model.firstChild.nodeValue);
	console.log(mName.firstChild.nodeValue);
	
}

function loadDate(){
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange=getData;
	xhr.open('get', '', true);
	xhr.send();
}

function getData(){
	if(xhr.readyState == 4 && xhr.status == 200){
		
	}
}