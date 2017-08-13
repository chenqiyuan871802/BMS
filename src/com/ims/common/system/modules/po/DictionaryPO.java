package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>数据字典[sys_dictionary]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-19 20:53:55
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class DictionaryPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 字典编号
	 */
	private String dic_id;
	
	/**
	 * 所属字典流水号
	 */
	private String dic_index_id;
	
	/**
	 * 字典对照码
	 */
	private String dic_code;
	
	/**
	 * 字典对照值
	 */
	private String dic_value;
	
	/**
	 * 显示颜色
	 */
	private String show_color;
	
	/**
	 * 当前状态(0:停用;1:启用)
	 */
	private String status;
	
	/**
	 * 编辑模式(0:只读;1:可编辑)
	 */
	private String edit_mode;
	
	/**
	 * 排序号
	 */
	private Integer sort_no;
	
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
	 * 字典键
	 */
	private String dic_key;
	

	public String getDic_key() {
		return dic_key;
	}

	public void setDic_key(String dic_key) {
		this.dic_key = dic_key;
	}

	/**
	 * 字典编号
	 * 
	 * @return dic_id
	 */
	public String getDic_id() {
		return dic_id;
	}
	
	/**
	 * 所属字典流水号
	 * 
	 * @return dic_index_id
	 */
	public String getDic_index_id() {
		return dic_index_id;
	}
	
	/**
	 * 字典对照码
	 * 
	 * @return dic_code
	 */
	public String getDic_code() {
		return dic_code;
	}
	
	/**
	 * 字典对照值
	 * 
	 * @return dic_value
	 */
	public String getDic_value() {
		return dic_value;
	}
	
	/**
	 * 显示颜色
	 * 
	 * @return show_color
	 */
	public String getShow_color() {
		return show_color;
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
	 * 排序号
	 * 
	 * @return sort_no
	 */
	public Integer getSort_no() {
		return sort_no;
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
	 * 字典编号
	 * 
	 * @param dic_id
	 */
	public void setDic_id(String dic_id) {
		this.dic_id = dic_id;
	}
	
	/**
	 * 所属字典流水号
	 * 
	 * @param dic_index_id
	 */
	public void setDic_index_id(String dic_index_id) {
		this.dic_index_id = dic_index_id;
	}
	
	/**
	 * 字典对照码
	 * 
	 * @param dic_code
	 */
	public void setDic_code(String dic_code) {
		this.dic_code = dic_code;
	}
	
	/**
	 * 字典对照值
	 * 
	 * @param dic_value
	 */
	public void setDic_value(String dic_value) {
		this.dic_value = dic_value;
	}
	
	/**
	 * 显示颜色
	 * 
	 * @param show_color
	 */
	public void setShow_color(String show_color) {
		this.show_color = show_color;
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
	 * 排序号
	 * 
	 * @param sort_no
	 */
	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
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