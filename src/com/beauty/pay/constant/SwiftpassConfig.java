package com.beauty.pay.constant;

import com.beauty.common.constant.BeautyCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSPropertiesUtil;

/**
 * 
 * 类名:com.beauty.pay.constant.SwiftpassConfig 描述:支付参数配置文件 编写者:陈骑元 创建时间:2017年6月3日
 * 上午8:45:11 修改说明:
 */
public class SwiftpassConfig {
	/**
	 * 商户号
	 */
	public final static String mch_id = IMSPropertiesUtil.getString("mch_id");
	/**
	 * 商户秘钥
	 */
	public final static String key = IMSPropertiesUtil.getString("key");
	/**
	 * 接口请求地址
	 */
	public final static String req_url = IMSPropertiesUtil.getString("req_url");
	/**
	 * 通知回调地址
	 */
	public final static String notify_url =IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY)+"/"+ IMSPropertiesUtil.getString("notify_url");

	public  final static String version = "1.1";
	public  final static String charset = "UTF-8";
	public  final static String sign_type = "MD5";
	
	

}
