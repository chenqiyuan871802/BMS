<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="user_status,sex"/>
<IMS:codeFormatter fields="user_status,sex"/>
<script type="text/javascript">
   //树的单击查询
  function treeOnClickQuery(treeNode){
     
     $('#queryForm').form('reset');//重置查询框的值
	 $('#cascade_id').val(treeNode.cascade_id);
	 doQuery('userList','queryForm');
  }
 
 //新增用户
 function addUser(){
  var treeNode= $("#deptTree").tree('getSelected')
  var dept_id="0";
   if(treeNode){
	   dept_id=treeNode.id
   }
   showWindow('addUserWindow','${ctx}/system/user/goAdd.jhtml?dept_id='+dept_id);
 }
</script>
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout"  data-options="fit:true">
		<div data-options="region:'west',split:false" title="组织机构树" style="width: 210px;">
		 <div class="easyui-layout" data-options="fit:true">
				
				<div data-options="region:'center'">
					  <ul id="deptTree" class="easyui-tree" data-options="url:'${ctx }/system/dept/loadDeptTree.jhtml',method:'get',animate:true,lines:true,onClick:treeOnClickQuery"></ul>
				</div>
			</div>
		
		</div>
		<div data-options="region:'center'" >
			<div class="easyui-layout" data-options="fit:true">
				<div style="height: 65px; background-color: white;"
					data-options="region:'north',split:false">
					<form id="queryForm" method="post">
					<input type="hidden"  name="cascade_id" id="cascade_id" />
						<table class="searchContent">
							<tr>
								<td width="7%" style="text-align: right">用户账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="account"   class="easyui-textbox" style="width: 120px;  " />
								</td>
								
								<td width="7%" style="text-align: right">用户姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 120px; " />
								</td>
								
								<td width="7%" style="text-align: right">性别：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="sex" class="easyui-combobox" editable="false"  data-options="data:sexStore,textField:1,valueField:0"   class="easyui-textbox" style="width: 120px;  " />
								</td>
								<td width="7%" style="text-align: right">用户状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="status"    class="easyui-combobox" editable="false"  data-options="data:user_statusStore,textField:1,valueField:0" style="width: 120px;  " />
								</td>
								
								<td width="20%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('userList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
								<td width="7%" style="text-align: right">手机号码：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 120px;  " />
								</td>
								
								<td width="7%" style="text-align: right">邮箱：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="email"   class="easyui-textbox" style="width: 120px; " />
								</td>
								
								<td width="7%" style="text-align: right">创建日期：</td>
								<td width="13%" style="text-align: left" colspan="3">
								<input type="text"  name="carete_time_begin" class="easyui-datebox" editable="false" style="width:140px" />
							至：<input type="text" name="carete_time__end"  class="easyui-datebox" editable="false" style="width:140px" />
								</td>
								
								
								
							</tr>
							
						</table>
					</form>
				</div>
				<div id="main"
					data-options="region:'center',split:false, border:false">
					<table id="userList" class="easyui-datagrid"
						style="width: 100%; height: 100%; padding: 0px;"
						data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:false,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                toolbar:'#toolbar',
	                queryParams : $('#queryForm').serializeObject(),
	                 url:'${ctx}/system/user/listUser.jhtml',
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="user_id" hidden=“true”>用户编号</th>
								<th data-options="field:'ck',checkbox:true"></th>
								<th field="account" formatter="formatCellTooltip" width="11%" align="center">用户账号</th>
								<th field="username"  formatter="formatCellTooltip" width="10%" align="center">用户名</th>
								<th field="dept_name"  formatter="formatCellTooltip" width="10%" align="center">所属机构</th>
								<th field="role_name"  formatter="formatCellTooltip" width="10%" align="center">所属角色</th>
								<th field="sex"  formatter="sexFormatter"  width="5%" align="center">性别</th>
								<th field="mobile"  formatter="formatCellTooltip" width="9%" align="center">手机号码</th>
								<th field="email"  formatter="formatCellTooltip" width="12%" align="center">邮箱</th>
								<th field="style"   width="8%" align="center">界面风格</th>
								<th field="status" formatter="user_statusFormatter"  width="7%" align="center">用户状态</th>
								<th field="create_time"   width="14%" align="center">创建时间</th>
								

							</tr>
						</thead>
					</table>
				</div>
				<div id="toolbar" style="padding: 2px;">

					<a href="#" class="easyui-linkbutton" iconCls="add"
						plain="true"
						onclick="addUser()">新增</a>

					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="edit" plain="true"
						onclick="modifyGridData('modifyUserWindow','userList','user_id','${ctx}/system/user/goModify.jhtml','请选择你要修改的用户信息');">修改</a> 
				    <a href="#" class="easyui-linkbutton" iconCls="password_reset"
		 	               plain="true" onclick="modifyGridData('modifyPasswordWindow','userList','user_id','${ctx}/system/user/goModifyPassword.jhtml','请选择你要重置密码的用户');">密码重置</a>
				   <a href="#" class="easyui-linkbutton" iconCls="user_move"
			               plain="true" onclick="showMoreGridData('moveUserWindow','userList','user_id','${ctx}/system/user/goMoveUser.jhtml','请选择你要移动的用户');">移动用户</a>
				    <a href="#"
						class="easyui-linkbutton" iconCls="del" plain="true"
						onclick="batchDeleteGridData('userList','user_id','${ctx}/system/user/batchDeleteUser.jhtml','请选择你删除的用户信息','你确认要删除选择的用户信息吗');">删除</a> 
				</div>

			</div>
		</div>
	</div>
	<div id="addUserWindow" class="easyui-window" title="新增用户"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 850px; height: 450px; background-color: #FFFFFF"></div>
	<div id="modifyUserWindow" class="easyui-window" title="修改用户管理"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 850px; height: 410px; background-color: #FFFFFF"></div>
	<div id="modifyPasswordWindow" class="easyui-window" title="密码重置"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 520px; height:180px; background-color: #FFFFFF"></div>
	<div id="moveUserWindow" class="easyui-window" title="移动用户"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 400px; height:480px; background-color: #FFFFFF"></div>
</body>


