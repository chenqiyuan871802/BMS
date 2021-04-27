<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="order_type,pay_way,order_source"/>
<IMS:codeStoreFilter filter="1,2,3" field="order_status"/>
<IMS:codeFormatter fields="order_type,pay_way,order_status,order_source"/>
</head>

<body style="margin: 0; padding: 0" >
<script type="text/javascript">
 function manualFormatter(value, row, index){
	 var order_money=row.order_money;
	 var pay_money=row.pay_money;
	 if(order_money==pay_money){
		 return 'X'
	 }else{
		 return 'Y'
	 }
 }
</script>
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:50px;padding:2px;background-color: white;"  data-options="region:'north',split:false">
        <form id="doForm" method="post">
        <input type="hidden"  name="shop_user_id" value="${shop_user_id}" />
        <input type="hidden"  name="total_month" value="${total_month}" />
         <table class="searchContent">
						<tr>
								<td width="6%" style="text-align: right">员工姓名：</td>
								<td width="13%" style="text-align: left">
								 ${shopUserPO.username }
								</td>
								<td width="6%" style="text-align: right">订单编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_id"   class="easyui-textbox" prompt="请输入订单编号" style="width: 160px;  " />
								</td>
								<td width="6%" style="text-align: right">顾客账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile" prompt="请输入顾客手机号"    class="easyui-textbox" style="width: 160px; " />
								</td>
								
								
								<td width="30%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('dataList','doForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-refresh"  onclick="$('#doForm').form('reset')">重置</a> 
								</td>
							</tr>
							
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="dataList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/listShopUserServerOrder.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="order_id"  width="6%"    align="center">订单编号</th>
				<th field="mobile" width="8%"   align="center">顾客账号 </th>
				<th field="order_content" width="8%"   align="center">消费内容</th>
				<th field="order_remark" width="15%"   align="center">订单备注</th>
				<th field="order_money" width="4%"   align="center">价格<br/>（元）</th>
				<th field="pay_money" width="5%" align="center">实付金额<br/>（元）</th>
				<th field="extend_beauty_num" width="5%" align="center">消耗颜值</th>
				<th field="pay_way" formatter="pay_wayFormatter" width="5%" align="center">支付方式</th>
				<th field="pay_time" width="10%" align="center">支付时间</th>
				<th field="cash_income" width="6%" align="center">现金收入</th>
				<th field="extend_income" width="6%" align="center">消耗收入</th>
				<th field="manual" formatter="manualFormatter"  width="4%" align="center">手工费</th>
				<th field="server_name" width="6%" align="center">服务员工</th>
				<th field="account" width="6%" align="center">操作员工</th>

			</tr>
		</thead>
	</table>
	
    </div>
    </div>
   <div id="showOrderDetailWindow" class="easyui-window" title="订单详情"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:800px; height: 440px; background-color: #FFFFFF"></div>
   <div id="completeOrderWindow" class="easyui-window" title="完成订单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:800px; height: 440px; background-color: #FFFFFF"></div>
   <div id="modifyOrderWindow" class="easyui-window" title="修改订单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:800px; height: 440px; background-color: #FFFFFF"></div>
	<div id="modifyShopUserWindow" class="easyui-window" title="修改员工信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
   <div id="modifyPasswordWindow" class="easyui-window" title="密码重置"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 520px; height:180px; background-color: #FFFFFF"></div>
   
    
</body>


