<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />

<!-- 浏览器标签显示网站logo 
写两行是为了兼容、为了备用
1：将“shortcut icon”看做一个字符串，在遵循标准的浏览器中此字符串会被识别为“icon”，而在“IE”中会被识别为“shortcut icon”；所以为了兼容。
2：写两行是为了备用，一行用不了，就用另外一行。-->
<link rel="icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath %>static/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/login/css/style.css" />

<style>
body {
	height: 100%;
	width:100%;
	background-image:url(static/login/images/TheWarderingEarth.jpg); 
	background-repeat:no-repeat;
	background-size:cover;/* 自适应大小  */
	overflow: hidden;
}
canvas {
	z-index: -1;
	position: absolute;
}
</style>

<script src="<%=basePath %>static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<script src="<%=basePath %>static/js/Particleground.js" tppabs="js/Particleground.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script>
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#696969',
			lineColor : '#696969'
		});
	});
	var timer;
	function showfh(){
		fhi = 1;
		//关闭提示晃动屏幕，注释掉这句话即可
		timer = setInterval(xzfh2, 10); 
	};
	var current = 0;
	var fhi = 1;
	var current2 = 1;
	function xzfh2(){
		if(fhi>3){
			document.body.style.transform = 'rotate(0deg)';
			clearInterval(timer);
			return;
		}
		current = (current2)%360;
		document.body.style.transform = 'rotate('+current+'deg)';
		current ++;
		if(current2 == 1)
			{current2 = -1;}
		else
			{current2 = 1;}
		fhi++;
	};
	
</script>
</head>
<body>
	<dl class="admin_login" id="loginbox">
		<dt>
			<strong>后台管理系统</strong> <em>Management System</em>
		</dt>
		<dd class="user_icon">
			<input type="text" placeholder="账号" class="login_txtbx" name="loginname" id="loginname"/>
		</dd>
		<dd class="pwd_icon">
			<input type="password" placeholder="密码" class="login_txtbx" name="password" id="password"/>
		</dd>
		<dd class="val_icon">
			<div class="checkcode" style="width:300px;">
				<input type="text" name="code" id="code" placeholder="验证码" class="login_txtbx" AUTOCOMPLETE="off" style="width:176px;" />
				<!-- <img src="static/login/yan.png" /> -->
				<img style="height:42px;float:right;" id="codeImg" alt="点击更换" title="点击更换" />
			</div>
		</dd>
		<dd style="height:14px;">
			<input type="checkbox" id="saveid" value="记住密码" class="ver_btn" onclick="savePaw();" style="position:absolute;left:230px;top:0px;">
			<div style="float:right;">记住密码</div>
		</dd>
		<dd>
			<input type="button" onclick="severCheck();" id="to-recover" value="立即登录" class="submit_btn" />
		</dd>
	</dl>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});
	
	
		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}
		
		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}
		
		//回车事件，按回车键相当于点击登录
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});
		
		//服务器校验
		function severCheck(){
			if(check()){
				
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = "Peppa"+loginname+",fh,"+password+"Pig"+",fh,"+$("#code").val();
				$.ajax({
					type: "POST",
					url: 'login_login',
			    	data: {KEYDATA:code,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							$("#loginbox").tips({
								side : 1,
								msg : '正在登录 , 请稍后 .......',
								bg : '#ff7700',
								time : 11
							});
							saveCookie();
							window.location.href="main/index";
						}else if("usererror" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 7
							});
							showfh();
							$("#loginname").focus();
						}else if("codeerror" == data.result){
							$("#code").tips({
								side : 1,
								msg : "验证码输入有误",
								bg : '#FF5080',
								time : 7
							});
							showfh();
							$("#code").focus();
						}else{
							$("#loginname").tips({
								side : 1,
								msg : "缺少参数",
								bg : '#FF5080',
								time : 7
							});
							showfh();
							$("#loginname").focus();
						}
					}
				});
			}
		}
		
		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 7
				});
				showfh();
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 7
				});
				showfh();
				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {

				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 7
				});
				showfh();
				$("#code").focus();
				return false;
			}

			/* $("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 .......',
				bg : '#ff7700',
				time : 10
			}); */

			return true;
		}
		
		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
	</script>
	
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
	<c:if test="${'1' == pd.msg}">
		<script type="text/javascript">
		$(tsMsg());
		function tsMsg(){
			alert('此用户在其它终端已经早于您登录,您暂时无法登录');
		}
		</script>
	</c:if>
	<c:if test="${'2' == pd.msg}">
		<script type="text/javascript">
			$(tsMsg());
			function tsMsg(){
				alert('您被系统管理员强制下线');
			}
		</script>
	</c:if>
	
</body>
</html>
