package com.ims.common.core.asset;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.ShopUserPO;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.matatype.impl.HashDto;
import com.ims.common.core.support.redis.JedisUtil;
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.system.service.ResourceCacheService;

import com.ims.common.system.modules.po.DictionaryPO;
import com.ims.common.system.modules.po.ParamPO;

/**
 * 
 * 类描述： <b>应用系统上下文</b> 
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-6-10 上午10:36:52
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class IMSCxt {
	private static Log log = LogFactory.getLog(IMSCxt.class);
	/**
	 * 资源缓存管理器
	 */
	private static ResourceCacheService resourceCacheService=(ResourceCacheService)getBean("resourceCacheService");;
	/**
	 * 缓存管理器
	 */
	private static CacheManager cacheManager = getCacheManager();
	
	/**
	 * 将Request请求参数封装为Dto对象
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("all")
	public static Dto getParamAsDto(HttpServletRequest request) {
		Dto dto = new HashDto();
		Map<String, String[]> map = request.getParameterMap();
		Iterator<String> keyIterator = (Iterator) map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = "";
			if(map.get(key).length<=1){
				value=map.get(key)[0];
			}else{
				value=StringUtils.join(map.get(key), ",");
			}
			dto.put(key, value);
		}
		if(dto.containsKey("page")){   //分页参数转化
			int page=dto.getInteger("page");
			int limit=dto.getInteger("rows");
			int start=(page-1)*limit;
			dto.put("limit",limit);
			dto.put("start",start );
			dto.remove("page");
			dto.remove("rows");
		}
		UserPO userPO = IMSCxt.getUserInfo(request.getSession());
		if(IMSUtils.isNotEmpty(userPO)){
			dto.put(IMSCons.LOGIN_USER_ID, userPO.getUser_id());
		}
		
		
		return dto;
	}

	/**
	 * 向Response流输出字符串
	 * <p/>
	 * <p>
	 * 输出字符串不做任何加工，原样输出。
	 * 
	 * @param response
	 * @param outString
	 *            输出字符串
	 */
	public static void writeRaw(HttpServletResponse response, String outString) {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			response.getWriter().write(outString == null ? "" : outString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向Response流输出业务数据字符串
	 * <p/>
	 * 如果传入的参数是一个Json字符串则直接输出。若为一个常规字符串则将其自动作为Key为appmsg的json属性值输出。(缺省追加参数：
	 * appcode=1 success=true)
	 * 
	 * @param response
	 * @param outString
	 *            输出符合Json格式的业务数据字符串 / 提示信息常规字符串
	 */
	public static void write(HttpServletResponse response, String outString) {
		
		if (StringUtils.indexOf(outString, "{") == -1 && !outString.equals("[]")) {
			outString = writeMsg(outString);
		}
		try {
			response.getWriter().write(outString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向Response流输出提示信息字符串
	 * 
	 * <p>
	 * 缺省追加参数：appcode=1 success=true appmsg
	 * </p>
	 * 
	 * @param msg
	 *            提示信息，这个提示信息被转换为Json的key为:appmsg。前台根据appmsg取出。
	 */
	private static String writeMsg(String msg) {
		Dto dto = Dtos.newOutDto();
		dto.put(IMSCons.APPCODE_KEY, IMSCons.SUCCESS);
		dto.put(IMSCons.APPMSG_KEY, msg);
		String outString = IMSJson.toJson(dto);
		return outString;
	}

	/**
	 * 向Response流输出业务数据字符串
	 * <p/>
	 * 如果传入的参数是一个Json字符串则直接输出。若为一个常规字符串则将其自动作为Key为appmsg的json属性值输出。(缺省追加参数：
	 * appcode=1 success=true)
	 * 
	 * @param response
	 * @param appMsg
	 *            提示信息常规字符串
	 * @param appCode
	 *            状态码
	 */
	public static void write(HttpServletResponse response, int appCode, String appMsg) {
		Dto dto = Dtos.newOutDto();
		dto.put(IMSCons.APPCODE_KEY, appCode);
		dto.put(IMSCons.APPMSG_KEY, appMsg);
		String outString = IMSJson.toJson(dto);
		try {
			response.getWriter().write(outString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向Response流输出业务数据字符串
	 * 
	 * @param session
	 *            输出符合Json格式的业务数据字符串
	 */
	public static UserPO getUserInfo(HttpSession session) {
		UserPO userPO = (UserPO) session.getAttribute(IMSCons.USERINFOKEY);
		return userPO;
	}
	/**
	 * 向Response流输出业务数据字符串
	 * 
	 * @param session
	 *            输出符合Json格式的业务数据字符串
	 */
	public static ShopUserPO getShopUserInfo(HttpSession session) {
		ShopUserPO userPO = (ShopUserPO) session.getAttribute(BeautyCons.SHOP_USER_INFO_KEY);
		return userPO;
	}
	/**
	 * 向Response流输出业务数据字符串
	 * 
	 * @param session
	 *            输出符合Json格式的业务数据字符串
	 */
	public static CustomUserPO getCustomUserInfo(HttpSession session) {
		CustomUserPO userPO = (CustomUserPO) session.getAttribute(BeautyCons.CUSTOM_USER_INFO_KEY);
		return userPO;
	}
	/**
	 * 获取缓存管理器
	 * 
	 * @return
	 */
	public static CacheManager getCacheManager() {
		CacheManager cacheManager = (CacheManager) getBean("cacheManager");
		return cacheManager;
	}
	/** 
	 * 从Spring容器上下文中获取SpringBean组件
	 * 
	 * @param springBeaID
	 *            SpringBeaID
	 * @return
	 */
	public static Object getBean(String springBeaID) {
		Object bean = IMSBeanLoader.getSpringBean(springBeaID);
		return bean;
	}
   
	/**
	 * 获取指定ID的SqlDao组件
	 * 
	 * @param springBeaID
	 * @return
	 */
	public static SqlDao getSqlDao() {
		SqlDao sqlDao = (SqlDao) getBean("sqlDao");
		return sqlDao;
	}
	/**
	 * 获取指定的Cache
	 * 
	 * @param cacheName
	 */
	public static Cache getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		return cache;
	}
	/**
	 * 根据指定缓存名称和缓存键获取相应的存储值
	 * @param cacheName 缓存名称
	 * @param cacheKey  缓存键
	 * @return
	 */
	public static Object getCacheValue(String cacheName,String cacheKey){
		Cache cache=getCache(cacheName);
		Element element=cache.get(cacheKey);
		return element==null?null:element.getObjectValue()	;
	}
	/**
	 * 根据指定缓存名称和缓存键进行删除缓存值
	 * @param cacheName 缓存名称
	 * @param cacheKey  缓存键
	 * @return
	 */
	public static boolean removeCacheValue(String cacheName,String cacheKey){
		Cache cache = cacheManager.getCache(cacheName);
		return cache.remove(cacheKey);
	}

	/**
	 * 根据参数键获取参数值
	 * 
	 * @param paramPO参数键
	 * 
	 */
	public static String getParamValue(String param_key) {
		String param_value = "";
		if (IMSUtils.isNotEmpty(param_key)) {
			ParamPO paramPO = resourceCacheService.getParamPOByParamKey(param_key);
			if (paramPO != null) {
				param_value = paramPO.getParam_value();
			}
		} else {
			log.error("参数键不能为空");
		}

		return param_value;
	}
	/**
	 * 从数据库参数表中根据参数键获取参数值
	 * 
	 * @param param_key
	 *            参数键
	 * @param defaultValue
	 *            缺省值
	 * @return
	 */
	public static String getParamValue(String param_key, String defaultValue) {
		String valueString = getParamValue(param_key);
		if (IMSUtils.isEmpty(valueString)) {
			valueString = defaultValue;
		}
		return valueString;
	}
	/**
	 * 
	 * 简要说明：根据参数键获取键值参数实体
	 * 编写者：陈骑元
	 * 创建时间：2017年1月24日 上午10:22:18
	 * @param 说明
	 * @return 说明
	 */
	public static ParamPO getParam(String param_key) {
		ParamPO paramPO=null;
		if (IMSUtils.isEmpty(param_key)) {
			 paramPO = resourceCacheService.getParamByKey(param_key);
		}
		return paramPO;
	}

	

	
	/**
	 * 从Properties参数配置文中根据参数键获取参数值
	 * 
	 * @param key
	 *            参数键
	 * @return
	 */
	public static String getParamValueOfProperties(String key) {
		String valueString = IMSPropertiesUtil.getString(key);
		return valueString;
	}
	
	/**
	 * 删除键值参数的存储值
	 * @param key
	 * @return
	 */
	public static boolean removeParamCache(String key){
		
		return removeCacheValue(IMSCons.CACHE.IMSRESOURCECACHE, IMSCons.CACHE_PREFIX.PARAM +key);
	}
	/**
	 * 根据数据字典标识键获取字典对照集合
	 * 
	 * @param dickey
	 *            数据字典标识键
	 * @return
	 */
	public static List<DictionaryPO> getDictionaryList(String dic_key) {
		List<DictionaryPO> dicList = resourceCacheService.getDictionaryList(dic_key);
		return dicList;
	}
	/**
	 * 删除字典缓存
	 * @param dic_key
	 * @return
	 */
    public static boolean removeDictionaryCache(String dic_key){
		
		return IMSCxt.removeCacheValue(IMSCons.CACHE.IMSRESOURCECACHE, IMSCons.CACHE_PREFIX.DIC +dic_key);
	}
    /**
     * 刷新系统缓存值
     */
    public static void  refreshCache(){
    	Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
    	cache.removeAll();
    }
    
	/**
	 * 根据数据字典标识键和字典对照代码获取字典对照值
	 * 
	 * @param dic_key
	 *            数据字典标识键
	 * @param dic_code
	 *            数据字典对照代码
	 * @return
	 */
	public static String getDicValue(String dic_key, String dic_code) {
		String dic_value = "";
		List<DictionaryPO> dicList = resourceCacheService.getDictionaryList(dic_key);
		for (DictionaryPO dictionaryPO : dicList) {
			if (dictionaryPO.getDic_code().equals(dic_code)) {
				dic_value = dictionaryPO.getDic_value();
				break;
			}
		}
		return dic_value;
	}
	/**
	 * 根据数据字典标识键和字典对照代码获取字典对照值
	 * 
	 * @param dic_key
	 *            数据字典标识键
	 * @param dic_code
	 *            数据字典对照代码
	 * @return
	 */
	public static String getDicValue(List<DictionaryPO>  dicList, String dic_code) {
		String dic_value = "";
		for (DictionaryPO dictionaryPO : dicList) {
			if (dictionaryPO.getDic_code().equals(dic_code)) {
				dic_value = dictionaryPO.getDic_value();
				break;
			}
		}
		return dic_value;
	}
	/**
	 * 
	 * 简要说明：缓存所有参数数据
	 * 编写者：陈骑元
	 * 创建时间：2016年12月20日 下午3:27:42
	 * @param 说明
	 * @return 说明
	 */
	public static void cacheParamData(){
		resourceCacheService .cacheParamData();
	}
	/**
	 * 
	 * 简要说明：缓存所有字典数据
	 * 编写者：陈骑元
	 * 创建时间：2016年12月20日 下午3:28:24
	 * @param 说明
	 * @return 说明
	 */
	public static void cacheDicData(){
		resourceCacheService.cacheDicData();
	}
	/**
	 * 
	 * 简要说明：刷新单个键值参数到缓存中
	 * 编写者：陈骑元
	 * 创建时间：2016年12月20日 下午5:04:40
	 * @param 说明
	 * @return 说明
	 */
	public static ParamPO cacheParamOption(String param_key){
		return resourceCacheService.cacheParamOption(param_key);
	}
	/**
	 * 
	 * 简要说明：缓存字典数据到缓存并返回字典数据
	 * 编写者：陈骑元
	 * 创建时间：2016年12月20日 下午6:24:55
	 * @param 说明
	 * @return 说明
	 */
	public static List<DictionaryPO> cacheDic(String dic_key){
		return resourceCacheService.cacheDic(dic_key);
	}
   /**
    * 
    * 简要说明：删除缓存中的键值参数
    * 编写者：陈骑元
    * 创建时间：2016年12月20日 下午12:10:45
    * @param 说明
    * @return 说明
    */
	public static void deleteCacheParam(String param_key){
		resourceCacheService.deleteCacheParam(param_key);
	}
	/**
	 * 
	 * 简要说明：删除缓存中字典集合
	 * 编写者：陈骑元
	 * 创建时间：2016年12月20日 下午12:11:26
	 * @param 说明
	 * @return 说明
	 */
	public static void deleteCacheDic(String dic_key){
		resourceCacheService.deleteCacheDic(dic_key);
	}
    
	
	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-real-ip");
		if (IMSUtils.isEmpty(ip)) {
			ip = request.getHeader("X-Forward-For");
		}
		if (IMSUtils.isEmpty(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (IMSUtils.isEmpty(ip) || StringUtils.equalsIgnoreCase("unknown", ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (IMSUtils.isEmpty(ip)) {
			ip = request.getRemoteAddr();
		}
		if (StringUtils.indexOf(ip, "0:0") != -1) {
			ip = "127.0.0.1";
		}
		return ip;
	}
	/**
	 * 
	 * 简要说明：全局缓存redis 是否在线，
	 * 编写者：陈骑元
	 * 创建时间：2016年12月21日 下午10:50:40
	 * @param 说明
	 * @return 说明
	 */
	public static boolean isLive(){
		String isLive=System.getProperty(IMSCons.REDIS_ISLIVE_KEY);
		if("true".equals(isLive)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * 简要说明：清空redis 缓存
	 * 编写者：陈骑元
	 * 创建时间：2016年12月22日 下午2:18:57
	 * @param 说明
	 * @return 说明
	 */
	public static void flushDB(){
		JedisUtil.flushDB();
	}
	/**
	 * 
	 * 简要说明：获取当前数据库类型
	 * 编写者：陈骑元
	 * 创建时间：2017年2月9日 上午9:12:41
	 * @param 说明
	 * @return 说明
	 */
	public static String getDbType(){
		
		return getSqlDao().getDatabaseId();
	}

}
