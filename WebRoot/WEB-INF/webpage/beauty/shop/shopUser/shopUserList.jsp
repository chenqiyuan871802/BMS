<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="sex"/>
<IMS:codeFormatter fields="sex"/>
</head>
<script type="text/javascript">
function imageFormatter(value){  
	 if(IMSUtils.isNotEmpty(value)){
	    return '<img style="width:60px;height:60px"  src="${ctx }/'+value+'"  />';
	 }
	 return ''
}
//处理操作
function handleFormatter(value, row, index){
	var shop_user_id=row.shop_user_id;
	var handleStr="showShopUserDetail("+shop_user_id+")";
	var  str="<a href='#' onclick='"+handleStr+"' class='button-detail button-default button-lg'>详情</a>&nbsp;"
	return str;
}
function  loadSuccess(){
	$('.button-complete').linkbutton({ 
	});
	$('.button-detail').linkbutton({ 
	});
	$('.button-edit').linkbutton({ 
	});
}
function showShopUserDetail(shop_user_id){
	
	showWindow('showShopUserDetailWindow','${ctx}/shop/shopManage/showShopUserDetail.jhtml?shop_user_id='+shop_user_id);
}
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:75px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">员工账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="account"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">职位：</td>
								<td width="20%" style="text-align: left">
								<input type="text" name="post_code"   class="easyui-combobox"	 data-options="url:'${ctx}/shop/shopManage/queryShopPost.jhtml',method:'get',valueField:'post_code',textField:'post_name'" style="width: 150px; " />
								</td>
								<td width="30%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('shopUserList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-refresh"  onclick="$('#queryForm').form('clear')">重置</a> 
								</td>
							</tr>
							<tr>
						    	
								<td width="7%" style="text-align: right">工号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="work_number"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">手机：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px; " />
								</td>
								
								<td width="6%" style="text-align: right">入职时间：</td>
								<td width="13%" style="text-align: left">
								<input type="text"  name="entry_date_begin"   class="easyui-datebox" editable="false" style="width:150px" />
							      至：<input type="text" name="entry_date_end"  class="easyui-datebox" editable="false" style="width:150px" />
								</td>
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="shopUserList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
	                rownumbers:false,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/shopManage/listShopUser.jhtml',
	                onLoadSuccess:loadSuccess,
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="shop_user_id" hidden=“true”    align="center">员工编号</th>
				<th field="photo" formatter="imageFormatter"   align="center">照片</th>
				<th field="username" width="7%"   align="center">姓名</th>
				<th field="account" width="8%"   align="center">员工账号 </th>
				<th field="sex" width="5%" formatter="sexFormatter"   align="center">性别</th>
				<th field="work_number" width="8%"   align="center">工号</th>
				<th field="post_name" width="6%" align="center">职位</th>
				<th field="mobile" width="9%" align="center">手机</th>
				<th field="entry_date"    width="8%" align="center">入职时间</th>
				<th field="se" formatter="handleFormatter"    width="8%" align="center">操作</th>
				
			</tr>
		</thead>
	</table>
    </div>
    </div>
   <div id="showShopUserDetailWindow" class="easyui-window" title="员工详情"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:500px; height: 440px; background-color: #FFFFFF"></div>
	
   
    
</body>


