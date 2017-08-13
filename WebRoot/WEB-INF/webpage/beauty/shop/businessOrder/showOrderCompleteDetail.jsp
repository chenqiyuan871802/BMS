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
		      	<div class="column"><span class="current">顾客信息</span></div>
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">顾客姓名</td>
							<td class="kv-content">${orderDto.username }</td>
							<td class="kv-label">顾客手机</td>
							<td class="kv-content">${orderDto.mobile}</td>
						</tr>
					</tbody>
				</table>
				<div class="column"><span class="current">订单信息</span></div>
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">订单编号</td>
							<td class="kv-content">${orderDto.order_id}</td>
							<td class="kv-label">订单状态</td>
							<td class="kv-content" ><IMS:codeOut codeKey="${orderDto.order_status}" field="order_status"/></td>
						</tr>
						<tr>
							<td class="kv-label">预约时间</td>
							<td class="kv-content"><fmt:formatDate value="${orderDto.subscribe_time}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td class="kv-label">预交定金</td>
							<td class="kv-content" ><fmt:formatNumber type="number"  value="${orderDepositPO.deposit_money}" />元</td>
						</tr>
						<tr>
							<td class="kv-label">定金支付方式</td>
							<td class="kv-content"><IMS:codeOut codeKey="${orderDepositPO.pay_way}" field="pay_way"/></td>
							<td class="kv-label">定金支付时间</td>
							<td class="kv-content" ><fmt:formatDate value="${orderDepositPO.pay_time}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td class="kv-label">定金退回时间</td>
							
							<td class="kv-content" colspan="3" ><fmt:formatDate value="${orderDepositPO.back_time}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td class="kv-label">实付金额</td>
							<td class="kv-content"><fmt:formatNumber value="${orderDto.pay_money}" type="number"/>元</td>
							<td class="kv-label">支付方式</td>
							<td class="kv-content" ><IMS:codeOut codeKey="${orderDto.pay_way}" field="pay_way"/></td>
						</tr>
						<tr>
						<tr>
							<td class="kv-label">支付时间</td>
							<td class="kv-content" colspan="3" ><fmt:formatDate value="${orderDto.pay_time}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td class="kv-label">服务员工</td>
							<td class="kv-content">${orderDto.server_name}</td>
							<td class="kv-label">操作员工</td>
							<td class="kv-content" >${orderDto.account }</td>
						</tr>
						</tr>
					
					</tbody>
				</table>
					<div class="column"><span class="current">消费内容</span></div>
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-content" rowspan="3">
							<c:if test="${not empty nurseProjectPO.cover_photo}"><img src="${ctx}/${nurseProjectPO.cover_photo}" 
							   style="width:120px;height:120px" /></c:if>
							</td>
							<td class="kv-label">项目名称</td>
							<td class="kv-content">${nurseProjectPO.project_name}</td>
							
							
						</tr>
						<tr>
							<td class="kv-label">护理价格</td>
							<td class="kv-content"><fmt:formatNumber value="${nurseProjectPO.rmb_price }" type="number"/>元</td>
							
							
						</tr>
						<tr>
							<td class="kv-label">颜值</td>
							<td class="kv-content">${nurseProjectPO.beauty_price}个</td>
							
							
						</tr>
					</tbody>
				</table>
		</div>
	</div>
	
</body> 
</html>