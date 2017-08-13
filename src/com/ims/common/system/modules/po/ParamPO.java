package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>键值参数[sys_param]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-25 10:21:40
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class ParamPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 参数编号
	 */
	private String param_id;
	
	/**
	 * 参数名称
	 */
	private String param_name;
	
	/**
	 * 参数键名
	 */
	private String param_key;
	
	/**
	 * 参数键值
	 */
	private String param_value;
	
	/**
	 * 目录ID
	 */
	private String catalog_id;
	/**
	 * 所属科目
	 */
	private String catalog_name;
	
	/**
	 * 分类科目语义ID
	 */
	private String catalog_cascade_id;
	
	/**
	 * 参数备注
	 */
	private String param_remark;
	
	/**
	 * 当前状态(0:停用;1:启用)
	 */
	private String status;
	
	/**
	 * 编辑模式(0:只读;1:可编辑)
	 */
	private String edit_mode;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 创建用户ID
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
	 * 参数编号
	 * 
	 * @return param_id
	 */
	public String getParam_id() {
		return param_id;
	}
	
	/**
	 * 参数名称
	 * 
	 * @return param_name
	 */
	public String getParam_name() {
		return param_name;
	}
	
	/**
	 * 参数键名
	 * 
	 * @return param_key
	 */
	public String getParam_key() {
		return param_key;
	}
	
	/**
	 * 参数键值
	 * 
	 * @return param_value
	 */
	public String getParam_value() {
		return param_value;
	}
	
	/**
	 * 目录ID
	 * 
	 * @return catalog_id
	 */
	public String getCatalog_id() {
		return catalog_id;
	}
	
	/**
	 * 分类科目语义ID
	 * 
	 * @return catalog_cascade_id
	 */
	public String getCatalog_cascade_id() {
		return catalog_cascade_id;
	}
	
	/**
	 * 参数备注
	 * 
	 * @return param_remark
	 */
	public String getParam_remark() {
		return param_remark;
	}
	
	/**
	 * 当前状态(0:停用;1:启用)
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 编辑模式(0:只读;1:可编辑)
	 * 
	 * @return edit_mode
	 */
	public String getEdit_mode() {
		return edit_mode;
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
	 * 创建用户ID
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
	 * 参数编号
	 * 
	 * @param param_id
	 */
	public void setParam_id(String param_id) {
		this.param_id = param_id;
	}
	
	/**
	 * 参数名称
	 * 
	 * @param param_name
	 */
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	
	/**
	 * 参数键名
	 * 
	 * @param param_key
	 */
	public void setParam_key(String param_key) {
		this.param_key = param_key;
	}
	
	/**
	 * 参数键值
	 * 
	 * @param param_value
	 */
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}
	
	/**
	 * 目录ID
	 * 
	 * @param catalog_id
	 */
	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}
	
	/**
	 * 分类科目语义ID
	 * 
	 * @param catalog_cascade_id
	 */
	public void setCatalog_cascade_id(String catalog_cascade_id) {
		this.catalog_cascade_id = catalog_cascade_id;
	}
	
	/**
	 * 参数备注
	 * 
	 * @param param_remark
	 */
	public void setParam_remark(String param_remark) {
		this.param_remark = param_remark;
	}
	
	/**
	 * 当前状态(0:停用;1:启用)
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 编辑模式(0:只读;1:可编辑)
	 * 
	 * @param edit_mode
	 */
	public void setEdit_mode(String edit_mode) {
		this.edit_mode = edit_mode;
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
	 * 创建用户ID
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

	public String getCatalog_name() {
		return catalog_name;
	}

	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}
	

}