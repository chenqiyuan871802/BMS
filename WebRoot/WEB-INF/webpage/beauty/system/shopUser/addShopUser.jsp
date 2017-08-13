<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addShopUserForm" action="${ctx }/system/shopUser/saveShopUser.jhtml"
				method="post" enctype="multipart/form-data">
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="110px">员工账号：</td>
						<td><input  type="text"  name="account"	class="easyui-textbox" required="true" data-options="validType:'loginname'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">头像：</td>
						<td><input  type="text"    name="photo_file" type="text" 
							 class="easyui-filebox"   style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
					<td align="right" width="110px">姓名：</td>
						<td><input  type="text"  name="username"	class="easyui-textbox" required="true" data-options="validType:'length[1,50]'" style="width: 250px; height: 30px" ></td>
						
						<td align="right" width="110px">工号：</td>
						<td><input type="text"  name="work_number"	class="easyui-textbox"  data-options="validType:'length[0,50]'" required="true" style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="110px">密码：</td>
						<td><input  type="password"  name="password" id="password"	class="easyui-textbox" required="true" data-options="validType:'length[1,20]'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">确认密码：</td>
						<td><input  type="password"  name="repwd"	class="easyui-textbox" required="true" validType="equals['#password']" style="width: 250px; height: 30px" ></td>
					</tr>
					 <tr>
						<td align="right" width="110px">性别：</td>
						<td><input  type="text"   name="sex"	 value="2"  editable="false"  required="true"
							class="easyui-combobox"   data-options="data:sexStore,textField:1,valueField:0" style="width: 250px; height: 30px"></td>
						<td align="right" width="110px">用户状态：</td>
						<td><input  type="text"   name="status"	 value="1"  editable="false"  required="true"
							class="easyui-combobox"   data-options="data:staff_statusStore,textField:1,valueField:0" style="width: 250px; height: 30px"></td>
						
					 
					 </tr>
					<tr>
						<td align="right" width="110px">店铺名称：</td>
						<td><input  type="text"  name="shop_id"  required="true"	<c:if test="${not empty shop_id}">value="${shop_id}" readonly</c:if> 	class="easyui-combobox"	 editable="false"  data-options="url:'${ctx}/system/shopSys/queryShop.jhtml',method:'get',valueField:'shop_id',textField:'shop_name'
						,icons:[{
						iconCls:'icon-remove',
						 handler: function(e){
					                    $(e.data.target).combobox('clear')
				           }
					}]
						"   style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">职位：</td>
						<td><input  type="text"  name="post_code"	 class="easyui-combobox"	 data-options="url:'${ctx}/system/shopPost/queryShopPost.jhtml',method:'get',valueField:'post_code',textField:'post_name'" required="true"  style="width: 250px; height: 30px" ></td>
					</tr>
					
					<tr>
						<td align="right" width="110px">手机：</td>
						<td><input type="text"  name="mobile"	class="easyui-textbox"  data-options="validType:'mobile'"  required="true" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">身份证：</td>
						<td><input  type="text"   name="idno"	class="easyui-textbox" validType="idcard" required="true" style="width: 250px; height: 30px" ></td>
						
					</tr>
					<tr>
						<td align="right" width="100px">电话：</td>
						<td><input name="phone" type="text"
							class="easyui-textbox" data-options="validType:'phoneAndMobile'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">邮箱：</td>
						<td><input  type="text"   name="email"	class="easyui-textbox" validType="email" style="width: 250px; height: 30px" ></td>
						
					</tr>
					<tr>
						<td align="right" width="100px">出生日期：</td>
						<td><input name="born_date" type="text" class="easyui-datebox" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">入职日期：</td>
						<td><input  type="text"   name="entry_date"  value="${current_date }" required="true" class="easyui-datebox"	  style="width: 250px; height: 30px" ></td>
						
					</tr>
					
					<tr>
					<td align="right" width="110px">紧急联系人：</td>
						<td><input  type="text"   name="linkman" data-options="validType:'length[0,20]'"	class="easyui-textbox"  style="width: 250px; height: 30px" ></td>
						
						<td align="right" width="100px">紧急联系电话：</td>
						<td><input name="linkphone" type="text"
							class="easyui-textbox" data-options="validType:'phoneAndMobile'"
							style="width: 250px; height: 30px" ></td>
						
					</tr>
					<tr>
						<td align="right" width="110px">居住地址：</td>
						<td colspan="3">
						<input type="text"  name="address"	class="easyui-textbox"  data-options="validType:'length[0,100]'" style="width: 660px; height: 30px" >
						</td>
						
					</tr>
					<tr>
						<td align="right" width="110px">备注：</td>
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
			   onclick="submitFormData('addShopUserForm','shopUserList','addShopUserWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addShopUserWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>