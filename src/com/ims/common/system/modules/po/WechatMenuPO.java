package com.ims.common.system.modules.po;


import com.ims.common.core.matatype.BasePO;
import java.util.Date;
/**
 * 
 * 类描述： <b>微信菜单信息[wechat_menu]数据持久化对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-02 12:29:50
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */

public class WechatMenuPO extends BasePO {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单编号
	 */
	private String menu_id;
	
	/**
	 * 菜单名称
	 */
	private String menu_name;
	
	/**
	 * 父级菜单编号
	 */
	private String parent_id;
	
	/**
	 * 菜单类型
	 */
	private String menu_type;
	
	/**
	 * 菜单键值
	 */
	private String menu_key;
	
	/**
	 * 菜单URL
	 */
	private String url;
	
	/**
	 * media_id
	 */
	private String media_id;
	
	/**
	 * 消息模板编号
	 */
	private String template_id;
	
	/**
	 * 是否自动展开
	 */
	private String is_auto_expand;
	
	/**
	 * 排序
	 */
	private Integer sort_no;
	
	/**
	 * 备注
	 */
	private String remark;
	
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
	
	private int child_count;
	

	public int getChild_count() {
		return child_count;
	}

	public void setChild_count(int child_count) {
		this.child_count = child_count;
	}

	/**
	 * 菜单编号
	 * 
	 * @return menu_id
	 */
	public String getMenu_id() {
		return menu_id;
	}
	
	/**
	 * 菜单名称
	 * 
	 * @return menu_name
	 */
	public String getMenu_name() {
		return menu_name;
	}
	
	/**
	 * 父级菜单编号
	 * 
	 * @return parent_id
	 */
	public String getParent_id() {
		return parent_id;
	}
	
	/**
	 * 菜单类型
	 * 
	 * @return menu_type
	 */
	public String getMenu_type() {
		return menu_type;
	}
	
	/**
	 * 菜单键值
	 * 
	 * @return menu_key
	 */
	public String getMenu_key() {
		return menu_key;
	}
	
	/**
	 * 菜单URL
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * media_id
	 * 
	 * @return media_id
	 */
	public String getMedia_id() {
		return media_id;
	}
	
	/**
	 * 消息模板编号
	 * 
	 * @return template_id
	 */
	public String getTemplate_id() {
		return template_id;
	}
	
	/**
	 * 是否自动展开
	 * 
	 * @return is_auto_expand
	 */
	public String getIs_auto_expand() {
		return is_auto_expand;
	}
	
	/**
	 * 排序
	 * 
	 * @return sort_no
	 */
	public Integer getSort_no() {
		return sort_no;
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
	 * 菜单编号
	 * 
	 * @param menu_id
	 */
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	/**
	 * 菜单名称
	 * 
	 * @param menu_name
	 */
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	/**
	 * 父级菜单编号
	 * 
	 * @param parent_id
	 */
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
	/**
	 * 菜单类型
	 * 
	 * @param menu_type
	 */
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	
	/**
	 * 菜单键值
	 * 
	 * @param menu_key
	 */
	public void setMenu_key(String menu_key) {
		this.menu_key = menu_key;
	}
	
	/**
	 * 菜单URL
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * media_id
	 * 
	 * @param media_id
	 */
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	
	/**
	 * 消息模板编号
	 * 
	 * @param template_id
	 */
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	
	/**
	 * 是否自动展开
	 * 
	 * @param is_auto_expand
	 */
	public void setIs_auto_expand(String is_auto_expand) {
		this.is_auto_expand = is_auto_expand;
	}
	
	/**
	 * 排序
	 * 
	 * @param sort_no
	 */
	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}
	
	/**
	 * 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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