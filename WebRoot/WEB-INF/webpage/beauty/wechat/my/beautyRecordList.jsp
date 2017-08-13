<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>交易明细</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <style type="text/css">
        html,body{background:#f7f7f7;}
    </style>
</head>
<body>
<section class="aui-refresh-content">
    <div class="main transaction-detail">
     <input type="hidden" id="page" value="1"/>
        <div class="transaction-detail-list">
            <ul id="recordList">
            </ul>
        </div>
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
		url : '${ctx}/wechat/home/searchBeautyRecord.jhtml',
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
   var recordList=$("#recordList");
   var html='';
   if(clearStatus=='1'){
	   html=recordList.html();
   }
   for(var i=0;i<rows.length;i++){
	  var row=rows[i];
	  var record_type=row.record_type;
	  var total_beauty_num=row.total_beauty_num
	  var title='';
	  var cls='plus'
	  if(record_type=='1'){
		  title='购买颜值';
		  total_beauty_num='+'+total_beauty_num;
	  }else if(record_type=='2'){
		  title='兑换颜值'
		  total_beauty_num='+'+total_beauty_num;
	  }else{
		  title=row.shop_name+'('+row.short_name+')'
		  total_beauty_num='-'+total_beauty_num;
		  cls='subtract'
	  }
	  
	  
	  html+=' <li>';
	  html+='<div class="fl">'
	  html+='<span class="tit">'+title+'</span>'
	  html+='<span class="time">'+row.pay_time+'</span>'
	  html+='</div>'
	  html+='<div class="fr">'
	  html+='<span class="'+cls+'">'+total_beauty_num+'</span>'
      html+='</div>'
	  html+='</li>'
   }
   recordList.html(html);
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
</script>
</html>