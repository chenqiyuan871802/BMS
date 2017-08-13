package com.ims.common.core.asset;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.impl.HashDto;
/**
 * 
 * 类描述： <b>JSON资料格式处理类</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:46:47
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:46:47
 * 修改备注： 
 * @version
 */
public class IMSJson {

	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(IMSJson.class);

	private static Gson gson;

	static {
		GsonBuilder builder = new GsonBuilder();
		// 注册日期时间类型反序列化时的适配器(针对反序列化到JavaBean的情况，Map类型不需要处理)
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return IMSUtils.stringToDate(json.getAsString());
			}
		});
		gson = builder.create();
	}

	/**
	 * 将Java对象进行JSON序列化
	 * <p>
	 * 支持自定义日期时间类型格式
	 * <p>
	 * 
	 * @param pObject
	 * @param pDateFormat
	 *            日期时间类型格式字符串
	 * @return
	 */
	public static final String toJson(Object pObject, String pDateFormat) {
		String jsonString = "";
		if (IMSUtils.isEmpty(pDateFormat)) {
			pDateFormat = IMSCons.DATETIME;
		}
		GsonBuilder builder = new GsonBuilder();
	/*	if (IMSCons.JSON_FORMAT.equals(WebCxt.getCfgOfDB("json_format"))) {
			builder.setPrettyPrinting();
		}*/
		builder.setDateFormat(pDateFormat);
		Gson gson = builder.create();
		jsonString = gson.toJson(pObject);
		return jsonString;
	}

	/**
	 * 将Java对象进行JSON序列化
	 * <p>
	 * 缺省的日期时间类型为：yyyy-MM-dd HH:mm:ss
	 * <p>
	 * 
	 * @param pObject
	 * @return
	 */
	public static final String toJson(Object pObject) {
		String jsonString = toJson(pObject, IMSCons.DATETIME);
		return jsonString;
	}

	/**
	 * 将Java集合对象序列化为表格分页所需的Json对象<b>(服务器端分页)</b>
	 * <p>
	 * 缺省的日期时间类型为：yyyy-MM-dd HH:mm:ss
	 * <p>
	 * 
	 * @param pList 集合对象
	 * @param total 集合总数 
	 * @return
	 */
	public static final String toGridJson(List<? extends Object> pList, int total) {
		Dto tempDto = new HashDto();
		tempDto.put(IMSCons.READER_ROOT_PROPERTY, pList);
		tempDto.put(IMSCons.READER_TOTAL_PROPERTY, total);
		String jsonString = toJson(tempDto, IMSCons.DATETIME);
		return jsonString;
	}
	/**
	 * 将Java集合对象序列化为表格分页所需的Json对象<b>(服务器端分页)</b>
	 * <p>
	 * 缺省的日期时间类型为：yyyy-MM-dd HH:mm:ss
	 * <p>
	 * 
	 * @param pList 集合对象
	 * @param total 集合总数 
	 * @param pDateFormat 日期格式化
	 * @return
	 */
	public static final String toGridJson(List<? extends Object> pList, int total, String pDateFormat) {
		Dto tempDto = new HashDto();
		tempDto.put(IMSCons.READER_ROOT_PROPERTY, pList);
		tempDto.put(IMSCons.READER_TOTAL_PROPERTY, total);
		String jsonString = toJson(tempDto, pDateFormat);
		return jsonString;
	}
	
	/**
	 * 将Java集合对象序列化为表格分页所需的Json对象<b>(前台客户端分页或不分页)</b>
	 * <p>
	 * 缺省的日期时间类型为：yyyy-MM-dd HH:mm:ss
	 * <p>
	 * 
	 * @param pList 集合对象
	 * @return
	 */
	public static final String toGridJson(List<? extends Object> pList) {
		String jsonString = toJson(pList, IMSCons.DATETIME);
		return jsonString;
	}

	/**
	 * 将Json字符串转换为Java对象
	 * 
	 * @param json
	 * @param type
	 *            如果Java对象是一个普通类则直接JsonUtils.fromJson(json,
	 *            HashDto.class);即可。如果是一个泛型类(如一个dto集合类)则需要
	 *            使用如下方式传参：JsonUtils.fromJson(json, new
	 *            TypeToken<List<HashDto>>() {}.getType());
	 * @return
	 */
	public static final <T> T fromJson(String json, Type type) {
		T list = gson.fromJson(json, type);
		return (T) list;
	}

	/**
	 * 将json转换为List<Dto>集合对象的简便方法
	 * 
	 * @param json
	 * @return
	 */
	public static final List<Dto> fromJson(String json) {
		List<Dto> list = fromJson(json, new TypeToken<List<HashDto>>() {
		}.getType());
		return list;
	}
}