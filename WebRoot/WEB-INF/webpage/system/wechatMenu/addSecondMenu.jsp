<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
$(function(){
	
	$("#second_menu_type").combobox({  
		onSelect:function(item){
			var menu_type=item[0];
			if(menu_type=='view'){
				$("#second_url").textbox({required:true})
			}else{
				$("#second_url").textbox({required:false})
			}
			if(menu_type=='media_id'||menu_type=='view_limited'){
				$("#second_media_id").textbox({required:true})
			}else{
				$("#second_media_id").textbox({required:false})
			}
			
		}
	})
	
})

</script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addSecondMenuForm" action="${ctx }/system/wechatMenu/saveWechatMenu.jhtml"
				method="post" >
				<input type="hidden"  name="parent_id" value="${wechatMenuPO.menu_id}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						<td align="right" width="100px">上级菜单名称：</td>
						<td><input  type="text" value="${wechatMenuPO.menu_name}" disabled="true"
							class="easyui-textbox" 
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
				<tr>
						<td align="right" width="100px">菜单名称：</td>
						<td><input name="menu_name" type="text"
							class="easyui-textbox" data-options="validType:'length[1,7]'"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">菜单类型：</td>
						<td><input name="menu_type" id="second_menu_type" type="text" value="click"  editable="false" 
							class="easyui-combobox"   data-options="data:wechat_menu_typeStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">菜单键值：</td>
						<td><input name="menu_key"  type="text" 
							class="easyui-textbox" data-options="validType:'length[0,30]'"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="100px">菜单URL：</td>
						<td><input name="url" id="second_url"  type="text" 
							class="easyui-textbox" data-options="validType:'length[1,400]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="100px">永久素材编号：</td>
						<td><input name="media_id" id="second_media_id" type="text" 
							class="easyui-textbox" data-options="validType:'length[0,100]'"
							style="width: 280px; height: 30px" ></td>
					</tr>
					
					<tr>
						<td align="right" width="100px">自动展开：</td>
						<td><input name="is_auto_expand" type="text" value="1"  editable="false" 
							class="easyui-combobox"   data-options="data:is_auto_expandStore,textField:1,valueField:0"  
							style="width: 280px; height: 30px" ></td>
					</tr>
				
					<tr>
						<td align="right" width="100px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 280px; height: 30px" required="true"></td>
					</tr>
				  
					<tr>
						<td align="right" width="100px">备注：</td>
						<td><input name="remark" type="text"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[0,200]'"
							style="width: 280px; height: 50px"></td>
					</tr>
				
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('addSecondMenuForm','menuList','addSecondMenuWindow','1')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addSecondMenuWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>