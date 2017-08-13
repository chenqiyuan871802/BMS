package com.ims.common.core.support.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.ims.common.core.aspect.DataSourceAspect;
import com.ims.common.core.exception.FtpException;

/**
 * 
 * 类名:com.ims.common.core.support.ftp.FtpClient
 * 描述:FTP上传下载
 * 编写者:陈骑元
 * 创建时间:2017年2月9日 下午8:38:06
 * 修改说明:
 */
public class FtpClient {
	private Log logger = LogFactory.getLog(FtpClient.class);
	private static final byte[] LOCK = { 0 };
	private static FTPClient ftpClient = null;
	private Properties properties = null;
	private static final String FILELOCK = "Token.lock";

	public static void main(String[] args) throws Exception {
		String host = "192.168.206.130";
		int port = 21;
		String username = "ftpsuper";
		String password = "ftpsuper";
		String localUpPath = "E://test";
		String localDnPath = "C:/bankData/Feedback";
		String remotePath = "Feedback";
		FtpClient ftpClient = new FtpClient(host, port, username, password);

		// FTP上传文件
		ftpClient.uploadFolderFile(localUpPath,"image" );
		// FTP下载文件
		ftpClient.downLoadFile(remotePath, localDnPath);

		ftpClient.close();
	}

	public FtpClient() {
	}

	/**
	 * 初始化
	 * 
	 * @param host
	 *            IP
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @throws FtpException
	 */
	public FtpClient(String host, int port, String username, String password) throws FtpException {
		init(host, port, username, password);
	}

	/**
	 * FPT登录
	 */
	public void open() {
		init(properties.getProperty("FTPHOSTNAME"), Integer.valueOf(properties.getProperty("FTPPORT")),
				properties.getProperty("FTPUSERNAME"), properties.getProperty("FTPPASSWORD"));
	}

	/**
	 * 获取FTP连接
	 * 
	 * @param server
	 * @param user
	 * @param password
	 * @return
	 * @throws FtpException
	 */
	private void init(String host, int port, String username, String password) throws FtpException {
		synchronized (LOCK) {
			if (ftpClient == null) {
				ftpClient = new FTPClient();
			}
			try {
				ftpClient.connect(host, port);// 连接FTP服务器
			} catch (Exception e) {
				throw new FtpException("FTP[" + host + ":" + port + "]连接失败!", e);
			}
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				try {
					// 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件
					ftpClient.setControlEncoding("GBK");
					FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
					conf.setServerLanguageCode("zh");
					ftpClient.login(username, password);
				} catch (Exception e) {
					throw new FtpException("FTP用户[" + username + "]登陆失败!", e);
				}
			} else {
				throw new FtpException("FTP连接出错!");
			}
			logger.info("用户[" + username + "]登陆[" + host + "]成功.");
			if (properties == null) {
				properties = new Properties();
			}
			properties.setProperty("userName", username);
			properties.setProperty("hostName", host);
			try {
				// 设置被动模式
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			} catch (Exception e) {
				logger.error("", e);
				throw new FtpException("FTP初始化出错!", e);
			}
		}
	}

	/**
	 * 关闭FTP客户端
	 * 
	 * @throws Exception
	 */
	public void close() throws FtpException {
		synchronized (LOCK) {
			try {
				ftpClient.logout();
			} catch (IOException e) {
				logger.error("", e);
				ftpClient = null;
				throw new FtpException("FTP退出登录出错!", e);
			}
			logger.info(
					"用户[" + properties.getProperty("userName") + "]退出登录[" + properties.getProperty("hostName") + "].");
		}
	}
    /**
     * 
     * 简要说明：根据文件夹上传文件文件
     * 编写者：陈骑元
     * 创建时间：2017年1月1日 下午2:48:50
     * @param 说明  localeFolderPath 本地文件夹路径 remotePath ftp文件夹路径 为空或不存在将保存在ftp用户的主目录
     * @return 说明
     */
	public boolean uploadFolderFile(String localeFolderPath, String remotePath) {
		File file = new File(localeFolderPath);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!uploadFile(files[i].getAbsolutePath(), remotePath)) {
				return false;
			}
		}
		return files.length > 0;
	}

	/**
	 * 
	 * 简要说明： 
	 * 编写者：陈骑
	 * 元 创建时间：2017年1月1日 上午11:12:31
	 * @param 说明  localeFilePath 本地文件的路径    remotePath ftp文件夹路径 为空或不存在将保存在ftp用户的主目录 
	 * @return 说明
	 */
	public boolean uploadFile(String localeFilePath, String remotePath) {
		synchronized (LOCK) {
			InputStream input = null;
			try {
				File localeFile = new File(localeFilePath);
				if (!localeFile.isFile()) {
					logger.error("文件[" + localeFilePath + "]不存在或者路径不正确");
					return false;
				}
				// 转移到FTP服务器目录
				ftpClient.changeWorkingDirectory("");
				// 得到目录的相应文件列表
				FTPFile[] files = ftpClient.listFiles();
				// 获取上传文件的文件名
				String localeFileName = localeFile.getName();
				// 进行文件名变换
				String changeFileName = changeFileName(localeFileName, files);
				// 进行文件名查询
				String fileNameTemp = new String(changeFileName.getBytes("utf-8"), "ISO-8859-1"); // 进行文件名的转换,防止乱码
				String pathTemp = new String(remotePath.getBytes("GBK"), "ISO-8859-1");
				ftpClient.changeWorkingDirectory(pathTemp);
				input = new FileInputStream(localeFile);// 进行文件上传
				ftpClient.storeFile(fileNameTemp, input);
				String replyString = ftpClient.getReplyString();
				if (replyString.indexOf("226") > -1) {
					logger.info("文件[" + localeFilePath + "]上传成功");
					return true;
				} else {
					logger.error("文件[" + localeFilePath + "]上传失败,原因:" + replyString);
					return false;
				}

			} catch (IOException e) {
				logger.error("", e);
				throw new FtpException("FTP上传[" + localeFilePath + "]出错!", e);

			}
		}
	}

	/**
	 * 
	 * 简要说明：变换文件名 编写者：陈骑元 创建时间：2017年1月1日 上午11:21:58
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public String changeFileName(String fileName, FTPFile[] files) {
		int n = 0;
		// 创建一个可变的字符串对象 即StringBuffer对象，把filename值付给该对象
		StringBuffer fileNameSb = new StringBuffer("");
		fileNameSb = fileNameSb.append(fileName);
		while (checkFileName(fileNameSb.toString(), files)) {
			n++;
			String a = "[" + n + "]";
			int b = fileNameSb.lastIndexOf(".");// 最后一出现小数点的位置

			int c = fileNameSb.lastIndexOf("[");// 最后一次"["出现的位置

			if (c < 0) {
				c = b;
			}
			StringBuffer name = new StringBuffer(fileNameSb.substring(0, c));// 文件的名字
			StringBuffer suffix = new StringBuffer(fileNameSb.substring(b + 1));// 后缀的名称
			fileNameSb = name.append(a).append(".").append(suffix);

		}
		return fileNameSb.toString();

	}

	/**
	 * 
	 * 简要说明：检查文件是否存在 编写者：陈骑元 创建时间：2017年1月1日 上午11:22:39
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public static boolean checkFileName(String fileName, FTPFile[] files) {
		for (int i = 0; i < files.length; i++) {
			FTPFile ff = files[i];
			if (ff.getName().equals(fileName)) {
				return true; // 如果存在返回 正确信号
			}

		}
		return false;

	}

	/**
	 * 下载
	 * 
	 * @param remotePath
	 *            下载目录
	 * @param localPath
	 *            本地目录
	 * @return
	 * @throws Exception
	 */
	public boolean downLoadFile(String remotePath, String localPath) throws FtpException {
		synchronized (LOCK) {
			try {
				if (ftpClient.changeWorkingDirectory(remotePath)) {// 转移到FTP服务器目录
					FTPFile[] files = ftpClient.listFiles();
					if (files.length > 0) {
						File localdir = new File(localPath);
						if (!localdir.exists()) {
							localdir.mkdir();
						}
					}
					for (FTPFile ff : files) {
						if (!downLoadFile(ff, localPath)) {
							return false;
						}
					}
					return files.length > 0;
				}
			} catch (IOException e) {
				logger.error("", e);
				throw new FtpException("FTP下载[" + localPath + "]出错!", e);
			}
			return false;
		}
	}

	/**
	 * 递归下载文件
	 * 
	 * @param ftpFile
	 *            下载文件/目录
	 * @param localPath
	 *            本地目录
	 * @return
	 */
	public boolean downLoadFile(FTPFile ftpFile, String localPath) {
		// 当前处理文件本地路径
		String fileLocalPath = localPath + "/" + ftpFile.getName();
		if (ftpFile.isFile()) {// down file
			if (ftpFile.getName().indexOf("?") == -1) {
				OutputStream outputStream = null;
				try {
					File localFile = new File(fileLocalPath);
					if (!localFile.getParentFile().exists()) {
						localFile.getParentFile().mkdirs();
					}
					outputStream = new FileOutputStream(localFile);
					ftpClient.retrieveFile(ftpFile.getName(), outputStream);
					outputStream.flush();
					outputStream.close();
					logger.info("[" + localFile.getAbsolutePath() + "]下载成功!");
					return true;
				} catch (Exception e) {
					logger.error("", e);
					throw new FtpException("FTP下载[" + fileLocalPath + "]出错!", e);
				} finally {
					try {
						if (outputStream != null)
							outputStream.close();
					} catch (IOException e) {
					}
				}
			}
		} else { // deal dirctory
			File file = new File(fileLocalPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				// enter relative workdirectory
				if (ftpClient.changeWorkingDirectory(ftpFile.getName())) {
					logger.info("[" + file.getAbsolutePath() + "]目录");
					FTPFile[] files = null;
					files = ftpClient.listFiles();
					for (int i = 0; i < files.length; i++) {
						downLoadFile(files[i], fileLocalPath);
					}
					ftpClient.changeToParentDirectory();// return parent
														// directory
					return true;
				}
			} catch (Exception e) {
				logger.error("", e);
				throw new FtpException("FTP下载[" + fileLocalPath + "]出错!", e);
			}
		}
		return false;
	}

	/** 获得目录下最大文件名 */
	public String getMaxFileName(String remotePath) {
		try {
			ftpClient.changeWorkingDirectory(remotePath);
			FTPFile[] files = ftpClient.listFiles();
			Arrays.sort(files, new Comparator<FTPFile>() {
				public int compare(FTPFile o1, FTPFile o2) {
					return o2.getName().compareTo(o1.getName());
				}
			});
			return files[0].getName();
		} catch (IOException e) {
			logger.error("", e);
			throw new FtpException("FTP访问目录[" + remotePath + "]出错!", e);
		}
	}

	/**
	 * 连接参数
	 * 
	 * @param properties
	 *            <br>
	 *            FTPHOSTNAME:IP; FTPPORT:端口; FTPUSERNAME:用户名; FTPPASSWORD:密码
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * 连接参数
	 * 
	 * @param properties
	 *            <br>
	 *            FTPHOSTNAME:IP; FTPPORT:端口; FTPUSERNAME:用户名; FTPPASSWORD:密码
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = new Properties();
		String[] key = { "FTPHOSTNAME", "FTPPORT", "FTPUSERNAME", "FTPPASSWORD" };
		for (int i = 0; i < key.length; i++) {
			this.properties.put(key[i], properties.get(key[i]));
		}
	}
}