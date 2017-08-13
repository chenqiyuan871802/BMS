<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<body style="margin: 0; padding: 0" >
<script type="text/javascript">
$(function(){
	$('#easyui-tabs').tabs({   
        border:false,   
        onSelect:function(title){
        	var pp = $('#easyui-tabs').tabs('getSelected');//获取选中的对象   
           var index=	$('#easyui-tabs').tabs('getTabIndex', $('#easyui-tabs').tabs('getSelected'));
           if(index==0){
        	   $("#easyui-tabs").tabs("update",{  //基本信息
                   tab:pp, 
                   options:
                   {
                    cache:true,
                    content:'<iframe src="${ctx}/system/customUser/goShow.jhtml?custom_user_id=${custom_user_id}" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                    selected:true
                   }
               }); 
           }
           if(index==1){
        	   $("#easyui-tabs").tabs("update",{  //消费情况
                   tab:pp, 
                   options:
                   {
                    cache:true,
                    content:'<iframe src="${ctx}/system/business/initCustomRecord.jhtml?custom_user_id=${custom_user_id}" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                    selected:true
                   }
               }); 
           }
           if(index==2){
        	   $("#easyui-tabs").tabs("update",{  //待服务项目
                   tab:pp, 
                   options:
                   {
                    cache:true,
                    content:'<iframe src="${ctx}/system/business/initNoServerRecord.jhtml?custom_user_id=${custom_user_id}" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                    selected:true
                   }
               }); 
           }
         
        }
	
     })
      var pp = $('#easyui-tabs').tabs('getSelected');//获取选中的对象   
     $("#easyui-tabs").tabs("update",{  //基本信息
         tab:pp, 
         options:
         {
          cache:true,
          content:'<iframe src="${ctx}/system/customUser/goShow.jhtml?custom_user_id=${custom_user_id}" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
          selected:true
         }
     }); 
	
})
</script>
<div id="easyui-tabs" class="easyui-tabs" style="padding: 0px; overflow: hidden" fit="true" >
<div  title="基本信息" ></div>
 <div title="消费情况"></div>
 <div title="未服务项目"></div>
 
</div>
</body>
</html>