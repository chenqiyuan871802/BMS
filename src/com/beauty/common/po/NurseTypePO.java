package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>护理类型信息表[bis_nurse_type]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:04:13
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class NurseTypePO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 类型编号
	 */
	private String type_id;
	
	/**
	 * 类型名称
	 */
	private String type_name;
	
	/**
	 * 排序号
	 */
	private Integer sort_no;
	
	public Integer getSort_no() {
		return sort_no;
	}

	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}

	/**
	 * 备注
	 */
	private String type_remark;
	
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
	 * 类型编号
	 * 
	 * @return type_id
	 */
	public String getType_id() {
		return type_id;
	}
	
	/**
	 * 类型名称
	 * 
	 * @return type_name
	 */
	public String getType_name() {
		return type_name;
	}
	
	/**
	 * 备注
	 * 
	 * @return type_remark
	 */
	public String getType_remark() {
		return type_remark;
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
	 * 类型编号
	 * 
	 * @param type_id
	 */
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * 类型名称
	 * 
	 * @param type_name
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	/**
	 * 备注
	 * 
	 * @param type_remark
	 */
	public void setType_remark(String type_remark) {
		this.type_remark = type_remark;
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