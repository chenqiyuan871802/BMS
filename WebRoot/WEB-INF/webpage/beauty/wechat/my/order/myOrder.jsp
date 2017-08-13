<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
<title>订单信息</title>
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
	<section class="aui-refresh-content">
		<div class="order-list">
			<input type="hidden" id="page" value="1" />
			<div class="aui-tab" id="tab">
				<div class="aui-tab-item">
					<span>预约记录</span>
				</div>
				<div class="aui-tab-item aui-active">
					<span>礼包记录</span>
				</div>
			</div>
			<div id="order_list"></div>
		</div>
	</section>
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
<script type="text/javascript" src="${ctx}/static/wechat/js/aui-tab.js"></script>
<script type="text/javascript" src="${ctx}/static/wechat/js/aui-dialog.js"></script>
<script src="${ctx}/static/wechat/js/aui-pull-refresh.js"></script>
<script type="text/javascript">

   var index='${index}';
	var toast = new auiToast();
	var tab = new auiTab({
		element : document.getElementById("tab"),
		index :index,
		repeatClick : false
	}, function(ret) {
		 index=ret.index;
		if(index==1){
			doSearch('0');
		}else{
			doBagSearch('0')
		}
	});

	//页面初始化 
	$(function() {
		if(index=='2'){
			doBagSearch('0');
		}else{
			doSearch('0');
		}
		

	})
	function doSearch(clearStatus) {
		toast.loading({
			title : "订单数据加载中"

		})
		if (clearStatus == '0') {
			$("#page").val('1');
		}
		var page = $("#page").val();
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/order/listNurseOrder.jhtml',
			data : {
				page : page,
				rows : 5
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					createList(data.rows, clearStatus)
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
	function createList(rows, clearStatus) {
		var recordList = $("#order_list");
		var html = '';
		if (clearStatus == '1') {
			html = recordList.html();
		}
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			var order_status = row.order_status;
			var status_name
			var butStr = '';
			if (order_status == '1') {
				status_name = '已预约';
				butStr = '<div class="fr"><a class="btn cance" href="#" onclick="cancelOrder('
						+ row.order_id + ')">取消订单</a></div>'
			} else if (order_status == '2') {
				status_name = '服务中';
				butStr = '<div class="fr"><a class="btn" href="#" onclick="goPayOrder('
						+ row.order_id + ')">去支付</a></div>'
			} else if (order_status == '3') {
				status_name = '待支付';
			} else if (order_status == '4') {
				status_name = '已支付';
			} else if (order_status == '5') {
				status_name = '已完成';
			} else if (order_status == '6') {
				status_name = '已过期';
			} else {
				status_name = '已撤销';
			}
			html += '<div class="order-detail mt20" >';
			html += '<div class="top">';
			html += '<div class="state">';
			html += ' <span class="fl">' + status_name + '</span>';
			html += ' <span class="fr" onclick="showShopDetail('+row.shop_id+')">' + row.shop_name + '（' + row.short_name
					+ '）</span>';
			html += '</div>';
			html += ' <div class="box" onclick="showOrderDetail('
					+ row.order_id + ')">';
			html += ' <img class="fl" src="${ctx}/'+row.cover_photo+'">';
			html += '<div class="intro fl">';
			html += '<h3>' + row.project_name + '</h3>';
			html += '<span class="nurse-price">￥' + row.rmb_price + ' /'
					+ row.beauty_price + '个颜值</span>';
			html += '<span class="earnest">定金: ￥' + row.deposit_money
					+ '</span>';
			html += '</div>';
			html += '<div class="fr">';
			html += '<span>x' + row.buy_num + '</span>';
			html += '</div>';
			html += '</div>';
			html += '<div class="operate">';
			html += '<div class="fl">';
			html += '<span>到店时间</span>';
			html += '<span>' + row.subscribe_time + '</span>';
			html += '</div>'
			html += butStr;
			html += '</div>'
			html += '</div>'
			html += '</div>'
		}
		recordList.html(html);
	}
	var pullRefresh = new auiPullToRefresh({
		container : document.querySelector('.aui-refresh-content'),
		triggerDistance : 100
	}, function(ret) {
		if (ret.status == "success") {
			pullRefresh.cancelLoading(); //刷新成功后调用此方法隐藏
			var pageStr = $("#page").val();
			var page = parseInt(pageStr) + 1;
			$("#page").val(page);
			if(index==1){
				doSearch('1');
			}else{
				doBagSearch('1');
			}
			

		}
	})
	//撤销订单
	function cancelOrder(order_id) {
		var toast = new auiToast();
		var dialog = new auiDialog({})
		 dialog.alert({
             title:"确认提示",
             msg:'Ծ‸Ծ 真的要取消吗？',
             buttons:['必须取消 ','纯粹手滑']
         },function(ret){
            if(ret.buttonIndex==1){
            	
        		toast.loading({
        			title : "订单正在取消中"

        		})
        		$.ajax({
        			type : 'post',
        			url : '${ctx}/wechat/order/cancelOrder.jhtml',
        			data : {
        				order_id : order_id
        			},
        			dataType : 'json',
        			success : function(data) {

        				if (data) {
        					if (data.appcode == "1") {
        						toast.fail({
        							title : data.appmsg,

        							duration : 2000
        						});
        						doSearch('0');
        					} else {
        						toast.fail({
        							title : data.appmsg,
        							duration : 2000
        						});
        					}
        				} else {
        					toast.fail({
        						title : "订单取消失败，系统处理异常",
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
            
         })
	
	}
	function showShopDetail(shop_id){
	 window.location.href = '${ctx}/wechat/shop/showShopDetail.jhtml?shop_id='+shop_id;
	 }
	//跳转到支付页面
	function goPayOrder(order_id) {
		window.location.href = '${ctx}/wechat/order/goPayOrder.jhtml?order_id='
				+ order_id;
	}

	//查看订单详情
	function showOrderDetail(order_id) {
		window.location.href = '${ctx}/wechat/order/showNurseOrderDetail.jhtml?order_id='
				+ order_id;
	}
	//购买礼包记录
	function doBagSearch(clearStatus){
		toast.loading({
			title : "订单数据加载中"

		})
		if (clearStatus == '0') {
			$("#page").val('1');
		}
		var page = $("#page").val();
		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/order/listBagOrder.jhtml',
			data : {
				page : page,
				rows : 5
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					createBagList(data.rows, clearStatus)
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
	function createBagList(rows, clearStatus) {
		var recordList = $("#order_list");
		var html = '';
		if (clearStatus == '1') {
			html = recordList.html();
		}
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			var order_status = row.order_status;
			var status_name
			var butStr = '';
			if (order_status == '1') {
				status_name = '已预约';
				butStr = '<div class="fr"><a class="btn cance" href="#" onclick="cancelOrder('
						+ row.order_id + ')">取消订单</a></div>'
			} else if (order_status == '2') {
				status_name = '服务中';
				butStr = '<div class="fr"><a class="btn" href="#" onclick="goPayOrder('
						+ row.order_id + ')">去支付</a></div>'
			} else if (order_status == '3') {
				status_name = '待支付';
			} else if (order_status == '4') {
				status_name = '已支付';
			} else if (order_status == '5') {
				status_name = '已完成';
			} else if (order_status == '6') {
				status_name = '已过期';
			} else {
				status_name = '已撤销';
			}
			html += '<div class="order-detail mt20" >';
			html += '<div class="top">';
			html += '<div class="state">';
			html += ' <span class="fl">' + status_name + '</span>';
			html += ' <span class="fr"></span>';
			html += '</div>';
			html += ' <div class="box" onclick="showBagOrderDetail('
					+ row.order_id + ',\''+row.overdue_date+'\')">';
			html += ' <img class="fl" src="${ctx}/'+row.cover_photo+'">';
			html += '<div class="intro fl">';
			html += '<h3>' + row.bag_name + '</h3>';
			html += '<span class="subheading">' + row.project_content + '</span>';
			html += ' <p>￥'+row.pay_money+'</p>';
			html += '</div>';
			html += '<div class="fr">';
			html += '<span>x' + row.buy_num + '</span>';
			html += '</div>';
			html += '</div>';
			html += '<div class="operate">';
			html += '<div class="fl">';
			html += '<span>过期时间</span>';
			html += '<span>' + row.overdue_date + '</span>';
			html += '</div>'
			html += butStr;
			html += '</div>'
			html += '</div>'
			html += '</div>'
		}
		recordList.html(html);
	}
	function showBagOrderDetail(order_id,overdue_date)	{
		window.location.href = '${ctx}/wechat/order/showBagOrderDetail.jhtml?order_id='
			+ order_id+"&overdue_date="+overdue_date;
	}
	
</script>
</html>