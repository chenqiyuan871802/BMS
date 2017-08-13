package com.ims.common.core.tag;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.system.modules.po.DictionaryPO;

/**
 * 
 * 类描述： 全局参数输出标签
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：Oct 7, 2016 9:34:49 AM
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class ParamOutTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(ParamOutTag.class);
	private String paramKey;
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	private String defaultValue="";
	

	/**
	 * 标签开始
	 */
	@SuppressWarnings("static-access")
	public int doStartTag() throws JspException {
		
		try {
			String outValue=IMSCxt.getParamValue(paramKey,defaultValue);
			pageContext.getOut().write(outValue);
		} catch (IOException e) {
			log.error("键值参数标签发生错误："+ e.getMessage());
			e.printStackTrace();
		}
		return super.SKIP_BODY;
	}

	/**
	 * 标签结束
	 */
	@SuppressWarnings("static-access")
	public int doEndTag() throws JspException {
		return super.EVAL_PAGE;
	}


	/**
	 * 释放资源
	 */
	public void release() {
		paramKey = null;
		super.release();
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

 

}
