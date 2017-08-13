<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<div class="content">
			<div title="基本信息" data-options="closable:false" id="orderTable"
				class="basic-info">

				<input type="hidden" id="out_trade_no" />

				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">订单编号</td>
							<td class="kv-content">${orderDto.order_id}</td>
						</tr>
						<tr>
							<td class="kv-label">订单状态</td>
							<td class="kv-content"><IMS:codeOut
									codeKey="${orderDto.order_status}" field="order_status" /></td>
						</tr>
						<tr>
							<td class="kv-label">顾客账号</td>
							<td class="kv-content">${orderDto.mobile}</td>
						</tr>

						<tr>
							<td class="kv-label">护理项目</td>
							<td class="kv-content">${orderDto.order_content}</td>
						</tr>
						<tr>
							<td class="kv-label">护理价格</td>
							<td class="kv-content"><fmt:formatNumber
									value="${orderDto.order_money }" type="number" />元</td>
						</tr>
						<tr>
							<td class="kv-label">支付方式</td>
							<td class="kv-content"><img
								onclick="scanCodePay(${orderDto.order_id},'1')"
								src="${ctx }/static/shop/images/wechatpay.jpg" title="微信支付" /><img
								onclick="scanCodePay(${orderDto.order_id},'2')"
								style="margin: 0 0 0 50px"
								src="${ctx }/static/shop/images/alipay.jpg" title="支付宝支付" /></td>
						</tr>
					</tbody>
				</table>



			</div>
			<div id="showScanCodeDive" style="text-align: center; display: none">
				<span id="showText"
					style="display: none; font-size: 16; color: green"></span>
				<span id="showTime" style="font-size: 16; color: red"></span> <img
					id="showScanCodeImg" />
			</div>
			<div id="scanCodeSuccess" style="text-align: center; display: none">
			 <img src="${ctx}/static/shop/images/success.png" />
			</div>
			<div id="scanCodeFail" style="text-align: center; display: none">
			 <img src="${ctx}/static/shop/images/error.png" />
			</div>

			<script type="text/javascript">
	//微信支付
	 function scanCodePay(order_id,pay_way){
		var pay_type='支付宝'
		if(pay_way=='1'){
			pay_type='微信'
		}
		 $.messager.confirm('确认', '你确认使用'+pay_type+'进行订单支付吗 ？',
	 				function(r) {
	 			    if(r){
	 			    	
	 			    	$.messager.progress({
	 						title : '信息操作',
	 						text : '正在生成支付二维码，请耐心等待...'
	 					});
	 					$.ajax({
	 						type : 'post',
	 						url  :'${ctx}/shop/orderManage/scanCodePay.jhtml',
	 						data : {
	 						  'order_id' :order_id,
	 						   'pay_way':pay_way
	 						},
	 						dataType : 'json',
	 						success : function(data) {
	 							$.messager.progress('close');
	 							if (data) {
	 								if (data.appcode == "1") {
	 									if(pay_way=='1'){
	 										$("#showText").html('订单提交成功，请在3分钟内使用微信扫码完成支付，否则订单会自动取消')
	 									}else{
	 										$("#showText").html('订单提交成功，请在3分钟内使用支付宝扫码完成支付，否则订单会自动取消')
	 									}
	 									$("#out_trade_no").val(data.resultMap.out_trade_no)
	 									$("#orderTable").hide()
	 									$("#showScanCodeDive").show();
	 									$("#showText").show();
	 									$("#showScanCodeImg").attr('src',data.resultMap.code_img_url); 
	 									timeFunc();
	 									timeOrderPay();
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
	 							$.messager.progress('close');
	 							$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
	 						}
	 					})
	 			    	
	 			    }
		 })
	 }
	 var timeInterval;
	 var time=180;
	 function timeFunc(){
		 timeInterval=window.clearInterval(timeInterval)
		 timeInterval=self.setInterval("showSecond()",1000);
		
		
	 }
	 function showSecond(){
		  time-=1;
		  if(time==0){
			  timeInterval=window.clearInterval(timeInterval)
			  closeWindow('scanCodeOrderWindow')
			  
		  }else{
			  var minute=parseInt(time/60);
			  var second=time%60;
			  var msg='还剩余支付时间'
			  if(minute>0){
				  msg+=minute+'分钟';
			  }
			  if(second>0){
				  msg+=second+'秒';
			  }
			  $("#showTime").html(msg);
		  }
		
		 
	 }
	 var timerOrder;
	 var orderTime=180;
	 function timeOrderPay(){
		 timeOrderPay=window.clearInterval(timeOrderPay)
		 timeOrderPay=self.setInterval("checkOrderPay()",5000);
	 }
	//检查订单支付情况
	 function checkOrderPay(order_id){
		orderTime-=5;
		if(orderTime<=0){
			  timeOrderPay=window.clearInterval(timeOrderPay)
			  closeWindow('scanCodeOrderWindow')
			  return ;
		}
		var out_trade_no= $("#out_trade_no").val();
	 					$.ajax({
	 						type : 'post',
	 						url  :'${ctx}/shop/orderManage/checkOrderPay.jhtml',
	 						data : {
	 						  'out_trade_no' :out_trade_no
	 						},
	 						dataType : 'json',
	 						success : function(data) {
	 							$.messager.progress('close');
	 							if (data) {
	 								if (data.appcode == "1") { //支付成功
	 									timeOrderPay=window.clearInterval(timeOrderPay)
	 									timeInterval=window.clearInterval(timeInterval)
	 									 $("#showScanCodeDive").hide();
	 									 $("#scanCodeSuccess").show();
	 									setTimeout("closeScanCodeWindow();",2000); 
	 									  return ;
	 								}
	 								if(data.appcode == "-1"){
	 									timeOrderPay=window.clearInterval(timeOrderPay);
	 									timeInterval=window.clearInterval(timeInterval);
	 									$("#showScanCodeDive").hide();
	 									$("#scanCodeFail").show();
	 									setTimeout("closeScanCodeWindow();",2000); 
	 									return ;
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
	
	function closeScanCodeWindow(){
		
		closeWindow('scanCodeOrderWindow')
	}
	</script>
</body>
</html>