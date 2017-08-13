<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="enroll_mode,sex,recent_type,wechat_status"/>
<IMS:codeFormatter fields="enroll_mode,sex,wechat_status"/>
</head>
<script type="text/javascript">
//格式化
function imageFormatter(value){  
		 if(IMSUtils.isNotEmpty(value)){
		    return '<img style="width:60px;height:60px"  src="${ctx }/'+value+'"  />';
		 }
		 return ''
}
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:67px;background-color: white;"  data-options="region:'north',split:false">
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
								
								<td width="7%" style="text-align: right">店铺名称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="shop_id" value="${shop_id }" readonly  class="easyui-combobox"	 data-options="url:'${ctx}/system/shopSys/queryShop.jhtml',method:'get',valueField:'shop_id',textField:'shop_name'" style="width: 150px; " />
								</td>
								
							
								<td width="7%" style="text-align: right">注册方式：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="enroll_mode"    class="easyui-combobox" editable="false"  data-options="data:enroll_modeStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								
								<td width="20%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('customUserList','doForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="refresh"  onclick="$('#doForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
						    	<td width="7%" style="text-align: right">会员号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="custom_user_id"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">昵称：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="nikename"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								
								<td width="7%" style="text-align: right">微信状态：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="wechat_status"    class="easyui-combobox" editable="false"  data-options="data:wechat_statusStore,textField:1,valueField:0" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">最近到店时间：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="recent_type"   class="easyui-combobox" editable="false"  data-options="data:recent_typeStore,textField:1,valueField:0" style="width: 150px; " />
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
	                singleSelect:true,
	                autoRowHeight:false, 
	                 nowrap:false,
	                pagination:true,
	                toolbar:'#toolbar',
	                striped:true,
	                queryParams : $('#doForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/system/customUser/listCustomUser.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="photo" formatter="imageFormatter"   align="center">头像</th>
				<th field="custom_user_id" width="7%"    align="center">会员号</th>
				<th field="mobile" width="8%"   align="center">手机 </th>
				<th field="username" width="5%"   align="center">姓名</th>
				<th field="nikename" width="7%"   align="center">昵称</th>
				<th field="sex" width="5%" formatter="sexFormatter"   align="center">性别</th>
				<th field="shop_name" width="13%" align="center">店铺名称</th>
				<th field="wechat_status" formatter="wechat_statusFormatter" width="6%" align="center">微信状态</th>
				<th field="qq" width="7%" align="center">QQ</th>
				<th field="email" formatter="formatCellTooltip" width="10%" align="center">邮箱</th>
				<th field="born_date" width="8%" formatter="formatDateTime" align="center">出生日期</th>
				<th field="recent_time"    width="10%" align="center">最近到店时间</th>
				<th field="enroll_mode"   formatter="enroll_modeFormatter" width="6%" align="center">注册方式</th>
				
			</tr>
		</thead>
	</table>
	
    </div>
    </div>
  
    
</body>


