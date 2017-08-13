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
		      	 <form id="saveShopSetForm" action="${ctx }/shop/shopManage/saveShopSet.jhtml"
				method="post" >
		      	<table class="kv-table" style="width：600px;">
					<tbody>
						<tr>
							<td class="kv-label" colspan="4">
                                                            工作机管理（如果您店铺的所有员工都可以操作，建议设置电脑为工作机）
                                                 </td>
						
						</tr>
						<c:if test="${show_status=='0'}">
						<tr>
						<td class="kv-label">本机IP</td>
					    <td class="kv-content">
					    <input  type="text"  name="shop_ip" 	class="easyui-textbox" required="true"
					      style="width: 250px; height: 30px" >
					    </td>
						<td class="kv-label">是否设置密码</td>
					    <td class="kv-content">
					    <input  type="checkbox"  name="whether_set" id="whether_set"	 value="1"  onclick="handleFunc()"/>设置密码
					    </td>
					    </tr>
						<tr>
						<td class="kv-label">本机密码</td>
					    <td class="kv-content">
					    <input  type="password"  name="work_password" id="password" data-options="validType:'length[1,20]'"	class="easyui-textbox" 
					      style="width: 250px; height: 30px" >
					    </td>
						<td class="kv-label">重复密码</td>
					    <td class="kv-content">
					    <input  type="password"  name="repassword" id="repassword"	class="easyui-textbox" validType="equals['#password']"
					    
					      style="width: 250px; height: 30px" >
					    </td>
					    
					    </tr>
					    <tr>
					       <td class="kv-content" colspan="4">
					       <div  height="35px"  style="text-align: center; padding: 5px 0 0;">
					       <a class="easyui-linkbutton button-complete button-success" dhref="javascript:void(0)"
			    onclick="saveShopSet()" style="width:120px">设定工作机</a> 
			    
			    </div>
					       </td>
					    </tr>
					    </c:if>
						<c:if test="${show_status=='1'}">
						<tr>
						<td class="kv-label">本机IP</td>
					    <td class="kv-content">
					    <input  type="text"  name="shop_ip" value="${shopWorkPO.shop_ip}" 	class="easyui-textbox" required="true"
					      style="width: 250px; height: 30px" >
					    </td>
						<td class="kv-label">是否设置密码</td>
					    <td class="kv-content">
					    <input  type="checkbox"  name="whether_set" id="whether_set" <c:if test="${ shopWorkPO.whether_set=='1'}"> value="1" checked="checked"  disabled="true"  </c:if>	 onclick="handleFunc()"/>设置密码
					    </td>
					    </tr>
					  <c:if test="${ shopWorkPO.whether_set=='1'}">  
						<tr>
						<td class="kv-label">本机密码</td>
					    <td class="kv-content" colspan="3">
					    <input  type="password"  name="work_password" value=''   required="true"  autocomplete="off" id="password" data-options="validType:'length[1,20]'"	class="easyui-textbox" 
					      style="width: 250px; height: 30px" >
					    </td>
						
					    </tr>
					   </c:if>
					    <tr>
					       <td class="kv-content" colspan="4">
					       <div  height="35px"  style="text-align: center; padding: 5px 0 0;">
					       <a class="easyui-linkbutton button-complete button-success" dhref="javascript:void(0)"
			    onclick="resetShopSet('${shopWorkPO.work_id}','${ shopWorkPO.whether_set}')" style="width:120px">重置工作机</a> 
					       <a class="easyui-linkbutton button-complete button-danger" dhref="javascript:void(0)"
			    onclick="relieveShopSet('${shopWorkPO.work_id}','${ shopWorkPO.whether_set}')" style="width:120px">解除工作机</a> 
			    
			    </div>
					       </td>
					    </tr>
					    </c:if>
					</tbody>
				</table>
				</form>
				</div>
		</div>
	</div>
	<script type="text/javascript">
	  function handleFunc(){
		  if($("#whether_set").is(":checked")){
			
			  $("#password").textbox({required:true})
			  $("#repassword").textbox({required:true})
		  }else{
			  $("#password").textbox({required:false})
			  $("#repassword").textbox({required:false})
		  }
	  }
	  //
	  function saveShopSet(){
		  $.messager.progress({
				title : '信息操作',
				text : '数据正在保存中，请耐心等待...'
			});
			$('#saveShopSetForm').form('submit', {
				onSubmit : function(param) {
					var result = $(this).form('enableValidation').form('validate');
					if (!result) {
						$.messager.progress('close');
					}
					return result;
				},
				success : function(data) {
					$.messager.progress('close');
					var data = eval('(' + data + ')');
					if (data) {
						if (data.appcode == "1") {
							showMsg('提示', data.appmsg);
							localStorage.setItem("IMSWORKSN", data.work_sn);
							parent.window.refreshShopSet();
						} else if (data.appcode == "0") {
							$.messager.alert('警告信息', data.appmsg, 'warning');
						} else {
							$.messager.alert('错误信息', data.appmsg, 'error');
						}
					} else {
						$.messager.alert('错误信息', '操作失败', 'error');
					}
				},
				onLoadeError : function() {
					$.messager.progress('close');
					$.messager.alert('错误信息', '操作失败', 'error');
				}
			});

	  }
       function relieveShopSet(work_id,whether_set){
    	   var work_password='';
    	   if(whether_set=='1'){
    		 work_password=$("#password").textbox('getValue');
    		   if(work_password==''){
    			   $.messager.alert('警告信息','请输入本机密码进行工作机解除', 'warning');
    			   return ;
    		   }
    	   }
    		$.ajax({
				type : 'post',
				url : '${ctx}/shop/shopManage/relieveShopSet.jhtml',
				data : {
					work_id : work_id,
					work_password:work_password,
					whether_set:whether_set
				},
				dataType : 'json',
				success : function(data) {
					if (data) {
						if (data.appcode == "1") {
							showMsg('提示', data.appmsg);
							parent.window.refreshShopSet();
						} else {
							$.messager.alert('错误信息', data.appmsg, 'error');
							
						}
					} else {
						$.messager.alert('错误信息', '解除工作机失败', 'error');
					}
				},
				error : function() {
					$.messager.alert('错误信息', '解除工作机失败', 'error');
				}
			})
       }
       function resetShopSet(work_id,whether_set){
    	   var work_password='';
    	   if(whether_set=='1'){
    		 work_password=$("#password").textbox('getValue');
    		   if(work_password==''){
    			   $.messager.alert('警告信息','请输入本机密码进行工作机重置', 'warning');
    			   return ;
    		   }
    	   }
    		$.ajax({
				type : 'post',
				url : '${ctx}/shop/shopManage/resetShopSet.jhtml',
				data : {
					work_id : work_id,
					work_password:work_password,
					whether_set:whether_set
				},
				dataType : 'json',
				success : function(data) {
					if (data) {
						if (data.appcode == "1") {
							showMsg('提示', data.appmsg);
							localStorage.setItem("IMSWORKSN", data.work_sn);
						} else {
							$.messager.alert('错误信息', data.appmsg, 'error');
							
						}
					} else {
						$.messager.alert('错误信息', '重置工作机失败', 'error');
					}
				},
				error : function() {
					$.messager.alert('错误信息', '重置工作机失败', 'error');
				}
			})
       }
      
	</script>
	
</body> 
</html>