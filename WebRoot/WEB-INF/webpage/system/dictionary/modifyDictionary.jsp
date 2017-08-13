<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyDictionaryForm" action="${ctx }/system/dictionary/updateDictionary.jhtml"
				method="post" >
			 <input type="hidden"  name="dic_id" value="${dictionaryPO.dic_id}" />
			 <input type="hidden"  name="dic_index_id" value="${dictionaryPO.dic_index_id}" />
			 <input type="hidden"  name="dic_code_old" value="${dictionaryPO.dic_code}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
					<tr>
						<td align="right" width="100px">字典标识：</td>
						<td><input  type="text"  name="dic_key" value="${dictionaryIndexPO.dic_key }"
							class="easyui-textbox" disabled="true"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">字典名称：</td>
		 				<td><input  type="text"  name="dic_name" value="${dictionaryIndexPO.dic_name }" disabled="true"
							class="easyui-textbox" 
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">字典对照码：</td>
						<td><input  type="text"  name="dic_code" value="${dictionaryPO.dic_code }"  required="true" data-options="validType:'length[1,100]'"
							class="easyui-textbox" style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">字典对照值：</td>
						<td><input  type="text"  name="dic_value"  value="${dictionaryPO.dic_value }" required="true" data-options="validType:'length[1,100]'"
							class="easyui-textbox" style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">显示颜色：</td>
						<td><input  type="text"  name="show_color" value="${dictionaryPO.show_color}" data-options="validType:'length[0,20]'"
							class="easyui-textbox" style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">当前状态：</td>
						<td><input name="status" type="text" value="${dictionaryPO.status }"  editable="false" 
							class="easyui-combobox"   data-options="data:statusStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">编辑模式：</td>
						<td><input name="edit_mode" type="text" value="${dictionaryPO.edit_mode }"  editable="false" 
							class="easyui-combobox"   data-options="data:edit_modeStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="${dictionaryPO.sort_no}" data-options="min:1,max:1000000,required:true"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
				
				 
				
				
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('modifyDictionaryForm','dictionaryList','modifyDictionaryWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyDictionaryWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>