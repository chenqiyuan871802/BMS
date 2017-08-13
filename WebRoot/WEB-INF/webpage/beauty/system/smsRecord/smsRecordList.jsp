<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="sms_status,sms_type"/>
<IMS:codeFormatter fields="sms_status,sms_type"/>
</head>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:67px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">收件人：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">短信内容：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="content"   class="easyui-textbox" style="width: 150px; " />
								</td>
							
								<td width="7%" style="text-align: right">创建时间：</td>
								<td width="13%" style="text-align: left">
								<input type="text"  name="create_time_begin"    class="easyui-datebox" editable="false" style="width:100px" />
							      至：<input type="text" name="create_time_end"  class="easyui-datebox" editable="false" style="width:100px" />
								</td>
								
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('recordList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
								<td width="7%" style="text-align: right">短信类型：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="sms_type"    class="easyui-combobox" editable="false"  
								data-options="data:sms_typeStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">发送状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="status"    class="easyui-combobox" editable="false"  data-options="data:sms_statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">发送时间：</td>
								<td width="13%" style="text-align: left">
									<input type="text"  name="send_time_begin"    class="easyui-datebox" editable="false" style="width:100px" />
							      至：<input type="text" name="send_time_end"  class="easyui-datebox" editable="false" style="width:100px" />
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
	                url:'${ctx}/system/smsRecord/listSmsRecord.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="record_id" hidden=“true”   align="center">记录编号</th>
				<th data-options="field:'ck',checkbox:true"></th>
				<th field="mobile" width="10%"   align="center">收件人 </th>
				<th field="content" width="30%"   align="center">短信内容 </th>
				<th field="sms_type" formatter="sms_typeFormatter" width="8%" align="center">短信类型</th>
				<th field="create_time" width="12%" align="center">创建时间</th>
				<th field="send_time" width="12%" align="center">发送时间</th>
				<th field="status" formatter="sms_statusFormatter" width="6%" align="center">发送状态</th>
				<th field="failure_cause" width="20%" align="center">失败原因</th>
			
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('sendSmsWindow','${ctx}/system/smsRecord/goSend.jhtml');">发送短信</a> 
		
	    <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="batchDeleteGridData('recordList','record_id','${ctx}/system/smsRecord/batchDeleteSmsRecord.jhtml','请选择你要删除的短信记录','你确认要删除该短信记录吗？')">删除</a>
	</div>
    </div>
    </div>
   <div id="sendSmsWindow" class="easyui-window" title="发送短信"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:840px; height: 380px; background-color: #FFFFFF"></div>

   
   
    
</body>


