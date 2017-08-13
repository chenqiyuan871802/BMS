<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/ims.tld" prefix="IMS"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="ch" class="no-js">

<head>

<meta charset="utf-8">
<title><IMS:paramOut paramKey="shop_sys_title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="shortcut icon"
	href="${ctx }/static/common/images/favicon.ico">

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/weblib/easyui/themes/metro-orange/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/weblib/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/weblib/easyui/themes/color.css">
<script type="text/javascript"
	src="${ctx}/static/weblib/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/weblib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/weblib/easyui/locale/easyui-lang-zh_CN.js"></script>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
	function login() {

		var account = $("#account").val();
		var password = $("#password").val();
		var worksn = localStorage.getItem("IMSWORKSN");
		if (account == "") {

			$.messager.alert('警告信息', '账号不能为空，请输入', 'warning');
			return;
		}
		if (password == "") {

			$.messager.alert('警告信息', '密码不能为空，请输入', 'warning');
			return;
		}
	       $.ajax({
					type : 'post',
					url : '${ctx}/shop/login/doLogin.jhtml',
					data : {
						'account' : account,
						'password' : password,
						'worksn' : worksn

					},
					dataType : 'json',
					success : function(data) {
						if (data) {
							if (data.appcode == "1") {
								if (data.user_type == '1') {
									window.location.href = '${ctx}/shop/login/goOwnerMain.jhtml';
								} else {
									window.location.href = '${ctx}/shop/login/goMain.jhtml';
								}

							} else if (data.appcode == "0") {
								$.messager
										.alert('警告信息', data.appmsg, 'warning');
							} else {
								$.messager.alert('错误信息', data.appmsg, 'error');
							}
						} else {
							$.messager.alert('错误信息', '操作失败', 'error');
						}
					},
					error : function() {
						$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
					}
				})
	}
	function enterSumbit() {
		if (event.keyCode == 13) {
			login();
		}

	}
</script>

</head>
<style>
body {
	width: 100%;
	height: 100%;
	background: url(${ctx }/static/common/images/system/shopbg.png) no-repeat fixed center;
	overflow: hidden;
	position: relative;
}

.text {
	font-family: "微软雅黑";
	font-size: 36px;
	color: #b66001;
	text-align: center;
	padding: 120px 0 6px 0;
}

.text1 {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #e58a25;
	text-align: center;
}

.kuang {
	width: 650px;
	height: 340px;
	background: #fff;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -325px;
	margin-top: 130px;
}

.kuang1 {
	width: 250px;
	height: 340px;
	position: relative;
}

.logo {
	position: absolute;
	left: 50%;
	margin-left: -37px;
	top: 50%;
	margin-top: -80px;
}

.page-container {
	width: 400px;
	height: 340px;
	position: relative;
	position: absolute;
	top: 0;
	left: 36%;
}

.a, .pass {
	margin-left: 8px;
}

.text2 {
	font-family: "微软雅黑";
	font-size: 16px;
	color: #e58924;
	margin: 30px 0 15px 50px;
}

.text3 {
	font-family: "微软雅黑";
	font-size: 16px;
	color: #cccccc;
	position: absolute;
	left: 32%;
	top: 9%;
}

input {
	border: none;
	outline: none;
	margin-left: 10px;
}

.line {
	width: 300px;
	height: 30px;
	border-bottom: 1px solid #ebebeb;
	padding: 30px 0 10px 0;
	margin: 10px 40px 0 40px;
}

.btn {
	margin: 0;
	padding: 0;
	border: none;
	background-color: transparent;
	outline: none;
	background: url(${ctx }/static/common/images/system/shopbtn.png) no-repeat center;
	width: 330px;
	height: 50px;
	font-family: "微软雅黑";
	font-size: 16px;
	color: aliceblue;
	margin: 40px 25px;
}
</style>
</head>

<body oncontextmenu="return false">
	<div class="text">信息管理系统界面</div>
	<div class="text1">INFORMATION MANAGEMENT SYSTEM GUI</div>
	<div class="kuang">
		<div class="kuang1">
			<img class="logo" src="${ctx }/static/common/images/system/shoplogo.png">

		</div>
		<div class="page-container">


			<div class="text2">用户登录</div>
			<div class="text3">User login</div>




			<form action="" method="post">
				<div class="line">
					<img class="a" src="${ctx }/static/common/images/system/account.png"> <input type="text"
						name="account"  id="account" class="username" placeholder="请输入账号"
						autocomplete="off"  />
				</div>
				<div class="line">
					<img class="pass" src="${ctx }/static/common/images/system/pass.png"> <input type="password"
						name="password" id="password" class="password" placeholder="请输入密码"
						oncontextmenu="return false" onpaste="return false" onkeydown="javascript:enterSumbit();" />
				</div>
				<button class="btn" id="submit" type="button" onclick="login()">立即登录</button>
			</form>

		</div>
		<div class="alert" style="display: none">
			<h2>消息</h2>
			<div class="alert_con">
				<p id="ts"></p>
				<p style="line-height: 70px">
					<a class="btn">确定</a>
				</p>
			</div>
		</div>
	</div>
	<!-- Javascript -->

</body>

</html>
