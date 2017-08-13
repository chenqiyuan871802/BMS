package com.ims.common.core.support.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSPropertiesUtil;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.system.modules.po.DictionaryPO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 类描述： <b>Redis客户端</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-2 上午12:51:44
 * 修改人：蓝枫 
 * 修改时间：2016-6-2 上午12:51:44
 * 修改备注： 
 * @version
 */
@SuppressWarnings("deprecation")
public class JedisUtil {
	private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
	//连接池对象
	private static JedisPool jedisPool;

	static {
		String host = IMSPropertiesUtil.getString("redis.host");
		int port = IMSPropertiesUtil.getInt("redis.port");
		int timeout=IMSPropertiesUtil.getInt("redis.timeout");
		String password=IMSPropertiesUtil.getString("redis.password");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(IMSPropertiesUtil.getInt("redis.maxIdle"));
        config.setMinIdle(IMSPropertiesUtil.getInt("redis.minIdle"));
        config.setMaxTotal(IMSPropertiesUtil.getInt("redis.maxActive"));
        config.setMaxWaitMillis(IMSPropertiesUtil.getInt("redis.maxWait"));
        config.setTestOnBorrow(IMSPropertiesUtil.getBoolean("redis.testOnBorrow",true));
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        config.setMinEvictableIdleTimeMillis(60000l);
        config.setTimeBetweenEvictionRunsMillis(3000l);
        config.setNumTestsPerEvictionRun(-1);
        if(IMSUtils.isEmpty(password)){
        	jedisPool = new JedisPool(config, host, port);
        }else{
        	jedisPool = new JedisPool(config, host, port,timeout,password);
        }
        
	}

	/**
	 * 获取Jedis连接客户端
	 * 
	 * @return
	 */
	public static Jedis getJedisClient() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			logger.error("获取Redis客户端连接失败。"+e);
		}
		if (jedis == null) {
			logger.warn("没有获取到Redis客户端连接。");
		}
		return jedis;
	}

	/**
	 * 安全回收资源
	 * 
	 * @param jedis
	 */
	public static void close(Jedis jedis) {
		try {
			jedisPool.returnResource(jedis);
		} catch (Exception e) {
			if (jedis.isConnected()) {
				jedis.quit();
				jedis.disconnect();
			}
		}
	}

	/**
	 * 设置字符串型数据
	 * 
	 * @param key
	 *            存储键
	 * @param value
	 *            存储值
	 * @param timeout
	 *            超时时间(单位：秒） 设置为0，则无时效性。
	 * @return
	 */
	public static void setString(String key, String value, int timeout) {
		if (IMSUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.set(key, value);
			if (timeout > 0) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 设置字符串型数据过期时间
	 * 
	 * @param key
	 *            存储键
	 * @param timeout
	 *            超时时间(单位：秒）
	 * @param key
	 */
	public static void exprString(String key, int timeout) {
		if (IMSUtils.isEmpty(key)) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.expire(key, timeout);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 设置序列化对象数据
	 * 
	 * @param key
	 *            存储键
	 * @param value
	 *            存储值
	 * @param timeout
	 *            超时时间(单位：秒） 设置为0，则无时效性。
	 * @return
	 */
	public static void setObj(String key, byte[] value, int timeout) {
		if (IMSUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.set(key.getBytes(), value);
			if (timeout > 0) {
				jedis.expire(key, timeout);
			}
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 获取字符串型数据
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		if (IMSUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			value = jedis.get(key);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
		return value;
	}

	/**
	 * 获取序列化对象数据
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] getObj(String key) {
		if (IMSUtils.isEmpty(key)) {
			throw new NullPointerException("Key不能为空!");
		}
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			value = jedis.get(key.getBytes());
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
		return value;
	}

	/**
	 * 删除对象数据
	 * 
	 * @param key
	 */
	public static void delObj(String key) {
		if (IMSUtils.isEmpty(key)) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.del(key.getBytes());
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 删除字符串数据
	 * 
	 * @param key
	 */
	public static void delString(String key) {
		if (IMSUtils.isEmpty(key)) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.del(key);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 清除DB
	 * 
	 * @param key
	 */
	public static void flushDB() {
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			jedis.flushDB();
			logger.info("Redsi缓存DB重置成功。");
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
	}

   /**
    * 
    * 简要说明：判断redis 是否存活 false 不在线
    * 编写者：陈骑元
    * 创建时间：2016年12月12日 上午11:47:39
    * @param 说明
    * @return 说明
    */
	public static  boolean  isLive() {
		Jedis jedis = null;
		try {
			jedis = getJedisClient();
			if(jedis!=null){
				String ping=jedis.ping();
				if("PONG".equals(ping)){
					return true;
				}
			}
		
		
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			logger.error("操作Redis失败", e);
		} finally {
			close(jedis);
		}
		return false;
	}

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		List<DictionaryPO> dicList =Lists.newArrayList();
		Jedis jedis = JedisUtil.getJedisClient();
		List<String> dicRedisList = jedis.hvals(IMSCons.CACHE_PREFIX.DIC + "user_status");
		if (IMSUtils.isNotEmpty(dicRedisList)) {
			// 则压入到集合中
            for (String dicString : dicRedisList) {
	            dicList.add((DictionaryPO) IMSJson.fromJson(dicString, DictionaryPO.class));
            }
		}
	  for(DictionaryPO dictionaryPO:dicList ){
		  dictionaryPO.println();
	  }
	}
}
