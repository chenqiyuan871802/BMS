package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>礼包信息[bis_nurse_bag]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-13 00:56:54
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class NurseBagPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 礼包编号
	 */
	private String bag_id;
	
	/**
	 * 礼包名称
	 */
	private String bag_name;
	
	/**
	 * 礼包总价格
	 */
	private Double bag_total_price;
	
	/**
	 * 礼包封面图片
	 */
	private String cover_photo;
	
	/**
	 * cover_big_photo
	 */
	private String cover_big_photo;
	
	/**
	 * 开卡数量
	 */
	private Integer open_card_num;
	
	/**
	 * 卡剩余量
	 */
	private Integer remain_num;
	
	/**
	 * 最大购买量
	 */
	private Integer max_buy_num;
	
	/**
	 * 最少购买量
	 */
	private Integer min_buy_num;
	
	/**
	 * 上架时间
	 */
	private Date putaway_time;
	
	/**
	 * 下架时间
	 */
	private Date soldout_time;
	
	/**
	 * 礼包过期时间
	 */
	private Integer overdue_time;
	
	/**
	 * content
	 */
	private String content;
	
	/**
	 * 状态 1 在售 2 下架
	 */
	private String status;
	
	/**
	 * sort_no
	 */
	private Integer sort_no;
	
	/**
	 * 是否删除 0 有效 1 删除
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
	
	/**
	 * 礼包介绍
	 */
	private String bag_introduce;
	
	/**
	 * 购买次数
	 */
	private Integer buy_count;
	

	/**
	 * 礼包编号
	 * 
	 * @return bag_id
	 */
	public String getBag_id() {
		return bag_id;
	}
	
	/**
	 * 礼包名称
	 * 
	 * @return bag_name
	 */
	public String getBag_name() {
		return bag_name;
	}
	
	/**
	 * 礼包总价格
	 * 
	 * @return bag_total_price
	 */
	public Double getBag_total_price() {
		return bag_total_price;
	}
	
	/**
	 * 礼包封面图片
	 * 
	 * @return cover_photo
	 */
	public String getCover_photo() {
		return cover_photo;
	}
	
	/**
	 * cover_big_photo
	 * 
	 * @return cover_big_photo
	 */
	public String getCover_big_photo() {
		return cover_big_photo;
	}
	
	/**
	 * 开卡数量
	 * 
	 * @return open_card_num
	 */
	public Integer getOpen_card_num() {
		return open_card_num;
	}
	
	/**
	 * 卡剩余量
	 * 
	 * @return remain_num
	 */
	public Integer getRemain_num() {
		return remain_num;
	}
	
	/**
	 * 最大购买量
	 * 
	 * @return max_buy_num
	 */
	public Integer getMax_buy_num() {
		return max_buy_num;
	}
	
	/**
	 * 最少购买量
	 * 
	 * @return min_buy_num
	 */
	public Integer getMin_buy_num() {
		return min_buy_num;
	}
	
	/**
	 * 上架时间
	 * 
	 * @return putaway_time
	 */
	public Date getPutaway_time() {
		return putaway_time;
	}
	
	/**
	 * 下架时间
	 * 
	 * @return soldout_time
	 */
	public Date getSoldout_time() {
		return soldout_time;
	}
	
	/**
	 * 礼包过期时间
	 * 
	 * @return overdue_time
	 */
	public Integer getOverdue_time() {
		return overdue_time;
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
	 * 状态 1 在售 2 下架
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * sort_no
	 * 
	 * @return sort_no
	 */
	public Integer getSort_no() {
		return sort_no;
	}
	
	/**
	 * 是否删除 0 有效 1 删除
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
	 * 礼包介绍
	 * 
	 * @return bag_introduce
	 */
	public String getBag_introduce() {
		return bag_introduce;
	}
	
	/**
	 * 购买次数
	 * 
	 * @return buy_count
	 */
	public Integer getBuy_count() {
		return buy_count;
	}
	

	/**
	 * 礼包编号
	 * 
	 * @param bag_id
	 */
	public void setBag_id(String bag_id) {
		this.bag_id = bag_id;
	}
	
	/**
	 * 礼包名称
	 * 
	 * @param bag_name
	 */
	public void setBag_name(String bag_name) {
		this.bag_name = bag_name;
	}
	
	/**
	 * 礼包总价格
	 * 
	 * @param bag_total_price
	 */
	public void setBag_total_price(Double bag_total_price) {
		this.bag_total_price = bag_total_price;
	}
	
	/**
	 * 礼包封面图片
	 * 
	 * @param cover_photo
	 */
	public void setCover_photo(String cover_photo) {
		this.cover_photo = cover_photo;
	}
	
	/**
	 * cover_big_photo
	 * 
	 * @param cover_big_photo
	 */
	public void setCover_big_photo(String cover_big_photo) {
		this.cover_big_photo = cover_big_photo;
	}
	
	/**
	 * 开卡数量
	 * 
	 * @param open_card_num
	 */
	public void setOpen_card_num(Integer open_card_num) {
		this.open_card_num = open_card_num;
	}
	
	/**
	 * 卡剩余量
	 * 
	 * @param remain_num
	 */
	public void setRemain_num(Integer remain_num) {
		this.remain_num = remain_num;
	}
	
	/**
	 * 最大购买量
	 * 
	 * @param max_buy_num
	 */
	public void setMax_buy_num(Integer max_buy_num) {
		this.max_buy_num = max_buy_num;
	}
	
	/**
	 * 最少购买量
	 * 
	 * @param min_buy_num
	 */
	public void setMin_buy_num(Integer min_buy_num) {
		this.min_buy_num = min_buy_num;
	}
	
	/**
	 * 上架时间
	 * 
	 * @param putaway_time
	 */
	public void setPutaway_time(Date putaway_time) {
		this.putaway_time = putaway_time;
	}
	
	/**
	 * 下架时间
	 * 
	 * @param soldout_time
	 */
	public void setSoldout_time(Date soldout_time) {
		this.soldout_time = soldout_time;
	}
	
	/**
	 * 礼包过期时间
	 * 
	 * @param overdue_time
	 */
	public void setOverdue_time(Integer overdue_time) {
		this.overdue_time = overdue_time;
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
	 * 状态 1 在售 2 下架
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * sort_no
	 * 
	 * @param sort_no
	 */
	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}
	
	/**
	 * 是否删除 0 有效 1 删除
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
	
	/**
	 * 礼包介绍
	 * 
	 * @param bag_introduce
	 */
	public void setBag_introduce(String bag_introduce) {
		this.bag_introduce = bag_introduce;
	}
	
	/**
	 * 购买次数
	 * 
	 * @param buy_count
	 */
	public void setBuy_count(Integer buy_count) {
		this.buy_count = buy_count;
	}
	

}