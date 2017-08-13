package com.ims.common.core.matatype;

import javax.servlet.http.HttpServletRequest;

import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.impl.HashDto;



/**
 * 
 * 类描述： <b>数据传输对象实例化辅助类</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:38:21
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:38:21
 * 修改备注： 
 * @version
 */
public class Dtos {

	/**
	 * 创建一个常规的Dto对象
	 * 
	 */
	public static Dto newDto() {
		return new HashDto();
	}
	
	/**
	 * 创建一个响应Request的Dto对象
	 * <p>
	 * 缺省加入KV变量：outDto.put(AOSCons.REQUEST_SUCCESS, true); outDto.setAppCode(AOSCons.SUCCESS);<br>
	 * setAppCode()可覆盖。
	 * </P>
	 */
	public static Dto newOutDto() {
		Dto outDto = Dtos.newDto();
		//请求成功参数标识
		outDto.put(IMSCons.REQUEST_SUCCESS, true);
		//业务处理成功标识。如果业务失败，则重新赋值。
		outDto.setAppCode(IMSCons.SUCCESS);
		return outDto;
	}

	/**
	 * 创建一个常规的Dto对象，并初始化一个键值对。
	 * 
	 * @param keyString
	 * @param valueObject
	 * @return
	 */
	public static Dto newDto(String keyString, Object valueObject) {
		Dto dto = new HashDto();
		dto.put(keyString, valueObject);
		return dto;
	}

	/**
	 * <b>创建一个带有初始化参数的Dto对象</b>
	 * <p>
	 * 参数包含：1、界面提交的请求参数；2、当前用户对象(UserInfoVO)，通过getUserInfo()获得。
	 * 
	 * @param request
	 * @return
	 */
	public static Dto newDto(HttpServletRequest request) {
		return new HashDto(request);
	}

	/**
	 * 创建一个数学运算SQL的参数DTO
	 * <p>
	 * 如：Dto dto = Dtos.newCalcDto("MIN(cascade_id_)");
	 * 
	 * @param expr
	 *            构造参数为运算表达式
	 */
	public static Dto newCalcDto(String expr) {
		Dto dto = newDto();
		dto.put(IMSCons.CALCEXPR, expr);
		return dto;
	}

}
