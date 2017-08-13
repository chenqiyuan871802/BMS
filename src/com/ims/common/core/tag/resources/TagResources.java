package com.ims.common.core.tag.resources;

import org.apache.commons.lang3.StringUtils;

/**
 * 资源文件路径常量
 * 
 * <p>此文件需放在资源文件根目录
 * 
 * @author OSWorks-XC
 * @date 2013-06-06
 */
public class TagResources {
	
	/**
	 * 动态读取基础路径
	 */
	static{
		String basePath = TagResources.class.getCanonicalName();
		basePath = StringUtils.substringBeforeLast(basePath, ".").replace(".", "/");
		setBasePath("/" + basePath + "/");
	}
	
	private  static String basePath;
		
	public final static String CODE_STORE_VM = basePath + "codeStoreTag.vm";
	
	public final static String CODE_FORMATTER_VM = basePath + "codeFormatterTag.vm";
	
	public final static String MULTICODE_FORMATTER_VM = basePath + "multiCodeFormatterTag.vm";
	

	public static String getBasePath() {
		return basePath;
	}

	public static void setBasePath(String basePath) {
		TagResources.basePath = basePath;
	}

}
