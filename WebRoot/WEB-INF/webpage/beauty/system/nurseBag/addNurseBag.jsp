<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">

/**
 * 保存礼包信息
 */
function saveNurseBag(){
	 var projectIds=getTableCellValue('#addNurseProject',0);
	 if(projectIds==''){
		 $.messager.alert('警告信息', '请添加护理项目', 'warning');
		 return ;
	 }
	
	 submitFormData('addNurseBagForm','nurseBagList','addNurseBagWindow')
}

</script>
 <link href="${ctx}/static/weblib/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
   <script type="text/javascript" charset="utf-8" src="${ctx}/static/weblib/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/static/weblib/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/weblib/umeditor/lang/zh-cn/zh-cn.js"></script>
<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addNurseBagForm" action="${ctx }/system/nurseBag/saveNurseBag.jhtml"
				method="post" enctype="multipart/form-data" >
			<input type="hidden" name="project_ids" id="project_ids"  />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
					<tr>
						<td align="right" width="110px">礼包名称：</td>
						<td><input name="bag_name" type="text" data-options="validType:'length[1,50]'"
							class="easyui-textbox" required="true"
							style="width: 250px; height: 30px" ></td>
							<td align="right" width="110px">状态：</td>
						<td><input  type="text"   name="status"	 value="1"  editable="false"  required="true"
							class="easyui-combobox"   data-options="data:show_statusStore,textField:1,valueField:0" style="width: 250px; height: 30px"></td>
					</tr>
					<tr>
						<td align="right" width="110px">列表图片：</td>
						<td ><input name="cover_photo_file" type="text" 
							 class="easyui-filebox"  
							style="width: 250px; height: 30px"  required="true"></td>
						<td align="right" width="110px">封面图片：</td>
						<td ><input name="cover_big_photo_file" type="text" 
							 class="easyui-filebox"  
							style="width: 250px; height: 30px"  required="true"></td>
					</tr>
				 
				    <tr>
						<td align="right" width="110px">护理项目：</td>
						<td colspan="3">
						<table cellpadding=5 cellspacing=0 width="90% "align="center" class="formTabel" id="addNurseProject">
						<tr>
						<td align="center" width="23%">项目名称</td>
						<td align="center" width="18%">价格（元）</td>
						<td align="center" width="18%">数量</td>
						<td align="center" width="20%">操作</td>
						<td align="center" rowspan="10">
						<a class="easyui-linkbutton" data-options="iconCls:'add'"href="javascript:void(0)"
			                   onclick="showWindow('showNurseProjectList','${ctx}/system/nurseBag/initNurseProject.jhtml?operate_mode=1');" style="width:120px">选择项目</a>
			           </td>
			           </tr>
						</table>
						
						</td>
					</tr>
					
					<tr>
					<td align="right" width="110px">礼包价格：</td>
						<td><input type="text"  name="bag_total_price" id="bag_total_price"	class="easyui-numberbox" 
							data-options="min:0,max:9999999999,precision:2" readonly style="width: 250px; height: 30px" >元</td>
					<td align="right" width="110px">开卡数量：</td>
						<td><input name="open_card_num" type="text"
							class="easyui-numberspinner" data-options="min:1,max:10000000"
							style="width: 250px; height: 30px" required="true"></td>
					</tr>
					
					<tr>
					<td align="right" width="110px">每次最少购买量：</td>
						<td><input name="min_buy_num" type="text"
							class="easyui-numberspinner" data-options="min:1,max:1000000"
							style="width: 250px; height: 30px" required="true"></td>
					<td align="right" width="110px">每次最大购买量：</td>
						<td><input name="max_buy_num" type="text"
							class="easyui-numberspinner" data-options="min:1,max:1000000"
							style="width: 250px; height: 30px" required="true"></td>
					</tr>
					<tr>
					<td align="right" width="110px">允许购买次数：</td>
						<td><input name="buy_count" type="text"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 250px; height: 30px" required="true"></td>
					<td align="right" width="110px">过期时间：</td>
						<td><input name="overdue_time" type="text" value="20"
							class="easyui-numberspinner" data-options="min:1,max:1000000"
							style="width: 250px; height: 30px" required="true">天</td>
						
					</tr>
					<tr>
					<td align="right" width="110px">上架时间：</td>
						<td><input name="putaway_time" type="text"
							class="easyui-datebox" value="${current_time}" editable="false"
							style="width: 250px; height: 30px" required="true"></td>
					<td align="right" width="110px">下架时间：</td>
						<td><input name="soldout_time" type="text" value="${current_time}"
							class="easyui-datebox" "  editable="false"
							style="width: 250px; height: 30px" required="true"></td>
					</tr>
					
					
					<tr>
					<td align="right" width="110px">排序号：</td>
						<td><input name="sort_no" type="text"
							class="easyui-numberspinner" value="1" data-options="min:1,max:1000000,required:true"
							style="width: 250px; height: 30px" required="true"></td>
					<td align="right" width="110px">礼包介绍：</td>
					<td >
					<input type="text"  name="bag_introduce"	class="easyui-textbox"  data-options="validType:'length[0,500]'" style="width: 550px; height: 70px" >
					</td>
					</tr>
					<tr>
					<td align="right" width="110px">购买说明：</td>
					<td colspan="3">
					<script id="addContent" name="content" type="text/plain" style="height: 150px;" ></script>
					</td>
					</tr>
				</table>

			</form>
		</div>
	<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="saveNurseBag()" style="width: 70px">保存</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addNurseBagWindow')" style="width: 70px">关闭</a>
		</div>
	</div>
<script type="text/javascript">
   var um=UM.getEditor('addContent');
       um.setWidth("98%");
   </script>
</body>
</html>