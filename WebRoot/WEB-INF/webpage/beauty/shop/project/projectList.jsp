<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="show_status" />
<IMS:codeFormatter fields="show_status" />
<script type="text/javascript">
	//查看大图
	function showBigImageView(imageSrc) {
		var imageUrl = '${ctx}/' + imageSrc;
		$('#bigImage').attr('src', imageUrl);
		$('#bigImageViewWindow').window('open');
	}
	function imageFormatter(value) {
		if (IMSUtils.isNotEmpty(value)) {
			return '<img title="点击查看大图" onclick="showBigImageView(\'' + value
					+ '\')"  style="width:60px;height:60px"  src="${ctx }/'
					+ value + '"  />';
		}
		return ''
	}
	//处理操作
	function handleFormatter(value, row, index){
		var project_id=row.project_id;
		var handleStr="showProjectDetail("+project_id+")";
		var  str="<a href='#' onclick='"+handleStr+"' class='button-detail button-default button-lg'>详情</a>&nbsp;"
		return str;
	}
	function  loadSuccess(){
		
		$('.button-detail').linkbutton({ 
		});
	
	}
	function showProjectDetail(project_id){
		
		showWindow('showNurseProjectWindow','${ctx}/shop/shopManage/showProjectDetail.jhtml?project_id='+project_id);
	}
</script>
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div style="height: 43px; background-color: white;"
			data-options="region:'north',split:false">
			<form id="queryForm" method="post">
				<table class="searchContent">
					<tr>
					<tr>
						<td width="5%" style="text-align: right">项目编号：</td>
						<td width="12%" style="text-align: left"><input type="text"
							name="project_id" class="easyui-textbox" style="width: 150px;" />
						</td>

						<td width="5%" style="text-align: right">项目名称：</td>
						<td width="12%" style="text-align: left"><input type="text"
							name="project_name" class="easyui-textbox" style="width: 150px;" />
						</td>
						<td width="5%" style="text-align: right">分类：</td>
						<td width="12%" style="text-align: left"><input type="text"
							name="type_id" class="easyui-combobox"
							data-options="url:'${ctx}/shop/shopManage/queryNurseType.jhtml',method:'get',valueField:'type_id',textField:'type_name'"
							style="width: 150px;" /></td>

						<td width="5%" style="text-align: right">价格区间：</td>
						<td width="12%" style="text-align: left"><input
							name="price_begin" type="text" class="easyui-numberspinner"
							data-options="min:1,max:1000000,increment:10"
							style="width: 100px;">至<input name="price_end"
							type="text" class="easyui-numberspinner"
							data-options="min:1,max:1000000,increment:10"
							style="width: 100px;"></td>

						<td width="40%" rowspan="4" algin="left">&nbsp;<a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search"
							onclick="doQuery('nurseProjectList','queryForm')">查询</a> &nbsp; <a
							href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-refresh" onclick="$('#queryForm').form('reset')">重置</a>
						</td>
					</tr>


				</table>
			</form>
		</div>
		<div id="main"
			data-options="region:'center',split:false, border:false">
			<table id="nurseProjectList" class="easyui-datagrid"
				style="width: 100%; height: 100%; padding: 0px;"
				data-options="
				     border:false,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/shopManage/listProject.jhtml',
	                onLoadSuccess:loadSuccess,
	                pageSize:20">

				<thead>
					<tr>
						<th field="cover_photo" formatter="imageFormatter" width="8%"
							align="center">护理项目图片</th>
						<th field="project_id" width="6%" align="center">项目编号</th>
						<th field="project_name" width="10%" align="center">项目名称</th>
						<th field="rmb_price" width="6%" align="center">护理价格(元)</th>
						<th field="beauty_price" width="6%" align="center">颜值售价(个)</th>
						<th field="manual_price" width="6%" align="center">标准手工费(元)</th>
						<th field="active_price" width="6%" align="center">活动手工费(元)</th>
						<th field="server_time" width="6%" align="center">服务时长(分钟)</th>
						<th field="type_name" width="7%" align="center">分类</th>
						<th field="create_time" width="10%" align="center">建立时间</th>
						<th field="modify_time" formatter="handleFormatter" width="5%" align="center">操作</th>

					</tr>
				</thead>
			</table>

		</div>
	</div>

	<div id="showNurseProjectWindow" class="easyui-window" title="查看项目信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width: 800px; height: 440px; background-color: #FFFFFF"></div>
	<div id="bigImageViewWindow" class="easyui-window" title="查看大图"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width: 1150px; height: 440px; background-color: #FFFFFF">
		<body style="margin: 0; padding: 0">
			<div style="text-align: center; vertical-align: middle;">
				<img id="bigImage" style="vertical-align: middle;">
			</div>
		</body>
	</div>


</body>


