<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>已拆礼包</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    
</head>
<body>
   
    <div class="my-project-list">
        <div class="aui-tab" id="tab">
            <div class="aui-tab-item aui-active"><span>未使用</span></div>
            <div class="aui-tab-item"><span>已使用</span></div>
            <div class="aui-tab-item"><span>已过期</span></div>
        </div>
    
        <div class="box" id="box">
        <input type="hidden" id="page" value="1" />
            <ul id="project_list">
            </ul>
        </div>
  
         <div class="null-page" id="noProject" style="display:none">
            <img src="${ctx}/static/wechat/images/none-gift.png">
            <span>您还没有项目哦~</span>
        </div>
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
<script type="text/javascript" src="${ctx}/static/wechat/js/aui-tab.js" ></script>
<script src="${ctx}/static/wechat/js/aui-pull-refresh.js"></script>
<script type="text/javascript">
var toast = new auiToast();
var index=1;
var tab = new auiTab({
	element : document.getElementById("tab"),
	index : 1,
	repeatClick : false
}, function(ret) {
	 index=ret.index;
	 
	 doSearch(index,'0');
});
//页面初始化 
$(function() {

	doSearch(1,'0');
})
function doSearch(index,clearStatus) {
	toast.loading({
		title : "数据加载中"

	})
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/project/listMyProject.jhtml',
		data : {
			
			project_status:index
			
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				if(index==1){
					createOneList(data.rows, clearStatus)
				}else if(index==2){
					createTwoList(data.rows, clearStatus)
				}else{
					createThreeList(data.rows, clearStatus)
				}
				
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
function createOneList(rows, clearStatus) {
	var recordList = $("#project_list");
	
	 var length=rows.length;
	 if(length>0){
		 $("#box").show();
		 $("#noProject").hide();
		 var html = '';
			if (clearStatus == '1') {
				html = recordList.html();
			}
		 for (var i = 0; i < length; i++) {
				var row=rows[i];
				html += '<li onclick="showProjectDetail('+row.project_id+')">';
				html += '<img class="fl" src="${ctx}/'+row.cover_photo+'">';
				html += '<div class="intro fl">';
				html += '  <h3>'+row.project_name+'</h3>';
				html += '  <p>有效期至：'+row.overdue_date+'</p>';
				html += '</div>';
				html += ' <div class="state">';
				html += '<span>未使用</span>';
				html += ' </div>';
			
			}
		 recordList.html(html);
	 }else{
		 if(clearStatus=='0'){
			 $("#box").hide();
			 $("#noProject").show();
		 }
		
	 }
	
	
}
function createTwoList(rows, clearStatus) {
	var recordList = $("#project_list");
	
	 var length=rows.length;
	 if(length>0){
		 $("#box").show();
		 $("#noProject").hide();
		 var html = '';
			if (clearStatus == '1') {
				html = recordList.html();
			}
		 for (var i = 0; i < length; i++) {
				var row=rows[i];
				html += '<li onclick="showProjectDetail('+row.project_id+')">';
				html += '<img class="fl" src="${ctx}/'+row.cover_photo+'">';
				html += '<div class="intro fl">';
				html += '  <h3>'+row.project_name+'</h3>';
				html += '  <p>有效期至：'+row.overdue_date+'</p>';
				html += '</div>';
				html += ' <div class="state">';
				html += '<span>已使用</span>';
				html += ' </div>';
			
			}
		 recordList.html(html);
	 }else{
		 if(clearStatus=='0'){
			 $("#box").hide();
			 $("#noProject").show();
		 }
	 }
	
	recordList.html(html);
}
//创建列表数据
function createThreeList(rows, clearStatus) {
	var recordList = $("#project_list");
	
	 var length=rows.length;
	 if(length>0){
		 $("#box").show();
		 $("#noProject").hide();
		 var html = '';
			if (clearStatus == '1') {
				html = recordList.html();
			}
		 for (var i = 0; i < length; i++) {
				var row=rows[i];
				html += '<li onclick="showProjectDetail('+row.project_id+')">';
				html += '<img class="fl" src="${ctx}/'+row.cover_photo+'">';
				html += '<div class="intro fl">';
				html += '  <h3>'+row.project_name+'</h3>';
				html += '  <p>有效期至：'+row.overdue_date+'</p>';
				html += '</div>';
				html += ' <div class="state">';
				html += '<span>已过期</span>';
				html += ' </div>';
			
			}
		 recordList.html(html);
	 }else{
		 if(clearStatus=='0'){
			 $("#box").hide();
			 $("#noProject").show();
		 }
	 }
}

function showProjectDetail(project_id){
	 window.location.href = '${ctx}/wechat/project/showProjectDetail.jhtml?project_id='+project_id;
}
</script>
</html>