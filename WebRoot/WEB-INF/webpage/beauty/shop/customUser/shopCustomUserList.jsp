<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/eayuiLibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="sex,recent_type,whether_type,order_type,pay_way"/>
<IMS:codeStoreFilter filter="1,2,3" field="order_status"/>
<IMS:codeFormatter fields="sex,order_type,pay_way,order_status"/>
</head>
<script type="text/javascript">
//格式化
function imageFormatter(value){  
		 if(IMSUtils.isNotEmpty(value)){
		    return '<img style="width:60px;height:60px"  src="${ctx }/'+value+'"  />';
		 }
		 return ''
}
//格式化
function bagFormatter(value){  
	 if(value>0){
		 return '有'
	 }
	 return '无'
}

//操作处理函数
function handleFunction(value, row, index){
	    var custom_user_id=row.custom_user_id;
	    var str =''
	    var handleStr="showCustomUserDetail("+custom_user_id+")";
	    var recordStr="queryOrderRecord("+custom_user_id+")";
	    str+="<a href='#' onclick='"+handleStr+"' class='button-detail button-default button-lg'>查看资料</a>&nbsp;"
	    str+="<a href='#' onclick='"+recordStr+"' class='button-detail button-warning button-lg'>消费情况</a>&nbsp;"
	   
		return str;
}
//查询消费记录
function queryOrderRecord(custom_user_id){
	
	showWindow('showCustomRecordWindow','${ctx}/shop/orderManage/initCustomRecord.jhtml?custom_user_id='+custom_user_id);
}

function  loadSuccess(){
	$('.button-complete').linkbutton({ 
	});
	$('.button-detail').linkbutton({ 
	});
	$('.button-edit').linkbutton({ 
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
                          , month = parseInt($(this).attr('abbr'), 10) + 1; // 月份    
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
              var month = d.getMonth();  
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
      
  });    
  function showCustomUserDetail(custom_user_id){
	  showWindow('showCustomRecordWindow','${ctx}/shop/shopManage/showCustomUserDetail.jhtml?custom_user_id='+custom_user_id);
  }
</script>
<body style="margin: 0; padding: 0" >
<div class="easyui-layout"  data-options="fit:true">
<div  style="height:80px;background-color: white;"  data-options="region:'north',split:false">
        <form id="queryForm" method="post">
        <table class="searchContent">
					<tr>
						<tr>
								<td width="7%" style="text-align: right">账号：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="mobile"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">姓名：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="username"   class="easyui-textbox" style="width: 150px;  " />
								</td>
								<td width="7%" style="text-align: right">生日筛选：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="born_date"  id="datetime1"  style="width: 150px;  " />
								</td>
								
								
								
								<td width="20%" rowspan="4" algin="left">
										&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery('customUserList','queryForm')">查询</a> &nbsp;
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-refresh" onclick="$('#queryForm').form('reset')">重置</a> 
								</td>
							</tr>
							<tr>
								<td width="7%" style="text-align: right">颜值：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="beauty_type"   class="easyui-combobox" editable="false"  data-options="data:whether_typeStore,textField:1,valueField:0" style="width: 150px; " />
								</td>
								<td width="7%" style="text-align: right">礼包：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="bag_type"   class="easyui-combobox" editable="false"  data-options="data:whether_typeStore,textField:1,valueField:0" style="width: 150px; " />
								</td>
								
								<td width="7%" style="text-align: right">时间分类：</td>
								<td width="13%" style="text-align: left">
								<input type="text" name="recent_type"   class="easyui-combobox" editable="false"  data-options="data:recent_typeStore,textField:1,valueField:0" style="width: 150px; " />
								</td>
								
					</tr>
					
				</table>
        </form>
      </div>
    <div id="main" data-options="region:'center',split:false, border:false">	
	<table id="customUserList" class="easyui-datagrid" style="width: 100%; height: 100%; padding: 0px;"
		data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                 nowrap:false,
	                pagination:true,
	                toolbar:'#toolbar',
	                striped:true,
	                queryParams : $('#queryForm').serializeObject(),
	                fit:true,
	                url:'${ctx}/shop/shopManage/listShopCustomUser.jhtml',
	                onLoadSuccess:loadSuccess,
	                pageSize:20">
	               
		<thead>
			<tr>
				<th field="photo" formatter="imageFormatter"   align="center">头像</th>
				<th field="custom_user_id" width="7%"    align="center">会员号</th>
				<th field="mobile" width="8%"   align="center">账号 </th>
				<th field="username" width="5%"   align="center">姓名</th>
				<th field="nikename" width="7%"   align="center">昵称</th>
				<th field="sex" width="4%" formatter="sexFormatter"   align="center">性别</th>
				<th field="born_date" width="8%" formatter="formatDateTime" align="center">生日</th>
				<th field="beauty_num" width="6%" align="center">持有颜值</th>
				<th field="bag_num"  formatter="bagFormatter" width="6%" formatter="formatDateTime" align="center">礼包</th>
				<th field="recent_time"    width="10%" align="center">最近到店时间</th>
				<th field="a" formatter="handleFunction"    width="15%" align="center">操作</th>
				
			</tr>
		</thead>
	</table>
	
    </div>
    </div>
   <div id="showCustomUserWindow" class="easyui-window" title="顾客详情信息"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:900px; height: 430px; background-color: #FFFFFF"></div>
   <div id="showCustomRecordWindow" class="easyui-window" title="消费情况"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true,cls:'theme-panel-orange'"
		style="width:1200px; height: 460px; background-color: #FFFFFF"></div>
    
</body>


