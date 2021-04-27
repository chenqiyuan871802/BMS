<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="order_type,pay_way,order_source"/>
<IMS:codeStoreFilter filter="1,2,3" field="order_status"/>
<IMS:codeFormatter fields="order_type,pay_way,order_status,order_source"/>
</head>
<script type="text/javascript">
var post_code='${shopUserInfo.post_code }'
	var user_type='${shopUserInfo.user_type }'
//操作处理函数
function handleFunction(value, row, index){
	    var order_status=row.order_status;
	    var order_id=row.order_id;
	    var order_type=row.order_type;
	    var str =''
	    var handleStr="showOrderDetail("+order_id+","+order_type+","+order_status+")";
	    var modifyStr="modifyOrder("+order_id+","+order_type+","+order_status+")";
	    var completeStr="completeOrder("+order_id+","+order_type+","+order_status+")";
	    if(order_status ==4){
	    	
	    	str+="<a href='#' onclick='"+completeStr+"' class='button-complete button-success'>完成</a>&nbsp;"
	    }
	    str+="<a href='#' onclick='"+handleStr+"' class='button-detail button-default'>详情</a>&nbsp;"
	    if(post_code=='01'||user_type=='1'){
    		var modify_status=row.modify_status;
    		if(modify_status!='1'&&order_status ==5){
    			str+="<a href='#' onclick='"+modifyStr+"' class='button-edit button-warning'>修改</a>" 
    		}
    	}
		return str;
}
function showOrderDetail(order_id,order_type,order_status){
	 showWindow('showOrderDetailWindow','${ctx}/shop/orderManage/showOrderDetail.jhtml?order_id='+order_id);
}
function modifyOrder(order_id,order_type,order_status){
	 showWindow('modifyOrderWindow','${ctx}/shop/orderManage/goModifyOrder.jhtml?operateWay=1&order_id='+order_id);
}
function completeOrder(order_id,order_type,order_status){
	 showWindow('modifyOrderWindow','${ctx}/shop/orderManage/goModifyOrder.jhtml?operateWay=2&order_id='+order_id);
}
function  loadSuccess(){
		$('.button-complete').linkbutton({ 
		});
		$('.button-detail').linkbutton({ 
		});
		$('.button-edit').linkbutton({ 
		});
}

</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:80px;padding:2px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <input type="hidden"  name="order_type" value="${order_type}" />
         <table class="searchContent">
						<tr>
								<td width="6%" style="text-align: right">订单编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_id"   class="easyui-textbox" prompt="请输入订单编号" style="width: 160px;  " />
								</td>
								<td width="6%" style="text-align: right">顾客账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile" prompt="请输入顾客手机号"    class="easyui-textbox" style="width: 160px; " />
								</td>
								<td width="6%" style="text-align: right">顾客姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username" prompt="请输入顾客姓名"    class="easyui-textbox" style="width: 160px; " />
								</td>
								<td width="6%" style="text-align: right">订单状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_status"    class="easyui-combobox" editable="false"  data-options="data:order_statusStore,textField:1,valueField:0" style="width: 160px;  " />
								</td>
								
								<td width="30%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('recordList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
						    	<td width="6%" style="text-align: right">消费内容：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_content"   class="easyui-textbox" style="width: 160px;  " />
								</td>
								<td width="6%" style="text-align: right">支付方式：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="pay_way"    class="easyui-combobox" editable="false"  data-options="data:pay_wayStore,textField:1,valueField:0" style="width: 160px;  " />
								</td>
								<td width="6%" style="text-align: right">操作员工：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="account" prompt="请输入员工账号"    class="easyui-textbox" style="width: 160px; " />
								</td>
								
								<td width="6%" style="text-align: right">支付时间：</td>
								<td width="13%" style="text-align: left">
								<input type="text"  name="pay_time_begin"   showSeconds="false" class="easyui-datetimebox" editable="false" style="width:160px" />
							      至：<input type="text" name="pay_time_end" showSeconds="false" class="easyui-datetimebox" editable="false" style="width:160px" />
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
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/listBusinessRecord.jhtml',
	                onLoadSuccess:loadSuccess,
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="order_id"  width="8%"    align="center">订单编号</th>
				<th field="mobile" width="7%"   align="center">顾客账号 </th>
				<th field="username" width="5%"   align="center">顾客姓名</th>
				<th field="order_content" width="7%"   align="center">消费内容</th>
				<th field="order_remark" width="15%"   align="center">订单备注</th>
				<th field="order_type" formatter="order_typeFormatter" width="6%"   align="center">消费类型</th>
				<th field="order_money" width="4%"   align="center">价格<br/>（元）</th>
				<th field="pay_money" width="5%" align="center">实付金额<br/>（元）</th>
				<th field="extend_beauty_num" width="5%" align="center">消耗颜值</th>
				<th field="pay_way" formatter="pay_wayFormatter" width="5%" align="center">支付方式</th>
				<th field="pay_time" width="9%" align="center">支付时间</th>
				<th field="short_name" width="8%" align="center">店铺简称</th>
				<th field="server_name" width="5%" align="center">服务员工</th>
				<th field="account" width="6%" align="center">操作员工</th>
				<th field="order_status"   formatter="order_statusFormatter" width="4%" align="center">状态</th>
				<th  field="operate"  formatter="handleFunction"  width="12%" align="center">操作</th>
				
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


