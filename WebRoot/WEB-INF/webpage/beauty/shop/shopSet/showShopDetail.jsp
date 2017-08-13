<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<div class="content">
		      <div title="基本信息" data-options="closable:false" class="basic-info">
		      	<div class="column"><span class="current">店铺资料</span></div>
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">店铺全称</td>
							<td class="kv-content">${shopPO.shop_name}（${shopPO.short_name }）</td>
							<td class="kv-label">店铺法人</td>
							<td class="kv-content">${shopOwner.username}</td>
							<td class="kv-content" rowspan="8"><c:if test="${not empty shopPO.shop_qrcode}"><img src="${ctx}/${shopPO.shop_qrcode}?+Math.random()" style="width:150px;height:150px" /></br>商家二维码</c:if></td>
						</tr>
						<tr>
							<td class="kv-label">店铺描述</td>
							<td class="kv-content">${shopPO.shop_remark}</td>
							<td class="kv-label">法人手机</td>
							<td class="kv-content">${shopOwner.mobile}</td>
						</tr>
						<tr>
							<td class="kv-label">营业执照号码</td>
							<td class="kv-content">${shopPO.shop_license}</td>
							<td class="kv-label">身份证号码</td>
							<td class="kv-content">${shopOwner.idno}</td>
						</tr>
						<tr>
							<td class="kv-label">店铺面积</td>
							<td class="kv-content"><fmt:formatNumber type="number"  value="${shopPO.shop_area}" />㎡</td>
							<td class="kv-label">店铺电话</td>
							<td class="kv-content">${shopPO.shop_phone}</td>
						</tr>
						<tr>
							<td class="kv-label">营业时间</td>
							<td class="kv-content">${shopPO.begin_time}-${shopPO.end_time}</td>
							<td class="kv-label">店铺数量</td>
							<td class="kv-content">${count}</td>
						</tr>
						<tr>
							<td class="kv-label">详细地址</td>
							<td class="kv-content">${shopPO.shop_address}</td>
							<td class="kv-label">主营项目</td>
							<td class="kv-content">${shopPO.shop_project}</td>
						</tr>
					</tbody>
				</table>
				</div>
		</div>
	</div>
	
</body> 
</html>