<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="isMobile" value="${false}"/>
<c:set var="userAgent" value="${fn:toLowerCase(header['user-agent'])}"/>
<c:set var="topMenuClass" value="mainMenu"/>

<c:choose>
	<c:when test="${fn:contains(userAgent,'android')}"><c:set var="isMobile" value="${true}"></c:set></c:when>
	<c:when test="${fn:contains(userAgent,'ios')}"><c:set var="isMobile" value="${true}"></c:set></c:when>
	<c:when test="${fn:contains(userAgent,'mobile')}"><c:set var="isMobile" value="${true}"></c:set></c:when>
	<c:when test="${fn:contains(userAgent,'iphone')}"><c:set var="isMobile" value="${true}"></c:set></c:when>
</c:choose>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>WSDL Helper</title>
		<script src="js/dojo-release-1.10.4/dojo/dojo.js" data-dojo-config="async: true"></script>
		<link rel="stylesheet" href="js/dojo-release-1.10.4/dijit/themes/claro/claro.css">
		
		<script type="text/javascript" src="js/highlight/shCore.js"></script>
		<script type="text/javascript" src="js/highlight/brush/shBrushXml.js"></script>
		<link type="text/css" rel="stylesheet" href="js/highlight/css/shCoreDefault.css"/>
		
		<link rel="stylesheet" href="css/template.css">
		<script type="text/javascript" src="js/template.js"></script>
		<c:if test="${isMobile}">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
		</c:if>
	</head>
	<body class="claro">
		<div class="wrapper">
			<div class="mainMenu">
				<div class="wrapper-center">
					<ul>
						<li><i class="file-code-o"></i> WSDL Helper</li>
						<li><a><span>Inicio</span></a></li>
						<li  class="selected"><a><span>Ayuda</span></a></li>
						<li><a><span>Acerca de</span></a></li>
					</ul>
				</div>
			</div>
			<div class="mainContent">
				<div class="table-row">
					<div class="table-cell cell60">
						<div class="SectionBox h65">
							<div class="titleBox cell100">
								<div class="table-row">
									<div class="table-cell cell21">
										<div class="padleft3 padTop2">
											<label class="titleType1">Editor:</label>
										</div>
									</div>
									<div class="table-cell cell45 align-right">
										<div class="topBtnCenter btnFileContainer">
											<div id="filePath" class="btnFileText">Selecione un archivo</div>
											<div class="buttonA btnFile"><span>Subir</span><input id="xmlUploader" type="file" name="xmlFile" onchange="updateFilePath(this.value, this);"/></div>
										</div>
									</div>
									<div class="table-cell cell34 align-right">
										<div class="topBtn">
											<button onclick="sendXML();" class="buttonA">Analizar</button>
										</div>
									</div>
								</div>
							</div>
							<div class="textBox padleft3 padRight3 padBottom1">
								<div class="viewXMLformated textAreaBox" onclick="activeEditor(1, this);"><pre class="brush: xml"></pre></div>
								<textarea id="xmlEditor" name="xmlText" style="display: none;" onblur="activeEditor(0, this);"></textarea>
							</div>
						</div>
						<div class="SectionBox h33">
							<div class="titleBox cell100">
								<div class="table-row">
									<div class="table-cell cell66">
										<div class="padleft3 padTop2">
											<label class="titleType1">Consola:</label>
											<div class="table-cell consoleIcons" title="Número de lineas"><i class="fa-terminal"></i><label class="titleType1"> 0</label></div>
											<div class="table-cell consoleIcons" title="Errores"><i class="times-circle"></i><label class="titleType1"> 0</label></div>
											<div class="table-cell consoleIcons" title="Advertencias"><i class="exclamation-triangle"></i><label class="titleType1"> 0</label></div>
										</div>
									</div>
									<div class="table-cell cell33 align-right">
										<div class="topBtn">
											<button onclick="clearConsole();" class="buttonA">Limpiar consola</button>
										</div>
									</div>
								</div>
							</div>
							<div class="textBox padleft3 padRight3 padBottom1">
								<div id="consola" class="consoleBox"></div>
							</div>
						</div>
					</div>
					<div class="table-cell cell40">
						<div class="SectionBox h49">
							<div class="titleBox cell100">
								<div class="table-row">
									<div class="table-cell cell66">
										<div class="padleft3 padTop2">
											<label class="titleType1">arbol xml:</label>
										</div>
									</div>
								</div>
							</div>
							<div class="textBox padleft3 padRight3 padBottom1">
								<div class="textAreaBox"></div>
							</div>
						</div>
						<div class="SectionBox h49">
							<div class="titleBox cell100">
								<div class="table-row">
									<div class="table-cell cell66">
										<div class="padleft3 padTop2">
											<label class="titleType1">información:</label>
										</div>
									</div>
								</div>
							</div>
							<div class="textBox padleft3 padRight3 padBottom1">
								<div class="textAreaBox"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<button data-dojo-type="dijit/form/Button" id="buttonOne">Highlight Code</button>
	</body>
</html>