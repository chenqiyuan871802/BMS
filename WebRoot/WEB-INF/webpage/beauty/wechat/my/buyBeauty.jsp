<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>购买颜值</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
</head>
<body>
    <div class="buy-coin-box">
        <ul class="config">
           <c:forEach  var="beauty" items="${beautyList}" varStatus="status">
           
            <li dataid="${beauty.config_id}">
                <i class="ico" ><img src="${ctx}/static/wechat/images/ico-coin1.png"></i>
                <div class="box">
                    <p class="fl"><span>${beauty.buy_num }币</span><span>赠送${beauty.give_num }个</span></p>
                    <p class="price fr">￥<fmt:formatNumber type="number"  value="${beauty.total_price }" /></p>
                </div>
               
            </li>
            </c:forEach>
           
        </ul>
        <input id="config_id" type="hidden"/>
        <input id="shop_id" value="${shop_id }" type="hidden"/>
        <div class="btn"><a href="#" onclick="buyBeautyPay()">立即支付</a></div>
    </div>
</body>
<style>
 .config .active{
    background:#F00;
	border:#F00 1px solid;
 } 
</style>
<script type="text/javascript">

$(function(){  
	$('.config li').click(function(){
	    $(this).addClass('active').siblings().removeClass('active');
	    $('#config_id').val($(this).attr('dataid'));
	})
 })
 //颜值支付
 function buyBeautyPay(){
	var toast = new auiToast();
	  var config_id = $("#config_id").val();
		if (config_id== '') {
			toast.fail({
				title : "请选择你要购买的颜值",
				duration : 2000
			});
			return;
	 }
		var shop_id= $("#shop_id").val();
		 $.ajax({
				type : 'post',
				url : '${ctx}/wechat/order/saveBuyBeautyOrder.jhtml',
				data : {
					config_id:config_id,
					shop_id:shop_id
				},
				dataType : 'json',
				success : function(data) {
					
					if (data) {
						if (data.appcode == "1") {
							var token_id=data.token_id
						    window.location.href = 'https://pay.swiftpass.cn/pay/jspay?token_id='+token_id+'&showwxtitle=1';
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