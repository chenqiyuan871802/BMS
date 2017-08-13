<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>

<head>
    <title>微信登录</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    
</head>
<body>
    <div class="loginbox">
        <divi class="avatar">
        <input  type="hidden" id="openid"  value="${customUserPO.openid}" 	>
       <c:if test="${ empty customUserPO.photo}">
		<img src="${ctx }/static/common/images/system/person_img.png" style="width:150px;height:150px" /></c:if>
		<c:if test="${not empty customUserPO.photo}"><img src="${ctx}/${customUserPO.photo }" style="width:150px;height:150px" /></c:if>
        </divi>
        <div class="login-input">
            <div class="input-text"><i class="ico-phone"></i><input type="text" id="mobile" placeholder="请输入手机号码"></div>
            <div class="input-text"><i class="ico-password"></i><input class="code" id="checkCode" type="text" placeholder="请输入验证码">
            <a class="wechat" id="sendCode" href="#" onclick="sendCheckCode(this)">
            <span class="get-code">获取验证码</span>
             </a>
            </div>
            <input type="button" onclick="login()" value="登录">
        </div>
    </div>
</body>
<<script type="text/javascript">
var mobileReg = /^1[3|4|5|8|9]\d{9}$/;

var toast = new auiToast();
function login() {
	var mobile = $("#mobile").val();
	var checkCode = $("#checkCode").val();
	if (mobile == '') {
		toast.fail({
			title : "请输入手机号码",
			duration : 2000
		});
		return;
	}
	if (checkCode == '') {
		toast.fail({
			title : "请输入验证码",
			duration : 2000
		});
		return;
	}
	if (!mobileReg.test(mobile)) { //验证手机号码
		toast.fail({
			title : "输入手机号码不合法",
			duration : 2000
		});
		return;
	}
	var openid=$("#openid").val();
	
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/login/doLogin.jhtml',
		data : {
			mobile : mobile,
			checkCode:checkCode,
			openid:openid,
			loginWay:'1'
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				if (data.appcode == "1") {
					toast.success({
					    title:data.appmsg,
					    duration:2000
					});
					 window.location.href = '${ctx}/wechat/project/findProject.jhtml';
				} else {
					toast.fail({
						title : data.appmsg,
						duration : 2000
					});
				}
			} else {
				toast.fail({
					title : "登陆失败，系统处理异常",
					duration : 2000
				});
			}
		},
		error : function() {
			toast.fail({
				title : "登陆失败，网络异常",
				duration : 2000
			});
		}
	})
 
}
//发送验证码
function sendCheckCode() {

	var mobile = $("#mobile").val();
	if (mobile == '') {
		toast.fail({
			title : "请输入手机号码",
			duration : 2000
		});
		return;
	}
	if (!mobileReg.test(mobile)) { //验证手机号码
		toast.fail({
			title : "输入手机号码不合法",
			duration : 2000
		});
		return;
	}
	
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/login/sendCheckCode.jhtml',
		data : {
			mobile : mobile
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				if (data.appcode == "1") {
					$("#sendCode").removeAttr('href');
					$("#sendCode").removeAttr('onclick');
					toast.success({
					    title:data.appmsg,
					    duration:2000
					});
					var t = 60;
					for (i = 1; i <= t; i++) {

						window.setTimeout("updateGetCode(" + i + "," + t
								+ ")", i * 1000);
					}
				} else {
					toast.fail({
						title : data.appmsg,
						duration : 2000
					});
				}
			} else {
				toast.fail({
					title : "验证码发送失败",
					duration : 2000
				});
			}
		},
		error : function() {
			toast.fail({
				title : "验证码发送失败",
				duration : 2000
			});
		}
	})

}
function updateGetCode(num, t) {

	if (num == t) {
		$("#sendCode").attr('onclick', 'sendCheckCode()');
		$("#getCode").html("重新发送");
	} else {
		var printnr = t - num;
		$("#getCode").html(printnr + "秒后再发送");
	}

}</script>
</html>