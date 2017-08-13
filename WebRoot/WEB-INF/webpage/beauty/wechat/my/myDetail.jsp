<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>个人资料</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">

		<script src="${ctx}/static/newDate/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>
		<script src="${ctx}/static/newDate/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>

		<link href="${ctx}/static/newDate/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/static/newDate/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/newDate/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>
		<script src="${ctx}/static/newDate/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>

		<!-- S 可根据自己喜好引入样式风格文件 -->
		<script src="${ctx}/static/newDate/js/mobiscroll.android-ics-2.5.2.js" type="text/javascript"></script>
		<link href="${ctx}/static/newDate/css/mobiscroll.android-ics-2.5.2.css" rel="stylesheet" type="text/css" /
  
</head>
  <style>
         body{
            background:url(${ctx}/static/wechat/images/my-coin-bg.jpg) 0 2.346667rem no-repeat #f5b24f;background-size:cover;
        }
    </style>
<body>
    <div class="my-base-intro">
        <div class="my-avatar" onclick="uploadImg(${customUserPO.custom_user_id })">
            <img id="userImg" src="${ctx}/${customUserPO.photo}">
            <i class="arrow-t"></i>
            <span>${customUserPO.nikename}</span>
        </div>
        <div class="my-base-intro-cot">

            <ul>
                <li>
                    <label>手机号</label>
                     <input type="text" value="${customUserPO.mobile}" readonly unselectable="on">
                   <input type="hidden" id="born_date_str"  />
                </li>
                <li>
                    <label>昵称</label>
                    <input type="text" id="nikename" value="${customUserPO.nikename}">
                </li>
                <li>
                    <label>生日</label>
                    <input type="text" id="born_date" value="<fmt:formatDate value="${customUserPO.born_date}" pattern="yyyy-MM-dd"/>" onchange="addAstro()">
                    
                </li>
                <li>
                    <label>性别</label>
                     <select id="sex">
                        <option value="2" <c:if test="${customUserPO.sex=='2'}"> selected</c:if>>女</option>
                        <option value="1"  <c:if test="${customUserPO.sex=='1'}"> selected</c:if>>男</option>
                        <option value="0" <c:if test="${customUserPO.sex=='0'}"> selected</c:if>>保密</option>
                    </select>
                </li>
            </ul>
             
            <div class="btn">
                <a href="#" onclick="updateMyInfo();">修改完成</a>
            </div>
        </div>
    </div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
var toast = new auiToast();
$(function(){
	var currYear = (new Date()).getFullYear();
	var opt = {};
	opt.date = {
		preset: 'date'
	};
	//opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };
	opt.datetime = {
		preset: 'datetime'
	};
	opt.time = {
		preset: 'time'
	};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		lang: 'zh'
		
	};   
	$("#born_date").scroller('destroy').scroller($.extend(opt['date'], opt['default']));
	var optDateTime = $.extend(opt['datetime'], opt['default']);
   
	 addAstro();
	 
	 getWxConfig();
})
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
						jsApiList : [ 'chooseImage','previewImage','uploadImage','downloadImage', ]//分享到朋友圈 
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

function uploadImg(custom_user_id){
	wx.chooseImage({
	    count: 1, // 默认9
	    sizeType: [ 'original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	    success: function (res) { //选择成功以后上传到微信服务器
	    	var localIds=res.localIds;
	    	wx.uploadImage({    //调用上传接口
	    	    localId:localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
	    	    isShowProgressTips: 1, // 默认为1，显示进度提示
	    	    success: function (res) {
	    	    	 toast.loading({
	    	             title:"上传头像中"
	    	            
	    	         })
	    	        $.ajax({
	    				type : 'post',
	    				url : '${ctx}/wechat/home/updateMyImg.jhtml',
	    				data : {
	    					media_id:res.serverId,
	    					custom_user_id:custom_user_id
	    				},
	    				dataType : 'json',
	    				success : function(data) {
	    					toast.hide();
	    					if (data.appcode == "1") {
	    						
	    						toast.success({
		    						title : data.appmsg,
		    						duration : 2000
		    					});
	    						$("#userImg").attr("src", '${ctx}/'+data.savePath);  
	    					} else { //微信注入权限接口
	    						
	    						toast.fail({
		    						title : data.appmsg,
		    						duration : 2000
		    					});
	    					}
	    				},
	    				error : function() {

	    					toast.fail({
	    						title : "上传头像失败",
	    						duration : 2000
	    					});

	    				}
	    			})
	    			toast.hide();
	    	    },
	    	    fail:function(){
	    	    	toast.fail({
						title : "上传头像失败",
						duration : 2000
					});
	    	    }
	    	});
	      
	    }
	});
}
function updateMyInfo(){
	
	var nikename=$("#nikename").val();
	var born_date=$("#born_date_str").val();
	
	var sex=$('#sex option:selected') .val();//选中的值
	toast.loading({
        title:"数据处理中"
    })
  
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/home/updateMyInfo.jhtml',
		data : {
			custom_user_id:'${customUserPO.custom_user_id}',
			nikename:nikename,
			born_date:born_date,
			sex:sex
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				if (data.appcode == "1") {
					toast.success({
					    title:data.appmsg,
					    duration:2000
					});
					window.location.href ='${ctx}/wechat/home/goHome.jhtml';
				} else {
					toast.fail({
						title : data.appmsg,
						duration : 2000
					});
				}
			} else {
				toast.fail({
					title : "个人资料修改失败，系统处理异常",
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
function getAstro(month,day){    
    var s="魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
    var arr=[20,19,21,21,21,22,23,23,23,23,22,22];
    return s.substr(month*2-(day<arr[month-1]?2:0),2);
}
//添加星座
function addAstro(){
	var born_date=$("#born_date").val();
	
	$("#born_date_str").val(born_date);
	if(born_date!=''){
		var array=born_date.split("-"); //字符分割 
		var month=parseInt(array[1]);
		var day=parseInt(array[2]);
		var astro=getAstro(month, day)+'座';
		$("#born_date").val(born_date+"  "+astro);
	}
	
	
}
</script>
</html>