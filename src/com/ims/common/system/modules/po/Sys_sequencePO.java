package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
/**
 * 
 * 类描述： <b>ID配置表[sys_sequence]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-24 00:39:05
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class Sys_sequencePO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水号
	 */
	private String seq_id;
	
	/**
	 * 名称
	 */
	private String seq_name;
	
	/**
	 * 类型
	 */
	private String seq_type;
	
	/**
	 * 前缀
	 */
	private String seq_prefix;
	
	/**
	 * 起始值
	 */
	private String seq_start;
	
	/**
	 * 递增步长
	 */
	private String seq_step;
	
	/**
	 * 当前值
	 */
	private String seq_cur_value;
	
	/**
	 * 当前状态
	 */
	private String seq_status;
	
	/**
	 * 连接符
	 */
	private String seq_connector;
	
	/**
	 * 后缀
	 */
	private String seq_seq_suffix;
	
	/**
	 * DBSequence名称
	 */
	private String seq_seq_db_seq_name;
	
	/**
	 * 最大值
	 */
	private String seq_max_value;
	
	/**
	 * 是否循环
	 */
	private String seq_is_circul;
	
	/**
	 * 最小值
	 */
	private String seq_min_value;
	
	/**
	 * 是否左补足
	 */
	private String seq_is_leftpad;
	
	/**
	 * 当前格式化值
	 */
	private String seq_format_value;
	
	/**
	 * 备注
	 */
	private String seq_remark;
	

	/**
	 * 流水号
	 * 
	 * @return seq_id
	 */
	public String getSeq_id() {
		return seq_id;
	}
	
	/**
	 * 名称
	 * 
	 * @return seq_name
	 */
	public String getSeq_name() {
		return seq_name;
	}
	
	/**
	 * 类型
	 * 
	 * @return seq_type
	 */
	public String getSeq_type() {
		return seq_type;
	}
	
	/**
	 * 前缀
	 * 
	 * @return seq_prefix
	 */
	public String getSeq_prefix() {
		return seq_prefix;
	}
	
	/**
	 * 起始值
	 * 
	 * @return seq_start
	 */
	public String getSeq_start() {
		return seq_start;
	}
	
	/**
	 * 递增步长
	 * 
	 * @return seq_step
	 */
	public String getSeq_step() {
		return seq_step;
	}
	
	/**
	 * 当前值
	 * 
	 * @return seq_cur_value
	 */
	public String getSeq_cur_value() {
		return seq_cur_value;
	}
	
	/**
	 * 当前状态
	 * 
	 * @return seq_status
	 */
	public String getSeq_status() {
		return seq_status;
	}
	
	/**
	 * 连接符
	 * 
	 * @return seq_connector
	 */
	public String getSeq_connector() {
		return seq_connector;
	}
	
	/**
	 * 后缀
	 * 
	 * @return seq_seq_suffix
	 */
	public String getSeq_seq_suffix() {
		return seq_seq_suffix;
	}
	
	/**
	 * DBSequence名称
	 * 
	 * @return seq_seq_db_seq_name
	 */
	public String getSeq_seq_db_seq_name() {
		return seq_seq_db_seq_name;
	}
	
	/**
	 * 最大值
	 * 
	 * @return seq_max_value
	 */
	public String getSeq_max_value() {
		return seq_max_value;
	}
	
	/**
	 * 是否循环
	 * 
	 * @return seq_is_circul
	 */
	public String getSeq_is_circul() {
		return seq_is_circul;
	}
	
	/**
	 * 最小值
	 * 
	 * @return seq_min_value
	 */
	public String getSeq_min_value() {
		return seq_min_value;
	}
	
	/**
	 * 是否左补足
	 * 
	 * @return seq_is_leftpad
	 */
	public String getSeq_is_leftpad() {
		return seq_is_leftpad;
	}
	
	/**
	 * 当前格式化值
	 * 
	 * @return seq_format_value
	 */
	public String getSeq_format_value() {
		return seq_format_value;
	}
	
	/**
	 * 备注
	 * 
	 * @return seq_remark
	 */
	public String getSeq_remark() {
		return seq_remark;
	}
	

	/**
	 * 流水号
	 * 
	 * @param seq_id
	 */
	public void setSeq_id(String seq_id) {
		this.seq_id = seq_id;
	}
	
	/**
	 * 名称
	 * 
	 * @param seq_name
	 */
	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}
	
	/**
	 * 类型
	 * 
	 * @param seq_type
	 */
	public void setSeq_type(String seq_type) {
		this.seq_type = seq_type;
	}
	
	/**
	 * 前缀
	 * 
	 * @param seq_prefix
	 */
	public void setSeq_prefix(String seq_prefix) {
		this.seq_prefix = seq_prefix;
	}
	
	/**
	 * 起始值
	 * 
	 * @param seq_start
	 */
	public void setSeq_start(String seq_start) {
		this.seq_start = seq_start;
	}
	
	/**
	 * 递增步长
	 * 
	 * @param seq_step
	 */
	public void setSeq_step(String seq_step) {
		this.seq_step = seq_step;
	}
	
	/**
	 * 当前值
	 * 
	 * @param seq_cur_value
	 */
	public void setSeq_cur_value(String seq_cur_value) {
		this.seq_cur_value = seq_cur_value;
	}
	
	/**
	 * 当前状态
	 * 
	 * @param seq_status
	 */
	public void setSeq_status(String seq_status) {
		this.seq_status = seq_status;
	}
	
	/**
	 * 连接符
	 * 
	 * @param seq_connector
	 */
	public void setSeq_connector(String seq_connector) {
		this.seq_connector = seq_connector;
	}
	
	/**
	 * 后缀
	 * 
	 * @param seq_seq_suffix
	 */
	public void setSeq_seq_suffix(String seq_seq_suffix) {
		this.seq_seq_suffix = seq_seq_suffix;
	}
	
	/**
	 * DBSequence名称
	 * 
	 * @param seq_seq_db_seq_name
	 */
	public void setSeq_seq_db_seq_name(String seq_seq_db_seq_name) {
		this.seq_seq_db_seq_name = seq_seq_db_seq_name;
	}
	
	/**
	 * 最大值
	 * 
	 * @param seq_max_value
	 */
	public void setSeq_max_value(String seq_max_value) {
		this.seq_max_value = seq_max_value;
	}
	
	/**
	 * 是否循环
	 * 
	 * @param seq_is_circul
	 */
	public void setSeq_is_circul(String seq_is_circul) {
		this.seq_is_circul = seq_is_circul;
	}
	
	/**
	 * 最小值
	 * 
	 * @param seq_min_value
	 */
	public void setSeq_min_value(String seq_min_value) {
		this.seq_min_value = seq_min_value;
	}
	
	/**
	 * 是否左补足
	 * 
	 * @param seq_is_leftpad
	 */
	public void setSeq_is_leftpad(String seq_is_leftpad) {
		this.seq_is_leftpad = seq_is_leftpad;
	}
	
	/**
	 * 当前格式化值
	 * 
	 * @param seq_format_value
	 */
	public void setSeq_format_value(String seq_format_value) {
		this.seq_format_value = seq_format_value;
	}
	
	/**
	 * 备注
	 * 
	 * @param seq_remark
	 */
	public void setSeq_remark(String seq_remark) {
		this.seq_remark = seq_remark;
	}
	

}