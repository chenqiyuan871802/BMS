<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="ch" class="no-js">
<head>
<title>欢迎登录后台管理系统</title>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
<link rel="shortcut icon"
	href="${ctx }/static/common/images/favicon.ico">

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/weblib/easyui/themes/default/easyui.css">
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
	function login() {
		var account = $("#account").val();
		var password = $("#password").val();
		if (account == "") {

			$.messager.alert('警告信息', '用户账号不能为空，请输入', 'warning');
			return;
		}
		if (password == "") {

			$.messager.alert('警告信息', '用户密码不能为空，请输入', 'warning');
			return;
		}
		$
				.ajax({
					type : 'post',
					url : '${ctx}/system/login/doLogin.jhtml',
					data : {
						'account' : account,
						'password' : password

					},
					dataType : 'json',
					success : function(data) {
						if (data) {
							if (data.appcode == "1") {
								window.location.href = '${ctx}/system/login/goMain.jhtml';
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

<style>
body {
	width: 100%;
	height: 100%;
	background: url(${ctx }/static/common/images/system/logobg.png)
		no-repeat fixed center;
	overflow: hidden;
	position: relative;
}

.kuang {
	width: 330px;
	height: 450px;
	background: #fff;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -175px;
	margin-top: 120px;
}

.kuang1 {
	height: 200px;
	background: #fafafa;
	position: relative;
}

.logo {
	position: absolute;
	left: 50%;
	margin-left: -37px;
	top: 50%;
	margin-top: -50px;
}

input {
	border: none;
	outline: none;
	margin-left: 10px;
}

.line {
	width: 250px;
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
	background: url(${ctx }/static/common/images/system/loginback.png) no-repeat center center;
	width: 330px;
	height: 50px;
	font-family: "微软雅黑";
	font-size: 16px;
	color: aliceblue;
	position: absolute;
	bottom: 0;
}

.a, .pass {
	margin-left: 8px;
}
</style>
</head>

<body oncontextmenu="return false">

	<div class="kuang">
		<div class="kuang1">
			<img class="logo"
				src="${ctx }/static/common/images/system/logoimg.png">

		</div>
		<div class="page-container">

			<form action="" method="post">
				<div class="line">
					<img class="a"
						src="${ctx }/static/common/images/system/account.png"> <input
						type="text" name="account"  id="account" class="username" autocomplete="off" placeholder="请输入账号"
						autocomplete="off" />
				</div>
				<div class="line">
					<img class="pass"
						src="${ctx }/static/common/images/system/pass.png"> <input
						type="password" name="password" class="password" id="password" autocomplete="off"
						placeholder="请输入密码" oncontextmenu="return false" onkeydown="javascript:enterSumbit();"
						onpaste="return false" />
				</div>
				<button class="btn" id="submit" type="button" onclick="login()">登录</button>
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

