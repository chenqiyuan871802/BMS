package com.ims.common.system.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.ShopUserPO;
import com.beauty.wechat.constant.WechatCons;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.system.modules.po.UserPO;

/**
 * 
 * 类描述： 请求过滤器 创建人：陈骑元 邮箱：240823329@qq.com 创建时间：Oct 11, 2016 1:44:21 AM 修改人：
 * 修改时间： 修改备注：
 * 
 * @version 1.0
 */
public class HttpRequestFilter implements Filter {

	private Log log = LogFactory.getLog(HttpRequestFilter.class);
	private Boolean enabled; // 过滤器开关
	private String[] excludes; // 忽略过滤配置

	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String enabledCfg = filterConfig.getInitParameter("enabled");
		enabledCfg = IMSUtils.isEmpty(enabledCfg) ? "true" : enabledCfg;
		enabled = BooleanUtils.toBoolean(enabledCfg);
		String excludesCfg = filterConfig.getInitParameter("excludes");
		excludes = StringUtils.split(excludesCfg, ",");
	}

	/**
	 * 过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Dto paramDto=Dtos.newDto(httpRequest);
		String requestUri = httpRequest.getRequestURI();
		String logString = IMSCons.CONSOLE_FLAG3 + "HTTP请求: " + requestUri + " >> 参数列表: "
				+ IMSCxt.getParamAsDto(httpRequest);
		log.info(logString);
		if (!enabled) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		if (isExclude(requestUri)) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		if (requestUri.indexOf("/nodify/") > -1) { // 有关的支付通知都放行
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		if (requestUri.indexOf("/wechat/shareBag/") > -1) { // 有关的支付通知都放行
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		String ctxPath = httpRequest.getContextPath();
		if (requestUri.indexOf("/wechat/") > -1) { // 微信端的访问
			HttpSession session=httpRequest.getSession();
			String code=(String)session.getAttribute("accessTokenCode");
			if (IMSUtils.isEmpty(code)) { //如果code 为空则进行静默授权
				 requestUri=requestUri+"?"+this.getDtoParam(paramDto, "UTF-8");
				 session.setAttribute("accessTokenCode", code); // code说明
				 session.setAttribute("backRequestUri", requestUri); // 记住进入页面,登陆成功后,返回该页面,上一级操作
			    httpResponse.sendRedirect(WechatCxt.getOauth2Url("wechat/login/goLogin.jhtml", WechatCons.GRANT_NO));
				return;
			}

		} else if (requestUri.indexOf("/shop/") > -1) { // 商家端访问
			ShopUserPO userPO = IMSCxt.getShopUserInfo(httpRequest.getSession());
			if (IMSUtils.isEmpty(userPO)) {
				httpResponse.getWriter().write("<script type=\"text/javascript\">parent.location.href='" + ctxPath
						+ "/shop/login/goLogin.jhtml'</script>");
				httpResponse.getWriter().flush();
				httpResponse.getWriter().close();
				return;
			}
		} else {
			UserPO userPO = IMSCxt.getUserInfo(httpRequest.getSession());
			if (IMSUtils.isEmpty(userPO)) {
				httpResponse.getWriter().write("<script type=\"text/javascript\">parent.location.href='" + ctxPath
						+ "/system/login/goLogin.jhtml'</script>");
				httpResponse.getWriter().flush();
				httpResponse.getWriter().close();
				return;
			}
		}

		chain.doFilter(httpRequest, httpResponse);
	}
	/**
	 * 将参数转换成string
	 * @param paramDto
	 * @param requestEncoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	private String getDtoParam(Dto paramDto,String requestEncoding) throws IOException{
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
	 * 检查URI例外
	 * 
	 * @param uri
	 *            待检查的URI
	 * @return true: 例外
	 */
	private boolean isExclude(String uri) {
		boolean out = false;
		for (String exclude : excludes) {
			if (StringUtils.indexOfIgnoreCase(uri, exclude) != -1) {
				out = true;
				break;
			}
		}
		return out;
	}

	/**
	 * 释放资源
	 */
	@Override
	public void destroy() {
		enabled = null;
		excludes = null;
	}

}
