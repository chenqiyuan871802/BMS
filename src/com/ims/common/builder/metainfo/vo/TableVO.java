package com.ims.common.builder.metainfo.vo;

import com.ims.common.core.matatype.BasePO;

/**
 * 
 * 类描述： <b>数据表值对象</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-6-10 上午10:59:20
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
public class TableVO extends BasePO{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 表名
	 */
	private String name;
	
	/**
	 * 注释
	 */
	private String comment;
	
	/**
	 * 所有者
	 */
	private String owner;
	
	/**
	 * 自增列主键的名称(如果表是以自增列作为主键)
	 */
	private String autoincrementPkName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAutoincrementPkName() {
		return autoincrementPkName;
	}

	public void setAutoincrementPkName(String autoincrementPkName) {
		this.autoincrementPkName = autoincrementPkName;
	}

	
}
