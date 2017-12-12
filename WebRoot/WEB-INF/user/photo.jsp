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
<script type="text/javascript" src="js/jquery.min.js" ></script>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" type="text/css" href="css/photo.css" /> 
 <link href="css/sim-prev-anim.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="css/main.css" /> 
    <link rel="stylesheet" type="text/css" href="css/layui.css" /> 

<script type="text/javascript" src="js/imageshow.js" ></script>
<script type="text/javascript" src="js/util.js" ></script>

  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/layui.js" ></script>
</head>

<script>

function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
    var t=document.getElementById("xt-right");
     t.onscroll=function(e)
{
    console.log(t.scrollTop);
 
    if(t.scrollTop>100){
    $("#goTopBtn").css("display","block");
    }else{
    $("#goTopBtn").css("display","none");
    }
}
    $("#getmore").removeAttr("href");
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
};
</script>
<style>
html{overflow:hidden}


.photo-container{
float:left;
margin:20px 20px 20px 20px;
}
.photo-style{
width:200px;
height:200px;
margin-left:100px;
border-radius:50%;
}
.photo-one-style{
float:left;
}
.photo-desc{
border-radius:20;
width:690px;
height:200px;
margin-left:20px;
background-color:white;
 border:1px dashed;
 border-radis:30;
 float:left;
 filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.8;
}

.person-name{
font-size:18px;
font-family:宋体;
margin-left:30px;
margin-top:15px;

}
.person-descirption{
font-size:18px;
font-family:宋体;
margin-left:30px;
margin-top:15px;

}
.person-descirption-detail-container{
width:100%;
height:30%;
overflow:auto;
}
.person-descirption-detail{
font-size:18px;
font-family: 楷体_GB2312;
margin-left:30px;
margin-top:4px;

}
.person-descirption-publish{
margin-left:61%;
position:relative;
top:13px;
font-style: italic;
}
#more{
width:100%;

margin:0 auto;
margin-bottom:3%;
height:30px;
float:left;
text-align:center;
}
#more a{display: block;width: 100px;padding:8px 0;color:#fff;margin:0 auto;background:#21b6b4;text-align:center;border-radius:3px;cursor: pointer;}

#goTopBtn{
position:fixed;

right:2%;
top:90%;
display:none;
}

#upwall{

position:fixed;

right:3%;
top:80%;
}
@-webkit-keyframes rotation{
from {-webkit-transform: rotate(0deg);}
to {-webkit-transform: rotate(360deg);}
}

.Rotation{
-webkit-transform: rotate(360deg);
animation: rotation 9s linear infinite;
-moz-animation: rotation 9s linear infinite;
-webkit-animation: rotation 9s linear infinite;
-o-animation: rotation 9s linear infinite;
}


</style>

<body>
<embed id="player" src="music/tiankong.mp3" autoplay="true" width="0" height="0" loop="true" />

<div class="qiqiu1 qiqiu">
    	<img src="img/201101asdsaf22135412367.png"/>
        <div class="text">记忆</div>
    </div>
    	<div class="qiqiu2 qiqiu">
    	<img src="img/201101ddd22135412367.png"/>
        <div class="text">友情</div>
    </div>
    	<div class="qiqiu3 qiqiu">
    	<img src="img/20110122135aaaaa412367.png"/>
        <div class="text">记忆</div>
    </div>
    	<div class="qiqiu4 qiqiu">
    	<img src="img/20110122135s412367.png"/>
        <div class="text">记忆</div>
    </div>
    	<div class="qiqiu5 qiqiu">
    	<img src="img/20110122135412367.png"/>
       <div class="text">美好</div>
    </div>
	
	<div class="yun1 yun"> </div>
	<div class="yun2 yun"> </div>
	<div class="yun3 yun"> </div>	
	<div class="yun4 yun"> </div>




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
            <li><a href="getUserPhoto.do" class="hover"><em class="two"></em>记忆相册</a></li>
        </ul>
                        <ul>
            <li><a href="getMessage.do" ><em class="two"></em>内部通讯</a></li>
        </ul>  
                <ul>
            <li><a href="getTimeLog.do" ><em class="two"></em>更新日志</a></li>
        </ul>     
                <ul>
		<li><a href="userAutoService.do"><em class="two"></em>自助服务</a></li>
		</ul> 
    </div>
</div>
<!-- right -->
<div id="xt-right">

   <div id="up-wall">
   <div style="margin:0 auto;font-size:16px">上墙信息</div>
   <br/>
    <form class="layui-form" id="up-wall-form" action="upWall.do" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block" style="width:240px">
      <input type="text" name="wall_username" lay-verify="title" autocomplete="off" placeholder="请输入姓名" class="layui-input" >
    </div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">名言</label>
    <div class="layui-input-block" style="width:240px">
      <input type="text" name="wall_mingyan" lay-verify="title" autocomplete="off" placeholder="请输入名言" class="layui-input" width="200px">
    </div>
  </div>
  
   <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">简介</label>
    <div class="layui-input-block" style="width:240px">
      <textarea  name="wall_textarea" placeholder="请输入简介内容" class="layui-textarea"></textarea>
     
    </div>
  </div> 
   <input type="submit" class="layui-btn" value="立即提交"/>
      <button type="reset" class="layui-btn layui-btn-primary" onclick="upwallend()">立即关闭</button>
  </form>
   </div>





<div id="upwall">
<input value="我要上墙" type="button" class="green-int" onclick="upwall()"/>
</div>
<div id="goTopBtn" onclick="returntop()">
<img src="images/top.png" onclick="document.getElementById('xt-right').scrollTop=0;"/>
</div>
   <div style="margin:0 auto;font-size:27px;margin-left:39%;margin-bottom:10px;height:64px;"><img src="images/photo.png"/></div>

<div class="photo-all-container">
<c:forEach items="${List}" var="photo">

<div class="photo-container">
<div class="photo-one-style"   ><img src="userimage/${photo.path }" class="Rotation photo-style" title="用户头像" /></div>
<div class="photo-desc">
<p class="person-name">个人姓名:${photo.title }</p>
<p class="person-descirption">个人名言：${photo.wisdom }</p>
<p class="person-descirption">简要介绍:</p>
<div class="person-descirption-detail-container">
<p class="person-descirption-detail">${photo.content }</p>

</div>
<p class="person-descirption-publish">时间：${photo.publishdate } ${photo.author }发布</p>
</div>
</div>

</c:forEach>
</div>






<div id="more"><a href="" onclick="getMore()" id="getmore" style="cursor:hand">加载更多..</a></div>
</div>		
</div>

<script>
var current=5;
$(function(){
$("#up-wall-form").validate({
			rules : {
				wall_username : "required",
				wall_mingyan: "required",
				wall_textarea:"required",
			},
			messages : {
				wall_username : {
					required : "姓名不能为空",
					
				},
				wall_mingyan : "名言不能为空",
				wall_textarea:"介绍不能为空",
			}
		});


});
function getMore(){
$.ajax({
   url:"getMore.do?current="+current,
   type:"POST",
   dataType:"JSON",
   success:function(data){
   if(data.length!=0){
      for(var i=0;i<data.length;i++){
    $(".photo-all-container").append('<div class="photo-container">'+
   '<div class="photo-one-style"   ><img src="userimage/'+data[i].path+'" class="Rotation photo-style" title="用户头像" /></div>'+
   '<div class="photo-desc">'+
   '<p class="person-name">个人姓名:'+data[i].title+'</p>'+
   '<p class="person-descirption">个人名言：'+data[i].wisdom+'</p>'+
   '<p class="person-descirption">简要介绍:</p>'+
   '<div class="person-descirption-detail-container">'+
   '<p class="person-descirption-detail">'+data[i].content+'</p>'+
   '</div>'+
   '<p class="person-descirption-publish">时间：'+data[i].publishdate+' '+data[i].author+'发布</p>'+
   '</div>'+
   '</div>');       
   }
   }else{ 
   document.getElementById("getmore").innerHTML="数据加载完";
   } 
   current+=5;
   }

});
}
function loginout(){
	if(confirm("确定退出系统")){
	$("#loginout").attr("href","loginout.do");
	 }
	}

function upwall(){
$("#up-wall").slideDown(500);

}	
function upwallend(){
$("#up-wall").hide();
}

 function returntop(){
  var top=document.getElementById('x-right').scrollTop=0;
 if(top!=0){
  setInterval(function(){top-10},3000);
 }
 }
</script>
</body>

</html>
