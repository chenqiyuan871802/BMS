<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyRoleForm" action="${ctx }/system/role/updateRole.jhtml"
				method="post" >
			 <input type="hidden"  name="role_id" value="${rolePO.role_id}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="100px">角色名称：</td>
						<td><input  type="text"  name="role_name" value="${rolePO.role_name }" required="true"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					
					
				  <tr>
						<td align="right" width="100px">角色类型：</td>
						<td><input name="role_type" type="text" value="${rolePO.role_type }"   editable="false" 
							class="easyui-combobox"   data-options="data:role_typeStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					
				  <tr>
						<td align="right" width="100px">当前状态：</td>
						<td><input name="status" type="text" value="${rolePO.status }"   editable="false" 
							class="easyui-combobox"   data-options="data:statusStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
				  <tr>
						<td align="right" width="100px">编辑模式：</td>
						<td><input name="edit_mode" type="text" value="${rolePO.edit_mode }"   editable="false" 
							class="easyui-combobox"   data-options="data:edit_modeStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					
					<tr>
						<td align="right" width="100px">角色描述：</td>
						<td><input name="role_remark" type="text" value="${rolePO.role_remark}"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,400]'"
							style="width: 280px; height: 90px"></td>
					</tr>
				
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('modifyRoleForm','roleList','modifyRoleWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyRoleWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>