<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<meta   http-equiv= "Pragma"   content= "no-cache" /> 
<meta   http-equiv= "Cache-Control"   content= "no-cache" /> 
<meta   http-equiv= "Expires"   content= "0" /> 
</head>
<body>
 
	<div class="container">
		<div class="content">
		      <div title="基本信息" data-options="closable:false" class="basic-info">
		      	<div class="column"><span class="current">顾客信息</span></div>
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">顾客姓名</td>
							<td class="kv-content">${orderDto.username }</td>
							<td class="kv-label">顾客手机</td>
							<td class="kv-content">${orderDto.mobile}</td>
						</tr>
					</tbody>
				</table>
				<div class="column"><span class="current">购买颜值</span></div>
		      	<table class="kv-table">
					<tbody>
						
						<tr>
							<td class="kv-label">消费内容</td>
							<td class="kv-content">${orderDto.order_content}</td>
							
						</tr>
						<tr>
							<td class="kv-label">支付金额</td>
							<td class="kv-content"><fmt:formatNumber type="number"  value="${orderDto.order_money}" />元</td>
							
						</tr>
						<tr>
							<td class="kv-label">实付金额</td>
							<td class="kv-content" ><fmt:formatNumber type="number"  value="${orderDto.pay_money}" />元</td>
						</tr>
					
					
					</tbody>
				</table>
				<form id="modifyOrderForm" action="${ctx }/shop/orderManage/modifyOrder.jhtml"
				method="post"  autocomplete="off">
		      	<table class="kv-table">
					<tbody>
						 <input type="hidden"  name="order_id" value="${orderDto.order_id}" />
						 <input type="hidden"  name="operateWay" value="${operateWay}" />
						<tr>
							<td class="kv-label">服务员工</td>
							<td class="kv-content"><input  type="text" value="${orderDto.server_user_id }" name="server_user_id" class="easyui-combobox"	 data-options="url:'${ctx}/shop/orderManage/queryShopUser.jhtml',method:'get',valueField:'shop_user_id',textField:'username'" class="easyui-textbox" required="true" data-options="validType:'loginname'" style="width: 250px; height: 30px" ></td>
							<td class="kv-label" rowspan="3">订单备注</td>
							<td class="kv-content"  rowspan="3">
							<input  type="text" prompt="填写订单备注" value="${orderDto.order_remark}"
							 name="order_remark"	class="easyui-textbox"  data-options="validType:'length[0,400]'"
							style="width: 250px; height: 60px" >
							</td>
							
							
						</tr>
						<tr>
							<td class="kv-label">经手员工</td>
							<td class="kv-content"><input  type="text"  name="account" prompt="请输入经手员工账号"   autocomplete="off"	class="easyui-textbox" required="true" style="width: 250px; height: 30px" ></td>
						</tr>
						
						<tr>
							<td class="kv-label">操作密码</td>
							<td class="kv-content" > <input  type="password"  name="password" prompt="请输入对应员工密码"  autocomplete="off"	class="easyui-textbox" required="true" 
							 style="width: 250px; height: 30px" ></td>
						</tr>
					
					
					</tbody>
				</table>
				</form>
		<div  height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton button-complete button-success" dhref="javascript:void(0)"
			    onclick="submitFormData('modifyOrderForm','recordList','modifyOrderWindow')" style="width: 70px">确定</a> &nbsp;
		    <a class="easyui-linkbutton button-complete button-danger"  href="javascript:void(0)"
				onclick="closeWindow('modifyOrderWindow')" style="width: 70px">关闭</a>
		</div>
		</div>
	</div>
	
</body> 
</html>