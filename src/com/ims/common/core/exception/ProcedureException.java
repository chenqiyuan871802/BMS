package com.ims.common.core.exception;


/**
 * 
 * 类描述： <b>存储过程调用异常</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-2 下午11:28:26
 * 修改人：蓝枫 
 * 修改时间：2016-6-2 下午11:28:26
 * 修改备注： 
 * @version
 */
public class ProcedureException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProcedureException() {
		super();
	}
	
	/**
	 * 异常
	 * 
	 * @param prcName
	 * @param appCode
	 * @param appMsg
	 */
	public ProcedureException(String prcName, int appCode, String appMsg) {
		super("存储过程调用异常。状态码：" + appCode + "。状态信息： " + appMsg + "。");		
	}
}
