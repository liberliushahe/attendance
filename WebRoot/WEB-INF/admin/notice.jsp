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
<title>用户管理</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="js/util.js" charset=”utf-8″></script>
</head>

<script>
$(function() { 

$(".update-form").each(function(){ 
    var tmp=$(this).children().eq(5); 
    var btn=tmp.children("#edit"); 
    btn.bind("click",function(){ 
        var id=btn.parent().parent().children("td").get(0).innerHTML; 
        var title=btn.parent().parent().children("td").get(1).innerHTML; 
        var content=btn.parent().parent().children("td").get(2).innerHTML; 
        var author=btn.parent().parent().children("td").get(3).innerHTML;
        var date=btn.parent().parent().children("td").get(4).innerHTML;
        $("#update-id").val(id);
        $("#update-title").val(title); 
        $("#update-content").val(content);
        $("#update-author").val(author); 
        $("#update-date").val(date); 
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
function checkaddnoticeform(){
     var title= $("#add-title").val(); 
     var content= $("#add-content").val();
      var author= $("#add-author").val(); 
      var date= $("#add-date").val(); 
      if(title==""){
      alert("公告题目不能为空");
      }
      if(content==""){
      alert("公告内容不能为空");
      }
     if(author==""){
      alert("发布人不能为空");
      }
         if(date==""){
      alert("发布日期不能为空");
      }
   if(title!="" && content!=""&& author!="" && date!=""){
   $("#addnoticedata").submit(); 
   }
}

function add(){
$("#shade").show();
$("#addnoticeform").slideDown(500);
   }
function update(){

$("#shade").show();
$("#updatenoticeform").slideDown(500);
   }
function addend(){
$("#addnoticeform").hide();
$("#shade").hide();
}
function updateend(){
$("#updatenoticeform").hide();
$("#shade").hide();
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
            <li><a href="system.do"><em class="four"></em>系统设置</a></li>
        </ul>
        <ul>
            <li><a href="statis.do"><em class="five"></em>报表数据</a></li>
        </ul>
        <ul>
            <li><a href="getNotice.do" class="hover"><em class="five"></em>公告管理</a></li>
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
   <div id="addnoticeform">
   <form action="addNoticeList.do" id="addnoticedata" method="post">
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" class="tableform">
   <tr ><th colspan="2" ><span style="font-size: 19px" >增 加 公 告</span></th><th></th></tr>
   <tr><td><span>公告题目:</span></td><td><input type="text" class="int-text" name="add-title" id="add-title" style="width:250px"/></td></tr><br/>
    <tr><td><span>发 布 人</span></td><td><input type="text" class="int-text" name="add-author" id="add-author" style="width:250px"/></td></tr>
   <tr><td><span>发布时间</span></td><td><input type="text" class="int-text" name="add-date" id="add-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:250px"/></td></tr>
   <tr><td><span>公告内容</span></td><td>     <textarea name="add-content" id="add-content"  style="width:250px;height:160px;overflow:scroll;resize:none;" >
</textarea> </td></tr>
   <tr><td></td><td><input type="button" value="增加" class="green-int" onclick="javascript:checkaddnoticeform()"/>&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="javascript:addend()" /></td></tr>
   </table>
   </form>   
   </div>
   <!-- 更新数据 -->
      <div id="updatenoticeform">
   <form action="updateNoticeList.do" id="adddata" method="post">
   <input type="hidden" class="int-text" name="update-id" id="update-id" />
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" class="tableform">
    <tr ><th colspan="2" ><span style="font-size: 19px" >更 新 公 告</span></th><th></th></tr>
   <tr><td><span>公告题目:</span></td><td><input type="text" class="int-text" name="update-title" id="update-title" style="width:250px"/></td></tr><br/>
    <tr><td><span>发 布 人</span></td><td><input type="text" class="int-text" name="update-author" id="update-author" style="width:250px"/></td></tr>
   <tr><td><span>发布时间</span></td><td><input type="text" class="int-text" name="update-date" id="update-date"  style="width:250px"/></td></tr>
   <tr><td><span>公告内容</span></td><td>     <textarea id="update-content" name="update-content"  style="width:250px;height:160px;overflow:scroll;resize:none;" >
   </textarea> </td></tr>
   <tr><td></td><td><input type="submit" value="更新" class="green-int" />&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="javascript:updateend(this)" /></td></tr>
   </table>
  
   </form>   
   </div>
    <div class="xt-bt">管理系统 > 考勤信息管理 > 考勤管理</div>
    <div class="xt-input">
       <form action="query.do" method="post" id="querybyname">      
         &nbsp;<input type="button" value="增加公告" class="green-int" onclick="add()"/>
     	
        
       </form>
        
    </div>
    <div class="xt-table">
        <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%">
            <tr>
  
            <th>编号</th>           
            <th>公告题目</th>
            <th>公告内容</th>
            <th>发布人</th>
            <th>公告发布日期</th>
            <th>操作</th>
            </tr>
            <c:forEach items="${List}" var="notice">
            <tr class="update-form">
                <td>${notice.id}</td>
                <td>${notice.title}</td>
                <td>${notice.content}</td>
                <td>${notice.author}</td>
                <td>${notice.publishdate}</td>
                <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a href="deleteNotice.do?id=${notice.id}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
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
