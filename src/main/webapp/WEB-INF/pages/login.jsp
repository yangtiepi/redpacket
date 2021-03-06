<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
<meta charset="UTF-8">

<script type="text/javascript">
if(top!=window){
	top.location.href=window.location.href;
}
</script>

<title>微信红包 </title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->

<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/login.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="js/html5.js"></script>
<![endif]-->
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/tooltips.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<!-- 引入页面公共资源:easyui主题/皮肤;easyui图标样式;easyui依赖jquery库(jquery.min.js);easyui核心文件;easyui汉化文件 -->
<%@include file="/WEB-INF/pages/common.jsp" %>
</head>
<script type="text/javascript">
$(function(){
	$("#randomImg").click(function(){
		//刷新验证码
		var img = document.getElementById("randomImg");
		//因为在有些有些浏览器中会把多次相同的请求视为一次请求,所有在后面加一个随机数区分
		img.src = "/randomCode/service.do?" + Math.random();
	});
	$("#rememberPassword").click(function(){
		$("#rememberName").each(function(){
			this.checked=true;
		});
	});
});
</script>
<body>
<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<img src="images/32.gif" style="width: 70px;height: 70px;" >
			</div>
		
			<div class="login_form">
				<form class="easyui-form" id="login_form" method="post">
					<div class="form-group">
						<label for="j_username" class="t">账　号：</label> 
						<input id="name" name="username" type="text" class="form-control x319 in" 
						autocomplete="off">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" name="password" type="password" 
						class="password form-control x319 in">
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						 <input  name="randomCode" type="text" class="form-control x164 in">
						<img src="randomCode/service.do" id="randomImg" style="cursor: pointer;"  id="randomImg" title="看不清,换一张" />
					</div>
					<div class="form-group">
						<label class="t"></label>
						<label for="j_remember" class="m">
						<input id="rememberName" name="rememberName" type="checkbox" value="true">&nbsp;记住账号!</label>&nbsp;
						&nbsp;&nbsp;&nbsp;
						<input id="rememberPassword" name="rememberPassword" type="checkbox" value="true">&nbsp;记住密码!</label>
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit_btn" 
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp; </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">微信红包后台 &copy; 2015 - 2025 <a href="#">系统登陆</a></div>
	</div>
</div>

<!-- Javascript -->

<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script src="js/scripts.js"></script>
<div style="text-align:center;">

</div>
</body>
</html>