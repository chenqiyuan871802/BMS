package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>礼包项目[bis_project_record]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-21 00:56:35
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class ProjectRecordPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录项目
	 */
	private String record_id;
	
	/**
	 * 项目编号
	 */
	private String project_id;
	
	/**
	 * 礼包编号
	 */
	private String bag_record_id;
	
	/**
	 * 消费店铺
	 */
	private String shop_id;
	
	/**
	 * 1 未使用 2 已使用 3 已过期
	 */
	private String project_status;
	
	/**
	 * 领取时间
	 */
	private Date draw_time;
	
	/**
	 * 使用时间
	 */
	private Date use_time;
	

	/**
	 * 记录项目
	 * 
	 * @return record_id
	 */
	public String getRecord_id() {
		return record_id;
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
	 * 礼包编号
	 * 
	 * @return bag_record_id
	 */
	public String getBag_record_id() {
		return bag_record_id;
	}
	
	/**
	 * 消费店铺
	 * 
	 * @return shop_id
	 */
	public String getShop_id() {
		return shop_id;
	}
	
	/**
	 * 1 未使用 2 已使用 3 已过期
	 * 
	 * @return project_status
	 */
	public String getProject_status() {
		return project_status;
	}
	
	/**
	 * 领取时间
	 * 
	 * @return draw_time
	 */
	public Date getDraw_time() {
		return draw_time;
	}
	
	/**
	 * 使用时间
	 * 
	 * @return use_time
	 */
	public Date getUse_time() {
		return use_time;
	}
	

	/**
	 * 记录项目
	 * 
	 * @param record_id
	 */
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
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
	 * 礼包编号
	 * 
	 * @param bag_record_id
	 */
	public void setBag_record_id(String bag_record_id) {
		this.bag_record_id = bag_record_id;
	}
	
	/**
	 * 消费店铺
	 * 
	 * @param shop_id
	 */
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	/**
	 * 1 未使用 2 已使用 3 已过期
	 * 
	 * @param project_status
	 */
	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}
	
	/**
	 * 领取时间
	 * 
	 * @param draw_time
	 */
	public void setDraw_time(Date draw_time) {
		this.draw_time = draw_time;
	}
	
	/**
	 * 使用时间
	 * 
	 * @param use_time
	 */
	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}
	

}