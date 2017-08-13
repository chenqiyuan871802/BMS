<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="is_auto_expand,wechat_menu_type"/>
<IMS:codeFormatter fields="is_auto_expand,wechat_menu_type"/>
<script type="text/javascript">
$(function(){
   $('#addmenu').menu({    
    onClick:function(item){    
      
      if(item.name=='addSecondMenu'){
        var menuRow = $('#menuList').treegrid('getSelected');
        
		if(menuRow!=null){
			 var parent_id=menuRow.parent_id;
			 if(parent_id=='0'){
				 var menu_id =menuRow.menu_id;
				 showWindow('addSecondMenuWindow','${ctx}/system/wechatMenu/goAddSecondMenu.jhtml?menu_id='+menu_id);
			 }else{
				 $.messager.alert('警告信息', '微信最多只能创建二级菜单,请选择一级菜单', 'warning'); 
			 }
		     
		}else{
			$.messager.alert('警告信息', '请选择一级菜单', 'warning');
		}
      }else{
         showWindow('addFirstMenuWindow','${ctx}/system/wechatMenu/goAddFirstMenu.jhtml');
      }
     
    }    
});  
});
function modifyMenu(){
	 var menuRow = $('#menuList').treegrid('getSelected');
	 if(menuRow!=null){
		 var parent_id=menuRow.parent_id;
		 var menu_id =menuRow.menu_id;
		 if(parent_id=='0'){
			 showWindow('modifyFirstMenuWindow','${ctx}/system/wechatMenu/goModifyFirstMenu.jhtml?menu_id='+menu_id);
		 }else{
			 showWindow('modifySecondMenuWindow','${ctx}/system/wechatMenu/goModifySecondMenu.jhtml?menu_id='+menu_id);
		 }
		 
	 }else{
		 $.messager.alert('警告信息', '请选择你要修改的菜单', 'warning');
	 }
}

</script>
</head>
<body style="margin: 0; padding: 0">
	<table id="menuList"  class="easyui-treegrid" style="width: 100%; height: 100%; padding: 0px;" 
		data-options="
				url: '${ctx }/system/wechatMenu/listWechatMenu.jhtml',
				method: 'get',
				lines: true,
				toolbar:'#toolbar',
				fit:'true',
				rownumbers: true,
				idField: 'menu_id',
				treeField: 'menu_name'
			">
		<thead>
			<tr>
				<th data-options="field:'menu_name'" width="15%" align="left">菜单名称</th>
				<th data-options="field:'is_auto_expand'" formatter="is_auto_expandFormatter" width="6%" align="center">自动展开</th>
				<th data-options="field:'menu_type'" formatter="wechat_menu_typeFormatter" width="15%" align="center">菜单类型</th>
				<th data-options="field:'menu_key'" width="12%" align="center">菜单键值</th>
				<th data-options="field:'url'" width="20%" align="center">菜单URL</th>
				<th data-options="field:'media_id'" width="12%" align="center">永久素材编号</th>
				<th data-options="field:'sort_no'" width="6%" align="center">排序号</th>
				<th data-options="field:'create_time'"  width="13%" align="center">创建时间</th>

			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">

		<a href="#" class="easyui-menubutton" data-options="menu:'#addmenu',iconCls:'add'">新增</a> 
			
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyMenu()">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="wechat" plain="true"
			onclick="doAjax('${ctx}/system/wechatMenu/syncWechatMenu.jhtml','','你确定要把菜单同步到微信吗?')">同步微信</a>

		<a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('menuList','menu_id','${ctx}/system/wechatMenu/deleteWechatMenu.jhtml','你选择你要删除菜单','你确认要删除该菜单吗？','1');">删除</a>
	</div>
    <div id="addmenu" style="width:150px;">
        <div data-options="name:'addFirstMenu',iconCls:'book'">新增一级菜单</div>
		<div data-options="name:'addSecondMenu',iconCls:'addCatalog'">新增二级菜单</div>
		
	</div>
	<div id="addSecondMenuWindow" class="easyui-window" title="新增二级菜单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 470px; background-color: #FFFFFF">
	<div id="addFirstMenuWindow" class="easyui-window" title="新增一级菜单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 450px; background-color: #FFFFFF">
	<div id="modifySecondMenuWindow" class="easyui-window" title="修改二级菜单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 470px; background-color: #FFFFFF">
	<div id="modifyFirstMenuWindow" class="easyui-window" title="修改一级菜单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 450px; background-color: #FFFFFF">
	
	
</body>


