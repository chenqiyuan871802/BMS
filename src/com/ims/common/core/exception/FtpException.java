package com.ims.common.core.exception;
/**
 * 
 * 类名:com.ims.common.core.exception.FtpException
 * 描述: FTP异常
 * 编写者:陈骑元
 * 创建时间:2017年1月24日 下午2:31:06
 * 修改说明:
 */
@SuppressWarnings("serial")
public class FtpException extends RuntimeException {
	public FtpException() {
	}

	public FtpException(String message) {
		super(message);
	}

	public FtpException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
