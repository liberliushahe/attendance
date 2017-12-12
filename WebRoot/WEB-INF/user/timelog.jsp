<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="img/time.ico" >
<title>管理平台</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" type="text/css" href="css/layui.css" />
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="js/layui.all.js" ></script>
<script type="text/javascript" src="js/util.js"  charset="utf-8"></script>
</head>
<script>
function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
};
function loginout(){
	if(confirm("确定退出系统")){
	$("#loginout").attr("href","loginout.do");
	 }
	}
	
</script>


<body>
<!-- load div -->
<div id="bg_div">
<div id="bg_div_loadimage"><img src="img/load.gif"/></div>
</div>
<!-- top -->
<div id="xt-top">
    <div class="xt-logo"><img src="img/logo.png" width="160px" height="50px"/></div>
    <div class="xt-geren">
        <div class="xt-exit"><span class="xt-span">您好! ${sessionScope.username},欢迎您<span class="xt-yanse"></span>登录管理中心</span>
            <a href="#" id="loginout" class="exit" onclick="loginout()">退出</a></div>
    </div>
</div>
<!-- left -->
<div class="xt-center">
<div id="xt-left">
<div id="bg_music"></div>
    <div class="xt-logo"></div>
    <div class="xt-menu">
        <ul>
            <li><a href="usermain.do" ><em class="one"></em>网站首页</a></li>
        </ul>
        <ul>
            <li><a href="getUserList.do" ><em class="two"></em>个人考勤</a></li>
        </ul>
        				 <ul>
                 <li><a href="quickQuery.do" ><em class="two"></em>快捷查询</a></li>
                </ul>  
        <ul>
            <li><a href="getUserPhoto.do"><em class="two"></em>记忆相册</a></li>
        </ul>   
                                <ul>
            <li><a href="getMessage.do" ><em class="two"></em>内部通讯</a></li>
        </ul>  
                        <ul>
            <li><a href="getTimeLog.do" class="hover"><em class="two"></em>更新日志</a></li>
        </ul>   
                <ul>
		<li><a href="userAutoService.do"><em class="two"></em>自助服务</a></li>
		</ul>
    </div>
</div>
<!-- right -->
<div id="xt-right" >
   
   <div id="shade"></div>  
    <div class="xt-bt">管理系统 > 考勤信息管理 > 日志更新</div>
<ul class="layui-timeline" style="margin-left:60px;margin-top:10px">
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">8月18日</h3>
      <p>
                       一切准备工作似乎都已到位。发布之弦，一触即发。
        <br>不枉近百个日日夜夜与之为伴。因小而大，因弱而强。
        <br>无论它能走多远，抑或如何支撑？至少我曾倾注全心，无怨无悔 <i class="layui-icon"></i>
      </p>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">8月16日</h3>
      <p>快捷查询功能开发</p>
      <ul>
        <li>联系人快捷查询功能实现通过ajax异步获取用户姓名快捷操作和查询</li>
        <li>内部人员信息导入功能开发</li>
      </ul>
    </div>
  </li>
    <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">8月6日</h3>
      <p>记忆相册功能开发和内部通讯功能开发</p>
      <ul>
        <li>通过懒加载方式实现记忆相册功能开发</li>
        <li>websocket实现即时通讯</li>
      </ul>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">7月26日</h3>
      <p>图表实现考勤个人数据统计和全员考勤数据统计</p>
      <ul>
        <li>个人首页实现考勤数据图表展示</li>
        <li>所有考勤人员总考勤数据统计图表实现</li>
      </ul>
    </div>
  </li>
    <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">7月16日</h3>
      <p>考勤数据导入功能开发和邮件发送模块开发</p>
      <ul>
        <li>通过excel文件实现数据获取</li>
        <li>javamail插件实现邮件发送模块</li>
      </ul>
    </div>
  </li>
    <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">7月6日</h3>
      <p>前端页面的实现 ，权限控制实现</p>
      <ul>
        <li>登录界面 login.jsp和打卡界面index.jsp</li>
        <li>后台管理界面实现</li>
      </ul>
    </div>
  </li>
    <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">6月16日</h3>
      <p>开发相关业务dao层实现,service 实现 ，controll实现</p>
      <ul>
        <li>工具包开发</li>
        <li>配置文件设置</li>
      </ul>
    </div>
  </li>
    <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">5月26日</h3>
      <p>开始搭建考勤管理系统项目架构，基本类的设计和数据库的设计</p>
      <ul>
        <li>dao service controll util</li>
        <li>timetable考勤表等</li>
      </ul>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">5月15日</h3>
      <p>
       开始项目框架的搭建
        <br>数据库的设计和实现
        <br>项目框架搭建和环境配置

      </p>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
    <div class="layui-timeline-content layui-text">
      <div class="layui-timeline-title">过去</div>
    </div>
  </li>
</ul>


</div>
</div>

</body>
</html>
