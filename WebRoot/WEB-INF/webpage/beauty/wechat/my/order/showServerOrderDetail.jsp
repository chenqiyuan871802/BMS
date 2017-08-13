<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head> 
    <title>订单详情</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <style type="text/css">
        html,body{background:#f7f7f7;}
    </style>
</head>
<body>
    <div class="order-detail">
        <div class="top">
            <div class="state"><span class="fl">服务中</span></div>
            <div class="box">
                <img class="fl" src="${ctx }/${nurseProjectPO.cover_photo}">
                <div class="intro fl">
                    <h3>${nurseProjectPO.project_name}</h3>
                    <p>￥<fmt:formatNumber value="${nurseProjectPO.rmb_price}" type="number" />/${nurseProjectPO.beauty_price}颜值</p>
                </div>
                <div class="fr">
                    <span>x${orderDto.buy_num }</span>
                </div>
            </div>
        </div>
        <div class="order-detail-describe mt20">
            <div class="hd">
                <p>定金：￥<fmt:formatNumber value="${orderDepositPO.deposit_money }" type="number" /></p>
                <p>支付方式：<IMS:codeOut codeKey="${orderDepositPO.pay_way}" field="pay_way"/></p>
            </div>
            <div class="cot mt60">
                <p>订单编号：${orderDto.order_id}</p>
                <p>创建时间：<fmt:formatDate value="${orderDto.create_time}" pattern="yyyy-MM-dd HH:mm"/></p>
                <p>预约时间：<fmt:formatDate value="${orderDto.subscribe_time}" pattern="yyyy-MM-dd HH:mm"/></p>
                <p>商家编号：${orderDto.shop_id }</p>
                <p>商家名称：${orderDto.shop_name }（${orderDto.short_name }）</p>
                 <p onclick="openLocation(${orderDto.gps_x},${orderDto.gps_y})">商家地址：${orderDto.shop_address}</p>
            </div>
        </div>
        <div class="h120"></div>
        <div class="order-detail-bottom">
            <a class="fr btn" href="#" onclick="goPayOrder(${orderDto.order_id})">去支付</a>
        </div>
    </div>
     <!-- 底部菜单 -->
    <div class="h88 mt20"></div>
    <div class="bottom-nav">
        <ul>
         
            <li class="find-project">
            <a href="${ctx}/wechat/project/findProject.jhtml" >
                <i class="ico"></i>
                <span>找项目</span>
             </a>
                
            </li>
          
           
            <li class="find-gift">
             <a href="${ctx}/wechat/bag/goBag.jhtml" >
                <i class="ico"></i>
                <span>找礼包</span>
               </a> 
            </li>
           
            
            <li class="mine">
            <a href="${ctx}/wechat/home/goHome.jhtml" >
                <i class="ico"></i>
                <span>我的</span>
                </a>
            </li>
            
        </ul>
    </div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
//跳转到支付页面
function goPayOrder(order_id){
	 window.location.href ='${ctx}/wechat/order/goPayOrder.jhtml?order_id='+order_id;
}
$(function() {
	getWxConfig();
})
function getWxConfig() {
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/bag/getWechatConfig.jhtml',
		data : {
			url : location.href.split('#')[0]
		},
		dataType : 'json',
		success : function(data) {
			if (data.appcode == "1") {
				wx.config({
					debug : false,
					appId : data.appid,
					timestamp : data.timestamp,
					nonceStr : data.nonceStr,
					signature : data.signature,
					jsApiList : [ 'openLocation','getLocation']//分享到朋友圈 
				});
			} else { //微信注入权限接口
				

			}
		},
		error : function() {

			toast.fail({
				title : "获取微信签名网络异常",
				duration : 2000
			});

		}
	})
}
//打开百度地图
function openLocation(gps_x,gps_y){
	if(gps_x==0||gps_y==0){
		toast.fail({
			title : "请在后台配置店铺的百度坐标",
			duration : 2000
		});
		return ;
	}
	var point=BdmapEncryptToMapabc(gps_y,gps_x);
	wx.openLocation({
	    latitude:point.lat, // 纬度，浮点数，范围为90 ~ -90
	    longitude: point.lng, // 经度，浮点数，范围为180 ~ -180。
	    name: '${orderDto.shop_name}（${orderDto.short_name}）', // 位置名
	    address:'${orderDto.shop_address}', // 地址详情说明
	    scale: 20 // 地图缩放级别,整形值,范围从1~28。默认为最大
	   
	});
	
}

function BdmapEncryptToMapabc(bd_lat,bd_lon)  
{  
 var point=new Object();  
 var x_pi = 3.14159265358979324 * 3000.0 / 180.0;  
    var x = new Number(bd_lon - 0.0065);  
    var y = new Number(bd_lat - 0.006);  
    var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
    var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);  
    var Mars_lon = z * Math.cos(theta);  
    var Mars_lat = z * Math.sin(theta);  
    point.lng=Mars_lon;  
    point.lat=Mars_lat;  
    return point;  
}  
</script>
</html>