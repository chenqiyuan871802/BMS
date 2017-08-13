<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="edit_mode,status,is_auto_expand,menu_type"/>
<IMS:codeFormatter fields="edit_mode,status,is_auto_expand,menu_type"/>
<script type="text/javascript">
   //树的单击查询
  function treeOnClickQuery(treeNode){
     
     $('#queryForm').form('reset');//重置查询框的值
	 $('#cascade_id').val(treeNode.cascade_id);
	 doQuery('menuList','queryForm');
  }
  //提交菜单数据并进行刷新
 function submitMenuData(formId, windowId){
    $('#'+formId).form('submit', {
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data) {
					if (data.appcode=="1") {
						showMsg('提示', data.appmsg);
						 $("#menuTree").tree('reload');//刷新树
						 $('#menuList').datagrid({});  //刷新菜单列表
					     $('#'+windowId).window('close');
						
					}else if(data.appcode=="0"){
					   $.messager.alert('警告信息', data.appmsg, 'warning');
					}else {
						$.messager.alert('错误信息', data.appmsg, 'error');
					}
				} else {
					$.messager.alert('错误信息', '操作失败', 'error');
				}
			},
			onLoadeError:function(){
			         $.messager.alert('错误信息', '操作失败', 'error');
			}
		});
 
 }
 
  //修改菜单
  function modifyMenu(){
     var row= $('#menuList').datagrid('getSelected');
 		if(row!=null){
 		 var edit_mode=row.edit_mode;
 		 if(edit_mode=='1'){
 		   var menu_id=row.menu_id;
 		   showWindow('modifyMenuWindow','${ctx}/system/menu/goModify.jhtml?menu_id='+menu_id)
 		 }else{
 		    $.messager.alert('警告信息', '你选择的菜单数据编辑模式为只读，只读的数据不允许删除和修改', 'warning');
 		 }
 		
 		}else{
 			$.messager.alert('警告信息', '请选择你要修改菜单信息', 'warning');
 		}
  }
  //删除菜单
  function deleteMenu(){

 	 var row= $('#menuList').datagrid('getSelected');
 	 if(row!=null){
 	   if(row.edit_mode=='0'){
 	          $.messager.alert('警告信息', '你选择的菜单数据编辑模式为只读，只读的数据不允许删除和修改,请重新选择你要删除菜单数据', 'warning');
 	          return;
 	       }
 		 var menu_id=row.menu_id;
 		 	$.messager.confirm('确认', '你确认要删除选择的菜单数据吗？',
 				function(r) {
 			    if(r){
 					$.ajax({
 						type : 'post',
 						url  :'${ctx}/system/menu/deleteMenu.jhtml',
 						data : {
						  'menu_id' :menu_id
						},
 						dataType : 'json',
 						success : function(data) {
 							if (data) {
 								if (data.appcode == "1") {
 									showMsg('提示', data.appmsg);
						          $("#menuTree").tree('reload');//刷新树
						          $('#menuList').datagrid({});  //刷新菜单列表
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
 			    }
 				});
 	}else{
 		$.messager.alert('警告信息','请选择你要删除的菜单数据', 'warning');
 	}
 	
 	
 }
</script>
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout"  data-options="fit:true">
		<div data-options="region:'west',split:false" title="功能菜单树" style="width: 210px;">
		 <div class="easyui-layout" data-options="fit:true">
				
				<div data-options="region:'center'">
					  <ul id="menuTree" class="easyui-tree" data-options="url:'${ctx }/system/menu/loadMenuTree.jhtml',method:'get',animate:true,lines:true,onClick:treeOnClickQuery"></ul>
				</div>
			</div>
		
		</div>
		<div data-options="region:'center'" >
			<div class="easyui-layout" data-options="fit:true">
				<div style="height: 35px; background-color: white;"
					data-options="region:'north',split:false">
					<form id="queryForm" method="post">
					<input type="hidden"  name="cascade_id" id="cascade_id" />
						<table class="searchContent">
							<tr>
								<td width="15%" style="text-align: right">菜单名称：</td>
								<td width="40%" style="text-align: left">
								<input type="text" name="menu_name" id="menu_name"  class="easyui-textbox" style="width: 200px;" />
								</td>
								
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('menuList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							
						</table>
					</form>
				</div>
				<div id="main"
					data-options="region:'center',split:false, border:false">
					<table id="menuList" class="easyui-datagrid"
						style="width: 100%; height: 100%; padding: 0px;"
						data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                toolbar:'#toolbar',
	                queryParams : $('#queryForm').serializeObject(),
	                 url:'${ctx}/system/menu/listMenu.jhtml',
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="menu_id" hidden=“true”>菜单编号</th>
								<th field="menu_name" formatter="formatCellTooltip" width="15%" align="center">菜单名称</th>
								<th field="url"  formatter="formatCellTooltip" width="32%" align="center">菜单URL</th>
								<th field="menu_type"  formatter="menu_typeFormatter"   width="8%" align="center">菜单类型</th>
								<th field="is_auto_expand"  formatter="is_auto_expandFormatter"   width="8%" align="center">自动展开</th>
								<th field="sort_no"   width="7%" align="center">排序号</th>
								<th field="icon_name"   width="12%" align="center">节点图标</th>
								<th field="status" formatter="statusFormatter" width="7%" align="center">当前状态</th>
								<th field="edit_mode" formatter="edit_modeFormatter" width="7%" align="center">编辑模式</th>
								

							</tr>
						</thead>
					</table>
				</div>
				<div id="toolbar" style="padding: 2px;">

					<a href="#" class="easyui-linkbutton" iconCls="add"
						plain="true"
						onclick="showWindow('addMenuWindow','${ctx}/system/menu/goAdd.jhtml');">新增</a>

					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="edit" plain="true"
						onclick="modifyMenu();">修改</a> <a href="#"
						class="easyui-linkbutton" iconCls="del" plain="true"
						onclick="deleteMenu();">删除</a> 

				</div>

			</div>
		</div>
	</div>
	<div id="addMenuWindow" class="easyui-window" title="新增菜单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 485px; background-color: #FFFFFF"></div>
	<div id="modifyMenuWindow" class="easyui-window" title="修改菜单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 485px; background-color: #FFFFFF"></div>
</body>


