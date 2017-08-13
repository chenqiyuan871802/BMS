package com.ims.common.core.exception;
/**
 * 
 * 类描述： <b>异常信息VO</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:23:18
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:23:18
 * 修改备注： 
 * @version
 */
public class ExceptionVO {
	
	/**
	 * 异常编号
	 */
	private String id;
	
	/**
	 * 异常摘要信息
	 */
	private String info;
	
	/**
	 * 异常排查建议
	 */
	private String suggest;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
}
