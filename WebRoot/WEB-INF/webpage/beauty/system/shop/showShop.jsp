<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">

</head>
<body style="margin: 0; padding: 0">
	<table cellpadding=5 cellspacing=0 width=100% align="center"
		class="formTabel">

					<tr>
						<td align="right" width="110px">店主账号：</td>
						<td>
						${shopUserPO.account }
						</td>
							<td align="right" width="110px">状态：</td>
						<td>
						<IMS:codeOut codeKey="${shopPO.show_status}" field="status"/>
						</td>
						<td width="130px" rowspan="3">
						<c:if test="${not empty shopPO.shop_qrcode}"><img src="${ctx}/${shopPO.shop_qrcode}?+Math.random()" style="width:120px;height:120px" /></c:if>
						</td>
					</tr>
					<tr>
						<td align="right" width="110px">店铺名称：</td>
						<td>
						${shopPO.shop_name}
						</td>
						<td align="right" width="110px">店铺简称：</td>
						<td>${shopPO.short_name}</td>
						
					</tr>
					
					<tr>
						<td align="right" width="110px">月目标现金收入：</td>
						<td>${shopPO.month_cash_in}</td>
						<td align="right" width="110px">月目标消耗收入：</td>
						<td>
						${shopPO.month_expend_in}
						</td>
						
					</tr>
					<tr>
					<td align="right" width="110px">店铺标志：</td>
						<td>
						<c:if test="${not empty shopPO.shop_image}"><img src="${ctx}/${shopPO.shop_image}" style="width:150px;height:150px" /></c:if>
					
						<td align="right" width="110px">店铺封面图：</td>
						<td colspan="2">
						<c:if test="${not empty shopPO.shop_detail_image}"><img src="${ctx}/${shopPO.shop_detail_image}" style="width:150px;height:150px" /></c:if>
						</td>
					</tr>
					<tr>
						<td align="right" width="110px">营业执照号：</td>
						<td>${shopPO.shop_license}</td>
						<td align="right" width="110px">营业时间：</td>
						<td colspan="2">${shopPO.begin_time}-${shopPO.end_time}</td>
					</tr>
					<tr>
						
						<td align="right" width="110px">店铺面积：</td>
						<td>${shopPO.shop_area}㎡</td>
						<td align="right" width="110px">主营项目：</td>
						<td colspan="2">${shopPO.shop_project}</td>
					</tr>
					
					<tr>
						
						<td align="right" width="110px">店铺类型：</td>
						<td><IMS:codeOut codeKey="${shopPO.shop_type}" field="shop_type"/></td>
						<td align="right" width="11px">排序号：</td>
						<td colspan="2">${shopPO.sort_no}</td>
						
					</tr>
					<tr>
						<td align="right" width="110px">店铺电话：</td>
						<td>${shopPO.shop_phone}</td>
						<td align="right" width="110px">店铺地址：</td>
						<td colspan="2">${shopPO.shop_address}</td>
						
					</tr>
					 <tr>
						<td align="right" width="110px">地址X坐标：</td>
						<td>${shopPO.gps_x}</td>
						<td align="right" width="110px">地址Y坐标：</td>
						<td colspan="2">${shopPO.gps_y}</td>
						
					</tr>
				
					<tr>
						
						
						<td align="right" width="110px">备注：</td>
						<td colspan="4">${shopPO.shop_remark}</td>
						
					</tr>
					<tr>
					<td align="right" width="110px">店铺介绍：</td>
					<td colspan="4">
					${shopPO.shop_intro}
					</td>
					</tr>



	</table>


</body>
</html>