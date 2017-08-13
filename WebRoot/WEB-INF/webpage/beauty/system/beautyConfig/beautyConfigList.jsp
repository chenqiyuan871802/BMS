<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>

<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:40px;background-color: white;"  data-options="region:'north',split:false">
       
        <table class="searchContent">
					<tr>
					
								
								<td width="25%" style="text-align: left">
								一元人民币可兑换<input type="text"  id="exchange_beauty" disabled value="<IMS:paramOut paramKey="exchange_beauty"/>"  class="easyui-textbox" style="width: 80px; " />
							           个颜值
								</td>
								<td width="25%" style="text-align: left">
								颜值有效期<input type="text"  id="beauty_overtime" disabled  value="<IMS:paramOut paramKey="beauty_overtime"/>"  class="easyui-textbox" style="width: 80px; " />
							    天
								</td>
							
								
							</tr>
							
					
				</table>

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
	                url:'${ctx}/system/beautyConfig/listBeautyConfig.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="config_id"  hidden=“true”  align="center">配置编号</th>
				<th field="buy_num" width="12%"   align="center">购买颜值数量 </th>
				<th field="give_num" width="12%"   align="center">赠送颜值数量 </th>
				<th field="remark"  formatter="formatCellTooltip" width="45%"   align="center">备注</th>
				<th field="create_time"    width="15%" align="center">创建时间</th>
				<th field="modify_time"    width="15%" align="center">更新时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('addWindow','${ctx}/system/beautyConfig/goAdd.jhtml');">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyGridData('modifyWindow','dataList','config_id','${ctx}/system/beautyConfig/goModify.jhtml','请选择你要修改的颜值基本设置信息');;">修改</a>
     	<a href="#" class="easyui-linkbutton" iconCls="config"
			plain="true"
			onclick="showWindow('configWindow','${ctx}/system/beautyConfig/goConfig.jhtml');">颜值配置</a>
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('dataList','config_id','${ctx}/system/beautyConfig/deleteBeautyConfig.jhtml','请选择你要删除的颜值基本设置信息','你确认要删除该颜值基本设置信息吗？')">删除</a>
	  
	
    </div>
    </div>
    </div>
   <div id="addWindow" class="easyui-window" title="新增颜值配置信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 300px; background-color: #FFFFFF"></div>
	<div id="modifyWindow" class="easyui-window" title="修改颜值配置信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 300px; background-color: #FFFFFF"></div>
	<div id="configWindow" class="easyui-window" title="颜值配置"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 200px; background-color: #FFFFFF"></div>
   
   
    
</body>


