package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>短信记录[bis_sms_record]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-12 00:26:47
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class SmsRecordPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录编号
	 */
	private String record_id;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 短信类型 1普通短信 2验证短信 3支付验证短信
	 */
	private String sms_type;
	
	/**
	 * 发送用户编号，
	 */
	private String send_user_id;
	
	/**
	 * 用户类型 1员工 2顾客
	 */
	private String user_type;
	
	/**
	 * create_user_id
	 */
	private String create_user_id;
	
	/**
	 * create_time
	 */
	private Date create_time;
	
	/**
	 * 短信状态  0失败 1成功
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
	 * 手机号码
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
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
	 * 短信类型 1普通短信 2验证短信 3支付验证短信
	 * 
	 * @return sms_type
	 */
	public String getSms_type() {
		return sms_type;
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
	 * 用户类型 1员工 2顾客
	 * 
	 * @return user_type
	 */
	public String getUser_type() {
		return user_type;
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
	 * 短信状态  0失败 1成功
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
	 * 手机号码
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * 短信类型 1普通短信 2验证短信 3支付验证短信
	 * 
	 * @param sms_type
	 */
	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
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
	 * 用户类型 1员工 2顾客
	 * 
	 * @param user_type
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
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
	 * 短信状态  0失败 1成功
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