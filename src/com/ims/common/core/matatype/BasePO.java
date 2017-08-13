package com.ims.common.core.matatype;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.asset.IMSXmlUtils;
import com.ims.common.core.matatype.impl.HashDto;

/**
 * 
 * 类描述： <b>值对象</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-2 上午12:36:31
 * 修改人：蓝枫 
 * 修改时间：2016-6-2 上午12:36:31
 * 修改备注： 
 * @version
 */
public abstract class BasePO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(BasePO.class);

	/**
	 * 将当前对象转换为Dto对象
	 * 
	 * @return dto 返回的Dto对象
	 */
	public Dto toDto() {
		Dto dto = new HashDto();
		IMSUtils.copyProperties(this, dto);
		return dto;
	}

	/**
	 * 将当前对象转换为XML字符串
	 * 
	 * @param pStyle
	 *            XML文档风格
	 * @return String 返回的XML格式字符串
	 */
	public String toXml() {
		String xmlString = IMSXmlUtils.toNodeXmlFromDto(this.toDto(), "root");
		return xmlString;
	}

	/**
	 * 将当前对象转换为JSON字符串
	 * 
	 * @return String 返回的JSON格式字符串
	 */
	public String toJson() {
		String jsonString = IMSJson.toJson(this);
		return jsonString;
	}

	/**
	 * 清除当前对象属性
	 */
	public void clear() {
		Method[] methods = this.getClass().getMethods();
		for (int i = 0, n = methods.length; i < n; i++) {
			try {
				Method method = methods[i];
				if ((method.getModifiers() & Modifier.PUBLIC) == 1) {
					String methodName = method.getName();
					if (methodName.startsWith("set")) {
						method.invoke(this, new Object[] { null });
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将此对象以可读形式打印输出
	 * 
	 */
	public void println() {
		if (log.isDebugEnabled()) {
			System.out.println(IMSCons.CONSOLE_FLAG1 + toJson());
		}
	}
}
