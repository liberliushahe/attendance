<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="img/time.ico">
<title>用户日报</title>
   <script type="text/javascript" src="js/jquery-1.12.1.min.js"></script> 
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/date/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/util.js" charset="utf-8"></script>
  <style>
.xt-table tr td:hover{
 background-color:none;
}
.td_info_title{
color:red;

}
.xt-table tr:hover{
  background-color:white;
  }
input.error { border: 1px solid red; }
label.error {
background:url(“./demo/images/unchecked.gif”) no-repeat 0px 0px;
padding-left: 16px;
padding-bottom: 2px;
font-weight: bold;
color: #EA5200;
}
  </style>
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
		<div id="bg_div_loadimage">
			<img src="img/load.gif" />
		</div>
	</div>
	<!-- top -->
	<div id="xt-top">
		<div class="xt-logo">
			<img src="img/logo.png" width="160px" height="50px" />
		</div>
		<div class="xt-geren">
			<div class="xt-exit">
				<span class="xt-span">您好! ${sessionScope.username},欢迎您<span
					class="xt-yanse"></span>登录管理中心</span> <a href="#" id="loginout"
					class="exit" onclick="loginout()">退出</a>
			</div>
		</div>
	</div>
	<!-- left -->
	<div class="xt-center">
		<div id="xt-left">
			<div id="bg_music"></div>
			<div class="xt-logo"></div>
			<div class="xt-menu">
				<ul>
					<li><a href="usermain.do"><em class="one"></em>网站首页</a></li>
				</ul>
				<ul>
					<li><a href="getUserList.do"><em class="two"></em>个人考勤</a></li>
				</ul>
				<ul>
				<li><a href="quickQuery.do"><em class="two"></em>快捷查询</a>
				</li>
				</ul>
				<ul>
					<li><a href="getUserPhoto.do"><em class="two"></em>记忆相册</a></li>
				</ul>
				<ul>
					<li><a href="getMessage.do"><em class="two"></em>内部通讯</a></li>
				</ul>
				<ul>
					<li><a href="getTimeLog.do"><em class="two"></em>更新日志</a></li>
				</ul>
				<ul>
				<li><a href="userAutoService.do" class="hover"><em class="two"></em>自助服务</a></li>
				</ul>
			</div>
		</div>
		<!-- right -->
		<div id="xt-right">
			<div id="shade"></div>
			<div class="xt-bt">管理系统 > 考勤信息管理 > 自助服务</div>
			<span style="margin-left:15px;font-size:15px;color:red;">注意：红色字体为必填项，其他都是默认值，不用修改</span>
			<div class="xt-table" >			
				<form autocomplete="off" id="report1" action="addUserReport.do" method="post" >
				<table border="0.1" cellpadding="0" cellspacing="0" bgcolor="#00CCFF"
					width="100%" id="tb">
				<thead>

				</thead>	
				
				<tbody>

				<tr>
				<td colspan="6">用户信息</td>
				</tr>
	 <tr>
    <td><span class="td_info_title">用户名：</span></td>
    <td><input type="text" id="username" name="username" class="int-text" placeholder="填写日报用户名"/></td>
    <td><span class="td_info_title">登录密码：</span></td>
    <td><input type="password" id="password" name="password" class="int-text" value="" placeholder="填写日报密码（加密）"/></td>
    <td><span class="td_info_title">个人邮箱：</span></td>
    <td><input type="text" name="email" class="int-text" placeholder="填写有效邮箱"/></td>
    </tr>  
    <tr>
				<td colspan="6">日报信息</td>
				</tr>
            <tr>			
				 <tr>
    <td>开始时间：</td>
    <td><input type="text" name="btime" class="int-text" value="540" placeholder="9:00"/></td>
    <td>工作时间：</td>
    <td><input type="text" name="wtime" class="int-text" value="8"/><input type="hidden" name="wrepid" class="int-text" value="8"/></td>
    <td>任务类型：</td>
    <td><input type="text" name="wtype" class="int-text" value="15"/></td>
    </tr>  
            <tr>
    <td>忙闲程度：</td>
    <td><input type="text" name="wbusy" class="int-text" value="2"/></td>
    <td>工作状态：</td>
    <td><input type="text" name="wstat" class="int-text" value="1"/></td>
    <td>出勤地点：</td>
    <td><input type="text" name="wplace" class="int-text" value="gc"/></td>
    </tr>
        <tr>
    <td><span class="td_info_title">出勤记事(必填)：</span></td>
    <td><input type="text" name="wdesc" class="int-text" placeholder="填写文本"/></td>
    <td><span class="td_info_title">项目任务：</span></td>
    <td><input type="text" name="wtask" class="int-text" placeholder="填写项目编号"/></td>
        <td><span class="td_info_title">工作日志(必填)：</span></td></td>
    <td><input type="text" name="wlog" class="int-text" placeholder="填写文本"/></td>
    </tr>
        <tr>
    <td>成果总结：</td>
    <td><input type="text" name="wsum" class="int-text"/></td>
    <td>进        度：</td>
    <td><input type="text" name="wprog" class="int-text" value="0"/></td>
        <td>成        果：</td>
    <td><input type="text" name="wamount" class="int-text"/></td>
    </tr>
        <tr>
    <td>单         位：</td>
    <td><input type="text" name="wunit" class="int-text" value="0"/></td>
    <td>工作计划：</td>
    <td><input type="text" name="wplan" class="int-text" value=""/></td>
        <td>协作要求：</td>
    <td><input type="text" name="wquestion" class="int-text"/></td>
    </tr>
    <tr>
    <td colspan="6"><input type="reset" value="重置" class="green-int" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交" class="green-int" /></td>

    </tr>
		
				</tbody>
				</table>
					</form>	
					
<!-- 数据编辑 -->		
	<form autocomplete="off" id="report1" action="updateUserReport.do" method="post" >		
	 <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%" id="tb">
            <tr>   
            <th></th>        
            <th>用户姓名</th>
            <th>用户邮箱</th>
            <th>出勤记事</th>
            <th>工作日志</th>
            <th>项目任务</th>           
            <th colspan="2">操作</th>
            </tr>                 
            <tr class="update-form">  
            <td><input type="hidden" name="id" class="int-text" value="${requestScope.userreport.id}"/></td>                         
                <td><input type="text" name="username" class="int-text" value="${requestScope.userreport.username}" readonly="readonly"/></td>
                <td><input type="text" name="email" class="int-text" value="${requestScope.userreport.email}"/></td>
                <td><input type="text" name="wdesc" class="int-text" value="${requestScope.userreport.wdesc}"/></td>
                <td><input type="text" name="wlog" class="int-text" value="${requestScope.userreport.wlog}"/></td>                
                <td><input type="text" name="wtask" class="int-text" value="${requestScope.userreport.wtask}"/></td>                             
                <td><input  type="submit" class="yellow-xt" style="background-color:gray;border-radius:6px;border:none;width:40px;height:22px;" value="更新"/></td>
                 <td><a href="deleteUserReport.do?id=${requestScope.userreport.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
                 </tr>
         
            
        </table>	
        </form>	
   
       <span style="margin-left:46%;font-size:15px;text-align:center">用户日报列表</span> 
     
        
        <!-- 用户所写日报列表 -->		
         <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%" id="tb">
            <tr>   
            <th>用户姓名</th>        
            <th>日报时间</th>
            <th>工作时间</th>
            <th>出勤记事</th>
            <th>工作日志</th>
            <th>项目任务</th>           
           
            </tr>
         
           
             <c:forEach items="${List}" var="reportdetail">
           
            <tr class="update-form">
            
              
                <td>${reportdetail.username}</td>
                <td>${reportdetail.btime}</td>
                <td>${reportdetail.wtime}</td>
                <td>${reportdetail.wdesc}</td>
                
                <td>${reportdetail.wlog}</td>                             
                <td>${reportdetail.wtask}</td>              
            </tr>
            </c:forEach>
         
            
        </table>	
			</div>
		</div>
	</div>	
     <script>
         $(function(){
          $.validator.setDefaults({
			submitHandler : function(form) {
			form.submit();
			}
			});
        	$("#report1").validate({
			rules : {
				username : "required",
				password : "required",
				wdesc:"required",
				email: {
                    required: true,
                    email: true
                       },
                wtask:"required",
                wlog:"required",
			},
			messages : {
				password : {
					required : "密码不能为空",
					
				},
				username : "用户名不能为空",
				wdesc:"出勤记事不能为空",
				email:"请输入正确的邮箱",
				wtask:"项目任务不能为空",
				wlog:"工作日志不能为空",
			}
		});
         });
         
     </script>
</body>
</html>
