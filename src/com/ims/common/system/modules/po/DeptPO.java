package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>组织机构[sys_dept]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-10 10:00:29
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class DeptPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水号
	 */
	private String dept_id;
	
	/**
	 * 节点语义ID
	 */
	private String cascade_id;
	
	/**
	 * 组织名称
	 */
	private String dept_name;
	
	/**
	 * 父节点流水号
	 */
	private String parent_id;
	
	/**
	 * 机构代码
	 */
	private String dept_code;
	
	/**
	 * 主要负责人
	 */
	private String manager;
	
	/**
	 * 部门电话
	 */
	private String phone;
	
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 是否自动展开
	 */
	private String is_auto_expand;
	
	/**
	 * 节点图标文件名称
	 */
	private String icon_name;
	
	/**
	 * 排序号
	 */
	private Integer sort_no;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 是否已删除 0有效 1删除
	 */
	private String is_del;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 创建人ID
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
	 * 子节点数目
	 */
	private int child_count;

	public int getChild_count() {
		return child_count;
	}

	public void setChild_count(int child_count) {
		this.child_count = child_count;
	}

	/**
	 * 流水号
	 * 
	 * @return dept_id
	 */
	public String getDept_id() {
		return dept_id;
	}
	
	/**
	 * 节点语义ID
	 * 
	 * @return cascade_id
	 */
	public String getCascade_id() {
		return cascade_id;
	}
	
	/**
	 * 组织名称
	 * 
	 * @return dept_name
	 */
	public String getDept_name() {
		return dept_name;
	}
	
	/**
	 * 父节点流水号
	 * 
	 * @return parent_id
	 */
	public String getParent_id() {
		return parent_id;
	}
	
	/**
	 * 机构代码
	 * 
	 * @return dept_code
	 */
	public String getDept_code() {
		return dept_code;
	}
	
	/**
	 * 主要负责人
	 * 
	 * @return manager
	 */
	public String getManager() {
		return manager;
	}
	
	/**
	 * 部门电话
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 传真
	 * 
	 * @return fax
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * 地址
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
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
	 * 节点图标文件名称
	 * 
	 * @return icon_name
	 */
	public String getIcon_name() {
		return icon_name;
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
	 * 备注
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * 是否已删除 0有效 1删除
	 * 
	 * @return is_del
	 */
	public String getIs_del() {
		return is_del;
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
	 * 创建人ID
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
	 * @param dept_id
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	/**
	 * 节点语义ID
	 * 
	 * @param cascade_id
	 */
	public void setCascade_id(String cascade_id) {
		this.cascade_id = cascade_id;
	}
	
	/**
	 * 组织名称
	 * 
	 * @param dept_name
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	/**
	 * 父节点流水号
	 * 
	 * @param parent_id
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 机构代码
	 * 
	 * @param dept_code
	 */
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	
	/**
	 * 主要负责人
	 * 
	 * @param manager
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	/**
	 * 部门电话
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 传真
	 * 
	 * @param fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * 地址
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * 节点图标文件名称
	 * 
	 * @param icon_name
	 */
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
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
	 * 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 是否已删除 0有效 1删除
	 * 
	 * @param is_del
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
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
	 * 创建人ID
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