<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>

<script type="text/javascript">
function downloadData(){
	 $('#queryForm').form('submit', {   
		    url:'${ctx}/system/finance/downloadShareBagRecord.jhtml'
	  });  
}
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:70px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								
								
								<td width="7%" style="text-align: right">分享者账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="share_mobile"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">分享者姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="share_username"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">礼包名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="bag_name"   class="easyui-textbox" style="width: 210px; " />
								</td>
							
								<td width="20%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('dataList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
						<tr>
								<td width="7%" style="text-align: right">被分享者账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="receive_mobile"   class="easyui-textbox" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">被分享者姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="receive_username"   class="easyui-textbox" style="width: 150px; " />
								</td>
								
								<td width="7%" style="text-align: right">分享日期：</td>
								<td width="13%" colspan="3" style="text-align: left">
								<input type="text"  name="create_time_begin"   class="easyui-datebox" editable="false" style="width:100px" />
							      至：<input type="text" name="create_time_end"  class="easyui-datebox" editable="false" style="width:100px" />
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
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                toolbar:'#toolbar',
	                url:'${ctx}/system/finance/listShareBagRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="record_id" width="15%"   align="center">流水号</th>
				
				<th field="share_mobile" width="12%" align="center">分享者账号</th>
				<th field="share_username" width="9%" align="center">分享者姓名</th>
				<th field="bag_id"  width="8%" align="center">礼包编号</th>
				<th field="bag_name"    width="15%" align="center">礼包名称</th>
				<th field="receive_mobile" width="12%" align="center">被分享者账号</th>
				<th field="receive_username" width="9%" align="center">被分享者姓名</th>
				<th field="bag_time"    width="14%" align="center">分享时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
  
	<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="excel" plain="true"
			onclick="downloadData()">导出Excel</a>
	  
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


