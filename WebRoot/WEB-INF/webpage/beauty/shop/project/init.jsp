<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<script type="text/javascript">
$(function(){
   	$('#easyui-tabs').tabs({   
        border:false,   
        onSelect:function(title){
        	 var pp = $('#easyui-tabs').tabs('getSelected');//获取选中的对象   
        	 var index=	$('#easyui-tabs').tabs('getTabIndex', $('#easyui-tabs').tabs('getSelected'));
             if(index==0){
            	 $("#easyui-tabs").tabs("update",{ 
                     tab:pp, 
                     options:
                     {
                      cache:true,
                      content:'<iframe src="${ctx}/shop/shopManage/initBag.jhtml" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                      selected:true
                     }
                 }); 
             }else{
            	 $("#easyui-tabs").tabs("update",{ 
                     tab:pp, 
                     options:
                     {
                      cache:true,
                      content:'<iframe src="${ctx}/shop/shopManage/initProject.jhtml" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                      selected:true
                     }
                 }); 
             }
        	
             
            
          }
     });
     var pp = $('#easyui-tabs').tabs('getSelected');//获取选中的对象   
      $("#easyui-tabs").tabs("update",{ 
                   tab:pp, 
                   options:
                   {
                    cache:true,
                    content:'<iframe src="${ctx}/shop/shopManage/initBag.jhtml" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                    selected:true
                   }
               }); 
	
});
</script>
<body style="margin: 0; padding: 0" >
<div id="easyui-tabs" class="easyui-tabs"  style="padding: 0px; overflow: hidden" fit="true" >
<div   title="礼包列表(${bagCount }个)"   >
</div>
<div  title="护理项目(${projectCount }个)"   >
</div>

</div>
</body>

