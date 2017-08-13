<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/ims.tld" prefix="IMS"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set> 
<!-- EasyUI 需要引入的CSS文件和JS文件 -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/weblib/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/weblib/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/weblib/easyui/themes/color.css">
<script type="text/javascript" src="${ctx}/static/weblib/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/weblib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/weblib/easyui/locale/easyui-lang-zh_CN.js"></script>
<!--系统定义的CSS和JS文件  -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/fonts/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/css/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/css/common.css">
<script type="text/javascript" src="${ctx}/static/common/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/common/js/easyuiValidate.js"></script>
<!-- 导入highcharts图表 -->
<script src="${ctx}/static/weblib/highcharts/highcharts.src.js" type="text/javascript"></script>
<script src="${ctx}/static/weblib/highcharts/modules/exporting.src.js" type="text/javascript"></script>
<script src="${ctx}/static/weblib/highcharts/themes/grid.js" type="text/javascript"></script>
<script type="text/javascript">
window.UMEDITOR_HOME_URL = "${ctx}/static/weblib/umeditor/";
$.ajaxSetup({
	error: function (xhr, status, e) { $.messager.alert('错误信息', '操作失败'+e, 'error');  }
});
</script>
  