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
					
							<li class="layui-nav-item"><a href="javascript:void(0)"
								onclick="return false;" class="menuTab">首页</a></li>

					
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<div class="layui-tab product-tab" lay-filter="mainTab"
				lay-allowclose="true">
				<div class="layui-tab-content product-content menu-tab ">
					<div class="layui-tab-item layui-show">
						<iframe frameborder="0" id="mainContent" name="mainContent"
							src="${ctx}/shop/login/goShopOwnerIndex.jhtml" width="100%" height="100%"></iframe>
					</div>
				</div>
			</div>

		</div>
		<!-- 底部区域 -->
		<div class="main-footer foot1">
			<div class="layui-mian">
				<p class="jqadmin-copyright">
					版权所有 <span class="layui"><IMS:paramOut
							paramKey="shop_sys_title" /></span>
				</p>
			</div>
		</div>
	</div>

</body>
</html>