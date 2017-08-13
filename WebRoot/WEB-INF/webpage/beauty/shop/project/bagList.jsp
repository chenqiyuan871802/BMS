<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<script type="text/javascript">
//查看大图
function showBigImageView(imageSrc){
  var imageUrl='${ctx}/'+imageSrc;
	$('#bigImage').attr('src',imageUrl); 
	$('#bigImageViewWindow').window('open');
}
function imageFormatter(value){  
	 if(IMSUtils.isNotEmpty(value)){
	    return '<img title="点击查看大图" onclick="showBigImageView(\''+value+'\')"  style="width:60px;height:60px"  src="${ctx }/'+value+'"  />';
	 }
	 return ''
}
function putFormatter(value, row, index){
	if(row!=null){
		var soldout_time=row.soldout_time;
		return value+'至'+soldout_time;
	}
	return ''
	
}
//处理操作
function handleFormatter(value, row, index){
	var bag_id=row.bag_id;
	var handleStr="showBagDetail("+bag_id+")";
	var  str="<a href='#' onclick='"+handleStr+"' class='button-detail button-default button-lg'>详情</a>&nbsp;"
	return str;
}
function  loadSuccess(){
	
	$('.button-detail').linkbutton({ 
	});

}
function showBagDetail(bag_id){
	
	showWindow('showBagWindow','${ctx}/shop/shopManage/showBagDetail.jhtml?bag_id='+bag_id);
}
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:45px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">礼包编号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="bag_id"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">礼包名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="bag_name"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">价格区间：</td>
								<td width="13%" style="text-align: left">
								<input name="price_begin" type="text"
							class="easyui-numberspinner"  data-options="min:1,max:1000000,increment:10"
							style="width: 100px; ">至<input name="price_end" type="text"
							class="easyui-numberspinner"  data-options="min:1,max:1000000,increment:10"
							style="width: 100px;">
								</td>
							
								<td width="35%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('nurseBagList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-refresh"  onclick="$('#queryForm').form('clear')">重置</a> 
								</td>
							</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="nurseBagList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	               
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/shopManage/listBag.jhtml',
	                onLoadSuccess:loadSuccess,
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="cover_photo" formatter="imageFormatter" width="10%"   align="center">礼包宣传图片</th>
				<th field="bag_id" width="6%"   align="center">礼包编号</th>
				<th field="bag_name" width="15%"   align="center">礼包名称 </th>
				<th field="create_time"    width="8%" align="center">建立时间</th>
				<th field="bag_total_price" width="6%"   align="center">礼包价格(元) </th>
				<th field="project_content" width="15%"   align="center">礼包内容 </th>
				<th field="putaway_time" formatter="putFormatter" width="13%"   align="center">上架时间</th>
				<th field="overdue_time" width="6%"   align="center">有效期（天）</th>
			    <th field="modify_time" formatter="handleFormatter" width="5%" align="center">操作</th>
			</tr>
		</thead>
	</table>
	
    </div>
    </div>
  <div id="showBagWindow" class="easyui-window" title="查看礼包详情信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width: 550px; height: 440px; background-color: #FFFFFF"></div>
    <div id="bigImageViewWindow" class="easyui-window" title="查看大图"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width: 1150px; height: 440px; background-color: #FFFFFF">
		<body style="margin: 0; padding: 0">
	<div   style="text-align: center; vertical-align: middle;">
		<img id="bigImage"  style="vertical-align:middle;" >
	</div>
    </body>
    </div>
    
</body>


