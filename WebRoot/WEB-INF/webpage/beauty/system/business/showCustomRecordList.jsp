<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="order_type,pay_way,order_source,order_status"/>
<IMS:codeFormatter fields="order_type,pay_way,order_status,order_source"/>
</head>
<script type="text/javascript">
 function downloadData(){
	 $('#queryForm').form('submit', {   
		    url:'${ctx}/system/business/downloadSystemFinance.jhtml'
	  });  
 }
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:65px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <input type="hidden" name="custom_user_id" value="${custom_user_id }"  />
        <table class="searchContent">
				<tr>
								<td width="6%" style="text-align: right">订单编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_id"   class="easyui-textbox"  style="width: 120px;  " />
								</td>
							    <td width="6%" style="text-align: right">消费内容：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_content"   class="easyui-textbox" style="width: 120px;  " />
								</td>
								<td width="6%" style="text-align: right">支付方式：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="pay_way"    class="easyui-combobox" editable="false"  data-options="data:pay_wayStore,textField:1,valueField:0" style="width: 120px;  " />
								</td>
								<td width="6%" style="text-align: right">订单状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_status"    class="easyui-combobox" editable="false"  data-options="data:order_statusStore,textField:1,valueField:0" style="width: 120px;  " />
								</td>
								
								<td width="30%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('recordList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
						    <td width="7%" style="text-align: right">店铺名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="shop_id"   class="easyui-combobox"	 data-options="url:'${ctx}/system/shopSys/queryShop.jhtml',method:'get',valueField:'shop_id',textField:'shop_name'" style="width: 120px; " />
								</td>
								
								
								<td width="6%" style="text-align: right">服务员工：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="server_name"    class="easyui-textbox" style="width: 120px; " />
								</td>
								<td width="6%" style="text-align: right">最后操作员工：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="handle_name"    class="easyui-textbox" style="width: 120px; " />
								</td>
									<td width="6%" style="text-align: right">完成日期：</td>
								<td width="13%"  style="text-align: left">
								<input type="text"  name="finish_time_begin"   class="easyui-datebox" editable="false" style="width:110px" />
							      至：<input type="text" name="finish_time_end"  class="easyui-datebox" editable="false" style="width:110px" />
								</td>
					</tr>
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="recordList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
		           
				    border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                toolbar:'#toolbar',
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/business/listAllRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="order_id"  width="8%"    align="center">订单编号</th>
				<th field="order_content" width="12%"   align="center">消费内容</th>
				<th field="order_money" width="6%"   align="center">价格<br/>（元）</th>
				<th field="pay_way" formatter="pay_wayFormatter" width="7%" align="center">支付方式</th>
				<th field="pay_money" width="7%" align="center">实付金额<br/>（元）</th>
				<th field="extend_beauty_num" width="7%" align="center">消耗颜值</th>
				<th field="shop_id" width="8%" align="center">店铺编号</th>
				<th field="shop_name" width="10%" align="center">店铺名称</th>
				<th field="server_name" width="8%" align="center">服务员工</th>
				<th field="handle_name" width="8%" align="center">最后操作员工</th>
				<th field="pay_time" width="12%" align="center">支付时间</th>
				<th field="finish_time" width="12%" align="center">完成时间</th>
				<th field="order_status"   formatter="order_statusFormatter" width="8%" align="center">状态</th>
				
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
	<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true"
			onclick="showGridData('showOrderDetailWindow','recordList','order_id','${ctx}/system/business/showOrderDetail.jhtml','请选择你要查看信息');;">查看详情</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="excel" plain="true"
			onclick="downloadData()">导出Excel</a>
	  
	</div>
    </div>
    </div>
   <div id="showOrderDetailWindow" class="easyui-window" title="查看详情"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:1000px; height: 480px; background-color: #FFFFFF"></div>

   
    
</body>


