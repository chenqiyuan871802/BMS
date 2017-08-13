package com.ims.common.core.exception;

import com.ims.common.core.asset.IMSUtils;

/**
 * 
 * 类描述：<b>系统异常类</b> 
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:23:32
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:23:32
 * 修改备注： 
 * @version
 */
public class IMSException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IMSException() {
		super();
	}

	/**
	 * 支持传参数给异常描述字符串进行合并
	 * 
	 * @param errID
	 * @param args
	 */
	public IMSException(int errID, Object... args) {
		super("异常编号：" + errID);
		ExceptionVO vo = ExceptionInfoUtil.getExceptionInfo(String.valueOf(errID));
		if (IMSUtils.isNotEmpty(vo)) {
			String errMsg = "异常编号：" + errID;
			errMsg = errMsg + "\n异常摘要：" + IMSUtils.merge(vo.getInfo(), args);
			errMsg = errMsg + "\n异常排查建议：" + vo.getSuggest() ;
			errMsg = errMsg + "\n异常详细堆栈信息";
			System.out.println(errMsg);
		} else {
			System.out.println("没有查询到异常编号为[" + errID + "]的异常配置信息。");
		}
	}

	/**
	 * 根据异常ID获取异常相关信息
	 * 
	 * @param errID
	 */
	public IMSException(int errID) {
		super("异常编号：" + errID);
		ExceptionVO vo = ExceptionInfoUtil.getExceptionInfo(String.valueOf(errID));
		if (IMSUtils.isNotEmpty(vo)) {
			String errMsg = "异常编号：" + errID;
			errMsg = errMsg + "\n异常摘要：" + IMSUtils.merge(vo.getInfo(), "");
			errMsg = errMsg + "\n异常排查建议：" + vo.getSuggest()  ;
			errMsg = errMsg + "\n异常详细堆栈信息";
			System.out.println(errMsg);
		} else {
			System.out.println("没有查询到异常编号为[" + errID + "]的异常配置信息。");
		}
	}
	
	/**
	 * 直接打印简单信息
	 * 
	 * @param pMsg
	 * @param pNestedException
	 */
	public IMSException(String pMsg) {
		super(pMsg);
	}

	/**
	 * 直接打印简单信息和异常堆栈
	 * 
	 * @param pMsg
	 * @param pNestedException
	 */
	public IMSException(String pMsg, Throwable pNestedException) {
		super(pMsg);
		pNestedException.printStackTrace();
	}
	
	/**
	 * 直接打印异常堆栈
	 * 
	 * @param pMsg
	 * @param pNestedException
	 */
	public IMSException(Throwable pNestedException) {
		super(pNestedException);
		pNestedException.printStackTrace();
	}

}
