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
	
	loadData();
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
			a.href = "../sales.do?cmd=choiceName&model=718&name=" + data.list[i];
			if(data.name == data.list[i]) a.style.fontWeight = "bold";
			li.appendChild(a);
			modelList.appendChild(li);
		}
		
		mainImg.src = "../img/" + data.mainImg;
		subImg.src = "../img/" + data.subImg;
		
		/*
		
		<li><a href="../sales.do?cmd=choiceName&model=718&name=718 Cayman Models">718 Cayman Models</a></li>
		<li><a href="../sales.do?cmd=choiceName&model=718&name=718 Boxster Models">718 Boxster Models</a></li>
		<li><a href="../sales.do?cmd=choiceName&model=718&name=718 GTS Models">718 GTS Models</a></li>
	 
		
		list
		:
		(3) ["718 Cayman Models", "718 Boxter Models", "718 GTS Models"]
		mainImg
		:
		"718_Cayman_Models_main.jpg"
		name
		:
		"718 Cayman Models"
		subImg
		:
		"718_Cayman_Models_sub.PNG"
		*/
	}
}