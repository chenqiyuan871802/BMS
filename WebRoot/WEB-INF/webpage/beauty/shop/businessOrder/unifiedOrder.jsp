<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<div class="content">
			<div data-options="closable:false" id="unifiedTable"
				class="basic-info">

				<input type="hidden" id="out_trade_no" />
				<input type="hidden" id="scan_order_id" value="${orderDto.order_id}" />

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
							<td class="kv-label">支付授权码(18位)</td>
							<td class="kv-content"><input type="text" id="auth_code" onchange="unifiedPay()"   
								style="width: 250px; height: 30px"></td>
						</tr>
					</tbody>
				</table>
               <!-- 
				<div height="35px"
					style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
					<a class="easyui-linkbutton button-complete button-success"
						href="javascript:void(0)"
						onclick="unifiedPay(${orderDto.order_id})" style="width: 70px">确认支付</a>
					&nbsp;

				</div>
				 -->
				</div>
				<div id="unifiedSuccess" style="text-align: center; display: none">
					<img src="${ctx}/static/shop/images/success.png" />
				</div>
				<div id="unifiedFail" style="text-align: center; display: none">
				<span id="showErrorMsg" style="font-size: 16; color: red"></span>
					<img src="${ctx}/static/shop/images/error.png" />
				</div>

<script type="text/javascript">
  $(function() {
	  $('#auth_code').focus();
	  
     })
	//微信支付
	 function unifiedPay(){
		 var auth_code=$("#auth_code").val();
		
		 if(auth_code==''||auth_code==undefined){
			 $.messager.alert('警告信息', '请填写支付授权码', 'warning');
			 return ;
		 }
		 var order_id=$("#scan_order_id").val();
		 $.messager.progress({
				title : '信息操作',
				text : '支付正在进行中，请耐心等待...'
			});
			$.ajax({
				type : 'post',
				url  :'${ctx}/shop/orderManage/unifiedPay.jhtml',
				data : {
				  'order_id' :order_id,
				   'auth_code':auth_code
				},
				dataType : 'json',
				success : function(data) {
					$.messager.progress('close');
					if (data) {
						$("#unifiedTable").hide()
						if (data.appcode == "1") {
							 $("#unifiedSuccess").show();
							 
						} else {
							 $("#unifiedFail").show();
							 $("#showErrorMsg").html(data.appmsg)
						}
						setTimeout("closeUnifiedWindow();",5000); 
						 return ;
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
	
	function closeUnifiedWindow(){
		
		closeWindow('unifiedOrderWindow')
	}
	</script>
</body>
</html>