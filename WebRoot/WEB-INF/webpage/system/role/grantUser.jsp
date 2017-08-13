<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
   //树的单击查询
  function treeOnClickQuery(treeNode){
     
     $('#queryUserForm').form('reset');//重置查询框的值
	 $('#cascade_id').val(treeNode.cascade_id);
	 doQuery('userList','queryUserForm');
  }
   //待选择的查询
 function search(){
	 doQuery('userList','queryUserForm');
 }
   //已经选择查询
 function selectSearch(){
	 doQuery('selectUserList','selectUserForm');
 }
 //选择用户
 function selectUser(){
	 var rows = $('#userList').datagrid('getChecked');
	 var user_ids = getRowValues(rows,'user_id');
	 var role_id='${role_id}';
	 if(user_ids!=''){
		 $.ajax({
				type : 'post',
				url  :'${ctx}/system/saveRoleUser.jhtml',
				data : {
				  'user_ids' :user_ids,
				  'role_id': role_id
				},
				dataType : 'json',
				success : function(data) {
					if (data) {
						if (data.appcode == "1") {
							showMsg('提示', data.appmsg);
				          $('#userList').datagrid({});  //刷新菜单列表
				          $('#selectUserList').datagrid({});  //刷新菜单列表
						} else if(data.appcode=="0"){
			                 $.messager.alert('警告信息', data.appmsg, 'warning');
			             }else {
							$.messager.alert('错误信息',data.appmsg, 'error');
						}
					} else {
						$.messager.alert('错误信息', '删除失败',
								'error');
					}
				},
				error : function() {
					$.messager.alert('错误信息', '删除失败，网络连接超时',
							'error');
				}
			})
	 }else{
		 $.messager.alert('警告信息', '请选择你要授权的用户', 'warning');
	 }
	 
	 	
 }
 //撤销用户
 function cancelUser(){
	 var rows = $('#selectUserList').datagrid('getChecked');
	 var user_ids = getRowValues(rows,'user_id');
	 var role_id='${role_id}';
	 if(user_ids!=''){
		 $.ajax({
				type : 'post',
				url  :'${ctx}/system/deleteRoleUser.jhtml',
				data : {
				  'user_ids' :user_ids,
				  'role_id': role_id
				},
				dataType : 'json',
				success : function(data) {
					if (data) {
						if (data.appcode == "1") {
							showMsg('提示', data.appmsg);
				          $('#userList').datagrid({});  //刷新菜单列表
				          $('#selectUserList').datagrid({});  //刷新菜单列表
						} else if(data.appcode=="0"){
			                 $.messager.alert('警告信息', data.appmsg, 'warning');
			             }else {
							$.messager.alert('错误信息',data.appmsg, 'error');
						}
					} else {
						$.messager.alert('错误信息', '删除失败',
								'error');
					}
				},
				error : function() {
					$.messager.alert('错误信息', '删除失败，网络连接超时',
							'error');
				}
			})
	 }else{
		 $.messager.alert('警告信息', '请选择你要撤销的用户', 'warning');
	 }
	 
 }
</script>
   <div class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="region:'center',border:false" >
	<div class="easyui-layout"  data-options="fit:true,border:false">
		<div data-options="region:'west',collapsible:false" title="组织机构树" style="width: 210px;">
	
				<div data-options="region:'center',border:false">
					  <ul id="deptTree" class="easyui-tree" data-options="url:'${ctx }/system/dept/loadDeptTree.jhtml',method:'get',animate:true,lines:true,onClick:treeOnClickQuery"></ul>
				</div>
		</div>
		<div data-options="region:'center',border:false">
		<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west',collapsible:false" width="400px" title="待选用户列表">
		<div class="easyui-layout"  data-options="fit:true">
        <div  style="height:34px;background-color: white;"  data-options="region:'north',split:false, border:false">
        <form id="queryUserForm" method="post">
        <input type="hidden"  name="cascade_id" id="cascade_id" />
        <table class="searchContent">
		<tr>
					
		<td width="11%" style="text-align:left">
		<input class="easyui-searchbox"  name="queryParam" data-options="prompt:'用户账号或用户名',searcher:search" style="width:300px"></input>
		</td>
		</tr>
					
		</table>
        </form>
      </div>
        <div  data-options="region:'center',split:false, border:false">	
		<table id="userList" class="easyui-datagrid"  
						style="width: 100%; height: 100%; padding: 0px;"
						data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:false,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                queryParams : $('#queryUserForm').serializeObject(),
	                 url:'${ctx}/system/listUser.jhtml',
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="user_id" hidden=“true”>用户编号</th>
								<th data-options="field:'ck',checkbox:true"></th>
								<th field="account" formatter="formatCellTooltip" width="30%" align="center">用户账号</th>
								<th field="username"  formatter="formatCellTooltip" width="30%" align="center">用户名</th>
								<th field="dept_name"  formatter="formatCellTooltip" width="30%" align="center">所属机构</th>
							</tr>
						</thead>
					</table>
		</div>
		</div>
		</div>
		<div data-options="region:'center'" >
		<div style="text-align: center;position:absolute;  top:30%; margin-top:10px; padding: 20px 5px;">
	    <a href="javascript:void(0)" title="选中授权" class="easyui-linkbutton"
					iconCls="right" iconAlign="right" onClick="selectUser()">选中</a>
		<a href="javascript:void(0)" title="撤销授权" class="easyui-linkbutton"
					iconCls="left" style="margin:20px 0px 0px 0px;" onClick="cancelUser()">撤销</a>
		</div>
	
		</div>
		<div data-options="region:'east',collapsible:false" width="400px" title="已选用户列表">
		<div class="easyui-layout"  data-options="fit:true">
        <div  style="height:34px;background-color: white;"  data-options="region:'north',split:false, border:false">
        <form id="selectUserForm" method="post">
        <input type="hidden"  name="role_id" value="${role_id}"/>
        <table class="searchContent">
		<tr>
					
		<td width="11%" style="text-align:left">
		<input class="easyui-searchbox"  name="queryParam" data-options="prompt:'用户账号或用户名',searcher:selectSearch" style="width:300px"></input>
		</td>
		</tr>
		</table>
        </form>
      </div>
        <div  data-options="region:'center',split:false, border:false">	
		<table id="selectUserList" class="easyui-datagrid"  
						style="width: 100%; height: 100%; padding: 0px;"
						data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:false,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                queryParams : $('#selectUserForm').serializeObject(),
	                 url:'${ctx}/system/listSelectUser.jhtml',
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="user_id" hidden=“true”>用户编号</th>
								<th data-options="field:'ck',checkbox:true"></th>
								<th field="account" formatter="formatCellTooltip" width="30%" align="center">用户账号</th>
								<th field="username"  formatter="formatCellTooltip" width="30%" align="center">用户名</th>
								<th field="dept_name"  formatter="formatCellTooltip" width="30%" align="center">所属机构</th>
							</tr>
						</thead>
					</table>
		</div>
		</div>
		</div>
		</div>
		</div>
		
	</div>
	</div>
	<div data-options="region:'south'" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('grantUserWindow')" style="width: 70px">关闭</a>
   </div>
   </div>
</body>


