<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:35px;background-color: white;"  data-options="region:'north',split:false">
        <form id="doForm" method="post">
         <input type="hidden"  name="active_id" value="${active_id}" />
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">兑换码：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="cdkey"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">兑换手机：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px; " />
								</td>
							  <td width="7%" style="text-align: right">状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="status"    class="easyui-combobox" editable="false"  data-options="data:bond_statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
							
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('couponRecordListt','doForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#doForm').form('reset')">重置</a> 
								</td>
							</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="couponRecordListt" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
				     rownumbers:true,
	                singleSelect:false,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                queryParams : $('#doForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/couponActive/listCouponRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="record_id" hidden="true"   align="center">记录编号</th>
				<th field="cdkey" width="20%"   align="center">兑换码</th>
				<th field="mobile" width="20%"   align="center">兑换手机</th>
				<th field="status" formatter="bond_statusFormatter" width="10%"   align="center">状态</th>
				<th field="exchange_time"    width="20%" align="center">兑换时间</th>
				<th field="create_time"    width="20%" align="center">创建时间</th>
				
				
			</tr>
		</thead>
	</table>
	
    </div>
    </div>

 
    
</body>


