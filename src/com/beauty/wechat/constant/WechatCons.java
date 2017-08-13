package com.beauty.wechat.constant;

/**
 * 
 * 类名:com.beauty.wechat.constant.WechatCons
 * 描述:微信常量表
 * 编写者:陈骑元
 * 创建时间:2017年4月29日 下午3:36:20
 * 修改说明:
 */
public class WechatCons {
	
	/**
	 * 弹出授权页面 1
	 */
	public static final String GRANT_YES="1";
	/**
	 * 不弹出授权页面
	 */
	public  static final String GRANT_NO="0";
	
	/**
	 * 获取微信access_token 地址
	 */
	public static final String  ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?1=1";
	/**
	 * 用户基本信息
	 */
	public static final String  USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?1=1";
	/**
	 * 创建菜单URL 
	 */
	public static final String  CREATE_MENU_URL=" https://api.weixin.qq.com/cgi-bin/menu/create?1=1";
	/**
	 * 发送模板消息 
	 */
	public static final String  SEND_TEMPLATE_MESSAGE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?1=1";
	
	/**
	 * 用户同意授权，获取code URL地址
	 */
	public static final String OAUTH2_AUTHORIZE_URL="https://open.weixin.qq.com/connect/oauth2/authorize";
	/**
	 * 通过code换取网页授权access_token URL地址
	 */
	public static final String  OAUTH2__TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?1=1";
	/**
	 * 通过网页授权获取用户信息
	 */
	public static final String  OAUTH2__URER_URL="https://api.weixin.qq.com/sns/userinfo?1=1";
	/**
	 * js权限签名算法获取
	 */
	public static final String  JSAPI_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";
	/**
	 * 上传文件图片URL
	 */
	public static final String UPLOADIMG_URL ="https://api.weixin.qq.com/cgi-bin/media/uploadimg?1=1";
	/**
	 * 下载文件图片URL
	 */
	public static final String DOWNLOADIMG_URL ="https://api.weixin.qq.com/cgi-bin/media/get?1=1";
	/**
	 * 群发文本信息
	 */
	public static final String SEND_MESSAGE_URL="https://api.weixin.qq.com/cgi-bin/message/mass/send?1=1";
	/**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 事件类型：VIEW(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";
    /**
     * 上报地理位置信息
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    /**
	 * JSON数据格式类型
	 */
	public static String JSON_DATA_TYPE = "JSON";
	/**
	 * keg--value参数类型
	 */
	public static String PARAM_DATA_TYPE = "PARAM";
	
	
	/**
	 * post请求类型
	 */
	public static String REQUEST_POST = "POST";
	
	/**
	 * get请求类型
	 */
	public static String REQUEST_GET = "GET";
	
	/**
	 * 返回的错误代码信息
	 */
	public static String RETURN_ERROR_INFO_CODE = "errcode";
	
	/**
	 *微信 执行成功
	 */
	public static String RETURN_OK="0";
	

	/**
	 * 返回的错误信息
	 */
	public static String RETURN_ERROR_INFO_MSG = "errmsg";
	/**
	 * access_token的键
	 */
	public static String ACCESS_TOKEN_KEY="access_token";
	/**
	 * jsapi权限签名算法键
	 */
	public static String TICKET_KEY="jsapi_ticket";


}
