<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>modifypass</title>

	
	 <link rel="stylesheet" type="text/css" href="css/styles.css" />
	<script type="text/javascript" src="js/jquery-1.12.1.min.js"></script> 
<script type="text/javascript" src="js/time.js"></script> 
  </head>
  
  <body>    
  
    
  <div class="game_time">

	<div class="hold">
		<div class="pie pie1"></div>
	</div>

	<div class="hold">
		<div class="pie pie2"></div>
	</div>

	<div class="bg"> </div>
	
	<div class="time"></div>
	
</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p>密码修改成功,5秒后页面自动跳转至登录界面，请稍后.... </p>
</div>
   <SCRIPT language="JavaScript">        
   setTimeout(function () {       
   window.top.location.href="<%=path%>/login.jsp";     },5000); 
    countDown();
     </script>   </body>
</html>
