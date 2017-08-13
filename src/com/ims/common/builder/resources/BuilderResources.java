package com.ims.common.builder.resources;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 类描述：  资源文件路径常量 <p>此文件需放在资源文件根目录
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-6-10 下午03:14:27
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class BuilderResources {
	
	/**
	 * 动态读取基础路径
	 */
	static{
		String basePath = BuilderResources.class.getCanonicalName();
		basePath = StringUtils.substringBeforeLast(basePath, ".").replace(".", "/");
		setBasePath("/" + basePath + "/");
	}
	
	private  static String basePath;

	public final static String PO_JAVA_VM = basePath + "po.java.vm";

	public final static String MAPPER_JAVA_VM = basePath + "mapper.java.vm";
	
	public final static String SERVICE_JAVA_VM = basePath + "service.java.vm";
	
	public final static String CONTROLLER_JAVA_VM = basePath + "controller.java.vm";

	public final static String MAPPER_XML_VM = basePath + "mapper.xml.vm";


	public static String getBasePath() {
		return basePath;
	}

	public static void setBasePath(String basePath) {
		BuilderResources.basePath = basePath;
	}


}
