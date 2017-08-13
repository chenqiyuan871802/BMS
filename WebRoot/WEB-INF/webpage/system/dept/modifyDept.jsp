<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
//变换组织机构只能变换当前同级组织机构或者负机构
function selectParentDept(treeNode){
	var id=treeNode.id;
	var deptId=$("#dept_id").val();
	var parent_id_old=$("#parent_id_old").val();
	//获取comboxtree 中tree对象
	var  parentTree= $('#parentTree').combotree('tree');
	var parentNode=parentTree.tree('find',deptId);
	var  nodes=parentTree.tree('getChildren',parentNode.target);
	if(deptId==id){
		$('#parentTree').combotree('setValue', parent_id_old);//设置选中该节点
		$.messager.alert('警告信息', '上级机构不能选择当前机构或当前机构下面的机构，请重新选择上级机构', 'warning');
		return ;
	}
	for(var i=0; i<nodes.length; i++){
		var nodeId=nodes[i].id;
		if(id==nodeId){
			$('#parentTree').combotree('setValue',parent_id_old);//设置选中该节点
			$.messager.alert('警告信息', '上级机构不能选择当前机构或当前机构下面的机构，请重新选择上级机构', 'warning');
		
			return ;
		}
	}
	
  
  
}
</script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyDeptForm" action="${ctx }/system/dept/updateDept.jhtml"
				method="post" >
				
				 <input type="hidden"  name="dept_id" id="dept_id" value="${deptPO.dept_id}" />
				 <input type="hidden"  id="parent_id_old" name="parent_id_old" value="${deptPO.parent_id}" />
				 <input type="hidden"  name="cascade_id" value="${deptPO.cascade_id}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="100px">机构名称：</td>
						<td><input name="dept_name" value="${deptPO.dept_name }"  type="text"
							class="easyui-textbox" data-options="validType:'length[1,100]'"
							style="width: 250px; height: 30px" required="true"></td>
						<td align="right" width="100px">上级机构：</td>
						<td><input  type="text" id="parentTree" name="parent_id"  <c:if test="${deptPO.dept_id=='0'}">disabled="true" value="0" </c:if>   <c:if test="${deptPO.dept_id!='0'}"> value="${deptPO.parent_id }"  </c:if>	
						class="easyui-combotree"
							data-options="url:'${ctx }/system/dept/loadDeptTree.jhtml',method:'get',onSelect:selectParentDept" required="true"
							style="width: 250px; height: 30px" ></td>
					</tr>
				  
					<tr>
						<td align="right" width="100px">机构代码：</td>
						<td><input name="dept_code" type="text" value="${deptPO.dept_code }"
							class="easyui-textbox" data-options="validType:'length[0,50]'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="100px">主要负责人：</td>
						<td><input name="manager" type="text" value="${deptPO.manager }"
							class="easyui-textbox" data-options="validType:'length[0,50]'"
							style="width: 250px; height: 30px" ></td>
					</tr>
				  
					<tr>
						<td align="right" width="100px">电话：</td>
						<td><input name="phone" type="text" value="${deptPO.phone}"
							class="easyui-textbox" data-options="validType:'phoneAndMobile'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="100px">传真：</td>
						<td><input name="fax" type="text" value="${deptPO.fax}"
							class="easyui-textbox" data-options="validType:'fax'"
							style="width: 250px; height: 30px" ></td>
					</tr>
				  
					
					<tr>
						<td align="right" width="100px">地址：</td>
						<td><input name="address" type="text" value="${deptPO.address}"
							class="easyui-textbox" data-options="validType:'length[0,200]'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="100px">节点图标：</td>
						<td><input name="icon_name" type="text" value="${deptPO.icon_name}"
							class="easyui-textbox" data-options="validType:'length[0,100]'"
							style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
					<td align="right" width="100px">自动展开：</td>
						<td><input name="is_auto_expand" type="text" value="${deptPO.is_auto_expand }"  editable="false" 
							class="easyui-combobox"   data-options="data:is_auto_expandStore,textField:1,valueField:0"  
							style="width: 250px; height: 30px" ></td>
						
							<td align="right" width="100px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="${deptPO.sort_no }" data-options="min:1,max:1000000,required:true"
							style="width: 250px; height: 30px" required="true"></td>
					</tr>
					
					
				   <tr>
						<td align="right" width="100px">备注：</td>
						<td colspan="3"><input name="remark" type="text" value="${deptPO.remark }"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,500]'"
							style="width: 670px; height: 80px"></td>
					</tr>
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitDeptData('modifyDeptForm','modifyDeptWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyDeptWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>