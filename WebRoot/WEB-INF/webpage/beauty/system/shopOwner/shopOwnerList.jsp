<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="status,sex"/>
<IMS:codeFormatter fields="status,sex"/>
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
								<td width="7%" style="text-align: right">店主账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="account"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 150px; " />
								</td>
							
								<td width="7%" style="text-align: right">状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="status"    class="easyui-combobox" editable="false"  data-options="data:statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('shopOwnerList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
								<td width="7%" style="text-align: right">手机：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">身份证：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="idno"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">邮箱：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="email"   class="easyui-textbox" style="width: 150px; " />
								</td>
								
								
								
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="shopOwnerList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
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
	                url:'${ctx}/system/shopOwner/listShopOwner.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="shop_user_id" hidden="true" align="center">编号</th>
				<th field="account" width="12%" align="center">店主账号</th>
				<th field="username" width="10%" align="center">姓名</th>
				<th field="sex" formatter="sexFormatter"  width="6%" align="center">性别</th>
				<th field="mobile" width="10%" align="center">手机</th>
				<th field="idno" width="15%" align="center">身份证</th>
				<th field="email" width="15%" align="center">邮箱</th>
				<th field="phone" width="10%" align="center">电话</th>
				<th field="status"   formatter="statusFormatter" width="6%" align="center">状态</th>
				<th field="create_time"    width="13%" align="center">创建时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addShopOwnerWindow','${ctx}/system/shopOwner/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyGridData('modifyShopOwnerWindow','shopOwnerList','shop_user_id','${ctx}/system/shopOwner/goModify.jhtml','请选择你要修改的店主信息');;">修改</a>
	   <a href="#" class="easyui-linkbutton" iconCls="password_reset"
		 	               plain="true" onclick="modifyGridData('modifyPasswordWindow','shopOwnerList','shop_user_id','${ctx}/system/shopUser/goModifyPassword.jhtml','请选择你要重置密码的店主');">密码重置</a>
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('shopOwnerList','shop_user_id','${ctx}/system/shopOwner/deleteShopOwner.jhtml','请选择你要删除的店主信息','你确认要删除店主信息吗？')">删除</a>
	  
	</div>
    </div>
    </div>
   <div id="addShopOwnerWindow" class="easyui-window" title="新增店主信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 850px; height: 380px; background-color: #FFFFFF"></div>
	<div id="modifyShopOwnerWindow" class="easyui-window" title="修该店主信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 850px; height: 380px; background-color: #FFFFFF"></div>
   
   <div id="modifyPasswordWindow" class="easyui-window" title="密码重置"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 520px; height:180px; background-color: #FFFFFF"></div>
    
</body>


