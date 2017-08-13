<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/layuiTaglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><IMS:paramOut paramKey="shop_sys_title" /></title>
<link rel="shortcut icon"
	href="${ctx }/static/common/images/favicon.ico" />
<link href="${ctx }/static/shop/css/shop.css" rel="stylesheet"
	type="text/css" />

</head>

<body>
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header" style="background-color: #f7b824;">
			<!-- logo区域 -->
			<div class="layui-logo-box">
				<a class="logo" href="http://jqadmin.jqcool.net" title="美研社"></a>
			</div>

			<!-- 主菜单区域 -->
			<div class="layui-main-menu">
				<ul class="layui-nav clearfix" id="menu" lay-filter="main-menu">
					<c:forEach var="menu" items="${grantMenuList}" varStatus="status">
						<c:if test="${menu.parent_id!='0' }">
							<li class="layui-nav-item"><a href="javascript:void(0)"
								onclick="return false;" class="menuTab"
								data-id="menu${status.index + 1}" data-title="${menu.menu_name}"
								data-href="${ctx }/${menu.url }" data-type="tabAdd">${menu.menu_name}</a></li>

						</c:if>
					</c:forEach>
				</ul>
			</div>
			<!-- 头部右侧导航 -->
			<div class="header-right">
			<ul class="layui-nav">
			<li class="layui-nav-item"><a href="javascript:void(0)">
			          
						<i class="layui-icon" style="position: relative;">&#xe612;</i>
						${shopUserInfo.account}(${shopUserInfo.username})
						</a>
						<dl class="layui-nav-child">
					      <dd><a href="${ctx}/shop/login/goModifyUserPassword.jhtml" data-toggle="ajax" data-width="500" data-height="280" data-title="修改密码">修改密码</a></dd>
					      <c:if test="${shopUserInfo.user_type=='1'}">
					      <dd><a onclick="backIndex()">返回首页</a></dd>
					      </c:if>
					      <dd><a onclick="exit()">退出系统</a></dd>
					    </dl>
					</li>
				</ul>
				
			</div>
		</div>

		<div class="layui-body">
			<div class="layui-tab product-tab" lay-filter="mainTab"
				lay-allowclose="true">
				<c:if test="${shopUserInfo.user_type=='1'}">
				<ul class="layui-tab-title">
					<li class="shop-home layui-this">我的店面</li>
				</ul>

				<div class="layui-tab-content product-content menu-tab ">
					<div class="layui-tab-item layui-show">
						<iframe frameborder="0" id="mainContent" name="mainContent"
							src="${ctx}/shop/shopManage/goMyShop.jhtml" width="100%" height="100%"></iframe>
					</div>
				</div>
				</c:if>
				<c:if test="${shopUserInfo.user_type=='2'}">
				<ul class="layui-tab-title">
					<li class="shop-home layui-this">首页</li>
				</ul>

				<div class="layui-tab-content product-content menu-tab ">
					<div class="layui-tab-item layui-show">
						<iframe frameborder="0" id="mainContent" name="mainContent"
							src="${ctx}/shop/login/goIndex.jhtml" width="100%" height="100%"></iframe>
					</div>
				</div>
				</c:if>
			</div>

		</div>
		<!-- 底部区域 -->
		<div class="main-footer foot">
			<div class="layui-mian">
				<p class="jqadmin-copyright">
					版权所有 <span class="layui"><IMS:paramOut
							paramKey="shop_sys_title" /></span>
				</p>
			</div>
		</div>
	</div>

</body>
<script>
	layui.use('element', function() {
		var $ = layui.jquery, element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
		//触发事件
		var active = {
			tabAdd : function() {
				var href = $(this).data('href');
				var title = $(this).data('title');
				var id = $(this).data("id");
				if ($('#' + id).length == 0) {
					element.tabAdd('mainTab', {
						title : title, //用于演示
						content : '<iframe frameborder="0" src="' + href
								+ '" id="' + id + '" name="' + id
								+ '" width="100%" height="100%"></iframe>',
						id : id
					});
				}
				element.tabChange('mainTab', id);
				$(".layui-this").each(function() {
					if (!$(this).parent().hasClass("layui-tab-title")) {
						$(this).removeClass("layui-this");
					}

				});
				$('#' + id).attr("src", href);
			},
			tabChange : function() {
				var id = $(this).data("id");
				var inx = $('#' + id).parent().index();
				var href = $(this).data('href');
				if ($('#' + id).length > 0) {
					$('#' + id).attr('src', href);
					element.tabChange('mainTab', id);
				}
				$(".layui-this").each(function() {
					if (!$(this).parent().hasClass("layui-tab-title")) {
						$(this).removeClass("layui-this");
					}

				});
			}
		};
		$('.menuTab').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	//退出系统
	function exit() {
		layui.use('layer', function(){
			  var layer = layui.layer;
		
			  layer.confirm('你确认注销并安全退出系统吗？', {
			    btn: ['确定','取消'] //按钮
			  }, function(){
				  window.location.href = '${ctx}/shop/login/loginout.jhtml';
			  });
			 
		}); 
	} 
	//返回首页
	function backIndex(){
		window.location.href = '${ctx}/shop/login/goOwnerMain.jhtml';
	}
</script>

</html>