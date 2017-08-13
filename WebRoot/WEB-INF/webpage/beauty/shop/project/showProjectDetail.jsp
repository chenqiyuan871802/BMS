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
							<td class="kv-label">护理项目图片</td>
							<td class="kv-content"><img style="width:120px;height:120px" title="查看大图" onclick="showBigImageView('${nurseProjectPO.cover_photo}')" src="${ctx }/${nurseProjectPO.cover_photo}"  /></td>
						</tr>
						<tr>
							<td class="kv-label">项目名称</td>
							<td class="kv-content">${nurseProjectPO.project_name}</td>
						</tr>
						<tr>
							<td class="kv-label">护理价格</td>
							<td class="kv-content"><fmt:formatNumber value="${nurseProjectPO.rmb_price}" type="number"/>元&nbsp;&nbsp;&nbsp;或&nbsp;&nbsp;&nbsp;${nurseProjectPO.beauty_price}个颜值</td>
						</tr>
						<tr>
							<td class="kv-label">服务时间</td>
							<td class="kv-content">${nurseProjectPO.server_time}分钟</td>
						</tr>
						<tr>
							<td class="kv-label">内容描述</td>
							<td class="kv-content"><div class="projectContent">${nurseProjectPO.content}</div></td>
						</tr>
						
						
					</tbody>
				</table>
				
		</div>
	</div>
</body> 
</html>