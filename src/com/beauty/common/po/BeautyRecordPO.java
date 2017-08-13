package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>颜值流水[bis_beauty_record]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-12 23:41:53
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class BeautyRecordPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录编号
	 */
	private String record_id;
	
	/**
	 * 订单编号
	 */
	private String order_id;
	
	/**
	 * 兑换编号
	 */
	private String cdkey;
	
	/**
	 * 购买人编号
	 */
	private String custom_user_id;
	
	/**
	 * 颜值数量
	 */
	private Integer beauty_num;
	
	/**
	 * 赠送数量
	 */
	private Integer give_num;
	
	/**
	 * 总价
	 */
	private Double total_money;
	
	/**
	 * 单个颜值价值
	 */
	private Double singe_price;
	
	/**
	 * 1购买美丽币2 兑换美丽币3服务消费
	 */
	private String record_type;
	
	/**
	 * 支付时间
	 */
	private Date pay_time;
	
	/**
	 * 颜值状态1有效 2过期
	 */
	private String vaild_status;
	
	/**
	 * 店铺编号
	 */
	private String shop_id;
	
	/**
	 * remain_num
	 */
	private Integer remain_num;
	

	/**
	 * 记录编号
	 * 
	 * @return record_id
	 */
	public String getRecord_id() {
		return record_id;
	}
	
	/**
	 * 订单编号
	 * 
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
	}
	
	/**
	 * 兑换编号
	 * 
	 * @return cdkey
	 */
	public String getCdkey() {
		return cdkey;
	}
	
	/**
	 * 购买人编号
	 * 
	 * @return custom_user_id
	 */
	public String getCustom_user_id() {
		return custom_user_id;
	}
	
	/**
	 * 颜值数量
	 * 
	 * @return beauty_num
	 */
	public Integer getBeauty_num() {
		return beauty_num;
	}
	
	/**
	 * 赠送数量
	 * 
	 * @return give_num
	 */
	public Integer getGive_num() {
		return give_num;
	}
	
	/**
	 * 总价
	 * 
	 * @return total_money
	 */
	public Double getTotal_money() {
		return total_money;
	}
	
	/**
	 * 单个颜值价值
	 * 
	 * @return singe_price
	 */
	public Double getSinge_price() {
		return singe_price;
	}
	
	/**
	 * 1购买美丽币2 兑换美丽币3服务消费
	 * 
	 * @return record_type
	 */
	public String getRecord_type() {
		return record_type;
	}
	
	/**
	 * 支付时间
	 * 
	 * @return pay_time
	 */
	public Date getPay_time() {
		return pay_time;
	}
	
	/**
	 * 颜值状态1有效 2过期
	 * 
	 * @return vaild_status
	 */
	public String getVaild_status() {
		return vaild_status;
	}
	
	/**
	 * 店铺编号
	 * 
	 * @return shop_id
	 */
	public String getShop_id() {
		return shop_id;
	}
	
	/**
	 * remain_num
	 * 
	 * @return remain_num
	 */
	public Integer getRemain_num() {
		return remain_num;
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
	 * 订单编号
	 * 
	 * @param order_id
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	/**
	 * 兑换编号
	 * 
	 * @param cdkey
	 */
	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}
	
	/**
	 * 购买人编号
	 * 
	 * @param custom_user_id
	 */
	public void setCustom_user_id(String custom_user_id) {
		this.custom_user_id = custom_user_id;
	}
	
	/**
	 * 颜值数量
	 * 
	 * @param beauty_num
	 */
	public void setBeauty_num(Integer beauty_num) {
		this.beauty_num = beauty_num;
	}
	
	/**
	 * 赠送数量
	 * 
	 * @param give_num
	 */
	public void setGive_num(Integer give_num) {
		this.give_num = give_num;
	}
	
	/**
	 * 总价
	 * 
	 * @param total_money
	 */
	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}
	
	/**
	 * 单个颜值价值
	 * 
	 * @param singe_price
	 */
	public void setSinge_price(Double singe_price) {
		this.singe_price = singe_price;
	}
	
	/**
	 * 1购买美丽币2 兑换美丽币3服务消费
	 * 
	 * @param record_type
	 */
	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}
	
	/**
	 * 支付时间
	 * 
	 * @param pay_time
	 */
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	
	/**
	 * 颜值状态1有效 2过期
	 * 
	 * @param vaild_status
	 */
	public void setVaild_status(String vaild_status) {
		this.vaild_status = vaild_status;
	}
	
	/**
	 * 店铺编号
	 * 
	 * @param shop_id
	 */
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	/**
	 * remain_num
	 * 
	 * @param remain_num
	 */
	public void setRemain_num(Integer remain_num) {
		this.remain_num = remain_num;
	}
	

}