<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>美研社</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <style type="text/css">
        html,body{background:#fafafa;}
    </style>
</head>
<body>
    <div class="find-project">
        <ul>
          <c:forEach  var="project" items="${projectList}" varStatus="status">
         
            <li>
             <a href="#" onclick="showProjectDetail('${project.project_id}')" >
                <div class="box">
                    <img class="fl" src="${ctx}/${project.cover_photo}">
                    <div class="intro fl">
                        <h3>${project.project_name }</h3>
                        <p class="num">${project.beauty_price}</p>
                        <div class="b">
                            <span class="price fl">￥${project.rmb_price}</span>
                            <span class="time fr">${project.server_time }分钟</span>
                        </div>
                    </div>
                </div>
                </a>
                <div class="operate">
                    <span class="fl">${project.type_name }</span>
                    <a class="btn fr" href="#" onclick="selectShop('${project.project_id}')">预约</a>
                </div>
            </li>
           </c:forEach>
            
        </ul>
    </div>
    <!-- 底部菜单 -->
    <div class="h88 mt20"></div>
    <div class="bottom-nav">
        <ul>
         
            <li class="find-project cur">
            <a href="${ctx}/wechat/project/findProject.jhtml" >
                <i class="ico"></i>
                <span>找项目</span>
             </a>
                
            </li>
          
           
            <li class="find-gift">
             <a href="${ctx}/wechat/bag/goBag.jhtml" >
                <i class="ico"></i>
                <span>找礼包</span>
               </a> 
            </li>
           
            
            <li class="mine">
            <a href="${ctx}/wechat/home/goHome.jhtml" >
                <i class="ico"></i>
                <span>我的</span>
                </a>
            </li>
            
        </ul>
    </div>
</body>
<script type="text/javascript">
 function showProjectDetail(project_id){
	 window.location.href = '${ctx}/wechat/project/showProjectDetail.jhtml?project_id='+project_id;
 }
 //预约项目
 function selectShop(project_id){
	 window.location.href = '${ctx}/wechat/shop/goFindShop.jhtml?returnType=1&project_id='+project_id;
 }
</script>
</html>