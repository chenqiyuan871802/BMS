<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addUserForm" action="${ctx }/system/user/saveUser.jhtml"
				method="post" >
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="80px">用户账号：</td>
						<td><input  type="text"  name="account"	class="easyui-textbox" required="true" data-options="validType:'account'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="80px">用户名：</td>
						<td><input  type="text"  name="username"	class="easyui-textbox" required="true" data-options="validType:'length[1,50]'" style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="80px">密码：</td>
						<td><input  type="password"  name="password" id="password"	class="easyui-textbox" required="true" data-options="validType:'length[1,20]'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="80px">确认密码：</td>
						<td><input  type="password"  name="repwd"	class="easyui-textbox" required="true" validType="equals['#password']" style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="80px">所属机构：</td>
						<td><input  type="text"  name="dept_id" value="${dept_id}"	class="easyui-combotree"
							data-options="url:'${ctx }/system/dept/loadDeptTree.jhtml',method:'get'" required="true"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="80px">性别：</td>
						<td><input type="text"  name="sex"	 editable="false"  value="3"
							class="easyui-combobox"   data-options="data:sexStore,textField:1,valueField:0"style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="80px">手机：</td>
						<td><input type="text"  name="mobile"	class="easyui-textbox"  data-options="validType:'mobile'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="80px">邮箱：</td>
						<td><input  type="text"   name="email"	class="easyui-textbox" validType="email" style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="80px">QQ：</td>
						<td><input type="text"  name="qq"	class="easyui-textbox"  data-options="validType:'QQ'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="80px">微信：</td>
						<td><input  type="text"   name="wechat"	class="easyui-textbox" validType="wbchat" style="width: 250px; height: 30px" ></td>
					</tr>
					 <tr>
						<td align="right" width="80px">证件号：</td>
						<td><input  type="text"   name="idno"	class="easyui-textbox" validType="idcard" style="width: 250px; height: 30px" ></td>
					  <td align="right" width="80px">联系地址：</td>
					  <td><input type="text"  name="address"	class="easyui-textbox"  data-options="validType:'length[0,200]'" style="width: 250px; height: 30px" ></td>
					 </tr>
					 <tr>
						<td align="right" width="80px">用户状态：</td>
						<td><input  type="text"   name="status"	 value="1"  editable="false"  required="true"
							class="easyui-combobox"   data-options="data:user_statusStore,textField:1,valueField:0" style="width: 250px; height: 30px"></td>
					  <td align="right" width="80px"> 锁定次数：</td>
					  <td>
					  <input name="lock_num" type="text"
							class="easyui-numberspinner" value="5" data-options="min:3,max:100,required:true"
							style="width: 250px; height: 30px" required="true">
					  </td>
					 </tr>
					
					<tr>
						<td align="right" width="80px">备注：</td>
						<td colspan="3">
						<input type="text"  name="remark"	class="easyui-textbox"  data-options="validType:'length[0,400]'" style="width: 660px; height: 70px" >
						</td>
						
					</tr>
				  
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('addUserForm','userList','addUserWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addUserWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>