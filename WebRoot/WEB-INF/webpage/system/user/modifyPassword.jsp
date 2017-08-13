<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyPasswordForm" action="${ctx }/system/user/updatePassword.jhtml"
				method="post" >
			<input type="hidden" name="user_id" value="${user_id}"  />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="100px">密码：</td>
						<td><input id="modifyPassword" type="password"  name="password"	class="easyui-textbox" required="true" data-options="validType:'length[1,50]'" style="width: 280px; height: 30px" ></td>
					</tr>
					
					<tr>
						<td align="right" width="100px">确认密码：</td>
						<td><input  type="password"  name="repwd"	class="easyui-textbox" required="true" validType="equals['#modifyPassword']" style="width: 280px; height: 30px" ></td>
					</tr>

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('modifyPasswordForm','userList','modifyPasswordWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyPasswordWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>