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
							<td class="kv-label">消费内容</td>
							<td class="kv-content">${orderDto.order_content}</td>
							<td class="kv-label">实付金额</td>
							<td class="kv-content" ><fmt:formatNumber type="number"  value="${orderDto.pay_money}" />元</td>
						</tr>
						<tr>
							<td class="kv-label">支付方式</td>
							<td class="kv-content"><IMS:codeOut codeKey="${orderDto.pay_way}" field="pay_way"/></td>
							<td class="kv-label">支付时间</td>
							<td class="kv-content" ><fmt:formatDate value="${orderDto.pay_time}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td class="kv-label">服务员工</td>
							<td class="kv-content">${orderDto.server_name}</td>
							<td class="kv-label">操作员工</td>
							<td class="kv-content" >${orderDto.account }</td>
						</tr>
					
					</tbody>
				</table>
				
		</div>
	</div>
	
</body> 
</html>