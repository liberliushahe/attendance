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
<title>管理平台</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
</head>

<script>
$(function() { 

$(".update-form").each(function(){ 
    var tmp=$(this).children().eq(8); 
    var btn=tmp.children("#edit"); 
    btn.bind("click",function(){ 
        var id=btn.parent().parent().children("td").get(0).innerHTML; 
        var name=btn.parent().parent().children("td").get(1).innerHTML; 
        var date=btn.parent().parent().children("td").get(2).innerHTML; 
        var start=btn.parent().parent().children("td").get(3).innerHTML;
        var end=btn.parent().parent().children("td").get(4).innerHTML;
        var week=btn.parent().parent().children("td").get(5).innerHTML;
        var holiday=btn.parent().parent().children("td").get(6).innerHTML;
        var explain=btn.parent().parent().children("td").get(7).innerHTML;
        $("#update-id").val(id);
        $("#update-name").val(name); 
        $("#update-date").val(date);
        $("#update-start").val(start); 
        $("#update-end").val(end); 
        if(week=="是"){
        $("#update-week-yes").attr('checked', true); 
        }else{
         $("#update-week-no").attr('checked', true); 
        }
        if(holiday=="是"){
        $("#update-holiday-yes").attr('checked', true); 
        }else{
         $("#update-holiday-no").attr('checked', true); 
        } 
        $("#update-explain").val(explain); 
        update();
        }); 
    }); 
}); 

function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
}
function query(){
  var name=$("#username").val();
  var start_time=$("#start-time");
  var end_time=$("#end-time");
  if(name==""){
  alert("用户名不能为空");
  }
  if(start_time>end_time){
  alert("开始日期不能大于结束时间");
  }
  if(name!=""){
  $("#querybyname").submit();
  }
  
}
function add(){
$("#shade").show();
$("#addform").slideDown(500);
   }
function update(){

$("#shade").show();
$("#updateform").slideDown(500);
   }
function addend(){
$("#addform").hide();
$("#shade").hide();
}
function updateend(){
$("#updateform").hide();
$("#shade").hide();
}
function loginout(){
if(confirm("确定退出系统")){
$("#loginout").attr("href","loginout.do");
 }
}
</script>

<body>
<!-- top -->
<div id="xt-top">
    <div class="xt-logo"><img src="img/logo.png" width="160px" height="50px"/></div>
    <div class="xt-geren">
        <div class="xt-exit"><span class="xt-span">您好! 管理员:${sessionScope.username}，欢迎您<span class="xt-yanse"></span>登录管理中心</span>
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
            <li><a href="getList.do" class="hover"><em class="two"></em>考勤管理</a></li>
        </ul>
        <ul>
            <li><a href="#"><em class="three"></em>用户管理</a></li>
        </ul>
        <ul>
            <li><a href="#"><em class="four"></em>系统设置</a></li>
        </ul>
        <ul>
            <li><a href="#"><em class="five"></em>报表数据</a></li>
        </ul>
         <ul>
            <li><a href="#"><em class="six"></em>公告管理</a></li>
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
    <!-- 增加记录 -->
   <div id="addform">
   <form action="add.do" id="adddata" method="post">
   <span style="font-size: 19px">添 加 记 录</span><br/><br/>
   <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input type="text" class="int-text" name="username"/><br/>
   <br />
   <span>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</span><input type="text" class="int-text" name="date" onclick="WdatePicker()"/><br/><br/>
   <span>上班打卡时间:</span><input type="text" class="int-text" name="start-time" onclick="WdatePicker({dateFmt:'H:mm:ss'})"/><br/><br/>
   <span>下班打卡时间:</span><input type="text" class="int-text" name="end-time" onclick="WdatePicker({dateFmt:'H:mm:ss'})"/> <br/> <br/>
   <span>周&nbsp;&nbsp;末&nbsp;&nbsp;加&nbsp;&nbsp;班:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是&nbsp;&nbsp;<input type="radio" class="int-text" name="week" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="week" value="0" checked="checked"/><br/><br/>
   <span>假&nbsp;&nbsp;日&nbsp;&nbsp;加&nbsp;&nbsp;班:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" value="0" checked="checked"/><br/><br/>
   <span>说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明:</span><input type="text" class="int-text" name="explain" /><br/><br/>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="增加" class="green-int" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="button" value="关闭" class="green-int" onclick="javascript:addend()" />
   </form>   
   </div>
   <!-- 更新数据 -->
      <div id="updateform">
   <form action="update.do" id="adddata" method="post">
   <span style="font-size: 19px">更 新 记 录</span><br/><br/>
   <input type="hidden" class="int-text" name="id" id="update-id" />
   <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input type="text" class="int-text" name="username" id="update-name"/><br/>
   <br />
   <span>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</span><input type="text" class="int-text" name="date" id="update-date"/><br/><br/>
   <span>上班打卡时间:</span><input type="text" class="int-text" name="starttime" id="update-start" value="1"/><br/><br/>
   <span>下班打卡时间:</span><input type="text" class="int-text" name="endtime" id="update-end" value="0"/> <br/> <br/>
   <span>周&nbsp;&nbsp;末&nbsp;&nbsp;加&nbsp;&nbsp;班:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是&nbsp;&nbsp;<input type="radio" class="int-text" name="week" id="update-week-yes" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="week" id="update-week-no" value="0"/><br/><br/>
   <span>假&nbsp;&nbsp;日&nbsp;&nbsp;加&nbsp;&nbsp;班:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" id="update-holiday-yes" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" id="update-holiday-no" value="0"/><br/><br/>
   <span>说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明:</span><input type="text" class="int-text" name="explain" id="update-explain"/><br/><br/>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="更新" class="green-int" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="button" value="关闭" class="green-int" onclick="javascript:updateend(this)" />
   </form>   
   </div>
   
    <div class="xt-bt">管理系统 > 考勤信息管理 > 个人考勤管理</div>
    <div class="xt-input">
       <form action="query.do" method="post" id="querybyname">
       <span>姓名</span><input type="text" class="int-text" id="username" name="username"/>
        <span>开始日期</span><input type="text" class="int-text" id="start-time" name="starttime" onclick="WdatePicker({dateFmt:'yyyy/M/dd'})"/>
        <span>结束日期</span><input type="text" class="int-text" id="end-time" name="endtime" onclick="WdatePicker({dateFmt:'yyyy/M/dd'})"/>
        &nbsp;&nbsp;
        <input type="button" value="开始查询" class="green-int" onclick="query()"/>
         &nbsp;&nbsp;<input type="button" value="增加考勤" class="green-int" onclick="add()"/>
     
       </form>
        
    </div>
    <div class="xt-table">
        <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%">
            <tr>
            <th>编号</th>           
            <th>姓名</th>
            <th>日期</th>
            <th>上班打卡时间</th>
            <th>下班打卡时间</th>
            <th>周末加班</th>
            <th>假日加班</th>
            <th>说明</th>
            <th>操作</th>
            </tr>
            <c:forEach items="${List}" var="timetable">
            <tr class="update-form">
                <td>${timetable.id}</td>
                <td>${timetable.username}</td>
                <td>${timetable.date}</td>
                <td>${timetable.starttime}</td>
                <td>${timetable.endtime}</td>
                
                <td>${timetable.weekend}</td>                             
                <td>${timetable.holiday}</td>
                <td>${timetable.explain}</td>
                <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a href="delete.do?id=${timetable.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="xt-fenye">
        <div class="xt-fenye-left">当前第 ${page.currentPage} / ${page.totalPage}页,每页10条，共 ${page.totalCount}条记录</div>
        <div class="xt-fenye-right">
        <c:choose>
         <c:when test="${page.hasPrePage}">
         <a href="getNotice.do?currentPage=1">首页</a>  
	<a href="getNotice.do?currentPage=${page.currentPage -1 }">上一页</a>
         </c:when>
        <c:otherwise>
        <a >首页</a> <a>上一页</a>
        </c:otherwise>
        </c:choose>
                <c:choose>
         <c:when test="${page.hasNextPage}">
         <a href="getNotice.do?currentPage=${page.currentPage + 1 }">下一页</a>
	<a href="getNotice.do?currentPage=${page.totalPage }">尾页</a>
         </c:when>
        <c:otherwise>
        <a>下一页</a> <a>尾页</a>
        </c:otherwise>
        </c:choose>
            
            <input type="text" name="text" />
            <a href="#" class="xt-link">跳转</a>
        </div>
    </div>

</div>
</div>

</body>
</html>
