<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>在线考勤系统</title>
  <head>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="img/time.ico" >
    <link rel="stylesheet" type="text/css" href="css/login.css" />   
   <link rel="stylesheet" type="text/css" href="css/loginmain.css" />
  <script type="text/javascript" src="js/jquery-1.12.1.min.js"></script> 
  <script type="text/javascript" src="js/jquery.slideunlock.js"></script> 
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/Particleground.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<style>

body{height:100%;background:#16a085;overflow:hidden;}

</style>
</head>
<body >

   <div class="register_box">
   <bgsound src="music/welcome.mp3" autostart=true>

	<h3>考勤管理系统账号注册</h3>
	<form autocomplete="off" action="register.do" id="register" method="post" >
		
       <input id="name" name="username" class="register_input"  type="text" placeholder="请务必输入真实姓名(考勤需要)" onkeydown="convert()" />		
		 <input id="userid" name="userid" class="register_input"  type="text" placeholder="登录名(手动填写以名字拼音全称)">	
		<input id="tel" name="tel" class="register_input" minlength=11 maxlength=11 type="text" placeholder="请输入电话">
        <input  id="email" name="email" class="register_input"  type="email"  placeholder="请输入邮箱邮箱" onblur="checkEmailUsed()">
        <label id="infoemail" class="error"></label>		
		<input id="pass" name="password" class="register_input" minlength=7 maxlength=10 type="password"   placeholder="请输入密码">				
  <div id="slider">
    <div id="slider_bg"></div>
    <span id="label">>></span> <span id="labelTip">拖动滑块验证</span> 
</div>
		 
		<div class="mb2">
		<input type="submit" class="register-submit" value="注册" onsubmit="return check()"/>
</div>
		<div style="font-size:14px">已 有 账 号? 请 点 击 <a href="login.jsp" style="color:red">登录</a></div>
		<div>Copyright © 2017 jijiuxue All rights Reserved</div>
	</form>	
</div>

<script>
var flag=false;
var f;
 $(function () {
 //手机号码验证
  jQuery.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");
 //判断用户名是否是汉字
 jQuery.validator.addMethod("chcharacter", function(value, element) {
var tel = /^[\u4e00-\u9fa5]+$/;
return this.optional(element) || (tel.test(value));
}, "请输入汉字");
 
 
          $('body').particleground({
          dotColor: '#5cbdaa',
          lineColor: '#5cbdaa'
  });
     check();
  $.validator.setDefaults({
    submitHandler: function(form) {
    $("#pass").val(MD5($("#pass").val()));
f=check();
if(f==true){
form.submit();
}else{
alert("验证没通过");
}    
    }
}); 

 $("#register").validate({
 rules:{
  username:{
  required:true,
  chcharacter:true
  
  },
  tel:{
  required:true,
  isMobile:true
  },
  email:"required",
  password:"required",
  userid:"required"
 },
 messages:{
 username:{
 required:"姓名不能为空"
 },
 tel:{
 required:"电话号码不能为空",
 minlength:"长度不能小于11",
 maxlength:"长度不能大于11",

   },
 password:{ 
     required:"密码不能为空",
 minlength:"长度不能小于7",
 maxlength:"长度不能大于10",
   },
   email:"邮箱格式不正确",
   userid:"登录名不能为空"
 }
 });

 
 
    });
function  convert(){
	    var input =$("#name").val();
	     str = ConvertPinyin(input);
	     $("#userid").val(str);
	}  
function check(){
var slider = new SliderUnlock("#slider",{
				successLabelTip : "验证通过"	
		},function(){
				alert("验证成功"); 
				flag=true;           	
        	});
        slider.init();
        return flag;  
}	
//检查邮箱是否注册	
  function checkEmailUsed(){

  var email = $.trim($("#email").val());
  $.ajax({
			url:"checkEmail.do?email="+email,
			type: "GET",
			dataType:"text",
			async:false,
			success:function(value){
			if(value=="0"){
		    $("#infoemail").html("");		
			}else{
			if(email.indexOf("@")!=-1){
			$("#infoemail").html("邮箱已经被注册,请重新输入&nbsp;&nbsp;&nbsp;&nbsp;");
			}else{
			$("#infoemail").html("");
			}
			
			}
			}				
			});
			}
</script>

</body>
</html>