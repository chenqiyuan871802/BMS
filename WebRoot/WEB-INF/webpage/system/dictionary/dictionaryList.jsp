<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/webpage/common/taglibs.jsp"%>
<head>
<meta charset="UTF-8">
<IMS:codeStore fields="edit_mode,status"/>
<IMS:codeFormatter fields="edit_mode,status"/>
<script type="text/javascript">
 //树的单击查询
  function treeOnClickQuery(treeNode){
     $('#key_name').searchbox('setValue','');
      $('#dictionaryList').datagrid('loadData', { total: 0, rows: [] });//清空数据字典明细表
    var paramValue={};
    paramValue['catalog_cascade_id']=treeNode.cascade_id 
	 $('#dictionaryIndexList').datagrid({
	        queryParams : paramValue
	    });
  }
  //点击进行字典查询
  function doSearchIndex(value,name){
   var treeNode = $('#catalogTree').tree('getSelected');
   var cascade_id;
    if(treeNode){  //如果没有选中树节点则获取根节点ID
       cascade_id=treeNode.cascade_id;
    }else{
       var rootNode = $("#catalogTree").tree('getRoot');
       cascade_id=rootNode.cascade_id;
    }
    $('#dictionaryList').datagrid('loadData', { total: 0, rows: [] });//清空数据字典明细表
     var paramValue={};
      paramValue['catalog_cascade_id']=cascade_id;
      paramValue['key_name']=value;
       
      $('#dictionaryIndexList').datagrid({
	        queryParams : paramValue
	    });
       
  }
  //字典对照明细表查询
  function doSearch(value,name){
    var row= $('#dictionaryIndexList').datagrid('getSelected');
    if(row!=null){
      var paramValue={};
      paramValue['dic_index_id']=row.dic_index_id;
      paramValue['code_value']=value;
       
      $('#dictionaryList').datagrid({
            url:'${ctx}/system/dictionary/listDictionary.jhtml',
	        queryParams : paramValue
	    });
    }
   
  }
  function addDictionaryIndex(){
    var treeNode = $('#catalogTree').tree('getSelected');
    var catalog_id;
    if(treeNode){  //如果没有选中树节点则获取根节点ID
       catalog_id=treeNode.id;
    }else{
       var rootNode = $("#catalogTree").tree('getRoot');
       catalog_id=rootNode.id;
    }
    showWindow('addDictionaryIndexWindow','${ctx}/system/dictionary/goAddDictionaryIndex.jhtml?catalog_id='+catalog_id)
  }
 function queryDictionary(rowIndex,rowData){
 
   $('#code_value').searchbox('setValue','');
    var paramValue={};
    paramValue['dic_index_id']=rowData.dic_index_id; 
	 $('#dictionaryList').datagrid({
	         url:'${ctx}/system/dictionary/listDictionary.jhtml',
	        queryParams : paramValue
	    });
 
 }
 //提交字典数据并清空字典明细表
 function submitDictionIndexData(formId, windowId){
    $('#'+formId).form('submit', {
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data) {
					if (data.appcode=="1") {
						showMsg('提示', data.appmsg);
						 $('#dictionaryList').datagrid('loadData', { total: 0, rows: [] });//清空数据字典明细表
						 $('#dictionaryIndexList').datagrid({});  //刷新数据字典表
					     $('#'+windowId).window('close');
						
					}else if(data.appcode=="0"){
					   $.messager.alert('警告信息', data.appmsg, 'warning');
					}else {
						$.messager.alert('错误信息', data.appmsg, 'error');
					}
				} else {
					$.messager.alert('错误信息', '操作失败', 'error');
				}
			},
			onLoadeError:function(){
			         $.messager.alert('错误信息', '操作失败', 'error');
			}
		});
 
 }
 //修改字典对照表信息
 function modifyDictionary(){
     var row= $('#dictionaryList').datagrid('getSelected');
 		if(row!=null){
 		 var edit_mode=row.edit_mode;
 		 if(edit_mode=='1'){
 		   var dic_id=row.dic_id;
 		   showWindow('modifyDictionaryWindow','${ctx}/system/dictionary/goModifyDictionary.jhtml?dic_id='+dic_id)
 		 }else{
 		    $.messager.alert('警告信息', '你选择的字典对照数据编辑模式为只读，只读的数据不允许删除和修改', 'warning');
 		 }
 		
 		}else{
 			$.messager.alert('警告信息', '请选择你要字典对照数据信息', 'warning');
 		}
 }
 //删除字典对照表信息
  function deleteDictionary(){
     var rows = $('#dictionaryList').datagrid('getChecked');
 	var dic_ids = '';
 	for (var i = 0; i < rows.length; i++) {
 	       if(rows[i].edit_mode=='0'){
 	          $.messager.alert('警告信息', '你选择的字典对照数据存在编辑模式为只读的数据，只读的数据不允许删除和修改,请重新选择你要删除字典对照数据', 'warning');
 	          return;
 	       }
 			if(i==0){
 				dic_ids=rows[i].dic_id;
 			}else{
 				dic_ids+= ","+rows[i].dic_id ;
 			}
 	};
 	if(dic_ids!=''){
 	   $.messager.confirm('确认', '你确认要删除这些字典对照数据吗？',
 				function(r) {
 			    if(r){
 					$.ajax({
 						type : 'post',
 						url  :'${ctx}/system/dictionary/batchDeleteDictionary.jhtml',
 						data : {
						  'dic_ids' :dic_ids
						},
 						dataType : 'json',
 						success : function(data) {
 							if (data) {
 								if (data.appcode == "1") {
 									showMsg('提示', data.appmsg);
 									$('#dictionaryList').datagrid({});
 								} else if(data.appcode=="0"){
					                 $.messager.alert('警告信息', data.appmsg, 'warning');
					             }else {
 									$.messager.alert('错误信息',data.appmsg, 'error');
 								}
 							} else {
 								$.messager.alert('错误信息', '删除失败',
 										'error');
 							}
 						},
 						error : function() {
 							$.messager.alert('错误信息', '删除失败，网络连接超时',
 									'error');
 						}
 					})
 			    }
 				});
 	}else{
 	  $.messager.alert('警告信息','请选择你要删除的字典对照数据', 'warning');
 	}
  }
  //删除字典数据
  function deleteDictionaryIndex(){

 	 var row= $('#dictionaryIndexList').datagrid('getSelected');
 	 if(row!=null){
 		 var dic_index_id=row.dic_index_id;
 		 	$.messager.confirm('确认', '你确认要删除选择的字典数据吗？',
 				function(r) {
 			    if(r){
 					$.ajax({
 						type : 'post',
 						url  :'${ctx}/system/dictionary/deleteDictionaryIndex.jhtml',
 						data : {
						  'dic_index_id' :dic_index_id
						},
 						dataType : 'json',
 						success : function(data) {
 							if (data) {
 								if (data.appcode == "1") {
 									showMsg('提示', data.appmsg);
 									 $('#dictionaryList').datagrid('loadData', { total: 0, rows: [] });//清空数据字典明细表
						            $('#dictionaryIndexList').datagrid({});
	
 								} else if(data.appcode=="0"){
					                 $.messager.alert('警告信息', data.appmsg, 'warning');
					             }else {
 									$.messager.alert('错误信息',data.appmsg, 'error');
 								}
 							} else {
 								$.messager.alert('错误信息', '删除失败',
 										'error');
 							}
 						},
 						error : function() {
 							$.messager.alert('错误信息', '删除失败，网络连接超时',
 									'error');
 						}
 					})
 			    }
 				});
 	}else{
 		$.messager.alert('警告信息','请选择你要删除的字典数据', 'warning');
 	}
 	
 	
 }
</script>
</head>
<body style="margin: 0; padding: 0">
	<div class="easyui-layout"  data-options="fit:true">
		<div data-options="region:'west',split:false" title="字典分类科目" style="width: 210px;">
		 <div class="easyui-layout" data-options="fit:true">
				
				<div data-options="region:'center'">
					  <ul id="catalogTree" class="easyui-tree" data-options="url:'${ctx }/system/catalog/loadCatalogTree.jhtml?root_key=DIC_TYPE',method:'get',animate:true,lines:true,onClick:treeOnClickQuery"></ul>
				</div>
			</div>
		
		</div>
		<div data-options="region:'center'" >
					<table id="dictionaryIndexList" class="easyui-datagrid"
						style="width: 100%; height: 100%; padding: 0px;"
						data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:true,
	                autoRowHeight:false, 
	                striped:true,
	                toolbar:'#toolbar',
	                 url:'${ctx}/system/dictionary/listDictionaryIndex.jhtml',
	                 onClickRow:queryDictionary,
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="dic_index_id" hidden=“true”>字典索引编号</th>
								<th field="dic_key"  width="50%" align="center">字典标识</th>
								<th field="dic_name"  width="48%" align="center">字典名称</th>
							</tr>
						</thead>
					</table>
			
				<div id="toolbar" style="padding: 2px;">

					<a href="#" class="easyui-linkbutton" iconCls="add"
						plain="true"
						onclick="addDictionaryIndex();">新增</a>

					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="edit" plain="true"
						onclick="modifyGridData('modifyDictionaryIndexWindow','dictionaryIndexList','dic_index_id','${ctx}/system/dictionary/goModifyDictionaryIndex.jhtml','请选择你要修改的字典数据');">修改</a> <a href="#"
						class="easyui-linkbutton" iconCls="del" plain="true"
						onclick="deleteDictionaryIndex();">删除</a> 
                     <input class="easyui-searchbox" id="key_name"  data-options="prompt:'字典标识或名称',searcher:doSearchIndex" style="width:200px">
				</div>

			</div>
		<div data-options="region:'east',split:false"  style="width:550px;">
			
					<table id="dictionaryList" class="easyui-datagrid"
						style="width: 100%; height: 100%; padding: 0px;"
						data-options="
				     border:false,
	                rownumbers:true,
	                singleSelect:false,
	                autoRowHeight:false, 
	                striped:true,
	                toolbar:'#tb',
	                url:'',
	                fit:true,
	                pageSize:20">

						<thead>
							<tr>
								<th field="dic_id" hidden=“true”>字典明细编号</th>
								<th data-options="field:'ck',checkbox:true"></th>
								<th field="dic_code"  width="19%" align="center">字典对照码</th>
								<th field="dic_value"   width="19%" align="center">字典对照值</th>
								<th field="show_color" width="15%" align="center">显示颜色</th>
								<th field="sort_no" width="11%" align="center">排序号</th>
								<th field="status"  formatter="statusFormatter" width="14%" align="center">当前状态</th>
								<th field="edit_mode" formatter="edit_modeFormatter" width="14%" align="center">编辑模式</th>
								
							</tr>
						</thead>
					</table>
			
				

			</div>
		  <div id="tb" style="padding: 2px;">

					<a href="#" class="easyui-linkbutton" iconCls="add"
						plain="true"
						onclick="modifyGridData('addDictionaryWindow','dictionaryIndexList','dic_index_id','${ctx}/system/dictionary/goAddDictionary.jhtml','请先在数据字典列表上选择一条字典数据');">新增</a>

					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="edit" plain="true"
						onclick="modifyDictionary();">修改</a> <a href="#"
						class="easyui-linkbutton" iconCls="del" plain="true"
						onclick="deleteDictionary();">删除</a> 
                     <input class="easyui-searchbox" name="code_value" id="code_value"  data-options="prompt:'字典对照码或字典对照值',searcher:doSearch" style="width:200px">
				</div>
		  
		</div>
	
	<div id="addDictionaryIndexWindow" class="easyui-window" title="新增字典"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 300px; background-color: #FFFFFF"></div>
	
	<div id="addDictionaryWindow" class="easyui-window" title="新增字典对照"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 410px; background-color: #FFFFFF"></div>
	<div id="modifyDictionaryIndexWindow" class="easyui-window" title="修改字典"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 300px; background-color: #FFFFFF"></div>
	<div id="modifyDictionaryWindow" class="easyui-window" title="修改字典对照"
		data-options="collapsible:false,shadow:false,minimizable:false,maximizable:false,modal:true,closed:true"
		style="width: 500px; height: 410px; background-color: #FFFFFF"></div>
</body>


