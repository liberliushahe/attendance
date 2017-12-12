<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>在线打卡</title>
		<link rel="shortcut icon" href="img/time.ico" >
		<link rel="stylesheet" href="css/main.css" />
		<link rel="stylesheet" href="css/styles.css" />
		<script type="text/javascript" src="js/jquery.min.js" ></script>
	    <script type="text/javascript" src="js/jquery.leoweather.min.js" ></script>
		
	</head>
	<body>
		
		<div id="jqContent"></div>
		<div id="mainCss">
			
			<div id="mainInner">
				<div id="beijin">
					
				</div>
			<div class="welcome-info">
	<div> <marquee behavior="scroll">欢迎来到在线签到系统<span id="weather"></span></marquee></div>
</div>

<script type="text/javascript">
	$('#weather').leoweather({format:'，{时段}好！，<span id="colock">现在时间是：<strong>{年}年{月}月{日}日 星期{周} {时}:{分}:{秒}</strong>，</span> <b>{城市}天气</b> {天气} {夜间气温}℃ ~ {白天气温}℃'});
</script>
			  <div class="container">
	<section>
		<ul class="longshadow">
			<li>
				<figure><span id="one">在</span></figure>
			</li>
			<li>
				<figure><span id="two">线</span></figure>
			</li>
			<li>
				<figure><span id="three">考</span></figure>
			</li>
			<li>
				<figure><span id="four">勤</span></figure>
			</li>
			<li>
				<figure><span id="five">系</span></figure>
			</li>
			<li>
				<figure><span id="six">统</span></figure>
			</li>																			
		</ul>
	</section>
</div>	
<!--
表盘开始
-->
<!--
	<div id="innerclock">
<div class="wrapper clearfix show">
	<div class="clock" id="clock2"></div>
</div>
</div>
-->
<div id="daka">
	<div id="success"><img src="img/success.png"  width="600px" height="510px" /></div>
	<div id="form-data">
	 <a href="work.do"> <img src="img/dakaji.png" width="500px" height="400px" onclick="check()"/></a>  
	</div>
	
</div>
		
</div>

</div>

	<div id="footer">考勤管理版权所有2017--jijiuxue</div>
<script type="text/javascript" src="js/jquery.longShadow.js" ></script>
<script type="text/javascript" src="js/clock-1.1.0.min.js"></script>

	<script>
	function check(){

		$("#beijin").show();
	}
	function down(){

		$("#success").hide();
		$("#beijin").hide();

	}
	$(function() {
	var flag="${flag}";
	if(flag!=""){
	   alert(flag);
	}
	
	var clock = $("#clock").clock(),
	data = clock.data('clock');
	// data.pause();
	// data.start();
	// data.setTime(new Date());
	var d = new Date();
	d.setHours(9);
	d.setMinutes(51);
	d.setSeconds(20);

	var clock1 = $("#clock1").clock({
		width: 210,
		height: 200,
		theme: 't2',
		date: d
	});

	var clock2 = $("#clock2").clock({
		width: 150,
		height: 220,
		theme: 't3'
	});
		
		$('#one').longShadow({
			colorShadow: '#a13c32',
			sizeShadow: 70
		});

		$('#two').longShadow({
			colorShadow: '#af7a4b',
			sizeShadow: 70,
			directionShadow: 'bottom-left'
		});

		$('#three').longShadow({
			colorShadow: '#828f46',
			sizeShadow: 20,
			directionShadow: 'top'
		});

		$('#four').longShadow({
			colorShadow: '#3b6767',
			sizeShadow: 20,
			directionShadow: 'bottom'
		});

		$('#five').longShadow({
			colorShadow: '#3e9277',
			sizeShadow: 70,
			directionShadow: 'top-right'
		});

		$('#six').longShadow({
			colorShadow: '#525283',
			sizeShadow: 70,
			directionShadow: 'top-left'
		});	
				
	});
</script>
	</body>
</html>
