package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>现金流水[bis_cash_record]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-07 22:45:48
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class CashRecordPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 流水号
	 */
	private String record_id;
	
	/**
	 * 订单编号
	 */
	private String order_id;
	
	/**
	 * 购买人编号
	 */
	private String custom_user_id;
	
	/**
	 * 颜值数量
	 */
	private Integer beauty_num;
	
	/**
	 * 金额
	 */
	private Double money;
	
	/**
	 * 支付方式1 微信 2支付宝
	 */
	private String pay_way;
	
	/**
	 * 流水类型1收入 2支出
	 */
	private String cash_type;
	
	/**
	 * 记录类型1购买美丽币2购买套餐 3使用消费4 预约
	 */
	private String record_type;
	
	/**
	 * 支付时间
	 */
	private Date pay_time;
	

	/**
	 * 流水号
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
	 * 金额
	 * 
	 * @return money
	 */
	public Double getMoney() {
		return money;
	}
	
	/**
	 * 支付方式1 微信 2支付宝
	 * 
	 * @return pay_way
	 */
	public String getPay_way() {
		return pay_way;
	}
	
	/**
	 * 流水类型1收入 2支出
	 * 
	 * @return cash_type
	 */
	public String getCash_type() {
		return cash_type;
	}
	
	/**
	 * 记录类型1购买美丽币2购买套餐 3使用消费4 预约
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
	 * 流水号
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
	 * 金额
	 * 
	 * @param money
	 */
	public void setMoney(Double money) {
		this.money = money;
	}
	
	/**
	 * 支付方式1 微信 2支付宝
	 * 
	 * @param pay_way
	 */
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}
	
	/**
	 * 流水类型1收入 2支出
	 * 
	 * @param cash_type
	 */
	public void setCash_type(String cash_type) {
		this.cash_type = cash_type;
	}
	
	/**
	 * 记录类型1购买美丽币2购买套餐 3使用消费4 预约
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
	

}