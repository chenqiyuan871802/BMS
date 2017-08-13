<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>我的收藏</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
   
</head>
<body>

    <c:if test="${show_data=='0' }">
    <div class="null-page">
        <img src="${ctx}/static/wechat/images/none-collect.png">
        <span>您还没有收藏哦~</span>
    </div>
    </c:if>
    <c:if test="${show_data=='1' }">
    <div class="search-box">
        <div class="shop-list search-list">
            <ul>
            <c:forEach  var="shop" items="${shopList}" varStatus="status">
                <li onclick="showShopDetail(${shop.shop_id})">
                    <img class="fl" src="${ctx}/${shop.shop_image}">
                    <div class="intro fl">
                        <h3>${shop.shop_name }(${shop.short_name })</h3>
                        <p class="location">${shop.shop_address }</p>
                    </div>
                </li>
              </c:forEach>
            </ul>
        </div>
    </div>
    
    </c:if>
    
</body>
<script type="text/javascript">
function showShopDetail(shop_id){
	   window.location.href = '${ctx}/wechat/shop/showShopDetail.jhtml?shop_id='+shop_id;
}
</script>
</html>