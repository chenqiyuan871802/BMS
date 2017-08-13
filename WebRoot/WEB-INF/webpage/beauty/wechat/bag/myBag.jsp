<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
<title>我的大礼包</title>
<meta charset="utf-8">
<meta name="viewport"
	content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
<meta name="format-detection"
	content="telephone=no,email=no,date=no,address=no">
<style type="text/css">
html, body {
	background: #f7f7f7;
}
</style>
</head>
<body>
	<div class="main my-gift-bag" id="box">
	
	<input type="hidden" id="serverAddress" value="<IMS:paramOut paramKey="request_url"/>">
		<ul id="bagList">
		</ul>
	</div>
	<div class="null-page" id="noBag" style="display: none">
		<img src="${ctx}/static/wechat/images/none-gift.png"> <span>您还没有任何礼包哦~</span>
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
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	var toast = new auiToast();
	$(function() {
		doSearch();
		getWxConfig();
	})
	function doSearch() {
		toast.loading({
			title : "数据加载中"

		})
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/bag/listMyBag.jhtml',
			data : {

			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					createList(data.rows)
				} else {
					toast.fail({
						title : "查询失败",
						duration : 2000
					});
				}
				toast.hide();
			},
			error : function() {
				toast.fail({
					title : "网络异常",
					duration : 2000
				});
				toast.hide();
			}
		})
	}
	//创建列表数据
	function createList(rows) {
		if (rows.length > 0) {
			var bagList = $("#bagList");
			var html = '';
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				var receive_status = row.receive_status;
			    var record_type=row.record_type;
			  
				var share_num = row.share_num;
				var receiveBth = '';
				var numStr='';
				var shareBtn='';
				 if(record_type=='2'){
					 numStr='领取数量：' + row.buy_num 
				 }else{
				   if (receive_status == '0' && share_num > 0) { //
							receiveBth = '<a href="#" onclick="receiveBag('
									+ row.record_id + ')">领取礼包</a>'
					}
				   if(share_num>0){
					 shareBtn='class="share-btn" onclick="shareBag(\''+row.bag_name+'\','+row.record_id+',\''+row.cover_photo+'\')"';
				   }
				   numStr='剩余数量：' + row.buy_num+"-"+row.share_num 
				 }
			
				html += ' <li> <div class="tit">' + row.bag_name + '</div>'
				html += ' <div '+shareBtn+' ></div>'
				html += ' <div class="box">'
				html += ' <div class="fl">'
				html += ' <span>'+ numStr+'</span>'
				html += '<span>有效期至：' + row.overdue_date + '</span>'
				html += '</div>'
				html += '<div class="btn">' + receiveBth + '</div>'
				html += '</div>'
				html += '<img onclick="showBagDetail(' + row.bag_id
						+ ')" src="${ctx}/' + row.cover_photo + '">'
				html += '</li>'
			}
			bagList.html(html);
		} else {
			$('#box').hide();
			$('#noBag').show();
		}

	}
	//领取礼包
	function receiveBag(record_id) {
		toast.loading({
			title : "数据处理中"

		})
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/bag/receiveBag.jhtml',
			data : {
				record_id : record_id
			},
			dataType : 'json',
			success : function(data) {
				toast.hide();
				if (data.appcode == "1") {
					toast.success({
						title : data.appmsg,
						duration : 4000
					});
                  
					
				} else {
					toast.fail({
						title : data.appmsg,
						duration : 4000
					});
				}
				window.location.href = '${ctx}/wechat/project/initMyProject.jhtml';
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

	function showBagDetail(bag_id) {
		//window.location.href = '${ctx}/wechat/bag/showBagDetail.jhtml?bag_id='+ bag_id;
		window.location.href = '${ctx}/wechat/project/initMyProject.jhtml';
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
	function shareBag(bag_name,record_id,image){
		 var serverAddress=$("#serverAddress").val();
		 wx.onMenuShareAppMessage({
		      title: bag_name,
		      desc: '我向你分享了一个美研社超值美白大礼包， 快去领取吧~',
		      link: serverAddress+'/wechat/shareBag/initShareBag.jhtml?record_id='+record_id,
		      imgUrl: serverAddress+'/'+image,
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
		 toast.success({
				title :'请点击右上角"发送给朋友"按钮进行礼包分享',
				duration : 2000
			});
	}
	
</script>
</html>