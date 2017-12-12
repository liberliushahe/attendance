<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>在线考勤系统修改密码</title>
  <head>
    <base href="<%=basePath%>">
        <script type="text/javascript" src="js/jquery-1.12.1.min.js"></script> 
    <link rel="shortcut icon" href="img/time.ico" >
    <link rel="stylesheet" type="text/css" href="css/login.css" />   
    <link rel="stylesheet" type="text/css" href="css/loginmain.css" />
    <link rel="stylesheet" type="text/css" href="css/layer.css" />

    <script type="text/javascript" src="js/jquery.slideunlock.js"></script> 
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/Particleground.js"></script>
    <script type="text/javascript" src="js/util.js"></script>
    <script type="text/javascript" src="js/layer.js"></script>
    <script type="text/javascript" src="js/md5.js"></script>
    <style>

body{height:100%;background:#16a085;overflow:hidden;}
.reademail{
color:white;
}
</style>
</head>
<body >

   <div class="modify_box">
   <bgsound src="music/welcome.mp3" autostart=true>

	<h3>考勤管理系统密码修改</h3>
	<form autocomplete="off" action="modify.do" id="modify" method="post" >		
   <div> <input  id="email" name="email" class="modify_input"  type="email"  placeholder="请输入邮箱邮箱" onblur="checkEmailUsed()"/>	<label id="infoemail" class="error"></label></div>
	<div><input   name="code" id="modify-input-code"  type="text"  placeholder="请输入验证码">&nbsp;<input type="button" id="modify_input_button" value="发送验证码" alt="发送验证码到邮箱" onclick="sendEmailCode(this)"/>
	<label id="come-email"></label>
	</div>
	
	
	
	<div><input id="pass" name="password" class="modify_input" minlength=5 maxlength=10 type="password"   placeholder="请输入密码"></div>				
	 
		<div class="mb2">
		<input type="submit" id="modifypass" class="modify-submit" value="修改"/><br/><br/>
		突然记起密码了请点击<a style="font-size:12px;color:yellow" href="login.jsp">登录</a><br/>
		Copyright © 2017 jijiuxue All rights Reserved
</div>
		
		
	</form>	
</div>

<script>

	var flag = false;
	var f;
	var countdown = 60;
	var emailcode="";
	$(function() {		

		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
		$.validator.setDefaults({
			submitHandler : function(form) {
			$("#pass").val(MD5($("#pass").val()));
			var codeflag= checkEmailCode();
			if(codeflag=="1"){		    
            form.submit();
		   
			
			}else if(codeflag=="0"){
			layer.alert("验证码不正确,请前往邮箱查看",{
                skin: 'layui-layer-molv',
                closeBtn: 1,
                title:"在线考勤提醒您",
                shift: 1 //动画类型
            });
			}else if(codeflag=="3"){
			layer.alert("请先获取验证码",{
                skin: 'layui-layer-molv',
                closeBtn: 1,
                title:"在线考勤提醒您",
                shift: 1 //动画类型
            });
			
			}
			
			
			else{
			layer.alert("验证码不能为空",{
                skin: 'layui-layer-lan',
                closeBtn: 1,
                title:"在线考勤提醒您",
                shift: 1 //动画类型
            });
			
			}
				
			}
		});

		$("#modify").validate({
			rules : {
				email : "required",
				password : "required",
				
			},
			messages : {
				password : {
					required : "密码不能为空",
					minlength : "长度不能小于5",
					maxlength : "长度不能大于10",
				},
				email : "邮箱格式不正确",
				
			}
		});

	});
	function checkEmailCode(){

	var flag;
	var code=$("#modify-input-code").val();
	if(code==emailcode && code!=""&& emailcode!="" && emailcode!="null" && code!="null"){
	flag=1;
	}else if(code===""){
	flag=2;
	}else if(emailcode==="" || emailcode==="null"){
	flag=3;
	}else{
	flag=0;
	}
	return flag;
	}
	function convert() {
		var input = $("#name").val();
		str = ConvertPinyin(input);
		$("#userid").val(str);
	}
   
	function sendEmailCode(val) {
		var email = $.trim($("#email").val());
		if (email === "") {
			layer.alert('请注意：邮箱不能为空',{
                skin: 'layui-layer-molv',
                closeBtn: 1,
                title:"考勤系统提醒您",
                shift: 1 //动画类型
            });
			return;
		} 
			settime(val);
			
			$.ajax({
			url:"sendCode.do?email="+email,
			type: "GET",
			dataType:"text",
			async:false,
			success:function(value){
			var subvalue=value.substring(0,1);
			emailcode=value.substring(1,7);
			if(subvalue=="1"){		
			$("#come-email").html("请立即前往邮箱<a href=\"https://mail.qq.com\" target=\"_blank\" class=\"reademail\">查看邮件</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			},
			error:function(){
			alert("邮件发送失败");
			}						
			});
		
	}
	function settime(val) {
		if (countdown == 0) {
			val.removeAttribute("disabled");
			val.value = "重新获取验证码";
			
		} else {
			val.setAttribute("disabled", true);
			val.value = "重新发送(" + countdown + ")";
			countdown--;
		}
		setTimeout(function() {
			settime(val);
		}, 1000);
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
			if(email.length!=0){
						$("#infoemail").html("该邮箱未注册,请先注册账号&nbsp;&nbsp;&nbsp;&nbsp;");
			
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