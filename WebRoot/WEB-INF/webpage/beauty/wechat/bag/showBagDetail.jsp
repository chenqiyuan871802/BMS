<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
<title>礼包详情</title>
<meta charset="utf-8">
<meta name="viewport"
	content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
<meta name="format-detection"
	content="telephone=no,email=no,date=no,address=no">

</head>
<style>
.main.package-detail .btn {
	background: #ea8b21;
	width: 18.4rem;
	height: 2.346667rem;
	margin: 0 auto 1.066667rem auto;
	border-radius: 5px;
	-webkit-border-radius: 5px;
}

.main.package-detail .btn a {
	display: block;
	font-size: 0.746667rem;
	color: #fff;
	text-align: center;
	line-height: 2.346667rem;
}
</style>
<body>
 <div class="main package-detail">
 <input type="hidden" id="serverAddress" value="<IMS:paramOut paramKey="request_url"/>">
        <div class="banner">
           <img src="${ctx}/${bag.cover_big_photo}">
        </div>
        <div class="package-detail-hd">
            <h2>${bag.bag_name }</h2>
            <div class="fr">
                <span class="price" onclick="robBag(${bag.bag_id})">￥<fmt:formatNumber
						value="${bag.bag_total_price }" type="number" /></span>
                <span class="text">（${bagProject}）</span>
            </div>
        </div>
        <div class="box project">
            <h2>包含项目</h2>
            <ul>
            <c:forEach var="project" items="${bagProjectList}" varStatus="status">
                <li>
                    <img class="fl"  src="${ctx}/${project.cover_photo}">
                    <div class="intro fl">
                        <h3>${project.project_name}</h3>
                        <p>服务时长：${project.server_time }分钟</p>
                    </div>
                    <div class="fr">
                        <span>￥<fmt:formatNumber
									value="${project.rmb_price}" type="number" /></span>
                        <span>${project.project_num }次</span>
                    </div>
                </li>
               </c:forEach>
            </ul>
        </div>
        <!-- 
        <div class="box text">
            <h2 class="tit">礼包介绍</h2>
            <p>${bag.bag_introduce}</p>
        </div>
         -->
        <div class="box text content">
           ${bag.content}
        </div>
    </div>
    <div class="h120"></div>
    <div class="package-detail-bottom">
        <span class="orang">共节省<fmt:formatNumber
									value="${remainTotalPrice}" type="number" />元</span>
        <a href="#" onclick="robBag(${bag.bag_id})">免费抢</a>
    </div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var toast = new auiToast(); 
$(function() {
	getWxConfig();

})
	function robBag(bag_id) {
		
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/bag/checkRobBag.jhtml',
			data : {
				bag_id : bag_id
			},
			dataType : 'json',
			success : function(data) {
				toast.hide();
				if (data.appcode == "1") {
					 window.location.href = '${ctx}/wechat/bag/goRobBag.jhtml?bag_id='+bag_id;
				} else {
					toast.fail({
						title : data.appmsg,
						duration : 2000
					});
				}
			},
			error : function() {
				toast.hide();
				toast.fail({
					title : "网络异常",
					duration : 2000
				});

			}
		})
		
	}
	function getWxConfig() {
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/bag/getWechatConfig.jhtml',
			data : {
				url : location.href.split('#')[0]
			},
			dataType : 'json',
			success : function(data) {
				if (data.appcode == "1") {
					wx.config({
						debug : false,
						appId : data.appid,
						timestamp : data.timestamp,
						nonceStr : data.nonceStr,
						signature : data.signature,
						jsApiList : [ 'onMenuShareTimeline', 'onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone' ]//分享到朋友圈 
					});
				} else { //微信注入权限接口
					

				}
			},
			error : function() {

				toast.fail({
					title : "获取微信签名网络异常",
					duration : 2000
				});

			}
		})
	}
	
	wx.ready(function () {
		
		 var serverAddress=$("#serverAddress").val();
			wx.onMenuShareAppMessage({
			      title: '${bag.bag_name}',
			      desc: '${bag.bag_introduce}',
			      link: serverAddress+'/wechat/bag/showBagDetail.jhtml?bag_id=${bag.bag_id}',
			      imgUrl: serverAddress+'/${bag.cover_photo}',
			      success: function (res) {
			    	  toast.success({
							title :'礼包分享成功',
							duration : 2000
						});

			      },
			      cancel: function (res) {
			       
			      },
			      fail: function (res) {
			       
			      }
			    });
	})
</script>
</html>