<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<input type="hidden" name="project_id"
				value="${nurseProjectPO.project_id }" />
			<table cellpadding=5 cellspacing=0 width=100% align="center"
				class="formTabel">

				<tr>
					<td align="center" width="110px">店铺信息</td>
					<td align="right" width="110px">店铺编号：</td>
					<td>${orderDto.shop_id}</td>
					<td align="right" width="110px">店铺名称：</td>
					<td>${orderDto.shop_name}</td>
				</tr>
				<tr>
					<td align="center" width="110px" rowspan="4">订单信息</td>
					<td align="right" width="110px">订单编号：</td>
					<td>${orderDto.order_id}</td>
					<td align="right" width="110px">订单状态：</td>
					<td><IMS:codeOut codeKey="${orderDto.order_status}"
							field="order_status" /></td>
				</tr>
				<tr>

					<td align="right" width="110px">顾客姓名：</td>
					<td>${orderDto.username}</td>
					<td align="right" width="110px">顾客账号：</td>
					<td>${orderDto.mobile}</td>
				</tr>

			
				<tr>

					<td align="right" width="110px">实付金额：</td>
					<td><fmt:formatNumber value="${orderDto.pay_money}" type="number"/>元</td>
					<td align="right" width="110px">购买份数：</td>
					<td>${orderDto.buy_num }份</td>
					
				</tr>
				<tr>
                    <td align="right" width="110px">支付方式：</td>
					<td><IMS:codeOut codeKey="${orderDto.pay_way}" field="pay_way"/></td>
					<td align="right" width="110px">支付时间：</td>
					
					<td ><fmt:formatDate value="${orderDto.pay_time}"
					
							pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
				<tr>
					<td align="center" width="110px" rowspan="3">消费内容</td>
					<td align="center" width="110px" rowspan="3" colspan="2"><c:if
							test="${not empty nurseBag.cover_photo}">
							<img src="${ctx}/${nurseBag.cover_photo}"
								style="width: 120px; height: 120px" />
						</c:if></td>
					<td align="right" width="110px">礼包名称：</td>
					<td>${nurseBag.bag_name}</td>

				</tr>
				<tr>
					<td align="right" width="110px">礼包内容：</td>
					<td>${bagProject}</td>
				</tr>
				<tr>
					<td align="right" width="110px">礼包价格：</td>
					<td><fmt:formatNumber value="${nurseBag.bag_total_price }" type="number"/>元</td>
				</tr>

			</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">

			<a class="easyui-linkbutton" data-options="iconCls:'close'"
				href="javascript:void(0)"
				onclick="closeWindow('showOrderDetailWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>