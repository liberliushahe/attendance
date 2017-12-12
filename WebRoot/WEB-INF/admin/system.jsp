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
<title>系统设置</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="js/util.js" charset=”utf-8″></script>
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
function uploadImage(){
$('#emailproperty').hide();
$('#system-imageupload').show();
}
function  batchDelete(){
alert("功能开发中.....");
}
function emailProperty(){
$('#system-imageupload').hide();
$('#emailproperty').show();
}

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
            <li><a href="system.do" class="hover"><em class="four"></em>系统设置</a></li>
        </ul>
        <ul>
            <li><a href="statis.do"><em class="five"></em>报表数据</a></li>
        </ul>
        <ul>
            <li><a href="getNotice.do" ><em class="five"></em>公告管理</a></li>
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
    <div class="xt-bt">管理系统  > 考勤信息管理 > 系统设置</div>
    <div class="xt-input">
       <form action="query.do" method="post" id="querybyname">      
      &nbsp;<input type="button" value="上传图片" class="green-int" onclick="uploadImage()"/>
      &nbsp;<input type="button" value="批量删除" class="green-int" onclick="batchDelete()"/>
      &nbsp;<input type="button" value="邮箱设置" class="green-int" onclick="emailProperty()"/> 
      </form>     
    </div>
   <div id="system-container">
    <div class="xt-table" id="system-imageupload">
        <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%">
            <tr>
  
            <th>编号</th>           
            <th>图片</th>
            <th>描述</th>
           <th>操作</th>
            </tr>
            <tr>
  
            <td>1</td>           
            <td><img src="img/search.png"/></td>
            <td>测试案例</td>
            <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a 

href="deleteuser.do?id=${employee.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            <tr>
  
            <td>2</td>           
            <td><img src="img/success.png" width="60px" height="60px"/></td>
            <td>测试案例</td>
            <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a 

href="deleteuser.do?id=${employee.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            <c:forEach items="${List}" var="employee">
            <tr class="updateuser-form"> 
                <td>${employee.id}</td>
                <td>${employee.ip}</td>
                <td>${employee.username}</td>
                <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a 

href="deleteuser.do?id=${employee.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="xt-table" id="emailproperty">
       <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%">
            <tr>
  
            <th>编号</th>           
            <th>邮件服务器地址</th>
            <th>开启SSL</th>
            <th>发送邮箱</th>
            <th>密码</th>
            <th>操作</th>
            </tr>
            <tr>
            <td>1</td>           
            <td>smtp.qq.com</td>
            <td>是</td>
            <td>1540077031@qq.com</td>
            <td>EDRSTSijIYGTY</td>
            <td><a  href="#" class="yellow-xt" id="edit">编辑</a></td>
            </tr>
            
            <c:forEach items="${List}" var="employee">
            <tr class="updateuser-form"> 
                <td>${employee.id}</td>
                <td>${employee.ip}</td>
                <td>${employee.username}</td>
                <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a 

href="deleteuser.do?id=${employee.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    
    </div>
   
   </div>


</div>
</div>

</body>
</html>
