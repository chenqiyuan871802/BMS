package com.ims.common.core.asset;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.ims.common.core.matatype.Dto;

/**
 * 
 * 类名:com.ims.common.core.asset.HttpUtil
 * 描述:HTTP工具类
 * 编写者:陈骑元
 * 创建时间:2017年3月13日 下午4:44:10
 * 修改说明:
 */

public final class HttpUtil {
	private static final Logger logger = Logger.getLogger(HttpUtil.class);
	private static HttpClient httpClient = null;  
    private static final String CHARSET = "UTF-8";  
	private HttpUtil() {
	}
	public static synchronized HttpClient getSaveHttpClient(){  
        if(httpClient == null){  
        	HttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
        	HttpConnectionManagerParams managerParams = new HttpConnectionManagerParams();
        	managerParams.setConnectionTimeout(30000);
        	managerParams.setMaxTotalConnections(50);
        	managerParams.setDefaultMaxConnectionsPerHost(30);
        	managerParams.setSoTimeout(20000);
        	manager.setParams(managerParams);
        	httpClient = new HttpClient(manager);
        	httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,CHARSET);
        }  
        return httpClient;  
    }
	

	public static final String httpClientGet(String url,String param) {
		String result = "";
		HttpClient client = HttpUtil.getSaveHttpClient();
		GetMethod getMethod = new GetMethod(url+"?"+param);
		try {
			
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == 200){
				result = getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			getMethod.releaseConnection();
		}
		return result;
	}

	public static final String httpClientPost(String url, ArrayList<NameValuePair> list) {
		String result = "";
		HttpClient client = HttpUtil.getSaveHttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			NameValuePair[] params = new NameValuePair[list.size()];
			for (int i = 0; i < list.size(); i++) {
				params[i] = list.get(i);
			}
			postMethod.addParameters(params);
			client.executeMethod(postMethod);
			result = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	
	public static final String httpClientPost(String url,Dto paramDto) {
		String result = "";
		HttpClient client = HttpUtil.getSaveHttpClient();
		PostMethod postMethod = new PostMethod(url);
		try {
			for(Map.Entry<String,Object> entry:paramDto.entrySet()){    
			    postMethod.addParameter(entry.getKey(), entry.getValue()+"");
			}   
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == 200){
				result = postMethod.getResponseBodyAsString();
			}
			
		} catch (Exception e) {
			logger.error(e);
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
}
