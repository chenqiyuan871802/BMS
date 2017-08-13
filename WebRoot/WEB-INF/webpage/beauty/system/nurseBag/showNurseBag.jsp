<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<script type="text/javascript">

function operateFormatter(value, row, index) {
	var str = '<a  title="查看护理项目详情"   onclick="showNurseProjectDetail(\''+row.project_id+'\')" href="javascript:void(0)" ><img title="查看护理项目详情" src="${ctx}/static/common/images/icons/search.png"></a>';
	return str;
}

/**
 * 查看护理项目详情
 */
function showNurseProjectDetail(project_id){
	showWindow('showNurseProjectWindow','${ctx}/system/nurseProject/goShow.jhtml?project_id='+project_id)
	
}

</script>
<div class="easyui-layout" style="width:100%;height:100%;">
<div data-options="region:'east',split:false" title="护理项目"  style="width:400px;">
<table  class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				    border:false,
	                singleSelect:false,
	                autoRowHeight:false, 
	                striped:true,
	                fit:true,
	                 url:'${ctx}/system/nurseBag/queryBagProjectList.jhtml?bag_id=${nurseBagPO.bag_id }',
	                ">
	               
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th field="project_name" width="35%"   align="center">项目名称 </th>
				<th field="project_new_price" width="20%"   align="center">价格</th>
				<th field="project_num" width="20%"   align="center">数量</th>
				<th field="project_id" width="20%" formatter="operateFormatter"   align="center">操作</th>
			</tr>
		</thead>
	</table>
	<div id="modifyTool" style="padding: 2px;">
        
		<a href="#" class="easyui-linkbutton" iconCls="add"
			plain="true"
			onclick="showWindow('showNurseProjectList','${ctx}/system/nurseBag/initNurseProject.jhtml?operate_mode=2');">添加</a> 
	
	   <a href="#" class="easyui-linkbutton" iconCls="del"
			plain="true" onclick="deleteNurseProject()">删除</a>
	  
	</div>
</div>
		<div data-options="region:'center',border:false" style="padding: 5px;">

				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
					<tr>
						<td align="right" width="110px">礼包名称：</td>
						<td>${nurseBagPO.bag_name}</td>
							<td align="right" width="110px">状态：</td>
						<td><IMS:codeOut codeKey="${nurseBagPO.status}" field="show_status"/></td>
					</tr>
				 
					<tr>
						<td align="right" width="110px">列表图片：</td>
						<td >
						<c:if test="${not empty nurseBagPO.cover_photo}"><img src="${ctx}/${nurseBagPO.cover_photo}" onclick="showBigImageView('${nurseBagPO.cover_photo}')"  title="点击查看大图" style="width:150px;height:150px" /></c:if>
						<td align="right" width="110px">封面图片：</td>
						<td >
						<c:if test="${not empty nurseBagPO.cover_big_photo}"><img src="${ctx}/${nurseBagPO.cover_big_photo}" onclick="showBigImageView('${nurseBagPO.cover_big_photo}')"  title="点击查看大图" style="width:150px;height:150px" /></c:if>
					
					</tr>
				 
					<tr>
					<td align="right" width="110px">礼包价格：</td>
						<td>${nurseBagPO.bag_total_price}元</td>
					<td align="right" width="110px">开卡数量：</td>
						<td>${nurseBagPO.open_card_num}</td>
					</tr>
					
					<tr>
					<td align="right" width="110px">每次最少购买量：</td>
						<td>${nurseBagPO.min_buy_num}</td>
					<td align="right" width="110px">每次最大购买量：</td>
						<td>${nurseBagPO.max_buy_num}</td>
					</tr>
					<tr>
					<td align="right" width="110px">允许购买次数：</td>
						<td>${nurseBagPO.buy_count}</td>
					<td align="right" width="110px">过期时间：</td>
						<td>${nurseBagPO.overdue_time}天</td>
						
					</tr>
					<tr>
					<td align="right" width="110px">上架时间：</td>
						<td><fmt:formatDate value="${nurseBagPO.putaway_time}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td align="right" width="110px">下架时间：</td>
						<td><fmt:formatDate value="${nurseBagPO.soldout_time}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
					
					
					<tr>
					<td align="right" width="110px">排序号：</td>
						<td>${nurseBagPO.sort_no}</td>
					<td align="right" width="110px">礼包介绍：</td>
					<td colspan="3">
				    <div>${nurseBagPO.bag_introduce}</div>
					</td>
					</tr>
					<tr>
					<td align="right" width="110px">购买说明：</td>
					<td colspan="3">
				    <div class="projectContent">${nurseBagPO.content}</div>
					</td>
					</tr>
				</table>

			
		</div>
	<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('showNurseBagWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>