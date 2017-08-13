<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>找店铺</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
</head>
<body>
<section class="aui-refresh-content">
    <div class="search-box">
    <input type="hidden" id="page" value="1"/>
        <div class="shop-list search-list">
            <ul id="shopList">
              
            </ul>
        </div>
    </div>
   </section>
</body>
<script src="${ctx}/static/wechat/js/api.js"></script>
<script src="${ctx}/static/wechat/js/aui-pull-refresh.js"></script>
<script type="text/javascript">
var toast = new auiToast();
//页面初始化 
$(function() {
	doSearch(0);

})
function doSearch(clearStatus) {
	toast.loading({
        title:"加载中"
       
    })
	if(clearStatus=='0'){
		$("#page").val('1');
	}
   var page=$("#page").val();
   
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/shop/searchShop.jhtml',
		data : {
			page:page,
			rows:10
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				createList(data.rows,clearStatus)
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
function createList(rows,clearStatus){
   var shopList=$("#shopList");
   var html='';
   if(clearStatus=='1'){
	   html=shopList.html();
   }
   for(var i=0;i<rows.length;i++){
	  var row=rows[i];
	   html+=' <li> <a href="#" onclick="subscribeProject('+row.shop_id+')"><img class="fl" src="${ctx}/'+row.shop_image+'">'
	  html+='<div class="intro fl">'
	  html+='<h3>'+row.shop_name+'('+row.short_name+')</h3>'
	  html+='<p class="location">'+row.shop_address+'</p>'
	  html+='</div>'
	  html+='<div class="fr"><span></span></div>'
	  html+='<a></li>'
   }
   shopList.html(html);
}
var pullRefresh = new auiPullToRefresh({
	container: document.querySelector('.aui-refresh-content'),
	triggerDistance: 100
},function(ret){
	if(ret.status=="success"){
		pullRefresh.cancelLoading(); //刷新成功后调用此方法隐藏
	   var pageStr=$("#page").val();  
	    var page=parseInt(pageStr)+1;
	    $("#page").val(page);
	   doSearch('1');
	 
	}
})

function subscribeProject(shop_id){
	 window.location.href = '${ctx}/wechat/project/goSubscribeProject.jhtml?&project_id=${project_id}&shop_id='+shop_id;
}
</script>
</html>