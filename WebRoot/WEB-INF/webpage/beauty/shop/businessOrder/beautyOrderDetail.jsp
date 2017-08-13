<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>

<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0">
<style type="text/css">
.span1,.span2{
display:inline-block;
}
.span1{
width:100px;
}
.span2{
width:200px;
}
</style>

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 5px;">
			<form id="auditForm" action="${ctx }/ihome/applyCommon/saveAuditByJyj.jhtml"
				method="post">
             <input type="hidden"  id="copyIdno" value="${dataDto.child_idno }" />
             <input type="hidden"  name="child_id" value="${dataDto.child_id }" />
             <input type="hidden"  name="user_id" value="${dataDto.user_id }" />
             <input type="hidden"  name="delay_id" value="${dataDto.delay_id}" />
				<table cellpadding=5 cellspacing=0 width=100% align="center"
					class="formTabel">

					<tr>
						<td align="right" width="100px">评审项目：</td>
						<td >延缓入学</td>
					</tr>


					<tr>
						<td align="right" width="100px">评审规则：</td>
						<td>
                           1.大于七周岁的适龄儿童需要提供延缓入学证明，才能申请积分入学
                        </td>
					</tr>
					<tr>
						<td align="right" width="100px">入学人：</td>
						<td >
                            <span class="span1"> ${dataDto.child_name }</span><span class="span2">${dataDto.child_idno }</span>&nbsp;&nbsp;
                         <a href="#" title="点击按钮复制证件号"   class="easyui-linkbutton" id="copyButton" data-options="iconCls:'icon-back'">复制证件号</a>
                        </td>
					</tr>
					
					<tr >
						<td align="right" width="100px" rowspan="3" >评审结果：</td>
						<td align="left" >
						<input type="radio" name="audit_result"  value="1" checked />证明有效
						<span style="margin-left:100px"><input type="radio" name="audit_result"  value="0"  />证明无效</span>
						
						</td>
					</tr>

				</table>

			</form>
		</div>
		<div data-options="region:'south',border:false" height="35px"
			style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'ok'"
				href="javascript:void(0)"
				onclick="submitFormData('auditForm','unAuditedList','auditWindow')"
				style="width: 70px">保存</a> &nbsp; <a class="easyui-linkbutton"
				data-options="iconCls:'close'" href="javascript:void(0)"
				onclick="closeWindow('auditWindow')" style="width: 70px">关闭</a>
		</div>
	</div>

</body>
</html>