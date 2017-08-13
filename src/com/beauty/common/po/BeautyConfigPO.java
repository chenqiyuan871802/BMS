package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>美研币配置[bis_beauty_config]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-29 08:57:02
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class BeautyConfigPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 配置编号
	 */
	private String config_id;
	
	/**
	 * 购买数量
	 */
	private Integer buy_num;
	
	/**
	 * 赠送数量
	 */
	private Integer give_num;
	
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
	 * 备注
	 */
	private String remark;
	

	/**
	 * 配置编号
	 * 
	 * @return config_id
	 */
	public String getConfig_id() {
		return config_id;
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
	 * 赠送数量
	 * 
	 * @return give_num
	 */
	public Integer getGive_num() {
		return give_num;
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
	 * 备注
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	

	/**
	 * 配置编号
	 * 
	 * @param config_id
	 */
	public void setConfig_id(String config_id) {
		this.config_id = config_id;
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
	 * 赠送数量
	 * 
	 * @param give_num
	 */
	public void setGive_num(Integer give_num) {
		this.give_num = give_num;
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
	
	/**
	 * 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}