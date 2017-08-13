<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>IMS信息系统综合平台</title>
<IMS:codeStore fields="sex,layout_style"/>
<link rel="shortcut icon"
	href="${ctx }/static/common/images/favicon.ico">
	<script type="text/javascript">
		$(function() {

			tabClose();
			tabCloseEven();
			
			loadWorkMain();

		})
		//加载工作平台
		function loadWorkMain(){
			addTab('工作台','${ctx}/system/login/goMainIndex.jhtml','home');
		}
		//退出系统
		function exit() {
			$.messager
					.confirm(
							'确认',
							'你确认注销并安全退出系统吗？',
							function(r) {
								if (r) {
									window.location.href = '${ctx}/system/login/loginout.jhtml';
								}
							});
		}
		function menuHandler(item){
			var title=item.text;
			var url=item.name;
			var iconCls=item.iconCls;
			addTab(title,url,iconCls);
		}
	</script>
</head>
<body class="easyui-layout">
	<div id="topDiv" region="north" border="false"
		style="height: 89px; padding: 0px; overflow: hidden; background: url(${ctx }/static/common/images/system/topbg.gif) repeat-x;">
		<div class="topleft">
			<img src="${ctx }/static/common/images/system/logo.png" title="系统首页" />

		</div>
	<ul class="nav">
	<c:forEach var="cardMenu" items="${cardMenus}" varStatus="status">
    <li><a href="#"  class="easyui-menubutton"  data-options="menu:'#menu_${ status.index + 1}'"><img src="${ctx }/static/common/images/system/${cardMenu.iconCls}.png" title="${cardMenu.menuName }" /><h2>${cardMenu.menuName }</h2></a></li>
    <div id="menu_${ status.index + 1}"  data-options="onClick:menuHandler"  style="width: 120px;">
     <c:forEach var="menu" items="${cardMenu.subMenu}">
     
       <div data-options="name:'${ctx }/${menu.url }',iconCls:'${menu.icon_name}'" >${menu.menu_name}</div>
     
     </c:forEach>
     </div>
    </c:forEach>
    </ul>
		<div class="topright">
			<ul>
				<li><a href="#" onclick="loadWorkMain();"><img
						src="${ctx }/static/common/images/system/home.png" title="工作台"
						class="homeImg" /> 工作台</a></li>
				<li><a href="#" onclick="showWindow('modifyUserWindow','${ctx}/system/goUserSet.jhtml');"><img
						src="${ctx }/static/common/images/system/user_set.png" title="个人设置"
						class="userSet" />个人设置</a></li>
				<li><a href="#" onclick="showWindow('modifyUserPasswordWindow','${ctx}/system/goModifyUserPassword.jhtml');"><img
						src="${ctx }/static/common/images/system/password.png" title="退出"
						class="userSet" />修改密码</a></li>
				<li><a href="javascript:void(0)" onclick="exit()"><img
						src="${ctx }/static/common/images/system/exit.png" title="退出"
						class="homeImg" />退出</a></li>
			</ul>
			<div class="user">
				<span>${userPO.username }</span> <i>消息</i> <b>5</b>
			</div>
				
		</div>
	</div>
	
	<div id="mainPanle" region="center" border="false"
		style="padding: 0px; overflow: hidden">
		
		<div id="mainTabs" class="easyui-tabs"
			style="padding: 0px; overflow: hidden" fit="true"></div>
			
	</div>
     
	<div id="mmRight" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabclose" iconCls="close_current">关闭当前标签</div>
		<div id="mm-tabcloseother" iconCls="close_other">关闭其他标签</div>
		<div id="mm-tabcloseall" iconCls="close_all">关闭全部标签</div>

		<div class="menu-sep"></div>
		<div id="mm-tabcloseleft" iconCls="close_left">关闭左则标签</div>
		<div id="mm-tabcloseright" iconCls="close_right">关闭右则标签</div>

		<div class="menu-sep"></div>
		<div id="mm-exit" iconCls="menu_cancel">取消</div>
	</div>
    
	<div region="south" style="height: 25px; background: #D2E0F2;">
		<div class="footer">IMS信息系统综合平台</div>
	</div>
</body>
<div id="modifyUserWindow" class="easyui-window" title="个人设置"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 850px; height: 470px; background-color: #FFFFFF"></div>
<div id="modifyUserPasswordWindow" class="easyui-window" title="修改密码"
	data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
	style="width: 520px; height: 220px; background-color: #FFFFFF">
</div>
</html>