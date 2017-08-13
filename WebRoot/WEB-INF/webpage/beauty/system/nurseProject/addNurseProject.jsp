<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
 <link href="${ctx}/static/weblib/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
   <script type="text/javascript" charset="utf-8" src="${ctx}/static/weblib/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/static/weblib/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/weblib/umeditor/lang/zh-cn/zh-cn.js"></script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addNurseProjectForm" action="${ctx }/system/nurseProject/saveNurseProject.jhtml"
				method="post" enctype="multipart/form-data" >
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					
					<tr>
						<td align="right" width="110px">项目名称：</td>
						<td ><input name="project_name" type="text" data-options="validType:'length[1,50]'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
						
						<td align="right" width="110px">状态：</td>
						<td><input  type="text"   name="status"	 value="1"  editable="false"  required="true"
							class="easyui-combobox"   data-options="data:show_statusStore,textField:1,valueField:0" style="width: 250px; height: 30px"></td>
					</tr>
					<tr>
						
						<td align="right" width="110px">列表图片：</td>
						<td><input name="cover_photo_file" type="text" 
							 class="easyui-filebox"  
							style="width: 250px; height: 30px"  required="true"></td>
					<td align="right" width="110px">封面图片：</td>
						<td><input name="cover_big_photo_file" type="text" 
							 class="easyui-filebox"  
							style="width: 250px; height: 30px"  required="true"></td>
						
					</tr>
					<tr>
						<td align="right" width="110px">分类：</td>
						<td><input  type="text"  name="type_id"  class="easyui-combobox"	 data-options="url:'${ctx}/system/nurseType/queryNurseType.jhtml',method:'get',valueField:'type_id',textField:'type_name'" class="easyui-textbox" required="true" data-options="validType:'loginname'" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">所用设备：</td>
						<td ><input name="use_device" type="text" data-options="validType:'length[0,200]'"
							class="easyui-textbox" 
							style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
					<td align="right" width="110px">服务时长：</td>
						<td><input name="server_time" type="text"
							class="easyui-numberspinner" data-options="min:1,max:1000000"
							style="width: 250px; height: 30px" required="true">分钟</td>
					<td align="right" width="110px">人民币售价：</td>
						<td><input type="text"  name="rmb_price"	class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2" required="true" style="width: 250px; height: 30px" >元</td>
					</tr>
					<tr>
					<td align="right" width="110px">颜值售价：</td>
						<td><input name="beauty_price" type="text"
							class="easyui-numberspinner" data-options="min:1,max:1000000"
							style="width: 250px; height: 30px" required="true">个</td>
					<td align="right" width="110px">标准手工费：</td>
						<td><input type="text"  name="manual_price"	required="true"  class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2" style="width: 250px; height: 30px" >元</td>
					</tr>
					<tr>
					<td align="right" width="110px">活动手工费：</td>
						<td><input type="text"  name="active_price"	 class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2" style="width: 250px; height: 30px" >元</td>
					<td align="right" width="110px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 250px; height: 30px" required="true"></td>
					</tr>
					<tr>
					<td align="right" width="110px">内容描述：</td>
					<td colspan="3">
					<script id="addContent" name="content" type="text/plain" style="height: 250px;" ></script>
					</td>
					</tr>
				  
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('addNurseProjectForm','nurseProjectList','addNurseProjectWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addNurseProjectWindow')" style="width: 70px">关闭</a>
		</div>
	</div>
<script type="text/javascript">
   var um=UM.getEditor('addContent');
       um.setWidth("98%");
   </script>
</body>
</html>