package com.ims.common.core.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * 类名:com.ims.common.core.aspect.ChooseDataSource
 * 描述:获取数据源
 * 编写者:陈骑元
 * 创建时间:2017年1月19日 下午5:17:14
 * 修改说明:
 */
public class ChooseDataSource extends AbstractRoutingDataSource {
	public static Map<String, List<String>> METHODTYPE = new HashMap<String, List<String>>();

	// 获取数据源名称
	protected Object determineCurrentLookupKey() {
		return HandleDataSource.getDataSource();
	}

	// 设置方法名前缀对应的数据源
	public void setMethodType(Map<String, String> map) {
		for (String key : map.keySet()) {
			List<String> v = new ArrayList<String>();
			String[] types = map.get(key).split(",");
			for (String type : types) {
				if (StringUtils.isNotBlank(type)) {
					v.add(type);
				}
			}
			METHODTYPE.put(key, v);
		}
	}
}
