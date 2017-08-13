package com.ims.common.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import redis.clients.jedis.Jedis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.support.redis.JedisUtil;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.mapper.ParamMapper;
import com.ims.common.system.modules.po.DictionaryPO;
import com.ims.common.system.modules.po.ParamPO;
import com.ims.common.system.modules.service.SystemService;

/**
 * 
 * 类描述： 系统资源缓存服务主要包括字典、键值参数、用户信息、菜单权限等等 创建人：陈骑元 邮箱：240823329@qq.com 创建时间：Oct 5,
 * 2016 11:33:35 PM 修改人： 修改时间： 修改备注：
 * 
 * @version 1.0
 */
@Service("resourceCacheService")
public class ResourceCacheService {
	private static Log log = LogFactory.getLog(ResourceCacheService.class);
	@Autowired
	private SystemService systemService;
	@Autowired
	private ParamMapper paramMapper;

	/**
	 * 根据参数键获取参数对象 redis与Eache同时共存
	 * 
	 * @param param_key
	 *            参数键
	 * 
	 */
	public ParamPO getParamPOByParamKey(String param_key) {
		ParamPO paramPO = null;
		if (IMSCxt.isLive()) { // 判断redis 是否在线 ,在线就去redis 获取 不在线就去Ehchache中获取
			paramPO = getRedisParamPOByParamKey(param_key);
		} else {
			paramPO = getEhcacheParamPOByParamKey(param_key);
		}
		return paramPO;
	}

	/**
	 * 
	 * 简要说明：通过参数键获取Ehcahce的 编写者：陈骑元 创建时间：2016年12月20日 上午9:46:24
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public ParamPO getEhcacheParamPOByParamKey(String param_key) {
		ParamPO paramPO = null;
		if (IMSUtils.isNotEmpty(param_key)) {
			Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
			Element element = cache.get(IMSCons.CACHE_PREFIX.PARAM + param_key);
			if (element != null) {
				paramPO = (ParamPO) element.getObjectValue();
			} else {
				paramPO = this.cacheParamToEhcache(param_key);
			}
		} else {
			log.error("参数键不能为空");
		}

		return paramPO;
	}

	/**
	 * 
	 * 简要说明：通过参数键获取redis的参数缓存 编写者：陈骑元 创建时间：2016年12月20日 上午9:46:24
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public ParamPO getRedisParamPOByParamKey(String param_key) {
		ParamPO paramPO = null;
		if (IMSUtils.isNotEmpty(param_key)) {
			Jedis jedis = JedisUtil.getJedisClient();
			String keyValue = jedis.hget(IMSCons.CACHE_PREFIX.PARAM, param_key);
			if (IMSUtils.isNotEmpty(keyValue)) {
				paramPO = (ParamPO) IMSJson.fromJson(keyValue, ParamPO.class);
			} else { // 如果redis 找不到就去数据库中查找，并存放redis中
				paramPO = this.cacheParamToRedis(param_key);

			}
			JedisUtil.close(jedis);
		} else {
			log.error("参数键不能为空");
		}

		return paramPO;
	}

	/**
	 * 根据数据字典标识键获取字典对照集合
	 * 
	 * @param dic_key
	 *            数据字典标识键
	 * @return
	 */
	public List<DictionaryPO> getDictionaryList(String dic_key) {
		List<DictionaryPO> dicList = Lists.newArrayList();
		if (IMSCxt.isLive()) { // 判断redis 是否在线，不在线就使用Ehcache缓存
			dicList = getRedisDictionaryList(dic_key);
		} else {
			dicList = getEhcacheDictionaryList(dic_key);
		}
		return dicList;

	}

	/**
	 * 根据数据字典标识键获取字典对照集合从Ehcache中
	 * 
	 * @param dic_key
	 *            数据字典标识键
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DictionaryPO> getEhcacheDictionaryList(String dic_key) {
		List<DictionaryPO> dicList = Lists.newArrayList();
		;
		if (IMSUtils.isNotEmpty(dic_key)) {
			Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
			Element element = cache.get(IMSCons.CACHE_PREFIX.DIC + dic_key);
			if (element != null) {
				dicList = (List<DictionaryPO>) element.getObjectValue();
			} else {
				dicList = this.cacheDicToEhcache(dic_key);
			}
		} else {
			log.error("字典标识键不能为空");
		}

		return dicList;
	}

	/**
	 * 根据数据字典标识键获取字典对照集合从redis中
	 * 
	 * @param dic_key
	 *            数据字典标识键
	 * @return
	 */
	public List<DictionaryPO> getRedisDictionaryList(String dic_key) {
		List<DictionaryPO> dicList = Lists.newArrayList();
		Jedis jedis = JedisUtil.getJedisClient();
		List<String> dicRedisList = jedis.lrange(IMSCons.CACHE_PREFIX.DIC + dic_key, 0, -1);
		if (IMSUtils.isNotEmpty(dicRedisList)) {
			// 则压入到集合中
			for (String dicString : dicRedisList) {
				dicList.add((DictionaryPO) IMSJson.fromJson(dicString, DictionaryPO.class));
			}
		} else {
			dicList = this.cacheDicToRedis(dic_key);

		}
		JedisUtil.close(jedis);
		return dicList;
	}

	/**
	 * 
	 * 简要说明：根据参数键获取参数配置信息 编写者：陈骑元 创建时间：2016年12月20日 下午4:25:57
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public ParamPO getParamByKey(String param_key) {
		ParamPO paramPO = null;
		if (IMSUtils.isNotEmpty(param_key)) {
			Dto pDto = Dtos.newDto();
			pDto.put("param_key", param_key);
			pDto.put("status", DicCons.ENABLED_YES);
			paramPO = paramMapper.selectOne(pDto);
		} else {
			log.error("参数标识键不能为空");
		}
		return paramPO;
	}

	/**
	 * 根据数据字典标识键从数据库中获取字典对照集合
	 * 
	 * @param dic_key
	 *            数据字典标识键
	 * @return
	 */
	public List<DictionaryPO> getDictionaryListByDicKey(String dic_key) {
		List<DictionaryPO> dicList = Lists.newArrayList();
		if (IMSUtils.isNotEmpty(dic_key)) {
			Dto inDto = Dtos.newDto();
			inDto.put("status", DicCons.ENABLED_YES);
			inDto.put("dic_key", dic_key);
			dicList = this.getDics(inDto);
			if (dicList.size() == 0) {
				log.error("字典标识键[" + dic_key + "]在系统中没有字典对照集合或者已经被停用");
			}
		} else {
			log.error("字典标识键[" + dic_key + "]在系统中不存在");
		}
		return dicList;
	}

	/**
	 * 
	 * 简要说明：通过参数查询字典数据信息 编写者：陈骑元 创建时间：2016年12月20日 下午7:53:03
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<DictionaryPO> getDics(Dto pDto) {

		return systemService.listDic(pDto);
	}

	/**
	 * 
	 * 简要说明：查询键值参数信息 编写者：陈骑元 创建时间：2016年12月20日 下午7:57:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<ParamPO> getParams(Dto pDto) {

		return systemService.listParam(pDto);
	}

	/**
	 * 
	 * 简要说明：缓存所有的键值参数 编写者：陈骑元 创建时间：2016年12月20日 下午1:52:52
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void cacheParamData() {
		if (IMSCxt.isLive()) { // redis在线使用redis,没有使用Ehcache
			this.cacheParamDataToRedis();
		} else {
			this.cacheParamDataToEhcache();
		}
	}

	/**
	 * 
	 * 简要说明：缓存所有键值参数到Ehcache中 编写者：陈骑元 创建时间：2016年12月20日 下午1:42:57
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void cacheParamDataToEhcache() {
		Dto pDto = Dtos.newDto();
		pDto.put("status", DicCons.ENABLED_YES);
		List<ParamPO> paramPOList = paramMapper.list(pDto);
		Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
		for (ParamPO paramPO : paramPOList) {
			Element newElement = new Element(IMSCons.CACHE_PREFIX.PARAM + paramPO.getParam_key(), paramPO);
			cache.put(newElement);
		}

	}

	/**
	 * 
	 * 简要说明：缓存所有键值参数到redis中 编写者：陈骑元 创建时间：2016年12月20日 下午1:44:06
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void cacheParamDataToRedis() {
		Dto pDto = Dtos.newDto();
		pDto.put("status", DicCons.ENABLED_YES);
		List<ParamPO> paramPOList = paramMapper.list(pDto);
		Map<String, String> cacheMap = Maps.newHashMap();
		for (ParamPO paramPO : paramPOList) {
			cacheMap.put(paramPO.getParam_key(), IMSJson.toJson(paramPO));
		}
		if (IMSUtils.isNotEmpty(cacheMap)) {
			Jedis jedis = JedisUtil.getJedisClient();
			jedis.hmset(IMSCons.CACHE_PREFIX.PARAM, cacheMap);
			JedisUtil.close(jedis);
		}
	}

	/***
	 * 
	 * 简要说明：缓存字典数据 编写者：陈骑元 创建时间：2016年12月20日 下午3:13:34
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void cacheDicData() {
		if (IMSCxt.isLive()) { // redis在线则使用redis 缓存，redis不在线则使用Ehcache缓存
			this.cacheDicDataToRedis();
		} else {
			this.cacheDicDataToEhcache();
		}
	}

	/**
	 * 
	 * 简要说明：缓存字典数据到Ehcache中 编写者：陈骑元 创建时间：2016年12月20日 下午1:56:15
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void cacheDicDataToEhcache() {
		Dto pDto = Dtos.newDto();
		pDto.put("status", DicCons.ENABLED_YES);
		List<DictionaryPO> dicList = this.getDics(pDto);
		Map<String, List<DictionaryPO>> dicMap = new HashMap<String, List<DictionaryPO>>();
		for (DictionaryPO dictionaryPO : dicList) {
			String key = IMSCons.CACHE_PREFIX.DIC + dictionaryPO.getDic_key();
			List<DictionaryPO> listTemp = Lists.newArrayList();
			if (dicMap.containsKey(key)) { // 如果存在则从Map中获取
				listTemp = dicMap.get(key);
			}
			listTemp.add(dictionaryPO);
			dicMap.put(key, listTemp);
		}
		Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
		// 将字典对照项目载入缓存
		for (String key : dicMap.keySet()) {
			Element newElement = new Element(key, dicMap.get(key));
			cache.put(newElement);
		}

	}

	/**
	 * 
	 * 简要说明：缓存字典数据到redis中 编写者：陈骑元 创建时间：2016年12月20日 下午1:56:15
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void cacheDicDataToRedis() {
		Dto pDto = Dtos.newDto();
		pDto.put("status", DicCons.ENABLED_YES);
		List<DictionaryPO> dicList = this.getDics(pDto);
		Jedis jedis = JedisUtil.getJedisClient();
		// 将字典对照项目载入缓存
		for (DictionaryPO dictionaryPO : dicList) {
			jedis.rpush(IMSCons.CACHE_PREFIX.DIC + dictionaryPO.getDic_key(), IMSJson.toJson(dictionaryPO));
		}
		JedisUtil.close(jedis);

	}

	/**
	 * 
	 * 简要说明：刷新单个键值参数到缓存中 并返回缓存的信息 编写者：陈骑元 创建时间：2016年12月20日 下午4:40:05
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public ParamPO cacheParamOption(String param_key) {
		ParamPO paramPO = null;
		if (IMSCxt.isLive()) { // 如果redis在线则使用redis,不存在则使用
			paramPO = cacheParamToRedis(param_key);
		} else {
			paramPO = cacheParamToEhcache(param_key);
		}
		return paramPO;
	}

	/**
	 * 将单个参数配置项刷到缓存Ehcache，如果已存在则覆盖
	 * 
	 * @param fieldKey
	 *            参数KEY
	 * @param value
	 *            参数值
	 */
	public ParamPO cacheParamToEhcache(String param_key) {
		ParamPO paramPO = getParamByKey(param_key);
		if (IMSUtils.isNotEmpty(paramPO)) {
			Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
			Element newElement = new Element(IMSCons.CACHE_PREFIX.PARAM + param_key, paramPO);
			cache.put(newElement);
			return paramPO;
		} else {
			log.error("参数配置信息刷新到Ehcache中失败，参数键[" + param_key + "]的参数配置信息已经被停用或者在系统中不存在");
			return null;
		}
	}

	/**
	 * 
	 * 简要说明：将单个参数配置信息刷到缓存Redis中，如果已存在则覆盖 编写者：陈骑元 创建时间：2016年12月20日 下午4:21:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public ParamPO cacheParamToRedis(String param_key) {

		ParamPO paramPO = getParamByKey(param_key);
		if (IMSUtils.isNotEmpty(paramPO)) {
			Jedis jedis = JedisUtil.getJedisClient();
			jedis.hset(IMSCons.CACHE_PREFIX.PARAM, param_key, IMSJson.toJson(paramPO));
			JedisUtil.close(jedis);
			return paramPO;
		} else {
			log.error("参数配置信息刷新到redis中失败，参数键[" + param_key + "]的参数配置信息已经被停用或者在系统中不存在");
			return null;
		}

	}

	/**
	 * 
	 * 简要说明：将单个字典刷新到字典中 编写者：陈骑元 创建时间：2016年12月20日 下午5:15:30
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<DictionaryPO> cacheDic(String dic_key) {
		List<DictionaryPO> dicList = Lists.newArrayList();
		// 如果redis 在线，则使用redis，不在线则使用Ehcache
		if (IMSCxt.isLive()) {
			dicList = this.cacheDicToRedis(dic_key);
		} else {
			dicList = this.cacheDicToEhcache(dic_key);
		}
		return dicList;
	}

	/**
	 * 
	 * 简要说明：刷新单个字典到Ehcache中 编写者：陈骑元 创建时间：2016年12月20日 下午5:08:50
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<DictionaryPO> cacheDicToEhcache(String dic_key) {
		List<DictionaryPO> dicList = getDictionaryListByDicKey(dic_key);
		Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
		cache.remove(IMSCons.CACHE_PREFIX.DIC + dic_key);
		if (IMSUtils.isNotEmpty(dicList)) {
			Element newElement = new Element(IMSCons.CACHE_PREFIX.DIC + dic_key, dicList);
			cache.put(newElement);
			return dicList;
		} else {
			log.error("字典数据信息刷新到Ehcache中失败，字典标识键[" + dic_key + "]在系统中没有字典对照集合或者已经被停用");
			return Lists.newArrayList();
		}
	}

	/**
	 * 
	 * 简要说明：刷新单个字典到Redis中 编写者：陈骑元 创建时间：2016年12月20日 下午5:13:49
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<DictionaryPO> cacheDicToRedis(String dic_key) {
		List<DictionaryPO> dicList = getDictionaryListByDicKey(dic_key);
		Jedis jedis = JedisUtil.getJedisClient();
		// 先把原来的清空
		jedis.del(IMSCons.CACHE_PREFIX.DIC + dic_key);
		if (IMSUtils.isNotEmpty(dicList)) {
			// 再插入redis 中
			for (DictionaryPO dictionaryPO : dicList) {
				jedis.rpush(IMSCons.CACHE_PREFIX.DIC + dic_key, IMSJson.toJson(dictionaryPO));
			}

		} else {
			log.error("字典数据信息刷新到Redis中失败，字典标识键[" + dic_key + "]在系统中没有字典对照集合或者已经被停用");
		}
		JedisUtil.close(jedis);
		return dicList;
	}

	/**
	 * 
	 * 简要说明：删除缓存的键值参数 编写者：陈骑元 创建时间：2016年12月20日 上午11:27:40
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void deleteCacheParam(String param_key) {

		if (IMSCxt.isLive()) { // 如果redis在线，则去删除redis中的缓存
			Jedis jedis = JedisUtil.getJedisClient();
			jedis.hdel(IMSCons.CACHE_PREFIX.PARAM, param_key);
			JedisUtil.close(jedis);
		}
		// Ehcache 也去清除一次吧
		Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
		cache.remove(IMSCons.CACHE_PREFIX.PARAM + param_key);
	}

	/**
	 * 
	 * 简要说明：删除缓存中的字典 编写者：陈骑元 创建时间：2016年12月20日 上午11:37:55
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public void deleteCacheDic(String dic_key) {
		if (IMSCxt.isLive()) {
			Jedis jedis = JedisUtil.getJedisClient();
			jedis.del(IMSCons.CACHE_PREFIX.DIC + dic_key);
			JedisUtil.close(jedis);
		}
		// Ehcache 也去清除一次吧
		Cache cache = IMSCxt.getCache(IMSCons.CACHE.IMSRESOURCECACHE);
		cache.remove(IMSCons.CACHE_PREFIX.DIC + dic_key);
	}

}
