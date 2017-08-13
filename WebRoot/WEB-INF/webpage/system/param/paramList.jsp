<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="edit_mode,status"/>
<IMS:codeFormatter fields="edit_mode,status"/>
<script type="text/javascript">
  function addParam(){
    var treeNode = $('#catalogTree').tree('getSelected');
    var catalog_id;
    if(treeNode){  //如果没有选中树节点则获取根节点ID
       catalog_id=treeNode.id;
    }else{
       var rootNode = $("#catalogTree").tree('getRoot');
       catalog_id=rootNode.id;
    }
    showWindow('addParamWindow','${ctx}/system/param/goAdd.jhtml?catalog_id='+catalog_id)
  }
  //树的单击查询
  function treeOnClickQuery(treeNode){
     
     $('#queryForm').form('reset');//重置查询框的值
	 $('#catalog_cascade_id').val(treeNode.cascade_id);
	 doQuery('paramList','queryForm');
  }
  //修改键值参数
  function modifyParam(){
     var row= $('#paramList').datagrid('getSelected');
 		if(row!=null){
 		 var edit_mode=row.edit_mode;
 		 if(edit_mode=='1'){
 		   var param_id=row.param_id;
 		   showWindow('modifyParamWindow','${ctx}/system/param/goModify.jhtml?param_id='+param_id)
 		 }else{
 		    $.messager.alert('警告信息', '你选择的键值参数数据编辑模式为只读，只读的数据不允许删除和修改', 'warning');
 		 }
 		
 		}else{
 			$.messager.alert('警告信息', '请选择你要修改键值参数信息', 'warning');
 		}
  }
  //删除键值参数数据
  function deleteParam(){
     var rows = $('#paramList').datagrid('getChecked');
 	var param_ids = '';
 	for (var i = 0; i < rows.length; i++) {
 	       if(rows[i].edit_mode=='0'){
 	          $.messager.alert('警告信息', '你选择的键值参数存在编辑模式为只读的数据，只读的数据不允许删除和修改,请重新选择你要删除键值参数数据', 'warning');
 	          return;
 	       }
 			if(i==0){
 				param_ids=rows[i].param_id;
 			}else{
 				param_ids+= ","+rows[i].param_id ;
 			}
 	};
 	if(param_ids!=''){
 	   $.messager.confirm('确认', '你确认要删除这些键值参数数据吗？',
 				function(r) {
 			    if(r){
 					$.ajax({
 						type : 'post',
 						url  :'${ctx}/system/param/batchDeleteParam.jhtml',
 						data : {
						  'param_ids' :param_ids
						},
 						dataType : 'json',
 						success : function(data) {
 							if (data) {
 								if (data.appcode == "1") {
 									showMsg('提示', data.appmsg);
 									$('#paramList').datagrid({});
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
 	  $.messager.alert('警告信息','请选择你要删除的键值参数数据', 'warning');
 	}
  }
</script>
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout"  data-options="fit:true">
		<div data-options="region:'west',split:false" title="参数分类科目" style="width: 210px;">
		 <div class="easyui-layout" data-options="fit:true">
				
				<div data-options="region:'center'">
					  <ul id="catalogTree" class="easyui-tree" data-options="url:'${ctx }/system/catalog/loadCatalogTree.jhtml?root_key=PARAM_TYPE',method:'get',animate:true,lines:true,onClick:treeOnClickQuery"></ul>
				</div>
			</div>
		
		</div>
		<div data-options="region:'center'" >
			<div class="easyui-layout" data-options="fit:true">
				<div style="height: 35px; background-color: white;"
					data-options="region:'north',split:false">
					<form id="queryForm" method="post">
					<input type="hidden"  name="catalog_cascade_id" id="catalog_cascade_id" />
						<table class="searchContent">
							<tr>
								<td width="10%" style="text-align: right">参数名称：</td>
								<td width="30%" style="text-align: left">
								<input type="text" name="param_name" id="param_name"  class="easyui-textbox" style="width: 200px;" />
								</td>
								<td width="10%" style="text-align: right">参数键：</td>
								<td width="30%" style="text-align: left">
								<input type="text" name="param_key" id="param_key" class="easyui-textbox" style="width: 200px;" />
								</td>

								<td width="20%" rowspan="4" algin="center">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('paramList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh" 
							onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							
						</table>
					</form>
				</div>
				<div id="main"
					data-options="region:'center',split:false, border:false">
					<table id="paramList" class="easyui-datagrid"
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
	                 url:'${ctx}/system/param/listParam.jhtml',
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="param_id" hidden=“true”>参数编号</th>
								<th data-options="field:'ck',checkbox:true"></th>
								<th field="param_name" formatter="formatCellTooltip" width="15%" align="center">参数名称</th>
								<th field="param_key"  formatter="formatCellTooltip" width="15%" align="center">参数键</th>
								<th field="param_value"  formatter="formatCellTooltip"   width="20%" align="center">参数值</th>
								<th field="param_remark" formatter="formatCellTooltip" width="20%" align="center">备注</th>
								<th field="catalog_name"   width="10%" align="center">所属分类</th>
								<th field="status" formatter="statusFormatter" width="7%" align="center">当前状态</th>
								<th field="edit_mode" formatter="edit_modeFormatter" width="7%" align="center">编辑模式</th>

							</tr>
						</thead>
					</table>
				</div>
				<div id="toolbar" style="padding: 2px;">

					<a href="#" class="easyui-linkbutton" iconCls="add"
						plain="true"
						onclick="addParam();">新增</a>

					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="edit" plain="true"
						onclick="modifyParam();">修改</a> <a href="#"
						class="easyui-linkbutton" iconCls="del" plain="true"
						onclick="deleteParam();">删除</a> 

				</div>

			</div>
		</div>
	</div>
	<div id="addParamWindow" class="easyui-window" title="新增参数"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 420px; background-color: #FFFFFF"></div>
	<div id="modifyParamWindow" class="easyui-window" title="修改参数"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 420px; background-color: #FFFFFF"></div>
</body>


