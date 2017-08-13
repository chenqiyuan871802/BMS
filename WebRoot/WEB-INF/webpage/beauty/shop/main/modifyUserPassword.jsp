<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="uk-layui-form">
	<form class="layui-form"   action="${pageContext.request.contextPath}/shop/login/updateUserPassword.jhtml"
	 method="post"  data-target="#mainajaxwin"
	>
		<div class="layui-form-item">
			<label class="layui-form-label">原始密码：</label>
			<div class="layui-input-inline">
				<input type="password" name="oldpwd"  lay-verify="required"
					placeholder="请输入原始密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码：</label>
			<div class="layui-input-inline">
				<input type="password" id="password" name="password" lay-verify="required"
					placeholder="请输入登陆密码" width="200px" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 确认密码：</label>
			<div class="layui-input-inline">
				<input type="password"  id="repassword" name="repwd" lay-verify="repass"
				 placeholder="请输入确认密码" 	 autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-button">
			<div class="layui-button-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
				<button type="reset"  class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form();
  form.on('submit(formDemo)', function (formObj) {
	  $.ajax({
	        url: formObj.form.action,
	        type: formObj.form.method,
	        data: formObj.field,
	        dataType : 'json',
	        success : function(data) {
				if (data) {
					if (data.appcode == "1") {
						layer.alert(data.appmsg, {
							icon: 1,
							time: 2000 //2秒关闭（如果不配置，默认是3秒）
							});
						layer.close(layerwin);
					}  else {
						layer.alert(data.appmsg);
					}
				} else {
					layer.alert('操作失败');
				}
			},
			error : function() {
				
				layer.alert('操作失败，网络连接超时');
			}
	    });
      return false;
  });
  form.render(); //更新全部
  form.verify({
	  repass: function(value){
	    if(value != $('#password').val()){
	      return '两次输入的密码不一致，请确认';
	    }
	  }
	});      
});
</script>

