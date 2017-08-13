<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="modifyCustomUserForm" action="${ctx }/system/customUser/updateCustomUser.jhtml"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="custom_user_id" value="${customUserPO.custom_user_id }"  />
				<input type="hidden" name="mobile_old" value="${customUserPO.mobile }"  />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="110px">手机：</td>
						<td><input type="text"  name="mobile" value="${customUserPO.mobile }" class="easyui-textbox"  data-options="validType:'mobile'"  required="true" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">头像：</td>
						<td>
						<c:if test="${ empty customUserPO.photo}">
						<img src="${ctx }/static/common/images/system/person_img.png" style="width:150px;height:150px" /></c:if>
						<c:if test="${not empty customUserPO.photo}"><img src="${ctx}/${customUserPO.photo }" style="width:150px;height:150px" /></c:if>
						<input  type="text"    name="photo_file" type="text" 
							 class="easyui-filebox"   style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
					<td align="right" width="110px">姓名：</td>
						<td><input  type="text"  name="username" value="${customUserPO.username }"	class="easyui-textbox" required="true" data-options="validType:'length[1,50]'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">性别：</td>
						<td><input  type="text"   name="sex"	 value="${customUserPO.sex}"  editable="false"  required="true"
							class="easyui-combobox"   data-options="data:sexStore,textField:1,valueField:0" style="width: 250px; height: 30px"></td>
						
					</tr>
					
				 <tr>
				 <td align="right" width="110px">昵称：</td>
						<td><input  type="text"  name="nikename" value="${customUserPO.nikename }"	class="easyui-textbox"  data-options="validType:'length[0,20]'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="100px">出生日期：</td>
						<td><input name="born_date" type="text" class="easyui-datebox"  value="<fmt:formatDate value="${customUserPO.born_date}" pattern="yyyy-MM-dd"/>"  style="width: 250px; height: 30px" ></td>
					</tr>
					
					<tr>
						<td align="right" width="100px">电话：</td>
						<td><input name="phone" type="text" value="${customUserPO.phone }"
							class="easyui-textbox" data-options="validType:'phoneAndMobile'"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">QQ：</td>
						<td><input  type="text"   name="qq"	value="${customUserPO.qq }" class="easyui-textbox" validType="QQ" style="width: 250px; height: 30px" ></td>
						
					</tr>
					<tr>
					<td align="right" width="110px">邮箱：</td>
						<td><input  type="text"   name="email" value="${customUserPO.email}"	class="easyui-textbox" validType="email" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">通信地址：</td>
						<td >
						<input type="text"  name="address" value="${customUserPO.address}"	class="easyui-textbox"  data-options="validType:'length[0,100]'" style="width: 250px; height: 30px" >
						</td>
						
					</tr>
					<tr>
						<td align="right" width="110px">备注：</td>
						<td colspan="3">
						<input type="text"  name="remark" value="${customUserPO.remark}"	class="easyui-textbox"  data-options="validType:'length[0,400]'" style="width: 660px; height: 70px" >
						</td>
						
					</tr>
				  
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('modifyCustomUserForm','customUserList','modifyCustomUserWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyCustomUserWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>