<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">
 function saveConfig(){
	 $.messager.progress({
         title:'信息操作',
         text:'数据正在保存中，请耐心等待...'
     });
	 $('#configForm').form('submit', {
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
						var exchange_beauty=$("#exchange_beauty_id").textbox("getValue");
						var beauty_overtime=$("#beauty_overtime_id").textbox("getValue");
						$("#exchange_beauty").textbox("setValue",exchange_beauty);
						$("#beauty_overtime").textbox("setValue",beauty_overtime);
						showMsg('提示', data.appmsg);
						$('#configWindow').window('close');
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
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="configForm" action="${ctx }/system/beautyConfig/saveConfig.jhtml"
				method="post" >
			
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						
					<tr>
						<td align="right" width="150px">一元人民币可兑换：</td>
						<td><input name="exchange_beauty" id="exchange_beauty_id" type="text" value="<IMS:paramOut paramKey="exchange_beauty"/>"
							class="easyui-numberspinner"  data-options="min:1,max:1000000,required:true"
							style="width: 150px; height: 30px" required="true">个颜值</td>
					</tr>
					
					<tr>
						<td align="right" width="150px">颜值有效期：</td>
						<td><input name="beauty_overtime" id="beauty_overtime_id" type="text" value="<IMS:paramOut paramKey="beauty_overtime"/>"
							class="easyui-numberspinner"  data-options="min:1,max:1000000,required:true"
							style="width: 150px; height: 30px" required="true">天</td>
					</tr>
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="saveConfig()" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('configWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>