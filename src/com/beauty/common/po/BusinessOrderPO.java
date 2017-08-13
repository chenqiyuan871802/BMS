package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>营业订单[bis_business_order]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-23 21:59:58
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class BusinessOrderPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 订单编号
	 */
	private String order_id;
	
	/**
	 * 订单内容
	 */
	private String order_content;
	
	/**
	 * 项目编号
	 */
	private String project_id;
	
	/**
	 * 店铺编号
	 */
	private String shop_id;
	
	/**
	 * 消费者编号
	 */
	private String custom_user_id;
	
	/**
	 * 预约时间
	 */
	private Date subscribe_time;
	
	/**
	 * 订单类型 1美研币 2礼包3项目
	 */
	private String order_type;
	
	/**
	 * 订单金额
	 */
	private Double order_money;
	
	/**
	 * 购买数量
	 */
	private Integer buy_num;
	
	/**
	 * 实际支付金额
	 */
	private Double pay_money;
	
	/**
	 * 消耗美研币数量
	 */
	private Integer extend_beauty_num;
	
	/**
	 * 现金收入
	 */
	private Double cash_income;
	
	/**
	 * 消耗收入
	 */
	private Double extend_income;
	
	/**
	 * 支付方式1 微信 2支付宝 3美颜币 4套餐
	 */
	private String pay_way;
	
	/**
	 * 支付时间
	 */
	private Date pay_time;
	
	/**
	 * 美研币支付验证码
	 */
	private String check_code;
	
	/**
	 * 订单状态
	 */
	private String order_status;
	
	/**
	 * 订单备注
	 */
	private String order_remark;
	
	/**
	 * server_user_id
	 */
	private String server_user_id;
	
	/**
	 * 经手员工编号
	 */
	private String handle_user_id;
	
	/**
	 * 订单创建时间
	 */
	private Date create_time;
	
	/**
	 * 服务时间
	 */
	private Date service_time;
	
	/**
	 * 撤销时间
	 */
	private Date cancel_time;
	
	/**
	 * cancel_type
	 */
	private String cancel_type;
	
	/**
	 * 订单完成时间
	 */
	private Date finish_time;
	
	/**
	 * 订单所属 1平台 2商家
	 */
	private String order_source;
	
	/**
	 * 修改状态 1已经修改0未修改
	 */
	private String modify_status;
	
	/**
	 * 免费美研币
	 */
	private Integer free_num;
	

	/**
	 * 订单编号
	 * 
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
	}
	
	/**
	 * 订单内容
	 * 
	 * @return order_content
	 */
	public String getOrder_content() {
		return order_content;
	}
	
	/**
	 * 项目编号
	 * 
	 * @return project_id
	 */
	public String getProject_id() {
		return project_id;
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
	 * 消费者编号
	 * 
	 * @return custom_user_id
	 */
	public String getCustom_user_id() {
		return custom_user_id;
	}
	
	/**
	 * 预约时间
	 * 
	 * @return subscribe_time
	 */
	public Date getSubscribe_time() {
		return subscribe_time;
	}
	
	/**
	 * 订单类型 1美研币 2礼包3项目
	 * 
	 * @return order_type
	 */
	public String getOrder_type() {
		return order_type;
	}
	
	/**
	 * 订单金额
	 * 
	 * @return order_money
	 */
	public Double getOrder_money() {
		return order_money;
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
	 * 实际支付金额
	 * 
	 * @return pay_money
	 */
	public Double getPay_money() {
		return pay_money;
	}
	
	/**
	 * 消耗美研币数量
	 * 
	 * @return extend_beauty_num
	 */
	public Integer getExtend_beauty_num() {
		return extend_beauty_num;
	}
	
	/**
	 * 现金收入
	 * 
	 * @return cash_income
	 */
	public Double getCash_income() {
		return cash_income;
	}
	
	/**
	 * 消耗收入
	 * 
	 * @return extend_income
	 */
	public Double getExtend_income() {
		return extend_income;
	}
	
	/**
	 * 支付方式1 微信 2支付宝 3美颜币 4套餐
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
	 * 美研币支付验证码
	 * 
	 * @return check_code
	 */
	public String getCheck_code() {
		return check_code;
	}
	
	/**
	 * 订单状态
	 * 
	 * @return order_status
	 */
	public String getOrder_status() {
		return order_status;
	}
	
	/**
	 * 订单备注
	 * 
	 * @return order_remark
	 */
	public String getOrder_remark() {
		return order_remark;
	}
	
	/**
	 * server_user_id
	 * 
	 * @return server_user_id
	 */
	public String getServer_user_id() {
		return server_user_id;
	}
	
	/**
	 * 经手员工编号
	 * 
	 * @return handle_user_id
	 */
	public String getHandle_user_id() {
		return handle_user_id;
	}
	
	/**
	 * 订单创建时间
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	
	/**
	 * 服务时间
	 * 
	 * @return service_time
	 */
	public Date getService_time() {
		return service_time;
	}
	
	/**
	 * 撤销时间
	 * 
	 * @return cancel_time
	 */
	public Date getCancel_time() {
		return cancel_time;
	}
	
	/**
	 * cancel_type
	 * 
	 * @return cancel_type
	 */
	public String getCancel_type() {
		return cancel_type;
	}
	
	/**
	 * 订单完成时间
	 * 
	 * @return finish_time
	 */
	public Date getFinish_time() {
		return finish_time;
	}
	
	/**
	 * 订单所属 1平台 2商家
	 * 
	 * @return order_source
	 */
	public String getOrder_source() {
		return order_source;
	}
	
	/**
	 * 修改状态 1已经修改0未修改
	 * 
	 * @return modify_status
	 */
	public String getModify_status() {
		return modify_status;
	}
	
	/**
	 * 免费美研币
	 * 
	 * @return free_num
	 */
	public Integer getFree_num() {
		return free_num;
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
	 * 订单内容
	 * 
	 * @param order_content
	 */
	public void setOrder_content(String order_content) {
		this.order_content = order_content;
	}
	
	/**
	 * 项目编号
	 * 
	 * @param project_id
	 */
	public void setProject_id(String project_id) {
		this.project_id = project_id;
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
	 * 消费者编号
	 * 
	 * @param custom_user_id
	 */
	public void setCustom_user_id(String custom_user_id) {
		this.custom_user_id = custom_user_id;
	}
	
	/**
	 * 预约时间
	 * 
	 * @param subscribe_time
	 */
	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	
	/**
	 * 订单类型 1美研币 2礼包3项目
	 * 
	 * @param order_type
	 */
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	
	/**
	 * 订单金额
	 * 
	 * @param order_money
	 */
	public void setOrder_money(Double order_money) {
		this.order_money = order_money;
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
	 * 实际支付金额
	 * 
	 * @param pay_money
	 */
	public void setPay_money(Double pay_money) {
		this.pay_money = pay_money;
	}
	
	/**
	 * 消耗美研币数量
	 * 
	 * @param extend_beauty_num
	 */
	public void setExtend_beauty_num(Integer extend_beauty_num) {
		this.extend_beauty_num = extend_beauty_num;
	}
	
	/**
	 * 现金收入
	 * 
	 * @param cash_income
	 */
	public void setCash_income(Double cash_income) {
		this.cash_income = cash_income;
	}
	
	/**
	 * 消耗收入
	 * 
	 * @param extend_income
	 */
	public void setExtend_income(Double extend_income) {
		this.extend_income = extend_income;
	}
	
	/**
	 * 支付方式1 微信 2支付宝 3美颜币 4套餐
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
	 * 美研币支付验证码
	 * 
	 * @param check_code
	 */
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	
	/**
	 * 订单状态
	 * 
	 * @param order_status
	 */
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	/**
	 * 订单备注
	 * 
	 * @param order_remark
	 */
	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}
	
	/**
	 * server_user_id
	 * 
	 * @param server_user_id
	 */
	public void setServer_user_id(String server_user_id) {
		this.server_user_id = server_user_id;
	}
	
	/**
	 * 经手员工编号
	 * 
	 * @param handle_user_id
	 */
	public void setHandle_user_id(String handle_user_id) {
		this.handle_user_id = handle_user_id;
	}
	
	/**
	 * 订单创建时间
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	/**
	 * 服务时间
	 * 
	 * @param service_time
	 */
	public void setService_time(Date service_time) {
		this.service_time = service_time;
	}
	
	/**
	 * 撤销时间
	 * 
	 * @param cancel_time
	 */
	public void setCancel_time(Date cancel_time) {
		this.cancel_time = cancel_time;
	}
	
	/**
	 * cancel_type
	 * 
	 * @param cancel_type
	 */
	public void setCancel_type(String cancel_type) {
		this.cancel_type = cancel_type;
	}
	
	/**
	 * 订单完成时间
	 * 
	 * @param finish_time
	 */
	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	
	/**
	 * 订单所属 1平台 2商家
	 * 
	 * @param order_source
	 */
	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}
	
	/**
	 * 修改状态 1已经修改0未修改
	 * 
	 * @param modify_status
	 */
	public void setModify_status(String modify_status) {
		this.modify_status = modify_status;
	}
	
	/**
	 * 免费美研币
	 * 
	 * @param free_num
	 */
	public void setFree_num(Integer free_num) {
		this.free_num = free_num;
	}
	

}