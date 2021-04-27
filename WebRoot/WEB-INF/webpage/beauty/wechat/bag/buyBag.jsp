<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>购买礼包</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <style type="text/css">
        html,body{background:#f7f7f7;}
    </style>
</head>
<body>
    <div class="payfor-page">
        <div class="project mt20">
            <div class="box">
                <img class="fl" src="${ctx}/${bag.cover_photo}">
                 <input type="hidden" id="min_buy_num"  value="${bag.min_buy_num}">
                 <input type="hidden" id="max_buy_num"  value="${bag.max_buy_num}">
                 <input type="hidden" id="bag_total_price"  value="${bag.bag_total_price}">
                 <input type="hidden" id="order_money" value="${bag.bag_total_price*bag.min_buy_num }" >
                <div class="intro fl">
                    <h3>${bag.bag_name}</h3>
                    <p>￥<fmt:formatNumber
						value="${bag.bag_total_price }" type="number" /></p>
                </div>
                <p class="payfor-validity">有效期至：${overdue_date }</p>
                <div class="count-box">
                    <i class="sy_minus">-</i>
                    <input class="num" type="text" id="buy_num" onchange="showBuyNum()" value="${bag.min_buy_num}">
                    <i class="sy_plus">+</i>
                </div>
            </div>
            <div class="cl"></div>
            <div class="h30"></div>
        </div>
        <div class="payfor-bottom">
            <span class="fl">&nbsp;<i id="showMoney">&nbsp;</i></span>
            <div class="fr">
                <span class="num" id="num">数量x${bag.min_buy_num}</span>
                <a class="btn fr" href="#" onclick="buyBagPay(${bag.bag_id})">确定</a>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
/*添加数量*/
$(document).ready (function(){
    var reg = /(.*[\:\：]\s*)([\+\d\.]+)(\s*元)/g;
    $ (".sy_minus").click (function (){
        var me = $ (this), txt = me.next (":text"), pc = me.closest("p");
        var val = parseFloat (txt.val ());
        val = val < 1 ? 1 : val;
        txt.val (val - 1);
        var price = parseFloat (pc.prev("p").text().replace(reg,'$2')) * txt.val ();
        pc.next("p").text (pc.next("p").text().replace(reg, "$1" + price + "$3"));
        var num = $(this).siblings('input').val();
        $(this).parent().parent().find('.data i.num').html(num)
        showBuyNum();
    });
     
    $(".sy_plus").click (function (){
        var me = $ (this), txt = me.prev (":text"), pc = me.closest("p");
        var val = parseFloat (txt.val ());
        txt.val (val + 1);
        var price = parseFloat (pc.prev("p").text().replace(reg,'$2')) * txt.val ();
        pc.next("p").text (pc.next("p").text().replace(reg, "$1" + price + "$3"));
        var num = $(this).siblings('input').val();
        $(this).parent().parent().find('.data i.num').html(num)
        showBuyNum();
    });
})[0].onselectstart = new Function ("return false");
 
  var toast = new auiToast(); 

  //触发购买数量
  function showBuyNum(){
	  var buy_num_str=$("#buy_num").val();
	  var min_buy_num_str=$("#min_buy_num").val();
	  var max_buy_num_str=$("#max_buy_num").val();
	  var bag_total_price_str=$("#bag_total_price").val();
	  var buy_num=parseInt(buy_num_str);
	  var min_buy_num=parseInt(min_buy_num_str);
	  var max_buy_num=parseInt(max_buy_num_str);
	  var bag_total_price=parseFloat(bag_total_price_str)
	  if(isNaN(buy_num)){
 		  buy_num=min_buy_num;
	  }
	  var num=buy_num;
	  if(buy_num<min_buy_num){
			toast.fail({
				title : "最少购买"+min_buy_num+"份",
				duration : 2000
			});
			num=min_buy_num
					
		
	  }
	  if(buy_num>max_buy_num){
			toast.fail({
				title : "最多购买"+max_buy_num+"份",
				duration : 2000
			});
		  num=max_buy_num;
		
	  }
	 var total_money=bag_total_price*num;
	 var money=parseFloat(total_money.toFixed(2));
	 $("#buy_num").val(num);
     $("#num").html('数量x'+num);
    
     $("#order_money").val(0);
     
  }
  function toThousands(num) {
	    var result = '', counter = 0;
	    num = (num || 0).toString();
	    for (var i = num.length - 1; i >= 0; i--) {
	        counter++;
	        result = num.charAt(i) + result;
	        if (!(counter % 3) && i != 0) { result = ',' + result; }
	    }
	    return result;
	}
  //购买礼包支付
  function buyBagPay(bag_id){
	    var order_money=$("#order_money").val();
	    var buy_num=$("#buy_num").val();
		toast.loading({
            title:"数据处理中"
           
        })
        $.ajax({
			type : 'post',
			url : '${ctx}/wechat/order/saveFreeBagOrder.jhtml',
			data : {
				bag_id:bag_id,
				order_money:0,
				buy_num:buy_num
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					if (data.appcode == "1") {
						//var token_id=data.token_id
					    window.location.href = '${ctx}/wechat/order/goMyOrder.jhtml?index=2';
					} else {
						toast.fail({
							title : data.appmsg,
							duration : 2000
						});
					}
				} else {
					toast.fail({
						title : "购买礼包失败，系统处理异常",
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
</script>
</html>