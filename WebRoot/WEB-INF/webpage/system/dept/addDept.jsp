<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addDeptForm" action="${ctx }/system/dept/saveDept.jhtml"
				method="post" >
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="100px">机构名称：</td>
						<td><input name="dept_name" type="text"
							class="easyui-textbox"   data-options="validType:'length[1,100]'"
							style="width: 250px; height: 30px" required="true"></td>
						<td align="right" width="100px">上级机构：</td>
						<td><input  type="text"  name="parent_id"  value="${parent_id}"	class="easyui-combotree"
							data-options="url:'${ctx }/system/dept/loadDeptTree.jhtml',method:'get'" required="true"
							style="width: 250px; height: 30px" ></td>
					</tr>
				  
					<tr>
						<td align="right" width="100px">机构代码：</td>
						<td><input name="dept_code" type="text"
							class="easyui-textbox" data-options="validType:'length[0,50]'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="100px">主要负责人：</td>
						<td><input name="manager" type="text"
							class="easyui-textbox" data-options="validType:'length[0,50]'"
							style="width: 250px; height: 30px" ></td>
					</tr>
				  
					<tr>
						<td align="right" width="100px">电话：</td>
						<td><input name="phone" type="text"
							class="easyui-textbox" data-options="validType:'phoneAndMobile'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="100px">传真：</td>
						<td><input name="fax" type="text"
							class="easyui-textbox" data-options="validType:'fax'"
							style="width: 250px; height: 30px" ></td>
					</tr>
				  
					
					<tr>
						<td align="right" width="100px">地址：</td>
						<td><input name="address" type="text"
							class="easyui-textbox" data-options="validType:'length[0,200]'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="100px">节点图标：</td>
						<td><input name="icon_name" type="text"
							class="easyui-textbox" data-options="validType:'length[0,100]'"
							style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
					<td align="right" width="100px">自动展开：</td>
						<td><input name="is_auto_expand" type="text" value="1"  editable="false" 
							class="easyui-combobox"   data-options="data:is_auto_expandStore,textField:1,valueField:0"  
							style="width: 250px; height: 30px" ></td>
						
							<td align="right" width="100px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 250px; height: 30px" required="true"></td>
					</tr>
					
					
				   <tr>
						<td align="right" width="100px">备注：</td>
						<td colspan="3"><input name="remark" type="text"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,500]'"
							style="width: 670px; height: 80px"></td>
					</tr>
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitDeptData('addDeptForm','addDeptWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addDeptWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>