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
<title>内部通讯</title>
<script type="text/javascript" src="js/jquery.min.js"></script>

<link href="<%=path %>/css/styles.css" rel="stylesheet" type="text/css">
<link href="<%=path %>/css/message.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/util.js"></script>

</head>
<script>
function windowHeight() {
	var de = document.documentElement;
	return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
window.onload=window.onresize=function(){


window.setInterval(getUserStat,2000); 
	var wh=windowHeight();
	document.getElementById("xt-left").style.height = document.getElementById("xt-right").style.height = (wh-document.getElementById("xt-top").offsetHeight)+"px";
};

function loginout(){
	if(confirm("确定退出系统")){
	$("#loginout").attr("href","loginout.do");
	 }
	}
</script>
<style>
html {
	overflow: hidden
}
</style>

<body>
 <!-- 用户私聊 -->
   <div id="chatWithUser">
   <div style="width:100%;height:76%;border-bottom:1px solid"></div>
    <div style="width:100%;height:24%;">
    
    
  <button name="" class="sub_but">关闭</button>&nbsp;&nbsp;<button name="" class="sub_but">发 送</button>&nbsp;&nbsp;
    </div>
   </div>



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
					<li><a href="usermain.do"><em class="one"></em>网站首页</a>
					</li>
				</ul>
				<ul>
					<li><a href="getUserList.do"><em class="two"></em>个人考勤</a>
					</li>
				</ul>
				 <ul>
                 <li><a href="quickQuery.do" ><em class="two"></em>快捷查询</a></li>
                </ul> 
				<ul>
					<li><a href="getUserPhoto.do"><em class="two"></em>记忆相册</a>
					</li>
				</ul>
				<ul>
					<li><a href="getMessage.do" class="hover"><em class="two"></em>内部通讯</a>
					</li>
				</ul>

				<ul>
					<li><a href="getTimeLog.do"><em class="two"></em>更新日志</a>
					</li>
				</ul>
				        <ul>
		<li><a href="userAutoService.do"><em class="two"></em>自助服务</a></li>
		</ul>
			</div>
		</div>
		<!-- right所有内容在此div编辑 -->
		<div id="xt-right">
		<div class="chatbox">
  <div class="chat_top fn-clear">
 
    <div class="uinfo fn-clear">
      <div class="uface"><img src="userimage/${employee.image }" width="40" height="40"  alt=""/></div>
      <div class="uname">
           ${employee.username }
       
      </div>
    </div>
  </div>
  <div class="chat_message fn-clear">
    <div class="chat_left">
      <div class="message_box" id="message_box">
<div class="notice">
      			<h2>重要公告</h2>
        <p>根据《中华人民共和国宪法》和相关法律法规规定，在保护公民合法言论自由的同时，禁止利用互联网、通讯工具、媒体以及其他方式从事以下行为：</p>
	      <ul>
		     <li>一、组织、煽动抗拒、破坏宪法和法律、法规实施的。</li>
		     <li>二、捏造或者歪曲事实，散布谣言，妨害社会管理秩序的。</li>
		    <li>三、组织、煽动非法集会、游行、示威、扰乱公共场所秩序的。</li>
		    <li>四、从事其他侵犯国家、社会、集体利益和公民合法权益的。</li>
	     </ul>
       <p class="publish">群聊管理员---2017-09-08</p>
      		
      	</div>
<!-- 消息开始-->
 
 
<!-- 消息结束--> 
      </div>
    <!-- 快捷操作开始-->
 <div class="helpbox" style="padding-left:3px">
 &nbsp;&nbsp;
 <img src="img/helpicon/Smile.png" onclick="alert('功能未开发')"/> &nbsp;&nbsp;
  <img src="img/helpicon/pic.png" onclick="alert('功能未开发')"/>
 </div>
 
<!-- 快捷操作结束-->  
      <div class="write_box">
        <textarea id="message" name="message" class="write_area" placeholder="说点啥吧...你也可以通过@小创 +你想问的问题(例如@小创 你好)"></textarea>
        <input type="hidden" name="fromname" id="fromname" value="${employee.username }" />
        <input type="hidden" name="to_uid" id="to_uid" value="0">
        <div class="facebox fn-clear">
          <div class="expression"></div>
          <div class="chat_type" id="chat_type">
          </div>
         <button name="" class="sub_but">发 送</button>&nbsp;&nbsp;

        </div>
      </div>
    </div>
    <div class="chat_right">
      <ul class="user_list" title="双击用户私聊">
        <span ><em style="margin:0 auto;width:30px">所有用户</em></span>
         <li class="fn-clear" data-id="2"><span><img src="userimage/rebot.jpg" width="30" height="30"  alt=""/></span><em>小创</em><small class="online" title="在线"></small></li>
        
         <c:forEach items="${List}" var="employee">
        <li class="fn-clear" data-id="2" id="${employee.id}"><span><img src="userimage/${employee.image }" width="30" height="30"  alt=""/></span><em>${employee.username }</em><c:if test="${employee.online==1 }"><small class="online" title="在线"></small></c:if> <c:if test="${employee.online==0 }"><small class="offline" title="离线"></small></c:if></li>
    
      </c:forEach>
      </ul>
    </div>
  </div>
</div>
<script type="text/javascript">
//与服务端请求连接
var websocket=null;
var username='${employee.username}';

if('WebSocket' in window){

wsServer = "ws://" + location.host+"${pageContext.request.contextPath}" + "/server";
websocket=new WebSocket(wsServer);
}else{
alert("您的浏览器不支持websocket");
}
websocket.onerror=function(){
setMessageInnerHTML("连接出错");
};
//连接成功建立的回调方法
websocket.onopen = function () {
       
       
        var welcome=' <div class="welcome-bye">'+
        	'<img src="images/face/39.gif" /><p>欢迎'+username+'加入群聊</p>'+
        	' </div>';
        websocket.send(welcome);
};
   
   //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    };

    //连接关闭的回调方法
    websocket.onclose = function () {
        var bye=' <div class="welcome-bye">'+
        	'<img src="images/face/29.gif" /><p>'+username+'离开群聊</p>'+
        	' </div>';
        $("#message_box").append(bye);
        chmodStat();
    };
//将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
    $("#message_box").append(innerHTML); 
    if(innerHTML.indexOf("@小创")!=-1){ 
     //消息延时执行
     setTimeout(function(){getMessageFromTuLing(innerHTML);},1000);          
    }     
	$('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
    }

 //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
         chmodStat();
        closeWebSocket();
    };
 //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket连接发生错误");
    };
 //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
$(document).ready(function(e) {
	$('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
	$('.uname').hover(
	    function(){
		    $('.managerbox').stop(true, true).slideDown(100);
	    },
		function(){
		    $('.managerbox').stop(true, true).slideUp(100);
		}
	);
	
	var fromname = $('#fromname').val();
	var to_uid   = 0; // 默认为0,表示发送给所有用户
	var to_uname = '';
	$('.user_list > li').dblclick(function(){
          alert("用户私聊功能未开发...........");
	});
	//发送消息
	$('.sub_but').click(function(event){
	    sendMessage(event, fromname, to_uid, to_uname);
	});			
	//结束聊天
	$('.sub_but_end').click(function(event){
	    closeWebSocket();
	});
		
	/*按下按钮或键盘按键*/
	$("#message").keydown(function(event){

		var e = window.event || event;
        var k = e.keyCode || e.which || e.charCode;
		//按下ctrl+enter发送消息
		if((event.ctrlKey && (k == 13 || k == 10) )){
		$('.sub_but').click();
			sendMessage(event, fromname, to_uid, to_uname);
		}
	});
});
function sendMessage(event, from_name, to_uid){
    var time=formatCurrentDate();
    var msg = $("#message").val();
    var path='${employee.image}';
	var htmlData =   '<div class="msg_item fn-clear">'
                   + '   <div class="uface"><img src="userimage/'+path+'" width="40" height="40"  alt=""/></div>'
			       + '   <div class="item_right">'
			       + '     <div class="msg own" id="content_data">' + msg + '</div>'
			       + '     <div class="name_time">' + from_name + ' · '+time+'</div>'
			       + '   </div>'
			       + '</div>';
	
	$("#message").val('');
	websocket.send(htmlData);
}
//通过异步刷新修改用户状态
function chmodStat(){
var userid='${employee.userid}';
$.ajax({
    url:"chmodStat.do?userid="+userid+"&online=0",
     type: "POST",
     async:false,
     dataType:"text",
     contentType : false,
     success:function(){
     }

});
}
//定时获取用户状态

function  getUserStat(){
$.ajax({
    url:"getUserStat.do",
     type: "POST",
     async:false,
     dataType:"json",
     contentType : false,
     success:function(res){
     for(var i=0;i<res.length;i++){
     var image=res[i].image;
     var online=res[i].online;
     var username=res[i].username;
     var id=res[i].id;
     if(online==1){
          document.getElementById(id).innerHTML="<span><img src='userimage/"+image+"' width='30' height='30'/></span><em>"+username+"</em><small class='online' title='在线'></small>";
     
     }else{
          document.getElementById(id).innerHTML="<span><img src='userimage/"+image+"' width='30' height='30'/></span><em>"+username+"</em><small class='offline' title='离线'></small>";
     
     }
    
     } 

     }

});

}
//获取格式化时间
function add0(m){return m<10?'0'+m:m};
function formatCurrentDate()
{
var time = new Date();
var y = time.getFullYear();
var m = time.getMonth()+1;
var d = time.getDate();
var h = time.getHours();
var mm = time.getMinutes();
var s = time.getSeconds();
return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}
//通过接口获取返回数据
function getMessageFromTuLing(value){
   //获取发送内容
   var msg=$(value).find("#content_data").html();
   var msg_new=msg.substring(msg.indexOf("创")+1);
   msg_new.replace(/\s/ig,'');
   $.getJSON("http://www.tuling123.com/openapi/api?key=836cc333f85746d88296a75c59340ba0&info="+msg_new,function(data){
    if(data.code == 100000){
        var time=formatCurrentDate();
        var from_name="小创";
     var htmlData = '<div class="msg_item fn-clear">'
                   + '   <div class="uface"><img src="userimage/rebot.jpg" width="40" height="40"  alt=""/></div>'
			       + '   <div class="item_right">'
			       + '     <div class="msg own">' + data.text + '</div>'
			       + '     <div class="name_time">' + from_name + ' · '+time+'</div>'
			       + '   </div>'
			       + '</div>';

     $("#message_box").append(htmlData);
     $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
    }else{
    message="您说的我没懂";
    $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
    }       
   }
   ); 
}


</script>
		
		</div>
		<!-- 主区域结束 -->
	</div>
	</div>
</body>
</html>
