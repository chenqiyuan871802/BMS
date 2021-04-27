<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>预约</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <style type="text/css">
        html,body{background:#f7f7f7;}
    </style>
      <script type="text/javascript">
        $(function(){
        //ajax获取日历json数据
        var date = new Date();
        var nowDays = date.getDate();
        var nowMonth = date.getMonth() + 1;
        var days = new Array("day0","day1","day2","day3","day4","day5","day6");
        if(nowMonth == 2){
            for(i=0;i<7;i++){
                if(days[i-1] == 28){
                   break;
                }
                else{
                    days[i]= nowDays + i;
                }
            }
        }
        else if (nowMonth == 1 || nowMonth == 3 || nowMonth == 5 || nowMonth == 7 || nowMonth == 8 || nowMonth == 10 || nowMonth == 12){
            for(i=0;i<7;i++){
                if(days[i-1] == 31){
                   break;
                }
                else{
                    days[i]= nowDays + i;
                }
            }
        }
        else{
            for(i=0;i<7;i++){
                if(days[i-1] == 30){
                   break;
                }
                else{
                    days[i]= nowDays + i;
                }
            }
        } 
        var signList=[
            {"signDay":days[0]},
            {"signDay":days[1]},
            {"signDay":days[2]},
            {"signDay":days[3]},
            {"signDay":days[4]},
            {"signDay":days[5]},
            {"signDay":days[6]}
        ];
        calUtil.init(signList);
        $('#calendar th.on').eq(0).addClass('today')
        $('#calendar th.on').eq(1).addClass('tomorrow')
        $('#calendar th.on').eq(2).addClass('bermorgen')
        });
    </script>
</head>
<body>
    <div class="main appointment-box">
        <div class="box">
        
            <div class="input-text">
                <label>预约人：</label>
              <input type="text" value="${customUserPO.mobile }" readonly unselectable="on">  
            </div>
            <div class="cl"></div>
           
             <div class="input-text" onclick="selectShop('${project.project_id}')">
                <label>预约商家：</label>
            <input type="text" value="${shopPO.shop_name }(${shopPO.short_name })" readonly unselectable="on">     
            </div>
            <div class="cl"></div>

           
            <div class="input-text service-time" onClick="showPopuCalendar()">
                <label>服务时间：</label>
                <div class="service-time-cot">
                    <span class="service_date">请选择服务时间</span>
                    <span class="service_time"></span>
                    <span class="service_week"></span>
                </div>
                <input id="shop_id" type="hidden" value="${shopPO.shop_id}">
                <input id="service_date" type="hidden" value="">
                <input id="service_time" type="hidden" value="">
                <input id="service_week" type="hidden" value="">
                <i class="ico-calendar"></i>
            </div>
            <div class="cl"></div>
          
        </div>
        <div class="box mt20">
            <div class="input-text service-items">
                <label>所选项目</label>
                <div class="cot">
                    <img class="fl" src="${ctx}/${project.cover_photo}">
                    <div class="intro fl">
                        <h3>${project.project_name }</h3>
                        <p>服务时长：${project.server_time }分钟</p>
                    </div>
                    <div class="fr">
                        <span>x1</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="appointment-bottom">
        <div class="tips-text">
            <!-- <p>*预约需交定金，到店消费后，定金原路退回。如当天没来，则预约过期，扣除定金</p>
            <p>*每次只预约一个项目，如需多个，请致电与店内人员沟通。</p> -->
        </div>
        <div class="bottom-bar">
            <span class="fl">&nbsp;&nbsp;<i>&nbsp;&nbsp;</i></span>
            <a class="btn fr"  style="padding:0"  href="#" onclick="saveSubscribeOrder()">立即预约</a>
        </div>
    </div>
   
 <!-- 选择日期 -->
    <div class="popu-calendar">
        <div class="hd">
            <span>请选择服务时间</span>
            <div onClick="closeCalendar()" class="close"></div>
        </div>
        <div id="calendar"></div>
        <div class="tips">*最远可以预约未来7天内</div>
    </div>
    <!-- 选择时间段 -->
    <div class="popu-time">
        <div class="hd">
            <div onclick="backPopuCalendar()" class="back"></div>
            <p><span><span class="popup-hd-date"></span><span class="popup-hd-week"></span></span></p>
            <div onclick="closeTime()" class="close"></div>
        </div>
        <div class="cot">
            <ul id="showTime">
                
            </ul>
        </div>
        <div class="cl"></div>
        <div class="btn"><a href="javascript:comfrinSelect()">确定选择</a></div>
    </div>
    <!-- 黑色半透明遮罩层 -->
    <div class="mask"></div>
</body>
<script type="text/javascript" src="${ctx}/static/wechat/js/calendar.js?v1.0222"></script>
<script type="text/javascript">  
/*显示选择日期*/
function showPopuCalendar(){
    $('.popu-calendar,.mask').show();
}
/*关闭日期*/
function closeCalendar(){
    $('.popu-calendar,.mask').hide();
}
/*返回选择日期*/
function backPopuCalendar(){
    $('.popu-time').hide();
}
/*关闭选择时间日期*/
function closeTime(){
    $('.popu-calendar,.popu-time,.mask').hide();
}
var selectTime ={
     onTime:'',
}

/*确定选择*/
function comfrinSelect(){
    $('.popu-calendar,.popu-time,.mask').hide();
    $('#service_time').html(selectTime.onTime)
    $('.service_date').html($('#service_date').val())
    $('.service_week').html($('#service_week').val())
    $('.service_time').html(selectTime.onTime)
}
    function saveSubscribeOrder(){
	 var toast = new auiToast(); 
	   var service_date=$("#service_date").val();
	   var service_time=$(".service_time").html();
	   var subscribe_time=service_date+" "+service_time
	   if(service_time==""){
		   toast.fail({
				title : "请输入服务时间",
				duration : 2000
			});
			return;
		}
    	
    	toast.loading({
            title:"数据处理中"
           
        })
        $.ajax({
			type : 'post',
			url : '${ctx}/wechat/order/saveAppSubscribeOrder.jhtml',
			data : {
				project_id:'${project.project_id}',
				shop_id:'${shopPO.shop_id}',
				subscribe_time:subscribe_time+':00'
			},
			dataType : 'json',
			success : function(data) {
				
				if (data) {
					if (data.appcode == "1") {
						toast.success({
						    title:'预约成功',
						    duration:2000
						});
						//var token_id=data.token_id
					    window.location.href="${ctx}/wechat/order/goMyOrder.jhtml?index=1"
						//window.location.href = 'https://pay.swiftpass.cn/pay/jspay?token_id='+token_id+'&showwxtitle=1';
					} else {
						toast.fail({
							title : data.appmsg,
							duration : 2000
						});
					}
				} else {
					toast.fail({
						title : "预约失败，系统处理异常",
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
//查询预约时间 
 function showSubscribeTime(){
 	var shop_id=$("#shop_id").val();
 	  var subscribe_date=$("#service_date").val();
 	$.ajax({
 		type : 'post',
 		url : '${ctx}/wechat/order/showSubscribeTime.jhtml',
 		data : {
 			shop_id:shop_id,
 			subscribe_date:subscribe_date
 		},
 		dataType : 'json',
 		success : function(data) {
 			if (data) {
 				 var showTime=$("#showTime");
 				 var html='';
 				var rows=data.rows;
 				 for(var i=0;i<rows.length;i++){
 					  var row=rows[i];
 					  var status=row.status;
 					 var curFlag=row.curFlag;
 					 
 					  if(status=="3"){
 						 html+='<li class="disable">'+row.showtime+'</li>'
 					  }else if(status=="2"){
 						
 						 html+='<li class="disable full">'+row.showtime+'</li>'
 						
 					  }else{
 						 html+='<li>'+row.showtime+'</li>'
 					  }
 					   
 				   }
 				 showTime.html(html);
 				 /*选中时间段*/
 			    $('.popu-time ul li').on('click',function(){
 			        if(!$(this).hasClass('disable')){
 			            $(this).addClass('cur')
 			            $(this).siblings().removeClass('cur')
 			            selectTime.onTime = $(this).html()
 			        }
 			    })
 			} 
 		},
 		error : function() {
 			
 		}
 	})
 }
//预约项目
 function selectShop(project_id){
 	 window.location.href = '${ctx}/wechat/shop/goFindShop.jhtml?returnType=1&project_id=${project.project_id}';
 }
 
</script>
</html>