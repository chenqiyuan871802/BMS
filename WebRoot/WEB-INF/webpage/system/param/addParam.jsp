<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addParamForm" action="${ctx }/system/param/saveParam.jhtml"
				method="post" >
			
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						<td align="right" width="100px">所属分类：</td>
						<td><input name="catalog_id" type="text"  value="${catalog_id }"
							class="easyui-combotree"
							data-options="url:'${ctx }/system/catalog/loadCatalogTree.jhtml?root_key=PARAM_TYPE',method:'get'"
							style="width: 280px; height: 30px" required="true" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">参数名称：</td>
						<td><input  type="text"  name="param_name" required="true"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">参数键：</td>
						<td><input  type="text"  name="param_key" required="true"
							class="easyui-textbox" data-options="validType:'keyname'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					
				   <tr>
						<td align="right" width="100px">参数值：</td>
						<td><input name="param_value" type="text"
							class="easyui-textbox" data-options="validType:'length[1,500]'"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">当前状态：</td>
						<td><input name="status" type="text" value="1"  editable="false" 
							class="easyui-combobox"   data-options="data:statusStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">编辑模式：</td>
						<td><input name="edit_mode" type="text" value="1"  editable="false" 
							class="easyui-combobox"   data-options="data:edit_modeStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
				
					
					<tr>
						<td align="right" width="100px">备注：</td>
						<td><input name="param_remark" type="text"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,200]'"
							style="width: 280px; height: 80px"></td>
					</tr>
				
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('addParamForm','paramList','addParamWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addParamWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>