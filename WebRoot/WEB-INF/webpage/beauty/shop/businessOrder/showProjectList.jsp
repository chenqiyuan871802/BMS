<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<div class="easyui-layout" data-options="fit:true">
 <div data-options="region:'center',border:false" >
<div class="easyui-layout"   data-options="fit:true,border:false"  >
		<div style="height:40px; background-color: white;"
			data-options="region:'north',split:false">
			<form id="doForm" method="post">
			<input type="hidden"  id="order_id" value="${order_id}" />
				<table class="searchContent">
					<tr>
              
						<td width="10%" style="text-align: right">项目名称：</td>
						<td width="15%" style="text-align: left"><input type="text"
							name="project_name" class="easyui-textbox" style="width: 150px;" />
						</td>
						<td width="10%" style="text-align: right">分类：</td>
						<td width="15%" style="text-align: left"><input type="text"
							name="type_id" class="easyui-combobox"
							data-options="url:'${ctx}/shop/orderManage/queryNurseType.jhtml',method:'get',valueField:'type_id',textField:'type_name'"
							style="width: 150px;" /></td>

						<td width="40%" rowspan="4" algin="left">&nbsp;<a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search"
							onclick="doQuery('nurseProjectList','doForm')">查询</a> &nbsp; <a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="refresh" onclick="$('#doForm').form('clear')">重置</a>
						</td>
					</tr>


				</table>
			</form>
		</div>
		<div id="main"
			data-options="region:'center',split:false, border:false">
			<table id="nurseProjectList" class="easyui-datagrid"
				style="width: 100%; height: 100%; padding: 0px;"
				data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                toolbar:'#toolbar',
	                striped:true,
	                queryParams : $('#doForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/listNurseProject.jhtml',
	                pageSize:20">

				<thead>
					<tr>
						<th field="project_id" width="8%" hidden="true" align="center">项目编号</th>
						<th data-options="field:'ck',checkbox:true"></th>
						<th field="project_name" width="25%" align="center">项目名称</th>
						<th field="type_name" width="15%" align="center">分类</th>
						<th field="rmb_price" width="15%" align="center">项目价格(元)</th>
						<th field="beauty_price" width="15%" align="center">颜值(个)</th>
					</tr>
				</thead>
			</table>
   
		</div>
		</div>
		</div>
		<div  height="40px"  data-options="region:'south',border:false"
			style="text-align: center; background: #F4F4F4;">
			<a class="easyui-linkbutton button-complete button-success" dhref="javascript:void(0)"
			    onclick="chooseProject()" style="width: 70px">确定</a> &nbsp;
		    <a class="easyui-linkbutton button-complete button-danger"  href="javascript:void(0)"
				onclick="closeWindow('showNurseProjectListWindow')" style="width: 70px">取消</a>
		</div>
	</div>
	<script type="text/javascript">
	 function chooseProject(){
		 var row= $('#nurseProjectList').datagrid('getSelected');
		 if(row!=null){
			$("#project_name").textbox('setValue',row.project_name);
			$("#order_content").val(row.project_name);
			$("#order_money").numberbox('setValue',row.rmb_price);
			$("#project_id").val(row.project_id);
			closeWindow('showNurseProjectListWindow')
		 }else{
			 $.messager.alert('警告信息', '请选择你的项目', 'warning');
		 }
		
	 }
	</script>
</body>


