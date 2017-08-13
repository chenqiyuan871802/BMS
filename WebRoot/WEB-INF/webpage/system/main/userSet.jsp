<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
//提交菜单数据并进行刷新
function submitUserSetData(formId, windowId){
	var style='${userPO.style}'
	var styleNew=$("#styleId").combobox('getValue');
   $('#'+formId).form('submit', {
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data) {
					if (data.appcode=="1") {
						showMsg('提示', '个人信息设置成功');
					     $('#'+windowId).window('close');
					     if(style!=styleNew){
					    	 $.messager
								.confirm(
										'确认',
										'你重置了界面风格，你确定要马上刷新吗？',
										function(r) {
											if (r) {
												 window.location.href = '${ctx}/system/login/goMain.jhtml';
											}
										});
					    	
					     }
					    
					}else {
						$.messager.alert('错误信息', '个人信息设置失败', 'error');
					}
				} else {
					$.messager.alert('错误信息', '操作失败', 'error');
				}
			},
			onLoadeError:function(){
			         $.messager.alert('错误信息', '操作失败', 'error');
			}
		});

}

</script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyUserForm"
				action="${ctx }/system/updateUserSet.jhtml" method="post">
				<div class="easyui-panel" title="基础信息">
					<input type="hidden" name="user_id" value="${userPO.user_id}" />
					<table cellpadding=5 cellspacing=0 width=100% align="center"
						class="formTabel">

						<tr>
							<td align="right" width="80px">用户账号：</td>
							<td><input type="text" name="account" disabled="true"
								value="${userPO.account }" class="easyui-textbox"
								disabled="disabled" data-options="validType:'account'"
								style="width: 250px; height: 30px"></td>
							<td align="right" width="80px">用户名：</td>
							<td><input type="text" name="username"
								value="${userPO.username}" class="easyui-textbox"
								required="true" data-options="validType:'length[1,50]'"
								style="width: 250px; height: 30px"></td>
						</tr>

						<tr>
							<td align="right" width="80px">所属机构：</td>
							<td><input type="text" name="dept_id"
								value="${userPO.dept_id}" disabled="disabled"
								class="easyui-combotree"
								data-options="url:'${ctx }/system/dept/loadDeptTree.jhtml',method:'get'"
								required="true" style="width: 250px; height: 30px"></td>
							<td align="right" width="80px">性别：</td>
							<td><input type="text" name="sex" editable="false"
								value="${userPO.sex}" class="easyui-combobox"
								data-options="data:sexStore,textField:1,valueField:0"
								style="width: 250px; height: 30px"></td>
						</tr>
						<tr>
							<td align="right" width="80px">手机：</td>
							<td><input type="text" name="mobile"
								value="${userPO.mobile }" class="easyui-textbox"
								data-options="validType:'mobile'"
								style="width: 250px; height: 30px"></td>
							<td align="right" width="80px">邮箱：</td>
							<td><input type="text" name="email" value="${userPO.email }"
								class="easyui-textbox" validType="email"
								style="width: 250px; height: 30px"></td>
						</tr>
						<tr>
							<td align="right" width="80px">QQ：</td>
							<td><input type="text" name="qq" value="${userPO.qq}"
								class="easyui-textbox" data-options="validType:'QQ'"
								style="width: 250px; height: 30px"></td>
							<td align="right" width="80px">微信：</td>
							<td><input type="text" name="wechat"
								value="${userPO.wechat}" class="easyui-textbox"
								validType="wbchat" style="width: 250px; height: 30px"></td>
						</tr>
						<tr>
							<td align="right" width="80px">证件号：</td>
							<td><input type="text" name="idno" value="${userPO.idno}"
								class="easyui-textbox" validType="idcard"
								style="width: 250px; height: 30px"></td>
							<td align="right" width="80px">联系地址：</td>
							<td><input type="text" name="address"
								value="${userPO.address}" class="easyui-textbox"
								data-options="validType:'length[0,200]'"
								style="width: 250px; height: 30px"></td>
						</tr>
						<tr>
							<td align="right" width="80px">备注：</td>
							<td colspan="3"><input type="text" name="remark"
								value="${userPO.remark }" class="easyui-textbox"
								data-options="validType:'length[0,400]'"
								style="width: 660px; height: 70px"></td>

						</tr>
					</table>
				</div>
				<div class="easyui-panel" title="配置信息">
					<table cellpadding=5 cellspacing=0 width=100% align="center"
						class="formTabel">
						<tr>
							<td align="right" width="80px">界面风格：</td>
							<td colspan="3">
							<input type="text" id="styleId" name="style" editable="false"
								value="${userPO.style}" class="easyui-combobox"
								data-options="data:layout_styleStore,textField:1,valueField:0"
								style="width: 250px; height: 30px">
							</td>

						</tr>
					</table>
				</div>
			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"
				href="javascript:void(0)"
				onclick="submitUserSetData('modifyUserForm','modifyUserWindow')"
				style="width: 70px">保存</a> &nbsp; <a class="easyui-linkbutton"
				data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyUserWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>