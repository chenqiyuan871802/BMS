package com.ims.common.core.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ims.common.core.asset.IMSCons;


/**
 * 
 * 类描述：  * <b>Web监听:导出WebApplicationContext对象</b>
 * <p>
 * 提供在非Servlet环境下获取已经启动的Spring上下文的能力
 * </P>
 * 创建人：陈骑元
 * 创建时间：2016-6-2 下午11:51:02
 * 修改人：蓝枫 
 * 修改时间：2016-6-2 下午11:51:02
 * 修改备注： 
 * @version
 */
public class WebApplicationContextExporterListener implements ServletContextListener {

	private static WebApplicationContext webApplicationContext;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		String flag = IMSCons.STR_TRUE;
		if (webApplicationContext == null) {
			flag = IMSCons.STR_FALSE;
		}
		System.setProperty(IMSCons.WEBAPPCXT_IS_SUCCESS_KEY, flag);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 导出ServletContext下的WebApplicationContext
	 * 
	 * @return
	 */
	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

}
