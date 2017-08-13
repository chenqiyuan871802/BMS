package com.beauty.common.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>店铺信息表[bis_shop]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-15 22:00:02
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class ShopPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 店铺编号
	 */
	private String shop_id;
	
	/**
	 * 店主编号
	 */
	private String owner_id;
	
	/**
	 * 商店名称
	 */
	private String shop_name;
	
	/**
	 * 商家简称
	 */
	private String short_name;
	
	/**
	 * 月目标现金收入
	 */
	private Double month_cash_in;
	
	/**
	 * 月目标消耗收入
	 */
	private Double month_expend_in;
	
	/**
	 * 商家营业执照
	 */
	private String shop_license;
	
	/**
	 * 商家标志图片
	 */
	private String shop_image;
	
	/**
	 * 详情图片
	 */
	private String shop_detail_image;
	
	/**
	 * x坐标
	 */
	private Double gps_x;
	
	/**
	 * y坐标
	 */
	private Double gps_y;
	
	/**
	 * 排序号
	 */
	private Integer sort_no;
	
	/**
	 * 店铺地址
	 */
	private String shop_address;
	
	/**
	 * 店铺二维码
	 */
	private String shop_qrcode;
	
	/**
	 * 店铺电话
	 */
	private String shop_phone;
	
	/**
	 * 店铺面积
	 */
	private Double shop_area;
	
	/**
	 * 店铺主营项目
	 */
	private String shop_project;
	
	/**
	 * 店铺类型
	 */
	private String shop_type;
	
	/**
	 * 商家介绍
	 */
	private String shop_intro;
	
	/**
	 * 显示状态 0禁用 1启用
	 */
	private String show_status;
	
	/**
	 * 是否删除 0:有效1:删除
	 */
	private String is_del;
	
	/**
	 * shop_remark
	 */
	private String shop_remark;
	
	/**
	 * 营业开始时间
	 */
	private String begin_time;
	
	/**
	 * 营业结束时间
	 */
	private String end_time;
	
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
	 * 店铺编号
	 * 
	 * @return shop_id
	 */
	public String getShop_id() {
		return shop_id;
	}
	
	/**
	 * 店主编号
	 * 
	 * @return owner_id
	 */
	public String getOwner_id() {
		return owner_id;
	}
	
	/**
	 * 商店名称
	 * 
	 * @return shop_name
	 */
	public String getShop_name() {
		return shop_name;
	}
	
	/**
	 * 商家简称
	 * 
	 * @return short_name
	 */
	public String getShort_name() {
		return short_name;
	}
	
	/**
	 * 月目标现金收入
	 * 
	 * @return month_cash_in
	 */
	public Double getMonth_cash_in() {
		return month_cash_in;
	}
	
	/**
	 * 月目标消耗收入
	 * 
	 * @return month_expend_in
	 */
	public Double getMonth_expend_in() {
		return month_expend_in;
	}
	
	/**
	 * 商家营业执照
	 * 
	 * @return shop_license
	 */
	public String getShop_license() {
		return shop_license;
	}
	
	/**
	 * 商家标志图片
	 * 
	 * @return shop_image
	 */
	public String getShop_image() {
		return shop_image;
	}
	
	/**
	 * 详情图片
	 * 
	 * @return shop_detail_image
	 */
	public String getShop_detail_image() {
		return shop_detail_image;
	}
	
	/**
	 * x坐标
	 * 
	 * @return gps_x
	 */
	public Double getGps_x() {
		return gps_x;
	}
	
	/**
	 * y坐标
	 * 
	 * @return gps_y
	 */
	public Double getGps_y() {
		return gps_y;
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
	 * 店铺地址
	 * 
	 * @return shop_address
	 */
	public String getShop_address() {
		return shop_address;
	}
	
	/**
	 * 店铺二维码
	 * 
	 * @return shop_qrcode
	 */
	public String getShop_qrcode() {
		return shop_qrcode;
	}
	
	/**
	 * 店铺电话
	 * 
	 * @return shop_phone
	 */
	public String getShop_phone() {
		return shop_phone;
	}
	
	/**
	 * 店铺面积
	 * 
	 * @return shop_area
	 */
	public Double getShop_area() {
		return shop_area;
	}
	
	/**
	 * 店铺主营项目
	 * 
	 * @return shop_project
	 */
	public String getShop_project() {
		return shop_project;
	}
	
	/**
	 * 店铺类型
	 * 
	 * @return shop_type
	 */
	public String getShop_type() {
		return shop_type;
	}
	
	/**
	 * 商家介绍
	 * 
	 * @return shop_intro
	 */
	public String getShop_intro() {
		return shop_intro;
	}
	
	/**
	 * 显示状态 0禁用 1启用
	 * 
	 * @return show_status
	 */
	public String getShow_status() {
		return show_status;
	}
	
	/**
	 * 是否删除 0:有效1:删除
	 * 
	 * @return is_del
	 */
	public String getIs_del() {
		return is_del;
	}
	
	/**
	 * shop_remark
	 * 
	 * @return shop_remark
	 */
	public String getShop_remark() {
		return shop_remark;
	}
	
	/**
	 * 营业开始时间
	 * 
	 * @return begin_time
	 */
	public String getBegin_time() {
		return begin_time;
	}
	
	/**
	 * 营业结束时间
	 * 
	 * @return end_time
	 */
	public String getEnd_time() {
		return end_time;
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
	 * 店铺编号
	 * 
	 * @param shop_id
	 */
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	/**
	 * 店主编号
	 * 
	 * @param owner_id
	 */
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	
	/**
	 * 商店名称
	 * 
	 * @param shop_name
	 */
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	/**
	 * 商家简称
	 * 
	 * @param short_name
	 */
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	
	/**
	 * 月目标现金收入
	 * 
	 * @param month_cash_in
	 */
	public void setMonth_cash_in(Double month_cash_in) {
		this.month_cash_in = month_cash_in;
	}
	
	/**
	 * 月目标消耗收入
	 * 
	 * @param month_expend_in
	 */
	public void setMonth_expend_in(Double month_expend_in) {
		this.month_expend_in = month_expend_in;
	}
	
	/**
	 * 商家营业执照
	 * 
	 * @param shop_license
	 */
	public void setShop_license(String shop_license) {
		this.shop_license = shop_license;
	}
	
	/**
	 * 商家标志图片
	 * 
	 * @param shop_image
	 */
	public void setShop_image(String shop_image) {
		this.shop_image = shop_image;
	}
	
	/**
	 * 详情图片
	 * 
	 * @param shop_detail_image
	 */
	public void setShop_detail_image(String shop_detail_image) {
		this.shop_detail_image = shop_detail_image;
	}
	
	/**
	 * x坐标
	 * 
	 * @param gps_x
	 */
	public void setGps_x(Double gps_x) {
		this.gps_x = gps_x;
	}
	
	/**
	 * y坐标
	 * 
	 * @param gps_y
	 */
	public void setGps_y(Double gps_y) {
		this.gps_y = gps_y;
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
	 * 店铺地址
	 * 
	 * @param shop_address
	 */
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	
	/**
	 * 店铺二维码
	 * 
	 * @param shop_qrcode
	 */
	public void setShop_qrcode(String shop_qrcode) {
		this.shop_qrcode = shop_qrcode;
	}
	
	/**
	 * 店铺电话
	 * 
	 * @param shop_phone
	 */
	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}
	
	/**
	 * 店铺面积
	 * 
	 * @param shop_area
	 */
	public void setShop_area(Double shop_area) {
		this.shop_area = shop_area;
	}
	
	/**
	 * 店铺主营项目
	 * 
	 * @param shop_project
	 */
	public void setShop_project(String shop_project) {
		this.shop_project = shop_project;
	}
	
	/**
	 * 店铺类型
	 * 
	 * @param shop_type
	 */
	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}
	
	/**
	 * 商家介绍
	 * 
	 * @param shop_intro
	 */
	public void setShop_intro(String shop_intro) {
		this.shop_intro = shop_intro;
	}
	
	/**
	 * 显示状态 0禁用 1启用
	 * 
	 * @param show_status
	 */
	public void setShow_status(String show_status) {
		this.show_status = show_status;
	}
	
	/**
	 * 是否删除 0:有效1:删除
	 * 
	 * @param is_del
	 */
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	
	/**
	 * shop_remark
	 * 
	 * @param shop_remark
	 */
	public void setShop_remark(String shop_remark) {
		this.shop_remark = shop_remark;
	}
	
	/**
	 * 营业开始时间
	 * 
	 * @param begin_time
	 */
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	
	/**
	 * 营业结束时间
	 * 
	 * @param end_time
	 */
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
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