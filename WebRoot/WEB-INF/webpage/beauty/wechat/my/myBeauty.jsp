<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>我的颜值</title>
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
        <div class="my-coin-cot mine">
            <div class="box">
                <h3>我的颜值 (个)</h3>
                <span class="num">${customUserPO.beauty_num}</span>
                <span  class="detail" onclick="initBeautyRecord()">交易明细</span>
            </div>
            <div class="btn">
                <a href="#" onclick="buyBeauty()">购买颜值</a>
                <a href="#" onclick="exchangeBeauty()">兑换颜值</a>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
/**
 * 购买颜值
 */
function buyBeauty(){

	window.location.href ='${ctx}/wechat/order/listBeautyInfo.jhtml';
}
/**
 * 兑换颜值
 */
function exchangeBeauty(){
	window.location.href ='${ctx}/wechat/home/goExchangeBeauty.jhtml';
}
/**
 * 颜值交易记录
 */
function initBeautyRecord(){
	window.location.href ='${ctx}/wechat/home/initBeautyRecord.jhtml';
}

</script>
</html>