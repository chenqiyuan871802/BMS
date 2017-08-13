<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>意见反馈</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    
</head>
<body>
    <div class="advice-feedback">
        <textarea placeholder="有什么好的建议？如采纳，有重赏" onchange="showWordNum()" id="content"></textarea>
        <div class="statistics">还可输入<span id="showWordNum">500</span>字</div>
        <div class="btn"><a href="#" onclick="saveOpinion();">提交</a></div>
    </div>
</body>
<script type="text/javascript">
function showWordNum(){
	var maxlen=500;
	var content=$("#content").val()
	var len=content.length;
	var remainNum= maxlen-len;
	if(remainNum>=0){
		$("#showWordNum").html(remainNum);
	}else{
		$("#content").val(content.substring(0, maxlen))
	}
	
}
//反馈意见
function saveOpinion(){
	var toast = new auiToast();
	var content=$("#content").val()
	if(content==''){
		toast.fail({
			title : "请输入你的建议",
			duration : 2000
		});
		return;
	}
	toast.loading({
        title:"数据处理中"
       
    })
	$.ajax({
		type : 'post',
		url : '${ctx}/wechat/home/saveOpinion.jhtml',
		data : {
			content:content
		},
		dataType : 'json',
		success : function(data) {
			if (data) {
				if (data.appcode == "1") {
					toast.success({
					    title:data.appmsg,
					    duration:2000
					});
					$("#content").val('')
				} else {
					toast.fail({
						title : data.appmsg,
						duration : 2000
					});
				}
			} else {
				toast.fail({
					title : "反馈建议失败，系统处理异常",
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