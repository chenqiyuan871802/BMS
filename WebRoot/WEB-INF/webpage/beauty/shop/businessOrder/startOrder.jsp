<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<div class="content">
		      <div title="基本信息" data-options="closable:false" class="basic-info">
		     <form id="startOrderForm" action="${ctx }/shop/orderManage/startOrder.jhtml"
				method="post" >
		      <input type="hidden"  name="order_id" value="${orderDto.order_id}" />
		      <input type="hidden"  name="project_id" id="project_id" value="${orderDto.project_id}" />
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">订单编号</td>
							<td class="kv-content">${orderDto.order_id}</td>
						</tr>
						<tr>
							<td class="kv-label">订单状态</td>
							<td class="kv-content"><IMS:codeOut codeKey="${orderDto.order_status}" field="order_status"/></td>
						</tr>
						<tr>
							<td class="kv-label">顾客账号</td>
							<td class="kv-content">${orderDto.mobile}</td>
						</tr>
						<tr>
							<td class="kv-label">预约时间</td>
							<td class="kv-content"><fmt:formatDate value="${orderDto.subscribe_time}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td class="kv-label">护理项目</td>
							<td class="kv-content">${nurseProjectPO.project_name}<a class="easyui-linkbutton button-complete button-warning" href="javascript:void(0)"
			    onclick="initNurseProject('${orderDto.order_id}')" style="width: 70px;margin: 0 0 0 100px">修改项目</a></td>
						</tr>
						<tr>
							<td class="kv-label">护理价格</td>
							<td class="kv-content"><fmt:formatNumber value="${nurseProjectPO.rmb_price }" type="number"/>元</td>
						</tr>
					</tbody>
				</table>
				</form>
				<div  height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton button-complete button-success" href="javascript:void(0)"
			    onclick="submitFormData('startOrderForm','recordList','startOrderWindow')" style="width: 70px">确认开始</a> &nbsp;
		</div>
		</div>
	</div>
	<script type="text/javascript">
	//初始化护理项目
	 function initNurseProject(order_id){
		 showWindow('showNurseProjectListWindow','${ctx}/shop/orderManage/initNurseProject.jhtml?order_id='+order_id);
	 }
	</script>
</body> 
</html>