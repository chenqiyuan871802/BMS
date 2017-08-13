<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/shopTaglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>IMS信息系统综合平台</title>

<style type="text/css">
.tabcount {
	width: 94%;
	margin: 0 auto;
	height: auto;
	margin-top: 20px;
	border-radius: 5px;
	text-align:center;
}


.tabcount a {
	display: inline-block;
	width: 30%;
	height: 150px;
	color: #fff;
	text-align: center;
	margin: 0px 10px;
	overflow:hidden;
}

.tabcount a span {
	display: block;
	font-size:20px;
	font-weight:bold;
	
}

.spancount {
   padding:20px 0px;
	font-size: 40px !important;
}
.bicon1{
  background-color:#44b7ee;
}
.bicon2{
  background-color:#efa76b;
}
.bicon3{
  background-color:#a2d74f;
}
.bicon4{
  background-color:#efa76b;
}
.shopTitle{padding:30px 30px 30px 150px; overflow:hidden;}
.shopInfo{height:32px; line-height:32px; padding-bottom:8px;}
.shopInfo span{float:left;}
.shopInfo b{padding-left:8px;font-size:20px;}
.shopInfo a{padding-left:15px;color:#EB5409;}
.shopInfo a:hover{color:#000;}
.shopInfo i{font-style:normal; padding-left:8px;}
</style>
</head>
<body>
<div class="shopTitle">
    <div class="shopInfo">
    <span><img src="${ctx }/static/shop/images/ico01.png" alt="收银台" /></span>
    <b>收银台</b>
    </div>
    <div class="tabcount">
		<a href="javascript:void(0)" class="bicon1"> <span class="spancount">${today_total_money }元</span><span>今日营业总额</span>
		</a> <a href="javascript:void(0)" class="bicon2" > <span class="spancount">${today_total_order }单</span><span>今日订单总数</span>

		</a>
	</div>
</body>
</html>