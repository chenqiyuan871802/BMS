<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div >
	
				<input type="hidden" name="custom_user_id" value="${customUserPO.custom_user_id }"  />
				<input type="hidden" name="mobile_old" value="${customUserPO.mobile }"  />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="110px">手机：</td>
						<td>${customUserPO.mobile }</td>
						<td align="right" width="110px">头像：</td>
						<td>
						<c:if test="${ empty customUserPO.photo}">
						<img src="${ctx }/static/common/images/system/person_img.png" style="width:100px;height:100px" /></c:if>
						<c:if test="${not empty customUserPO.photo}"><img src="${ctx}/${customUserPO.photo }" style="width:150px;height:150px" /></c:if>
						
					</tr>
					<tr>
					<td align="right" width="110px">姓名：</td>
						<td>
						${customUserPO.username }
						</td>
						<td align="right" width="110px">性别：</td>
						<td>
						<IMS:codeOut codeKey="${customUserPO.sex}" field="sex"/>
						</td>
						
					</tr>
					
				 <tr>
				 <td align="right" width="110px">昵称：</td>
						<td>${customUserPO.nikename }</td>
						<td align="right" width="100px">出生日期：</td>
						<td>
						<fmt:formatDate value="${customUserPO.born_date}" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					
					<tr>
						<td align="right" width="100px">电话：</td>
						<td>
						${customUserPO.phone }
						</td>
						<td align="right" width="110px">QQ：</td>
						<td>
						${customUserPO.qq }</td>
						
					</tr>
					<tr>
					<td align="right" width="110px">邮箱：</td>
						<td>${customUserPO.email}</td>
						<td align="right" width="110px">通信地址：</td>
						<td >
						${customUserPO.address}
						</td>
						
					</tr>
					<tr>
					<td align="right" width="110px">微信标识号：</td>
						<td>${customUserPO.openid}</td>
						<td align="right" width="110px">微信状态：</td>
						<td >
						<IMS:codeOut codeKey="${customUserPO.wechat_status}" field="wechat_status"/>
						</td>
						
					</tr>
					
					<tr>
					<td align="right" width="110px">注册方式：</td>
						<td><IMS:codeOut codeKey="${customUserPO.enroll_mode}" field="enroll_mode"/></td>
						<td align="right" width="110px">注册时间：</td>
						<td >
						<fmt:formatDate value="${customUserPO.enroll_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						
					</tr>
					<tr>
						<td align="right" width="110px">备注：</td>
						<td colspan="3">
						${customUserPO.remark}
						</td>
						
					</tr>
				  
				

				</table>

			</form>
	</div>

</body>
</html>