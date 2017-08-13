<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:37px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
								<td width="7%" style="text-align: right">收件人：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">收件人账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">内容：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="content"   class="easyui-textbox" style="width: 150px; " />
								</td>
							
								<td width="7%" style="text-align: right">发送时间：</td>
								<td width="13%" style="text-align: left">
									<input type="text"  name="send_time_begin"    class="easyui-datebox" editable="false" style="width:100px" />
							      至：<input type="text" name="send_time_end"  class="easyui-datebox" editable="false" style="width:100px" />
								</td>
								
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('recordList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="recordList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
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
	                url:'${ctx}/system/wechatRecord/listWechatRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="record_id" hidden=“true”   align="center">记录编号</th>
				<th data-options="field:'ck',checkbox:true"></th>
				<th field="username" width="10%"   align="center">收件人</th>
				<th field="mobile" width="10%"   align="center">收件人账号 </th>
				<th field="content" width="60%"   align="center">内容 </th>
				<th field="send_time" width="12%" align="center">发送时间</th>
			
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('sendSmsWindow','${ctx}/system/wechatRecord/goAdd.jhtml');">发送信息</a> 
			
	    <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="batchDeleteGridData('recordList','record_id','${ctx}/system/wechatRecord/batchDeleteWechatRecord.jhtml','请选择你要删除的信息记录','你确认要删除选择信息记录吗？')">删除</a>
	  
	</div>
    </div>
    </div>
   <div id="sendSmsWindow" class="easyui-window" title="发送信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:840px; height: 450px; background-color: #FFFFFF"></div>
   <div id="selectCustomUserWindow" class="easyui-window" title="选择发送用户"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:850px; height: 450px; background-color: #FFFFFF"></div>

   
   
    
</body>


