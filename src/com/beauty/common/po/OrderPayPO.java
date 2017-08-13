package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>订单支付信息[bis_order_pay]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-08 00:09:14
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class OrderPayPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private String pay_id;
	
	/**
	 * 订单编号
	 */
	private String order_id;
	
	/**
	 * 买家支付账号
	 */
	private String buy_account;
	
	/**
	 * 发送给银行或第三方支付平台的支付订单号
	 */
	private String pay_code;
	
	/**
	 * 支付方式
	 */
	private String pay_way;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 支付时间
	 */
	private Date pay_time;
	
	/**
	 * 支付金额
	 */
	private Double pay_money;
	
	/**
	 * 支付状态1待支付 2 支付成功 3支付失败
	 */
	private String pay_status;
	
	/**
	 * 支付回执1 已收到 2 未收到
	 */
	private String pay_back;
	
	/**
	 * pay_type 1收款 2 退款
	 */
	private String pay_type;
	
	/**
	 * 错误信息
	 */
	private String error_message;
	
	/**
	 * 通知时间
	 */
	private Date nodify_time;
	
	/**
	 * 威富通交易号
	 */
	private String transaction_id;
	
	/**
	 * 微信订单号
	 */
	private String out_transaction_id;
	
	/**
	 * 威富通退款单号
	 */
	private String refund_id;
	
	/**
	 * 授权码
	 */
	private String auth_code;
	

	/**
	 * 编号
	 * 
	 * @return pay_id
	 */
	public String getPay_id() {
		return pay_id;
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
	 * @return buy_account
	 */
	public String getBuy_account() {
		return buy_account;
	}
	
	/**
	 * 发送给银行或第三方支付平台的支付订单号
	 * 
	 * @return pay_code
	 */
	public String getPay_code() {
		return pay_code;
	}
	
	/**
	 * 支付方式
	 * 
	 * @return pay_way
	 */
	public String getPay_way() {
		return pay_way;
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
	 * 支付时间
	 * 
	 * @return pay_time
	 */
	public Date getPay_time() {
		return pay_time;
	}
	
	/**
	 * 支付金额
	 * 
	 * @return pay_money
	 */
	public Double getPay_money() {
		return pay_money;
	}
	
	/**
	 * 支付状态1待支付 2 支付成功 3支付失败
	 * 
	 * @return pay_status
	 */
	public String getPay_status() {
		return pay_status;
	}
	
	/**
	 * 支付回执1 已收到 2 未收到
	 * 
	 * @return pay_back
	 */
	public String getPay_back() {
		return pay_back;
	}
	
	/**
	 * pay_type 1收款 2 退款
	 * 
	 * @return pay_type
	 */
	public String getPay_type() {
		return pay_type;
	}
	
	/**
	 * 错误信息
	 * 
	 * @return error_message
	 */
	public String getError_message() {
		return error_message;
	}
	
	/**
	 * 通知时间
	 * 
	 * @return nodify_time
	 */
	public Date getNodify_time() {
		return nodify_time;
	}
	
	/**
	 * 威富通交易号
	 * 
	 * @return transaction_id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	
	/**
	 * 微信订单号
	 * 
	 * @return out_transaction_id
	 */
	public String getOut_transaction_id() {
		return out_transaction_id;
	}
	
	/**
	 * 威富通退款单号
	 * 
	 * @return refund_id
	 */
	public String getRefund_id() {
		return refund_id;
	}
	
	/**
	 * 授权码
	 * 
	 * @return auth_code
	 */
	public String getAuth_code() {
		return auth_code;
	}
	

	/**
	 * 编号
	 * 
	 * @param pay_id
	 */
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
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
	 * @param buy_account
	 */
	public void setBuy_account(String buy_account) {
		this.buy_account = buy_account;
	}
	
	/**
	 * 发送给银行或第三方支付平台的支付订单号
	 * 
	 * @param pay_code
	 */
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	
	/**
	 * 支付方式
	 * 
	 * @param pay_way
	 */
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
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
	 * 支付时间
	 * 
	 * @param pay_time
	 */
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	
	/**
	 * 支付金额
	 * 
	 * @param pay_money
	 */
	public void setPay_money(Double pay_money) {
		this.pay_money = pay_money;
	}
	
	/**
	 * 支付状态1待支付 2 支付成功 3支付失败
	 * 
	 * @param pay_status
	 */
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	
	/**
	 * 支付回执1 已收到 2 未收到
	 * 
	 * @param pay_back
	 */
	public void setPay_back(String pay_back) {
		this.pay_back = pay_back;
	}
	
	/**
	 * pay_type 1收款 2 退款
	 * 
	 * @param pay_type
	 */
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	
	/**
	 * 错误信息
	 * 
	 * @param error_message
	 */
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	
	/**
	 * 通知时间
	 * 
	 * @param nodify_time
	 */
	public void setNodify_time(Date nodify_time) {
		this.nodify_time = nodify_time;
	}
	
	/**
	 * 威富通交易号
	 * 
	 * @param transaction_id
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	/**
	 * 微信订单号
	 * 
	 * @param out_transaction_id
	 */
	public void setOut_transaction_id(String out_transaction_id) {
		this.out_transaction_id = out_transaction_id;
	}
	
	/**
	 * 威富通退款单号
	 * 
	 * @param refund_id
	 */
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	
	/**
	 * 授权码
	 * 
	 * @param auth_code
	 */
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	

}