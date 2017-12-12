<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>在线考勤系统</title>
  <head>
     
    <link rel="shortcut icon" href="img/time.ico" >
    <link rel="stylesheet" type="text/css" href="css/login.css" />   
   <link rel="stylesheet" type="text/css" href="css/loginmain.css" />
   <link rel="stylesheet" type="text/css" href="css/layer.css" />
   <script type="text/javascript" src="js/jquery-1.12.1.min.js"></script> 
  <script type="text/javascript" src="js/Particleground.js"></script>
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="js/layer.js"></script>
  <script type="text/javascript" src="js/md5.js"></script>
  <script type="text/javascript" src="js/util.js"></script>
<style>

body{height:100%;background:#16a085;overflow:hidden;}

</style>




</head>
<body>
<div id='cs_box'>
	<span class='cs_title'>在线咨询</span>
	<span class='cs_close'>x</span>
	<div class='cs_img'></div>
	<span class='cs_info'>有什么可以帮到你</span>
	<div class='cs_btn'>点击咨询</div>
</div>
<script type="text/javascript">
	myEvent(window,'load',function(){
		cs_box.set({
			img_path : 'img/kefu.gif',		//设置图片路径
			qq : '445576823',		//设置QQ号码
		});
	});
</script>



   <div class="logo_box">

	<h3>登录在线考勤管理系统</h3>
	<form autocomplete="off" action="login.do" id="login" method="post">
		<div>
			<input id="username" name="username" class="text" type="text" placeholder="输入用户名">
		</div>
		
		  <input id="password" name="password" class="text"  type="password" placeholder="请输入密码" ">
		
		<div class="mb2"><input type="submit" id="modifypass" class="login-submit" value="登录"/></div>
		<div style="font-size:14px"><a href="online.do">在线打卡</a>&nbsp;|&nbsp;<a href="register.jsp">注册账号</a>&nbsp;|&nbsp;<a href="modifypass.jsp">忘记密码?</a></div>
		<div>Copyright © 2017 jijiuxue All rights Reserved</div>
	</form>	
</div>
<script>
var pass=$("#password").val();
$(function (){
var message="${requestScope.errorinfo}";
if(message!=""){
layer.alert(message,{
                skin: 'layui-layer-molv',
                closeBtn: 1,
                title:"考勤系统提醒您",
                shift: 1 //动画类型
            });
}
$.validator.setDefaults({
			submitHandler : function(form) {
	        $("#password").val(MD5($("#password").val()));
			form.submit();
			}
			});


$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
$("#login").validate({
			rules : {
				username : "required",
				password : "required",
				
			},
			messages : {
				password : {
					required : "密码不能为空",
					
				},
				username : "用户名不能为空",
				
			}
		});

		

});
</script>

</body>
</html>