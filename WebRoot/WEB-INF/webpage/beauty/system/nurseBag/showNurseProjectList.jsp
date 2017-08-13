<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="show_status"/>
<IMS:codeFormatter fields="show_status"/>
</head>
<body style="margin: 0; padding: 0" >
<script type="text/javascript">
 function selectNurseProject(){
	 var operate_mode='${operate_mode}';
	 var dataRows = $('#nurseProjectList').datagrid('getChecked');
	 var len=dataRows.length;
	 if(len>0){
		 var tableId="#addNurseProject"
	
		 if(operate_mode=='2'){
			 tableId="#modifyNurseProject"
		 }
		 createTabelRow(tableId,dataRows)
		showMsg('提示', '护理项目添加成功');
		closeWindow('showNurseProjectList')
	 }else{
		 $.messager.alert('警告信息', '请选择护理项目', 'warning');
	 }
	 
 }
 
 function createTabelRow(tableId,dataRows){
	 var tableObj=$(tableId);
	 var len=dataRows.length;
	 var projectIds=getTableCellValue(tableId,0);
	 var arr=projectIds.split(",");
	 var sum_price=0;
	 for(var i=0;i<len;i++){
		 var dataRow=dataRows[i];
		 var project_id=dataRow.project_id
		 var checkResult=checkProjectId(arr,project_id)
		 if(checkResult){
			 sum_price+=dataRow.rmb_price;
			 var html='<tr>';
			 html+='<td align="center">'+dataRow.project_name+'<input type="hidden" name="project_id" value="'+dataRow.project_id+'"/></td>'
			 html+='<td align="center"><input type="text"  name="project_new_price" 	class="easyui-numberbox" value="'+dataRow.rmb_price+'"data-options="min:0,max:9999999999,precision:2,onChange:changePrice" required="true" style="width: 100px; height: 30px" ></td>'
			 html+='<td align="center"><input name="project_num" type="text"  class="easyui-numberspinner" value="1" data-options="min:1,max:10000000,onChange:changeNum" style="width: 100px; height: 30px" required="true"></td>'
		     html+='<td align="center"><input type="hidden" name="project_old_price" value="'+dataRow.rmb_price+'"/><a class="easyui-linkbutton" data-options="iconCls:\'icon-search\' "href="javascript:void(0)" onclick="showNurseProjectDetail('+dataRow.project_id+')" style="width:60px">查看</a> &nbsp;&nbsp;';   
	         html+='<a class="easyui-linkbutton" data-options="iconCls:\'del\' "href="javascript:void(0)" onclick="deleteProject(this)" style="width:60px">删除</a>'
	         html+='</td>'
	         html+='</tr>'
	         tableObj.append(html);
		 }
			
	 }
	 $.parser.parse(tableId)
	 sumTotalPrice();
	 
	}
 function checkProjectId(arr,project_id){
	
	 for(var j=0;j<arr.length;j++){
		 var proid=arr[j];
		 if(project_id==proid){
			 return false;
		 }
	 }
	 return true;
 }
 
</script>
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:35px;background-color: white;"  data-options="region:'north',split:false">
        <form id="doForm" method="post">
         <input type="hidden"  name="status" value="1" />
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
							  <td width="7%" style="text-align: right">分类：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="type_id"   class="easyui-combobox"	 data-options="url:'${ctx}/system/nurseType/queryNurseType.jhtml',method:'get',valueField:'type_id',textField:'type_name'" style="width: 150px;  " />
								</td>
								
							
								<td width="40%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('nurseProjectList','doForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#doForm').form('reset')">重置</a> 
								</td>
							</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="nurseProjectList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
	                singleSelect:false,
	                autoRowHeight:false, 
	                pagination:true,
	                toolbar:'#projectTool',
	                striped:true,
	                queryParams : $('#doForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/nurseProject/listNurseProject.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
			   <th data-options="field:'ck',checkbox:true"></th>
				<th field="project_id" width="8%"   align="center">项目编号</th>
				<th field="project_name" width="13%"   align="center">项目名称 </th>
				<th field="type_name" width="10%"   align="center">分类</th>
				<th field="server_time" width="10%" align="center">服务时长(分钟)</th>
				<th field="rmb_price" width="10%" align="center">人民币售价(元)</th>
				<th field="beauty_price" width="10%" align="center">颜值售价(个)</th>
				<th field="manual_price" width="10%" align="center">标准手工费(元)</th>
				<th field="active_price" width="10%" align="center">活动手工费(元)</th>
				<th field="modify_time"    width="13%" align="center">更新时间</th>
				<th field="sort_no" width="5%" align="center">排序号</th>
				
			</tr>
		</thead>
	</table>
	<div id="projectTool" style="padding: 2px;">
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"  onclick="selectNurseProject()">选择护理项目</a> 
	</div>
    </div>
    
    </div>

 
    
</body>


