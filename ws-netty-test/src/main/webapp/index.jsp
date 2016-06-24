<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>web socket test</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
</head>
<script type="text/javascript">
	var data = {name: "zhangsan", age: 22};
	var url = 'ws://localhost/ws/test';
	var ws = new WebSocket(url);
	ws.onopen = function(){
		console.log('onopen...');
	}
	
	ws.onmessage = function(msg){
		console.log('get the msg: ' + msg);
	};
	
	ws.onclose = function(arg){
		console.log('onclose: ');
		console.dir(arg);
	}
	
	ws.onerror = function(event){
		console.log('onerror: ');
		console.dir(event);
	};

	//document.getElementById('showBtn').onclick = function(){
	//	ws.send(JSON.stringify(data));
	//};
	function clickHandler(){
		console.log('send..');
		ws.send(JSON.stringify(data));
	}

	
</script>
<body>
	<button id="showBtn" onclick="clickHandler();">获取</button>
</body>
</html>