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
  function queryFinanceTotal(){
	  var total_month=$('#datetime1').datebox('getValue');
	  $.ajax({
			type : 'post',
			url  :'${ctx}/shop/orderManage/queryFinanceTotal.jhtml',
			data : {
			  'total_month' :total_month
			},
			dataType : 'json',
			success : function(data) {
				if (data) {
					$("#month_cash_in").html(data.month_cash_in);
					$("#month_expend_in").html(data.month_expend_in);
					$("#total_cash_income").html(data.total_cash_income);
					$("#total_extend_income").html(data.total_extend_income);
					$("#cash_income_percent").html(data.cash_income_percent);
					$("#extend_income_percent").html(data.extend_income_percent);
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
  function downloadShopFinance(){
	  
	  $('#queryForm').form('submit', {   
		    url:'${ctx}/shop/orderManage/downloadShopFinance.jhtml'
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
	      
	      queryFinanceTotal();
	  }); 
      function queryForm(){
    	  queryFinanceTotal();
    	  doQuery('recordList','queryForm');
      }
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:90px;padding:2px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
						<tr>
								<td width="6%" style="text-align: right">目标现金收入：</td>
								<td width="13%" style="text-align: left">
							       <span id="month_cash_in"></span>
								</td>
								<td width="6%" style="text-align: right">目标消耗收入：</td>
								<td width="13%" style="text-align: left">
							   <span id="month_expend_in"></span>
								</td>
								<td width="6%" rowspan="4" style="text-align: right">统计月份：</td>
								<td width="10%" rowspan="4"  style="text-align: left">
					             <input type="text"  name="total_month"  id="datetime1"  class="easyui-datebox" editable="false" style="width:130px" />		
								</td>
								<td width="30%" rowspan="4" algin="left">
								
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="queryForm()">查询</a> &nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="excel" onclick="downloadShopFinance()">导出Excel</a> 
							
								</td>
							</tr>
							<tr>
						    	<td width="6%" style="text-align: right">现金收入：</td>
								<td width="13%" style="text-align: left">
								  <span id="total_cash_income"></span>
								</td>
								<td width="6%" style="text-align: right">消耗收入：</td>
								<td width="13%" style="text-align: left">
								  <span id="total_extend_income"></span>
								</td>
								
								
					        </tr>
							<tr>
						    	<td width="6%" style="text-align: right">现金收入完成率：</td>
								<td width="13%" style="text-align: left">
								<span id="cash_income_percent"  style="color:red"></span>
								</td>
								<td width="6%" style="text-align: right">消耗收入完成率：</td>
								<td width="13%" style="text-align: left">
								<span id="extend_income_percent" style="color:red"></span>
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
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/orderManage/listFinanceTotal.jhtml',
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="order_id"  width="6%"    align="center">订单编号</th>
				<th field="pay_time" width="9%" align="center">交易时间</th>
				<th field="mobile" width="7%"   align="center">顾客账号 </th>
				<th field="username" width="5%"   align="center">顾客姓名</th>
				<th field="order_content" width="10%"   align="center">交易内容</th>
				<th field="order_remark" width="15%"   align="center">订单备注</th>
				<th field="order_money" width="4%"   align="center">价格<br/>（元）</th>
				<th field="pay_money" width="5%" align="center">实付金额<br/>（元）</th>
				<th field="extend_beauty_num" width="5%" align="center">消耗颜值</th>
				<th field="pay_way" formatter="pay_wayFormatter" width="5%" align="center">支付方式</th>
				<th field="cash_income" width="6%" align="center">现金收入<br/>（元）</th>
				<th field="extend_income" width="6%" align="center">消耗收入<br/>（元）</th>
				<th field="server_name" width="5%" align="center">服务员工</th>
				<th field="account" width="6%" align="center">经手员工</th>
				
				
			</tr>
		</thead>
	</table>
	
    </div>
    </div>
   <div id="showOrderDetailWindow" class="easyui-window" title="订单详情"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:800px; height: 440px; background-color: #FFFFFF"></div>
   <div id="completeOrderWindow" class="easyui-window" title="完成订单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:800px; height: 440px; background-color: #FFFFFF"></div>
   <div id="modifyOrderWindow" class="easyui-window" title="修改订单"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:800px; height: 440px; background-color: #FFFFFF"></div>
	<div id="modifyShopUserWindow" class="easyui-window" title="修改员工信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 1050px; height: 480px; background-color: #FFFFFF"></div>
   <div id="modifyPasswordWindow" class="easyui-window" title="密码重置"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 520px; height:180px; background-color: #FFFFFF"></div>
   
    
</body>


