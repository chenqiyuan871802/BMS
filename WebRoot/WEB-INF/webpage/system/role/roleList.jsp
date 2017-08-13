<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="role_type,status,edit_mode"/>
<IMS:codeFormatter fields="role_type,status,edit_mode"/>
</head>
<script type="text/javascript">
//修改菜单
function modifyRole(){
   var row= $('#roleList').datagrid('getSelected');
		if(row!=null){
		 var edit_mode=row.edit_mode;
		 if(edit_mode=='1'){
		   var role_id=row.role_id;
		   showWindow('modifyRoleWindow','${ctx}/system/role/goModify.jhtml?role_id='+role_id)
		 }else{
		    $.messager.alert('警告信息', '你选择的角色数据编辑模式为只读，只读的数据不允许删除和修改', 'warning');
		 }
		
		}else{
			$.messager.alert('警告信息', '请选择你要修改角色信息', 'warning');
		}
}
//菜单授权
function grantMenu(){
	 var row= $('#roleList').datagrid('getSelected');
		if(row!=null){
		 var  role_id=row.role_id;
	     showWindow('grantMenuWindow','${ctx}/system/initGrantMenu.jhtml?role_id='+role_id)
		}else{
			$.messager.alert('警告信息', '请选择你要菜单授权的角色', 'warning');
		}
}
//授权用户
function grantUser(){
	var row= $('#roleList').datagrid('getSelected');
	if(row!=null){
	  var  role_id=row.role_id;
      showWindow('grantUserWindow','${ctx}/system/initGrantUser.jhtml?role_id='+role_id)
	}else{
		$.messager.alert('警告信息', '请选择你要用户授权的角色', 'warning');
	}
}
//删除角色
function deleteRole(){
	 var row= $('#roleList').datagrid('getSelected');
 	 if(row!=null){
 	   if(row.edit_mode=='0'){
 	       $.messager.alert('警告信息', '你选择的角色数据编辑模式为只读，只读的数据不允许删除和修改,请重新选择你要删除角色', 'warning');
 	       return;
 	  }
 	  deleteGridData('roleList','role_id','${ctx}/system/role/deleteRole.jhtml','你选择你要删除的角色信息数据','此数据为基础数据，删除后，与此相关的数据会受到影响，建议只进行修改和增加操作，您确定要删除这些角色信息吗');
 	 }else{
 		 
 		$.messager.alert('警告信息','请选择你要删除的角色', 'warning');
 	 }
}
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:37px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<td width="15%" style="text-align:right">
							角色名称：
						</td>
						<td width="40%" style="text-align:left">
							<input type="text" name="role_name" class="easyui-textbox" style="width: 200px;"/>
						</td>
						
					  
						<td width="50%" rowspan="4" algin="center">
							<div>
								&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('roleList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh" 
							onclick="$('#queryForm').form('reset')">重置</a> 
							</div>
						</td>
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="roleList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
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
	                url:'${ctx}/system/role/listRole.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="role_id" hidden="true" align="center">角色编号</th>
				<th field="role_name" width="20%" align="center">角色名称</th>
				<th field="role_type"    formatter="role_typeFormatter" width="10%" align="center">角色类型</th>
				<th field="status"   formatter="statusFormatter" width="10%" align="center">当前状态</th>
				<th field="edit_mode"   formatter="edit_modeFormatter"   width="10%" align="center">编辑模式</th>
				<th field="role_remark"    width="33%" align="center">备注</th>
				<th field="create_time"    width="15%" align="center">创建时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addRoleWindow','${ctx}/system/role/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyRole();">修改</a>
			 <a href="#" class="easyui-linkbutton" iconCls="role_grant"
			plain="true" onclick="grantMenu()">菜单授权</a>
	   <a href="#" class="easyui-linkbutton" iconCls="user_grant"
			plain="true" onclick="grantUser()">用户授权</a>
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteRole()">删除</a>
	  
	</div>

    </div>
    </div>
    <div id="addRoleWindow" class="easyui-window" title="新增角色"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height:350px; background-color: #FFFFFF"></div>
    <div id="modifyRoleWindow" class="easyui-window" title="修改角色"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height:350px; background-color: #FFFFFF"></div>
    <div id="grantMenuWindow" class="easyui-window" title="菜单授权"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 400px; height:480px; background-color: #FFFFFF"></div>
    <div id="grantAdminMenuWindow" class="easyui-window" title="菜单授权"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 400px; height:480px; background-color: #FFFFFF"></div>
    <div id="grantUserWindow" class="easyui-window" title="用户授权"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1100px; height:480px; background-color: #FFFFFF"></div>
   
   
    
</body>


