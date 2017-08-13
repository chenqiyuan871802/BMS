<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<div class="easyui-layout" data-options="fit:true">
			<form id="searchForm" method="post">
			<input type="hidden"  name="custom_user_id" value="${custom_user_id}" />
			<input type="hidden"  name="project_id" value="${project_id}" />
			</form>
		
		<div id="main"
			data-options="region:'center',split:false, border:false">
			<table id="payProjectList" class="easyui-datagrid"
				style="width: 100%; height: 100%; padding: 0px;"
				data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                striped:true,
	                queryParams : $('#searchForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/showPayProject.jhtml'
	             " >

				<thead>
					<tr>
						<th field="record_id" width="8%" hidden="true" align="center">项目编号</th>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="project_name" width="55%" align="center">项目名称</th>
						<th field="overdue_date" width="25%" align="center">有效期</th>
					</tr>
				</thead>
			</table>
   
	
		</div>
		<div  height="40px"  data-options="region:'south',border:false"
			style="text-align: center; background: #F4F4F4;">
			<a class="easyui-linkbutton button-complete button-success" dhref="javascript:void(0)"
			    onclick="choosePayProject()" style="width: 70px">确定</a> &nbsp;
		    <a class="easyui-linkbutton button-complete button-danger"  href="javascript:void(0)"
				onclick="closeWindow('showPayProjectWindow')" style="width: 70px">取消</a>
		</div>
	</div>
	<script type="text/javascript">
	 function choosePayProject(){
		 var row= $('#payProjectList').datagrid('getSelected');
		 if(row!=null){
			$("#record_id").val(row.record_id);
			$("#showPay").html(row.project_name);
			$('input:radio:first').prop("checked", false);
			$('input:radio:last').prop('checked', true); 
		
			closeWindow('showPayProjectWindow');
		 }else{
			 $.messager.alert('警告信息', '请选择你要支付的项目', 'warning');
		 }
		
	 }
	</script>
</body>


