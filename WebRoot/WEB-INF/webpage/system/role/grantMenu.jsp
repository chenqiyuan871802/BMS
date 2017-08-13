<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
 function submitGrantMenuData(){
             var innodes = $('#menuTree').tree('getChecked','indeterminate'); 
             var nodes = $('#menuTree').tree('getChecked');
			 var menuids = '';
			  for(var i=0; i<innodes.length; i++){  
                if (menuids != ''){ 
                   menuids += ','
                };  
                menuids += innodes[i].id;  
             }  
			 for(var i=0; i<nodes.length; i++){
			    if (menuids != ''){
			         menuids+= ','
			    };  
                menuids += nodes[i].id;  
			 }
			if(menuids==''){
			  $.messager.alert('警告信息', '请选择你要授权的菜单', 'warning');
				return ;
			}
			$("#menuids").val(menuids);
			submitFormData('grantMenuForm','roleList','grantMenuWindow');
 }
</script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="grantMenuForm" action="${ctx }/system/saveRoleMenu.jhtml"
				method="post" >
				<input type="hidden" name="role_id" value="${role_id}"  />
				<input type="hidden" name="menuids" id="menuids"  />
			<ul id="menuTree" class="easyui-tree" data-options="url:'${ctx }/system/loadGrantMenuTree.jhtml?role_id=${role_id}',method:'get',checkbox:true,animate:true,lines:true,"></ul>
				

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitGrantMenuData()" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('grantMenuWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>