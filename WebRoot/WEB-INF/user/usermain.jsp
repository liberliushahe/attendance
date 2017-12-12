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
<title>管理首页</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/float.js" ></script>
 <script type="text/javascript" src="js/jquery.leoweather.min.js" ></script>
<script type="text/javascript" src="js/clender.js" ></script>
<script type="text/javascript" src="js/util.js" charset=”utf-8″></script>
<style>

@media screen and (max-width: 300px) {
   #mainnotice{
   color:yellow;
   font-size:10px;
   } 
}
</style>
<script>
function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
};
$(function(){
 
      getreport();
	 $('#uploaduserimage').after('<input type="file" id="loadimage" name="imagefile" style="display:none" onchange ="uploadUserImage()">');
	$('#uploaduserimage').click(function(){
        document.getElementById("loadimage").click();
    });

	$('#weather').leoweather({format:'  {时段}好！，<span id="colock">现在时间是：<strong>{年}年{月}月{日}日 星期{周} {时}:{分}:{秒}</strong>，</span> <b>{城市}天气</b> {天气} {夜间气温}℃ ~ {白天气温}℃'});
    initial();
 

    
});




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
            alert("文件类型不符：正确类型.gif,.png,.jpeg,.jpg,.bmp");
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

function closenotice(){
 $("#mainnotice").css("display","none");
}
</script>
</head>



<body>
<div id="cloud1" class="cloud"></div>
<!-- load div -->
<div id="bg_div">
<div id="bg_div_loadimage"><img src="img/load.gif"/></div>
</div>
<!-- top -->
<div id="xt-top">


    <div class="xt-logo"><img src="img/logo.png" width="160px" height="50px"/></div>
    <div class="xt-geren">
        <div class="xt-exit"><span class="xt-span">您好! ${sessionScope.username},欢迎您 登录管理中心<span class="xt-yanse"></span></span>
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
            <li><a href="usermain.do" class="hover"><em class="one"></em>网站首页</a></li>
        </ul>
        <ul>
            <li><a href="getUserList.do"><em class="two"></em>个人考勤</a></li>
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
            <li><a href="getTimeLog.do" ><em class="two"></em>更新日志</a></li>
        </ul>  
        <ul>
		<li><a href="userAutoService.do"><em class="two"></em>自助服务</a></li>
		</ul>
    </div>
</div>
<!-- right -->
<div id="xt-right">
    <div class="xt-bt">基础信息 > 考勤信息管理 >管理首页</div>
    <div id="mainnotice">
     <h2>网站公告</h2>
     <hr/>
     <br/>
     <p style="text-align:left;color:white;padding:2px;text-indent:2em;line-height:21px">当月考勤数据查看无误之后，请点击考勤确认按钮，如果没有确认系统默认会发邮件提醒。全部人员确定通过之后系统自动发送邮件至公司，望各位知悉！</p>
     <br/>
     <input type="button" value="关闭" class="green-int" onclick="closenotice()"/>
    </div>
      <div>&nbsp;<marquee behavior="scroll"><span id="weather" style="font-size: 15px;"></span></marquee></div>
      <div id="index-main">
      <div id="index-main-middle-left">
      <div class="own-info">&nbsp;&nbsp;个人信息</div> 
      <div id="own-left">
      <img id="headimage" src="userimage/${sessionScope.imagepath}" style="border-radius:13px;width:165px;height:175px;" alt="头像" /><br/>
      <input type="button" value="点击上传头像" id="uploaduserimage" style="background: #00CCFF; outline:none;color: #fff;  padding: 5px 20px; border: none; border-radius: 8px; font-size: 14px;"/>
      </div>
      <div id="own-right">
      <span>当前登录用户：${sessionScope.employee.username}</span><br/>
      <span>当前登录ip：${sessionScope.ip}</span><br/><br/>
      <span>是否认证:&nbsp;<span style="color:#fff;width:60px;height:20px;background-color:#1eb86b; padding: 2px 6px;border-radius: 4px;" >已实名认证</span></span><br/>
      <span>联系号码:${sessionScope.employee.phone}</span><br/>
      <a href="https://mail.qq.com" target="_blank">个人邮箱:<span>${sessionScope.employee.email}</span></a>
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
<span class="matter">本月考勤记录：<strong>${requestScope.usercount}</strong>条</span>
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
<script type="text/javascript" src="js/echarts.min.js" ></script>

<script>
var myChart = echarts.init(document.getElementById("index-main-number"));
 var  option = {
     title : {
        text: '月打卡时间变化'
        
    },
     legend: {
        data:['下班打卡时间','上班打卡时间']
    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
     toolbox: {
        show : true,
        feature : {
            saveAsImage : {show: true}
        }
    },
    grid: {
        left: '1%',
        right: '1%',
        bottom: '6%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : [],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
             min:6,
             scale: true, 
          
             splitNumber: 4, 
             boundaryGap: [0.01, 0.01], 
             splitArea: { show: true } 
             
           
        }
    ],
    series : [
        {
            name:'上班打卡时间',
            type:'line',
            barWidth: '50%',
            data:[]
          
        },
        {
            name:'下班打卡时间',
            type:'line',
            barWidth: '50%',
            data:[]
             
        }
    ]
};

    myChart.setOption(option);  





var usermonth=[];
var starttime=[];
var endtime=[];
function getreport(){
      $.ajax({
         url:"getReport.do",
           type:"POST",
           dataType:"json",
           success:function(data){       
           var month=data[0];
           for(var i=0;i<month.length;i++){
           usermonth.push(month[i]);
           }
           var start=data[1];
           for(var j=0;j<start.length;j++){
           starttime.push(start[j]);
           }
           var end=data[2];
           for(var k=0;k<end.length;k++){
           endtime.push(end[k]);
           }
           
           myChart.setOption({
            xAxis : [
        {
        
            data :usermonth
           
        }
    ],
      series : [
        {
           name:'上班打卡时间',
            data:starttime
                       
        },
        {
           name:'下班打卡时间',
            data:endtime
           
        }
    ]     
           
           });
           },
           error:function(){
           alert("error");
           }
});
}
var myChart = Echarts.init(document.getElementById("index-main-number"));
$('#index-main-middle-right').resize(function () {
    myChart.resize();
});
</script>
</body>
</html>
