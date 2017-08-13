<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="status,shop_type"/>
<IMS:codeFormatter fields="status,shop_type"/>
</head>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:67px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">店铺编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="shop_id"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">店铺名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="shop_name"   class="easyui-textbox" style="width: 150px; " />
								</td>
							
								<td width="7%" style="text-align: right">状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="show_status"    class="easyui-combobox" editable="false"  data-options="data:statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('shopList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
								<td width="7%" style="text-align: right">店主账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="account"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">店铺简称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="short_name"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">营业执照号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="shop_license"   class="easyui-textbox" style="width: 150px; " />
								</td>
								
								
								
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="shopList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
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
	                url:'${ctx}/system/shopSys/listShop.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="shop_id" width="10%"   align="center">店铺编号</th>
				<th field="shop_name" width="11%"   align="center">店铺名称 </th>
				<th field="short_name" width="11%"   align="center">店铺简称 </th>
				<th field="account" width="10%" align="center">店主账号</th>
				<th field="username" width="7%" align="center">店铺法人</th>
				<th field="shop_license" width="15%" align="center">营业执照号</th>
				<th field="shop_phone" width="10%" align="center">店铺电话</th>
				<th field="show_status"   formatter="statusFormatter" width="6%" align="center">状态</th>
				<th field="sort_no" width="6%" align="center">排序号</th>
				<th field="modify_time"    width="12%" align="center">更新时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addShopWindow','${ctx}/system/shopSys/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyGridData('modifyShopWindow','shopList','shop_id','${ctx}/system/shopSys/goModify.jhtml','请选择你要修改的店主信息');;">修改</a>
	   <a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true"
			onclick="showGridData('showShopWindow','shopList','shop_id','${ctx}/system/shopSys/initInfo.jhtml','请选择你要查看的店铺信息');;">查看详情</a>
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('shopList','shop_id','${ctx}/system/shopSys/deleteShop.jhtml','请选择你要删除的店铺信息','你确认要删除店铺信息吗？')">删除</a>
	  
	</div>
    </div>
    </div>
   <div id="addShopWindow" class="easyui-window" title="新增店铺信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:1050px; height: 480px; background-color: #FFFFFF"></div>
	<div id="modifyShopWindow" class="easyui-window" title="修改店铺信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
	<div id="showShopWindow" class="easyui-window" title="查看详情"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1150px; height: 480px; background-color: #FFFFFF"></div>
   
   
    
</body>


