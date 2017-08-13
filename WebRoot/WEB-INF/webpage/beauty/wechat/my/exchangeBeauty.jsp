<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>兑换颜值</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    
    <style>
        html,body{
            background:#f7f7f7;
        }
    </style>
</head>
<body>
    
    <div class="my-exchange-coin">
        <p class="tel">绑定手机号：${mobile}</p>
        <input class="input-text" type="text"  id="cdkey" placeholder="请输入兑换码">
        <div class="btn">
            <a href="#" onclick="saveExchangeBeauty()">确定</a>
        </div>
    </div>
</body>
<script type="text/javascript">

/**
 * 兑换颜值
 */
 function saveExchangeBeauty(){
	 var toast = new auiToast();
	  var cdkey = $("#cdkey").val();
		if (cdkey== '') {
			toast.fail({
				title : "请输入兑换码",
				duration : 2000
			});
			return;
	 }
		toast.loading({
            title:"数据处理中"
           
        })
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/home/exchangeBeauty.jhtml',
			data : {
				cdkey:cdkey
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					if (data.appcode == "1") {
						toast.success({
						    title:data.appmsg,
						    duration:2000
						});
						window.location.href ='${ctx}/wechat/home/goMyBeauty.jhtml';
					} else {
						toast.fail({
							title : data.appmsg,
							duration : 2000
						});
					}
				} else {
					toast.fail({
						title : "兑换失败，系统处理异常",
						duration : 2000
					});
				}
			},
			error : function() {
				toast.fail({
					title : "网络异常",
					duration : 2000
				});
			}
		})
		toast.hide();
 }
</script>
</html>