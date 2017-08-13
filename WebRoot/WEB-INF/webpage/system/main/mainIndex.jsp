<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>IMS信息系统综合平台</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/common/css/workMain.css">
	<script type="text/javascript">
		$(function() {

			//我的待办点击事件
			$(document).on('click', '.work-item.green', function(event) {
				var width = (2 * $(this).width()) + 10;
				$(".todo-panel").width(width - 2).css({
					top : $(this).offset().top,
					left : $(this).offset().left
				}).show();
				event.stopPropagation();
			});
			$(".todo-panel").click(function() {
				event.stopPropagation();
			});
			$(document).click(function() {
				$(".todo-panel").hide();
			});

			//公开附件tab事件处理
			$(".attached-tab").on(
					"click",
					"a",
					function() {
						$(this).closest(".attached-tab").find("a").removeClass(
								"current");
						$(this).addClass("current");
						$(this).closest(".attached").find("ul")
								.addClass("hide");
						$(this).closest(".attached").find(
								"ul." + $(this).attr("attached")).removeClass(
								"hide");
					})
			loadPieChart();
			loadLineChart();
			loadColumnChart();
		});

		function loadLineChart() {
			$('#chart3').highcharts(
					{
						chart : {
							backgroundColor : '#ffffff',
							borderColor : '#ffffff',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title : {
							text : '',
							x : -20
						//center
						},
						tooltip : {
							shared : true,
							crosshairs : true
						},
						exporting : {
							enabled : false
						},
						xAxis : {
							categories : [ '第一周', '第二周', '第三周', '第四周', '第五周',
									'第六周', '第七周' ]
						},
						yAxis : {
							title : {
								text : '总量'
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ],

						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'middle',
							borderWidth : 0
						},
						series : [ {
							name : '邮件营销',
							data : [ 120, 132, 101, 134, 90, 230, 210 ]
						}, {
							name : '联盟广告',
							data : [ 220, 182, 191, 234, 290, 330, 310 ]
						}, {
							name : '视频广告',
							data : [ 150, 232, 201, 154, 190, 330, 410 ]
						}, {
							name : '直接访问',
							data : [ 320, 332, 301, 334, 390, 330, 320 ]
						}, {
							name : '搜索引擎',
							data : [ 820, 932, 901, 934, 1290, 1330, 1320 ]
						} ]
					});
		}
		function loadPieChart() {
			$('#chart0')
					.highcharts(
							{
								chart : {
									backgroundColor : '#ffffff',
									borderColor : '#ffffff',
									plotBackgroundColor : null,
									plotBorderWidth : null,
									plotShadow : false
								},
								exporting : {
									enabled : false
								},
								title : {
									text : ''
								},
								tooltip : {
									pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
								},
								plotOptions : {
									pie : {

										allowPointSelect : true,
										cursor : 'pointer',
										dataLabels : {
											enabled : true,
											format : '<b>{point.name}</b>: {point.percentage:.1f} %',
											style : {
												color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
														|| 'black'
											}
										}
									}
								},
								series : [ {
									type : 'pie',
									name : 'Browser share',
									data : [ [ 'Firefox', 45.0 ],
											[ 'IE', 26.8 ], {
												name : 'Chrome',
												y : 12.8,
												sliced : true,
												selected : true
											}, [ 'Safari', 8.5 ],
											[ 'Opera', 6.2 ], [ 'Others', 0.7 ] ]
								} ]
							});
		}
		function loadColumnChart() {
			$('#chart1').highcharts(
					{
						chart : {
							type : 'column',
							backgroundColor : '#ffffff',
							borderColor : '#ffffff',
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						exporting : {
							enabled : false
						},
						title : {
							text : ''
						},
						legend: {
				            enabled: false
				        },
						xAxis : {
							categories : [ '采购组织', '供应商', '新物料', 'uimaker',
									'信息管理', '业务系统', '采购商' ],
							crosshair : true
						},
						yAxis : {
							min : 0,
							plotLines : [ { //一条延伸到整个绘图区的线，标志着轴中一个特定值。
								color : 'red',
								dashStyle : 'Dash', //Dash,Dot,Solid,默认Solid
								width : 1.5,
								value : 43.3, //y轴显示位置
								zIndex : 5
							} ],
							title : {
								text : '',
							}

						},

						plotOptions : {
							column : {
								pointPadding : 0.2,
								borderWidth : 0,
								dataLabels : {
									enabled : true
								}
							},
							
						},
						series : [ {
							name : '蒸发量',
							data : [ 60, 45, 73, 23, 37, 48, 18 ]
						} ]
					});
		}
	</script>
</head>
<div class="container">
	<div id="hd"></div>

	<div id="workMain">
		<div class="workMain-content">
			<div class="right-zone">
				<div class="inform item-box">
					<div class="inform-hd">
						<label>通知公告</label> <a href="javascript:;">更多<span>&gt;</span></a>
					</div>
					<ul>
						<li><span></span> <a href="javascript:;" class="ellipsis">uimaker信息管理<i></i></a>
							<label>04-13</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">光电获土耳其最大固网<i></i></a>
							<label>04-12</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">2015中国线缆行业最具竞争</a>
							<label>04-11</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">2016年铝包钢绞线2016管道保温棉询</a>
							<label>04-10</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">力缆（特种导线）再次温棉询</a>
							<label>04-09</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">光电获土耳其最大固网</a>
							<label>04-08</label></li>
					</ul>
				</div>
				<div class="price item-box">
					<div class="inform-hd">
						<label>公开竞价</label> <a href="javascript:;">更多<span>&gt;</span></a>
					</div>
					<ul>
						<li><span></span> <a href="javascript:;" class="ellipsis">界面设计后台界面UI作品</a>
							<label>04-13</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">换肤设置-材料在线下单选购</a>
							<label>04-12</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">2016最新软件界面设计欣赏</a>
							<label>04-11</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">作者咨询QQ：32534386</a>
							<label>04-10</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">数据库备份/还原设置修复</a>
							<label>04-09</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">自定义文档属性基本参数设置</a>
							<label>04-08</label></li>
						<li><span></span> <a href="javascript:;" class="ellipsis">配货方式选择会员产品分类</a>
							<label>04-07</label></li>
					</ul>
				</div>
				<div class="attached item-box">
					<div class="inform-hd">
						<label>常用附件下载</label> <a href="javascript:;">更多<span>&gt;</span></a>
					</div>
					<div class="attached-tab">
						<a href="javascript:;" class="current item-left"
							attached="public-attached">公开附件</a> <a href="javascript:;"
							class="item-right" attached="inner-attached">内部附件</a>
					</div>
					<ul class="public-attached">
						<li><span></span> <a href="javascript:;" class="ellipsis">界面设计作品PSD源文件免费下载</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">uimaker版权所有禁止转载发布</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">意见建议反馈内容模版</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">系统错误修复文档下载分布</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">采集信息管理系统后台界面</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">用户管理信息文件同步</a>
						</li>
					</ul>
					<ul class="inner-attached hide">
						<li><span></span> <a href="javascript:;" class="ellipsis">意见建议反馈内容模版</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">这里显示的不同内容</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">界面设计作品PSD源文件免费下载</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">系统错误修复文档下载分布</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">采集信息管理系统后台界面</a>
						</li>
						<li><span></span> <a href="javascript:;" class="ellipsis">用户管理信息文件同步</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="center-part">
				<div class="center-items todo">
					<div class="calendar-part">
						<div class="easyui-calendar" style="width: 205px; height: 231px;"></div>
					</div>
					<ul class="work-items clearfix">
						<li>
							<div class="work-inner">
								<div class="work-item green">
									<i class="iconfont">&#xe61f;</i> <span class="num">14<span
										class="unit">个</span></span> <label>待办未处理</label>
								</div>
							</div>
						</li>
						<li>
							<div class="work-inner">
								<div class="work-item red">
									<i class="iconfont">&#xe622;</i> <span class="num">6<span
										class="unit">条</span></span> <label>预警信息未读</label>
								</div>
							</div>
						</li>
						<li>
							<div class="work-inner">
								<div class="work-item yellow">
									<i class="iconfont">&#xe61d;</i> <span class="num">9<span
										class="unit">封</span></span> <label>邮件未读</label>
								</div>
							</div>
						</li>
						<li>
							<div class="work-inner">
								<div class="work-item blue">
									<i class="iconfont">&#xe621;</i> <span title="2000,00万"
										class="num">2000,00<span class="unit">万</span></span> <label>我的询价金额</label>
								</div>
							</div>
						</li>
						<li>
							<div class="work-inner">
								<div class="work-item purple">
									<i class="iconfont">&#xe61e;</i> <span title="2000,00万"
										class="num">100,00<span class="unit">万</span></span> <label>已完成的合同金额</label>
								</div>
							</div>
						</li>
						<li>
							<div class="work-inner">
								<div class="work-item gray">
									<i class="iconfont">&#xe620;</i> <span class="num">10<span
										class="unit">个</span></span> <label>供应商开发</label>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="center-items chart0 clearfix">
					<div class="chart0-item">
						<div class="item-inner">
							<div class="item-content">
								<div class="content-hd">浏览器市场份额</div>
								<div class="chart-chart" id="chart0"></div>
							</div>
						</div>
					</div>
					<div class="chart0-item">
						<div class="item-inner">
							<div class="item-content">
								<div class="content-hd">询价降本率</div>
								<div class="chart-chart" id="chart1"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="center-items chart1">
					<div class="chart1-inner">
						<div class="item-hd">交货准确率</div>
						<div class="chart1-chart" id="chart3"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="ft"></div>
</div>
<div class="todo-panel">
	<div class="todo-title">
		<i class="iconfont">&#xe61f;</i> <span class="num">14&nbsp;<span
			class="unit">个</span></span> <label>待办未处理</label>
	</div>
	<div class="todo-items">
		<ul>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理
			</a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>10条</span>供应商开发申请未处理<i></i></a></a>
				<label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>0条</span>供应商开发申请未处理，请及时审批<i></i></a></a>
				<label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>1条</span>供应商开发申请未处理，请及时审批
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>4条</span>供应商开发申请未处理，请及时审批
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>6条</span>供应商开发申请未处理，请及时审批，未处理会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，未处理会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>开发申请未处理，请及时审批，未处理会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，会导致失效
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批，未处理
			</a></a> <label>04-13</label></li>
			<li><span></span> <a href="javascript:;" class="ellipsis">您有<span>2条</span>供应商开发申请未处理，请及时审批
			</a></a> <label>04-13</label></li>
		</ul>
	</div>

</div>
</body>
</html>