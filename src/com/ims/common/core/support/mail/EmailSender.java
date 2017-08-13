package com.ims.common.core.support.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import com.ims.common.core.asset.FileOperation;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSPropertiesUtil;
import com.ims.common.core.asset.IMSUtils;



/**
 * 
 * 类名:com.ims.common.core.support.email.EmailSender
 * 描述:邮件发送类
 * 编写者:陈骑元
 * 创建时间:2017年3月4日 下午5:59:21
 * 修改说明:
 */
public class EmailSender {

    private static final Logger log = Logger.getLogger(EmailSender.class);
    /**
     * 将EmailSendParamter中的List<File> attFiles转换成List<DataSource>
     * 
     * @param params
     * @return
     */
    private static final List<DataSource> changeFileToDataSource(Email params) {
        String[] attFiles = null;
        if (IMSUtils.isNotEmpty(params.getEmailSendAttFiles())) {
            attFiles = params.getEmailSendAttFiles().split(IMSCons.MARK_CSV);
        }
        List<File> files = new ArrayList<File>();
        if (IMSUtils.isNotEmpty(attFiles)) {
            for (String s : attFiles) {
                files.add((File) FileOperation.unzipobj(s));
            }
        }
        List<DataSource> attMents =FileOperation.fileDirsToDatasource(files);
        return attMents;
    }

    /**
     * 设置一些默认参数
     * 
     * @param params
     * @return
     */
    private static final Email defaultParams(Email params) {
        if (IMSUtils.isEmpty(params.getEmailSendAuth())) {
            params.setEmailSendAuth(IMSCons.TRUE);
        }
        if (IMSUtils.isEmpty(params.getEmailSendPort())) {
            params.setEmailSendPort(IMSCons.EMAILCONS.DEFAULTE_EMAIL_PORT);
        }
        if (IMSUtils.isEmpty(params.getEmailSendSocketPort())) {
            params.setEmailSendSocketPort(IMSCons.EMAILCONS.DEFAULT_EMAIL_SOCKETPORT);
        }
        if (IMSUtils.isEmpty(params.getEmailSendSocketClass())) {
            params.setEmailSendSocketClass(IMSCons.EMAILCONS.DEFAULT_EMAIL_SOCKETCLASS);
        }
        if (IMSUtils.isEmpty(params.getEmailSendFrom())) {
            params.setEmailSendFrom(params.getEmailSendUsername());
        }
        
        return params;
    }
    /**
     * 
     * 设置文件属性的一些参数
     * @param params
     * @return
     */
    private static final Email setPropertiesParams(Email params) {
    	if (IMSUtils.isEmpty(params.getEmailSendAuth())) {
    		params.setEmailSendAuth(IMSPropertiesUtil.getString("mail.smtp.auth"));
    	}
    	if (IMSUtils.isEmpty(params.getEmailSendSmtp())) {
    		params.setEmailSendSmtp(IMSPropertiesUtil.getString("email.smtp.host"));
    	}
    	if (IMSUtils.isEmpty(params.getEmailSendPort())) {
    		params.setEmailSendPort(IMSPropertiesUtil.getString("email.smtp.port"));
    	}
    
    	if (IMSUtils.isEmpty(params.getEmailSendUsername())) {
    		params.setEmailSendUsername(IMSPropertiesUtil.getString("email.user.name"));
    	}
    	if (IMSUtils.isEmpty(params.getEmailSendPassword())) {
    		params.setEmailSendPassword(IMSPropertiesUtil.getString("email.user.password"));
    	}
    	
    	return params;
    }

    /**
     * 得到邮件地址数组，用于同时向多人发送邮件。
     * 
     * @param addStr
     * @return
     * @throws AddressException
     */
    private static final Address[] getAddresses(String addStr) throws AddressException {
        if (IMSUtils.isEmpty(addStr)) {
            return new Address[0];
        }
        String[] addStrs = addStr.split(IMSCons.MARK_CSV);
        List<Address> adds = new ArrayList<Address>();
        for (String s : addStrs) {
            if (!IMSUtils.isEmpty(s)) {
                adds.add(new InternetAddress(s));
            }
        }
        return adds.toArray(new Address[adds.size()]);
    }

   

    /**
     * 邮件发送
     * 
     * @param params
     * 构建EmailSendParamter对象，封装邮件参数
     */
    public static boolean  send(Email params) {
    	setPropertiesParams(params);
        defaultParams(params);
        log.debug(params);

        if (!validate(params)) {
            return false;
        }

        Properties properties = System.getProperties();
        properties.put(IMSCons.EMAILCONS.EMAIL_SMTP, params.getEmailSendSmtp());
        properties.put(IMSCons.EMAILCONS.EMAIL_AUTH, params.getEmailSendAuth());
        properties.setProperty(IMSCons.EMAILCONS.EMAIL_POST, params.getEmailSendPort());
        properties.put(IMSCons.EMAILCONS.EMAIL_SOCKETPORT, params.getEmailSendSocketPort());
        properties.put(IMSCons.EMAILCONS.EMAIL_SOCKETCLASS, params.getEmailSendSocketClass());

        try {
        	log.info("开始发送邮件");
            //发送方信息
            Session session = Session.getDefaultInstance(properties, null);
            MimeMessage message = new MimeMessage(session);
            message.setSubject(params.getEmailSendSubject());
            message.setFrom(new InternetAddress(params.getEmailSendFrom()));
            if (!IMSUtils.isEmpty(params.getEmailSendTo())) {
                message.setRecipients(Message.RecipientType.TO, getAddresses(params.getEmailSendTo()));
            }
            if (!IMSUtils.isEmpty(params.getEmailSendCc())) {
                message.setRecipients(Message.RecipientType.CC, getAddresses(params.getEmailSendCc()));
            }
            if (!IMSUtils.isEmpty(params.getEmailSendBcc())) {
                message.setRecipients(Message.RecipientType.BCC, getAddresses(params.getEmailSendBcc()));
            }
            BodyPart bodyPart = new MimeBodyPart();
            if (IMSUtils.isEmpty(params.getEmailSendContent())) {
                params.setEmailSendContent(IMSCons.EMPTY);
            }
            bodyPart.setContent(params.getEmailSendContent(),IMSCons.EMAILCONS.EMAIL_CHARSET);
            Multipart mainmp = new MimeMultipart();
            mainmp.addBodyPart(bodyPart);
            //添加附件
            List<DataSource> attMents = changeFileToDataSource(params);
            if (IMSUtils.isNotEmpty(attMents)) {
                for (DataSource attach : attMents) {
                    bodyPart = new MimeBodyPart();
                    bodyPart.setDataHandler(new DataHandler(attach));
                    //MimeUtility.encodeText()解决附件名中文乱码
                    bodyPart.setFileName(MimeUtility.encodeText(attach.getName()));
                    mainmp.addBodyPart(bodyPart);
                }
            }
            message.setContent(mainmp);
            //这句抛nullpoint的话检查附件地址对不对。
            message.saveChanges();
            //邮件发送
            Transport transport = session.getTransport(IMSCons.EMAILCONS.EMAIL_SMTP);
            transport.connect(params.getEmailSendSmtp(), params.getEmailSendUsername(), params.getEmailSendPassword());
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            if (!IMSUtils.isEmpty(params.getEmailSendCc())) {
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            }
            if (!IMSUtils.isEmpty(params.getEmailSendBcc())) {
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
            }

            transport.close();
            log.info("发送邮件成功;其中收件人："+params.getEmailSendTo()+" 主题："+params.getEmailSendSubject()+" 内容:"+params.getEmailSendContent());
            return true;
          

        } catch (Exception e) {
            log.error("发送邮件失败："+e);
        }
        return false;
    }

    /**
     * 校验邮件发送参数
     * 
     * @param params
     * @return
     * @throws Exception 
     */
    private static final boolean validate(Email params)  {
        if (!IMSUtils.isEmpty(params.getEmailSendAuth())) {
            if (IMSCons.TRUE.equals(params.getEmailSendAuth())) {
                if (IMSUtils.isAnyEmpty(params.getEmailSendUsername(), params.getEmailSendPassword(),
                        params.getEmailSendSmtp(), params.getEmailSendSubject(), params.getEmailSendFrom(),
                        params.getEmailSendTo())) {
                	log.error("The email sending fields: [username, password, smtp, subject, from, TO] is Necessary, can't be null or empty");
                   return false;
                }
            } else if (IMSCons.FALSE.equals(params.getEmailSendAuth())) {
                if (IMSUtils.isAnyEmpty(params.getEmailSendSmtp(), params.getEmailSendSubject(), params.getEmailSendFrom(),
                        params.getEmailSendTo())) {
                	log.error("The email sending fields: [username, password, smtp, subject, from, TO] is Necessary, can't be null or empty");
                	 return false;
                }
            } else {
            	log.error("smtp身份认证：mail.smtp.auth 参数只能为空true 或 false");
            	return false;
            }
        } else {
        	log.error("smtp身份认证：mail.smtp.auth 参数不能空");
           return false;
        }
        return true;
    }
    public static void main(String[] args) {
        /**
         * 测试qq邮箱（成功，推荐）
         */
        long start =System.currentTimeMillis();
        Email params = new Email();
        //暂时不清楚这个参数有什么用
        params.setEmailSendAuth("true");
        params.setEmailSendUsername("haibing871802@163.com");
        //如果出现Caused by: javax.mail.AuthenticationFailedException，表明账户登录失败，用户名密码错误。
        //因为qq现在的第三方软件的登录密码变成了授权码了，不再是qq密码或者独立密码了，
        //获取方式http://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256。
        params.setEmailSendPassword("cqy871802");
        params.setEmailSendSmtp("smtp.163.com");
        params.setEmailSendSubject("测试内容");
        params.setEmailSendFrom("haibing871802@163.com");
        params.setEmailSendTo("240823329@qq.com");
       // params.setEmailSendCc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        //params.setEmailSendBcc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendContent("240823329@qq.com");
        List<String> files = new ArrayList<String>();
        files.add(FileOperation.zipobj(new File("E:\\test\\1.jpg")));
       // File f = new File("E:\\移动集团客户网络故障监测系统-通信协议V0.1.doc");
        //String fbyte = FileOperation.zipobj(f);
        //files.add(fbyte);
        params.setEmailSendAttFiles(IMSUtils.joinStringWithUnseeChar(files));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
         EmailSender.send(params);
/*
        *//**
         * 测试163邮箱（成功，推荐）
         *//*
        start = Util.times();
        params = new EmailSend();
        //暂时不清楚这个参数有什么用
        params.setEmailSendAuth("true");
        params.setEmailSendUsername("m13536597372@163.com");
        params.setEmailSendPassword("base123");
        params.setEmailSendSmtp("smtp.163.com");
        params.setEmailSendSubject("Base邮件标题");
        params.setEmailSendFrom("m13536597372@163.com");
        params.setEmailSendTo("m13536597372@163.com");
        params.setEmailSendCc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendBcc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendContent("Base邮件内容.");
        files = new ArrayList<String>();
        files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/fircosoft.tar.gz")));
        files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/summaryResult-good.xls")));
        f = new File("C:/Users/tingbo/Desktop/跳转回登录前页面.docx");
        fbyte = ZipUtil.zipobj(f);
        files.add(fbyte);
        params.setEmailSendAttFiles(Util.joinStringWithUnseeChar(files));
        end = Util.times();
        System.out.println(end - start);
        //EmailSender.send(params);

        *//**
         * 测试新浪邮箱（成功）
         *//*
        start = Util.times();
        params = new EmailSend();
        //暂时不清楚这个参数有什么用
        params.setEmailSendAuth("true");
        params.setEmailSendUsername("basetest@sina.com");
        params.setEmailSendPassword("base123");
        params.setEmailSendSmtp("smtp.sina.com");
        params.setEmailSendSubject("Base邮件标题");
        params.setEmailSendFrom("basetest@sina.com");
        params.setEmailSendTo("m13536597372@163.com");
        params.setEmailSendCc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendBcc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendContent("Base邮件内容.");
        files = new ArrayList<String>();
        files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/fircosoft.tar.gz")));
        files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/summaryResult-good.xls")));
        f = new File("C:/Users/tingbo/Desktop/跳转回登录前页面.docx");
        fbyte = ZipUtil.zipobj(f);
        files.add(fbyte);
        params.setEmailSendAttFiles(Util.joinStringWithUnseeChar(files));
        end = Util.times();
        System.out.println(end - start);
        //EmailSender.send(params);

        *//**
         * 测试搜狐邮箱（成功，发送给自己可以，发给邮箱服务器不行，非常不推荐使用）
         *//*
        start = Util.times();
        params = new EmailSend();
        //暂时不清楚这个参数有什么用
        params.setEmailSendAuth("true");
        params.setEmailSendUsername("ppag73726f807b81@sohu.com");
        params.setEmailSendPassword("base123");
        params.setEmailSendSmtp("smtp.sohu.com");
        params.setEmailSendSubject("Base邮件标题");
        params.setEmailSendFrom("ppag73726f807b81@sohu.com");
        params.setEmailSendTo("ppag73726f807b81@sohu.com");
        params.setEmailSendCc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendBcc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendContent("Base邮件内容.");
        files = new ArrayList<String>();
        files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/fircosoft.tar.gz")));
        files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/summaryResult-good.xls")));
        f = new File("C:/Users/tingbo/Desktop/跳转回登录前页面.docx");
        fbyte = ZipUtil.zipobj(f);
        files.add(fbyte);
        params.setEmailSendAttFiles(Util.joinStringWithUnseeChar(files));
        end = Util.times();
        System.out.println(end - start);
        //EmailSender.send(params);

        *//**
         * 测试阿里云邮箱
         *//*
        start = Util.times();
        params = new EmailSend();
        //暂时不清楚这个参数有什么用
        params.setEmailSendAuth("true");
        params.setEmailSendUsername("cyllayd@aliyun.com");
        params.setEmailSendPassword("abclay123");
        params.setEmailSendSmtp("smtp.aliyun.com");
        params.setEmailSendSubject("Base邮件标题");
        params.setEmailSendFrom("cyllayd@aliyun.com");
        params.setEmailSendTo("1035097367@qq.com");
        params.setEmailSendCc("m13536597372@163.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendBcc("1035097367@qq.com" + SymbolConstant.unsee0x01 + "1966646629@qq.com");
        params.setEmailSendContent("Base邮件内容.");
         files = new ArrayList<String>();
         files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/fircosoft.tar.gz")));
         files.add(ZipUtil.zipobj(new File("C:/Users/tingbo/Desktop/summaryResult-good.xls")));
         f = new File("C:/Users/tingbo/Desktop/跳转回登录前页面.docx");
         fbyte = ZipUtil.zipobj(f);
         files.add(fbyte);
         params.setEmailSendAttFiles(Util.joinStringWithUnseeChar(files));
        end = Util.times();
        System.out.println(end - start);
        EmailSender.send(params);*/
    }
  
}
