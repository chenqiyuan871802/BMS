<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="staff_status,sex"/>
<IMS:codeFormatter fields="staff_status,sex"/>
</head>
<script type="text/javascript">

</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:67px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">员工账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="account"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								
								<td width="7%" style="text-align: right">店铺名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="shop_id"   class="easyui-combobox"	 data-options="url:'${ctx}/system/shopSys/queryShop.jhtml',method:'get',valueField:'shop_id',textField:'shop_name'" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">职位：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="post_code"   class="easyui-combobox"	 data-options="url:'${ctx}/system/shopPost/queryShopPost.jhtml',method:'get',valueField:'post_code',textField:'post_name'" style="width: 150px; " />
								</td>
							
								<td width="7%" style="text-align: right">状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="status"    class="easyui-combobox" editable="false"  data-options="data:staff_statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								<td width="20%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('shopUserList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
						    	<td width="7%" style="text-align: right">姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">工号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="work_number"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">手机：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">身份证：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="idno"   class="easyui-textbox" style="width: 150px; " />
								</td>
								
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="shopUserList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
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
	                url:'${ctx}/system/shopUser/listShopUser.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="shop_user_id" hidden=“true”    align="center">员工编号</th>
				<th field="account" width="8%"   align="center">员工账号 </th>
				<th field="username" width="7%"   align="center">姓名</th>
				<th field="sex" width="5%" formatter="sexFormatter"   align="center">性别</th>
				<th field="work_number" width="8%"   align="center">工号</th>
				<th field="shop_name" width="13%" align="center">店铺名称</th>
				<th field="post_name" width="6%" align="center">职位</th>
				<th field="mobile" width="9%" align="center">手机</th>
				<th field="idno" width="13%" align="center">身份证</th>
				<th field="a" width="8%" align="center">销售收入</th>
				<th field="b" width="8%" align="center">手工收入</th>
				<th field="status"   formatter="staff_statusFormatter" width="6%" align="center">状态</th>
				<th field="entry_date"    width="8%" align="center">入职时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addShopUserWindow','${ctx}/system/shopUser/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyGridData('modifyShopUserWindow','shopUserList','shop_user_id','${ctx}/system/shopUser/goModify.jhtml','请选择你要修改的员工信息');;">修改</a>
		    <a href="#" class="easyui-linkbutton" iconCls="password_reset"
		 	               plain="true" onclick="modifyGridData('modifyPasswordWindow','shopUserList','shop_user_id','${ctx}/system/shopUser/goModifyPassword.jhtml','请选择你要重置密码的员工');">密码重置</a>
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('shopUserList','shop_user_id','${ctx}/system/shopUser/deleteShopUser.jhtml','请选择你要删除的员工信息','你确认要删除该员工信息吗？')">删除</a>
	  
	</div>
    </div>6
    </div>
   <div id="addShopUserWindow" class="easyui-window" title="新增员工信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:900px; height: 480px; background-color: #FFFFFF"></div>
	<div id="modifyShopUserWindow" class="easyui-window" title="修改员工信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
   <div id="modifyPasswordWindow" class="easyui-window" title="密码重置"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 520px; height:180px; background-color: #FFFFFF"></div>
   
    
</body>


