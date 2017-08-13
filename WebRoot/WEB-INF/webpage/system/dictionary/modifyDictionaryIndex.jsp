<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyDictionaryIndexForm" action="${ctx }/system/dictionary/updateDictionaryIndex.jhtml"
				method="post" >
			<input type="hidden"  name="dic_index_id" value="${dictionaryIndexPO.dic_index_id}" />
			   <input type="hidden"  name="dic_key_old" value="${dictionaryIndexPO.dic_key}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						<td align="right" width="100px">所属分类：</td>
						<td><input name="catalog_id" type="text"  value="${dictionaryIndexPO.catalog_id }"
							class="easyui-combotree"
							data-options="url:'${ctx }/system/catalog/loadCatalogTree.jhtml?root_key=DIC_TYPE',method:'get'"
							style="width: 280px; height: 30px" required="true" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">字典标识：</td>
						<td><input  type="text"  name="dic_key" value="${dictionaryIndexPO.dic_key }" required="true"
							class="easyui-textbox" data-options="validType:'keyname'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">字典名称：</td>
						<td><input  type="text"  name="dic_name" value="${dictionaryIndexPO.dic_name}" required="true"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					
				 
					<tr>
						<td align="right" width="100px">备注：</td>
						<td><input name="dic_remark" type="text"  value="${dictionaryIndexPO.dic_remark}"   class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,1000]'"
							style="width: 280px; height: 80px"></td>
					</tr>
				
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitDictionIndexData('modifyDictionaryIndexForm','modifyDictionaryIndexWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyDictionaryIndexWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>