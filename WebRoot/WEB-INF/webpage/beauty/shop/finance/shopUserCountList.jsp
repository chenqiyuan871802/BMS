<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="order_type,pay_way,order_source"/>
<IMS:codeStoreFilter filter="1,2,3" field="order_status"/>
<IMS:codeFormatter fields="order_type,pay_way,order_status,order_source"/>
</head>
<script type="text/javascript">
//查询财务总
function queryShopSum(){
	  var total_month=$('#datetime1').datebox('getValue');
	  $.ajax({
			type : 'post',
			url  :'${ctx}/shop/orderManage/queryShopSum.jhtml',
			data : {
			  'total_month' :total_month
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					if(data.total_cash_income==undefined){
						$("#total_cash_income").html(0);
						
					}else{
						$("#total_cash_income").html(data.total_cash_income);
					}
					if(data.total_extend_income==undefined){
						$("#total_extend_income").html(0);
					}else{
						$("#total_extend_income").html(data.total_extend_income);
					}
					if(data.manual_money==undefined){
						
						$("#manual_money").html(0)
					}else{
						$("#manual_money").html(data.manual_money);
					}
					
				} else {
					$.messager.alert('错误信息', '操作失败', 'error');
				}
			},
			error : function() {
				
				$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
			}
		})
}
  //下载财务总控
  function downloadShopUserCount(){
	  
	  $('#queryForm').form('submit', {   
		    url:'${ctx}/shop/orderManage/downloadShopUserCount.jhtml'
	  });  
  }
  $(function() {      
	    $('#datetime1').datebox({    
	          onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层    
	              span.trigger('click'); // 触发click事件弹出月份层    
	              if (!tds)    
	                  setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔    
	                      tds = p.find('div.calendar-menu-month-inner td');    
	                      tds.click(function(e) {    
	                          e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件    
	                          var year = /\d{4}/.exec(span.html())[0]// 得到年份    
	                          , month = parseInt($(this).attr('abbr'), 10) ; // 月份    
	                          $('#datetime1').datebox('hidePanel')// 隐藏日期对象    
	                          .datebox('setValue', year + '-' + month); // 设置日期的值    
	                      });    
	                  }, 0);    
	          },    
	          parser : function(s) {// 配置parser，返回选择的日期    
	        	  
	              if (!s)    
	                  return new Date();    
	              var arr = s.split('-');    
	              return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);    
	          },    
	          formatter : function(d) {  
	        	 
	              var month = d.getMonth()+1;  
	              if(month<10){  
	                  month = "0"+month;  
	              }  
	              if (d.getMonth() == 0) {    
	                  return d.getFullYear()-1 + '-' + 12;    
	              } else {    
	                  return d.getFullYear() + '-' + month;    
	              }    
	          }// 配置formatter，只返回年月    
	      });    
	      var p = $('#datetime1').datebox('panel'), // 日期选择对象    
	      tds = false, // 日期选择对象中月份    
	      span = p.find('span.calendar-text'); // 显示月份层的触发控件    
	      $('#datetime1').datebox('setValue', '${total_month}'); // 设置日期的值    
	      
	      queryShopSum()
	  }); 
      function queryForm(){
    	  queryShopSum();
    	  doQuery('recordList','queryForm');
      }
      function monthFormatter(value){
    	  var total_month=$('#datetime1').datebox('getValue');
    	  return total_month	;
      }
      function handleFunction(value, row, index){
    	  var shop_user_id=row.shop_user_id;
    	  var hStr="showShopUserServerOrder("+shop_user_id+")";
    	  var str="<a href='#' onclick='"+hStr+"' class='button-complete button-success'>查看详情</a>&nbsp;";
    	  return str;
      }
      //查看员工业绩
      function showShopUserServerOrder(shop_user_id){
    	  var total_month=$('#datetime1').datebox('getValue');
    	  showWindow('showShopUserServerOrderWindow','${ctx}/shop/orderManage/initShopUserServerOrder.jhtml?shop_user_id='+shop_user_id+"&total_month="+total_month);
      }
      
      function  loadSuccess(){
  		$('.button-complete').linkbutton({ 
  		});
  		$('.button-detail').linkbutton({ 
  		});
  		$('.button-edit').linkbutton({ 
  		});
  }
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:70px;padding:2px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
						<tr>
								<td width="6%" style="text-align: right">姓名：</td>
								<td width="13%" style="text-align: left">
							      <input type="text" name="server_name" prompt="请输入员工姓名"    class="easyui-textbox" style="width: 160px; " />
								</td>
								
								<td width="6%"  style="text-align: right">统计月份：</td>
								<td width="10%" colspan="3"   style="text-align: left">
					             <input type="text"  name="total_month"  id="datetime1"  class="easyui-datebox" editable="false" style="width:130px" />		
								</td>
								<td width="50%"  rowspan="4" algin="left">
								
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="queryForm()">查询</a> &nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="excel" onclick="downloadShopUserCount()">导出Excel</a> 
							
								</td>
							</tr>
							<tr>
						    	<td width="6%" style="text-align: right">手工费：</td>
								<td width="13%" style="text-align: left">
								  <span id="manual_money" style="color:red"></span>
								</td>
								<td width="6%" style="text-align: right">现金收入：</td>
								<td width="13%" style="text-align: left">
								  <span id="total_cash_income"  style="color:red"></span>
								</td>
								<td width="6%" style="text-align: right">消耗收入：</td>
								<td width="13%" style="text-align: left">
								  <span id="total_extend_income" style="color:red"></span>
								</td>
								
								
					        </tr>
							
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="recordList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
		            cls:'theme-datagrid', 
				    border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:true,
	                striped:true,
	                onLoadSuccess:loadSuccess,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/listShopUserCount.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="shop_user_id"  width="6%" hidden="true"   align="center">订单编号</th>
				<th field="username" width="9%" align="center">姓名</th>
				<th field="work_number" width="9%"   align="center">工号 </th>
				<th field="post_name" width="8%"   align="center">职位</th>
				<th field="manual_money" width="10%"   align="center">手工费</th>
				<th field="total_cash_income" width="10%"   align="center">现金收入</th>
				<th field="total_extend_income" width="10%" align="center">消耗收入</th>
				<th field="month" formatter="monthFormatter" width="10%" align="center">统计月份</th>
				<th field="handle" formatter="handleFunction" width="10%" align="center">操作</th>
				
				
			</tr>
		</thead>
	</table>
	
    </div>
    </div>
  <div id="showShopUserServerOrderWindow" class="easyui-window" title="业绩详情"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1300px; height:450px; background-color: #FFFFFF"></div>
   
    
</body>


