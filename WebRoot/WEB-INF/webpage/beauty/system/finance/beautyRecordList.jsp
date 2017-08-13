<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<IMS:codeStore fields="beauty_type"/>
<IMS:codeFormatter fields="beauty_type"/>
<script type="text/javascript">
 function  beautyFormatter(value, row, index){
	 var give_num=row.give_num
	 var record_type=row.record_type;
	 var str='';
	 if(record_type=='3'){
		  str= '+'
	  }else{
		  str='-'
	  }
	 var returnVal=str+value
	 if(IMSUtils.isNotEmpty(give_num)){
		 if(give_num!='0'){
			 returnVal+='('+str+give_num+')';
		 }
	 }
	 return returnVal;
		 
 }
 function orderFormatter(value, row, index){
	  var record_type=row.record_type;
	 if(record_type!='2'){
		 return value;
	  }
	 return '';
 }
 function  moneyFormatter(value, row, index){
	  var record_type=row.record_type;
	  if(record_type=='3'){
		  return '-'+value;
		  
	  }else if(record_type=='1'){
		 
		  return '+'+value;
	  }else{
		  
		  return value;
	  }
	  
	  
	
		 
 }
 function downloadData(){
	 $('#queryForm').form('submit', {   
		    url:'${ctx}/system/finance/downloadBeautyRecord.jhtml'
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
								
								
								<td width="7%" style="text-align: right">分类：</td>
								<td width="13%" style="text-align: left">
			                    <input type="text" name="record_type"    class="easyui-combobox" editable="false"  
			                    data-options="data:beauty_typeStore,textField:1,valueField:0" style="width: 150px;  " />

								</td>
								<td width="7%" style="text-align: right">支付时间：</td>
								<td width="13%" colspan="3" style="text-align: left">
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
	                url:'${ctx}/system/finance/listBeautyRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="record_id" width="15%"   align="center">流水号</th>
				<th field="order_id" formatter="orderFormatter" width="12%"   align="center">订单编号 </th>
				<th field="mobile" width="11%" align="center">顾客账号</th>
				<th field="username" width="8%" align="center">姓名</th>
				<th field="beauty_num" formatter="beautyFormatter"   width="12%" align="center">颜值数量<br/>(免费颜值数量)</th>
				<th field="total_money"  formatter="moneyFormatter"  width="10%" align="center">实际消费金额</th>
				<th field="record_type" formatter="beauty_typeFormatter"   width="10%" align="center">分类</th>
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


