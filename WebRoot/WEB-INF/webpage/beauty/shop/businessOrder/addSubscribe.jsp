<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<div class="content">
		      <div title="基本信息" data-options="closable:false" class="basic-info">
		     <form id="addSubscribeForm" action="${ctx }/shop/orderManage/saveSubscribeOrder.jhtml"
				method="post" >
				 <input type="hidden"  name="project_id" id="project_id" />
				 <input type="hidden"  name="order_content" id="order_content" />
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">顾客账号</td>
							<td class="kv-content" colspan="2">
							<input type="text"  name="mobile"	class="easyui-textbox"  data-options="validType:'mobile'"  required="true" style="width: 230px; height: 30px" >
							</td>
							
						</tr>
						<tr style="height: 30px">
							<td class="kv-label">消费内容</td>
							<td class="kv-content" >	<input type="text"  id="project_name"	class="easyui-textbox"  disabled="disabled" style="width: 230px; height: 30px" ></td>
						<td class="kv-label" rowspan="2" style="align:center">  <a class="easyui-linkbutton button-complete button-warning" href="javascript:void(0)"
			    onclick="initNurseProject()" style="width: 70px;">选择项目</a></td>
						</tr>
						<tr style="height: 30px">
							<td class="kv-label">护理价格</td>
							<td class="kv-content"><input type="text" id="order_money" name="order_money" required="true"  class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2" style="width: 230px; height: 30px" >元</td>
						</tr>
						<tr>
							<td class="kv-label">预约时间</td>
							<td class="kv-content" colspan="2">
							<input type="text"  name="subscribe_time" required="true"   showSeconds="false" class="easyui-datetimebox" editable="false" style="width: 230px; height: 30px"/>
							</td>
						</tr>
						<tr>
							<td class="kv-label">订单备注</td>
							<td class="kv-content" colspan="2">
							<input type="text"  name="order_remark"	class="easyui-textbox"  data-options="validType:'length[0,400]'" style="width: 230px; height: 80px" >
							</td>
						</tr>
						
					</tbody>
				</table>
				</form>
				<div  height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton button-complete button-success" href="javascript:void(0)"
			    onclick="saveSubscribe()" style="width: 70px">确定</a> &nbsp;
		    <a class="easyui-linkbutton button-complete button-danger"  href="javascript:void(0)"
				onclick="closeWindow('addSubscribeWindow')" style="width: 70px">关闭</a>
		</div>
		</div>
	</div>
	<script type="text/javascript">
	//初始化护理项目
	 function initNurseProject(){
		 showWindow('showNurseProjectListWindow','${ctx}/shop/orderManage/initNurseProject.jhtml?showWay=1');
	 }
	//保存订单信息
	function saveSubscribe(){
		var project_id=$("#project_id").val();
		
		if(IMSUtils.isEmpty(project_id)){
			$.messager.alert('警告信息', '请选择你要消费的项目', 'warning');
			return ;
		}
		
		submitFormData('addSubscribeForm','recordList','addSubscribeWindow')
	}
	</script>
</body> 
</html>