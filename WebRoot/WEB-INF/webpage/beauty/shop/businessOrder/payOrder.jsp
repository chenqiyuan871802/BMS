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
				<form id="startOrderForm"
					action="${ctx }/shop/orderManage/startOrder.jhtml" method="post">
					<input type="hidden" name="order_id"  id="order_id" value="${orderDto.order_id}" />
					<input type="hidden" name="mobile" id="mobile" value="${orderDto.mobile}" />
					<input type="hidden"  id="count" value="${count}" />
					<input type="hidden"  id="total_beauty" value="${beauty_num}" />
					<input type="hidden"  id="beauty_price" value="${nurseProjectPO.beauty_price}" />
					<input type="hidden"  id="record_id"  />
					<input type="hidden" id="payment" />
					<table class="kv-table">
						<tbody>
							<tr>
								<td class="kv-label">顾客姓名</td>
								<td class="kv-content">
								
								<input type="text"  name="username" id="username" value="${orderDto.username}"	class="easyui-textbox"  data-options="validType:'length[1,50]'"   style="width: 230px; height: 30px" >
								</td>
								<td class="kv-label">手机号码</td>
								<td class="kv-content">${orderDto.mobile}</td>
							</tr>
						</tbody>
					</table>
					<div class="column">
						<span class="current">消费记录</span>
					</div>
					<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">消费内容</td>
							<td class="kv-content" colspan="3">${nurseProjectPO.project_name}</td>
						</tr>
						<tr>
							<td class="kv-label">价格</td>
							<td class="kv-content" colspan="3">
							<fmt:formatNumber value="${nurseProjectPO.rmb_price }" type="number" />元
							（${nurseProjectPO.beauty_price }个颜值）
							</td>
						</tr>
						<tr>
							<td class="kv-label" rowspan="2">选择扣款方式</td>
							<td class="kv-content" colspan="3">
							<input type="radio" name="pay_way" value="1" onclick="clickRedio(this.value)" />颜值&nbsp;&nbsp;（可用颜值${beauty_num}个）
							
							</td>
						</tr>
						<tr>
						<td class="kv-content" colspan="3">
							<input type="radio" name="pay_way" value="2" />礼包项目（可用礼包项目${count}个）
							<c:if test="${count>0}">
							<a class="easyui-linkbutton button-complete button-warning" href="javascript:void(0)"
			    onclick="showPayProject()" style="width: 100px;">选择支付项目</a>
							</c:if>
					    </td>
					    </tr>
					    <tr>
							<td class="kv-label">待支付</td>
							<td class="kv-content" colspan="3">
							<span id="showPay" style="color:red"></span>
							</td>
						</tr>
						<tr>
						<td class="kv-label" >验证码</td>
						<td class="kv-content" colspan="3">
							<input  type="text"  name="checkCode" id="checkCode" prompt="请输入支付验证码" 
							 autocomplete="off"	class="easyui-textbox"  />
						    <a class="easyui-linkbutton button-warning" href="javascript:void(0)" id="sendCode"
			    onclick="sendCheckCode()" style="width: 120px"><span id="showCode" >发送验证码</span></a>
					    </td>
						</tr>
					</tbody>
					</table>
				</form>
				<div height="35px"
					style="text-align: center; background: #F4F4F4; padding: 5px 0 0;">
					<a class="easyui-linkbutton button-complete button-success"
						href="javascript:void(0)"
						onclick="payCommonOrder()"
						style="width: 70px">确定</a> &nbsp;
				</div>
			</div>
		</div>
		<script type="text/javascript">
		   var   t=60;
		    var num=0;
		    var timeout;
		    $(function(){
		    	clearTimeout(timeout);
		    })
		    
		    function clickRedio(pay_way){
		    	if(pay_way=='1'){
		    		 var beauty_price_temp=$("#beauty_price").val();
		           $("#showPay").html(beauty_price_temp+'个颜值')
		    	}
		    }
		    function showPayProject(){
		    	
		    	showWindow('showPayProjectWindow','${ctx}/shop/orderManage/initPayProject.jhtml?project_id=${orderDto.project_id}&custom_user_id=${orderDto.custom_user_id}');
		    }
		    //发送验证码
			function sendCheckCode(){
				var pay_way=$("input[name='pay_way']:checked").val();
				 if(pay_way==''||pay_way==undefined){
					 $.messager.alert('警告信息', '请选择扣款方式', 'warning');
					 return ;
				 }
				var mobile=$("#mobile").val();
				if(mobile==''){
					$.messager.alert('警告信息', '消费用户的手机号码不能为空', 'warning');
					return ;
				}
			    //颜值支付
				if(pay_way=='1'){
				   var total_beauty_temp=$("#total_beauty").val();
				   var beauty_price_temp=$("#beauty_price").val();
				   var total_beauty=parseInt(total_beauty_temp);
				   var beauty_price=parseInt(beauty_price_temp);
				   if(beauty_price>total_beauty){
					   $.messager.alert('警告信息', '颜值不足，请进行充值或使用其他扣款方式', 'warning');
					   return ;
				   }
				   
				}else{ //礼包项目支付
					   var count=$("#count").val();
				       if(count==0){
				    	   $.messager.alert('警告信息', '你没有可以支付项目', 'warning');
				    	   return ;
				       }
				       var record_id=$("#record_id").val();
				       if(record_id==''||record_id==undefined){
				    	   $.messager.alert('警告信息', '请选择你要支付的项目', 'warning');
				    	   return ;
				       }
				       
				}
				
				$.ajax({
					type : 'post',
					url : '${ctx}/wechat/login/sendCheckCode.jhtml',
					data : {
						mobile : mobile
					},
					dataType : 'json',
					success : function(data) {
						if (data) {
							if (data.appcode == "1") {
								showMsg('提示', data.appmsg);
								$("#sendCode").linkbutton("disable");
								updateGetCode();
							} else {
								$.messager.alert('错误信息', data.appmsg, 'error');
								
							}
						} else {
							$.messager.alert('错误信息', '验证码发送失败', 'error');
						}
					},
					error : function() {
						$.messager.alert('错误信息', '验证码发送失败', 'error');
					}
				})
			}
		 
			function updateGetCode() {
                num++;
                
				if (num == t) {
					clearTimeout(timeout);
					num=0;
					 $('#sendCode').linkbutton('enable');
					$("#showCode").html("重新发送验证码");
				} else {
					var printnr = t - num;
					$("#showCode").html(printnr + "秒后再发送");
					 timeout=setTimeout(function(){ updateGetCode()},1000);
				}
              
			}
			//进行订单支付
			function payCommonOrder(){
				var pay_way=$("input[name='pay_way']:checked").val();
				 if(pay_way==''||pay_way==undefined){
					 $.messager.alert('警告信息', '请选择扣款方式', 'warning');
					 return ;
				 }
				 var mobile=$("#mobile").val();
				 if(mobile==''){
						$.messager.alert('警告信息', '消费用户的手机号码不能为空', 'warning');
						return ;
					}
				 var checkCode=$("#checkCode").textbox('getValue');
				 if(checkCode==''){
						$.messager.alert('警告信息', '请输入支付验证码', 'warning');
						return ;
					}
				 var order_id=$("#order_id").val();
				 var username=$("#username").val();
				 //颜值支付
					if(pay_way=='1'){
					   var total_beauty_temp=$("#total_beauty").val();
					   var beauty_price_temp=$("#beauty_price").val();
					   var total_beauty=parseInt(total_beauty_temp);
					   var beauty_price=parseInt(beauty_price_temp);
					   if(beauty_price>total_beauty){
						   $.messager.alert('警告信息', '颜值不足，请进行充值或使用其他扣款方式', 'warning');
						   return ;
					   }
					   var beauty_num=$("#beauty_price").val();
					   payBeautyOrder(order_id,beauty_num,mobile,username,checkCode);
					}else{ //礼包项目支付
						   var count=$("#count").val();
					       if(count==0){
					    	   $.messager.alert('警告信息', '你没有可以支付项目', 'warning');
					    	   return ;
					       }
					       var record_id=$("#record_id").val();
					       if(record_id==''||record_id==undefined){
					    	   $.messager.alert('警告信息', '请选择你要支付的项目', 'warning');
					    	   return ;
					       }
					       payProjectOrder(order_id,record_id,mobile,username,checkCode);
					}
			}
			//颜值进行订单支付
			function payBeautyOrder(order_id,beauty_num,mobile,username,checkCode){
				$.ajax({
					type : 'post',
					url : '${ctx}/shop/orderManage/saveBeautyPayOrder.jhtml',
					data : {
						order_id:order_id,
						beauty_num:beauty_num,
						mobile:mobile,
						username:username,
						checkCode:checkCode
					},
					dataType : 'json',
					success : function(data) {
						if (data) {
							if (data.appcode == "1") {
								showMsg('提示', data.appmsg);
								$('#recordList').datagrid({});
								closeWindow('payOrderWindow')
							} else {
								$.messager.alert('错误信息', data.appmsg, 'error');
								
							}
						} else {
							$.messager.alert('错误信息', '收款失败：颜值支付系统发生异常', 'error');
						}
					},
					error : function() {
						$.messager.alert('错误信息', '收款失败：颜值支付系统发生异常', 'error');
					}
				})
			}
			//项目进行订单支付
			function payProjectOrder(order_id,record_id,mobile,username,checkCode){
				$.ajax({
					type : 'post',
					url : '${ctx}/shop/orderManage/saveProjectPayOrder.jhtml',
					data : {
						order_id:order_id,
						record_id:record_id,
						mobile:mobile,
						username:username,
						checkCode:checkCode
					},
					dataType : 'json',
					success : function(data) {
						if (data) {
							if (data.appcode == "1") {
								showMsg('提示', data.appmsg);
								$('#recordList').datagrid({});
								closeWindow('payOrderWindow')
							} else {
								$.messager.alert('错误信息', data.appmsg, 'error');
								
							}
						} else {
							$.messager.alert('错误信息', '收款失败：项目支付系统发生异常', 'error');
						}
					},
					error : function() {
						$.messager.alert('错误信息', '收款失败：项目支付系统发生异常', 'error');
					}
				})
			}
		</script>
</body>
</html>