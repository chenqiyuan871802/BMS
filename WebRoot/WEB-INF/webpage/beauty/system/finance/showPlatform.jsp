<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<IMS:codeFormatter fields="pay_way"/>
<style type="text/css">
.queryDiv {
	width: 94%;
	margin: 0 auto;
	height: auto;
	margin-top: 5px;
	margin-left: 45px;
	text-align:left;
	
	
}
.tabcount {
	width: 94%;
	margin: 0 auto;
	height: auto;
	margin-top: 5px;
	border-radius: 5px;
	text-align:left;
	
	
}


.tabcount a {
	display: inline-block;
	width: 20%;
	height: 80px;
	color: #fff;
	text-align: center;
	margin: 0px 10px;
	overflow:hidden;
}

.tabcount a span {
	display: block;
	font-size:20px;
	
	font-weight:bold;
	
}

.spancount {
     padding:10px 0px;
	font-size: 15px !important;
}
.bicon1{
  background-color:#44b7ee;
}
.bicon2{
  background-color:#4fd5d7;
}
.bicon3{
  background-color:#a2d74f;
}
.bicon4{
  background-color:#efa76b;
}

</style>
<script type="text/javascript">
$(function(){
	queryPlatformSum();
})
  //平台统计
  function queryPlatformSum(){
	  var create_time_begin=$("#create_time_begin").datebox("getValue");
	  var create_time_end=$("#create_time_end").datebox("getValue");
	  var time_type=$("#time_type").combobox("getValue");
	  var shop_id=$("#shop_id").val();
		$.messager.progress({
			title : '信息查询',
			text : '数据正在查询中，请耐心等待...'
		});
	  $.ajax({
			type : 'post',
			url : '${ctx}/system/finance/queryPlatformSum.jhtml',
			data : {
				create_time_begin:create_time_begin,
				create_time_end:create_time_end,
				shop_id:shop_id,
				time_type:time_type
			},
			dataType : 'json',
			success : function(data) {
				
				if (data) {
					
					if(data.total_money==undefined){
						$("#total_money").html(0)
					}else{
						$("#total_money").html(data.total_money)
					}
					
					if(data.project_total_money==undefined){
						$("#project_total_money").html(0)
					}else{
						$("#project_total_money").html(data.project_total_money)
					}
					
					if(data.bag_total_money==undefined){
						$("#bag_total_money").html(0)
					}else{
						$("#bag_total_money").html(data.bag_total_money)
					}
					
					if(data.beauty_total_money==undefined){
						$("#beauty_total_money").html(0)
					}else{
						$("#beauty_total_money").html(data.beauty_total_money)
					}
					if(data.total_extend_money==undefined){
						$("#total_extend_money").html(0)
					}else{
						$("#total_extend_money").html(data.total_extend_money)
					}
					
					if(data.project_extend_money==undefined){
						$("#project_extend_money").html(0)
					}else{
						$("#project_extend_money").html(data.project_extend_money)
					}
					
					if(data.bag_extend_money==undefined){
						$("#bag_extend_money").html(0)
					}else{
						$("#bag_extend_money").html(data.bag_extend_money)
					}
					if(data.beauty_extend_money==undefined){
						$("#beauty_extend_money").html(0)
					}else{
						$("#beauty_extend_money").html(data.beauty_extend_money)
					}
					if(data.subscribe_total_money==undefined){
						$("#subscribe_total_money").html(0)
					}else{
						$("#subscribe_total_money").html(data.subscribe_total_money)
					}
					
					
					
				
					
				} else {
					$.messager.alert('错误信息', '查询统计失败', 'error');
				}
			},
			error : function() {
				$.messager.alert('错误信息', '查询统计失败', 'error');
			}
			
		})
		$.messager.progress('close');
	  doQuery('recordList','doForm')
  }
  function handleFunc(newValue,oldValue){
	  $("#create_time_begin").datebox('clear');
	  $("#create_time_end").datebox('clear');
	  queryPlatformSum();
  }
  function downloadData(){
		 $('#doForm').form('submit', {   
			    url:'${ctx}/system/business/downloadShopFinance.jhtml'
		  });  
	 }
</script>
<body style="margin: 0; padding: 0" >
<div style="height:750px">
<div class="easyui-layout"  data-options="fit:true" >
<div  style="height:305px;background-color: white;"  data-options="region:'north',split:false">
   <form id="doForm" method="post">
    <input type="hidden" name="shop_id" id="shop_id" value="${shop_id }"/>
        <table class="searchContent">
					<tr>
						<tr>
								
								<td width="7%" style="text-align: right">时间：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="time_type"  id="time_type"   class="easyui-combobox" onchange="handlerFunc()" editable="false" 
								 data-options="data:[['1','上个月'],['3','前三个月'],['6','上半年']],textField:1,valueField:0,onChange:handleFunc" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">选择时间：</td>
								<td width="13%" style="text-align: left">
								<input type="text"  name="create_time_begin" id="create_time_begin" value="${month_first_day}"    class="easyui-datebox" editable="false" style="width:100px" />
								 至：<input type="text" name="create_time_end" id="create_time_end"  value="${current_day}"  class="easyui-datebox" editable="false" style="width:100px" />
								</td>
								
							
								<td width="50%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="queryPlatformSum()">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#doForm').form('reset')">重置</a> 
								</td>
							</tr>
					
				</table>
        </form>
  <div class="tabcount">
		<a href="javascript:void(0)" class="bicon1"> 
		<span  class="spancount">总现金收入（元）</span><span id="total_money"></span>
		</a> 
		<a href="javascript:void(0)" class="bicon2"> 
		<span  class="spancount">项目现金收入（元）</span><span id="project_total_money"></span>
		</a> 
		<a href="javascript:void(0)" class="bicon3"> 
		<span  class="spancount">礼包现金收入（元）</span><span id="bag_total_money"></span>
		</a> 
		<a href="javascript:void(0)" class="bicon4"> 
		<span  class="spancount">颜值现金收入（元）</span><span id="beauty_total_money"> </span>
		</a> 
		
	</div>
  <div class="tabcount">
		<a href="javascript:void(0)" class="bicon1"> 
		<span  class="spancount">总消耗收入（元）</span><span id="total_extend_money"></span>
		</a> 
		<a href="javascript:void(0)" class="bicon2"> 
		<span  class="spancount">礼包消耗收入（元）</span><span id="bag_extend_money"></span>
		</a> 
		<a href="javascript:void(0)" class="bicon3"> 
		<span  class="spancount">项目消耗收入（元）</span><span id="project_extend_money"></span>
		</a> 
		<a href="javascript:void(0)" class="bicon4"> 
		<span  class="spancount">颜值消耗收入（元）</span><span id="beauty_extend_money"></span>
		</a> 
		
	</div>
  <div class="tabcount">
		<a href="javascript:void(0)" class="bicon1"> 
		<span  class="spancount">总预定收入（元）</span><span id="subscribe_total_money"></span>
		</a> 
		
	</div>
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
	                toolbar:'#toolbar1',
	                queryParams : $('#doForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/business/listShopRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="order_id"  width="8%"    align="center">订单编号</th>
				<th field="pay_time" width="12%" align="center">交易时间</th>
				<th field="mobile" width="8%"   align="center">顾客账号 </th>
				<th field="order_content" width="15%" formatter="formatCellTooltip"   align="center">交易内容</th>
				<th field="order_money" width="6%"   align="center">价格<br/>（元）</th>
				<th field="pay_money" width="7%" align="center">实付金额<br/>（元）</th>
				<th field="extend_beauty_num" width="7%" align="center">消耗颜值</th>
				<th field="pay_way" formatter="pay_wayFormatter" width="7%" align="center">支付方式</th>
			    <th field="cash_income" width="7%" align="center">现金收入</th>
			    <th field="extend_income" width="7%" align="center">消耗收入</th>
				<th field="server_name" width="8%" align="center">服务员工</th>
				<th field="handle_name" width="8%" align="center">经手员工</th>
				
			
				
				
			</tr>
		</thead>
	</table>
	<div id="toolbar1" style="padding: 2px;">
        
	<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true"
			onclick="showGridData('showOrderDetailWindow','recordList','order_id','${ctx}/system/business/showOrderDetail.jhtml','请选择你要查看信息');;">查看详情</a>
	<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="excel" plain="true"
			onclick="downloadData()">导出Excel</a>
	  
	</div>
    </div>
    </div>
    </div>
    
    <div id="showOrderDetailWindow" class="easyui-window" title="查看详情"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:1000px; height: 480px; background-color: #FFFFFF"></div>
</body>


