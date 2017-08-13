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
                       content:'<iframe src="${ctx}/shop/orderManage/initFinanceTotal.jhtml" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                       selected:true
                      }
                  }); 
            }
            if(index==1){
            	  $("#easyui-tabs").tabs("update",{ 
                      tab:pp, 
                      options:
                      {
                       cache:true,
                       content:'<iframe src="${ctx}/shop/orderManage/initShopUserCount.jhtml" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
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
                    content:'<iframe src="${ctx}/shop/orderManage/initFinanceTotal.jhtml" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>',
                    selected:true
                   }
               }); 
	
});
</script>
<body style="margin: 0; padding: 0" >
<div id="easyui-tabs" class="easyui-tabs"  style="padding: 0px; overflow: hidden" fit="true" >
<div   title="财务总况"   >
</div>
<div  title="员工工资及业绩"   >
</div>

</div>
</body>

