<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">

</head>
]
<body style="margin: 0; padding: 0" >
<IMS:codeStore fields="sex"/>
<IMS:codeFormatter fields="sex"/>
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:37px;background-color: white;"  data-options="region:'north',split:false">
        <form id="doForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">手机号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="20%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('customUserList','doForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#doForm').form('reset')">重置</a> 
								</td>
							</tr>
							
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="customUserList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:false,
	                autoRowHeight:false, 
	                 nowrap:false,
	                pagination:false,
	                toolbar:'#tb',
	                striped:true,
	                 queryParams : $('#doForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/customUser/listSelectCustomUser.jhtml'
	                ">
	               
		<thead>
			<tr>
					<th data-options="field:'ck',checkbox:true"></th>
				<th field="custom_user_id" width="12%"    align="center">会员号</th>
			
				<th field="mobile" width="12%"   align="center">手机 </th>
				<th field="username" width="15%"   align="center">姓名</th>
				<th field="nikename" width="15%"   align="center">昵称</th>
				<th field="sex" width="8%" formatter="sexFormatter"   align="center">性别</th>
				<th field="openid" width="30%"   align="center">openid</th>
				
			</tr>
		</thead>
	</table>
	
    </div>
    </div>
  <div id="tb" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="selectCustomUser()">选择发送用户</a> 
		
	  
	</div>
    <script type="text/javascript">
    function selectCustomUser(){
   	 var dataRows = $('#customUserList').datagrid('getChecked');
   	 var len=dataRows.length;
   	 if(len>0){
   		var sendUser='';
   		var sendUserId='';
   		var sendOpenid='';
   		for(var i=0;i<len;i++){
   			var row=dataRows[i];
   			var username=row.username;
   			var custom_user_id=row.custom_user_id;
   		    var openid=row.openid
   		    if(i==0){
   		    	sendUser=username;
   		    	sendUserId=custom_user_id;
   	   		    sendOpenid=openid;
   		    }else{
   		    	sendUser+=','+username;
   		    	sendUserId+=','+custom_user_id;
   		    	sendOpenid+=','+openid;
   		    }
   		}
   	    $("#sendUser").textbox('setValue',sendUser);
   	    $("#sendUserId").val(sendUserId);
   	    $("#sendOpenid").val(sendOpenid);
   		closeWindow('selectCustomUserWindow')
   	 }else{
   		 $.messager.alert('警告信息', '请选择你要发送用户', 'warning');
   	 }
   	 
    }
    
    </script>
</body>


