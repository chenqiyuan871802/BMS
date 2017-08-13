<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">

</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
				 <input type="hidden"  name="project_id" value="${nurseProjectPO.project_id }" />
				<table cellpadding=5 cellspacing=0 width=100% align="center" class="formTabel">
					
					<tr>
						<td align="right" width="110px">项目名称：</td>
						<td  width="300px">${nurseProjectPO.project_name }</td>
					    <td align="right" width="110px">状态：</td>
						<td width="300px">
						<IMS:codeOut codeKey="${nurseProjectPO.status}" field="show_status"/>
						</td>
					</tr>
					<tr>
					<td align="right" width="110px">列表图片：</td>
						<td>
						<c:if test="${not empty nurseProjectPO.cover_photo}"><img src="${ctx}/${nurseProjectPO.cover_photo}" onclick="showBigImageView('${nurseProjectPO.cover_photo}')"  title="点击查看大图" style="width:150px;height:150px" /></c:if>
						</td>
					<td align="right" width="110px">封面图片：</td>
						<td>
						<c:if test="${not empty nurseProjectPO.cover_big_photo}"><img src="${ctx}/${nurseProjectPO.cover_big_photo}" onclick="showBigImageView('${nurseProjectPO.cover_big_photo}')"  title="点击查看大图" style="width:150px;height:150px" /></c:if>
						</td>
					</tr>
					<tr>
						<td align="right" width="110px">分类：</td>
						<td>
						${nurseProjectPO.type_name }
                        </td>
							<td align="right" width="110px">所用设备：</td>
						<td>
						 ${nurseProjectPO.use_device}
						</td>
					</tr>
					<tr>
					<td align="right" width="110px">服务时长：</td>
						<td>${nurseProjectPO.server_time }分钟</td>
					<td align="right" width="110px">人民币售价：</td>
						<td>${nurseProjectPO.rmb_price }元</td>
					</tr>
					<tr>
					<td align="right" width="110px">颜值售价：</td>
						<td>${nurseProjectPO.beauty_price}个</td>
					<td align="right" width="110px">标准手工费：</td>
						<td>${nurseProjectPO.manual_price}元</td>
					</tr>
					<tr>
					<td align="right" width="110px">活动手工费：</td>
						<td>
						${nurseProjectPO.active_price}
					     元</td>
					<td align="right" width="110px">排序号：</td>
						<td>${nurseProjectPO.sort_no }</td>
					</tr>
					<tr>
					<td align="right" width="110px">内容描述：</td>
					<td colspan="3" >
				 
				    <div class="projectContent"> ${nurseProjectPO.content }</div>
				   
					</td>
					</tr>
				  
				

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			
		    <a class="easyui-linkbutton" data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('showNurseProjectWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>