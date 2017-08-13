<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/shopTaglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>IMS信息系统综合平台</title>
</head>
<body>
    <div class="mainindex">
    <div class="welinfo">
    <span><img src="${ctx }/static/shop/images/dp.png" alt="提醒" /></span>
    <b>你好，${shopUserPO.username}&nbsp;|&nbsp;你上次登陆时间：${shopUserPO.last_login_time}</b>
    </div>
    <ul class="infolist">
    <li><span>姓名：${shopUserPO.username}</span></li>
      <li><span>账号：${shopUserPO.account}</span></li>
    <li><span>身份：${shopUserPO.post_name}</span></li>
  
    </ul>
    </div>
    <div class="xline"></div>
    <c:if test="${shopUserInfo.post_code=='01'}">
    <div class="container">
		<div class="content">
		      <div title="基本信息" data-options="closable:false" class="basic-info">
		      	<div class="column"><span class="current">店铺列表</span></div>
		      	<table class="kv-table">
					<tbody>
						<tr>
						   <td class="kv-label">店铺二维码</td>
							<td class="kv-label">店铺编号</td>
							<td class="kv-label">店铺简称</td>
							<td class="kv-label">营业额</td>
							<td class="kv-label">营业记录数</td>
							<td class="kv-label">新增客户数</td>
							<td class="kv-label">本月营业额</td>
							<td class="kv-label">本月目标额</td>
							
							
						</tr>
					</tbody>
					<c:forEach items="${shopCountList}" var="shopCount">
					<tr>   
					         <td class="kv-label">
					         <c:if test="${not empty shopCount.shop_qrcode}"><img src="${ctx}/${shopCount.shop_qrcode}" 
							   style="width:80px;height:80px" /></c:if>
					         </td>
							<td class="kv-label">${shopCount.shop_id}</td>
							<td class="kv-label">${shopCount.shop_name}</td>
							
							<td class="kv-label">
							今:${shopCount.today_money }<br/>
							<br/>
							昨:${shopCount.yes_money }
							</td>
							<td class="kv-label">
							今:${shopCount.today_count }<br/>
							<br/>
							昨:${shopCount.yes_count }
							</td>
							<td class="kv-label">
							今:${shopCount.today_add_count }<br/>
							<br/>
							昨:${shopCount.yes_add_count }
							</td>
							<td class="kv-label">
							${shopCount.month_total_money}
							</td>
							<td class="kv-label">
							${shopCount.month_cash_in }
							</td>
							
						</tr>
					</c:forEach>
				</table>
				
	</div>
    </c:if>
</body>
</html>