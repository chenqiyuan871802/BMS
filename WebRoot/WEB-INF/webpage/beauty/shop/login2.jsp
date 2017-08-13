<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/ims.tld" prefix="IMS"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><IMS:paramOut paramKey="shop_sys_title"/></title>

<link rel="shortcut icon"
			href="${ctx }/static/common/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${ctx }/static/shop/css/style.css">
<script type="text/javascript" src="${ctx }/static/common/js/jquery.js" ></script>

<link rel="stylesheet" type="text/css" href="${ctx}/static/weblib/easyui/themes/metro-orange/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/weblib/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/weblib/easyui/themes/color.css">
<script type="text/javascript" src="${ctx}/static/weblib/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/weblib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/weblib/easyui/locale/easyui-lang-zh_CN.js"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
	function login() {
		
	    var account=$("#account").val();
	    var password=$("#password").val();
	    var worksn=localStorage.getItem("IMSWORKSN");
		if(account==""){
			
			 $.messager.alert('警告信息','账号不能为空，请输入', 'warning');
			 return ;
		}
		if(password==""){
			
			 $.messager.alert('警告信息','密码不能为空，请输入', 'warning');
			 return ;
		}
		 $.ajax({
 						type : 'post',
 						url  :'${ctx}/shop/login/doLogin.jhtml',
 						data : {
						  'account' :account,
						  'password':password,
						  'worksn':worksn
						  
						},
 						dataType : 'json',
 						success : function(data) {
 							if (data) {
 								if (data.appcode == "1") {
 								   if(data.user_type=='1'){
 									  window.location.href = '${ctx}/shop/login/goOwnerMain.jhtml';
 								   }else{
 									  window.location.href = '${ctx}/shop/login/goMain.jhtml';
 								   }
 								 
 								} else if(data.appcode=="0"){
					                 $.messager.alert('警告信息', data.appmsg, 'warning');
					             }else {
 									$.messager.alert('错误信息',data.appmsg, 'error');
 								}
 							} else {
 								$.messager.alert('错误信息', '操作失败',
 										'error');
 							}
 						},
 						error : function() {
 							$.messager.alert('错误信息', '操作失败，网络连接超时',
 									'error');
 						}
 					})
	}
   function enterSumbit(){
      if(event.keyCode == 13){
         login();
       }
   
  }
   



</script> 

</head>

<body style="background-color:#df7611; background-image:url(${ctx }/static/shop/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <ul>
    <li><input name="account" id="account" type="text" class="loginuser" /></li>
    <li><input name="password" id="password" type="password" class="loginpwd"   onkeydown="javascript:enterSumbit();"/></li>
    <li><input name="" type="button" class="loginbtn" value="登录"  onclick="login()" /></li>
    </ul>
    
    </div>
    
    </div>
    
    
</body>

</html>
