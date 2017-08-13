<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>${project.project_name }</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
   
    
</head>
<body>
     <input type="hidden" id="serverAddress" value="<IMS:paramOut paramKey="request_url"/>">
    <div class="main project-detail">
        <div class="banner">
            <img src="${ctx}/${project.cover_big_photo}">
        </div>
        <div class="project-detail-hd">
            <span class="price">￥${project.rmb_price}</span>
            <h3>${project.project_name }</h3>
            <span class="time">服务时长：${project.server_time }分钟</span>
            <span class="num">${project.beauty_price}</span>
        </div>
        <div class="project-detail-cot">
            <div class="content">
              ${project.content}
            </div>
            <div class="h88"></div>
            
           
            <div class="btn">
            <a class="more" href="#" onclick="findProject()">更多项目</a>
            <a href="#" class="subscribe"  onclick="selectShop(${project.project_id})">马上预约</a>
            </div>
        </div>
    </div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var toast = new auiToast(); 
$(function() {
	getWxConfig();

})
//预约项目
function selectShop(project_id){
	 window.location.href = '${ctx}/wechat/shop/goFindShop.jhtml?returnType=1&project_id=${project.project_id}';
}
//预约项目
function findProject(){
	 window.location.href = '${ctx}/wechat/project/findProject.jhtml';
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
		      title: '${project.project_name}',
		      desc: '我向你分享了一个美研社超值护理项目， 快去看看吧~',
		      link: serverAddress+'/wechat/project/showProjectDetail.jhtml?project_id=${project.project_id}',
		      imgUrl: serverAddress+'/${project.cover_photo}',
		      success: function (res) {
		    	  toast.success({
						title :'项目分享成功',
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