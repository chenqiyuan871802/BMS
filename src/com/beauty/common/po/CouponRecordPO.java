package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>美研券兑换记录表[bis_coupon_record]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-26 21:47:37
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class CouponRecordPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录编号
	 */
	private String record_id;
	
	/**
	 * 活动编号
	 */
	private String active_id;
	
	/**
	 * 兑换码
	 */
	private String cdkey;
	
	/**
	 * 兑换手机
	 */
	private String mobile;
	
	/**
	 * 状态 1未兑换 2已兑换 3已失效
	 */
	private String status;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 兑换时间
	 */
	private Date exchange_time;
	

	/**
	 * 记录编号
	 * 
	 * @return record_id
	 */
	public String getRecord_id() {
		return record_id;
	}
	
	/**
	 * 活动编号
	 * 
	 * @return active_id
	 */
	public String getActive_id() {
		return active_id;
	}
	
	/**
	 * 兑换码
	 * 
	 * @return cdkey
	 */
	public String getCdkey() {
		return cdkey;
	}
	
	/**
	 * 兑换手机
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 状态 1未兑换 2已兑换 3已失效
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
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
	 * 兑换时间
	 * 
	 * @return exchange_time
	 */
	public Date getExchange_time() {
		return exchange_time;
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
	 * 活动编号
	 * 
	 * @param active_id
	 */
	public void setActive_id(String active_id) {
		this.active_id = active_id;
	}
	
	/**
	 * 兑换码
	 * 
	 * @param cdkey
	 */
	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}
	
	/**
	 * 兑换手机
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 状态 1未兑换 2已兑换 3已失效
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 兑换时间
	 * 
	 * @param exchange_time
	 */
	public void setExchange_time(Date exchange_time) {
		this.exchange_time = exchange_time;
	}
	

}