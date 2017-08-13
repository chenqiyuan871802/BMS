package com.ims.common.core.asset;

/**
 * 
 * 类描述：<b>全局常量表</b> 
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:31:20
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:31:20
 * 修改备注： 
 * @version
 */
public interface IMSCons {
	 /**
	  * 短信类型  普通短信
	  */
	 public static final String SMS_TYPE_NORMAL="1";
	 /**
	  * 短信类型验证短信
	  */
	 public static final String SMS_TYPE_CHECK="2";
	 /**
	  * 短信类型 支付短信
	  */
	 public static final String SMS_TYPE_PAY="3";
	
	/**
	 * 短信发送状态成功
	 */
	public static final String SMS_STATUS_SUCCESS="1";
	/**
	 * 短信发送状态失败
	 */
	public static final String SMS_STATUS_FAILURE="0";
	/***
	 * 系统键ID
	 */
	public static final String SYSTEM = "system_";
	/**
	 * 开关 开
	 */
	public static final String SWITCH_ON="on";
	/**
	 * 开关 关
	 */
	public static final String SWITCH_OFF="off";
	
	 public static final String EMPTY = "";
	/**
	 * 布尔值true
	 */
	public static final String TRUE="true";
	/*
	 * 布尔值false
	 */
	public static final String FALSE="false";
	
	public static final String APP_ID_KEY="IMS:id_";
	 
	public static final String SUPER_ADMIN="super";
	/**
	 * 超级用户的ID
	 */
	public static final String SUPER_ADMIN_USER_ID="cb33c25f5c664058a111a9b876152317";
	/**
	 * 当前登陆用户ID
	 */
	public static final String LOGIN_USER_ID="login_user_id";
	/**
	 * redis是否在线的键
	 * 
	 */
	public static final String REDIS_ISLIVE_KEY="redis.isLive";
    /**
     * 密码秘钥
     */
	public static final String PASSWORD_KEY = "IMSSYTEM";
	/**
	 * 界面风格 经典风格 1
	 */
	public static final String STYLE_CLASSIC="1";
	/**
	 * 界面风格  顶部布局
	 */
	public static final String STYLE_TOP_LAYOUT="2";
	/**
	 * 日期格式
	 */
	
	public static final String DATE = "yyyy-MM-dd";

	/**
	 * 日期时间格式
	 */
	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期时间格式 精确到分
	 */
	public static final String DATETIMEMIN = "yyyy-MM-dd HH:mm";
	
	/**
	 * 存储过程业务成功状态码：成功
	 */
	public static final int PROC_SUCCESS = 1;

	/**
	 * 业务状态码：成功
	 */
	public static final int SUCCESS = 1;
	
	/**
	 * 业务状态吗：警告
	 */
	public static final int WARN = 0;

	/**
	 * 业务状态码：失败
	 */
	public static final int ERROR = -1;

	/**
	 * 字符布尔值：真
	 */
	public static final String STR_TRUE = "1";

	/**
	 * 字符布尔值：假
	 */
	public static final String STR_FALSE = "0";

	/**
	 * 系统运行模式：开发模式
	 */
	public static final String RUNAS_DEV = "0";

	/**
	 * 系统运行模式：生产模式
	 */
	public static final String RUNAS_PRO = "1";
	/**
	 * 逗号分隔符
	 */
	public static final String MARK_CSV=",";
	/**
	 * 顿号分隔符
	 */
	public static final String MARK_PAUSE="、";
	/**
	 * 邮箱相关的参数
	 */
	public static final class EMAILCONS {
		/**
	     * 密送人
	     */
	    public static final String BCC = "BCC";

	    /**
	     * 抄送人
	     */
	    public static final String CC = "CC";

	    /**
	     * smtp服务端口，默认使用25，为了兼容性，请指定465，qq和163支持25和465，新浪支持465，搜狐支持465，如果没有，默认为465
	     */
	    public static final String DEFAULTE_EMAIL_PORT = "465";

	    /**
	     * socket连接类，使用新浪邮箱必须指定，如果没有默认为javax.net.ssl.SSLSocketFactory
	     */
	    public static final String DEFAULT_EMAIL_SOCKETCLASS = "javax.net.ssl.SSLSocketFactory";

	    /**
	     * 如果smtp连接不成功，会自动转换成socket协议，这里指定它的端口，请指定465，如果没有，默认为465
	     */
	    public static final String DEFAULT_EMAIL_SOCKETPORT = "465";

	    /**
	     * 邮件验证
	     */
	    public static final String EMAIL_AUTH = "mail.smtp.auth";

	    /**
	     * 邮件字符编码
	     */
	    public static final String EMAIL_CHARSET = "text/html;charset=UTF-8";

	    /**
	     * smtp服务端口，默认使用25，为了兼容性，请指定465，qq和163支持25和465，新浪支持465，搜狐支持465
	     */
	    public static final String EMAIL_POST = "mail.smtp.port";

	    /**
	     * 指定邮件接收协议
	     */
	    public static final String EMAIL_PROTOCOL = "mail.store.protocol";

	    /**
	     * 邮件协议发送协议smtp
	     */
	    public static final String EMAIL_SMTP = "smtp";

	    /**
	     * socket连接类，使用新浪邮箱必须指定，默认为javax.net.ssl.SSLSocketFactory
	     */
	    public static final String EMAIL_SOCKETCLASS = "mail.smtp.socketFactory.class";

	    /**
	     * 如果smtp连接不成功，会自动转换成socket协议，这里指定它的端口，请指定465
	     */
	    public static final String EMAIL_SOCKETPORT = "mail.smtp.socketFactory.port";

	    /**
	     * 邮件协议接收协议imap
	     */
	    public static final String IMAP = "imap";
	    public static final String IMAP_NOT_SSL_PROT = "143";
	    public static final String IMAP_SSL_PROT = "993";

	    /**
	     * 163邮箱imap地址
	     */
	    public static final String IMAP163 = "imap.163.com";

	    public static final String NOT_IMAP_NOT_SSL_PORT = "110";
	    public static final String NOT_IMAP_SSL_PORT = "995";

	    /**
	     * 163邮箱pop地址
	     */
	    public static final String POP163 = "pop.163.com";
	    /**
	     * qq邮箱imap地址
	     */
	    public static final String QQIMAP = "imap.qq.com";

	    /**
	     * qq邮箱pop地址
	     */
	    public static final String QQPOP = "pop.qq.com";

	    /**
	     * qq邮箱smtp地址
	     */
	    public static final String QQSMTP = "smtp.qq.com";

	    /**
	     * sina邮箱imap地址
	     */
	    public static final String SINAIMAP = "imap.sina.com";

	    /**
	     * sina邮箱pop地址
	     */
	    public static final String SINAPOP = "pop.sina.com";

	    /**
	     * sina邮箱smtp地址
	     */
	    public static final String SINASMTP = "smtp.sina.com";

	    /**
	     * 163邮箱smtp地址
	     */
	    public static final String SMTP163 = "smtp.163.com";

	    /**
	     * sohu邮箱imap地址
	     */
	    public static final String SOHUIMAP = "imap.sohu.com";

	    /**
	     * sohu邮箱pop地址
	     */
	    public static final String SOHUPOP = "pop.sohu.com";

	    /**
	     * sohu邮箱smtp地址
	     */
	    public static final String SOHUSMTP = "smtp.sohu.com";

	    /**
	     * 收件人
	     */
	    public static final String TO = "TO";


	}
	/**
	 * 是否标识
	 */
	public static final class IS {
		public static final String YES = "1";
		public static final String NO = "0";
	}

	/**
	 * Json输出模式。格式化输出模式。
	 */
	public static final String JSON_FORMAT = "0";

	/**
	 * Ext Reader对象的totalProperty属性名称
	 */
	public static final String READER_TOTAL_PROPERTY = "total";

	/**
	 * Ext Reader对象的root属性名称
	 */
	public static final String READER_ROOT_PROPERTY = "rows";

	/**
	 * Dto对象中的内部变量：交易状态码
	 */
	public static final String APPCODE_KEY = "appcode";

	/**
	 * Dto对象中的内部变量：交易状态信息
	 */
	public static final String APPMSG_KEY = "appmsg";
	
	/**
	 * 请求相应成功标志
	 */
	public static final String REQUEST_SUCCESS = "success";
	/**
	 * 请求错误
	 */
	public static final String REQUEST_ERROR = "error";

	/**
	 * 控制台醒目标记1
	 */
	public static final String CONSOLE_FLAG1 = "● ";

	/**
	 * 控制台醒目标记2
	 */
	public static final String CONSOLE_FLAG2 = "●● ";
	
	/**
	 * 控制台醒目标记3
	 */
	public static final String CONSOLE_FLAG3 = "●●● ";

	/**
	 * UserInfo对象在Session中的key，Dto中的当前UserInfo也使用此Key
	 */
	public static final String USERINFOKEY = "sessionUserInfo";
	
	/**
	 * (公众页)UserInfo对象在Session中的key，Dto中的当前UserInfo也使用此Key
	 */
	public static final String USERINFOJF = "sessionUserInfoJf";
	
	/**
	 * session保存验证码
	 */
	public static final String AUTHCODE = "authCode";

	/**
	 * 获取前端UI选择模型选中的标识字段的数组，前端请求参数key应为：aos_rows_，方能取到。
	 */
	public static final String IMS_ROWS_ = "ims_rows_";
    
	
	/**
	 * 数序运算SQL的参数Dto中的运算表达式Key。
	 */
	public static final String CALCEXPR = "_expr";

	/**
	 * 系统皮肤：blue
	 */
	public static final String SKIN_BLUE = "blue";

	/**
	 * 系统皮肤：gray
	 */
	public static final String SKIN_GRAY = "gray";

	/**
	 * 系统皮肤：neptune
	 */
	public static final String SKIN_NEPTUNE = "neptune";

	/**
	 * 系统皮肤：aos
	 */
	public static final String SKIN_AOS = "aos";

	/**
	 * DTO缺省字符串Key
	 */
	public static final String DEFAULT_STRING_KEY = "_default_string_a";

	/**
	 * DTO缺省List Key
	 */
	public static final String DEFAULT_LIST_KEY = "_default_list_a";

	/**
	 * DTO缺省BigDecimal Key
	 */
	public static final String DEFAULT_BIGDECIMAL_KEY = "_default_bigdecimal_a";

	/**
	 * DTO缺省Integer Key
	 */
	public static final String DEFAULT_INTEGER_KEY = "_default_integer_a";

	/**
	 * DTO缺省Boolean Key
	 */
	public static final String DEFAULT_BOOLEAN_KEY = "_default_boolean_a";
	
	/**
	 * 会话中验证码的缺省Key
	 */
	public static final String VERCODE = "_vercode";
	
	/**
	 * WEBAPPCXT是否成功的标志KEY
	 */
	public static final String WEBAPPCXT_IS_SUCCESS_KEY = "_webappcxt_is_success";
	
	/**
	 * ContextPath在系统变量中的Key
	 */
	public static final String CXT_KEY = "cxt";
	
	/**
	 * JOSQL AOSListUtils 中使用的KEY
	 */
	public static final String IMSLIST_KEY = ":IMSList";
	
	/**
	 * 排序器在参数对象中的Key
	 */
	public static final String ORDER_KEY = "_order";
	
	/**
	 * 点击菜单节点自动拼接到URL上面的菜单模块编号参数
	 */
	public static final String MODULE_ID_KEY = "aos_module_id_";
	
	/**
	 * 点击主页面上二级导航页面的页面ID参数
	 */
	public static final String PAGE_ID_KEY = "aos_page_id_";
	
	/**
	 * 角色授权模式。1：可见当前管理员创建的角色和当前管理员所属组织的其他管理员创建的角色。
	 */
	public static final String ROLE_GRANT_MODE_NOCASCADE = "1";
	
	/**
	 * 角色授权模式。2：可见当前管理员创建的角色和当前管理员所属组织及其下级子孙组织的其他管理员创建的角色。
	 */
	public static final String ROLE_GRANT_MODE_CASCADE = "2";
	
	/**
	 * 快捷菜单布局风格。1：平铺。
	 */
	public static final String NAV_QUICK_LAYOUT_FLAT = "1";
	
	/**
	 * 快捷菜单布局风格。2：树状。
	 */
	public static final String NAV_QUICK_LAYOUT_TREE = "2";
	
	/**
	 * JDBC执行模式。1：只读模式。
	 */
	public static final String JDBC_EXECUTE_ONLYREAD = "1";
	
     /**
	 * 科目分类根ID
	 */
	public static final String TREE_ROOT_ID="0";
	/**
	 * 科目根节点名称
	 */
	public static final String TREE_ROOT_NAME="全部分类";

	/**
	 * 科目根节点语义ID
	 */
	public static final String TREE_ROOT_CASCADE_ID="0";
	/**
	 * 树的叶节点图标CSS类名
	 */
	public static final String TREE_LEAF_INCONCLS="tree_leaf";
	/**
	 * 树的节点打开
	 */
	public static final String TREE_STATE_OPEN="open";
	/**
	 * 树的节点关闭
	 */
	public static final String TREE_STATE_CLOSED="closed";
	/**
	 * 菜单的根节点的名称
	 */
	public static final String MENU_ROOT_NAME="功能菜单";
	/**
	 * 组织机构根节点名称
	 */
	public static final String DEPT_ROOT_NAME="组织机构";
	/**
	 * 菜单根节点图标
	 */
	public static final String MENU_ROOT_ICONCLS="book";
	/**
	 * 组织机构根节点图标
	 */
	public static final String DEPT_ROOT_ICONCLS="dept_config";
	/**
	 * 微信一级菜单最多创建3个
	 */
	public static final int  WECHAT_FIRST_MENU_MAX=3;
	/**
	 *  微信二级菜单最多创建5个
	 */
	public static final int  WECHAT_SECOND_MENU_MAX=5;
	/**
	 * JS头<br>
	 */
	public static final String SCRIPT_START = "<script type=\"text/javascript\">\n";
	
	/**
	 * JS尾<br>
	 */
	public static final String SCRIPT_END = "\n</script>";
	/**
	 * 大写英文字母
	 */
	public static final String UPPER_LETTER = "ABCDEFGHIJKLMNOPKRSTUVWXYZ";
	/**
	 * 小写的英文字母
	 */
	public static final String LOWCASE_LETTER = "abcdefghijklmnopqrstuvwxyz";
	/**
	 * 数字字母
	 */
	public static final String NUMBER_LETTER= "0123456789";
	
	
	/**
	 * 对象删除规则
	 *
	 */
	public static  final class OBJECT_DELETE_RULE{
		//逻辑删除
		public static final String UPDATE = "update";
		//物理删除
		public static final String DELETE = "delete";
	}

	//缓存容器
	public static  final class CACHE{
		//字典、参数缓存
		public static final String IMSRESOURCECACHE = "imsResourceCache";
		//会话缓存
		public static final String IMSSESSIONCACHE = "imsSessionCache";
	}
	/**
	 * Cache对象前缀
	 *
	 */
	public static  final class CACHE_PREFIX{
		//全局参数
		public static final String PARAM = "ims.cache.param.key_:";
		//字典
		public static final String DIC = "ims.cache.dic.key_:";
		
	}
	/**
	 * 随机混合的类型
	 *
	 */
	public static  final class RANDOM_TYPE{
		//全部参与混合
		public static final String  FULL= "1";
		//大写
		public static final String UPPER = "2";
		//小写
		public static final String LOWCASE = "3";
		//数字
		public static final String NUMBER = "4";
		//大写与数字混合
		public static final String UPPER_NUMBER = "5";
		//大写与小写混合
		public static final String  UPPER_LOWCASE = "6";
		//小写与数字混合
		public static final String LOWCASE_NUMBER = "7";
		
	}

	
}
