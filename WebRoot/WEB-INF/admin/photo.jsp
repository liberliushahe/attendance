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
<title>相册管理</title>

<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" type="text/css" href="css/layui.css" />
  <link rel="stylesheet" type="text/css" href="css/layer.css" />
  <link rel="stylesheet" type="text/css" href="css/lightbox.css" />
  <script type="text/javascript" src="js/jquery.min.js" ></script>
<script type="text/javascript" src="js/lightbox.js" ></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/layer.js"></script>
  <script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<script>
$(function(){
$(".updatephoto-form").each(function(){ 
    var tmp=$(this).children().eq(7); 
    var btn=tmp.children("#editphoto"); 
    btn.bind("click",function(){ 
        var id=btn.parent().parent().children("td").get(0).innerHTML; 
        var title=btn.parent().parent().children("td").get(1).innerHTML; 
        var wisdom=btn.parent().parent().children("td").get(2).innerHTML; 
        var content=btn.parent().parent().children("td").get(3).innerHTML; 
        var date=btn.parent().parent().children("td").get(4).innerHTML; 
        var author=btn.parent().parent().children("td").get(5).innerHTML; 
        $("#addphoto-id").val(id);
        $("#update-title").val(title); 
        $("#update-content").val(content);
        $("#update-wisdom").val(wisdom);
        $("#update-date").val(date);
        $("#update-author").val(author);
        updateUserPhoto();
        
        }); 
    }); 
   $("#add-photo").validate({
			rules : {
				title : "required",
				content : "required",
				
			},
			messages : {
				title : "照片描述不能为空",								
				content : "照片内容不能为空",
				
			}
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
function adduserPhoto(){

$("#shade").show();
$("#addPhotoForm").slideDown(500);
}

function addPhotoend(){
$("#addPhotoForm").hide();
$("#shade").hide();
}
function  updateUserPhoto(){
$("#shade").show();
$("#updatephotoform").slideDown(500);
}

function updatePhotoend(){
$("#updatephotoform").hide();
$("#shade").hide();
}

function checkupdatephotoform(){
alert(1)
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
            <li><a href="getList.do"><em class="two"></em>考勤管理</a></li>
        </ul>
        <ul>
            <li><a href="getUser.do"><em class="two"></em>用户管理</a></li>
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
            <li><a href="getPhoto.do" class="hover"><em class="five"></em>相册管理</a></li>
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
   <div id="addPhotoForm">
   <form action="addPhoto.do" id="add-photo" method="post">
   <input type="hidden" class="int-text" name="photo-name" id="photo-name" />
   <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" class="tableform">
   <tr ><th colspan="2" ><span style="font-size: 19px" >增 加 照 片</span></th><th></th></tr>
   <tr><td><div><span>照片信息:</span></td><td><input type="text" class="int-text" name="title" id="title-add" /></div></td></tr><br/>
   <tr><td><span>照片内容:</span></td><td> <input type="text" class="int-text" name="content" id="content" /></td></tr>
   <tr><td></td><td><input type="submit" value="增加" id="upload-photo-add" class="green-int"/>&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="javascript:addPhotoend()" /></td></tr>
   </table>
   </form>   
   </div>
   <!-- 更新数据 -->
      <div id="updatephotoform">
   <form action="updatePhoto.do" id="updatephoto" method="post">
   <input type="hidden" class="int-text" name="addphoto-id" id="addphoto-id" />
   <input type="hidden" class="int-text" name="currentPage" value=${page.currentPage} />
    <table style="border-collapse:separate;margin:auto; border-spacing:0px 13px;" class="tableform">
   <tr ><th colspan="2" ><span style="font-size: 19px" >更 新 照 片</span></th><th></th></tr>
   <tr><td><span>照片描述:</span></td><td><input type="text" class="int-text" name="update-title" id="update-title" /></td></tr><br/>
   <tr><td><span>个人名言:</span></td><td> <input type="text" class="int-text" name="update-wisdom" id="update-wisdom" /></td></tr>
      <tr><td><span>个人简介:</span></td><td > <input type="text" class="int-text" name="update-content" id="update-content" /></td></tr>
      <tr><td><span>发布日期:</span></td><td> <input type="text" class="int-text" name="update-date" id="update-date" /></td></tr>
      <tr><td><span>发布人:</span></td><td> <input type="text" class="int-text" name="update-author" id="update-author" /></td></tr>
   
   
   
   <tr><td><span></span></td></td></tr>
   
   <tr><td></td><td><input type="submit" value="更新" class="green-int"/>&nbsp;&nbsp; <input type="button" value="关闭" class="green-int" onclick="javascript:updatePhotoend()" /></td></tr>
   </table>
   </div>
   
    <div class="xt-bt">管理系统 > 考勤信息管理 > 相册管理</div>  
   
<div class="xt-input">
        
         <button type="button" class="layui-btn layui-btn-normal" id="upload-photo" style="width:110px">选择文件</button>
   </div>     
      
       <div class="xt-table">
        <table cellpadding="0" cellspacing="0" border="0" bgcolor="#dcdcdc" width="100%" style="table-layout: fixed;">
            <tr>
            <th>编号</th>           
            <th>名字</th>
            <th>名言</th>
            <th>简介</th>
            <th>发布日期</th>
            <th>发布人</th>
            <th>照片</th>        
            <th>操作</th>
            </tr>
            <c:forEach items="${List}" var="photo">
            <tr class="updatephoto-form">
                <td>${photo.id}</td>
                <td>${photo.title}</td>
                <td>${photo.wisdom}</td>               
<td style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${photo.content}</td>
                <td>${photo.publishdate}</td>
                <td>${photo.author}</td>
               <td> <a href="userimage/${photo.path}" data-lightbox="roadtrip"><img src="images/${photo.path}" width="62px" height="53px" title="点击预览照片"/></a></td>
                
                <td><a  href="#" class="yellow-xt" id="editphoto">编辑</a><a href="deletePhoto.do?id=${photo.id}&currentPage=${page.currentPage}" class="blue-xt" onclick="return confirm('确定删除？')">删除</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
     <div class="xt-fenye">
        <div class="xt-fenye-left">当前第 ${page.currentPage} / ${page.totalPage}页,每页5条，共 ${page.totalCount}条记录</div>
        <div class="xt-fenye-right">
        <c:choose>
         <c:when test="${page.hasPrePage}">
         <a href="getPhoto.do?currentPage=1">首页</a>  
	<a href="getPhoto.do?currentPage=${page.currentPage -1 }">上一页</a>
         </c:when>
        <c:otherwise>
        <a >首页</a> <a>上一页</a>
        </c:otherwise>
        </c:choose>
                <c:choose>
         <c:when test="${page.hasNextPage}">
         <a href="getPhoto.do?currentPage=${page.currentPage + 1 }">下一页</a>
	<a href="getPhoto.do?currentPage=${page.totalPage }">尾页</a>
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

   <script type="text/javascript" src="js/layui.all.js"></script>
<script>

layui.use('upload', function(){
  var upload = layui.upload;
  var uploadInst = upload.render({
    
    elem: '#upload-photo' //绑定元素
    ,url: 'uploadPhoto.do' //上传接口

    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
          
        $('#preview').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
    
      if(res=="1"){
      location.reload();
      layer.alert("照片上传成功",{
                skin: 'layui-layer-molv',
                closeBtn: 1,
                title:"考勤系统提醒您",
                shift: 1 //动画类型
            });
             
      }
    }
    ,error: function(){
      //请求异常回调
    }
  });

});



</script>
</body>
</html>
