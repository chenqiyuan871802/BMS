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
			<form id="modifyShopForm" action="${ctx }/system/shopSys/updateShop.jhtml"
				method="post" enctype="multipart/form-data" >
				 <input type="hidden"  name="shop_id" value="${shopPO.shop_id }" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				
					<tr>
						<td align="right" width="110px">店主账号：</td>
						<td><input  type="text"  name="owner_id" value="${shopPO.owner_id}" editable="false"  class="easyui-combobox"	 data-options="url:'${ctx}/system/shopOwner/queryShopOwner.jhtml',method:'get',valueField:'shop_user_id',textField:'account'" class="easyui-textbox" required="true" data-options="validType:'loginname'" style="width: 250px; height: 30px" ></td>
							<td align="right" width="110px">状态：</td>
						<td><input  type="text"   name="show_status"	 value="${shopPO.show_status}"  editable="false"  required="true"
							class="easyui-combobox"   data-options="data:statusStore,textField:1,valueField:0" style="width: 250px; height: 30px"></td>
					</tr>
					<tr>
						<td align="right" width="110px">店铺名称：</td>
						<td><input name="shop_name" value="${shopPO.shop_name}" type="text" data-options="validType:'length[1,50]'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">店铺简称：</td>
						<td><input name="short_name" value="${shopPO.short_name}" type="text" data-options="validType:'length[1,10]'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
						
					</tr>
					
					<tr>
						<td align="right" width="110px">月目标现金收入：</td>
						<td><input type="text"  name="month_cash_in" value="${shopPO.month_cash_in}"	class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2" required="true" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">月目标消耗收入：</td>
						<td><input  type="text"   name="month_expend_in" value="${shopPO.month_expend_in}"	class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2"  required="true" style="width: 250px; height: 30px" ></td>
						
					</tr>
					<tr>
					<td align="right" width="110px">店铺标志：</td>
						<td>
						<c:if test="${not empty shopPO.shop_image}"><img src="${ctx}/${shopPO.shop_image}" style="width:150px;height:150px" /></c:if>
						<input name="shop_image_file" type="text" 
							 class="easyui-filebox"  
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">店铺封面图：</td>
						<td>
						<c:if test="${not empty shopPO.shop_detail_image}"><img src="${ctx}/${shopPO.shop_detail_image}" style="width:150px;height:150px" /></c:if>
						<input name="shop_detail_image_file" type="text" 
							 class="easyui-filebox"  
							style="width: 250px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="110px">营业执照号：</td>
						<td><input name="shop_license" value="${shopPO.shop_license}" type="text" data-options="validType:'length[1,50]'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">营业时间：</td>
						<td><input name="begin_time" value="${shopPO.begin_time}" type="text"  data-options="min:'8:00',max:'12:00'"
							class="easyui-timespinner" required="true" value="09:00"
							style="width: 100px; height: 30px" >-<input name="end_time" value="${shopPO.end_time}" type="text"  value="22:00"
							 required="true" class="easyui-timespinner" data-options="min:'12:00',max:'23:00'"
							style="width: 100px; height: 30px" ></td>
					</tr>
					<tr>
						
						<td align="right" width="110px">店铺面积：</td>
						<td><input name="shop_area" type="text" 
							class="easyui-numberbox" value="${shopPO.shop_area}"
							data-options="min:0,max:9999999999,precision:2" required="true"
							style="width: 250px; height: 30px" >㎡</td>
						<td align="right" width="110px">主营项目：</td>
						<td><input name="shop_project" value="${shopPO.shop_project}" type="text" data-options="validType:'length[1,50]'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
					</tr>
					
					<tr>
						
						<td align="right" width="110px">店铺类型：</td>
						<td><input name="shop_type" value="${shopPO.shop_type}" type="text" value="1"
							class="easyui-combobox"   data-options="data:shop_typeStore,textField:1,valueField:0" required="true"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="11px">排序号：</td>
						<td><input name="sort_no" value="${shopPO.sort_no}" type="text"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 250px; height: 30px" required="true"></td>
						
					</tr>
					<tr>
						<td align="right" width="110px">店铺电话：</td>
						<td><input name="shop_phone" value="${shopPO.shop_phone}" type="text" data-options="validType:'phoneAndMobile'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">店铺地址：</td>
						<td><input name="shop_address" value="${shopPO.shop_address}" type="text" data-options="validType:'length[1,100]'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
						
					</tr>
					 <tr>
						<td align="right" width="110px">地址X坐标：</td>
						<td><input type="text"  name="gps_x"	 value="${shopPO.gps_x}"class="easyui-textbox"  required="true" style="width: 250px; height: 30px" ></td>
						<td align="right" width="110px">地址Y坐标：</td>
						<td><input  type="text"   name="gps_y"  value="${shopPO.gps_y}"	class="easyui-textbox" 
                        required="true" style="width: 250px; height: 30px" ></td>
						
					</tr>
				
					<tr>
						
						
						<td align="right" width="110px">备注：</td>
						<td colspan="3"><input  type="text" value="${shopPO.shop_remark}"
							 name="shop_remark"	class="easyui-textbox"  data-options="validType:'length[0,400]'"
							style="width: 700px; height: 60px" ></td>
						
					</tr>
					<tr>
					<td align="right" width="110px">店铺介绍：</td>
					<td colspan="3">
					<script id="modifyContainer" name="shop_intro" type="text/plain" style="height: 250px;" >${shopPO.shop_intro}</script>
					</td>
					</tr>
				  
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('modifyShopForm','shopList','modifyShopWindow')" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('modifyShopWindow')" style="width: 70px">关闭</a>
		</div>
	</div>
	
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
   var um=UM.getEditor('modifyContainer');
   um.setWidth("98%");
   </script>
</body>
</html>