<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeFormatter fields="is_del,bond_status"/>
<IMS:codeStore fields="is_del,bond_status"/>
</head>

<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:40px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								
								<td width="7%" style="text-align: right">活动名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="active_name"   class="easyui-textbox" style="width: 150px; " />
								</td>
							   <td width="7%" style="text-align: right">删除标志：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="show_status"    class="easyui-combobox" editable="false"  data-options="data:is_delStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								<td width="50%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('dataList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
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
	                toolbar:'#toolbar',
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/couponActive/listCouponActive.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="active_id"  hidden=“true”  align="center">活动编号</th>
				<th field="active_name" width="20%"   align="center">活动名称 </th>
				<th field="beauty_num" width="10%"   align="center">颜值券数量 </th>
				<th field="bond_num" width="15%"   align="center">每张券可兑换颜值数量 </th>
				<th field="bond_count" width="10%"   align="center">已兑换数量 </th>
				<th field="remain_count" width="10%"   align="center">剩余数量 </th>
				<th field="is_del" formatter="is_delFormatter" width="10%"   align="center">删除标志 </th>
				<th field="create_time"    width="15%" align="center">创建时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addWindow','${ctx}/system/couponActive/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true"
			onclick="showGridData('showCouponRecordWindow','dataList','active_id','${ctx}/system/couponActive/initCouponRecord.jhtml','请选择你要查看礼券活动兑换码');;">查看礼券兑换码</a>
	   
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('dataList','active_id','${ctx}/system/couponActive/deleteCouponActive.jhtml','请选择你要删除的礼券信息','删除礼券信息，未兑换的礼券将失效，你确认要删除该礼券信息吗？')">删除</a>
	  
	</div>
    </div>
    </div>
   <div id="addWindow" class="easyui-window" title="新增活动礼券信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 220px; background-color: #FFFFFF"></div>
	<div id="showCouponRecordWindow" class="easyui-window" title="礼券兑换码信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 800px; height: 450px; background-color: #FFFFFF"></div>
   
   
    
</body>


