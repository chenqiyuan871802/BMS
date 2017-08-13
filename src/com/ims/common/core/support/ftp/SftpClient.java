package com.ims.common.core.support.ftp;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ims.common.core.asset.IMSPropertiesUtil;
import com.ims.common.core.exception.FtpException;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * 类名:com.ims.common.core.support.ftp.SftpClient
 * 描述:Sftp下载
 * 编写者:陈骑元
 * 创建时间:2017年1月24日 下午2:37:41
 * 修改说明:
 */
public class SftpClient {
    private Logger logger = LogManager.getLogger();
    private Session session = null;
    private ChannelSftp channel = null;

    private SftpClient() {
    }

    public static SftpClient connect() {
        return new SftpClient().init();
    }

    public SftpClient init() {
        try {
            Properties config = new Properties();
            String host = IMSPropertiesUtil.getString("sftp.host");
            int port = IMSPropertiesUtil.getInt("sftp.port");
            String userName = IMSPropertiesUtil.getString("sftp.user.name");
            String password =IMSPropertiesUtil.getString("sftp.user.password");
            int timeout = IMSPropertiesUtil.getInt("sftp.timeout");
            int aliveMax =IMSPropertiesUtil.getInt("sftp.aliveMax");
            JSch jsch = new JSch(); // 创建JSch对象
            session = jsch.getSession(userName, host, port); // 根据用户名，主机ip，端口获取一个Session对象
            if (password != null) {
                session.setPassword(password); // 设置密码
            }
            config.put("userauth.gssapi-with-mic", "no");
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(timeout); // 设置timeout时间
            session.setServerAliveCountMax(aliveMax);
            session.connect(); // 通过Session建立链接
            channel = (ChannelSftp)session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
            logger.info("SSH Channel connected.");
        } catch (JSchException e) {
            throw new FtpException("", e);
        }
        return this;
    }

    public void disconnect() {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
            logger.info("SSH Channel disconnected.");
        }
    }

    /** 发送文件 */
    public void put(String src, String dst) {
        try {
            channel.put(src, dst, new FileProgressMonitor());
        } catch (SftpException e) {
            throw new FtpException("", e);
        }
    }

    /** 获取文件 */
    public void get(String src, String dst) {
        try {
            channel.get(src, dst, new FileProgressMonitor());
        } catch (SftpException e) {
            throw new FtpException("", e);
        }
    }
}
