package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>护理项目信息表[bis_nurse_project]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-23 00:25:31
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class NurseProjectPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 项目编号
	 */
	private String project_id;
	
	/**
	 * 护理项目名称
	 */
	private String project_name;
	
	/**
	 * 类型编号
	 */
	private String type_id;
	
	/**
	 * 封面图片
	 */
	private String cover_photo;
	
	/**
	 * 封面大图片
	 */
	private String cover_big_photo;
	
	/**
	 * use_device
	 */
	private String use_device;
	
	/**
	 * 服务时长
	 */
	private Integer server_time;
	
	/**
	 * 人民币售价
	 */
	private Double rmb_price;
	
	/**
	 * 美丽币售价
	 */
	private Integer beauty_price;
	
	/**
	 * 手工价格
	 */
	private Double manual_price;
	
	/**
	 * 活动价格
	 */
	private Double active_price;
	
	/**
	 * 状态 1在售 2下架
	 */
	private String status;
	
	/**
	 * 排序号
	 */
	private Integer sort_no;
	
	/**
	 * 内容描述
	 */
	private String content;
	
	/**
	 * 是否删除0有效1删除
	 */
	private String is_del;
	
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
	
    private String type_name;
	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
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
	 * 护理项目名称
	 * 
	 * @return project_name
	 */
	public String getProject_name() {
		return project_name;
	}
	
	/**
	 * 类型编号
	 * 
	 * @return type_id
	 */
	public String getType_id() {
		return type_id;
	}
	
	/**
	 * 封面图片
	 * 
	 * @return cover_photo
	 */
	public String getCover_photo() {
		return cover_photo;
	}
	
	/**
	 * 封面大图片
	 * 
	 * @return cover_big_photo
	 */
	public String getCover_big_photo() {
		return cover_big_photo;
	}
	
	/**
	 * use_device
	 * 
	 * @return use_device
	 */
	public String getUse_device() {
		return use_device;
	}
	
	/**
	 * 服务时长
	 * 
	 * @return server_time
	 */
	public Integer getServer_time() {
		return server_time;
	}
	
	/**
	 * 人民币售价
	 * 
	 * @return rmb_price
	 */
	public Double getRmb_price() {
		return rmb_price;
	}
	
	/**
	 * 美丽币售价
	 * 
	 * @return beauty_price
	 */
	public Integer getBeauty_price() {
		return beauty_price;
	}
	
	/**
	 * 手工价格
	 * 
	 * @return manual_price
	 */
	public Double getManual_price() {
		return manual_price;
	}
	
	/**
	 * 活动价格
	 * 
	 * @return active_price
	 */
	public Double getActive_price() {
		return active_price;
	}
	
	/**
	 * 状态 1在售 2下架
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 排序号
	 * 
	 * @return sort_no
	 */
	public Integer getSort_no() {
		return sort_no;
	}
	
	/**
	 * 内容描述
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 是否删除0有效1删除
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
	 * 项目编号
	 * 
	 * @param project_id
	 */
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	
	/**
	 * 护理项目名称
	 * 
	 * @param project_name
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	/**
	 * 类型编号
	 * 
	 * @param type_id
	 */
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * 封面图片
	 * 
	 * @param cover_photo
	 */
	public void setCover_photo(String cover_photo) {
		this.cover_photo = cover_photo;
	}
	
	/**
	 * 封面大图片
	 * 
	 * @param cover_big_photo
	 */
	public void setCover_big_photo(String cover_big_photo) {
		this.cover_big_photo = cover_big_photo;
	}
	
	/**
	 * use_device
	 * 
	 * @param use_device
	 */
	public void setUse_device(String use_device) {
		this.use_device = use_device;
	}
	
	/**
	 * 服务时长
	 * 
	 * @param server_time
	 */
	public void setServer_time(Integer server_time) {
		this.server_time = server_time;
	}
	
	/**
	 * 人民币售价
	 * 
	 * @param rmb_price
	 */
	public void setRmb_price(Double rmb_price) {
		this.rmb_price = rmb_price;
	}
	
	/**
	 * 美丽币售价
	 * 
	 * @param beauty_price
	 */
	public void setBeauty_price(Integer beauty_price) {
		this.beauty_price = beauty_price;
	}
	
	/**
	 * 手工价格
	 * 
	 * @param manual_price
	 */
	public void setManual_price(Double manual_price) {
		this.manual_price = manual_price;
	}
	
	/**
	 * 活动价格
	 * 
	 * @param active_price
	 */
	public void setActive_price(Double active_price) {
		this.active_price = active_price;
	}
	
	/**
	 * 状态 1在售 2下架
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 排序号
	 * 
	 * @param sort_no
	 */
	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}
	
	/**
	 * 内容描述
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 是否删除0有效1删除
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