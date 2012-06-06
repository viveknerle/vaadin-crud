<html>
<head>
<%
String prefix = request.getParameter("prefix");
if (prefix == null || "".equals(prefix)) {
	prefix = "/get/resources/";
}
String jsEval = request.getParameter("jsEval");
if (jsEval == null || "".equals(jsEval)) {
	jsEval = "alert('alert')";
}

%>
<title>Pagina di test delle risorse statiche</title>

<script type="text/javascript" src="<%=prefix%>gettheme/jquery/js/jquery-1.7.2.js?ver=1.0debug=true"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/jquery/jquery-ui/development-bundle/ui/jquery-ui-1.8.18.custom.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/jquery/jquery-ui/js/jquery.ui.datepicker-it.js?ver=1.0debug=false"></script>
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>gettheme/jquery/jquery-ui/css/redmond/jquery-ui-1.8.18.custom.css?ver=1.0debug=false">
<script type="text/javascript" src="<%=prefix%>gettheme/jquery/js/jquery_blockui.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/jquery/js/jquery.cookie.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/jquery/js/jquery.timers.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/js/get-dialogs.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/js/get-init.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/js/get-defaultBehaviours.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/js/get-gestioneLocalita.js?ver=1.0debug=false"></script>
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>gettheme/css/get.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>gettheme/css/get-icone.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>pcrtheme/css/dom.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>pcrtheme/css/content.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>pcrtheme/css/services.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>pcrtheme/css/subtestatasp.css?ver=1.0debug=false">
<link class="getresource CSS" id="getresourceId_css_displaytag" rel="stylesheet" type="text/css" href="<%=prefix%>gettheme/css/get-displaytag.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>gettheme/css/get-compilazioneProcedimento.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>gettheme/css/get-guidaCompilazione.css?ver=1.0debug=false">
<link class="getresource CSS" rel="stylesheet" type="text/css" href="<%=prefix%>gettheme/css/get-homeSuap.css?ver=1.0debug=false">
<script type="text/javascript" src="<%=prefix%>gettheme/tiny_mce/tiny_mce.js?ver=1.0debug=false"></script>
<script type="text/javascript" src="<%=prefix%>gettheme/jquery/js/jQuery.tree.js?ver=1.0debug=false"></script>

<noscript>Your browser does not support JavaScript!</noscript>

</head>

<body style="text-align: left; padding: 10px;" onload="javascript:showLoaded()">
<h1>Pagina di test delle risorse statiche</h1>
<h3>Prefisso:<i><%=prefix%></i></h3>
<form action="">
<input type="text" size=50" name="prefix" value="<%=prefix%>" />
<input type="submit" value="Cambia prefisso" />
<textarea rows="10" cols="100" id="jsEval" name="jsEval"><%=jsEval%></textarea><br/>
<button onclick="javascript:valutaJs()" >Valuta javascript</button>
<button onclick="javascript:dynaLoad()" >Caricamento dinamico</button>
<br/><br/>
</form>
<script type="text/javascript">

function dynaLoad() {
document.getElementById('jsEval').value='jQuery.getScript("<%=prefix%>gettheme/css/get.css", function(data, textStatus, jqxhr) {\r\
	alert("loaded")\r\
})';
}
function valutaJs() {
	eval(document.getElementById('jsEval').value);
}
function showLoaded() {
	function testVar(variableString,message) {
		message = message || variableString;
		if (eval("typeof(" + variableString + ") === 'undefined'")) {
			document.body.innerHTML+='<div style="background-color:red">' + message + ' NOT loaded</div><br/>';
			return false;
		} else {
			document.body.innerHTML+='<div style="background-color:lime">' + message + ' loaded</div><br/>';
			return true;
		}	
		
	}
		
	if (testVar('jQuery')) {
		if (testVar('jQuery.ui')) {
			if (testVar('jQuery.datepicker')) {
			}	
		}
		if (testVar('jQuery.everyTime','jQuery timers')) {}
		if (testVar('jQuery.cookie')) {}
	}
	if (testVar('GET_UTILS','get-init.js')) {
		if (testVar('GET_UTILS._handlers','get-defaultBehaviours.js')) {}
	}
	if (testVar('GET_DIALOGS','get-dialogs.js')) {}
}

</script>
</body>
</html>