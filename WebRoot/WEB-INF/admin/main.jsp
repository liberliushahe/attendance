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
<title>管理首页</title>
<link rel="shortcut icon" href="img/time.ico" >
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/jquery.leoweather.min.js" ></script>
<script type="text/javascript" src="js/echarts.min.js" ></script>
<script type="text/javascript" src="js/util.js"></script>
<script>
function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
$('#uploaduserimage').after('<input type="file" id="loadimage" name="imagefile" style="display:none" onchange ="uploadUserImage()">');
	$('#uploaduserimage').click(function(){
        document.getElementById("loadimage").click();
    });
    
	$('#weather').leoweather({format:'  {时段}好！，<span id="colock">现在时间是：<strong>{年}年{月}月{日}日 星期{周} {时}:{分}:{秒}</strong>，</span> <b>{城市}天气</b> {天气} {夜间气温}℃ ~ {白天气温}℃'});
    
initial();
};






   

function uploadUserImage(){
        var image = new FormData();
        image.append('imagefile',$('#loadimage')[0].files[0]);
        $.ajax({
   
            url: "uploadImage.do",
            type: "POST",
            data: image,
            async:false,
            dataType:"text",
            contentType : false, 
            processData: false,
            scriptCharset: "utf-8",
            success: function (data) {
            if(data=="1"){
            alert("头像上传成功");
            window.location.reload();
            }else if(data=="2"){
            alert("文件类型不符：正确类型gif,png,jpeg,jpg,bmp");
            }else if(data=="3"){
            alert("文件大于500K");
            }else{
            alert("头像上传失败");
            }
            }
            
        });
       
}
function loginout(){
if(confirm("确定退出系统")){
$("#loginout").attr("href","loginout.do");
 }
}

</script>
</head>



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
            <li><a href="main.do" class="hover"><em class="one"></em>网站首页</a></li>
        </ul>
               <ul>
            <li><a href="getList.do"><em class="two"></em>考勤管理</a></li>
        </ul>
        <ul>
            <li><a href="getUser.do"><em class="two"></em>用户管理</a></li>
        </ul>
        <ul>
            <li><a href="system.do"><em class="four"></em>系统设置</a></li>
        </ul>
        <ul>
            <li><a href="statis.do"><em class="five"></em>报表数据</a></li>
        </ul>
         <ul>
            <li><a href="getNotice.do"><em class="five"></em>公告管理</a></li>
        </ul>
          <ul>
            <li><a href="getPhoto.do"><em class="five"></em>相册管理</a></li>
        </ul>
        <ul>
            <li><a href="quickAdminQuery.do"><em class="five"></em>快捷查询</a></li>
        </ul>
        
        
    </div>
</div>
<!-- right -->
<div id="xt-right">
    <div class="xt-bt">基础信息 > 考勤信息管理 >管理首页</div>
    
    
    
      <div>&nbsp;<marquee behavior="scroll"><span id="weather" style="font-size: 10px;"></span></marquee></div>
      <div id="index-main">
      <div id="index-main-middle-left">
      <div class="own-info">&nbsp;&nbsp;个人信息</div>
      <div id="own-left">
      <img id="headimage" src="userimage/${sessionScope.imagepath}" style="border-radius:13px;width:165px;height:175px;" alt="头像" /><br/>
      <input type="button" value="点击上传头像" id="uploaduserimage" style="background: #00CCFF; outline:none;color: #fff;  padding: 5px 20px; border: none; border-radius: 8px; font-size: 14px;"/>
      </div>
      <div id="own-right">
      <span>当前登录用户：${sessionScope.employee.username}</span><br/><br/>
      <span>当前登录ip：${sessionScope.ip}</span><br/><br/>
      <span>是否认证:&nbsp;<span style="color:#fff;width:60px;height:20px;background-color:#1eb86b; padding: 2px 6px;border-radius: 4px;" >已实名认证</span></span><br/><br/>
      <span>联系号码:${sessionScope.employee.phone}</span><br/><br/>
      <span>个人邮箱:${sessionScope.employee.email}</span>
      </div>
      
      </div>
      <div id="index-main-middle-right">
      <div class="own-info" id="chart">&nbsp;&nbsp;考勤统计</div>
      <div id="index-main-number"></div>
      </div>
      </div>
<div id="index-main-foot">
 <div id="index-main-foot-left">
 <div class="own-info">&nbsp;&nbsp;网站公告</div>
 <div id="index-main-foot-left-info">
 <ul>
 
 <c:forEach items="${sessionScope.list}" var="notice">
  <li><img src="img/ico_new.png" alt="" />&nbsp;&nbsp;${notice.title} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="font-size:9px;font-style: italic;text-align:right">${notice.publishdate} &nbsp;&nbsp;${notice.author}</span></li>

 </c:forEach>

 </ul>
 
 </div>
 
 </div>
<div id="index-main-foot-right">
<div class="own-info">&nbsp;&nbsp;个人事项</div>
<div class="own-info-detail" id="own-info-detail1">
<br/>
<img src="img/notepadsmall2.png" alt="" /><br/>
<span class="matter">本月考勤记录：<strong>${requestScope.admincount}</strong>条</span>
</div>
<div class="own-info-detail" id="own-info-detail2">
<br/>
<img src="img/notepadsmall1.png" alt="" /><br/>
<span class="matter">本月迟到次数：<strong>0</strong>次</span>
</div>
<div class="own-info-detail" id="own-info-detail3">
<br/>
<img src="img/notepadsmall3.png" alt="" /><br/>
<span class="matter">本月早退次数：<strong>0</strong>次</span>
</div>
</div>
</div>
<div id="copyright">版权所有 © 2017-6-jijiuxue 甘ICP05001297号</div>
</div>

</div>
<script>
  var myChart = echarts.init(document.getElementById("index-main-number"));
 var  option = {
    
    
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '2%',
        right: '2%',
        bottom: '14%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['1', '2', '3','4', '5', '6', '7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'上班打卡时间',
            type:'line',
            barWidth: '40%',
            data:['7.23', '8.52', '8.12', '8.52', '7.12', '8.52', '8.12', '8.52', '7.12', '8.52', '8.12', '8.52', '8.12', '8.12', '7.52', '8.12', '8.12', '8.52', '8.12', '8.12', '8.12', '8.52', '8.12', '8.52']
        },
        {
            name:'下班打卡时间',
            type:'line',
            barWidth: '40%',
            data:['18.13', '18.52', '18.12', '18.52', '17.12', '18.52', '18.12', '18.52', '17.12', '18.52', '18.12', '18.52', '18.12', '18.12', '17.52', '18.12', '18.12', '18.52', '18.12', '18.12', '18.12', '18.02', '18.12', '18.52']
        }
    ]
};

    myChart.setOption(option);  

   
</script>

</body>
</html>
