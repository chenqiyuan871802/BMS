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
    <div class="my-coin-box share-page-box">
        <div class="my-coin-logo"><img src="${ctx}/static/wechat/images/my-coin-logo.png"></div>
        <div class="share-page">
            <div class="tips">
                <p>${dataDto.msg}</p>
            </div>
            <div class="share-page-success">
                <p class="orang">
                 <c:if test="${dataDto.returnFlag=='3'}">
                                                  大礼包已放入您的账号
                 </c:if>
                 </p>
                <p class="orang">${dataDto.mobile }</p>
                <img src="${ctx}/static/wechat/images/ico_giftbag.png">
            </div>
            <div class="qrcode">
                <img src="${ctx}/static/wechat/images/code.png">
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
</script>
</html>