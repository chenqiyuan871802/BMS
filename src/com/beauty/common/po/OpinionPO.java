package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>返回意见[bis_opinion]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-17 11:27:21
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class OpinionPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * opinion_id
	 */
	private String opinion_id;
	
	/**
	 * custom_user_id
	 */
	private String custom_user_id;
	
	/**
	 * content
	 */
	private String content;
	
	/**
	 * create_time
	 */
	private Date create_time;
	

	/**
	 * opinion_id
	 * 
	 * @return opinion_id
	 */
	public String getOpinion_id() {
		return opinion_id;
	}
	
	/**
	 * custom_user_id
	 * 
	 * @return custom_user_id
	 */
	public String getCustom_user_id() {
		return custom_user_id;
	}
	
	/**
	 * content
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * create_time
	 * 
	 * @return create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	

	/**
	 * opinion_id
	 * 
	 * @param opinion_id
	 */
	public void setOpinion_id(String opinion_id) {
		this.opinion_id = opinion_id;
	}
	
	/**
	 * custom_user_id
	 * 
	 * @param custom_user_id
	 */
	public void setCustom_user_id(String custom_user_id) {
		this.custom_user_id = custom_user_id;
	}
	
	/**
	 * content
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * create_time
	 * 
	 * @param create_time
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	

}