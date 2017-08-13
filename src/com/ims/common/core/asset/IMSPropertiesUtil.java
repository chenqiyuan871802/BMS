package com.ims.common.core.asset;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
/**
 * 
 * 类名:com.ims.common.core.asset.PropertiesUtil
 * 描述:属性文件处理
 * 编写者:陈骑元
 * 创建时间:2017年1月24日 上午9:32:27
 * 修改说明:
 */
public final class IMSPropertiesUtil extends PropertyPlaceholderConfigurer {
	 private static Dto ctxPropertiesDto;

	    @Override
	    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
	        throws BeansException {
	        super.processProperties(beanFactoryToProcess, props);
	        ctxPropertiesDto = Dtos.newDto();
	        for (Object key : props.keySet()) {
	            String keyStr = key.toString();
	            String value = props.getProperty(keyStr);
	            ctxPropertiesDto.put(keyStr, value);
	        }
	    }

	    /**
	     * Get a value based on key , if key does not exist , null is returned
	     * 
	     * @param key
	     * @return
	     */
	    public static String getString(String key) {
	         return  ctxPropertiesDto.getString(key);
	     
	    }
	    /**
	     * Get a value based on key , if key does not exist , null is returned
	     * 
	     * @param key
	     * @return
	     */
	    public static String getFileSuffix(String contentType) {
	    	for(String key:ctxPropertiesDto.keySet()){
	    		String value=ctxPropertiesDto.getString(key);
	    		if(value.equals(contentType)){
	    			
	    			return key;
	    		}
	    	}
	    	return  "";
	    	
	    }

	    /**
	     * 根据key获取值
	     * 
	     * @param key
	     * @return
	     */
	    public static int getInt(String key) {
	        return  ctxPropertiesDto.getInteger(key);
	    }

	    /**
	     * 根据key获取值
	     * 
	     * @param key
	     * @param defaultValue
	     * @return
	     */
	    public static int getInt(String key, int defaultValue) {
	        String value = ctxPropertiesDto.getString(key);
	        if (StringUtils.isBlank(value)) {
	            return defaultValue;
	        }
	        return Integer.parseInt(value);
	    }

	    /**
	     * 根据key获取值
	     * @param key
	     * @param defaultValue
	     * @return
	     */
	    public static boolean getBoolean(String key, boolean defaultValue) {
	        String value = ctxPropertiesDto.getString(key);
	        if (StringUtils.isBlank(value)) {
	            return defaultValue;
	        }
	        return new Boolean(value);
	    }
}
