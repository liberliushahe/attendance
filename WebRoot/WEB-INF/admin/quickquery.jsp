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
<title>快捷查询</title>
<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.min.js" ></script>
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="js/util.js"  charset="utf-8"></script>
</head>
<script>
function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){
    getDepart();
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
};
$(function(){
$(".updatelinkman-form").each(function(){ 
    var tmp=$(this).children().eq(10); 
    var btn=tmp.children("#edit"); 
    btn.bind("click",function(){ 
        var id=btn.parent().parent().children("td").get(1).innerHTML; 
        var name=btn.parent().parent().children("td").get(2).innerHTML; 
        var phone=btn.parent().parent().children("td").get(3).innerHTML; 
        var depart=btn.parent().parent().children("td").get(4).innerHTML;
        var qq=btn.parent().parent().children("td").get(5).innerHTML;
        var matter=btn.parent().parent().children("td").get(6).innerHTML;
        var address=btn.parent().parent().children("td").get(7).innerHTML;
        var email=btn.parent().parent().children("td").get(8).innerHTML;
        var job=btn.parent().parent().children("td").get(9).innerHTML;
        $("#update-id").val(id);
        $("#update-name").val(name); 
        $("#update-phone").val(phone);
        updateDepart(depart); 
          
        //$("#update-depart").val(depart); 
        $("#update-qq").val(qq); 
        $("#update-matter").val(matter); 
        $("#update-address").val(address); 
        $("#update-email").val(email); 
        $("#update-job").val(job);        
        update();
        
        }); 
    });

 $("#addlink").validate({
			rules : {
				name : "required",
				phone : "required",
				depart:"required",
				
				
			},
			messages : {
				
				name : "姓名不能为空",
				phone : "手机号不能为空",
				depart:"部门不能为空",
			}
		});

});

function addend(){
$("#shade").hide();
$("#addlinkman").hide();

}
function updatelinkend(){
$("#shade").hide();
$("#updatelinkman").hide();
window.location.reload();
}

function add(){
$("#shade").show();
$("#addlinkman").slideDown(500);
}
function update(){
$("#shade").show();
$("#updatelinkman").slideDown(500);
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
var url="quickAdminQuery.do?currentPage="+page;
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
$("#batchdelete").attr("href","batchlinkmandelete.do?allid="+str+"&currentPage="+currentPage);
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
            <li><a href="getNotice.do"><em class="five"></em>公告管理</a></li>
        </ul>
         <ul>
            <li><a href="getPhoto.do"><em class="five"></em>相册管理</a></li>
        </ul>
        <ul>
            <li><a href="quickAdminQuery.do" class="hover"><em class="five"></em>快捷查询</a></li>
        </ul>
    </div>
</div>
<!-- right -->
<div id="xt-right">
   
   <div id="shade"></div>

    <!-- 增加记录 -->
   <div id="addlinkman">
   <form action="addLinkMan.do" id="id" method="post">
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" class="tableform">
   <tr ><th colspan="2" ><span style="font-size: 19px" >增 加 记 录</span></th><th></th></tr>
   <tr><td><span>姓&nbsp;名:</span></td><td><input type="text" class="int-text" name="name" id="username" /></td></tr><br/>
   <tr><td><span>电&nbsp;话:</span></td><td> <input type="text" class="int-text" name="phone" id="phone"/></td></tr>
   <tr><td><span>部&nbsp;门:</span></td><td><select class="int-text" id="all-depart" name="departid">
					<option value="0">---------请选择---------</option>
				</select></td></tr>
   <tr><td><span>q&nbsp;q:</span></td><td><input type="text" class="int-text" name="qq" id="qq"/></td></tr>
   <tr><td><span>事&nbsp;项:</span></td><td><input type="text" class="int-text" name="matter" id="matter"/></td></tr>
   <tr><td><span>地&nbsp;址:</span></td><td>  <input type="text" class="int-text" name="address" id="address"/></td></tr>
   <tr><td><span>邮&nbsp;件:</span></td><td><input type="text" class="int-text" name="email" id="email" /></td></tr>
   <tr><td><span>职&nbsp;位:</span></td><td><input type="text" class="int-text" name="job" id="job"/></td></tr>
   
   <tr><td></td><td><input type="submit" value="增加" class="green-int"/>&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="javascript:addend()" /></td></tr>
   </table>
   </form>   
   </div>
   <!-- 更新数据 -->
      <div id="updatelinkman">
   <form action="updateLinkMan.do" id="adddata" method="post">
   <input type="hidden" class="int-text" name="id" id="update-id" />
   <input type="hidden" class="int-text" name="currentPage" value=${page.currentPage} />
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 17px;" class="tableform">
   <tr><th colspan="2" ><span style="font-size: 19px" >更 新 记 录</span></th><th></th></tr>
  <tr><td><span>姓&nbsp;名:</span></td><td><input type="text" class="int-text" name="name" id="update-name" /></td></tr>
   <tr><td><span>电&nbsp;话:</span></td><td> <input type="text" class="int-text" name="phone" id="update-phone"/></td></tr>
   <tr><td><span>部&nbsp;门:</span></td><td><select class="int-text" id="update-depart" name="departid">
					<option value="0">----------请选择----------</option>
				</select></td></tr>
   <tr><td><span>q&nbsp;q:</span></td><td><input type="text" class="int-text" name="qq" id="update-qq"/></td></tr>
   <tr><td><span>事&nbsp;项:</span></td><td><input type="text" class="int-text" name="matter" id="update-matter"/></td></tr>
   <tr><td><span>地&nbsp;址:</span></td><td>  <input type="text" class="int-text" name="address" id="update-address"/></td></tr>
   <tr><td><span>邮&nbsp;件:</span></td><td><input type="text" class="int-text" name="email" id="update-email" /></td></tr>
   <tr><td><span>职&nbsp;位:</span></td><td><input type="text" class="int-text" name="job" id="update-job"/></td></tr>
   <tr><td></td><td><input type="submit" value="更新" class="green-int" />&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="updatelinkend()" /></td></tr>
   </table>
  
   </form>   
   </div>
   
    <div class="xt-bt">管理系统 > 考勤信息管理 > 快捷查询</div>
    <div class="xt-input">
         &nbsp;&nbsp;<input type="button" value="增加联系人" class="green-int" onclick="add()"/>
        &nbsp;<a id="batchdelete" class="button-a" href="" onclick="batchDelete()">批量删除</a>
      
       </form>
        
    </div>
    <div class="xt-table">
        <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%" id="tb">
            <tr>
            <th> <input type="checkbox" id="allChecks" onclick="ckAll()" /> 全选/全不选</th>
           
            <th>编号</th>           
            <th>姓名</th>
            <th>电话</th>
            <th>部门</th>
            <th>qq</th>
            <th>事项</th>
            <th>地址</th>
            <th>邮件</th>
            <th>职位</th>
            <th>操作</th>
            </tr>
          
            <c:forEach items="${List}" var="linkman">
           
            <tr class="updatelinkman-form">
                <td><input type="checkbox" name="check" class="check" value=${linkman.id}/></td>             
                <td>${linkman.id}</td>
                <td>${linkman.name}</td>
                <td>${linkman.phone}</td>
                <td>${linkman.depart.departname}</td>
                <td>${linkman.qq}</td>
                <td>${linkman.matter}</td>
                <td>${linkman.address}</td>
                <td>${linkman.email}</td>
                <td>${linkman.job}</td>
                <td><a  href="#" class="yellow-xt" id="edit">编辑</a><a href="deleteLinkMan.do?id=${linkman.id}&currentPage=${page.currentPage}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <div class="xt-fenye">
        <div class="xt-fenye-left">当前第 ${page.currentPage} / ${page.totalPage}页,每页10条，共 ${page.totalCount}条记录</div>
        <div class="xt-fenye-right">
        <c:choose>
         <c:when test="${page.hasPrePage}">
         <a href="quickAdminQuery.do?currentPage=1">首页</a>  
	<a href="quickAdminQuery.do?currentPage=${page.currentPage -1 }">上一页</a>
         </c:when>
        <c:otherwise>
        <a >首页</a> <a>上一页</a>
        </c:otherwise>
        </c:choose>
                <c:choose>
         <c:when test="${page.hasNextPage}">
         <a href="quickAdminQuery.do?currentPage=${page.currentPage + 1 }">下一页</a>
	<a href="quickAdminQuery.do?currentPage=${page.totalPage }">尾页</a>
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
<script>
var flag=true;
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
function updateDepart(name){

$.ajax({
    url:"getDepart.do",
     type: "POST",
     dataType:"JSON",
     success:function(data){
     for(var i=0;i<data.length;i++){
     if(data[i].departname.indexOf(name)!=-1){
             $("#update-depart").append(' <option value="'+data[i].departid+'" selected="selected">'+data[i].departname+'</option>');
       
       }else{
             $("#update-depart").append(' <option value="'+data[i].departid+'" >'+data[i].departname+'</option>');
       
       
       }
           } 

     }

});

}
</script>
</body>
</html>
