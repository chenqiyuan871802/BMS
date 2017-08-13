package com.ims.common.core.support.mail;

import java.io.Serializable;

import com.ims.common.core.matatype.BasePO;

/**
 * 
 * 类名:com.ims.common.core.support.email.Email
 * 描述:邮件实体类
 * 编写者:陈骑元
 * 创建时间:2017年3月2日 上午9:34:27
 * 修改说明:
 */
public class Email extends BasePO {
	  /**
     * 是否需要验证，true或false，如果没有，默认为true，暂时不清楚这个参数有什么用
     */
    private String emailSendAuth;
    /**
     * 如果smtp连接不成功，会自动转换成socket协议，这里指定它的端口，请指定465，如果没有，默认为465
     */
    private String emailSendSocketPort;
	  /**
     * smtp服务端口，默认使用25，为了兼容性，请指定465，qq和163支持25和465，新浪支持465，搜狐支持465，如果没有，默认为465
     */
    private String emailSendPort;
	
	/**
     * 邮件登陆名，必填
     */
    private String emailSendUsername;
    /**
     * 邮箱登录密码
     */
    private String emailSendPassword;
    /**
     * 邮件发件人邮箱，必填
     */
    private String emailSendFrom;
    /**
     * 邮件收件人邮箱，可多个，使用IMSCons.MARK_CSV隔开进行分隔，如果没有，默认使用EmailSendParamter.getUsername();
     */
    private String emailSendTo;
    /**
     * 邮件抄送人邮箱，可多个，使用IMSCons.MARK_CSV进行分隔
     */
    private String emailSendCc;
    /**
     * 邮件密送人邮箱，可多个，使用IMSCons.MARK_CSV隔开进行分隔
     */
    private String emailSendBcc;
    /**
     * 邮件标题
     */
    private String emailSendSubject;
    /**
     * 邮件正文
     */
    private String emailSendContent;
  
  
    /**
     * socket连接类，使用新浪邮箱必须指定，如果没有默认为javax.net.ssl.SSLSocketFactory
     */
    private String emailSendSocketClass;
    /**
     * 发件人名字
     */
    private String emailSendPersonal;
    /**
     * 邮件协议，如smtp.163.com，smtp.googlemail.com，必填
     */
    private String emailSendSmtp;
    /**
     * 邮件附件，IMSCons.MARK_CSV隔开
     */
    private String emailSendAttFiles;
    
	public String getEmailSendAuth() {
		return emailSendAuth;
	}
	public void setEmailSendAuth(String emailSendAuth) {
		this.emailSendAuth = emailSendAuth;
	}
	public String getEmailSendSocketPort() {
		return emailSendSocketPort;
	}
	public void setEmailSendSocketPort(String emailSendSocketPort) {
		this.emailSendSocketPort = emailSendSocketPort;
	}
	public String getEmailSendPort() {
		return emailSendPort;
	}
	public void setEmailSendPort(String emailSendPort) {
		this.emailSendPort = emailSendPort;
	}
	public String getEmailSendUsername() {
		return emailSendUsername;
	}
	public void setEmailSendUsername(String emailSendUsername) {
		this.emailSendUsername = emailSendUsername;
	}
	public String getEmailSendPassword() {
		return emailSendPassword;
	}
	public void setEmailSendPassword(String emailSendPassword) {
		this.emailSendPassword = emailSendPassword;
	}
	public String getEmailSendFrom() {
		return emailSendFrom;
	}
	public void setEmailSendFrom(String emailSendFrom) {
		this.emailSendFrom = emailSendFrom;
	}
	public String getEmailSendTo() {
		return emailSendTo;
	}
	public void setEmailSendTo(String emailSendTo) {
		this.emailSendTo = emailSendTo;
	}
	public String getEmailSendCc() {
		return emailSendCc;
	}
	public void setEmailSendCc(String emailSendCc) {
		this.emailSendCc = emailSendCc;
	}
	public String getEmailSendBcc() {
		return emailSendBcc;
	}
	public void setEmailSendBcc(String emailSendBcc) {
		this.emailSendBcc = emailSendBcc;
	}
	public String getEmailSendSubject() {
		return emailSendSubject;
	}
	public void setEmailSendSubject(String emailSendSubject) {
		this.emailSendSubject = emailSendSubject;
	}
	public String getEmailSendContent() {
		return emailSendContent;
	}
	public void setEmailSendContent(String emailSendContent) {
		this.emailSendContent = emailSendContent;
	}
	public String getEmailSendSocketClass() {
		return emailSendSocketClass;
	}
	public void setEmailSendSocketClass(String emailSendSocketClass) {
		this.emailSendSocketClass = emailSendSocketClass;
	}
	public String getEmailSendPersonal() {
		return emailSendPersonal;
	}
	public void setEmailSendPersonal(String emailSendPersonal) {
		this.emailSendPersonal = emailSendPersonal;
	}
	public String getEmailSendSmtp() {
		return emailSendSmtp;
	}
	public void setEmailSendSmtp(String emailSendSmtp) {
		this.emailSendSmtp = emailSendSmtp;
	}
	public String getEmailSendAttFiles() {
		return emailSendAttFiles;
	}
	public void setEmailSendAttFiles(String emailSendAttFiles) {
		this.emailSendAttFiles = emailSendAttFiles;
	}
}
