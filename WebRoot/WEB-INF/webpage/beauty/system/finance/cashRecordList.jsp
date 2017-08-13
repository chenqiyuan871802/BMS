<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<IMS:codeStore fields="cash_record_type"/>
<IMS:codeStoreFilter filter="3,4" field="pay_way"/>
<IMS:codeFormatter fields="cash_record_type,pay_way"/>
<script type="text/javascript">
 function  beautyFormatter(value){
	 if(IMSUtils.isNotEmpty(value)){
		 
		 return '-'+value;
	 }
	 return ''
		 
 }
 function  moneyFormatter(value, row, index){
	  var cash_type=row.cash_type;
	  if(cash_type=='2'){
		  return '-'+value;
		  
	  }else{
		  return '+'+value;
	  }
	
		 
 }
 function downloadData(){
	 $('#queryForm').form('submit', {   
		    url:'${ctx}/system/finance/downloadCashRecord.jhtml'
	  });  
 }
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:70px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								
								<td width="7%" style="text-align: right">订单编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="order_id"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">顾客账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">顾客姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 150px; " />
								</td>
							
								<td width="50%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('dataList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
						<tr>
								
								<td width="7%" style="text-align: right">支付方式：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="pay_way"    class="easyui-combobox" editable="false"  data-options="data:pay_wayStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">分类：</td>
								<td width="13%" style="text-align: left">
			                    <input type="text" name="record_type"    class="easyui-combobox" editable="false"  
			                    data-options="data:cash_record_typeStore,textField:1,valueField:0" style="width: 150px;  " />

								</td>
								<td width="7%" style="text-align: right">支付时间：</td>
								<td width="13%" style="text-align: left">
								<input type="text"  name="pay_time_begin"   class="easyui-datebox" editable="false" style="width:100px" />
							      至：<input type="text" name="pay_time_end"  class="easyui-datebox" editable="false" style="width:100px" />
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
	                toolbar:'#toolbar',
	                url:'${ctx}/system/finance/listCashRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="record_id" width="15%"   align="center">流水号</th>
				<th field="order_id" width="12%"   align="center">订单编号 </th>
				<th field="mobile" width="11%" align="center">顾客账号</th>
				<th field="username" width="8%" align="center">姓名</th>
				<th field="beauty_num" formatter="beautyFormatter"   width="8%" align="center">颜值数量</th>
				<th field="pay_way" formatter="pay_wayFormatter"    width="10%" align="center">支付方式</th>
				<th field="money"  formatter="moneyFormatter"  width="10%" align="center">实际消费金额</th>
				<th field="record_type" formatter="cash_record_typeFormatter"   width="10%" align="center">分类</th>
				<th field="pay_time"    width="14%" align="center">支付时间</th>
				
			</tr>
		</thead>
	</table>
<div id="toolbar" style="padding: 2px;">
  
	<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="excel" plain="true"
			onclick="downloadData()">导出Excel</a>
	  
	</div>
    </div>
    </div>
   <div id="addWindow" class="easyui-window" title="新增分类信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 260px; background-color: #FFFFFF"></div>
	<div id="modifyWindow" class="easyui-window" title="修改分类信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 260px; background-color: #FFFFFF"></div>
   
   
    
</body>


