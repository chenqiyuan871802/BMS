<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
</head>
<style type="text/css">
.queryDiv {
	width: 94%;
	margin: 0 auto;
	height: auto;
	margin-top: 5px;
	margin-left: 45px;
	text-align: left;
}

.tabcount {
	width: 94%;
	margin: 0 auto;
	height: auto;
	margin-top: 10px;
	border-radius: 5px;
	text-align: left;
}

.tabcount a {
	display: inline-block;
	width: 20%;
	height: 80px;
	color: #fff;
	text-align: center;
	margin: 0px 10px;
	overflow: hidden;
}

.tabcount a span {
	display: block;
	font-size: 20px;
	font-weight: bold;
}

.spancount {
	padding: 10px 0px;
	font-size: 15px !important;
}

.bicon1 {
	background-color: #44b7ee;
}

.bicon2 {
	background-color: #4fd5d7;
}

.bicon3 {
	background-color: #a2d74f;
}

.bicon4 {
	background-color: #efa76b;
}
</style>
<script type="text/javascript">
	$(function() {
		queryPlatformSum();
	})
	//平台统计
	function queryPlatformSum() {
		var create_time_begin = $("#create_time_begin").datebox("getValue");
		var create_time_end = $("#create_time_end").datebox("getValue");
		$.messager.progress({
			title : '信息查询',
			text : '数据正在查询中，请耐心等待...'
		});
		$.ajax({
			type : 'post',
			url : '${ctx}/system/finance/queryPlatformSum.jhtml',
			data : {
				create_time_begin : create_time_begin,
				create_time_end : create_time_end
			},
			dataType : 'json',
			success : function(data) {

				if (data) {

					if (data.total_money == undefined) {
						$("#total_money").html(0)
					} else {
						$("#total_money").html(data.total_money)
					}

					if (data.project_total_money == undefined) {
						$("#project_total_money").html(0)
					} else {
						$("#project_total_money")
								.html(data.project_total_money)
					}

					if (data.bag_total_money == undefined) {
						$("#bag_total_money").html(0)
					} else {
						$("#bag_total_money").html(data.bag_total_money)
					}

					if (data.beauty_total_money == undefined) {
						$("#beauty_total_money").html(0)
					} else {
						$("#beauty_total_money").html(data.beauty_total_money)
					}
					if (data.total_extend_money == undefined) {
						$("#total_extend_money").html(0)
					} else {
						$("#total_extend_money").html(data.total_extend_money)
					}

					if (data.project_extend_money == undefined) {
						$("#project_extend_money").html(0)
					} else {
						$("#project_extend_money").html(
								data.project_extend_money)
					}

					if (data.bag_extend_money == undefined) {
						$("#bag_extend_money").html(0)
					} else {
						$("#bag_extend_money").html(data.bag_extend_money)
					}
					if (data.beauty_extend_money == undefined) {
						$("#beauty_extend_money").html(0)
					} else {
						$("#beauty_extend_money")
								.html(data.beauty_extend_money)
					}
					if (data.subscribe_total_money == undefined) {
						$("#subscribe_total_money").html(0)
					} else {
						$("#subscribe_total_money").html(
								data.subscribe_total_money)
					}

				} else {
					$.messager.alert('错误信息', '查询统计失败', 'error');
				}
			},
			error : function() {
				$.messager.alert('错误信息', '查询统计失败', 'error');
			}
		})
		$.messager.progress('close');
	}
	function percashFormatter(value){
		if(value>0){
			
			return value+"%";
			
		}
		return value;
		
	}
	function perexpendFormatter(value){
		if(value>0){
			
			return value+"%";
			
		}
		return value;
		
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
	 function downloadData(){
		 $('#queryForm').form('submit', {   
			    url:'${ctx}/system/finance/downloadPlatformSum.jhtml'
		  });  
	 }
</script>
<body style="margin: 0; padding: 0">
<div  style="height: 950px">
	<div class="easyui-layout"
		data-options="fit:true">
		<div style="height: 320px; background-color: white;"
			data-options="region:'north',split:false">
			<div class="queryDiv">
				<input type="text" name="create_time_begin" id="create_time_begin"
					value="${month_first_day}" class="easyui-datebox" editable="false"
					style="width: 100px" /> 至：<input type="text"
					name="create_time_end" id="create_time_end" value="${current_day}"
					class="easyui-datebox" editable="false" style="width: 100px" />
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-search"
					onclick="queryPlatformSum()">统计</a>
			</div>
			<div class="tabcount">
				<a href="javascript:void(0)" class="bicon1"> <span
					class="spancount">总现金收入（元）</span><span id="total_money"></span>
				</a> <a href="javascript:void(0)" class="bicon2"> <span
					class="spancount">项目现金收入（元）</span><span id="project_total_money"></span>
				</a> <a href="javascript:void(0)" class="bicon3"> <span
					class="spancount">礼包现金收入（元）</span><span id="bag_total_money"></span>
				</a> <a href="javascript:void(0)" class="bicon4"> <span
					class="spancount">颜值现金收入（元）</span><span id="beauty_total_money">
				</span>
				</a>

			</div>
			<div class="tabcount">
				<a href="javascript:void(0)" class="bicon1"> <span
					class="spancount">总消耗收入（元）</span><span id="total_extend_money"></span>
				</a> <a href="javascript:void(0)" class="bicon2"> <span
					class="spancount">礼包消耗收入（元）</span><span id="bag_extend_money"></span>
				</a> <a href="javascript:void(0)" class="bicon3"> <span
					class="spancount">项目消耗收入（元）</span><span id="project_extend_money"></span>
				</a> <a href="javascript:void(0)" class="bicon4"> <span
					class="spancount">颜值消耗收入（元）</span><span id="beauty_extend_money"></span>
				</a>

			</div>
			<div class="tabcount">
				<a href="javascript:void(0)" class="bicon1"> <span
					class="spancount">总预定收入（元）</span><span id="subscribe_total_money"></span>
				</a>

			</div>
		</div>
		<div id="main"
			data-options="region:'center',split:false, border:false">
			<div class="easyui-layout" data-options="fit:true">
				<div style="height: 67px; background-color: white;" title="商家收入"
					data-options="region:'north',split:true">
					<form id="queryForm" method="post">
						<table class="searchContent">
							<tr>
                             <td width="6%" rowspan="4" style="text-align: right">统计月份：</td>
								<td width="10%" rowspan="4"  style="text-align: left">
					             <input type="text"  name="total_month"  id="datetime1"  class="easyui-datebox" editable="false" style="width:130px" />		
								</td>

								<td width="7%" style="text-align: right">店铺名称：</td>
								<td width="13%" style="text-align: left"><input type="text"
									name="shop_id" class="easyui-combobox"
									data-options="url:'${ctx}/system/shopSys/queryShop.jhtml',method:'get',valueField:'shop_id',textField:'shop_name'"
									style="width: 150px;" /></td>

								<td width="20%" rowspan="4" algin="left">&nbsp;<a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search"
									onclick="doQuery('dataList','queryForm')">查询</a> &nbsp; <a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="refresh" onclick="$('#queryForm').form('reset')">重置</a>
								</td>
							</tr>


						</table>
					</form>
				</div>

				<div id="main2"
					data-options="region:'center',split:false, border:false">
					<table id="dataList" class="easyui-datagrid"
						style="width: 100%; height: 100%; padding: 0px;"
						data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                pagination:false,
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                  toolbar:'#toolbar',
	                url:'${ctx}/system/finance/listPlatformSum.jhtml'
	                ">

						<thead>
							<tr>
								<th field="shop_id" width="7%" align="center">店铺编号</th>
								<th field="shop_name" width="12%" align="center">店铺名称</th>
								<th field="short_name" width="10%" align="center">店铺简称</th>
								<th field="total_cash_income" width="10%" align="center">店铺现金收入</th>
								<th field="month_cash_in" width="10%" align="center">现金收入<br/>目标业绩</th>
								<th field="per_cash_income" formatter="percashFormatter" width="10%" align="center">完成率</th>
								<th field="total_extend_income" width="10%" align="center">店铺消耗收入</th>
								<th field="month_expend_in" width="10%" align="center">消耗收入<br/>目标业绩</th>
								<th field="per_extend_income" formatter="perexpendFormatter" width="10%" align="center">完成率</th>
								<th field="total_free_num" width="10%" align="center">免费颜值收入</th>
							</tr>
						</thead>
					</table>
					<div id="toolbar" style="padding: 2px;">
  
	<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="excel" plain="true"
			onclick="downloadData()">导出Excel</a>
	  
	</div>
	
				</div>
				</div>
			</div>
		</div>
		</div>
</body>


