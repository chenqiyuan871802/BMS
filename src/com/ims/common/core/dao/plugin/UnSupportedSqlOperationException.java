package com.ims.common.core.dao.plugin;


/**
 * 不被支持的SQL操作异常
 * 
 * @author OSWorks-XC
 *
 */
public class UnSupportedSqlOperationException extends RuntimeException{
		
	private static final long serialVersionUID = 1L;
	
	public UnSupportedSqlOperationException() {
		super();
	}
	
}
