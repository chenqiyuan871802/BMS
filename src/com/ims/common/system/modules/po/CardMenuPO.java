package com.ims.common.system.modules.po;

import java.util.List;

import com.ims.common.core.matatype.BasePO;
/**
 * 
 * 类描述： 卡片菜单实体类
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：Oct 11, 2016 12:07:19 AM
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class CardMenuPO  extends BasePO{
	
		  
	    
	private static final long serialVersionUID = 1L;
	/**
	 * 一级菜单名称
	 */
	private String menuName;
	/**
	 * 图标样式
	 */
	private String iconCls;
	/**
	 * 子菜单
	 */
	private List<MenuPO> subMenu;
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<MenuPO> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuPO> subMenu) {
		this.subMenu = subMenu;
	}

}
