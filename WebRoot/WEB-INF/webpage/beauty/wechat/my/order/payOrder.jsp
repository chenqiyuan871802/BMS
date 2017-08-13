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

.selectProject .payfor-bottom {
	position: fixed;
	bottom: 0;
	height: 3.2rem;
	background: #fff;
	border-top: #e8e8e8 1px solid;
	width: 100%;
	padding: 0 0.8rem;
}

.selectProject .payfor-bottom span.fl {
	display: block;
	line-height: 3.2rem;
	font-size: 0.853333rem;
	color: #666666;
}

.selectProject .payfor-bottom span.fl i {
	font-style: normal;
	color: #ea8b21;
}

.selectProject .payfor-bottom span.num {
	font-size: 0.64rem;
	color: #999;
	line-height: 3.2rem;
}

.selectProject .payfor-bottom a.btn {
	display: block;
	width: 5.333333rem;
	height: 2.4rem;
	line-height: 2.4rem;
	text-align: center;
	color: #fff;
	font-size: 0.746667rem;
	background: #ea8b21;
	margin: 0.4rem 0 0.4rem 0.4rem;
}
.selectProject .payfor-bottom a.ctn {
	display: block;
	width: 5.333333rem;
	height: 2.4rem;
	line-height: 2.4rem;
	text-align: center;
	color: #fff;
	font-size: 0.746667rem;
	background: red;
	margin: 0.4rem 0 0.4rem 0.4rem;
}
</style>
</head>
<body>
	<div class="payfor-page" id="payPage">
	 <input type="hidden" id="payment" value="2"/>
	 <input type="hidden" id="count" value="${count}"/>
	 <input type="hidden" id="record_id" />
	 <input type="hidden" id="rmb_price"  value="<fmt:formatNumber value="${nurseProjectPO.rmb_price}"
							type="number" />"/>
	 <input type="hidden" id="beauty_price" value="${nurseProjectPO.beauty_price }"/>
		<div class="top">
			<p>订单编号：${orderDto.order_id }</p>
			<p>商家编号：${orderDto.shop_id }</p>
			<p>商家名称：${orderDto.shop_name }（${orderDto.short_name }）</p>
		</div>
		<div class="project mt20">
			<div class="box">
				<img class="fl" src="${ctx }/${nurseProjectPO.cover_photo}">
				<div class="intro fl">
					<h3>${nurseProjectPO.project_name }</h3>
					<p>
						￥
						<fmt:formatNumber value="${nurseProjectPO.rmb_price}"
							type="number" />
						/${nurseProjectPO.beauty_price }颜值
					</p>
				</div>
			</div>
		</div>
		<div class="cl"></div>
		<div class="payment mt20">
			<ul>
				<li><i class="ico"><img
						src="${ctx}/static/wechat/images/ico-meiyan-coin.png"></i> <span>颜值支付（可用颜值${customUserPO.beauty_num }个）</span>
					<div class="selectbox">
						<input class="aui-radio" type="radio" name="payment" value="1" onclick="clickRedio(this.value)">
					</div></li>
				<li><i class="ico"><img
						src="${ctx}/static/wechat/images/ico-wechat.png"></i> <span>微信支付</span>
					<div class="selectbox">
						<input class="aui-radio" type="radio" value="2" checked="" onclick="clickRedio(this.value)"
							name="payment">
					</div>
					</li>
				<li>
                    <i class="ico"><img src="${ctx}/static/wechat/images/ico-gift-bag.png"></i>
                    <span >使用礼包</span>
                    <span class="fr" onclick="selectProject()" id="payProjectInfo">
                    <c:if test="${count==0 }">暂无抵扣项目</c:if>
					<c:if test="${count>0 }">可使用${count}个项目抵扣</c:if></span>
                </li>
				</li>
			</ul>
		</div>
		<div class="payfor-bottom">
			<span class="fl">待支付：<i id="payPrice">￥<fmt:formatNumber value="${nurseProjectPO.rmb_price}" type="number" />
			</i></span> <a class="btn fr" href="#" onclick="payOrder(${orderDto.order_id})">确定</a>
		</div>
		<!-- 颜值支付提示 -->
		<div class="popu-meiyan-payfor">
			<div class="payfor-coin-logo">
				<img src="${ctx}/static/wechat/images/my-coin-logo.png">
			</div>
			<div class="cot">
				<span class="fl">颜值(个)</span> <span class="fr">-${nurseProjectPO.beauty_price }</span>
			</div>
			<div class="cl"></div>
			<div class="btn">
				<a href="#" onclick="closeTitle()">确定</a>
			</div>
		</div>
		<!-- 支付成功 -->
		<div class="popu-payfor-success">
			<img src="${ctx}/static/wechat/images/payfor-success.png"> <span>支付成功</span>
		</div>
		<!-- 黑色半透明遮罩层 -->
		<div class="mask"></div>
	</div>
	<div id="selectProject" class="selectProject" style="display: none">
		<div class="selec-project">
			<ul>
				<c:forEach var="project" items="${projectList}" varStatus="status">
					<li>
						<div class="selectbox fl">
							<input class="aui-radio" type="radio" name="record_id" value="${project.record_id}">
						</div> <img class="fl" src="${ctx}/${project.cover_photo}">
						<div class="intro fl">
							<h3 id="project_${project.record_id}">${project.project_name }</h3>
							<p>
								有效期至：
								<fmt:formatDate value="${project.overdue_date}"
									pattern="yyyy-MM-dd" />
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>

		</div>

		<div class="payfor-bottom">
			<span class="fl"><i>
			 <a class="btn fr" href="#" onclick="sureSelectProject()">确定</a>
			</i></span> <a class="ctn fr" href="#" onclick="cancelProject()">返回</a>
		</div>
	</div>
</body>

<script type="text/javascript">
  var toast = new auiToast();
   var beauty_price_str='${nurseProjectPO.beauty_price}'
   var total_beauty_str='${customUserPO.beauty_num }'
   function payOrder(order_id){
	
	 var payment=$("#payment").val();
	 
	 if(payment==1){  //颜值支付
		 beautyPayOrder(order_id);
	 }else if(payment==3){ //项目支付
		 projectPayOrder(order_id);
	 }else{  //微信支付
		 wechatPayOrder(order_id);
	 }
	
   }
   function clickRedio(value){
	  
	   $('#payment').val(value);
	   var count=$('#count').val();
	   if(count>0){
		   $("#payProjectInfo").html("可使用"+count+"个项目抵扣");
	   }
	   if(value=='2'){
		   $("#payPrice").html('￥'+$("#rmb_price").val());
	   }else{
		   $("#payPrice").html($("#beauty_price").val()+'币');
	   }
   }
   function selectProject(){
	  var count=$('#count').val();
	  if(count==0){
		  toast.fail({
				title :'暂无抵扣项目',
				duration : 2000
			});
		  return ;
	  }
	  $("#selectProject").show();
	  $("#payPage").hide();
   }
   //确认选择项目
   function sureSelectProject(){
	  
	   var record_id=$("input[name='record_id']:checked").val();

	   if(record_id==undefined){
		   toast.fail({
				title :'请选择支付项目',
				duration : 2000
			});
		   return ;
	   }
	   var project_name=$("#project_"+record_id).html();
	   $('#payProjectInfo').html(project_name);
	   $('#record_id').val(record_id);
	   $('#payment').val('3');
	   $('input:radio[name=payment]').attr('checked',false);
	   $("#selectProject").hide();
	   $("#payPage").show();
	   $("#payPrice").html('礼包项目');
   }
   //取消项目
   function cancelProject(){
	   $("#selectProject").hide();
	   $("#payPage").show();
   }
   //美丽币支付订单
   function beautyPayOrder(order_id){
	  var beauty_price=parseInt(beauty_price_str);
	  var total_beauty=parseInt(total_beauty_str);
	   if(beauty_price>total_beauty){ //颜值不足
		  
		   toast.fail({
				title :'颜值不足',
				duration : 2000
			});
		   return ;
	   }
	   toast.loading({
           title:"正在支付申请处理中"
          
       })
	   $.ajax({
			type : 'post',
			url : '${ctx}/wechat/order/beautyPayOrder.jhtml',
			data : {
				order_id:order_id,
				beauty_num:beauty_price
			},
			dataType : 'json',
			success : function(data) {
				
				if (data) {
					if (data.appcode == "1") {
						toast.success({
						    title:data.appmsg,
						    duration:2000
						});
						window.location.href ='${ctx}/wechat/order/goMyOrder.jhtml'
					} else {
						toast.fail({
							title : data.appmsg,
							duration : 2000
						});
					}
				} else {
					toast.fail({
						title : "支付失败，系统处理异常",
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
   //项目支付支付订单
   function projectPayOrder(order_id){
	   var count=$('#count').val();
		  if(count==0){
			  toast.fail({
					title :'暂无抵扣项目',
					duration : 2000
				});
			  return ;
		 }
	   var record_id=$('#record_id').val();
	   toast.loading({
           title:"正在支付申请处理中"
          
       })
	   $.ajax({
			type : 'post',
			url : '${ctx}/wechat/order/projectPayOrder.jhtml',
			data : {
				order_id:order_id,
				record_id:record_id
			},
			dataType : 'json',
			success : function(data) {
				
				if (data) {
					if (data.appcode == "1") {
						toast.success({
						    title:data.appmsg,
						    duration:2000
						});
						window.location.href ='${ctx}/wechat/order/goMyOrder.jhtml'
					} else {
						toast.fail({
							title : data.appmsg,
							duration : 2000
						});
					}
				} else {
					toast.fail({
						title : "支付失败，系统处理异常",
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
 //支付订单
   function wechatPayOrder(order_id){
	   toast.loading({
           title:"正在支付申请处理中"
          
       })
   	 $.ajax({
				type : 'post',
				url : '${ctx}/wechat/order/payOrder.jhtml',
				data : {
					order_id:order_id
				},
				dataType : 'json',
				success : function(data) {
					
					if (data) {
						if (data.appcode == "1") {
							var token_id=data.token_id
						    window.location.href = 'https://pay.swiftpass.cn/pay/jspay?token_id='+token_id+'&showwxtitle=1';
						} else {
							toast.fail({
								title : data.appmsg,
								duration : 2000
							});
						}
					} else {
						toast.fail({
							title : "支付失败，系统处理异常",
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
 
   function openSuccess(){
	   $(".popu-meiyan-payfor").show(); 
		 $(".mask").show();
   }
   function closeTitle(){
	  
	   $(".popu-meiyan-payfor").hide();
	   $(".mask").hide();
	   window.location.reload(); 
	}
</script>
</html>