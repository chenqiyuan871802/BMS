package com.ims.common.core.asset;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.ims.common.core.matatype.Dto;

/**
 * 类名:com.ims.common.core.asset.HttpRequestProxy
 * 描述:
 * 编写者:陈骑元
 * 创建时间:2017年4月29日 下午4:01:20
 * 修改说明:
 */
public class HttpRequestProxy {

	/**
	 * 连接超时
	 */
	private static int connectTimeOut = 5000;

	/**
	 * 读取数据超时
	 */
	private static int readTimeOut = 10000;

	/**
	 * 请求编码
	 */
	public static String requestEncoding = "UTF-8";

	private static Logger logger = Logger.getLogger(HttpRequestProxy.class);
    
	/**
	 * 
	 * 简要说明：默认使用UTF-8请求
	 * 编写者：陈骑元
	 * 创建时间：2017年4月29日 下午5:12:04
	 * @param 说明
	 * @return 说明
	 */
	public static String doGet(String reqUrl,Dto parameters){
		
		return doGet(reqUrl,parameters,requestEncoding);
	}
	/**
	 * <pre>
	 * 发送带参数的GET的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param parameters
	 *            参数映射表
	 * @return HTTP响应的字符串
	 */
	public static String doGet(String reqUrl, Dto  parameters,
			String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			url_con = doHttpGetConnection(reqUrl,parameters);

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			logger.error("网络故障", e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}

		return responseContent;
	}

	private static HttpURLConnection doHttpGetConnection(String reqUrl,
			Dto  parameters) throws IOException {
		HttpURLConnection url_con = null;
		String params = getDtoParam(parameters,HttpRequestProxy.requestEncoding);
		
		URL url = new URL(reqUrl);
		url_con = (HttpURLConnection) url.openConnection();
		url_con.setRequestMethod("GET");
		System.setProperty("sun.net.client.defaultConnectTimeout",
				String.valueOf(HttpRequestProxy.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
		System.setProperty("sun.net.client.defaultReadTimeout",
				String.valueOf(HttpRequestProxy.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
		
		url_con.setDoOutput(true);
		byte[] b = params.toString().getBytes();
		url_con.getOutputStream().write(b, 0, b.length);
		url_con.getOutputStream().flush();
		url_con.getOutputStream().close();

		return url_con;
	}

	/**
	 * <pre>
	 * 发送不带参数的GET的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @return HTTP响应的字符串
	 */
	public static String doGet(String reqUrl, String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			String queryUrl = reqUrl;
			int paramIndex = reqUrl.indexOf("?");

			if (paramIndex > 0) {
				queryUrl = reqUrl.substring(0, paramIndex);
				String parameters = reqUrl.substring(paramIndex + 1,
						reqUrl.length());
				String[] paramArray = parameters.split("&");
				for (int i = 0; i < paramArray.length; i++) {
					String string = paramArray[i];
					int index = string.indexOf("=");
					if (index > 0) {
						String parameter = string.substring(0, index);
						String value = string.substring(index + 1,
								string.length());
						params.append(parameter);
						params.append("=");
						params.append(URLEncoder.encode(value,
								HttpRequestProxy.requestEncoding));
						params.append("&");
					}
				}

				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(queryUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("GET");
			System.setProperty("sun.net.client.defaultConnectTimeout",
					String.valueOf(HttpRequestProxy.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
			System.setProperty("sun.net.client.defaultReadTimeout",
					String.valueOf(HttpRequestProxy.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
			
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			logger.error("网络故障", e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}

		return responseContent;
	}

	/**
	 * <pre>
	 * 发送带参数的POST的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param parameters
	 *            参数映射表
	 * @return HTTP响应的字符串
	 */
	public static String doPost(String reqUrl, Dto  parameters,
			String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			String params = getDtoParam(parameters,HttpRequestProxy.requestEncoding);
			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			System.setProperty("sun.net.client.defaultConnectTimeout",
					String.valueOf(HttpRequestProxy.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
			System.setProperty("sun.net.client.defaultReadTimeout",
					String.valueOf(HttpRequestProxy.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
			
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			logger.error("网络故障", e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}
	
	
	/**
	 * <pre>
	 * 发送带参数的POST的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param parameters
	 *            参数映射表
	 * @return HTTP响应的字符串
	 */
	public static String doJsonPost(String reqUrl, Dto  parameters,
			String jsonData) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			String params = getDtoParam(parameters,HttpRequestProxy.requestEncoding);

			URL url = new URL(reqUrl+"&"+params);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			System.setProperty("sun.net.client.defaultConnectTimeout",
					String.valueOf(HttpRequestProxy.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
			System.setProperty("sun.net.client.defaultReadTimeout",
					String.valueOf(HttpRequestProxy.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
			url_con.setDoOutput(true);
			url_con.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
			url_con.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
			
			byte[] b = jsonData.toString().getBytes(HttpRequestProxy.requestEncoding);
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					HttpRequestProxy.requestEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			logger.error("网络故障", e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}

	/**
	 * 
	 * @param reqUrl
	 * @param parameters
	 * @param recvEncoding
	 * @param fileIn
	 *            文件流
	 * @return
	 */
	public static String uploadMedia(String reqUrl, Dto  parameters,
			String recvEncoding, InputStream fileIn, String fileName,
			String contentType) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			String params = getDtoParam(parameters,HttpRequestProxy.requestEncoding);

			URL urlObj = new URL(reqUrl + "&" + params.toString());
			// 连接
			url_con = (HttpURLConnection) urlObj.openConnection();
			/**
			 * 设置关键值
			 */
			url_con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			url_con.setDoInput(true);
			url_con.setDoOutput(true);
			url_con.setUseCaches(false); // post方式不能使用缓存

			// 设置请求头信息
			url_con.setRequestProperty("Connection", "Keep-Alive");
			url_con.setRequestProperty("Charset",recvEncoding);

			// 设置边界
			url_con.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			// 请求正文信息

			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
					+ fileName + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] head = sb.toString().getBytes(recvEncoding);

			// 获得输出流
			OutputStream out = new DataOutputStream(url_con.getOutputStream());
			// 输出表头
			out.write(head);
			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			DataInputStream in = new DataInputStream(fileIn);
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(recvEncoding);// 定义最后数据分隔线

			out.write(foot);
			out.flush();
			out.close();

			InputStream iddn = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(iddn,
					recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
		} catch (IOException e) {
			logger.error("网络故障", e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}
	
	/**
	 * 将参数转换成string
	 * @param paramDto
	 * @param requestEncoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	private static String getDtoParam(Dto paramDto,String requestEncoding) throws IOException{
		StringBuffer params = new StringBuffer();
		// 设置边界
		for (Iterator iter = paramDto.entrySet().iterator(); iter
				.hasNext();) {
			Entry element = (Entry) iter.next();
			params.append(element.getKey().toString());
			params.append("=");
			params.append(URLEncoder.encode(element.getValue().toString(),
					requestEncoding));
			params.append("&");
		}

		if (params.length() > 0) {
			params = params.deleteCharAt(params.length() - 1);
		}
		
		return params.toString();
	}
	
	/**
	 * <pre>
	 * 发送带参数的GET的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param parameters
	 *            参数映射表
	 * @return HTTP响应的字符串
	 */
	public static String downMadGet(String reqUrl, Dto  parameters,String filePath,String fileId) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			url_con = doHttpGetConnection(reqUrl,parameters);
            String fileContentType = url_con.getHeaderField("Content-Type");
            InputStream in = url_con.getInputStream();
            if("text/plain".equals(fileContentType)){
            	BufferedReader rd = new BufferedReader(new InputStreamReader(in,
            			requestEncoding)); 
            	responseContent = rd.readLine();
            	rd.close();
            }else{
            	String fileSuffix =IMSPropertiesUtil.getFileSuffix(fileContentType);
            	BufferedInputStream bis = new BufferedInputStream(in);
            	String fileName =fileId+"."+fileSuffix;
            	String filePathName = filePath+File.separator+fileName;
				byte[] buf = new byte[8096];  
				OutputStream fileOut = new FileOutputStream(new File(filePathName));
				int size = 0;
				while ((size = bis.read(buf)) != -1) {
					fileOut.write(buf, 0, size);  
				}
				fileOut.flush();
				fileOut.close();
				bis.close();
				responseContent = "{filepath:'"+filePath+"',fileName:'"+fileName+"'}";
            }
            System.out.println(fileContentType);
			in.close();
		} catch (IOException e) {
			logger.error("网络故障", e);
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}

		return responseContent;
	}

	/**
	 * @return 连接超时(毫秒)
	 * @see com.hengpeng.common.web.HttpRequestProxy#connectTimeOut
	 */
	public static int getConnectTimeOut() {
		return HttpRequestProxy.connectTimeOut;
	}

	/**
	 * @return 读取数据超时(毫秒)
	 * @see com.hengpeng.common.web.HttpRequestProxy#readTimeOut
	 */
	public static int getReadTimeOut() {
		return HttpRequestProxy.readTimeOut;
	}

	/**
	 * @return 请求编码
	 * @see com.hengpeng.common.web.HttpRequestProxy#requestEncoding
	 */
	public static String getRequestEncoding() {
		return requestEncoding;
	}

	/**
	 * @param connectTimeOut
	 *            连接超时(毫秒)
	 * @see com.hengpeng.common.web.HttpRequestProxy#connectTimeOut
	 */
	public static void setConnectTimeOut(int connectTimeOut) {
		HttpRequestProxy.connectTimeOut = connectTimeOut;
	}

	/**
	 * @param readTimeOut
	 *            读取数据超时(毫秒)
	 * @see com.hengpeng.common.web.HttpRequestProxy#readTimeOut
	 */
	public static void setReadTimeOut(int readTimeOut) {
		HttpRequestProxy.readTimeOut = readTimeOut;
	}

	/**
	 * @param requestEncoding
	 *            请求编码
	 * @see com.hengpeng.common.web.HttpRequestProxy#requestEncoding
	 */
	public static void setRequestEncoding(String requestEncoding) {
		HttpRequestProxy.requestEncoding = requestEncoding;
	}

}
