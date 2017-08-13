<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="show_status"/>
<IMS:codeFormatter fields="show_status"/>
<script type="text/javascript">
//查看大图
function showBigImageView(imageSrc){
  var imageUrl='${ctx}/'+imageSrc;
	$('#bigImage').attr('src',imageUrl); 
	$('#bigImageViewWindow').window('open');
}
</script>
</head>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:67px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">项目编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="project_id"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">项目名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="project_name"   class="easyui-textbox" style="width: 150px; " />
								</td>
							
							
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('nurseProjectList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
								<td width="7%" style="text-align: right">分类：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="type_id"   class="easyui-combobox"	 data-options="url:'${ctx}/system/nurseType/queryNurseType.jhtml',method:'get',valueField:'type_id',textField:'type_name'" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="status"    class="easyui-combobox" editable="false"  data-options="data:show_statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								
								
								
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="nurseProjectList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
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
	                url:'${ctx}/system/nurseProject/listNurseProject.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="project_id" width="8%"   align="center">项目编号</th>
				<th field="project_name" width="13%"   align="center">项目名称 </th>
				<th field="type_name" width="10%"   align="center">分类</th>
				<th field="server_time" width="9%" align="center">服务时长(分钟)</th>
				<th field="rmb_price" width="9%" align="center">人民币售价(元)</th>
				<th field="beauty_price" width="9%" align="center">颜值售价(个)</th>
				<th field="manual_price" width="9%" align="center">标准手工费(元)</th>
				<th field="active_price" width="9%" align="center">活动手工费(元)</th>
				<th field="status"   formatter="show_statusFormatter" width="5%" align="center">状态</th>
				<th field="modify_time"    width="12%" align="center">更新时间</th>
				<th field="sort_no" width="5%" align="center">排序号</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addNurseProjectWindow','${ctx}/system/nurseProject/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyGridData('modifyNurseProjectWindow','nurseProjectList','project_id','${ctx}/system/nurseProject/goModify.jhtml','请选择你要修改的项目信息');;">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true"
			onclick="showGridData('showNurseProjectWindow','nurseProjectList','project_id','${ctx}/system/nurseProject/goShow.jhtml','请选择你要查看的项目信息');;">查看详情</a>
	
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('nurseProjectList','project_id','${ctx}/system/nurseProject/deleteNurseProject.jhtml','请选择你要删除的项目信息','你确认要删除项目信息吗？')">删除</a>
	  
	</div>
    </div>
    </div>
   <div id="addNurseProjectWindow" class="easyui-window" title="新增项目信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:1050px; height: 480px; background-color: #FFFFFF"></div>
	<div id="modifyNurseProjectWindow" class="easyui-window" title="修改项目信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
	<div id="showNurseProjectWindow" class="easyui-window" title="查看项目信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
    <div id="bigImageViewWindow" class="easyui-window" title="查看大图"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1150px; height: 480px; background-color: #FFFFFF">
		<body style="margin: 0; padding: 0">
	<div   style="text-align: center; vertical-align: middle;">
		<img id="bigImage"  style="vertical-align:middle;" >
	</div>
    </body>
	</div>
   
    
</body>


