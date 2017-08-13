package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>定金信息[bis_order_deposit]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-05 00:05:42
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class OrderDepositPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 定金编号
	 */
	private String deposit_id;
	
	/**
	 * 订单编号
	 */
	private String order_id;
	
	/**
	 * 买家支付账号
	 */
	private String pay_account;
	
	/**
	 * deposit_money
	 */
	private Double deposit_money;
	
	/**
	 * pay_way
	 */
	private String pay_way;
	
	/**
	 * 支付时间
	 */
	private Date pay_time;
	
	/**
	 * 定金状态
	 */
	private String deposit_status;
	
	/**
	 * 退回时间
	 */
	private Date back_time;
	
	/**
	 * 消费者编号
	 */
	private String custom_user_id;
	

	/**
	 * 定金编号
	 * 
	 * @return deposit_id
	 */
	public String getDeposit_id() {
		return deposit_id;
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
	 * 买家支付账号
	 * 
	 * @return pay_account
	 */
	public String getPay_account() {
		return pay_account;
	}
	
	/**
	 * deposit_money
	 * 
	 * @return deposit_money
	 */
	public Double getDeposit_money() {
		return deposit_money;
	}
	
	/**
	 * pay_way
	 * 
	 * @return pay_way
	 */
	public String getPay_way() {
		return pay_way;
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
	 * 定金状态
	 * 
	 * @return deposit_status
	 */
	public String getDeposit_status() {
		return deposit_status;
	}
	
	/**
	 * 退回时间
	 * 
	 * @return back_time
	 */
	public Date getBack_time() {
		return back_time;
	}
	
	/**
	 * 消费者编号
	 * 
	 * @return custom_user_id
	 */
	public String getCustom_user_id() {
		return custom_user_id;
	}
	

	/**
	 * 定金编号
	 * 
	 * @param deposit_id
	 */
	public void setDeposit_id(String deposit_id) {
		this.deposit_id = deposit_id;
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
	 * 买家支付账号
	 * 
	 * @param pay_account
	 */
	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	
	/**
	 * deposit_money
	 * 
	 * @param deposit_money
	 */
	public void setDeposit_money(Double deposit_money) {
		this.deposit_money = deposit_money;
	}
	
	/**
	 * pay_way
	 * 
	 * @param pay_way
	 */
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
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
	 * 定金状态
	 * 
	 * @param deposit_status
	 */
	public void setDeposit_status(String deposit_status) {
		this.deposit_status = deposit_status;
	}
	
	/**
	 * 退回时间
	 * 
	 * @param back_time
	 */
	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}
	
	/**
	 * 消费者编号
	 * 
	 * @param custom_user_id
	 */
	public void setCustom_user_id(String custom_user_id) {
		this.custom_user_id = custom_user_id;
	}
	

}