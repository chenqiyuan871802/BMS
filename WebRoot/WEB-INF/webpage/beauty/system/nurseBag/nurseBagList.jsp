<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="show_status"/>
<IMS:codeFormatter fields="show_status"/>
</head>
<script type="text/javascript">
//查看大图
function showBigImageView(imageSrc){
  var imageUrl='${ctx}/'+imageSrc;
	$('#bigImage').attr('src',imageUrl); 
	$('#bigImageViewWindow').window('open');
}
function getTableCellValue(tableId,cellIndex){
	
	 var trList = $(tableId+" tr");
	 var cellValue=''
	 for(var i=1;i<trList.length;i++){
	   var tdArr = trList.eq(i).find("td");
	   var  value= tdArr.eq(cellIndex).find("input").val();
	   if(i==1){
		   cellValue=value;
	   }else{
		   cellValue+=","+value;
	   }
			
	}
	 return cellValue;
}
/**
 * 查看护理项目详情
 */
function showNurseProjectDetail(project_id){
	showWindow('showNurseProjectWindow','${ctx}/system/nurseProject/goShow.jhtml?project_id='+project_id)
	
}
function deleteProject(obj){
	$(obj).parent().parent().remove();    
	sumTotalPrice()
}
function sumTotalPrice(){
	var operateWay=$("#operateWay").val();
	var tableId='#addNurseProject'
	var bag_total_price="#bag_total_price";
	if(operateWay=='2'){
		tableId='#modifyNurseProject'
	    bag_total_price="#modify_total_price";
	}
	var trList = $(tableId+" tr");
	var total_price=0;
	for(var i=1;i<trList.length;i++){
		 var tdArr = trList.eq(i).find("td");
		 var  price = tdArr.eq(1).find("input").val();//价格
		 var  num = tdArr.eq(2).find("input").val();//数量
		 var single_price=price*num;
		 total_price+=single_price;
	}
	$(bag_total_price).numberbox('setValue',total_price);
}
function changePrice(newPrice,oldPrice){
	if(newPrice==''||newPrice==0){
		 $(this).numberbox('setValue',oldPrice);
	}
	sumTotalPrice();
}
function changeNum(newNum,oldNum){
	if(newNum==''||newNum==0){
		 $(this).numberspinne('setValue',oldNum);
	}
	sumTotalPrice();
}
function addBag(){
	$("#operateWay").val("1");
	showWindow('addNurseBagWindow','${ctx}/system/nurseBag/goAdd.jhtml');
}
function modifyBag(){
	$("#operateWay").val("2");
	modifyGridData('modifyNurseBagWindow','nurseBagList','bag_id','${ctx}/system/nurseBag/goModify.jhtml','请选择你要修改的礼包信息');
}
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<input id="operateWay" type="hidden"/>
<div  style="height:37px;background-color: white;"  data-options="region:'north',split:false">
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
							
								<td width="7%" style="text-align: right">状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="status"    class="easyui-combobox" editable="false"  data-options="data:show_statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								<td width="35%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('nurseBagList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="nurseBagList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
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
	                url:'${ctx}/system/nurseBag/listNurseBag.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="bag_id" width="8%"   align="center">礼包编号</th>
				<th field="bag_name" width="15%"   align="center">礼包名称 </th>
				<th field="bag_total_price" width="8%"   align="center">礼包价格 </th>
				<th field="open_card_num" width="8%"   align="center">开卡数量</th>
				<th field="remain_num" width="8%"   align="center">剩余卡数量</th>
				<th field="putaway_time" width="10%"   align="center">上架时间</th>
				<th field="soldout_time" width="10%"   align="center">下架时间</th>
				<th field="overdue_time" width="8%"   align="center">过期时间（天）</th>
				<th field="status"   formatter="show_statusFormatter" width="6%" align="center">状态</th>
				<th field="create_time"    width="12%" align="center">创建时间</th>
				<th field="sort_no" width="5%" align="center">排序号</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="addBag()">新增</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="edit" plain="true"
			onclick="modifyBag();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-search" plain="true"
			onclick="showGridData('showNurseBagWindow','nurseBagList','bag_id','${ctx}/system/nurseBag/goShow.jhtml','请选择你要查看礼包信息');;">查看礼包详情</a>	
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteGridData('nurseBagList','bag_id','${ctx}/system/nurseBag/deleteNurseBag.jhtml','请选择你要删除的礼包信息','你确认要删除该礼包信息吗？')">删除</a>
	  
	</div>
    </div>
    </div>
   <div id="addNurseBagWindow" class="easyui-window" title="新增礼包信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width:1150px; height: 480px; background-color: #FFFFFF"></div>
	<div id="modifyNurseBagWindow" class="easyui-window" title="修改礼包信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1150px; height: 480px; background-color: #FFFFFF"></div>
	<div id="showNurseBagWindow" class="easyui-window" title="查看礼包信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1150px; height: 480px; background-color: #FFFFFF"></div>
	<div id="showNurseProjectList" class="easyui-window" title="护理项目列表"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
   <div id="showNurseProjectWindow" class="easyui-window" title="查看项目信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
    <div id="bigImageViewWindow" class="easyui-window" title="查看大图"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1150px; height: 480px; background-color: #FFFFFF">
		<body style="margin: 0; padding: 0">
	<div   style="text-align: center; vertical-align: middle;">
		<img id="bigImage"  style="vertical-align:middle;" >
	</div>
    </body>
    
</body>


