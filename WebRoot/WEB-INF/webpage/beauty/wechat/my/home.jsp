<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>

<head>
<title>我的</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <style type="text/css">
        html,body{background:#f7f7f7;}
    </style>
</head>
<body>
    <div class="main mycenter">
        <div class="top">
            <img class="avatar" src="${ctx}/${customUserPO.photo}">
            <div class="intro">
                <h3>${customUserPO.nikename}</h3>
                <p>
                    <span  onclick="showMyBeatuy()" class="num">${customUserPO.beauty_num}</span>
                    <!-- <span class="num2">${customUserPO.bag_num}</span> -->
                </p>
            </div>
            <i  onclick="showMyDetail()" class="arrow-r"></i>
        </div>
        <div class="cot">
            <ul>
                <li onclick="goRobBag()">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list1.png"></i>
                    <span class="tit">可抢大礼包</span>
                    <i class="arrow-r"></i>
                </li>
            </ul>
            <ul class="mt20">
                <li way="${ctx}/wechat/bag/goMyBag.jhtml">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list1.png"></i>
                    <span class="tit">我的大礼包</span>
                    <i class="arrow-r"></i>
                </li>
                <li way="${ctx}/wechat/project/initMyProject.jhtml">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list2.png"></i>
                    <span class="tit">已拆礼包</span>
                    <i class="arrow-r"></i>
                </li>
                <li way="${ctx}/wechat/order/goMyOrder.jhtml?index=1">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list3.png"></i>
                    <span class="tit">我的订单</span>
                    <i class="arrow-r"></i>
                </li>
                <li way="${ctx}/wechat/home/goMyBeauty.jhtml">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list3.png"></i>
                    <span class="tit">我的颜值</span>
                    <i class="arrow-r"></i>
                </li>
               
                <li way="${ctx}/wechat/shop/listShopCollect.jhtml">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list4.png"></i>
                    <span class="tit">我的收藏</span>
                    <i class="arrow-r"></i>
                </li>
                <!-- 
                <li way="${ctx}/wechat/login/goAppLogin.jhtml">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list5.png"></i>
                    <span class="tit">关于我们</span>
                    <i class="arrow-r"></i>
                </li>
                 -->
                <li way="${ctx}/wechat/home/goFeedback.jhtml">
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-mycenter-list5.png"></i>
                    <span class="tit">反馈意见</span>
                    <i class="arrow-r"></i>
                </li>
                
            </ul>
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
           
            
            <li class="mine  cur">
            <a href="${ctx}/wechat/home/goHome.jhtml" >
                <i class="ico"></i>
                <span>我的</span>
                </a>
            </li>
        </ul>
    </div>
</body>

<script type="text/javascript">
$(function(){
	$(".mt20").on("click","li",function(){      //只需要找到你点击的是哪个ul里面的就行
		var url = $(this).attr("way");
	  
		window.location.href =url;
	 });
})
/**
 * 个人单击详情
 */
function showMyDetail(){
	window.location.href ='${ctx}/wechat/home/goMyDetail.jhtml';
}
/**
 * 查看我的颜值
 */
function showMyBeatuy(){
	window.location.href ='${ctx}/wechat/home/goMyBeauty.jhtml';
}
function goRobBag(){
	window.location.href ='${ctx}/wechat/bag/goBag.jhtml';
}
</script>
</html>