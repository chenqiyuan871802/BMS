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
								
								<td width="7%" style="text-align: right">手机号码：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">内容：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="content"   class="easyui-textbox" style="width: 150px; " />
								</td>
							  
								<td width="6%" style="text-align: right">发送日期：</td>
								<td width="13%" colspan="3" style="text-align: left">
								<input type="text"  name="create_time_begin"   class="easyui-datebox" editable="false" style="width:110px" />
							      至：<input type="text" name="create_time_end"  class="easyui-datebox" editable="false" style="width:110px" />
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
	                singleSelect:false,
	                autoRowHeight:false, 
	                pagination:true,
	                toolbar:'#toolbar',
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/opinion/listOpinion.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="opinion_id"  hidden="true"  align="center">意见编号</th>
				<th data-options="field:'ck',checkbox:true"></th>
				<th field="mobile" width="14%"   align="center">手机号码 </th>
				<th field="content" formatter="formatCellTooltip" width="67%"   align="center">内容</th>
				<th field="create_time"    width="15%" align="center">发送时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
	   <a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true"
			onclick="showGridData('showOpinionWindow','dataList','opinion_id','${ctx}/system/opinion/goShow.jhtml','请选择你要查看的意见信息');;">查看意见</a>
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="batchDeleteGridData('dataList','opinion_id','${ctx}/system/opinion/batchDeleteOpinion.jhtml','请选择你要删除意见信息','你确认要删除选择的意见信息吗？')">删除</a>
	  
	</div>
    </div>
    </div>

   
   <div id="showOpinionWindow" class="easyui-window" title="查看意见信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 550px; height: 350px; background-color: #FFFFFF"></div>
    
</body>


