<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>领取礼包</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
   
    <style>
        body{
            background:url(${ctx}/static/wechat/images/my-coin-bg.jpg) 0 2.346667rem no-repeat #f5b24f;background-size:cover;
        }
    </style>
</head>
<body>
    <div class="my-coin-box">
        <div class="my-coin-logo"><img src="${ctx}/static/wechat/images/my-coin-logo.png"></div>
        <input  type="hidden" id="openid"  value="${dataDto.openid}" 	>
        <input  type="hidden" id="record_id"  value="${dataDto.record_id}" 	>
        <div class="loginbox share-page">
            <div class="tips">
                <p>${dataDto.msg}</p>
                <p class="orang">输入手机号领取</p>
            </div>
            <div class="login-input">
                <div class="input-text"><i class="ico-phone"></i><input type="text" id="mobile" placeholder="请输入手机号码"></div>
                <div class="input-text"><i class="ico-password"></i><input class="code" id="checkCode" type="text" placeholder="请输入验证码"><span id="getCode"  onclick="sendCheckCode(this)" class="get-code">获取验证码</span></div>
                <input type="button" value="领取礼包" onclick="receiveBagLogin()">
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
var mobileReg = /^1[3|4|5|8|9]\d{9}$/;
var toast = new auiToast();
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
		url : '${ctx}/wechat/shareBag/sendCheckCode.jhtml',
		data : {
			mobile : mobile
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				if (data.appcode == "1") {
					$("#getCode").removeAttr('onclick');
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
		$("#getCode").attr('onclick', 'sendCheckCode()');
		$("#getCode").html("重新发送");
	} else {
		var printnr = t - num;
		$("#getCode").html(printnr + "秒后再发送");
	}

}
/**
 * 领取礼包验证
 */
function receiveBagLogin() {
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
	var record_id=$("#record_id").val();
	
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/shareBag/saveCheckShareUser.jhtml',
		data : {
			mobile : mobile,
			checkCode:checkCode,
			openid:openid,
			record_id:record_id,
			loginWay:'1'
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				if (data.appcode == "1") {
					 window.location.href = '${ctx}/wechat/shareBag/saveShareBag.jhtml?mobile='+mobile+'&custom_user_id='+data.custom_user_id+"&record_id="+record_id;
				} else {
					toast.fail({
						title : data.appmsg,
						duration : 2000
					});
				}
			} else {
				toast.fail({
					title : "领取礼包失败，系统处理异常",
					duration : 2000
				});
			}
		},
		error : function() {
			toast.fail({
				title : "领取礼包失败，网络异常",
				duration : 2000
			});
		}
	})
 
}

</script>
</html>