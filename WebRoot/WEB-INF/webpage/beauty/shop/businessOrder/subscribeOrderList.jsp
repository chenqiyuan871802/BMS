<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="order_type,pay_way,order_source"/>
<IMS:codeStoreFilter filter="3,4,5,6,7" field="order_status"/>
<IMS:codeFormatter fields="order_type,pay_way,order_status,order_source"/>
</head>
<script type="text/javascript">
//操作处理函数
function handleFunction(value, row, index){
	    var order_status=row.order_status;
	    var order_id=row.order_id;
	    var order_type=row.order_type;
	    var str =''
	    var handleStr="payOrder("+order_id+","+order_type+","+order_status+")";
	    var showStr="showOrderDetail("+order_id+","+order_type+","+order_status+")";
	    var modifyStr="modifyOrder("+order_id+","+order_type+","+order_status+")";
	    var startStr="startOrder("+order_id+","+order_type+","+order_status+")";
	    var scanCodeStr="scanCodeOrder("+order_id+","+order_type+","+order_status+")";
	    var unifiedStr="unifiedOrder("+order_id+","+order_type+","+order_status+")";
	    var cancelStr="cancelOrder("+order_id+","+order_type+","+order_status+")";
	    if(order_status =='1'){
	    	str+="<a href='#' onclick='"+cancelStr+"' class='button-complete button-danger'>撤销</a>&nbsp;"
	    	str+="<a href='#' onclick='"+startStr+"' class='button-complete button-success'>开始</a>&nbsp;"
	    	
	    }else{
	        str+="<a href='#' onclick='"+handleStr+"' class='button-detail button-default'>收款</a>&nbsp;"
	       	str+="<a href='#' onclick='"+scanCodeStr+"' class='button-edit button-warning'>扫码</a>&nbsp;"
	       	str+="<a href='#' onclick='"+unifiedStr+"' class='button-edit button-warning'>扫码枪扫码</a>&nbsp;"
	    }
	    str+="<a href='#' onclick='"+showStr+"' class='button-detail button-default'>详情</a>&nbsp;"
		return str;
}
/**
 * 
 */
function payOrder(order_id,order_type,order_status){
	
	 showWindow('payOrderWindow','${ctx}/shop/orderManage/goPayOrder.jhtml?order_id='+order_id);
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
//开始订单
function startOrder(order_id,order_type,order_status){
	 showWindow('startOrderWindow','${ctx}/shop/orderManage/goStartOrder.jhtml?order_id='+order_id);
}
//扫描支付
function scanCodeOrder(order_id,order_type,order_status){
	
	 showWindow('scanCodeOrderWindow','${ctx}/shop/orderManage/goScanCodeOrder.jhtml?order_id='+order_id);
}
//扫描支付
function unifiedOrder(order_id,order_type,order_status){
	
	 showWindow('unifiedOrderWindow','${ctx}/shop/orderManage/goScanCodeOrder.jhtml?returnWay=1&order_id='+order_id);
}
//撤销订单
function cancelOrder(order_id,order_type,order_status){
	$.messager.confirm('确认', '你确认撤销该订单嘛？', function(r) {
		if (r) {
			$.messager.progress({
				title : '信息操作',
				text : '数据正在保存中，请耐心等待...'
			});
			$.ajax({
				type : 'post',
				url  :'${ctx}/shop/orderManage/cancelOrder.jhtml',
				data : {
				  'order_id' :order_id
				},
				dataType : 'json',
				success : function(data) {
					$.messager.progress('close');
					if (data) {
						if (data.appcode == "1") {
							showMsg('提示', data.appmsg);
							$('#recordList').datagrid({});
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
	});
}
function  loadSuccess(){
		$('.button-complete').linkbutton({ 
		});
		$('.button-detail').linkbutton({ 
		});
		$('.button-edit').linkbutton({ 
		});
}
//按钮处理事件
function dealOperate(btnValue){
	
	if(btnValue=='1'){
		$('#query_date').datebox('setValue', '');	
	}else if(btnValue=='2'){
		$('#query_date').datebox('setValue', getDateStr(0));	
	}else if(btnValue=='3'){
		$('#query_date').datebox('setValue', getDateStr(1));	
	}else{
		$('#query_date').datebox('setValue', getDateStr(2));	
	}
	
	doQuery('recordList','queryForm');
	
}
/**
 * 获取日期
 */
function getDateStr(addDayCount) { 
	var dd = new Date(); 
	dd.setDate(dd.getDate()+addDayCount);//获取addDayCount天后的日期 
	var y = dd.getFullYear(); 
	var m = dd.getMonth()+1;//获取当前月份的日期 
	var d = dd.getDate(); 
	return y+"-"+m+"-"+d; 
} 
function scanCodeColseFunc(){
	timeOrderPay=window.clearInterval(timeOrderPay)
	timeInterval=window.clearInterval(timeInterval)
	doQuery('recordList','queryForm');
}
function unifiedColseFunc(){
	
	doQuery('recordList','queryForm');
}
var orderTime=180;
var orderNum=0;
function refreshOrder(){
	orderNum++;
	if(orderNum==orderTime){
		orderNum=0;
		doQuery('recordList','queryForm')
	}
 orederTimeObj=setTimeout(function(){ refreshOrder()},1000);
	
}
refreshOrder();
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:80px;padding:2px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
         
        <table class="searchContent">
						<tr>
								<td width="6%" style="text-align: right">订单编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_id"   class="easyui-textbox" prompt="请输入订单编号" style="width: 150px;  " />
								</td>
								<td width="6%" style="text-align: right">顾客账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile" prompt="请输入顾客手机号"    class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="6%" style="text-align: right">顾客姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username" prompt="请输入顾客姓名"    class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="6%" style="text-align: right">订单状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_status"    class="easyui-combobox" editable="false"  data-options="data:order_statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								<td width="30%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('recordList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-refresh" onclick="$('#queryForm').form('clear')">重置</a> 
								</td>
							</tr>
							<tr>
						    	<td width="6%" style="text-align: right">护理项目：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_content"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="6%" style="text-align: right">按时间分类：</td>
								<td width="13%" colspan="3"  style="text-align: left">
								<input type="radio" name="timeCatalog"  value="1"  onclick="dealOperate(this.value)"/>全部</span>
								<input type="radio" name="timeCatalog"  value="2"  checked onclick="dealOperate(this.value)"/>今天</span>
								<input type="radio" name="timeCatalog"  value="3"  onclick="dealOperate(this.value)"/>明天</span>
								<input type="radio" name="timeCatalog"  value="4"  onclick="dealOperate(this.value)"/>后天</span>
								
								</td>
								
								<td width="6%" style="text-align: right">预约日期：</td>
								<td width="13%" style="text-align: left">
								<input type="text"  name="query_date" id="query_date"  value="${query_date}"   class="easyui-datebox" editable="false" style="width:150px" />
							      
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
	                 toolbar:'#tool',
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/listSubscribeOrder.jhtml',
	                 onLoadSuccess:loadSuccess,
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="order_id"  width="8%"    align="center">订单编号</th>
				<th field="mobile" width="7%"   align="center">顾客账号 </th>
				<th field="username" width="5%"   align="center">顾客姓名</th>
				<th field="order_content" formatter="formatCellTooltip"  width="8%"   align="center">护理项目</th>
				<th field="order_type" formatter="order_typeFormatter" width="6%"   align="center">消费类型</th>
				<th field="order_money" width="5%"   align="center">价格（元）</th>
				<th field="deposit_money" width="5%" align="center">定金（元）</th>
				<th field="subscribe_time" width="9%" align="center">预约时间</th>
				<th field="short_name" formatter="formatCellTooltip" width="8%" align="center">店铺简称</th>
				<th field="order_status"   formatter="order_statusFormatter" width="5%" align="center">状态</th>
				<th  field="operate"  formatter="handleFunction"  width="19%" align="center">操作</th>
				
			</tr>
		</thead>
	</table>
	<div id="tool" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="icon-add"
			plain="true"
			onclick="showWindow('addSubscribeWindow','${ctx}/shop/orderManage/goAddSubscribe.jhtml');">商家代预约</a> 
		
	  
	</div>
    </div>
    </div>
   <div id="showOrderDetailWindow" class="easyui-window" title="订单信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:900px; height: 480px; background-color: #FFFFFF"></div>
	<div id="startOrderWindow" class="easyui-window" title="开始订单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width: 550px; height: 350px; background-color: #FFFFFF"></div>
	<div id="scanCodeOrderWindow" class="easyui-window" title="扫猫支付"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,onBeforeClose:scanCodeColseFunc,cls:'theme-panel-orange'"
		style="width: 550px; height: 400px; background-color: #FFFFFF"></div>
	<div id="payOrderWindow" class="easyui-window" title="收款"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width: 750px; height: 400px; background-color: #FFFFFF"></div>
	<div id="unifiedOrderWindow" class="easyui-window" title="扫描枪扫猫支付"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,onBeforeClose:unifiedColseFunc,cls:'theme-panel-orange'"
		style="width: 550px; height: 400px; background-color: #FFFFFF"></div>
	<div id="addSubscribeWindow" class="easyui-window" title="商家代预约"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width: 600px; height: 380px; background-color: #FFFFFF"></div>

   <div id="showNurseProjectListWindow" class="easyui-window" title="项目列表"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:700px; height: 480px; background-color: #FFFFFF"></div>
   <div id="showPayProjectWindow" class="easyui-window" title="支付项目列表"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:500px; height: 350px; background-color: #FFFFFF"></div>
    
</body>


