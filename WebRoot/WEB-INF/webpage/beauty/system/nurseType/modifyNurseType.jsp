<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyForm" action="${ctx }/system/nurseType/updateNurseType.jhtml"
				method="post" >
			 <input type="hidden"  name="type_id" value="${nurseTypePO.type_id }" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						
					<tr>
						<td align="right" width="100px">分类名称：</td>
						<td><input  type="text"  name="type_name" value="${nurseTypePO.type_name }" required="true"
							class="easyui-textbox" data-options="validType:'length[1,50]'"
							style="width: 280px; height: 30px" ></td>
					</tr> 
					<tr>
						<td align="right" width="100px">排序号：</td>
						<td><input name="sort_no" type="text" value="${nurseTypePO.sort_no }"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">备注：</td>
						<td><input name="type_remark" type="text" value="${nurseTypePO.type_remark }" class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,200]'"
							style="width: 280px; height: 80px"></td>
					</tr>
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('modifyForm','dataList','modifyWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>