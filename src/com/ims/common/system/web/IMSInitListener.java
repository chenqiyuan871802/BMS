package com.ims.common.system.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.support.redis.JedisUtil;

/**
 * 
 * 类名:com.ims.common.system.web.IMSInitListener 描述:系统启动监听器 编写者:陈骑元
 * 创建时间:2016年12月21日 下午10:33:46 修改说明:
 */
public class IMSInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始缓存或重建缓存
	 */
	private void initCache(ServletContextEvent sce) {
		// 设置redis是否在线 true 在线，false是不在线 进行全局缓存，因为每次判断JedisUtil.isLive 太耗时间了
		Boolean isLive = JedisUtil.isLive();
		if (isLive) {
			//IMSCxt.flushDB(); // 每次启动都清空redis缓存
		}
		IMSCxt.refreshCache(); // 清除Ehcache缓存
		System.setProperty(IMSCons.REDIS_ISLIVE_KEY, isLive.toString());
		IMSCxt.cacheParamData();
		IMSCxt.cacheDicData();

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			initCache(sce);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
