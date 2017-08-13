<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
<title>找礼包</title>
<meta charset="utf-8">
<meta name="viewport"
	content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
<meta name="format-detection"
	content="telephone=no,email=no,date=no,address=no">
</head>
<body>
	<div class="main my-gift-bag">
		<ul id="bagList">

		</ul>
	</div>
	<!-- 底部菜单 -->
	<div class="h88 mt20"></div>
	<div class="bottom-nav">
		<ul>
			<li class="find-project"><a
				href="${ctx}/wechat/project/findProject.jhtml"> <i class="ico"></i>
					<span>找项目</span>
			</a></li>

			<li class="find-gift cur"><a
				href="${ctx}/wechat/bag/goBag.jhtml"> <i class="ico"></i> <span>找礼包</span>
			</a></li>


			<li class="mine"><a href="${ctx}/wechat/home/goHome.jhtml">
					<i class="ico"></i> <span>我的</span>
			</a></li>
		</ul>
	</div>
</body>
<script type="text/javascript">
	var toast = new auiToast();
	var timeInterval;
	//页面初始化 
	$(function() {
		doSearch(0);
		timeInterval = window.clearInterval(timeInterval)
		timeInterval = self.setInterval("doSearch(0)", 60000);
	})
	function doSearch(clearStatus) {
		toast.loading({
			title : "数据加载中"

		})

		$.ajax({
			type : 'post',
			url : '${ctx}/wechat/bag/searchBag.jhtml',
			data : {

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
		var bagList = $("#bagList");
		var html = '';
		if (clearStatus == '1') {
			html = bagList.html();
		}
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			html += '<li onclick="showBagDetail(' + row.bag_id + ')">'
			html += ' <div class="tit">' + row.bag_name + '</div>'
			html += ' <div  ></div>'
			html += ' <div class="box">'
			html += ' <div class="fl">'
			html += ' <span>只剩下'+row.remain_num+'了！ </span>'
			html += '<span>距结束: '+row.formatMinute+'</span>'
			html += '</div>'
			html += '<div class="btn"> </div>'
			html += '</div>'
			html += '<img src="${ctx}/' + row.cover_photo + '">'
			html += '</li>'
		}
		bagList.html(html);
	}
	function showBagDetail(bag_id) {
		window.location.href = '${ctx}/wechat/bag/showBagDetail.jhtml?bag_id='
				+ bag_id;
	}
</script>
</html>