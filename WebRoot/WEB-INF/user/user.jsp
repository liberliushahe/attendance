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
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
  <script type="text/javascript" src="js/layer.js"></script>
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
$(function(){
 var video1=getCookies("manage");
if(video1!="yes-1"){
  setCookies("manage","yes-1",30);
 $('#bg_music').append('<embed id="player" src="music/prompt.wav" autoplay="true" width="0" height="0" loop="false" />');

}


var info="${sessionScope.info}";
if(info!=""){
$("#createform").hide();
alert(info);

}
 $("#tableinfo").css("background-color","#B2E0FF");
 $('#load').after('<input type="file" id="load_xls" name="file" style="display:none" onchange ="uploadFile()">');
    $('#load').click(function(){
        document.getElementById("load_xls").click();
    });
});

function uploadFile() {
    var myform = new FormData();
        myform.append('file',$('#load_xls')[0].files[0]);
        $.ajax({
   
            url: "upload.do",
            type: "POST",
            data: myform,
            async:false,
            dataType:"text",
            contentType : false, 
            processData: false,
            scriptCharset: "utf-8",
            success: function (data) {
            if(data=="1"){
            alert("文件导入成功");
            window.location.reload();
            }else{
            alert("该文件已经存在");
            }
            }
            
        });
       
    }



function add(){
$("#shade").show();
$("#addform").slideDown(500);
var user="${sessionScope.employee.username}";
$("#username1").val(user);
}
 function querybydate(){
  var start_time=$("#start-time").val();
$("#query-date").attr("href","getUserList.do?date="+start_time);
}  


function loginout(){
	if(confirm("确定退出系统")){
	$("#loginout").attr("href","loginout.do");
	 }
	}
function checkaddform(){
var username1=$("#username1").val();
var date1=$("#date1").val();
if(username1==""){
alert("用户名不能为空");
}
if(date1==""){
alert("日期不能为空");
}
if(username1!="" && date1!=""){
$("#adddata").submit();
}
}	
function createtable(){
$("#shade").show();
$("#createform").slideDown(500);
}
function checkcreateform(){
var name=$("#username").val();
var date=$("#date").val();
if(name==""){
alert("用户名不能为空");

}
if(date==""){
alert("日期不能为空");

}
if(name!="" && date!=""){
$("#createdata").submit();
}
}
function createend(){
$("#createform").hide(300);
$("#shade").hide();
}
function batchDelete(){
alert("功能正在开发中...");
}
function getPage(){
var page=$("#page").val();
var total='${page.totalPage}';
if(page>total){
alert("输入页大于总页数");
}
if(page.length==0){
alert("页码不能为空");
}
if(page!=""&& page<=total && page.length!=0){
var url="getList.do?currentPage="+page;
$("#skip").attr('href',url);
}
}
function batchDelete(){
//获取当前页
var currentPage='${page.currentPage}';
var cks=document.getElementsByName("check");
var str="";
var id=0;
 for(var i=0;i<cks.length;i++){
 var flag=cks[i].checked;
 if(flag==true){
   id++;
   value1=cks[i].value;
  value1=value1.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');  
    str=str+value1+",";
 }
 }
if(id==0){
alert("您还没有选择呢,请选择一条数据,删除请慎重,不要谢我^_^");
}else{
if(confirm("您确定删除这些数据吗?")){
$("#batchdelete").attr("href","batchdelete.do?allid="+str+"&currentPage="+currentPage);
}

}
}
//全选
 function ckAll(){
 var flag=document.getElementById("allChecks").checked;
 var cks=document.getElementsByName("check");
 for(var i=0;i<cks.length;i++){
 cks[i].checked=flag;
 }
 }
 //考勤确认
 function sure(){
 var userid='${sessionScope.username}';
$.ajax({
   
            url: "userSure.do?username="+userid,
            type: "GET",
            async:false,
            dataType:"text",
            contentType : false, 
            processData: false,
            scriptCharset: "utf-8",
            success: function (data) {
            if(data=="1"){
            alert("考勤确认成功！");
            window.location.reload();
            }else{
            alert("系统出现故障");
            }
            }
            
        });
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
            <li><a href="getUserList.do" class="hover"><em class="two"></em>个人考勤</a></li>
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
   
   <div id="shade"></div>
  <!-- 创建记录 -->
   <div id="createform">
   <form action="createUser.do" id="createdata" method="post">
   <span style="font-size: 19px">生 成 记 录</span><br/><br/>
   <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input type="text" class="int-text" name="username" id="username" /><br/>
   <br />
   <span>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</span><input type="text" class="int-text" name="date" id="date" onclick="WdatePicker({dateFmt:'yyyy/M'})"/><br/><br/>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="确定" class="green-int" onclick="checkcreateform()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="button" value="关闭" class="green-int" onclick="javascript:createend()" />
   </form>   
   </div>
    <!-- 增加记录 -->
   <div id="addform">
   <form action="add.do" id="adddata" method="post">
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" class="tableform">
   <tr ><th colspan="2" ><span style="font-size: 19px" >增 加 记 录</span></th><th></th></tr>
   <tr><td><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span></td><td><input type="text" class="int-text" name="username" id="username1" /></td></tr><br/>
   <tr><td><span>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</span></td><td> <input type="text" class="int-text" name="date" id="date1" onclick="WdatePicker({dateFmt:'yyyy/M/d'})"/></td></tr>
   <tr><td><span>上班时间:</span></td><td><input type="text" class="int-text" name="start-time" onclick="WdatePicker({dateFmt:'H:mm:ss'})"/></td></tr>
   <tr><td><span>下班时间:</span></td><td><input type="text" class="int-text" name="end-time" onclick="WdatePicker({dateFmt:'H:mm:ss'})"/></td></tr>
   <tr><td><span>周末加班:</span></td><td>是&nbsp;&nbsp;<input type="radio" class="int-text" name="week" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="week" value="0" checked="checked"/></td></tr>
   <tr><td><span>假日加班:</span></td><td>  是&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" value="0" checked="checked"/></td></tr>
   <tr><td><span>说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明:</span></td><td><input type="text" class="int-text" name="explain" id="add-explain"/></td></tr>
   <tr><td></td><td><input type="button" value="增加" class="green-int" onclick="javascript:checkaddform()"/>&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="javascript:addend()" /></td></tr>
   </table>
   </form>   
   </div>
   <!-- 更新数据 -->
      <div id="updateform">
   <form action="update.do" id="adddata" method="post">
   <input type="hidden" class="int-text" name="id" id="update-id" />
   <input type="hidden" class="int-text" name="currentPage" value=${page.currentPage} />
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" class="tableform">
   <tr ><th colspan="2" ><span style="font-size: 19px" >更 新 记 录</span></th><th></th></tr>
   <tr><td><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span></td><td><input type="text" class="int-text" name="username" id="update-name"/></td></tr><br/>
   <tr><td><span>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</span></td><td> <input type="text" class="int-text" name="date" id="update-date"/></td></tr>
   <tr><td><span>上班时间:</span></td><td><input type="text" class="int-text" name="starttime" id="update-start" value="1"/></td></tr>
   <tr><td><span>下班时间:</span></td><td><input type="text" class="int-text" name="endtime" id="update-end" value="0"/></td></tr>
   <tr><td><span>周末加班:</span></td><td>是&nbsp;&nbsp;<input type="radio" class="int-text" name="week" id="update-week-yes" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="week" id="update-week-no" value="0"/></td></tr>
   <tr><td><span>假日加班:</span></td><td>  是&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" id="update-holiday-yes" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" name="holi" id="update-holiday-no" value="0"/></td></tr>
   <tr><td><span>说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明:</span></td><td><input type="text" class="int-text" name="explain" id="update-explain"/></td></tr>
   <tr><td></td><td><input type="submit" value="更新" class="green-int" />&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="javascript:updateend(this)" /></td></tr>
   </table>
  
   </form>   
   </div>
   
    <div class="xt-bt">管理系统 > 考勤信息管理 > 个人考勤管理</div>
    <div class="xt-input">
         &nbsp;&nbsp;<input type="button" value="增加考勤" class="green-int" onclick="add()"/>
         &nbsp;&nbsp;<c:if test="${employee.role==2}" ><input type="button" value="一键生成考勤" class="green-int" onclick="createtable()"/></c:if>
     	 <input type="file" id="btn_file" style="display:none"/>
     	 &nbsp;<c:if test="${employee.role==2}" ><input type="button" value="导入报表" class="green-int" id="load"/></c:if>
        &nbsp;&nbsp;<input type="button" value="批量删除" class="green-int" onclick="batchDelete()"/>
        &nbsp;&nbsp; <input type="button" value="考勤确认" class="yellow-int" onclick="sure()"/>
        &nbsp;&nbsp;&nbsp;<span style="color:red;font-size:20px"><marquee behavior="alternate"><img src="img/message.png"/>&nbsp;&nbsp;当月考勤查看无误后请点击考勤确认按钮！</marquee></span>
       </form>
        
    </div>
    <div class="xt-table">
        <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%" id="tb">
            <tr>
            <th> <input type="checkbox" id="allChecks" onclick="ckAll()" /> 全选/全不选</th>
           
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
             <c:choose>
               <c:when test="${timetable.weekend=='是'}">
               <tr class="update-form" >
                <td style="background-color:yellow;"><input type="checkbox" name="check" class="check" value=${timetable.id}/></td>             
                <td style="background-color:yellow;">${timetable.id}</td>
                <td style="background-color:yellow;">${timetable.username}</td>
                <td style="background-color:yellow;">${timetable.date}</td>
                <td style="background-color:yellow;">${timetable.starttime}</td>
                <td style="background-color:yellow;">${timetable.endtime}</td> 
                <td style="background-color:yellow;">${timetable.weekend}</td>                     
                <td style="background-color:yellow;">${timetable.holiday}</td>
                <td style="background-color:yellow;">${timetable.explain}</td>
                <td style="background-color:yellow;"><a  href="#" class="yellow-xt" id="edit">编辑</a><a href="delete.do?id=${timetable.id}&currentPage=${page.currentPage}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>  
               </c:when>
               <c:otherwise>
                <tr class="update-form">
                <td><input type="checkbox" name="check" class="check" value=${timetable.id}/></td>             
                <td>${timetable.id}</td>
                <td>${timetable.username}</td>
                <td>${timetable.date}</td>
                <td>${timetable.starttime}</td>
                <td>${timetable.endtime}</td> 
                 <td>${timetable.weekend}</td>                         
                <td>${timetable.holiday}</td>
                <td>${timetable.explain}</td>
                <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a href="delete.do?id=${timetable.id}&currentPage=${page.currentPage}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr> 
               </c:otherwise>
               </c:choose>
            
            
            
            
           
            </c:forEach>
        </table>
    </div>
    <div class="xt-fenye">
        <div class="xt-fenye-left">当前第 ${page.currentPage} / ${page.totalPage}页,每页10条，共 ${page.totalCount}条记录</div>
        <div class="xt-fenye-right">
        <c:choose>
         <c:when test="${page.hasPrePage}">
         <a href="getUserList.do?currentPage=1">首页</a>  
	<a href="getUserList.do?currentPage=${page.currentPage -1 }">上一页</a>
         </c:when>
        <c:otherwise>
        <a >首页</a> <a>上一页</a>
        </c:otherwise>
        </c:choose>
                <c:choose>
         <c:when test="${page.hasNextPage}">
         <a href="getUserList.do?currentPage=${page.currentPage + 1 }">下一页</a>
	<a href="getUserList.do?currentPage=${page.totalPage }">尾页</a>
         </c:when>
        <c:otherwise>
        <a>下一页</a> <a>尾页</a>
        </c:otherwise>
        </c:choose>
            
             <input type="text" name="text" id="page"/>
           <a href="" class="xt-link" id="skip" onclick="getPage()">跳转</a>
        </div>
    </div>
        <div class="xt-input">
      <span style="color:green;font-size:15px">已确认人员：</span>   
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
</div>
</div>

</body>
</html>
