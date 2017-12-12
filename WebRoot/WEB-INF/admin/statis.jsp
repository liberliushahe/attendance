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
<title>报表统计</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/echarts.min.js" ></script>
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
 <div class="xt-logo"><img src="img/logo.png" width="160px" height="50px"/></div>    <div class="xt-geren">
        <div class="xt-exit"><span class="xt-span">您好! 管理员：${sessionScope.username},欢迎您<span class="xt-yanse"></span>登录管理中心</span>
            <a href="#" id="loginout" class="exit" onclick="loginout()">退出</a></div>
    </div>
</div>
<!-- left -->
<div class="xt-center">
<div id="xt-left">
    <div class="xt-logo"></div>
    <div class="xt-menu">
        <ul>
            <li><a href="main.do" ><em class="one"></em>网站首页</a></li>
        </ul>
        <ul>
            <li><a href="getList.do" ><em class="two"></em>考勤管理</a></li>
        </ul>
        <ul>
            <li><a href="getUser.do" ><em class="three"></em>用户管理</a></li>
        </ul>
        <ul>
            <li><a href="system.do"><em class="four"></em>系统设置</a></li>
        </ul>
        <ul>
            <li><a href="statis.do" class="hover"><em class="five"></em>报表数据</a></li>
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
   
   <div id="shade"></div>
    <div class="xt-bt">管理系统 > 考勤信息管理 >统计管理</div>
  <div id="statis"></div>
   
   <hr/>
           <div class="xt-input">
      <span style="color:green;font-size:15px">确认人员：</span>   
     <c:forEach items="${sure}" var="name">
     <span class="green-int">${ name}</span>
     </c:forEach>
    </div>
        <div class="xt-input">
      <span style="color:red;font-size:15px">未确认人员：</span>
            <c:forEach items="${unsure}" var="employee">
     <span class="red-int">${ employee.username}</span>
     </c:forEach>
    </div>
     <div class="xt-input">
      <span style="color:green;font-size:15px">今日在线人员：</span>
            <c:forEach items="${work}" var="employee">
     <span class="green-int">${ employee.username}</span>
     </c:forEach>
    </div>
    <div class="xt-input">
      <span style="color:red;font-size:15px">今日离线人员：</span>
            <c:forEach items="${unwork}" var="employee">
     <span class="red-int">${ employee.username}</span>
     </c:forEach>
    </div>
</div>
</div>
<script>
  var myChart = echarts.init(document.getElementById("statis"));
option = {
    title : {
        text: '当月考勤记录统计',
        subtext: '数据由打卡机提供'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['考勤']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
     grid: {
        left: '1%',
        right: '3.5%',
        top: '28%',
        bottom: '10%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
    
        {
            type:'bar',
            itemStyle:{
            normal:{
            color:function(params){
            var colorList=['#C1232B','#B5C334','#C6E579','#F0805A','#9BCA63'];
            return colorList[params.dataIndex];
            }           
            }           
            },
            
            markLine : {
                data : [
                    {type : 'average', name : '平均值',
                     symbol:'pin',
                     symbolSize:[10,20]
                    },                   
                ],
                itemStyle:{
                normal:{
                color:'#2F4554'
                }
                }
                
            }
        }
    ]
};                                        
    myChart.setOption(option); 
//定时获取实时数据
var username=[];
var usercount=[];
$(function(){
  getUserReport();
});
 function getUserReport(){
      $.ajax({
         url:"getUserReport.do",
           type:"POST",
           dataType:"JSON",
           success:function(data){     
           for(var i=0;i<data.length;i++){
           username.push(data[i].name);
           usercount.push(data[i].count);
           }
                                         
           myChart.setOption({
            xAxis : [
        {
        
            data :username
           
        }
    ],
      series : [
        {
           name:'上班天数',
            data:usercount,
           barWidth: '30%',
           markPoint:{
            data:[
             {type:'max',name:'最大值'},
             {type:'min',name:'最小值'}
            
            ]           
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]     
           
           });
           },
           error:function(){
           console.log("error");
           }
});
}     
     
</script>
</body>
</html>
