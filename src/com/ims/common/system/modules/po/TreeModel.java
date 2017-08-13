package com.ims.common.system.modules.po;

import java.util.ArrayList;
import java.util.List;

import com.ims.common.core.asset.IMSCons;


/**
 * 
 * 类名:com.tunan.system.persistence.model.TreeModel
 * 描述:定义树模型
 * 编写者:陈骑元
 * 创建时间:2016年7月14日 下午3:18:29
 * 修改说明:
 */
public class TreeModel {
	/**
	 * 树模型ID
	 */
	private String id; 
	/**
	 * 父节点
	 */
	private String parentId;
	/**
	 * 语义ID
	 */
	private String cascade_id;
	
	/**
	 * 树节点名称
	 */
	private String text;
	/**
	 * 树节点图标
	 */
	private String  iconCls;
	/**
	 * 树节点打开或关闭状态
	 */
	private String state;
	/**
	 * 选中
	 */
	private String checked;
	/**
	 * 子节点
	 */
    private List<TreeModel> children=new ArrayList<TreeModel>();
    public void add(TreeModel treeModel){
    	if(IMSCons.TREE_ROOT_ID.equals(treeModel.getParentId())){
    		children.add( treeModel);
    	}else if(id.equals(treeModel.getParentId())){
    		children.add(treeModel);
    	}else{
    		for(TreeModel tmp_treeModel:children){
    			tmp_treeModel.add( treeModel);
    		}
    	}
    }
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<TreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}



	public String getCascade_id() {
		return cascade_id;
	}



	public void setCascade_id(String cascade_id) {
		this.cascade_id = cascade_id;
	}



	public String getChecked() {
		return checked;
	}



	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
