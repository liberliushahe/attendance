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
<title>快捷查询</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/date/WdatePicker.js"></script>
<script type="text/javascript" src="js/util.js" charset="utf-8"></script>
</head>
<script>
function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
     var content=document.getElementById("query-username");
     //获取span宽度
     var spanwidth=$("#span-name")[0].offsetWidth;

     var width=content.offsetWidth;//输入框的宽度
     var left=content["offsetLeft"];//左边框的距离
     var top=content["offsetTop"]+content.offsetHeight; //顶部的高度
     var pop=document.getElementById("pop");//获取数据显示div
    // pop.style.left=left+"px";   
	 //pop.style.border="black 1px solid";

     pop.style.top=top+"px";
     pop.style.width=width+"px";
     //获取内部表id
     document.getElementById("inner-table").style.width=width+"px";
     getDepart();
     clearSearch();
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
};
$(function(){
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
					<li><a href="quickQuery.do" class="hover"><em class="two"></em>快捷查询</a>
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
		<li><a href="userAutoService.do"><em class="two"></em>自助服务</a></li>
		</ul>
			</div>
		</div>
		<!-- right -->
		<div id="xt-right">
			<div id="shade"></div>
			<div class="xt-bt">管理系统 > 考勤信息管理 > 快捷查询</div>
			<div class="xt-input">
				<div style="float:left">
					<span id="span-name">----姓名----</span><input type="text" class="int-text"
						id="query-username" name="name" onkeyup="getLikeName()"
						onfocus="showSearch()" onblur="hideSearch()"
						onkeydown="clearSearch()" />
						<div id="pop" style="margin-left:75px;z-index:1000;position:absolute;font-size:14px;background-color:white;">
					<table id="inner-table" cellpadding='0' cellspacing='0' style="border:1px solid #eee;border-top:0px">
						<tbody id="likeName" >
		               
						</tbody>
					</table>
					</div>
				</div>
				&nbsp;&nbsp;<span>----部门----</span> <select class="int-text" id="all-depart" name="department-key">
					<option value="0">------------请选择------------</option>
				</select> &nbsp;&nbsp; <span>----关键字----</span><input type="text"
					class="int-text" id="query-key" name="key" /> &nbsp;&nbsp; <input
					type="button" id="start-query" value="开始查询" class="green-int"
					onclick="queryLinkMan()" />&nbsp;&nbsp;<span>共有联系人：${count }人</span>
				</form>
			</div>
			<div class="xt-table" >
				<table border="1" cellpadding="0" cellspacing="0" bgcolor="#00CCFF"
					width="100%" id="tb" style="border:1px solid black">
					<tr>
						<th>姓名</th>
						<th>电话</th>
						<th>部门</th>
						<th>qq</th>
						<th>事项</th>
						<th>地址</th>
						<th>邮件</th>
						<th>职位</th>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script>
//点击数据显示到输入框
function getSearchKey(value){
$("#query-username").val(value);
}	
//清空之前的数据
function clearSearch(){
var tbody_content=document.getElementById("likeName");
var size=tbody_content.childNodes.length;
for(var i=size-1;i>=0;i--){
tbody_content.removeChild(tbody_content.childNodes[i]);
}
var popdiv=document.getElementById("pop");
popdiv.style.border="none";



}
//失去焦点
//由于失去焦点在click函数执行之前 导致click函数不起作用 可以通过延迟的方法来解决此问题
function hideSearch(){

setTimeout('clearSearch()',300);
}

//获得焦点
function showSearch(){
getLikeName();
}
//获取部门
function getDepart(){
$.ajax({
    url:"getDepart.do",
     type: "POST",
     dataType:"JSON",
     success:function(data){
     for(var i=0;i<data.length;i++){
      $("#all-depart").append(' <option value="'+data[i].departid+'" >'+data[i].departname+'</option>');
     }      
     }

});
}
function getLikeName(){

var name=$("#query-username").val();
if(name===""){
name=null;
}
$.ajax({
    url:"getNameLike.do?name="+name,
     type: "POST",
     dataType:"JSON",
     success:function(data){
   
     for(var i=0;i<data.length;i++){
      $("#likeName").append('<tr><td onclick="getSearchKey(this.innerHTML)">'+data[i]+'</td></tr>');
     }      
     }

});
}
function queryLinkMan(){
$(".linkman").remove();
$("#start-query").val("正在查询");
$("#query-username").attr("disable",false);
$("#all-depart").attr("disable",false);
$("#query-key").attr("disable",false);
var name=$("#query-username").val();
var depart=$("#all-depart option:selected").val();
var key=$("#query-key").val();
$.ajax({
    url:"queryLinkMan.do?name="+name+"&department-key="+depart+"&key="+key,
     type: "POST",
     dataType:"JSON",
     success:function(data){   
     for(var i=0;i<data.length;i++){
      var qq;
      qq=data[i].qq; 
      if(qq==null){
      qq="";
      }
      matter=data[i].matter;
            if(matter==null){
      matter="";
      }
      address=data[i].address;
            if(address==null){
      address="";
      }
      email=data[i].email;
            if(email==null){
      email="";
      }
      job=data[i].job;
            if(job==null){
      job="";
      }
    $("#tb").append(' <tr class="linkman" style="border:1px solid"> '+            
            '<td>'+data[i].name+'</td>'+
            '<td>'+data[i].phone+'</td>'+
            '<td>'+data[i].departname+'</td>'+
            '<td><a title="点击进入聊天窗口" href="tencent://message/?uin='+qq+'&Site=咨询&Menu=yes">'+qq+'</a></td>'+
            '<td>'+matter+'</td>'+
            '<td>'+address+'</td>'+
            '<td><a href="mailto:'+email+'" title="点击直接发送邮箱">'+email+'</a></td>'+
            '<td>'+job+'</td>'+
            '</tr>');
    
     } 

     $("#start-query").val("开始查询");
$("#query-username").attr("disable",true);
$("#all-depart").attr("disable",true);
$("#query-key").attr("disable",true);
     

     }

});

}

</script>
</body>
</html>
