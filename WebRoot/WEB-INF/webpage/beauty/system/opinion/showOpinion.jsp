<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
					
					<tr>
						<td align="right" width="110px">手机号码：</td>
						<td >${opinionPO.mobile}</td>
						
					</tr>
					<tr>
						<td align="right" width="110px">发送时间：</td>
						<td ><fmt:formatDate value="${opinionPO.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						
					</tr>
					<tr>
						<td align="right" width="110px">内容：</td>
						<td >${opinionPO.content}</td>
						
					</tr>
					

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('showOpinionWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>