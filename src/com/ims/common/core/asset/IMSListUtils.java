  package com.ims.common.core.asset;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;
import org.josql.expressions.SelectItemExpression;



import com.google.common.collect.Lists;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
/**
 * 
 * 类描述：  <b>集合工具类</b>
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:54:30
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:54:30
 * 修改备注： 
 * @version
 */
public class IMSListUtils {

	/**
	 * 集合查询 使用DISTINCT去除值相等的记录，则必须指明字段。DISTINCT * 只能去除对象相等的记录，不能去除对象里值相等的那种记录。
	 * 
	 * @param targetList
	 *            目标集合
	 * @param clazz
	 *            集合存储对象类元
	 * @param jqlText
	 *            JOSQL脚本
	 * @param queryDto
	 *            查询条件
	 * @return 结果集合 集合内对象类型原集合的中对象的类型
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T select(List<?> targetList, Class clazz, String jqlText, Dto queryDto) {
		if (IMSUtils.isEmpty(clazz) || IMSUtils.isEmpty(jqlText)) {
			return (T) targetList;
		}
		if (IMSUtils.isEmpty(queryDto)) {
			queryDto = Dtos.newDto();
		}
		Query query = new Query();
		QueryResults outResults = null;
		jqlText = StringUtils.replace(jqlText, IMSCons.IMSLIST_KEY, clazz.getName());
		try {
			query.parse(jqlText);
			query.setVariables(queryDto);
			outResults = query.execute(targetList);
		} catch (QueryParseException e) {
			e.printStackTrace();
		} catch (QueryExecutionException e) {
			e.printStackTrace();
		}
		jqlText = jqlText.replaceAll(" ", "");
		jqlText = StringUtils.upperCase(jqlText);
		if (StringUtils.indexOf(jqlText, "SELECT*FROM") != -1) {
			// 查询的是整个对象，则直接返回
			return (T) outResults.getResults();
		} else {
			// 查询的是部分字段，则josql封装到了list，需要再通过反射进行二次包装后再返回
			List<SelectItemExpression> columnItemList = query.getColumns();
			List<String> columnList = Lists.newArrayList();
			for (SelectItemExpression columnItem : columnItemList) {
				String columnName = StringUtils.substringBefore(columnItem.getExpression().toString(), "[detail");
				columnList.add(columnName);
			}
			List<Object> outList = Lists.newArrayList();
			List<List<Object>> resultList = outResults.getResults();
			for (List<Object> rowList : resultList) {
				Object object = IMSReflector.newInstance(clazz.getName());
				for (int j = 0; j < columnList.size(); j++) {
					IMSReflector.setFieldValue(object, columnList.get(j), rowList.get(j));
				}
				outList.add(object);
			}
			return (T) outList;
		}
	}

	/**
	 * 集合查询
	 * 
	 * @param targetList
	 *            目标集合
	 * @param clazz
	 *            集合存储对象类元
	 * @param jqlText
	 *            JOSQL脚本
	 * @param queryDto
	 *            查询条件
	 * @return 结果集合 集合内对象类型为Dto
	 */
	@SuppressWarnings("rawtypes")
	public static List<Dto> selectDto(List<?> targetList, Class clazz, String jqlText, Dto queryDto) {
		List<Dto> outList = Lists.newArrayList();
		List<Object> list = select(targetList, clazz, jqlText, queryDto);
		for (Object object : list) {
			Dto dto = Dtos.newDto();
			IMSUtils.copyProperties(object, dto);
			outList.add(dto);
		}
		return outList;
	}

	/**
	 * 除去集合中值相等的那些重复记录
	 * 
	 * <p>提示：一个集合中对象值相等的重复对象和对象相等的重复对象是两回事。
	 * 
	 * @param targetList
	 *            输入集合
	 * @param clazz
	 *            集合存储对象类元
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T distinct(List<?> targetList, Class clazz) {
		List<String> listProperties = IMSReflector.getAttributes(clazz);
		String jql = "SELECT DISTINCT " + IMSFormater.toString(listProperties) + " FROM :AOSList";
		List<Object> outList = select(targetList, clazz, jql, null);
		return (T) outList;
	}
}
