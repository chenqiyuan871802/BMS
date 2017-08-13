package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>微信记录表[bis_wechat_record]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-23 10:05:05
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class WechatRecordPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录编号
	 */
	private String record_id;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 记录类型 1默认
	 */
	private String record_type;
	
	/**
	 * 发送用户编号，
	 */
	private String send_user_id;
	
	/**
	 * create_user_id
	 */
	private String create_user_id;
	
	/**
	 * create_time
	 */
	private Date create_time;
	
	/**
	 *  信息状态  0失败 1成功
	 */
	private String status;
	
	/**
	 * 发送时间
	 */
	private Date send_time;
	
	/**
	 * 失败原因
	 */
	private String failure_cause;
	

	/**
	 * 记录编号
	 * 
	 * @return record_id
	 */
	public String getRecord_id() {
		return record_id;
	}
	
	/**
	 * 内容
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 记录类型 1默认
	 * 
	 * @return record_type
	 */
	public String getRecord_type() {
		return record_type;
	}
	
	/**
	 * 发送用户编号，
	 * 
	 * @return send_user_id
	 */
	public String getSend_user_id() {
		return send_user_id;
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
	 * create_time
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 *  信息状态  0失败 1成功
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 发送时间
	 * 
	 * @return send_time
	 */
	public Date getSend_time() {
		return send_time;
	}
	
	/**
	 * 失败原因
	 * 
	 * @return failure_cause
	 */
	public String getFailure_cause() {
		return failure_cause;
	}
	

	/**
	 * 记录编号
	 * 
	 * @param record_id
	 */
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	
	/**
	 * 内容
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 记录类型 1默认
	 * 
	 * @param record_type
	 */
	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}
	
	/**
	 * 发送用户编号，
	 * 
	 * @param send_user_id
	 */
	public void setSend_user_id(String send_user_id) {
		this.send_user_id = send_user_id;
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
	 * create_time
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 *  信息状态  0失败 1成功
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 发送时间
	 * 
	 * @param send_time
	 */
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	/**
	 * 失败原因
	 * 
	 * @param failure_cause
	 */
	public void setFailure_cause(String failure_cause) {
		this.failure_cause = failure_cause;
	}
	

}