<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="is_auto_expand"/>
<IMS:codeFormatter fields="is_auto_expand"/>
<script type="text/javascript">
$(function(){
   $('#addmenu').menu({    
    onClick:function(item){    
      
      if(item.name=='addCatalog'){
        var catalogRow = $('#catalogList').treegrid('getSelected');
        
		if(catalogRow!=null){
		     var catalog_id =catalogRow.catalog_id;
			 showWindow('addCatalogWindow','${ctx}/system/catalog/goAddCatalog.jhtml?catalog_id='+catalog_id);
		}else{
			$.messager.alert('警告信息', '新增分类之前请先选中一个父分类', 'warning');
		}
		
        
      }else{
         showWindow('addSubjectWindow','${ctx}/system/catalog/goAddSubject.jhtml');
      }
     
    }    
});  
});
</script>
</head>
<body style="margin: 0; padding: 0">
	<table id="catalogList"  class="easyui-treegrid" style="width: 100%; height: 100%; padding: 0px;" 
		data-options="
				url: '${ctx }/system/catalog/listCatalog.jhtml',
				method: 'get',
				lines: true,
				toolbar:'#toolbar',
				fit:'true',
				rownumbers: true,
				idField: 'catalog_id',
				treeField: 'catalog_name'
			">
		<thead>
			<tr>
				<th data-options="field:'catalog_name'" width="27%" align="left">分类名称</th>
				<th data-options="field:'is_auto_expand'" formatter="is_auto_expandFormatter" width="7%" align="left">自动展开</th>
				<th data-options="field:'sort_no'" width="7%" align="left">排序号</th>
				<th data-options="field:'root_key'" width="15%" align="left">所属科目标识键</th>
				<th data-options="field:'root_name'" width="15%" align="left">所属科目名称</th>
				<th data-options="field:'icon_name'" width="10%" align="left">节点图标</th>
				<th data-options="field:'create_time'"  width="15%" align="left">创建时间</th>

			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">

		<a href="#" class="easyui-menubutton" data-options="menu:'#addmenu',iconCls:'add'">新增</a> 
			
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyGridData('modifyCatalogWindow','catalogList','catalog_id','${ctx}/system/catalog/goModify.jhtml','请选择你要修改的科目数据');">修改</a>

		<a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('catalogList','catalog_id','${ctx}/system/catalog/deleteCatalog.jhtml','你选择你要删除数据','删除分类会导致和分类所属数据失去关联，请保证该分类下没有业务数据。请谨慎操作，确认要删除吗？','1');">删除</a>
	</div>
    <div id="addmenu" style="width:150px;">
		<div data-options="name:'addCatalog',iconCls:'addCatalog'">新增分类</div>
		<div data-options="name:'addSubject',iconCls:'book'">新增科目（一级分类）</div>
	</div>
	<div id="addCatalogWindow" class="easyui-window" title="新增分类"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 370px; background-color: #FFFFFF">
	<div id="addSubjectWindow" class="easyui-window" title="新增科目"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 330px; background-color: #FFFFFF">
	<div id="modifyCatalogWindow" class="easyui-window" title="修改科目"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 250px; background-color: #FFFFFF">
	
</body>


