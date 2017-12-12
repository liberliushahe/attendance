<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName

()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 

"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="img/time.ico" >
<title>通讯管理</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="js/util.js"></script>
</head>

<script>
$(function() { 
$(".updateuser-form").each(function(){ 
    var tmp=$(this).children().eq(11); 
    var btn=tmp.children("#edit"); 
    btn.bind("click",function(){ 
        var id=btn.parent().parent().children("td").get(0).innerHTML; 
        var ip=btn.parent().parent().children("td").get(1).innerHTML; 
        var username=btn.parent().parent().children("td").get(2).innerHTML; 
        var password=btn.parent().parent().children("td").get(3).innerHTML;
        var userid=btn.parent().parent().children("td").get(4).innerHTML;
        var role=btn.parent().parent().children("td").get(5).innerHTML;
        var status=btn.parent().parent().children("td").get(6).innerHTML;
        var phone=btn.parent().parent().children("td").get(7).innerHTML;
        var email=btn.parent().parent().children("td").get(8).innerHTML;
        var isswitch=btn.parent().parent().children("td").get(9).innerHTML;
        var isflag=btn.parent().parent().children("td").get(10).innerHTML;
        $("#update-id").val(id);
        $("#update-ip").val(ip); 
        $("#update-username").val(username);
        $("#update-password").val(password); 
        $("#update-userid").val(userid); 
        if(role == "超级管理员"){
        $("#update-role").val(1);
        } else if(role == "管理员"){
        $("#update-role").val(2);
        }else{
        $("#update-role").val(0);
        }
        if(status=="有效"){
        $("#update-status-effect").attr('checked', true); 
        }else{
         $("#update-status-ineffect").attr('checked', true); 
        }
        $("#update-phone").val(phone); 
        $("#update-email").val(email); 
        if(isswitch=="是"){
        $("#update-isswitch-yes").attr('checked', true); 
        }else{
         $("#update-isswitch-no").attr('checked', true); 
        } 
        if(isflag=="是"){
        $("#update-isflag-yes").attr('checked', true); 
        }else{
         $("#update-isflag-no").attr('checked', true); 
        } 
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

};


function add(){
$("#shade").show();
$("#adduserform").slideDown(500);
   }
function update(){

$("#shade").show();
$("#updateuserform").slideDown(500);
   }
function addend(){
$("#adduserform").hide();
$("#shade").hide();
}
function updateend(){
$("#updateuserform").hide();
$("#shade").hide();
}
function loginout(){
if(confirm("确定退出系统")){
$("#loginout").attr("href","loginout.do");
 }
}
function checkaddform(){
var reg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;

   var username= $("#username").val();
   var ip=$("#ip").val();
   var pwd=$("#pwd").val();
   var tel=$("#tel").val();
   var name=$("#name").val();
   var ro=$("#ro").val();
   var eml=$("#eml").val();
   var zd=$('input:radio[name="zd"]:checked').val();
   var zt=$('input:radio[name="zt"]:checked').val();
   var js=$('input:radio[name="js"]:checked').val();
   
   if(username==""){
     alert("请输入用户名");
     return;
   }
   if(ip==""){
     alert("请输入ip地址");
     return;
   }
   if(pwd==""){
     alert("请输入密码");
     return;
   }
   if(tel==""){
     alert("请输入电话号码");
     return;
   }
   if(tel.length<11){
    alert("电话号码格式不正确");
     return;
   }
   if(name==""){
     alert("请输入登录名");
     return;
   }
   if(eml==""){
     alert("请输入邮箱");
     return;
   }
   if(!eml.match(reg)){
    alert("邮箱格式不正确");
     return;
   }
     $.post(
			'addUser.do',
			{ 
				"ip" : ip,
				"username" : username,
				"pwd" : pwd,
				"name" : name, 
				"ro" : ro,
				"zt" : zt,
				"tel" : tel,
				"eml" : eml,
				"js" : js,
				"zd" : zd	
			},
			function(info) {
				if (info == 1) { //直接跳转
				        alert("新增用户成功！");
					    window.location.href = "getUser.do";
				  } else { 
	                    alert("新增用户失败，请重新添加！");
	                    add();
	                    
				}
			});
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
var url="getUser.do?currentPage="+page;
$("#skip").attr('href',url);
}
}
function  convert(){
	    var input =$("#username").val();
	     str = ConvertPinyin(input);
	     $("#name").val(str);
	}

</script>

<body>
<div id="bg_div">
<div id="bg_div_loadimage"><img src="img/load.gif"/></div>
</div>
<!-- top -->
<div id="xt-top">
 <div class="xt-logo"><img src="img/logo.png" width="160px" height="50px"/></div>    <div 

class="xt-geren">
        <div class="xt-exit"><span class="xt-span">您好! 管理员：${sessionScope.username},

欢迎您<span class="xt-yanse"></span>登录管理中心</span>
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
            <li><a href="getUser.do" class="hover"><em class="three"></em>用户管理</a></li>
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
   
   <div id="shade"></div>

   
    <!-- 增加记录 -->
   <div id="adduserform">
   <form action="addUser.do" id="adddata" method="post">
   <div style="font-size: 19px" >增 加 用 户</div>
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" 

class="tableform">
   <!-- <tr ><td></td><td colspan="2" ><span style="font-size: 19px" >增 加 用 户

</span></td><td></td></tr>   -->
   <tr>
      <td><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span></td><td><input type="text" 

class="int-text" name="username" id="username" onkeydown="convert()" /></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>ip地址:</span></td><td><input 

type="text" class="int-text" name="ip" id="ip" value="0.0.0.0"/></td>
   </tr>
   <tr>
       <td><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span></td><td><input 

type="text" class="int-text" name="pwd" id="pwd" value="123456"/></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>电话号码:</span></td><td><input 

type="text" class="int-text" name="tel" id="tel"/></td>
   </tr>
   <tr>
      <td><span>登录名:</span></td><td><input type="text" class="int-text" name="name" 

id="name"/></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

箱:</span></td><td><input type="text" class="int-text" name="eml" id="eml"/></td>
   </tr>
   <tr>
      <td><span>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:</span></td><td><select 

style="width:173px;height:32px;font-size:16px" id="ro"><option value='0'>用户</option><option 

value='2'>管理员</option><option value='1' >超级管理员</option></select></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

态:</span></td><td>有效&nbsp;&nbsp;<input type="radio" class="int-text" name="zt" id="zt" 

value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无效&nbsp;&nbsp;<input type="radio" class="int-

text" name="zt" id="zt" value="0" checked="checked"/></td>
   </tr>
   <tr>
      <td><span>接收邮件:</span></td><td>是&nbsp;&nbsp;<input type="radio" class="int-text" 

name="js" id="js" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input 

type="radio" class="int-text" name="js" id="js" value="0" checked="checked"/></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>自动生成:</span></td><td>是

&nbsp;&nbsp;<input type="radio" class="int-text" name="zd" id="zd" 

value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" 

name="zd" id="zd" value="0" checked="checked"/></td>
   </tr>
   </table>
   <div><input type="button" value="增加" class="green-int" 

onclick="javascript:checkaddform()"/>&nbsp;&nbsp; <input type="button" value="关闭" 

class="green-int" onclick="javascript:addend()" /></div>
   </form>   
   </div>
   <!-- 更新数据 -->
   <div id="updateuserform">
   <form action="updateUser.do" id="adddata" method="post">
   <div style="font-size: 19px" >用户编辑</div>
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" 

class="tableform">
   <!-- <tr ><td></td><td colspan="2" ><span style="font-size: 19px" >增 加 用 户

</span></td><td></td></tr>   -->
   <tr><td><input type="hidden"  name="id" id="update-id" /></td></tr>
   <tr>
      <td><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span></td><td><input type="text" 

class="int-text" name="username" id="update-username" /></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>ip地址:</span></td><td><input 

type="text" class="int-text" name="ip" id="update-ip" /></td>
   </tr>
   <tr>
       <td><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span></td><td><input 

type="text" class="int-text" name="password" id="update-password" /></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>电话号码:</span></td><td><input 

type="text" class="int-text" name="phone" id="update-phone"/></td>
   </tr>
   <tr>
      <td><span>登录名:</span></td><td><input type="text" class="int-text" name="userid" 

id="update-userid"/></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

箱:</span></td><td><input type="text" class="int-text" name="email" id="update-email"/></td>
   </tr>
   <tr>
      <td><span>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:</span></td><td><select 

style="width:173px;height:32px;font-size:16px" id="update-role" name ="role"><option value='1' >超级管理员</option><option value='2'>管理员</option><option value='0'>用户</option></select></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

态:</span></td><td>有效&nbsp;&nbsp;<input type="radio" class="int-text" name="status" 

id="update-status-effect" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无效&nbsp;&nbsp;<input 

type="radio" class="int-text"  name="status" id="update-status-ineffect" value="0" 

/></td>
   </tr>
   <tr>
      <td><span>接收邮件:</span></td><td>是&nbsp;&nbsp;<input type="radio" class="int-text" 

name="isswitch" id="update-isswitch-yes" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否

&nbsp;&nbsp;<input type="radio" class="int-text" name="isswitch"  id="update-isswitch-no" 

value="0"/></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>自动生成:</span></td><td>是

&nbsp;&nbsp;<input type="radio" class="int-text" name="isflag" id="update-isflag-yes" 

value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;<input type="radio" class="int-text" 

name="isflag"  id="update-isflag-no" value="0"/></td>
   </tr>
   </table>
   <div><input type="submit" value="更新" class="green-int" 

onclick="javascript:checkupdateform()" />&nbsp;&nbsp; <input type="button" value="关闭" 

class="green-int" onclick="javascript:updateend()" /></div>
   </form>   
   </div>
    <div class="xt-bt">管理系统 > 考勤信息管理 > 用户管理</div>
    <div class="xt-input">
       <form action="query.do" method="post" id="querybyname">      
         &nbsp;<input type="button" value="增加用户" class="green-int" onclick="add()"/>
     	
        
       </form>
        
    </div>
    <div class="xt-table">
        <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%">
            <tr>
  
            <th>编号</th>           
            <th>ip地址</th>
            <th>姓名</th>
            <th>密码</th>
            <th>登录名</th>
            <th>角色</th>
            <th>状态</th>
            <th>电话</th>
            <th>邮箱</th>
            <th>接收邮件</th>
            <th>自动生成</th>
            <th>操作</th>
            </tr>
            
            <c:forEach items="${List}" var="employee">
            <tr class="updateuser-form"> 
                <td>${employee.id}</td>
                <td>${employee.ip}</td>
                <td>${employee.username}</td>
                <td>${employee.password}</td>
                <td>${employee.userid}</td>
                <!-- <td>${employee.role}</td>  -->
                <c:if test="${employee.role == 2}"><td>管理员</td></c:if>
                <c:if test="${employee.role == 1}"><td>超级管理员</td></c:if>
                <c:if test="${employee.role == 0}"><td>用户</td></c:if>
                <!-- <td>${employee.status}</td> -->
                <c:if test="${employee.status == 1}"><td>有效</td></c:if>
                <c:if test="${employee.status == 0}"><td>无效</td></c:if>
                <td>${employee.phone}</td>
                <td>${employee.email}</td>                             
                <!-- <td>${employee.isswitch}</td> -->
                <c:if test="${employee.isswitch == 1}"><td>是</td></c:if>
                <c:if test="${employee.isswitch == 0}"><td>否</td></c:if>
                <!-- <td>${employee.isflag}</td> -->
                <c:if test="${employee.isflag == 1}"><td>是</td></c:if>
                <c:if test="${employee.isflag == 0}"><td>否</td></c:if>
                <td> <a  href="#" class="yellow-xt" id="edit" display="disable">编辑</a>                                
             <a href="deleteuser.do?id=${employee.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="xt-fenye">
        <div class="xt-fenye-left">当前第 ${page.currentPage} / ${page.totalPage}页,每页10

条，共 ${page.totalCount}条记录</div>
        <div class="xt-fenye-right">
        <c:choose>
         <c:when test="${page.hasPrePage}">
         <a href="getUser.do?currentPage=1">首页</a>  
	<a href="getUser.do?currentPage=${page.currentPage -1 }">上一页</a>
         </c:when>
        <c:otherwise>
        <a >首页</a> <a>上一页</a>
        </c:otherwise>
        </c:choose>
        <c:choose>
         <c:when test="${page.hasNextPage}">
         <a href="getUser.do?currentPage=${page.currentPage + 1 }">下一页</a>
	<a href="getUser.do?currentPage=${page.totalPage }">尾页</a>
         </c:when>
        <c:otherwise>
        <a>下一页</a> <a>尾页</a>
        </c:otherwise>
        </c:choose>
            
             <input type="text" name="text" id="page"/>
           <a href="" class="xt-link" id="skip" onclick="getPage()">跳转</a>
        </div>
    </div>

</div>
</div>

</body>
</html>
