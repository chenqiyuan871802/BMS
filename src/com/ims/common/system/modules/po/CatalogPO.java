package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>分类科目[sys_catalog]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-25 09:25:54
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class CatalogPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 分类科目编号
	 */
	private String catalog_id;
	
	/**
	 * 分类科目语义ID
	 */
	private String cascade_id;
	
	/**
	 * 科目标识键
	 */
	private String root_key;
	
	/**
	 * 科目名称
	 */
	private String root_name;
	
	/**
	 * 分类名称
	 */
	private String catalog_name;
	
	/**
	 * 父节点编号
	 */
	private String parent_id;
	
	/**
	 * 排序号
	 */
	private Integer sort_no;
	
	/**
	 * 图标名称
	 */
	private String icon_name;
	
	/**
	 * 是否自动展开
	 */
	private String is_auto_expand;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * create_user_id
	 */
	private String create_user_id;
	
	/**
	 * 修改时间
	 */
	private Date modify_time;
	
	/**
	 * 修改用户ID
	 */
	private String modify_user_id;
	/**
	 * 子节点个数
	 */
    private Integer child_count;
	/**
	 * 分类科目编号
	 * 
	 * @return catalog_id
	 */
	public String getCatalog_id() {
		return catalog_id;
	}
	
	/**
	 * 分类科目语义ID
	 * 
	 * @return cascade_id
	 */
	public String getCascade_id() {
		return cascade_id;
	}
	
	/**
	 * 科目标识键
	 * 
	 * @return root_key
	 */
	public String getRoot_key() {
		return root_key;
	}
	
	/**
	 * 科目名称
	 * 
	 * @return root_name
	 */
	public String getRoot_name() {
		return root_name;
	}
	
	/**
	 * 分类名称
	 * 
	 * @return catalog_name
	 */
	public String getCatalog_name() {
		return catalog_name;
	}
	
	/**
	 * 父节点编号
	 * 
	 * @return parent_id
	 */
	public String getParent_id() {
		return parent_id;
	}
	
	/**
	 * 排序号
	 * 
	 * @return sort_no
	 */
	public Integer getSort_no() {
		return sort_no;
	}
	
	/**
	 * 图标名称
	 * 
	 * @return icon_name
	 */
	public String getIcon_name() {
		return icon_name;
	}
	
	/**
	 * 是否自动展开
	 * 
	 * @return is_auto_expand
	 */
	public String getIs_auto_expand() {
		return is_auto_expand;
	}
	
	/**
	 * 创建时间
	 * 
	 * @return create_time
	 */
	
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * create_user_id
	 * 
	 * @return create_user_id
	 */
	public String getCreate_user_id() {
		return create_user_id;
	}
	
	/**
	 * 修改时间
	 * 
	 * @return modify_time
	 */
	public Date getModify_time() {
		return modify_time;
	}
	
	/**
	 * 修改用户ID
	 * 
	 * @return modify_user_id
	 */
	public String getModify_user_id() {
		return modify_user_id;
	}
	

	/**
	 * 分类科目编号
	 * 
	 * @param catalog_id
	 */
	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}
	
	/**
	 * 分类科目语义ID
	 * 
	 * @param cascade_id
	 */
	public void setCascade_id(String cascade_id) {
		this.cascade_id = cascade_id;
	}
	
	/**
	 * 科目标识键
	 * 
	 * @param root_key
	 */
	public void setRoot_key(String root_key) {
		this.root_key = root_key;
	}
	
	/**
	 * 科目名称
	 * 
	 * @param root_name
	 */
	public void setRoot_name(String root_name) {
		this.root_name = root_name;
	}
	
	/**
	 * 分类名称
	 * 
	 * @param catalog_name
	 */
	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}
	
	/**
	 * 父节点编号
	 * 
	 * @param parent_id
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 排序号
	 * 
	 * @param sort_no
	 */
	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}
	
	/**
	 * 图标名称
	 * 
	 * @param icon_name
	 */
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	
	/**
	 * 是否自动展开
	 * 
	 * @param is_auto_expand
	 */
	public void setIs_auto_expand(String is_auto_expand) {
		this.is_auto_expand = is_auto_expand;
	}
	
	/**
	 * 创建时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * create_user_id
	 * 
	 * @param create_user_id
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	
	/**
	 * 修改时间
	 * 
	 * @param modify_time
	 */
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	
	/**
	 * 修改用户ID
	 * 
	 * @param modify_user_id
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	public Integer getChild_count() {
		return child_count;
	}

	public void setChild_count(Integer child_count) {
		this.child_count = child_count;
	}
	

}