<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<title>500error</title>

    <!-- Bootstrap -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/font-awesome.css" rel="stylesheet">
    <link href="assets/css/bootstrap-theme.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/animations.css">

    <!-- siimple style -->
    <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>

    <div class="cloud floating">
        <img src="assets/img/cloud.png" alt="Scoop Themes">
    </div>

    <div class="cloud pos1 fliped floating">
        <img src="assets/img/cloud.png" alt="Scoop Themes">
    </div>

    <div class="cloud pos2 floating">
        <img src="assets/img/cloud.png" alt="Scoop Themes">
    </div>

    <div class="cloud pos3 fliped floating">
        <img src="assets/img/cloud.png" alt="Scoop Themes">
    </div>


    <div id="wrapper">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12">
                    <img src="assets/img/logo.png" alt="500">
                    <br/>
                    <br/>
                    <h2 class="subtitle">抱歉 ！所请求的页面有错误，您可以发送邮件反馈，稍后为您自动跳转至登录</h2>
                    <br/>
             
                    <form class="form-inline validate signup" action="" method="get" role="form">
                        <div class="form-group">
                            <input type="email" class="form-control" name="EMAIL" id="exampleInputEmail1" placeholder="输入您的邮箱">
                        </div>
                        <input type="submit" name="subscribe" value="反馈" class="btn btn-theme">
                    </form>
                    <br/>
                </div>
                <div class="col-sm-12 align-center">
                   
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <p class="copyright">Copyright &copy; 2017 - attendance
                    </p>
                </div>
            </div>
        </div>
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="http://downloads.mailchimp.com/js/jquery.form-n-validate.js"></script>
    <script>
       setTimeout(function () {       
   window.top.location.href="<%=path%>/login.jsp";     },5000);   
$(document).ready( function () {
    $('#wrapper').height($(document).height());
    // I only have one form on the page but you can be more specific if need be.
    var $form = $('form');

    if ( $form.length > 0 ) {
        $('form input[type="submit"]').bind('click', function ( event ) {
            if ( event ) event.preventDefault();
            // validate_input() is a validation function I wrote, you'll have to substitute this with your own.
            if ( $form.validate() ) { register($form); }
        });
    }    
});

function appendResult(userText , className, iconClass){
  var resultHTML = "<div class='stretchLeft result "+ className + "'>" + userText + " <span class='fa fa-" + iconClass + "'></span>" + "</div>";
  $('body').append(resultHTML);
  $('.result').delay(10000).fadeOut('1000');
}


function register($form) {
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize(),
        cache       : false,
        dataType    : 'json',
        contentType: "application/json; charset=utf-8",
        error       : function(err) { alert("Could not connect to the registration server. Please try again later."); },
        success     : function(data) {
            if (data.result != "success") {
                appendResult('Wrong Email Or You Are Already Registered, Try Again', 'error', 'exclamation');
            } else {
                // It worked, carry on...
                appendResult('Successful, Check Your Email For Confirmation ', 'success', 'check');
            }
        }
    });
}
    </script>

</body>

</html>

