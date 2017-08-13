<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="addForm" action="${ctx }/system/couponActive/saveCouponActive.jhtml"
				method="post" >
			
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
				<tr>
						
					
					
					<tr>
						<td align="right" width="150px">活动名称：</td>
						<td><input  type="text"  name="active_name" required="true"
							class="easyui-textbox" data-options="validType:'length[1,50]'"
							style="width: 220px; height: 30px" ></td>
					</tr>
					<tr>
						<td align="right" width="150px">美研券数量：</td>
						<td><input name="beauty_num" type="text"
							class="easyui-numberspinner"  data-options="min:1,max:1000000,required:true"
							style="width: 220px; height: 30px" required="true">张券</td>
					</tr>
					<tr>
						<td align="right" width="150px">每张可兑换颜值数量：</td>
						<td><input name="bond_num" type="text"
							class="easyui-numberspinner"  data-options="min:1,max:1000000,required:true"
							style="width: 220px; height: 30px" required="true">个币</td>
					</tr>
					
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"href="javascript:void(0)"
			   onclick="submitFormData('addForm','dataList','addWindow')" style="width: 90px">生成美研卷</a> &nbsp;
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('addWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>