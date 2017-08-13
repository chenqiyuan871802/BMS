package com.ims.common.core.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.support.velocity.VelocityHelper;
import com.ims.common.core.tag.resources.TagResources;
import com.ims.common.system.modules.po.DictionaryPO;

/**
 * 
 * 类描述： 字典数据存储存储过滤标签
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：Oct 7, 2016 9:34:49 AM
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class CodeStoreFilterTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CodeStoreFilterTag.class);
	/**
	 * 字典名称
	 */
	private String field;
	/**
	 * 重命名标签函数
	 */
	private String rename;
	/**
	 * 过滤数据
	 */
	private String filter;
	
	

	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public String getRename() {
		return rename;
	}


	public void setRename(String rename) {
		this.rename = rename;
	}


	public String getFilter() {
		return filter;
	}


	public void setFilter(String filter) {
		this.filter = filter;
	}

    /**
     * 
     * 简要说明：判断字符串是否存在数组中
     * 编写者：陈骑元
     * 创建时间：2017年3月10日 下午5:09:51
     * @param 说明
     * @return 说明
     */
	public boolean exists(String[] array,String str){
		 if(IMSUtils.isNotEmpty(array)&&IMSUtils.isNotEmpty(str)){
			 for(int i=0;i<array.length;i++){
				 String strTemp=array[i];
				 if(str.equals(strTemp)){
					 return true;
				 }
			 }
		 }
		 return false;
		 
	}
	/**
	 * 标签开始
	 */
	@SuppressWarnings("static-access")
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append(IMSCons.SCRIPT_START);
		Dto vmDto=Dtos.newDto();
		String[] filterArray=filter.split(IMSCons.MARK_CSV);
		List<DictionaryPO> codeList=IMSCxt.getDictionaryList(field);
		List<DictionaryPO> newcodeList=Lists.newArrayList();
		for(DictionaryPO dicPO:codeList){
			if(!exists(filterArray,dicPO.getDic_code())){
				newcodeList.add(dicPO);
			}
		}
		 if(IMSUtils.isNotEmpty(rename)){
			 field=rename;
		 }
		 vmDto.put("field", field);
		 vmDto.put("codeList", newcodeList);
		 StringWriter writer = VelocityHelper.mergeFileTemplate(TagResources.CODE_STORE_VM, vmDto);
		 sb.append(writer.toString()).append("\n");
	
		sb.append(IMSCons.SCRIPT_END);
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

	/**
	 * 释放资源
	 */
	public void release() {
		field = null;
		rename = null;
		filter = null;
		super.release();
	}

	

}
