package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>购买礼包记录[bis_bag_record]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-20 21:54:26
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class BagRecordPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * record_id
	 */
	private String record_id;
	
	/**
	 * 礼包名称
	 */
	private String bag_id;
	
	/**
	 * 有效时间
	 */
	private Integer valid_day;
	
	/**
	 * 用户编号
	 */
	private String custom_user_id;
	
	/**
	 * 购买数量
	 */
	private Integer buy_num;
	
	/**
	 * 记录创建时间
	 */
	private Integer share_num;
	
	/**
	 * 礼包开始生效时间
	 */
	private Date create_time;
	
	/**
	 * order_id
	 */
	private String order_id;
	
	/**
	 * share_user_id
	 */
	private String share_user_id;
	
	/**
	 * 分享时间
	 */
	private Date bag_time;
	
	/**
	 * 1 购买礼包项目 2分享领取礼包项目
	 */
	private String record_type;
	
	/**
	 * 1有效 2过期
	 */
	private String status;
	
	/**
	 * 领取状态 0未领取 1已领取
	 */
	private String receive_status;
	

	/**
	 * record_id
	 * 
	 * @return record_id
	 */
	public String getRecord_id() {
		return record_id;
	}
	
	/**
	 * 礼包名称
	 * 
	 * @return bag_id
	 */
	public String getBag_id() {
		return bag_id;
	}
	
	/**
	 * 有效时间
	 * 
	 * @return valid_day
	 */
	public Integer getValid_day() {
		return valid_day;
	}
	
	/**
	 * 用户编号
	 * 
	 * @return custom_user_id
	 */
	public String getCustom_user_id() {
		return custom_user_id;
	}
	
	/**
	 * 购买数量
	 * 
	 * @return buy_num
	 */
	public Integer getBuy_num() {
		return buy_num;
	}
	
	/**
	 * 记录创建时间
	 * 
	 * @return share_num
	 */
	public Integer getShare_num() {
		return share_num;
	}
	
	/**
	 * 礼包开始生效时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * order_id
	 * 
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
	}
	
	/**
	 * share_user_id
	 * 
	 * @return share_user_id
	 */
	public String getShare_user_id() {
		return share_user_id;
	}
	
	/**
	 * 分享时间
	 * 
	 * @return bag_time
	 */
	public Date getBag_time() {
		return bag_time;
	}
	
	/**
	 * 1 购买礼包项目 2分享领取礼包项目
	 * 
	 * @return record_type
	 */
	public String getRecord_type() {
		return record_type;
	}
	
	/**
	 * 1有效 2过期
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 领取状态 0未领取 1已领取
	 * 
	 * @return receive_status
	 */
	public String getReceive_status() {
		return receive_status;
	}
	

	/**
	 * record_id
	 * 
	 * @param record_id
	 */
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	
	/**
	 * 礼包名称
	 * 
	 * @param bag_id
	 */
	public void setBag_id(String bag_id) {
		this.bag_id = bag_id;
	}
	
	/**
	 * 有效时间
	 * 
	 * @param valid_day
	 */
	public void setValid_day(Integer valid_day) {
		this.valid_day = valid_day;
	}
	
	/**
	 * 用户编号
	 * 
	 * @param custom_user_id
	 */
	public void setCustom_user_id(String custom_user_id) {
		this.custom_user_id = custom_user_id;
	}
	
	/**
	 * 购买数量
	 * 
	 * @param buy_num
	 */
	public void setBuy_num(Integer buy_num) {
		this.buy_num = buy_num;
	}
	
	/**
	 * 记录创建时间
	 * 
	 * @param share_num
	 */
	public void setShare_num(Integer share_num) {
		this.share_num = share_num;
	}
	
	/**
	 * 礼包开始生效时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * order_id
	 * 
	 * @param order_id
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * share_user_id
	 * 
	 * @param share_user_id
	 */
	public void setShare_user_id(String share_user_id) {
		this.share_user_id = share_user_id;
	}
	
	/**
	 * 分享时间
	 * 
	 * @param bag_time
	 */
	public void setBag_time(Date bag_time) {
		this.bag_time = bag_time;
	}
	
	/**
	 * 1 购买礼包项目 2分享领取礼包项目
	 * 
	 * @param record_type
	 */
	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}
	
	/**
	 * 1有效 2过期
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 领取状态 0未领取 1已领取
	 * 
	 * @param receive_status
	 */
	public void setReceive_status(String receive_status) {
		this.receive_status = receive_status;
	}
	

}