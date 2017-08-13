<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
  //设置原来的上级机构
  function lockParentNode(){
	  //获取树的对象
	  var moveTree=$("#deptTreeByMove");
	  //获取移动机构的上级机构节点
	  var parentNode = moveTree.tree('find', '${deptPO.parent_id}');
	  //设置选中
	  moveTree.tree('select', parentNode.target);

  }
  //树单击检查
  function treeOnClickCheck(treeNode){
	  var id=treeNode.id;
		var deptId=$("#dept_id").val();
		var parent_id_old=$("#parent_id_old").val();
		//获取comboxtree 中tree对象
	    var moveTree=$("#deptTreeByMove");
	    //当前机构上级机构节点
	    var parentNode = moveTree.tree('find', parent_id_old);
		  //当前机构机构节点
		var   deptNode=moveTree.tree('find',deptId);
		var  nodes=moveTree.tree('getChildren',deptNode.target);
		if(deptId==id){
			 
			  //恢复回原来的上级机构
			  moveTree.tree('select', parentNode.target);
			  $("#parent_id").val(parent_id_old);
			$.messager.alert('警告信息', '机构移动不允许移动到当前机构【'+deptNode.text+'】，系统将恢复原来的上级机构', 'warning');
			return ;
		}
		for(var i=0; i<nodes.length; i++){
			var nodeId=nodes[i].id;
			if(id==nodeId){
				 moveTree.tree('select', parentNode.target);
				 $("#parent_id").val(parent_id_old);
				$.messager.alert('警告信息', '机构移动不允许移动到当前机构【'+deptNode.text+'】下面的机构，其中你选择的机构【'+nodes[i].text+'】，系统将恢复原来的上级机构', 'warning');
				return ;
			}
		}
		$("#parent_id").val(id);
  }
</script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="moveDeptForm" action="${ctx }/system/dept/moveDept.jhtml"
				method="post" >
			 <input type="hidden"  name="parent_id" id="parent_id"  value="${deptPO.parent_id}"/>
			<input type="hidden"  name="dept_id" id="dept_id" value="${deptPO.dept_id}" />
			<input type="hidden"  id="parent_id_old" name="parent_id_old" value="${deptPO.parent_id}" />
			<input type="hidden"  name="cascade_id" value="${deptPO.cascade_id}" />
			 <ul id="deptTreeByMove" class="easyui-tree" data-options="url:'${ctx }/system/dept/loadDeptTree.jhtml',method:'get',onLoadSuccess:lockParentNode,onClick:treeOnClickCheck,animate:true,lines:true">
			 
			 </ul>
				

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitDeptData('moveDeptForm','moveDeptWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('moveDeptWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>