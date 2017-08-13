<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
 function submitShopPostData(){
             var innodes = $('#menuTree').tree('getChecked','indeterminate'); 
             var nodes = $('#menuTree').tree('getChecked');
			 var menuids = '';
			  for(var i=0; i<innodes.length; i++){  
                if (menuids != ''){ 
                   menuids += ','
                };  
                menuids += innodes[i].id;  
             }  
			 for(var i=0; i<nodes.length; i++){
			    if (menuids != ''){
			         menuids+= ','
			    };  
                menuids += nodes[i].id;  
			 }
			if(menuids==''){
			  $.messager.alert('警告信息', '请选择职位要授权的菜单', 'warning');
				return ;
			}
			$("#menuids").val(menuids);
			submitFormData('modifyForm','dataList','modifyWindow')
 }
</script>
<div class="easyui-layout" style="width:100%;height:100%;">
<div data-options="region:'east',split:false"  style="width:350px;">
<div class="easyui-panel" style="padding:5px" title="授权菜单" data-options="fit:true">
		<ul id="menuTree" class="easyui-tree" data-options="url:'${ctx }/system/shopPost/loadGrantMenuTree.jhtml?post_id=${shopPostPO.post_id }',method:'get',checkbox:true,animate:true,lines:true"></ul>
	</div>
</div>
<div data-options="region:'center'" >
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;"  title="职位信息">
			<form id="modifyForm" action="${ctx }/system/shopPost/updateShopPost.jhtml"
				method="post" >
			 <input type="hidden" name="menuids" id="menuids"  />
			 <input type="hidden" name="post_id" value="${shopPostPO.post_id }"  />
			 <input type="hidden" name="old_post_code" value="${shopPostPO.post_code }"  />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						
					<tr>
						<td align="right" width="100px">职位编码：</td>
						<td><input  type="text"  name="post_code" value="${shopPostPO.post_code}" required="true"
							class="easyui-textbox" data-options="validType:'loginname'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">职位名称：</td>
						<td><input name="post_name" type="text" value="${shopPostPO.post_name }"
						class="easyui-textbox" data-options="validType:'length[1,50]'"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">备注：</td>
						<td><input name="post_desc" type="text"  value="${shopPostPO.post_desc }"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,200]'"
							style="width: 280px; height: 140px"></td>
					</tr>
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitShopPostData()" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyWindow')" style="width: 70px">关闭</a>
		</div>
	</div>
   </div>
   </div>
</body>
</html>