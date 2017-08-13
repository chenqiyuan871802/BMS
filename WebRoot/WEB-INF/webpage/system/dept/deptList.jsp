<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="edit_mode,status,is_auto_expand"/>
<IMS:codeFormatter fields="edit_mode,status,is_auto_expand"/>
<script type="text/javascript">
   //树的单击查询
  function treeOnClickQuery(treeNode){
     
     $('#queryForm').form('reset');//重置查询框的值
	 $('#cascade_id').val(treeNode.cascade_id);
	 doQuery('deptList','queryForm');
  }
  //提交组织机构数据并进行刷新
 function submitDeptData(formId, windowId){
    $('#'+formId).form('submit', {
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data) {
					if (data.appcode=="1") {
						showMsg('提示', data.appmsg);
						 $("#deptTree").tree('reload');//刷新树
						 $('#deptList').datagrid({});  //刷组织机构列表
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
 //添加机构
 function addDept(){
	 var row= $('#deptList').datagrid('getSelected');
  var parent_id="0";
   if(row!=null){
     parent_id=row.dept_id;
   }
   showWindow('addDeptWindow','${ctx}/system/dept/goAdd.jhtml?parent_id='+parent_id);
 }
  //删除组织机构
  function deleteDept(){

 	 var row= $('#deptList').datagrid('getSelected');
 	 if(row!=null){
 	    
 		 var dept_id=row.dept_id;
 		 if(dept_id=='0'){
 			$.messager.alert('警告信息','顶级机构不能进行移动和删除操作，只能进行修改', 'warning');
 			return;
 		 }
 		 	$.messager.confirm('确认', '组织机构是基础数据，删除组织机构将同时删除下属的用户，请慎重，你确认要删除选择的组织机构数据吗？',
 				function(r) {
 			    if(r){
 					$.ajax({
 						type : 'post',
 						url  :'${ctx}/system/dept/deleteDept.jhtml',
 						data : {
						  'dept_id' :dept_id
						},
 						dataType : 'json',
 						success : function(data) {
 							if (data) {
 								if (data.appcode == "1") {
 									showMsg('提示', data.appmsg);
						          $("#deptTree").tree('reload');//刷新树
						          $('#deptList').datagrid({});  //刷新组织机构列表
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
 		$.messager.alert('警告信息','请选择你要删除的组织机构数据', 'warning');
 	}
 	
 }
  //移动机构
  function moveDept(){
	  var row= $('#deptList').datagrid('getSelected');
	   if(row!=null){
	 		 var dept_id=row.dept_id;
	 		 if(dept_id=='0'){
	 			$.messager.alert('警告信息','顶级机构不能进行移动和删除操作，只能进行修改', 'warning');
	 			return;
	 		 }
	 		showWindow('moveDeptWindow','${ctx}/system/dept/goMove.jhtml?dept_id='+dept_id);
	  }else{
		  $.messager.alert('警告信息','请选择你要移动的机构', 'warning');
	  }
  }
 //锁定第一行
 function lockFirstRow(data){
  $('#deptList').datagrid('selectRow',0);
   
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
				<div style="height: 35px; background-color: white;"
					data-options="region:'north',split:false">
					<form id="queryForm" method="post">
					<input type="hidden"  name="cascade_id" id="cascade_id" />
						<table class="searchContent">
							<tr>
								<td width="15%" style="text-align: right">机构名称：</td>
								<td width="40%" style="text-align: left">
								<input type="text" name="dept_name" id="dept_name"  class="easyui-textbox" style="width: 200px;" />
								</td>
								
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('deptList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							
						</table>
					</form>
				</div>
				
				<div id="main"
					data-options="region:'center',split:false, border:false">
					<table id="deptList" class="easyui-datagrid"
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
	                 url:'${ctx}/system/dept/listDept.jhtml',
	                onLoadSuccess:lockFirstRow,
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="dept_id" hidden=“true”>部门编号</th>
								<th field="dept_name" formatter="formatCellTooltip" width="15%" align="center">机构名称</th>
								<th field="dept_code"  formatter="formatCellTooltip" width="10%" align="center">机构代码</th>
								<th field="manager"  formatter="formatCellTooltip" width="8%" align="center">主要负责人</th>
								<th field="phone"  formatter="formatCellTooltip" width="11%" align="center">电话</th>
								<th field="fax"  formatter="formatCellTooltip" width="11%" align="center">传真</th>
								<th field="is_auto_expand"  formatter="is_auto_expandFormatter"   width="7%" align="center">自动展开</th>
								<th field="sort_no"   width="7%" align="center">排序号</th>
								<th field="icon_name"   width="10%" align="center">节点图标</th>
								
								<th field="remark" formatter="formatCellTooltip" width="20%" align="center">备注</th>

							</tr>
						</thead>
					</table>
				</div>
				<div id="toolbar" style="padding: 2px;">

					<a href="#" class="easyui-linkbutton" iconCls="add"
						plain="true"
						onclick="addDept()">新增</a>

					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="edit" plain="true"
						onclick="modifyGridData('modifyDeptWindow','deptList','dept_id','${ctx}/system/dept/goModify.jhtml','请选择你要修改的组织机构信息');">修改</a> 
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="dept_move" plain="true"
						onclick="moveDept();">移动机构</a> 
						<a href="#"
						class="easyui-linkbutton" iconCls="del" plain="true"
						onclick="deleteDept();">删除</a> 

				</div>

			</div>
		</div>
	</div>
	<div id="moveDeptWindow" class="easyui-window" title="移动机构"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 400px; height: 480px; background-color: #FFFFFF"></div>
	<div id="addDeptWindow" class="easyui-window" title="新增组织机构"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 850px; height: 380px; background-color: #FFFFFF"></div>
	<div id="modifyDeptWindow" class="easyui-window" title="修改组织机构"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 850px; height: 380px; background-color: #FFFFFF"></div>
</body>


