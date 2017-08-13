<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
 function saveConfig(formId){
	 $.messager.progress({
         title:'信息操作',
         text:'数据正在保存中，请耐心等待...'
     });
	 $('#'+formId).form('submit', {
			onSubmit : function(param) {
				var result=  $(this).form('enableValidation').form('validate');
				if(!result){
				    $.messager.progress('close');
				}
				return result;
			},
			success : function(data) {
			    $.messager.progress('close');
				var data = eval('(' + data + ')');
				if (data) {
					if (data.appcode=="1") {
						
						showMsg('提示', data.appmsg);
					}else if(data.appcode=="0"){
					   $.messager.alert('警告信息', data.appmsg, 'warning');
					}else {
						$.messager.alert('错误信息', data.appmsg, 'error');
					}
				} else {
					$.messager.alert('错误信息', '操作失败', 'error');
				}
			},
			onLoadeError:function(){
			  $.messager.progress('close');
			         $.messager.alert('错误信息', '操作失败', 'error');
			}
		});
	 
 }
</script>
<div class="easyui-layout"  data-options="fit:true">
<div  class="easyui-panel" title="预约参数设置" style="height:200px;background-color: white;"  data-options="region:'north',split:false">
<form id="subConfigForm" action="${ctx }/system/paramConfig/saveSubConfig.jhtml"
				method="post" >
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						
					<tr>
						<td align="right" width="150px">预约定金：</td>
						<td><input name="subscribe_deposit"  type="text" value="<IMS:paramOut paramKey="subscribe_deposit"/>"
							class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2"
							style="width: 350px; height: 30px" required="true">元</td>
					</tr>
					<tr>
						<td align="right" width="150px">每2小时可预约上限：</td>
						<td><input name="every_two_limit" class="easyui-numberbox" 
							data-options="min:0,max:9999999999" id="exchange_beauty_id" type="text" value="<IMS:paramOut paramKey="every_two_limit"/>"
							class="easyui-textbox"  
							style="width: 350px; height: 30px" required="true">个</td>
					</tr>
					
					<tr>
						<td align="right" width="150px">可取消预定时间：</td>
						<td><input name="cancel_time"  type="text" value="<IMS:paramOut paramKey="cancel_time"/>"
						 class="easyui-numberbox" data-options="min:0,max:9999999999"
							style="width: 350px; height: 30px" required="true">分</td>
					</tr>
					
					<tr>
						<td align="left" colspan="2"  style="padding:4px 450px;text-align:left;">
						<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="saveConfig('subConfigForm')" style="width: 70px">保存</a>
						</td>
						
					</tr>
					
					
				

				</table>

			</form>
</div>
    <div  class="easyui-panel"  title="短信参数"  data-options="region:'center',split:false, border:false"
    style="width:100%;height:290px;padding:10px;"  >
			<form id="smsConfigForm" action="${ctx }/system/paramConfig/saveSmsConfig.jhtml"
				method="post" >
			
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						
					<tr>
						<td align="right" width="150px">短信URL地址：</td>
						<td><input name="sms_url"  type="text" value="<IMS:paramOut paramKey="sms_url"/>"
							class="easyui-textbox"  
							style="width: 350px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="150px">App Key：</td>
						<td><input name="sms_app_key" id="exchange_beauty_id" type="text" value="<IMS:paramOut paramKey="sms_app_key"/>"
							class="easyui-textbox"  
							style="width: 350px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="150px">App Secret：</td>
						<td><input name="sms_app_secret" type="text" value="<IMS:paramOut paramKey="sms_app_secret"/>"
							class="easyui-textbox"  
							style="width: 350px; height: 30px" required="true"></td>
					</tr>
					<tr>
						<td align="right" width="150px">短信签名：</td>
						<td><input name="sms_signe"  type="text" value="<IMS:paramOut paramKey="sms_signe"/>"
							class="easyui-textbox"  
							style="width: 350px; height: 30px" required="true"><font color="red">多个短信签名使用英文分号(,)分割</font></td>
					</tr>
					<tr>
						<td align="right" width="150px">短信模板编号 ：</td>
						<td><input name="sms_template_code"  type="text" value="<IMS:paramOut paramKey="sms_template_code"/>"
							class="easyui-textbox"  
							style="width: 350px; height: 30px" required="true"><font color="red">多个模板编号使用英文分号(,)分割</font></td>
					</tr>
					<tr>
						<td align="left" colspan="2"  style="padding:4px 450px;text-align:left;">
						<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="saveConfig('smsConfigForm')" style="width: 70px">保存</a>
						</td>
						
					</tr>
					
					
				

				</table>

			</form>
		
   </div>
  
  </div>
  
</body>
</html>