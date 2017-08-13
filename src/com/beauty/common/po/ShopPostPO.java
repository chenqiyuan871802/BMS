package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>店铺职位信息表[bis_shop_post]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:10:11
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class ShopPostPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 职位编号
	 */
	private String post_id;
	
	/**
	 * 职位名称
	 */
	private String post_name;
	
	/**
	 * 职位编码
	 */
	private String post_code;
	
	/**
	 * 职位描述
	 */
	private String post_desc;
	
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
	 * 职位编号
	 * 
	 * @return post_id
	 */
	public String getPost_id() {
		return post_id;
	}
	
	/**
	 * 职位名称
	 * 
	 * @return post_name
	 */
	public String getPost_name() {
		return post_name;
	}
	
	/**
	 * 职位编码
	 * 
	 * @return post_code
	 */
	public String getPost_code() {
		return post_code;
	}
	
	/**
	 * 职位描述
	 * 
	 * @return post_desc
	 */
	public String getPost_desc() {
		return post_desc;
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
	 * 职位编号
	 * 
	 * @param post_id
	 */
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	
	/**
	 * 职位名称
	 * 
	 * @param post_name
	 */
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	
	/**
	 * 职位编码
	 * 
	 * @param post_code
	 */
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	
	/**
	 * 职位描述
	 * 
	 * @param post_desc
	 */
	public void setPost_desc(String post_desc) {
		this.post_desc = post_desc;
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