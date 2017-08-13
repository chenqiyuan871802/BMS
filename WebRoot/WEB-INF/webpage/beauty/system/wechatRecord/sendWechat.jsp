<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="sendForm" action="${ctx }/system/wechatRecord/sendWechat.jhtml"
				method="post" >
			    <input type="hidden"  name="sendUserId" id="sendUserId"  />
			    <input type="hidden"  name="sendOpenid" id="sendOpenid"  />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
				<td align="right" width="100px">发送用户：</td>
				<td >
				<input  type="text"  name="mobile" id="sendUser" required="true" readonly
							class="easyui-textbox" data-options="multiline:true,validType:'length[1,20000]'"
							style="width: 480px; height: 250px" >
						<a class="easyui-linkbutton" data-options="iconCls:'add' "href="javascript:void(0)"
			   onclick="showWindow('selectCustomUserWindow','${ctx}/system/customUser/initSelect.jhtml');" style="width: 100px">选择用户</a> 	
				</td>
				
			
				</tr>	
					
					
					<tr>
						<td align="right" width="100px">内容：</td>
						<td><input name="content" type="text" required="true"  class="easyui-textbox"
							data-options="multiline:true,validType:'length[1,100]'"
							style="width: 480px; height:80px">长短信按62个字符每条短信扣取</td>
					</tr>
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('sendForm','recordList','sendSmsWindow')" style="width: 100px">发送信息</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('sendSmsWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>