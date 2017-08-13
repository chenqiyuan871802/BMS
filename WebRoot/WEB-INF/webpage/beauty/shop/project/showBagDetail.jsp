<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<div class="content">
		      <div   class="basic-info">
		      	
		      	<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">礼包宣传图片</td>
							<td class="kv-content"><img style="width:120px;height:120px" title="查看大图" onclick="showBigImageView('${nurseBagPO.cover_photo}')" src="${ctx }/${nurseBagPO.cover_photo}"  /></td>
						</tr>
						<tr>
							<td class="kv-label">礼包名称</td>
							<td class="kv-content">${nurseBagPO.bag_name}</td>
						</tr>
						<tr>
							<td class="kv-label">礼包价格</td>
							<td class="kv-content"><fmt:formatNumber value="${nurseBagPO.bag_total_price}" type="number"/>元</td>
						</tr>
						<tr>
							<td class="kv-label">礼包包含</td>
							<td class="kv-content">
							<c:forEach  var="project" items="${bagProjectList}" varStatus="status">
							${ status.index + 1}、&nbsp;${project.project_name}</br>
							</c:forEach>
                            </td>
						</tr>
						<tr>
							<td class="kv-label">上架时间</td>
							<td class="kv-content">
							<fmt:formatDate value="${nurseBagPO.putaway_time}" pattern="yyyy-MM-dd"/>
							至
							<fmt:formatDate value="${nurseBagPO.soldout_time}" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr>
							<td class="kv-label">建立时间</td>
							<td class="kv-content">
							<fmt:formatDate value="${nurseBagPO.create_time}" pattern="yyyy-MM-dd"/>
							
							</td>
						</tr>
						<tr>
							<td class="kv-label">有效期</td>
							<td class="kv-content">自购买日起${nurseBagPO.overdue_time}天</td>
						</tr>
						<tr>
							<td class="kv-label">购买说明</td>
							<td class="kv-content"><div class="projectContent">${nurseBagPO.content}</div></td>
						</tr>
						
						
					</tbody>
				</table>
				
		</div>
	</div>

</body> 
</html>