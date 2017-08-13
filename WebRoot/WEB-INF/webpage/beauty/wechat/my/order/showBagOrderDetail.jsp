<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>订单详情</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <style type="text/css">
        html,body{background:#f7f7f7;}
    </style>
</head>
<body>
    <div class="order-detail">
        <div class="top">
            <div class="state"><span class="fl"><IMS:codeOut codeKey="${orderDto.order_status}" field="order_status"/></span></div>
            <div class="box">
                <img class="fl" src="${ctx }/${nurseBagPO.cover_photo}">
                <div class="intro fl">
                    <h3>${nurseBagPO.bag_name }</h3>
                    <p>￥<fmt:formatNumber value="${nurseBagPO.bag_total_price}" type="number" /></p>
                </div>
                <div class="fr">
                    <span>x${orderDto.buy_num}</span>
                </div>
            </div>
            <div class="cl"></div>
            <div class="validity">有效期至：${overdue_date}</div>
        </div>
        <div class="order-detail-describe mt20">
            <div class="hd">
                <p>实付金额：￥<fmt:formatNumber value="${orderDto.pay_money }" type="number" /></p>
                <p>支付方式：<IMS:codeOut codeKey="${orderDto.pay_way}" field="pay_way"/></p>
            </div>
             <div class="cot mt60">
                <p>订单编号：${orderDto.order_id}</p>
                <p>创建时间：<fmt:formatDate value="${orderDto.create_time}" pattern="yyyy-MM-dd HH:mm"/></p>
            </div>
        </div>
    </div>
     <!-- 底部菜单 -->
    <div class="h88 mt20"></div>
    <div class="bottom-nav">
        <ul>
         
            <li class="find-project">
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
</script>
</html>