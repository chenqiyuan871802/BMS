/**首页所需要的JS函数**/
/**
 * 首页显示日期时间函数
 * 
 */
function getDateTimeInfo() {
	var dateInfo;
	var tmpDate = new Date();
	var date = tmpDate.getDate();
	var month = tmpDate.getMonth() + 1;
	var year = tmpDate.getFullYear();
	var hours, minutes, seconds;
	var intHours, intMinutes, intSeconds;
	var today;
	var weekday;
	today = new Date();
	intHours = today.getHours();
	intMinutes = today.getMinutes();
	intSeconds = today.getSeconds();

	myArray = new Array(6);
	myArray[0] = "<font color='#CC3300'>星期日</font>";
	myArray[1] = "星期一";
	myArray[2] = "星期二";
	myArray[3] = "星期三";
	myArray[4] = "星期四";
	myArray[5] = "星期五";
	myArray[6] = "<font color='#CC3300'>星期六</font>";
	weekday = tmpDate.getDay();
	dateInfo = year + "-" + month + "-" + date + " " + myArray[weekday];

	weekday = tmpDate.getDay();

	if (intHours == 0) {
		hours = "00:";
	} else if (intHours < 12) {
		hours = intHours + ":";
	} else if (intHours == 12) {
		hours = "12:";
	} else {
		hours = intHours + ":";
	}
	if (intMinutes < 10) {
		minutes = "0" + intMinutes + ":";
	} else {
		minutes = intMinutes + ":";
	}
	if (intSeconds < 10) {
		seconds = "0" + intSeconds;
	} else {
		seconds = intSeconds;
	}
	window.setTimeout("getDateTimeInfo();", 100);

	// /return dateInfo + " " + (hours + minutes + seconds);

	var temp = document.getElementById('dateTimeView');
	temp.innerHTML = dateInfo + " " + (hours + minutes + seconds);
}
/** ******************************主页标签页函数开始******************************************************** */
/** 初始化左则菜单* */
function initLeftMenu() {
	$('.menuson li a').click(function() {
		var tabTitle = $(this).text();
		var url = $(this).attr("way");
		var iconCls = $(this).attr("iconCls");

		addTab(tabTitle, url, iconCls);

	})

}
/** *添加标签页函数* */
function addTab(title, url, iconCls) {

	if ($('#mainTabs').tabs('exists', title)) {
		$('#mainTabs').tabs('select', title);
	} else {
		$('#mainTabs').tabs('add', {
			title : title,
			iconCls : iconCls,
			content : createFrame(url),
			closable : true,
			width : $('#mainPanle').width() - 10,
			height : $('#mainPanle').height() - 26
		});

	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;" fit="true"></iframe>';
	return s;
}

/** 关闭标签页函数** */
function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children("span").text();
		$('#mainTabs').tabs('close', subtitle);
	})

	$(".tabs-inner").bind('contextmenu', function(e) {
		e.preventDefault();
		$('#mmRight').menu('show', {
			left : e.pageX,
			top : e.pageY
		})
		var subtitle = $(this).children("span").text();
		$('#mmRight').data("currtab", subtitle);

		return false;
	});
}
/** 绑定右键菜单事件* */
function tabCloseEven() {
	// 关闭当前标签页
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mmRight').data("currtab");
		$('#mainTabs').tabs('close', currtab_title);
	})
	// 关闭全部标签页
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#mainTabs').tabs('close', t);
		});
	});
	// 关闭其他标签页
	$('#mm-tabcloseother').click(function() {
		var currtab_title = $('#mmRight').data("currtab");
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (t != currtab_title)
				$('#mainTabs').tabs('close', t);
		});
	});
	// 关闭当前左侧全部标签页
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			$.messager.alert('警告信息', '当前标签页左则没有标签页了!', 'warning');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#mainTabs').tabs('close', t);
		});
		return false;
	});

	// 关闭当前右侧全部标签页
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			$.messager.alert('提示信息', '当前标签页右则没有标签页了!', 'warning');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#mainTabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mmRight').menu('hide');
	})
}
/** ******************************主页标签页函数结束******************************************************** */
/** 将FORM数据转化JSON对象* */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}
/** 公用函数* */
var IMSUtils = {
	// 判断参数是为空
	isEmpty : function(v) {
		switch (typeof v) {
		case 'undefined':
			return true;
		case 'string':
			if (trim(v).length == 0)
				return true;
			break;
		case 'boolean':
			if (!v)
				return true;
			break;
		case 'number':
			if (0 === v)
				return true;
			break;
		case 'object':
			if (null === v)
				return true;
			if (undefined !== v.length && v.length == 0)
				return true;
			for ( var k in v) {
				return false;
			}
			return true;
			break;
		}
		return false;
	},
	// 判断参数不为空
	isNotEmpty : function(v) {
		switch (typeof v) {
		case 'undefined':
			return false;
		case 'string':
			if (trim(v).length == 0)
				return false;
			break;
		case 'boolean':
			if (!v)
				return false;
			break;
		case 'number':
			if (0 === v)
				return false;
			break;
		case 'object':
			if (null === v)
				return false;
			if (undefined !== v.length && v.length == 0)
				return false;
			for ( var k in v) {
				return true;
			}
			return false;
			break;
		}
		return true;
	}

}
/** 去除字符串空格* */
function trim(str) {

	return str.replace(/(^\s*)|(\s*$)/g, '');
}
/** 获取DataGrid选择的值，根据所提供的键获取* */
function getRowValues(rows, rowKey) {
	var rowValues = "";
	for (var i = 0; i < rows.length; i++) {
		if (i == 0) {
			rowValues = rows[i]['' + rowKey];
		} else {
			rowValues += "," + rows[i]['' + rowKey];
		}
	}
	;
	return rowValues;

}

/** 信息弹出框***** */
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
/**
 * 增加列显示title
 * 
 * @param value
 * @returns {String}
 */
function formatCellTooltip(value) {

	if (IMSUtils.isNotEmpty(value)) {

		return "<span title='" + value + "'>" + value + "</span>";
	} else {
		return '';
	}
}
/**
 * 打开一个窗口 windowId 窗口ID url 地址 resize 窗口变化发生触发函数 title 窗口标题
 * 
 */
function showWindow(windowId, url, resize, title) {
	$('#' + windowId).window('open');
	var relativeTop = $(document).scrollTop() + 10;
	$('#' + windowId).panel('move', {
		top : relativeTop
	});
	if (IMSUtils.isNotEmpty(resize)) {
		$('#' + windowId).window('resize', resize);
	}
	if (IMSUtils.isNotEmpty(url)) {

		$('#' + windowId).window('refresh', url);
	}
	if (IMSUtils.isNotEmpty(title)) {
		$('#' + windowId).window('setTitle', title);
	}
}
/** 关闭窗口* */
function closeWindow(windowId) {
	$('#' + windowId).window('close');
}

/** 展示提示消息* */
function showMsg(title, msg, newConf) {
	var oldConf = {
		title : title,
		msg : "<span style='width:100%;text-align:center;'>" + msg + "</span>",
		icon : 'info',
		timeout : 1000,
		showType : 'slide',
		style : {
			right : '',
			top : document.body.scrollTop + document.documentElement.scrollTop,
			bottom : ''
		}
	};
	var config = $.extend(oldConf, newConf);
	$.messager.show(config);
}
/**
 * 查看表格数据函数 windowId Window窗口ID grid DataGrid表格ID rowKey 行主键ID url url地址 warnMsg
 * 警告信息
 */
function showGridData(windowId, gridId, rowKey, url, warnMsg) {
	if (IMSUtils.isEmpty(windowId)) {
		$.messager.alert('错误信息', '缺少Window窗口ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(gridId)) {
		$.messager.alert('错误信息', '缺少DataGrid表格ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(rowKey)) {
		$.messager.alert('错误信息', '缺少数据主键ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(url)) {
		$.messager.alert('错误信息', '缺少URL地址', 'error');
		return;
	}
	if (IMSUtils.isEmpty(warnMsg)) {
		warnMsg = "请选择你要查看数据";
	}
	var row = $('#' + gridId).datagrid('getSelected');
	if (row != null) {
		var rowValue = row['' + rowKey];
		if (url.indexOf("?") != -1) {
			url += "&" + rowKey + "=" + rowValue;
		} else {
			url += "?" + rowKey + "=" + rowValue;
		}
		showWindow(windowId, url);
	} else {
		$.messager.alert('警告信息', warnMsg, 'warning');
	}

}
/**
 * 查看多项表格数据函数 windowId Window窗口ID grid DataGrid表格ID rowKey 行主键ID url url地址
 * warnMsg 警告信息
 */
function showMoreGridData(windowId, gridId, rowKey, url, warnMsg) {
	if (IMSUtils.isEmpty(windowId)) {
		$.messager.alert('错误信息', '缺少Window窗口ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(gridId)) {
		$.messager.alert('错误信息', '缺少DataGrid表格ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(rowKey)) {
		$.messager.alert('错误信息', '缺少数据主键ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(url)) {
		$.messager.alert('错误信息', '缺少URL地址', 'error');
		return;
	}
	if (IMSUtils.isEmpty(warnMsg)) {
		warnMsg = "请选择你要处理数据";
	}
	var rows = $('#' + gridId).datagrid('getChecked');
	var rowValues = "";
	for (var i = 0; i < rows.length; i++) {

		if (i == 0) {
			rowValues = rows[i]['' + rowKey];
		} else {
			rowValues += "," + rows[i]['' + rowKey];
		}
	}
	;

	if (IMSUtils.isNotEmpty(rowValues)) {

		if (url.indexOf("?") != -1) {
			url += "&" + rowKey + "s=" + rowValues;
		} else {
			url += "?" + rowKey + "s=" + rowValues;
		}
		showWindow(windowId, url);
	} else {
		$.messager.alert('警告信息', warnMsg, 'warning');
	}

}
/**
 * 修改表格数据函数 windowId Window窗口ID grid DataGrid表格ID rowKey 行主键ID url url地址 warnMsg
 * 警告信息
 */
function modifyGridData(windowId, gridId, rowKey, url, warnMsg) {
	if (IMSUtils.isEmpty(windowId)) {
		$.messager.alert('错误信息', '缺少Window窗口ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(gridId)) {
		$.messager.alert('错误信息', '缺少DataGrid表格ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(rowKey)) {
		$.messager.alert('错误信息', '缺少数据主键ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(url)) {
		$.messager.alert('错误信息', '缺少URL地址', 'error');
		return;
	}
	if (IMSUtils.isEmpty(warnMsg)) {
		warnMsg = "请选择你要修改的数据";
	}
	var row = $('#' + gridId).datagrid('getSelected');
	if (row != null) {
		var rowValue = row['' + rowKey];
		if (url.indexOf("?") != -1) {
			url += "&" + rowKey + "=" + rowValue;
		} else {
			url += "?" + rowKey + "=" + rowValue;
		}
		showWindow(windowId, url);
	} else {
		$.messager.alert('警告信息', warnMsg, 'warning');
	}

}
/**
 * 删除表格数据函数 单条数据 grid DataGrid表格ID rowKey 行主键ID url url地址 confirmMsg确认信息 warnMsg
 * 警告信息 controlType 1代表树表格其他为数据表格
 */
function deleteGridData(gridId, rowKey, url, warnMsg, confirmMsg, controlType) {

	if (IMSUtils.isEmpty(gridId)) {
		$.messager.alert('错误信息', '缺少DataGrid表格ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(rowKey)) {
		$.messager.alert('错误信息', '缺少数据主键ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(url)) {
		$.messager.alert('错误信息', '缺少URL地址', 'error');
		return;
	}
	if (IMSUtils.isEmpty(confirmMsg)) {
		confirmMsg = "你确认要删除选择的数据吗？";
	}
	if (IMSUtils.isEmpty(warnMsg)) {
		warnMsg = "请选择你要删除的数据";
	}
	var row = $('#' + gridId).datagrid('getSelected');
	if (row != null) {
		var rowValue = row['' + rowKey];
		if (url.indexOf("?") != -1) {
			url += "&" + rowKey + "=" + rowValue;
		} else {
			url += "?" + rowKey + "=" + rowValue;
		}
		$.messager.confirm('确认', confirmMsg, function(r) {
			if (r) {
				$.messager.progress({
					title : '信息操作',
					text : '数据正在保存中，请耐心等待...'
				});
				$.ajax({
					type : 'post',
					url : url,
					dataType : 'json',
					success : function(data) {
						$.messager.progress('close');
						if (data) {
							if (data.appcode == "1") {
								showMsg('提示', data.appmsg);
								if (controlType == '1') {
									$('#' + gridId).treegrid({});
								} else {
									$('#' + gridId).datagrid({});
								}

							} else if (data.appcode == "0") {
								$.messager
										.alert('警告信息', data.appmsg, 'warning');
							} else {
								$.messager.alert('错误信息', data.appmsg, 'error');
							}
						} else {
							$.messager.alert('错误信息', '操作失败', 'error');
						}
					},
					error : function() {
						$.messager.progress('close');
						$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
					}
				})
			}
		});
	} else {
		$.messager.alert('警告信息', warnMsg, 'warning');
	}

}
/**
 * 批量删除表格数据函数 grid DataGrid表格ID rowKey 行主键ID url url地址 confirmMsg确认信息 warnMsg
 * 警告信息
 * 
 */
function batchDeleteGridData(gridId, rowKey, url, warnMsg, confirmMsg) {

	if (IMSUtils.isEmpty(gridId)) {
		$.messager.alert('错误信息', '缺少DataGrid表格ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(rowKey)) {
		$.messager.alert('错误信息', '缺少数据主键ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(url)) {
		$.messager.alert('错误信息', '缺少URL地址', 'error');
		return;
	}
	if (IMSUtils.isEmpty(confirmMsg)) {
		confirmMsg = "你确认要删除选择的数据吗？";
	}
	if (IMSUtils.isEmpty(warnMsg)) {
		warnMsg = "请选择你要删除的数据";
	}
	var rows = $('#' + gridId).datagrid('getChecked');
	var rowValues = "";
	for (var i = 0; i < rows.length; i++) {

		if (i == 0) {
			rowValues = rows[i]['' + rowKey];
		} else {
			rowValues += "," + rows[i]['' + rowKey];
		}
	}
	;
	var paramValue = {};
	paramValue[rowKey + 's'] = rowValues
	if (rowValues != '') {
		$.messager.confirm('确认', confirmMsg, function(r) {
			if (r) {
				$.messager.progress({
					title : '信息操作',
					text : '数据正在保存中，请耐心等待...'
				});
				$.ajax({
					type : 'post',
					url : url,
					data : paramValue,
					dataType : 'json',
					success : function(data) {
						$.messager.progress('close');
						if (data) {
							if (data.appcode == "1") {
								showMsg('提示', data.appmsg);
								$('#' + gridId).datagrid({});
							} else if (data.appcode == "0") {
								$.messager
										.alert('警告信息', data.appmsg, 'warning');
							} else {
								$.messager.alert('错误信息', data.appmsg, 'error');
							}
						} else {
							$.messager.alert('错误信息', '操作失败', 'error');
						}
					},
					error : function() {
						$.messager.progress('close');
						$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
					}
				})
			}
		});
	} else {
		$.messager.alert('警告信息', warnMsg, 'warning');
	}

}
/**
 * 操作表格数据函数 只作用单条 grid DataGrid表格ID rowKey 行主键ID url url地址 warnMsg 警告信息
 * confirmMsg确认信息
 */
function operateGridData(gridId, rowKey, url, warnMsg, confirmMsg) {
	if (IMSUtils.isEmpty(gridId)) {
		$.messager.alert('错误信息', '缺少DataGrid表格ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(rowKey)) {
		$.messager.alert('错误信息', '缺少数据主键ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(url)) {
		$.messager.alert('错误信息', '缺少URL地址', 'error');
		return;
	}

	if (IMSUtils.isEmpty(warnMsg)) {
		warnMsg = "请选择你要处理的数据";
	}
	var row = $('#' + gridId).datagrid('getSelected');
	if (row != null) {
		var rowValue = row['' + rowKey];
		if (url.indexOf("?") != -1) {
			url += "&" + rowKey + "=" + rowValue;
		} else {
			url += "?" + rowKey + "=" + rowValue;
		}
		if (IMSUtils.isNotEmpty(confirmMsg)) {
			$.messager.confirm('确认', confirmMsg, function(r) {
				if (r) {
					$.messager.progress({
						title : '信息操作',
						text : '数据正在保存中，请耐心等待...'
					});
					$.ajax({
						type : 'post',
						url : url,
						dataType : 'json',
						success : function(data) {
							$.messager.progress('close');
							if (data) {
								if (data.appcode == "1") {
									showMsg('提示', data.appmsg);
									$('#' + gridId).datagrid({});
								} else if (data.appcode == "0") {
									$.messager.alert('警告信息', data.appmsg,
											'warning');
								} else {
									$.messager.alert('错误信息', data.appmsg,
											'error');
								}
							} else {
								$.messager.alert('错误信息', '操作失败', 'error');
							}
						},
						error : function() {
							$.messager.progress('close');
							$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
						}
					})
				}
			});
		} else {
			$.messager.progress({
				title : '信息操作',
				text : '数据正在保存中，请耐心等待...'
			});
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'json',
				success : function(data) {
					$.messager.progress('close');
					if (data) {
						if (data.appcode == "1") {
							showMsg('提示', data.appmsg);
							$('#' + gridId).datagrid({});
						} else if (data.appcode == "0") {
							$.messager.alert('警告信息', data.appmsg, 'warning');
						} else {
							$.messager.alert('错误信息', data.appmsg, 'error');
						}
					} else {
						$.messager.alert('错误信息', '操作失败', 'error');
					}
				},
				error : function() {
					$.messager.progress('close');
					$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
				}
			})
		}

	} else {
		$.messager.alert('警告信息', warnMsg, 'warning');
	}

}
/**
 * ajax接口
 * @param url
 * @param paramData
 * @param confirmMsg
 */
function doAjax(url, paramData, confirmMsg) {
	if (IMSUtils.isEmpty(url)) {
		$.messager.alert('错误信息', '缺少URL地址', 'error');
		return;
	}
	if (IMSUtils.isNotEmpty(confirmMsg)) {
		$.messager.confirm('确认', confirmMsg, function(r) {
			if (r) {
				$.messager.progress({
					title : '信息操作',
					text : '数据正在保存中，请耐心等待...'
				});
				$.ajax({
					type : 'post',
					url : url,
					data : paramData,
					dataType : 'json',
					success : function(data) {
						$.messager.progress('close');
						if (data) {
							if (data.appcode == "1") {
								showMsg('提示', data.appmsg);
								if (IMSUtils.isNotEmpty(gridId)) {

									$('#' + gridId).datagrid({});
								}

							} else if (data.appcode == "0") {
								$.messager
										.alert('警告信息', data.appmsg, 'warning');
							} else {
								$.messager.alert('错误信息', data.appmsg, 'error');
							}
						} else {
							$.messager.alert('错误信息', '操作失败', 'error');
						}
					},
					error : function() {
						$.messager.progress('close');
						$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
					}
				})
			}
		});
	} else {
		$.messager.progress({
			title : '信息操作',
			text : '数据正在保存中，请耐心等待...'
		});
		$.ajax({
			type : 'post',
			url : url,
			data : paramData,
			dataType : 'json',
			success : function(data) {
				$.messager.progress('close');
				if (data) {
					if (data.appcode == "1") {
						showMsg('提示', data.appmsg);
						if (IMSUtils.isNotEmpty(gridId)) {

							$('#' + gridId).datagrid({});
						}
					} else if (data.appcode == "0") {
						$.messager.alert('警告信息', data.appmsg, 'warning');
					} else {
						$.messager.alert('错误信息', data.appmsg, 'error');
					}
				} else {
					$.messager.alert('错误信息', '操作失败', 'error');
				}
			},
			error : function() {
				$.messager.progress('close');
				$.messager.alert('错误信息', '操作失败，网络连接超时', 'error');
			}
		})
	}

}

/**
 * DataGrid列表查询数据 gridId DataGrid表格ID queryFromId 查询表单ID
 */
function doQuery(gridId, queryFromId) {
	$('#' + gridId).datagrid('options').pageNumber = 1;// 设置页码初始值为1
	$('#' + gridId).datagrid({
		queryParams : $('#' + queryFromId).serializeObject()
	});
}
/**
 * 提交表单数据并刷新表格 formId 表单ID gridId 如果controllerType=1 树网格ID windowId 窗口ID
 * controllerType 等于1 树网格,其他
 * 
 */
function submitFormData(formId, gridId, windowId, controlType) {
	if (IMSUtils.isEmpty(formId)) {
		$.messager.alert('错误信息', '缺少form表单ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(gridId)) {
		$.messager.alert('错误信息', '缺少DataGrid表格ID', 'error');
		return;
	}
	if (IMSUtils.isEmpty(windowId)) {
		$.messager.alert('错误信息', '缺少Window窗口ID', 'error');
		return;
	}
	$.messager.progress({
		title : '信息操作',
		text : '数据正在保存中，请耐心等待...'
	});
	$('#' + formId).form('submit', {
		onSubmit : function(param) {
			var result = $(this).form('enableValidation').form('validate');
			if (!result) {
				$.messager.progress('close');
			}
			return result;
		},
		success : function(data) {
			$.messager.progress('close');
			var data = eval('(' + data + ')');
			if (data) {
				if (data.appcode == "1") {
					showMsg('提示', data.appmsg);

					if (controlType == '1') {

						$('#' + gridId).treegrid({});
					} else {
						$('#' + gridId).datagrid({});
					}
					$('#' + windowId).window('close');

				} else if (data.appcode == "0") {
					$.messager.alert('警告信息', data.appmsg, 'warning');
				} else {
					$.messager.alert('错误信息', data.appmsg, 'error');
				}
			} else {
				$.messager.alert('错误信息', '操作失败', 'error');
			}
		},
		onLoadeError : function() {
			$.messager.progress('close');
			$.messager.alert('错误信息', '操作失败', 'error');
		}
	});

}
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}
/**
 * 根式时间为日期的格式yyyy-MM-dd
 * 
 * @param value
 * @returns
 */
function formatDateTime(value) {
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
	}

	return dt.format("yyyy-MM-dd"); // 扩展的Date的format方法(上述插件实现)
}


