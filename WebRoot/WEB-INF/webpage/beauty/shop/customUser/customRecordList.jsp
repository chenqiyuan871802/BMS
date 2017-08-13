<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>

<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:80px;padding:2px;background-color: white;"  data-options="region:'north',split:false">
        <form id="doForm" method="post">
        <input type="hidden" name="custom_user_id" value="${customUserPO.custom_user_id}"    />
        <table class="searchContent">
						<tr>
								
								<td width="6%" style="text-align: right">顾客账号：</td>
								<td width="13%" style="text-align: left">
								${customUserPO.mobile }
								</td>
								<td width="6%" style="text-align: right">顾客姓名：</td>
								<td width="13%" style="text-align: left">
								${customUserPO.username }
								</td>
								<td width="6%" style="text-align: right"></td>
								<td width="13%" style="text-align: left">
							
								</td>
								
							 
								
								<td width="30%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('recordList','doForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-refresh"  onclick="$('#doForm').form('clear')">重置</a> 
								</td>
							</tr>
							<tr>
							   <td width="6%" style="text-align: right">订单编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_id"   class="easyui-textbox" prompt="请输入订单编号" style="width: 160px;  " />
								</td>
						    	<td width="6%" style="text-align: right">消费内容：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_content"   class="easyui-textbox" style="width: 160px;  " />
								</td>
								<td width="6%" style="text-align: right">支付方式：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="pay_way"    class="easyui-combobox" editable="false"  data-options="data:pay_wayStore,textField:1,valueField:0" style="width: 160px;  " />
								</td>
								
								
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="recordList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
		            cls:'theme-datagrid', 
				    border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                queryParams : $('#doForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/listCustomRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="order_id"  width="8%"    align="center">订单编号</th>
				<th field="order_content" width="10%"   align="center">消费内容</th>
				<th field="order_money" width="8%"   align="center">价格<br/>（元）</th>
				<th field="pay_money" width="8%" align="center">实付金额<br/>（元）</th>
				<th field="extend_beauty_num" width="8%" align="center">消耗颜值(个)</th>
				<th field="pay_way" formatter="pay_wayFormatter" width="5%" align="center">支付方式</th>
				<th field="pay_time" width="12%" align="center">支付时间</th>
				<th field="server_name" width="8%" align="center">服务员工</th>
				<th field="account" width="8%" align="center">操作员工</th>
				<th field="order_status"   formatter="order_statusFormatter" width="6%" align="center">状态</th>
				
				
			</tr>
		</thead>
	</table>
	
    </div>
    </div>
   
    
</body>


