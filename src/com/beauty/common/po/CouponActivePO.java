package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>bis_coupon_active[bis_coupon_active]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-26 21:47:23
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class CouponActivePO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 活动编号
	 */
	private String active_id;
	
	/**
	 * 活动名称
	 */
	private String active_name;
	
	/**
	 * 美研券数量
	 */
	private Integer beauty_num;
	
	/**
	 * 每张美研券兑换美颜币数量
	 */
	private Integer bond_num;
	
	/**
	 * 是否删除 0有效 1删除
	 */
	private String is_del;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 创建用户编号
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
	 * 活动编号
	 * 
	 * @return active_id
	 */
	public String getActive_id() {
		return active_id;
	}
	
	/**
	 * 活动名称
	 * 
	 * @return active_name
	 */
	public String getActive_name() {
		return active_name;
	}
	
	/**
	 * 美研券数量
	 * 
	 * @return beauty_num
	 */
	public Integer getBeauty_num() {
		return beauty_num;
	}
	
	/**
	 * 每张美研券兑换美颜币数量
	 * 
	 * @return bond_num
	 */
	public Integer getBond_num() {
		return bond_num;
	}
	
	/**
	 * 是否删除 0有效 1删除
	 * 
	 * @return is_del
	 */
	public String getIs_del() {
		return is_del;
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
	 * 创建用户编号
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
	 * 活动编号
	 * 
	 * @param active_id
	 */
	public void setActive_id(String active_id) {
		this.active_id = active_id;
	}
	
	/**
	 * 活动名称
	 * 
	 * @param active_name
	 */
	public void setActive_name(String active_name) {
		this.active_name = active_name;
	}
	
	/**
	 * 美研券数量
	 * 
	 * @param beauty_num
	 */
	public void setBeauty_num(Integer beauty_num) {
		this.beauty_num = beauty_num;
	}
	
	/**
	 * 每张美研券兑换美颜币数量
	 * 
	 * @param bond_num
	 */
	public void setBond_num(Integer bond_num) {
		this.bond_num = bond_num;
	}
	
	/**
	 * 是否删除 0有效 1删除
	 * 
	 * @param is_del
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
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
	 * 创建用户编号
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