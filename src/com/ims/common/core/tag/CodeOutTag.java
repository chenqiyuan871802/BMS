package com.ims.common.core.tag;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.system.modules.po.DictionaryPO;

/**
 * 
 * 类描述： 字典数据存储标签
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：Oct 7, 2016 9:34:49 AM
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class CodeOutTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CodeOutTag.class);
	private String field;
	private String codeKey;
	private String mark=IMSCons.MARK_CSV;

	

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	/**
	 * 标签开始
	 */
	@SuppressWarnings("static-access")
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		if(IMSUtils.isNotEmpty(codeKey)){
			List<DictionaryPO> codeList=IMSCxt.getDictionaryList(field);
			String[]  keyArray=codeKey.split(mark);
			for (int i = 0; i < codeList.size(); i++) {
				DictionaryPO codeValue=codeList.get(i);
				for(int j=0;j<keyArray.length;j++){
					 String key=keyArray[j];
					 if(codeValue.getDic_code().equals(key)){
						 sb.append(codeValue.getDic_value()).append(IMSCons.MARK_PAUSE);
					 }
				}
				
			}
			if(sb.length()>1){
				sb.deleteCharAt(sb.length()-1);
			}
		
			
		}else{
			log.error("字典标签对应的codeKey字段不能为空");
		}
		
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			log.error("字典标签发生错误："+ e.getMessage());
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

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * 释放资源
	 */
	


}
