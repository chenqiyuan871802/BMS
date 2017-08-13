<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>店铺详情</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
  
</head>
<body>
   
    <div class="main package-detail shop-detail">
      
      <div onclick="saveShopCollect()" id="collect" class="top-collect-btn <c:if test="${count==1 }">cur</c:if> "></div>
        <div class="banner">
            <img   src="${ctx}/${shopPO.shop_detail_image}">
        </div>
        <div class="shop-detail-hd">
            <h2>${shopPO.shop_name}（${shopPO.short_name}）</h2>
            <p class="location" onclick="openLocation(${shopPO.gps_x},${shopPO.gps_y})">
          <span>&nbsp;</span>
                <span>${shopPO.shop_address}</span>
            </p>
        </div>
        <!-- 
        <div class="box text">
            <h2 class="tit">商家介绍</h2>
            <p>${shopPO.shop_intro}</p>
        </div>
         -->
        <div class="shop-detail-tell">
            <span class="fl">拨打商家电话：${shopPO.shop_phone }</span>
            <a href="tel:${shopPO.shop_phone }"><img src="${ctx }/static/wechat/images/ico-tell.png"></a>
        </div>
        <div class="box project">
            <h2>包含项目</h2>
            <ul>
            <c:forEach  var="project" items="${projectList}" varStatus="status">
                <li>
                    <img class="fl" src="${ctx}/${project.cover_photo}">
                    <div class="intro fl">
                        <h3>${project.project_name}</h3>
                        <p class="facility">所用设备：${project.use_device }</p>
                        <span class="price">￥${project.rmb_price}/${project.beauty_price}颜值</span>
                        <span class="time">${project.server_time }分钟</span>
                    </div>
                    <div class="selectbox">
                    
                        <input class="aui-radio"  name="project_id" value="${project.project_id}" type="radio" name="radio">
                    </div>
                </li>
             </c:forEach>
               
            </ul>
            <div class="h88"></div>
            <div class="btn"><a href="#" onclick="subscribeProject()">立即预约</a></div>
        </div>
    </div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var toast = new auiToast();
$(function() {
	getWxConfig();
})
function subscribeProject(){
	 var project_id=$("input[name='project_id']:checked").val();
	 if(project_id==''||project_id==undefined){
		 
		 toast.fail({
				title : "请选择预约项目",
				duration : 2000
			});
		 return ;
	 }
	 
	
	 window.location.href = '${ctx}/wechat/project/goSubscribeProject.jhtml?&shop_id=${shopPO.shop_id}&project_id='+project_id;
}

function saveShopCollect(){
	 var toast = new auiToast();
		toast.loading({
           title:"数据处理中"
          
       })
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/shop/saveShopCollect.jhtml',
			data : {
				shop_id:'${shopPO.shop_id}'
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					if (data.appcode == "1") {
						toast.success({
						    title:data.appmsg,
						    duration:2000
						});
						if(data.showClass=='1'){
							$("#collect").removeClass('cur')
						}else{
							$("#collect").addClass('cur')
						}
						 
					} else {
						toast.fail({
							title : data.appmsg,
							duration : 2000
						});
					}
				} else {
					toast.fail({
						title : "店铺收藏失败，系统处理异常",
						duration : 2000
					});
				}
			},
			error : function() {
				toast.fail({
					title : "网络异常",
					duration : 2000
				});
			}
		})
		toast.hide();
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
					jsApiList : [ 'openLocation','getLocation']//分享到朋友圈 
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
//打开百度地图
function openLocation(gps_x,gps_y){
	if(gps_x==0||gps_y==0){
		toast.fail({
			title : "请在后台配置店铺的百度坐标",
			duration : 2000
		});
		return ;
	}
	var point=BdmapEncryptToMapabc(gps_y,gps_x);
	wx.openLocation({
	    latitude:point.lat, // 纬度，浮点数，范围为90 ~ -90
	    longitude: point.lng, // 经度，浮点数，范围为180 ~ -180。
	    name: '${shopPO.shop_name}（${shopPO.short_name}）', // 位置名
	    address:'${shopPO.shop_address}', // 地址详情说明
	    scale: 20 // 地图缩放级别,整形值,范围从1~28。默认为最大
	   
	});
	
}

function BdmapEncryptToMapabc(bd_lat,bd_lon)  
{  
 var point=new Object();  
 var x_pi = 3.14159265358979324 * 3000.0 / 180.0;  
    var x = new Number(bd_lon - 0.0065);  
    var y = new Number(bd_lat - 0.006);  
    var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
    var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);  
    var Mars_lon = z * Math.cos(theta);  
    var Mars_lat = z * Math.sin(theta);  
    point.lng=Mars_lon;  
    point.lat=Mars_lat;  
    return point;  
}  
</script>
</html>