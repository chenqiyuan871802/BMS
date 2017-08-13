package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>数据字典索引表[sys_dictionary_index]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-10-02 22:26:06
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class DictionaryIndexPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水号
	 */
	private String dic_index_id;
	
	/**
	 * 字典标识
	 */
	private String dic_key;
	
	/**
	 * 字典名称
	 */
	private String dic_name;
	
	/**
	 * 所属分类流水号
	 */
	private String catalog_id;
	
	/**
	 * 所属分类流节点语义ID
	 */
	private String catalog_cascade_id;
	
	/**
	 * 备注
	 */
	private String dic_remark;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 创建用户编号
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
	 * 流水号
	 * 
	 * @return dic_index_id
	 */
	public String getDic_index_id() {
		return dic_index_id;
	}
	
	/**
	 * 字典标识
	 * 
	 * @return dic_key
	 */
	public String getDic_key() {
		return dic_key;
	}
	
	/**
	 * 字典名称
	 * 
	 * @return dic_name
	 */
	public String getDic_name() {
		return dic_name;
	}
	
	/**
	 * 所属分类流水号
	 * 
	 * @return catalog_id
	 */
	public String getCatalog_id() {
		return catalog_id;
	}
	
	/**
	 * 所属分类流节点语义ID
	 * 
	 * @return catalog_cascade_id
	 */
	public String getCatalog_cascade_id() {
		return catalog_cascade_id;
	}
	
	/**
	 * 备注
	 * 
	 * @return dic_remark
	 */
	public String getDic_remark() {
		return dic_remark;
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
	 * 创建用户编号
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
	 * 流水号
	 * 
	 * @param dic_index_id
	 */
	public void setDic_index_id(String dic_index_id) {
		this.dic_index_id = dic_index_id;
	}
	
	/**
	 * 字典标识
	 * 
	 * @param dic_key
	 */
	public void setDic_key(String dic_key) {
		this.dic_key = dic_key;
	}
	
	/**
	 * 字典名称
	 * 
	 * @param dic_name
	 */
	public void setDic_name(String dic_name) {
		this.dic_name = dic_name;
	}
	
	/**
	 * 所属分类流水号
	 * 
	 * @param catalog_id
	 */
	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}
	
	/**
	 * 所属分类流节点语义ID
	 * 
	 * @param catalog_cascade_id
	 */
	public void setCatalog_cascade_id(String catalog_cascade_id) {
		this.catalog_cascade_id = catalog_cascade_id;
	}
	
	/**
	 * 备注
	 * 
	 * @param dic_remark
	 */
	public void setDic_remark(String dic_remark) {
		this.dic_remark = dic_remark;
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
	 * 创建用户编号
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
	

}