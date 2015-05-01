require(["dojo",
         "dojo/on",
         "dojo/dom-attr",
         "dojo/dom-style",
         "dojo/query",
         "dojo/NodeList-traverse"
         ]);

//funcion AJAX para enviar XML a servidor
function sendXML(){
	var fd = new FormData();
	var xmlEditor = document.getElementById("xmlEditor").value;
	var xmlUploader = document.getElementById("xmlUploader").files;
	var inputValido = false;
	
	if(xmlEditor != ""){
		fd.append("xmlToParse", escape(xmlEditor));
		fd.append("typeInput", "s");
		inputValido = true;
		console.log("text");
	}else if(xmlUploader.length > 0){
		fd.append("xmlToParse", xmlUploader[0]);
		fd.append("typeInput", "f");
		inputValido = true;
		console.log("file");
	}
	
	if(inputValido){
		var xml = new XMLHttpRequest();
		xml.open("POST", "/WSDL_Helper/EnviarXML", true);
		xml.send(fd);		
		xml.onreadystatechange = function(){
			if(xml.readyState != 4){ return; }
			var lineas = JSON.parse(xml.responseText);
			
			var consola = document.getElementById("consola");
			consola.innerHTML = "";
			
			for(var nombreAttr in lineas.tokens){
				consola.innerHTML += lineas.tokens[nombreAttr]+"<br/>";
			}
			
		}
	}
}

//Funcion que resalta el XML de entrada
function resaltarXML(){
	SyntaxHighlighter.highlight();
}

//Funcion para pasar XML del textArea a Div
function mostrarXML(){
	var textXML = dojo.byId("xmlEditor").value.replace(/</g, "&lt;");
	dojo.query(".SectionBox .viewXMLformated.textAreaBox").forEach(function(node){
		node.innerHTML = ""; 
		var code = document.createElement("pre");
		code.setAttribute("class", "brush: xml");
		code.innerHTML = textXML;
		node.appendChild(code);
	});
}

//Llena area de edición con XML subido
function cargarArchivoTexto(fileInput){
	if (window.File && window.FileReader && window.FileList && window.Blob) {
		  var textArea = dojo.byId("xmlEditor");
		  var archivo = fileInput.files[0];
		  var lector = new FileReader();
		  
		  lector.onloadend = function(){
			  textArea.value = lector.result;
			  mostrarXML();
			  resaltarXML();
		  }
		  
		  if(archivo){
			  lector.readAsText(archivo);
		  }else{
			  textArea.value = "";
		  }
	} else {
	  alert('No se puede mostrar el contenido del archivo en el editor porque su explorador no soporta la API de File');
	}
}

//Funcion para limpiear la consola
function clearConsole(){
	document.getElementById("consola").innerHTML = "";
}

//Funcion para actualizar la ruta del archivo a actualizar en la vista
function updateFilePath(val, node){
	document.getElementById("filePath").innerHTML = val;
	cargarArchivoTexto(node);
}

//funcion para activar/desactivar el editor
function activeEditor(mode, node){
	switch(mode){
		case 0:
			dojo.style(node, "display", "none");
			dojo.removeAttr(dojo.query(node).siblings(".textAreaBox")[0], "style");
			mostrarXML();
			resaltarXML();
		break;
		case 1:
			dojo.style(node, "display", "none");
			dojo.removeAttr(dojo.byId("xmlEditor"), "style");
		break;
		default:
		break;
	}
}