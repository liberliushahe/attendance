<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>sessiontimeoutpage</title>
    <link rel="stylesheet" type="text/css" href="css/layer.css" />
     <script type="text/javascript" src="js/jquery-1.12.1.min.js"></script> 
    <script type="text/javascript" src="js/layer.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>       
   <SCRIPT language="JavaScript">     
  layer.alert("会话超时,请重新登录",{
                skin: 'layui-layer-molv',
                closeBtn: 1,
                title:"考勤系统提醒您",
                shift: 1 //动画类型
            });   
   setTimeout(function () {       
   window.top.location.href="<%=path%>/login.jsp";     },2000); 
    
     </script>   </body>
</html>
