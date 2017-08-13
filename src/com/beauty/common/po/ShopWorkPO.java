package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>工作机[bis_shop_work]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-16 15:51:54
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class ShopWorkPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 工作机编号
	 */
	private String work_id;
	
	/**
	 * 绑定店铺编号
	 */
	private String shop_id;
	
	/**
	 * 工作机IP
	 */
	private String shop_ip;
	
	/**
	 * 工作机密码
	 */
	private String work_password;
	
	/**
	 * 是否设置密码 0 是 1否
	 */
	private String whether_set;
	
	/**
	 * 工作机SN
	 */
	private String work_sn;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 创建人ID
	 */
	private String create_user_id;
	
	/**
	 * 修改时间
	 */
	private Date modify_time;
	
	/**
	 * 修改用户ID
	 */
	private String modify_user_id;
	

	/**
	 * 工作机编号
	 * 
	 * @return work_id
	 */
	public String getWork_id() {
		return work_id;
	}
	
	/**
	 * 绑定店铺编号
	 * 
	 * @return shop_id
	 */
	public String getShop_id() {
		return shop_id;
	}
	
	/**
	 * 工作机IP
	 * 
	 * @return shop_ip
	 */
	public String getShop_ip() {
		return shop_ip;
	}
	
	/**
	 * 工作机密码
	 * 
	 * @return work_password
	 */
	public String getWork_password() {
		return work_password;
	}
	
	/**
	 * 是否设置密码 0 是 1否
	 * 
	 * @return whether_set
	 */
	public String getWhether_set() {
		return whether_set;
	}
	
	/**
	 * 工作机SN
	 * 
	 * @return work_sn
	 */
	public String getWork_sn() {
		return work_sn;
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
	 * 创建人ID
	 * 
	 * @return create_user_id
	 */
	public String getCreate_user_id() {
		return create_user_id;
	}
	
	/**
	 * 修改时间
	 * 
	 * @return modify_time
	 */
	public Date getModify_time() {
		return modify_time;
	}
	
	/**
	 * 修改用户ID
	 * 
	 * @return modify_user_id
	 */
	public String getModify_user_id() {
		return modify_user_id;
	}
	

	/**
	 * 工作机编号
	 * 
	 * @param work_id
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
	
	/**
	 * 绑定店铺编号
	 * 
	 * @param shop_id
	 */
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	/**
	 * 工作机IP
	 * 
	 * @param shop_ip
	 */
	public void setShop_ip(String shop_ip) {
		this.shop_ip = shop_ip;
	}
	
	/**
	 * 工作机密码
	 * 
	 * @param work_password
	 */
	public void setWork_password(String work_password) {
		this.work_password = work_password;
	}
	
	/**
	 * 是否设置密码 0 是 1否
	 * 
	 * @param whether_set
	 */
	public void setWhether_set(String whether_set) {
		this.whether_set = whether_set;
	}
	
	/**
	 * 工作机SN
	 * 
	 * @param work_sn
	 */
	public void setWork_sn(String work_sn) {
		this.work_sn = work_sn;
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
	 * 创建人ID
	 * 
	 * @param create_user_id
	 */
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	
	/**
	 * 修改时间
	 * 
	 * @param modify_time
	 */
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	
	/**
	 * 修改用户ID
	 * 
	 * @param modify_user_id
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	

}