package com.ims.common.core.aspect;
/**
 * 
 * 类名:com.ims.common.core.aspect.HandleDataSource
 * 描述:数据源协调处理
 * 编写者:陈骑元
 * 创建时间:2017年1月19日 下午5:17:57
 * 修改说明:
 */
public class HandleDataSource {
	// 数据源名称线程池
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void putDataSource(String datasource) {
		holder.set(datasource);
	}

	public static String getDataSource() {
		return holder.get();
	}

	public static void clear() {
		holder.remove();
	}
}
