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
							<td class="kv-label">照片</td>
							<td class="kv-content"><img style="width:120px;height:120px"  src="${ctx }/${shopUserPO.photo}"  /></td>
						</tr>
						<tr>
							<td class="kv-label">员工姓名</td>
							<td class="kv-content">${shopUserPO.username}</td>
						</tr>
						<tr>
							<td class="kv-label">员工账号</td>
							<td class="kv-content">${shopUserPO.account}</td>
						</tr>
						<tr>
							<td class="kv-label">性别</td>
							<td class="kv-content"><IMS:codeOut codeKey="${shopUserPO.sex}" field="sex"/></td>
						</tr>
						<tr>
							<td class="kv-label">职位</td>
							<td class="kv-content">${shopPostPO.post_name }</td>
						</tr>
						<tr>
							<td class="kv-label">手机号码</td>
							<td class="kv-content">${shopUserPO.mobile}</td>
						</tr>
						<tr>
							<td class="kv-label">员工生日</td>
							<td class="kv-content"><fmt:formatDate value="${shopUserPO.born_date}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td class="kv-label">身份证号码</td>
							<td class="kv-content">${shopUserPO.idno}</td>
						</tr>
						<tr>
							<td class="kv-label">住址</td>
							<td class="kv-content">${shopUserPO.address }</td>
						</tr>
						
					</tbody>
				</table>
				
		</div>
	</div>

</body> 
</html>