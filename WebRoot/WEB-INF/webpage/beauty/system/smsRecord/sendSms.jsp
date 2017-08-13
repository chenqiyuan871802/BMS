<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="sendForm" action="${ctx }/system/smsRecord/sendSms.jhtml"
				method="post" >
			
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						
					<tr>
						<td align="right" width="100px">收件人：</td>
						<td><input  type="text"  name="mobile" required="true"
							class="easyui-textbox" data-options="multiline:true,validType:'length[1,500]'"
							style="width: 380px; height: 170px" >请填写手机号码，多个手机号以“,”号分隔开！</td>
					</tr>
					
					<tr>
						<td align="right" width="100px">短信内容：</td>
						<td><input name="content" type="text" required="true"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[1,100]'"
							style="width: 380px; height: 100px">长短信按62个字符每条短信扣取</td>
					</tr>
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('sendForm','recordList','sendSmsWindow')" style="width: 100px">发送短信</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('sendSmsWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>