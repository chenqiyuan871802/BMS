<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>

<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:40px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								
								<td width="7%" style="text-align: right">分类名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="type_name"   class="easyui-textbox" style="width: 200px; " />
								</td>
							
								<td width="50%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('dataList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="dataList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                toolbar:'#toolbar',
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/nurseType/listNurseType.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="type_id"  hidden=“true”  align="center">分类编号</th>
				<th field="type_name" width="30%"   align="center">分类名称 </th>
				<th field="type_remark" width="40%" align="center">备注</th>
				<th field="sort_no" width="8%" align="center">排序</th>
				<th field="modify_time"    width="13%" align="center">更新时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addWindow','${ctx}/system/nurseType/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyGridData('modifyWindow','dataList','type_id','${ctx}/system/nurseType/goModify.jhtml','请选择你要修改的分类信息');;">修改</a>
	
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('dataList','type_id','${ctx}/system/nurseType/deleteNurseType.jhtml','请选择你要删除的分类信息','你确认要删除该分类信息吗？')">删除</a>
	  
	</div>
    </div>
    </div>
   <div id="addWindow" class="easyui-window" title="新增分类信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 260px; background-color: #FFFFFF"></div>
	<div id="modifyWindow" class="easyui-window" title="修改分类信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 260px; background-color: #FFFFFF"></div>
   
   
    
</body>


