package com.ims.common.system.asset;
/**
 * 
 * 类描述： <b>数据字典常量表</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-6-10 下午03:37:22
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public interface DicCons { 
	/**
	 * 用户状态 有效
	 */
	public static final String USER_STATE_VALID="1";
	
	/**
	 * 用户状态  停用
	 */
	public static final String USER_STATUS_STOP="2";
	/**
	 * 用户状态 锁定
	 */
	public static final String USER_STATUS_LOCK="3";
	
	/**
	 * ID类型：APPID
	 */
	public static final String SEQUENCE_TYPE_APPID = "1";
	
	/**
	 * ID类型：UUID
	 */
	public static final String SEQUENCE_TYPE_UUID = "2";
	
	/**
	 * ID类型：DBSequence
	 */
	public static final String SEQUENCE_TYPE_DBSEQUENCE = "3";
	
	/**
	 * 用户类型：缺省
	 */
	public static final String USER_TYPE_DEFAULT = "1";
	
	/**
	 * 用户类型：超级用户
	 */
	public static final String USER_TYPE_SUPER = "2";
	
	/**
	 * 用户类型：注册用户
	 */
	public static final String USER_TYPE_REG = "3";
	
	/**
	 * 当前状态：启用
	 */
	public static final String ENABLED_YES = "1";
	
	/**
	 * 当前状态：停用
	 */
	public static final String ENABLED_NO = "0";
	/**
	 * 编辑模式：只读
	 */
	public static final String EDITMODE_READ="0";
	/**
	 * 编辑模式：可编辑
	 */
	public static final String EDITMODE_EDIT="1";
	
	/**
	 * 角色类型：普通角色
	 */
	public static final String ROLE_TYPE_COMMON = "1";
	
	/**
	 * 角色类型：管理角色
	 */
	public static final String ROLE_TYPE_ADMIN = "2";
	
	/**
	 * 用户状态：正常
	 */
	public static final String USER_STATUS_NORMAL = "1";
	
	/**
	 * 授权类型：经办权限
	 */
	public static final String GRANT_TYPE_BIZ = "1";
	
	/**
	 * 授权类型：管理权限
	 */
	public static final String GRANT_TYPE_ADMIN = "2";
	
	
	/**
	 * 图标类型：1-小图标
	 */
	public static final String SMALL_FILE_ICON = "1";
	
	/**
	 * 图标类型：2-大图标
	 */
	public static final String BIG_FILE_ICON = "2";
	
	/**
	 * 图标类型：3-矢量图标
	 */
	public static final String VECTOR_ICON = "3";
    /**
     * 菜单类型 系统菜单
     */
	public static final String MENU_TYPE_SYS="1";
	/**
	 * 菜单类型 业务菜单
	 */
	public static final String MENU_TYPE_BIS="2";
	/**
	 * 逻辑删除标识
	 *
	 */
	public static  final class DELETE_FLAG{
		//删除
		public static final String YES = "1";
		//正常
		public static final String NO = "0";
	}
	
 }
