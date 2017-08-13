<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addCatalogForm" action="${ctx }/system/catalog/saveCatalog.jhtml"
				method="post" >
				<input type="hidden"  name="parent_id" value="${catalogPO.catalog_id}" />
				<input type="hidden"  name="root_key" value="${catalogPO.root_key}" />
				<input type="hidden"  name="root_name" value="${catalogPO.root_name}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						<td align="right" width="100px">科目标识键：</td>
						<td><input  type="text" value="${catalogPO.root_key}"
							class="easyui-textbox" data-options="validType:'length[1,50]'" 
							style="width: 280px; height: 30px" disabled="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">科目名称：</td>
						<td><input  type="text" value="${catalogPO.root_name }"
							class="easyui-textbox" data-options="validType:'length[1,1000]'"
							style="width: 280px; height: 30px" disabled="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">上级分类：</td>
						<td><input  type="text" value="${catalogPO.catalog_name }"
							class="easyui-textbox" data-options="validType:'length[1,1000]'"
							style="width: 280px; height: 30px" disabled="true"></td>
					</tr>
				   <tr>
						<td align="right" width="100px">分类名称：</td>
						<td><input name="catalog_name" type="text"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">自动展开：</td>
						<td><input name="is_auto_expand" type="text" value="1"  editable="false" 
							class="easyui-combobox"   data-options="data:is_auto_expandStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">节点图标：</td>
						<td><input name="icon_name" type="text"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
				
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('addCatalogForm','catalogList','addCatalogWindow','1')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addCatalogWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>