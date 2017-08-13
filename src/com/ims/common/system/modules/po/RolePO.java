package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>角色表[sys_role]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-31 09:38:33
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class RolePO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 *  流水号
	 */
	private String role_id;
	
	/**
	 * 角色名称
	 */
	private String role_name;
	
	/**
	 * 当前状态 1启用 0禁用
	 */
	private String status;
	
	/**
	 * 角色类型
	 */
	private String role_type;
	
	/**
	 * 备注
	 */
	private String role_remark;
	
	/**
	 * 编辑模式(0:只读;1:可编辑)
	 */
	private String edit_mode;
	
	/**
	 * 创建用户编号
	 */
	private String create_user_id;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 修改用户ID
	 */
	private String modify_user_id;
	
	/**
	 * 修改时间
	 */
	private Date modify_time;
	

	/**
	 *  流水号
	 * 
	 * @return role_id
	 */
	public String getRole_id() {
		return role_id;
	}
	
	/**
	 * 角色名称
	 * 
	 * @return role_name
	 */
	public String getRole_name() {
		return role_name;
	}
	
	/**
	 * 当前状态 1启用 0禁用
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 角色类型
	 * 
	 * @return role_type
	 */
	public String getRole_type() {
		return role_type;
	}
	
	/**
	 * 备注
	 * 
	 * @return role_remark
	 */
	public String getRole_remark() {
		return role_remark;
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
	 * 创建用户编号
	 * 
	 * @return create_user_id
	 */
	public String getCreate_user_id() {
		return create_user_id;
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
	 * 修改用户ID
	 * 
	 * @return modify_user_id
	 */
	public String getModify_user_id() {
		return modify_user_id;
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
	 *  流水号
	 * 
	 * @param role_id
	 */
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	/**
	 * 角色名称
	 * 
	 * @param role_name
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	/**
	 * 当前状态 1启用 0禁用
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 角色类型
	 * 
	 * @param role_type
	 */
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	
	/**
	 * 备注
	 * 
	 * @param role_remark
	 */
	public void setRole_remark(String role_remark) {
		this.role_remark = role_remark;
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
	 * 创建用户编号
	 * 
	 * @param create_user_id
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
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
	 * 修改用户ID
	 * 
	 * @param modify_user_id
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	
	/**
	 * 修改时间
	 * 
	 * @param modify_time
	 */
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	

}