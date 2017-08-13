package com.ims.common.core.aspect;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * 类名:com.ims.common.core.aspect.DataSourceAspect
 * 描述:切换数据源(不同方法调用不同数据源)
 * 编写者:陈骑元
 * 创建时间:2017年1月19日 下午5:17:33
 * 修改说明:
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
	private Log logger = LogFactory.getLog(DataSourceAspect.class);

	 //多个拦截切点使用 || 或or连接
	@Pointcut("execution(* com.ims.common.system.modules.service..*.*(..)||com.beauty.common.service..*.*(..))")
	public void aspect() {
	}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 */
	@Before("aspect()")
	public void before(JoinPoint point) {
		String className = point.getTarget().getClass().getName();
		String method = point.getSignature().getName();
		logger.info(className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
		try {
			L: for (String key : ChooseDataSource.METHODTYPE.keySet()) {
				for (String type : ChooseDataSource.METHODTYPE.get(key)) {
					if (method.startsWith(type)) {
						logger.info(key);
						HandleDataSource.putDataSource(key);
						break L;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			HandleDataSource.putDataSource("write");
		}
	}

	@After("aspect()")
	public void after(JoinPoint point) {
		HandleDataSource.clear();
	}
}
