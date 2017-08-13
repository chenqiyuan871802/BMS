package com.beauty.common.constant;

import com.ims.common.core.asset.IMSCxt;

/**
 * 
 * 类名:com.beauty.common.constant.BeautyCons
 * 描述:美容有关常量表
 * 编写者:陈骑元
 * 创建时间:2017年4月17日 下午10:45:41
 * 修改说明:
 */

public class BeautyCons {
	/**
	 * 订单预约超期时间
	 */
	public final static String ORDRE_OVERDUE_TIME="order_overdue_time";
	/**
	 * 领取状态 未使用
	 */
	public final static String PROJECT_STATUS_NOUSE="1";
	/**
	 * 领取状态 已使用
	 */
	public final static String PROJECT_STATUS_USED="2";
	/**
	 * 领取状态 已过期
	 */
	public final static String PROJECT_STATUS_OVERDUE="3";
	/**
	 * 领取状态 已领取
	 */
	public final static String RECEIVE_STATUS_YES="1";
	/**
	 * 领取状态 未领取
	 */
	public final static String RECEIVE_STATUS_NO="0";
	
	/**
	 * 支付通知消息键
	 */
	public final static String PAY_NODIFY_KEY="pay_nodify_key";
	/**
	 * 预约通知消息键
	 */
	public final static String SUBSCRIBE_MESSAGE_KEY="subscribe_message_key";
	/**
	 * 回复信息参数键
	 */
	public final static String RESPONSE_MSG_KEY="response_msg";
	
	public final static String REQUEST_URL_KEY="request_url";
	/**
	 * 消费者取消
	 */
	public final static String CANCEL_TYPE_CUSTOM="1";
	/**
	 * 商家撤销
	 */
	public final static String CANCEL_TYPE_SHOP="2";
	
	/**
	 * 支付回执 收到
	 */
	public final static String PAY_BACK_YES="1";
	/**
	 * 支付回执 未收到
	 */
	public final static String PAY_BACK_NO="2";
	
	/**
	 * 收款
	 */
	public final static String PAY_RECORD_TYPE_PAY="1";
	/**
	 * 退款
	 */
	public final static String PAY_RECORD_TYPE_REFUND="2";
	/**
	 * 待支付
	 */
	public final static String PAY_STATUS_UNPAY="1";
	/**
	 * 支付成功
	 */
	public final static String PAY_STATUS_YES="2";
	/**
	 * 支付成功
	 */
	public final static String PAY_STATUS_NO="3";
	/**
	 * 退款成功
	 */
	public final static String REFUND_STATUS_YES="4";
	/**
	 * 退款失败
	 */
	public final static String REFUND_STATUS_NO="5";
	
	//支付方式 微信
	public final static String PAY_WAY_WECHAT="1";
	//支付方式 支付宝
	public final static String PAY_WAY_ALIPAY="2";
	//颜值
	public final static String PAY_WAY_BEAUTY="3";
	//礼包项目
	public final static String PAY_WAY_BAG="4";
	/**
	 * 定金支付
	 */
	public  final static String  PAY_TYPE_DEPOSIT="A";
	/**
	 * 美丽币支付
	 */
	public  final static String  PAY_TYPE_BEAUTY="M";
	/**
	 * APP礼包微信支付订单
	 */
	public  final static String  PAY_TYPE_BAG="B";
	
	/**
	 * APP微信订单支付
	 */
	public  final static String  PAY_TYPE_ORDER="R";
	
	/**
	 * 微信扫码支付订单
	 */
	public  final static String  PAY_TYPE_WECHAT="W";
	/**
	 * 小额支付订单
	 */
	public  final static String  PAY_TYPE_UNIFIED="U";
	/**
	 * 支付宝扫码支付订单
	 */
	public  final static String  PAY_TYPE_ALIPAY="Z";
	/**
	 * 退款处理
	 */
	public  final static String  PAY_TYPE_REFUND="T";
	/**
	 * 订单来源商家
	 */
	public static final String ORDER_SOURCE_SHOP="1";
	/**
	 * 订单来源平台
	 */
	public static final String ORDER_SOURCE_SYSTEM="2";
	/**
	 * 颜值有效状态 1有效
	 */
	public static final String VAILD_STATUS_YES="1";
	/**
	 * 颜值有效状态 过期
	 */
	public static final String VAILD_STATUS_NO="2";
	/**
	 * 礼包记录类型 购买
	 */
	public static final String BAG_RECORD_TYPE_GM="1";
	/**
	 * 礼包记录类型 分享
	 */
	public static final String BAG_RECORD_TYPE_FX="2";
	/**
	 * 颜值记录类型 购买
	 */
	public static final String BEAUTY_RECORD_TYPE_GM="1";
	/**
	 * 颜值记录类型 兑换
	 */
	public static final String BEAUTY_RECORD_TYPE_DH="2";
	/**
	 * 颜值记录类型 消费3
	 */
	public static final String BEAUTY_RECORD_TYPE_XF="3";
	/**
	 * 现金消费记录 购买美丽币
	 */
	public static final String CASH_RECORD_TYPE_BEAUTY="1";
	/**
	 * 现金消费记录 礼包
	 */
	public static final String CASH_RECORD_TYPE_BAG="2";
	/**
	 * 现金消费记录  使用消费
	 */
	public static final String CASH_RECORD_TYPE_EXPENSE="3";
	/**
	 * 现金消费记录 预约
	 */
	public static final String CASH_RECORD_TYPE_SUBSCRIBE="4";
	/**
	 * 商品上线状态 1 上线
	 */
	public static final String SHOW_STATUS_ON="1";
	/**
	 * 护理项目状态 2下架
	 */
	public static final String SHOW_STATUS_OFF="2";
	/**
	 * 状态启用
	 */
	public static final String STATUS_YES="1"; //启用
	/**
	 * 状态 禁用
	 */
	public static final String STATUS_NO="0"; //禁用
	/**
	 * 登陆方式 APP
	 */
	public static final String LOGIN_WAY_APP="1";
	/**
	 * 登陆方式 微信
	 */
	public static final String LOGIN_WAY_WECHAT="2";
	/**
	 * 验证码一小时至多发送次数
	 */
	public static final String ONE_HOUR_NUM="one_hour_num";
	
	
	/**
	 * 验证码redis缓存键
	 */
	public static final String REDIS_CHECK_CODE_KEY="_code_";
	/**
	 * 验证码一小时登陆次数
	 */
	public static final String REDIS_CHECK_COUNT_KEY="_count_";
	/**
	 * 验证码模板内容键
	 */
	public static final String CHECK_CODE_KEY="check_code_key";
	/**
	 * 短信验证码模板的
	 */
	public static final String CHECK_SMS_CODE="check_sms_code";
	/**
	 * 礼包验证码
	 */
	public static final String BAG_CHECK_SMS_CODE="bag_check_sms_code";
	/**
	 * 生成验证码位数键
	 */
	public static final String CHECK_CODE_NUM="check_code_num";
	/**
	 * 验证码有效时间
	 */
	public static final String CHECK_CODE_VAILD="check_code_vaild";
	/**
	 * 兑换码生成的位数
	 */
	public static final String CDKEY_NUM="cdkey_num";
	/**
	 * 订单类型 护理项目
	 */
    public static final  String ORDER_TYPE_PROJECT="1";
    /**
     * 订单类型 颜值
     */
    public static final String ORDER_TYPE_BEAUTY="2";
    /**
     * 订单类型 礼包
     */
    public static final String ORDER_TYPE_BAG="3";
    /**
     * 订单状态 草稿订单 只有添加预约成功才生效
     */
    public static final String ORDER_STATUS_DRAFT="0";
    /**
     * 订单状态 已预约
     */
    public static final String ORDER_STATUS_SUBSCRIBE="1";
    /**
     * 订单状态 服务中
     */
    public static final String ORDER_STATUS_SERVER="2";
    /**
     * 订单状态 待支付
     */
    public static  final String ORDER_STATUS_UNPAY="3";
    /**
     * 订单状态 已支付
     */
    public static final String ORDER_STATUS_PAY="4";
    /**
     * 订单状态 已完成
     */
    public static final String ORDER_STATUS_COMPLETE="5";
    /**
     * 订单状态 已过期
     */
    public static final String ORDER_STATUS_OVERDUE="6";
    /**
     * 订单状态 已撤销
     */
    public static final String ORDER_STATUS_UNDO="7";
	/**
	 * 欢迎的消息键
	 */
	public static final String WELCOM_MSG_KEY="welcome_msg";
	
	/**
	 * 微信状态 未关注
	 */
	public static final String WECHAT_STATUS_NO="1";
	/**
	 * 微信状态 已关注
	 */
	public static final String WECHAT_STATUS_YES="2";
	/**
	 * 微信状态 已退订
	 */
	public static final String WECHAT_STATUS_EXIT="3";
	/**
	 * 微信状态 网页授权
	 */
	public static final String WECHAT_STATUS_OAUTH="4";
	/**
	 * 兑换状态 1 未兑换
	 */
	public static final  String BOND_STATUS_NO="1";
	/**
	 * 兑换状态 2 未兑换
	 */
	public static final String BOND_STATUS_YES="2";
	/**
	 * 兑换状态 3无效
	 */
	public static final String BOND_STATUS_INVALID="3";
	/**
	 * 店铺用户类型 店主
	 */
	public static final String USER_TYPE_OWNER="1";
	/**
	 * 店铺用户类型 员工
	 */
	public static final String USER_TYPE_STAFF="2";
	/**
	 * 注册方式 微信
	 */
	public static final String ENROLL_MODE_APP="1";
	/**
	 * 注册方式 商家
	 */
	public static final String ENROLL_MODE_BIS="2";
	/**
	 * 注册方式 后台
	 */
	public static final String ENROLL_MODE_SYS="3";
	/**
	 * 店铺图片保存地址
	 */
	public static final String SHOP_IMAGE_URL="/imageFile/shopImage/";
	/**
	 * 店铺二维码保存地址
	 */
	public static final String SHOP_QRCODE_URL="/imageFile/shopQrCode/";
	/**
	 * 头像信息
	 */
	public static final String PHOTO_IMAGE_URL="/imageFile/photoImage/";
	/**
	 * 顾客头像信息
	 */
	public static final String CUSTOM_IMAGE_URL="/imageFile/customImage/";
	/**
	 * 护理项目图片
	 */
	public static final String NURSE_IMAGE_URL="/imageFile/nurseImage/";
	/**
	 * 礼包封面图片
	 */
	public static final String BAG_IMAGE_URL="/imageFile/bagImage/";
	/**
	 * 文件下划线
	 */
	public static final String FILE_SEPARATOR="/";
	/**
	 * 一元人民币兑换多少个颜值键
	 */
	public static final String EXCHANGE_BEAUTY_KEY="exchange_beauty";
	/**
	 * 颜值过期时间
	 */
	public static final String BEAUTY_OVERTIME_KEY="beauty_overtime";
	/**
	 * 禁用 或者离职
	 */
	public static final String SHOP_USER_STATUS_STOP="0";
	/**
	 * 商店用户状态 有效
	 */
	public static final String SHOP_USER_STATUS_VALID="1";
	/**
	 * 商店用户保存session键
	 */
	public static final String SHOP_USER_INFO_KEY= "shopUserInfo";
	/**
	 * 消费用户保存session键
	 */
	public static final String CUSTOM_USER_INFO_KEY= "customUserInfo";
	/**
	 * 退出类型 超时退出 1
	 */
	public static final String EXIT_TYPE_OVERTIME="1";
	/**
	 * 退出类型 自动退出2
	 */
	public static final String EXIT_TYPE_AUTO="2";
	
	public static final String TEMPLATE_KEY="template_key";
	public static final String SMS_URL="sms_url";
	public static final String SMS_APP_KEY="sms_app_key";
	public static final String SMS_APP_SRCRET="sms_app_secret";
	public static final String SMS_SIGNE="sms_signe";
	public static final String SMS_TEMPLATE_CODE="sms_template_code";
	public static final String SUBSCRIBE_DEPOSIT="subscribe_deposit";
	public static final String EVERY_TWO_LIMIT="every_two_limit";
	public static final String CANCEL_TIME="cancel_time";

}
