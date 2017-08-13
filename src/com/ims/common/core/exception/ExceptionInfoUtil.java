package com.ims.common.core.exception;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.impl.HashDto;
/**
 * 
 * 类描述： <b>异常信息参数配置加载类</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:16:03
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:16:03
 * 修改备注： 
 * @version
 */
@SuppressWarnings("rawtypes")
public class ExceptionInfoUtil {

	private static Log log = LogFactory.getLog(ExceptionInfoUtil.class);

	private static Dto parameterCacheDto = null;

	static {
		try {
			if (log.isDebugEnabled()) {
				log.debug("解析XML异常参数配置文件...");
			}
			parameterCacheDto = new HashDto();
			SAXReader reader = new SAXReader();
			InputStream is = ExceptionInfoUtil.class.getResourceAsStream("exceptionInfo.xml");
			Document document = reader.read(is);
			Element elRoot = document.getRootElement();
			Iterator elIt = elRoot.elementIterator();
			while (elIt.hasNext()) {
				Element el = (Element) elIt.next();
				Attribute attrKey = el.attribute("id");
				String id = attrKey.getText();
				Attribute attrInfo = el.attribute("info");
				String info = attrInfo.getText();
				Attribute attrSuggest = el.attribute("suggest");
				String suggest = attrSuggest.getText();
				ExceptionVO exceptionVO = new ExceptionVO();
				exceptionVO.setId(id);
				exceptionVO.setInfo(info);
				exceptionVO.setSuggest(suggest);
				parameterCacheDto.put(id, exceptionVO);
			}
		} catch (Exception e) {
			throw new IMSException("解析XML异常参数配置文件出错.", e);
		}
	}

	/**
	 * 获取异常配置参数
	 * 
	 * @param pKey
	 * @return
	 */
	public static ExceptionVO getExceptionInfo(String errID) {
		ExceptionVO vo = (ExceptionVO) parameterCacheDto.get(errID);
		return vo;
	}

	public static void main(String[] args) {

	}

}
