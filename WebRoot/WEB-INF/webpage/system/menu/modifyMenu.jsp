<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyMenuForm" action="${ctx }/system/menu/updateMenu.jhtml"
				method="post" >
				 <input type="hidden"  name="menu_id" value="${menuPO.menu_id}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				    
					<tr>
						<td align="right" width="100px">上级菜单：</td>
						<td><input  type="text"  name="parent_id" value="${menuPO.parent_id }" disabled="true"	 class="easyui-combotree"
							data-options="url:'${ctx }/system/menu/loadFirstMenuTree.jhtml',method:'get',value:0" required="true"
							style="width: 280px; height: 30px" ></td>
					</tr>
				   <tr>
						<td align="right" width="100px">菜单名称：</td>
						<td><input name="menu_name" type="text" value="${menuPO.menu_name}"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
				   <tr>
						<td align="right" width="100px">菜单URL：</td>
						<td><input name="url" type="text" value="${menuPO.url}"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">菜单类型：</td>
						<td><input name="menu_type" type="text"  value="${menuPO.menu_type}"   editable="false" 
							class="easyui-combobox"   data-options="data:menu_typeStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">自动展开：</td>
						<td><input name="is_auto_expand" type="text" value="${menuPO.is_auto_expand}"  editable="false" 
							class="easyui-combobox"   data-options="data:is_auto_expandStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">节点图标：</td>
						<td><input name="icon_name" type="text" value="${menuPO.icon_name}"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">当前状态：</td>
						<td><input name="status" type="text" value="${menuPO.status}"  editable="false" 
							class="easyui-combobox"   data-options="data:statusStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">编辑模式：</td>
						<td><input name="edit_mode" type="text" value="${menuPO.edit_mode}"   editable="false" 
							class="easyui-combobox"   data-options="data:edit_modeStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="${menuPO.sort_no}"  data-options="min:1,max:1000000,required:true"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
				   <tr>
						<td align="right" width="100px">备注：</td>
						<td><input name="remark" type="text" value="${menuPO.remark}"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,500]'"
							style="width: 280px; height: 60px"></td>
					</tr>
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitMenuData('modifyMenuForm','modifyMenuWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyMenuWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>