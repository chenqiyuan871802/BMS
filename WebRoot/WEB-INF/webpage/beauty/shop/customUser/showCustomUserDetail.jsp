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
		      	<div class="column"><span class="current">基本信息</span></div>
		      	<table class="kv-table">
					<tbody>
						<tr>
						   <td class="kv-label">顾客账号</td>
							<td class="kv-content">${customUserPO.mobile}</td>
							<td class="kv-label">顾客姓名</td>
							<td class="kv-content">${customUserPO.username }</td>
							<td class="kv-content" rowspan="3">
							<c:if test="${not empty customUserPO.photo}"><img src="${ctx}/${customUserPO.photo }" style="width:120px;height:120px" /></c:if>
							</td>
							
						</tr>
						<tr>
						   <td class="kv-label">性别</td>
							<td class="kv-content"><IMS:codeOut codeKey="${customUserPO.sex }" field="sex"/></td>
							<td class="kv-label">顾客生日</td>
							<td class="kv-content"><fmt:formatDate value="${customUserPO.born_date}" pattern="yyyy-MM-dd"/></td>
							
						</tr>
						<tr>
						   <td class="kv-label">注册时间</td>
							<td class="kv-content"><fmt:formatDate value="${customUserPO.enroll_time}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td class="kv-label">持有颜值</td>
							<td class="kv-content">${customUserPO.beauty_num }</td>
							
						</tr>
					</tbody>
				</table>
					<div class="column"><span class="current">礼包</span></div>
					 	<table class="kv-table">
					<tbody>
					<c:forEach var="bag" items="${bagList}">
					
			
					<tr>
					     <td class="kv-label" rowspan="3">
					     <c:if test="${not empty bag.cover_photo}"><img src="${ctx}/${bag.cover_photo }" style="width:90px;height:90px" /></c:if>
					     </td>
						   <td class="kv-label">礼包名称</td>
						 <td class="kv-content" colspan="3"	>${bag.bag_name} </td>
						</tr>
						<tr>
						   <td class="kv-label">包含项目</td>
							<td class="kv-content" >
							<c:forEach var="project" items="${bag.projectList}">
							 ${project.project_name }<c:if test="${project.project_status==2}">（已使用）</c:if>
							 <c:if test="${project.project_status==3}">（已过期）</c:if>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
							
							 </td>
							 <td class="kv-label">支付时间</td>
							<td class="kv-content"><fmt:formatDate value="${bag.create_time}"
							pattern="yyyy-MM-dd HH:mm" /> </td>
						</tr>
						<tr>
						   <td class="kv-label">礼包价格</td>
							<td class="kv-content" > <fmt:formatNumber value="${bag.bag_total_price}"
							type="number" />元</td>
							 <td class="kv-label">有效期</td>
							<td class="kv-content">${bag.valid_day}天</td>
						</tr>
						</c:forEach>
					</tbody>
					
					</table>
		</div>
	</div>
	
</body> 
</html>