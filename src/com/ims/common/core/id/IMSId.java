package com.ims.common.core.id;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.exception.IMSException;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.support.redis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 
 * 类描述： ID工具类 创建人：陈骑元 邮箱：240823329@qq.com 创建时间：2016-6-10 上午09:01:03 修改人： 修改时间：
 * 修改备注：
 * 
 * @version 1.0
 */
public class IMSId {

	/**
	 * 返回去除连接符-的UUID
	 * 
	 * @return
	 */
	public static String uuid() {
		String uuid = uuid2();
		return uuid.replaceAll("-", "");
	}

	/**
	 * 返回原生UUID
	 * 
	 * @return
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 返回原始UUID中指定序号的一组字符串。
	 * 
	 * @param index
	 *            有效索引序号[0,1,2,3,4]。
	 * @return
	 */
	public static String uuid(int index) {
		String[] uuids = uuid2().split("-");
		return uuids[index];
	}

	/**
	 * <p>
	 * 获取规则序列号：基于Redis生成
	 * </p>
	 * 生成规则：[前缀] + 时间戳 + 递增重复循环的序列
	 * 
	 * @param idType
	 *            ID类型 用于作为Redis Key的一部分。标识ID的唯一性。
	 * @param timeFormat
	 *            时间戳的格式 缺省值：yyMMddHHmmss
	 * @param maxIncr
	 *            循环递增序列最大值 9999
	 * @return
	 */
	public static String appId(String idType, String timeFormat, String maxIncr) {
		if (IMSUtils.isEmpty(idType)) {
			throw new IMSException("idType不能为空，请分配ID类型标识。");
		}
		String id = null;
		if (IMSCxt.isLive()) { // redis在线
			Jedis jedis = JedisUtil.getJedisClient();

			String IDSET = "IDSET";
			String key = IMSCons.APP_ID_KEY + idType;
			timeFormat = IMSUtils.isEmpty(timeFormat) ? "yyMMddHHmmss" : timeFormat;
			maxIncr = IMSUtils.isEmpty(maxIncr) ? "9999" : maxIncr;
			while (true) {
				long myIncrLong = jedis.incr(key);
				if (myIncrLong > Integer.valueOf(maxIncr)) {
					jedis.del(key);
					myIncrLong = jedis.incr(key);
				}
				String myIncrStr = String.format("%" + maxIncr.length() + "d", myIncrLong).replace(" ", "0");
				String myPrefix = IMSUtils.getDateStr(timeFormat);
				id = myPrefix + myIncrStr;
				if (!jedis.sismember(IDSET, id)) {
					jedis.sadd(IDSET, id);
					break;
				}
			}
			JedisUtil.close(jedis);

		} else { // reids不在线
			id = IMSUtils.getDateStr("yyMMddHHmmssSSS");
		}
		return id;
	}

	/**
	 * 
	 * 简要说明：生成有规律的编号 编写者：陈骑元 创建时间：2017年4月16日 下午10:18:22
	 * 
	 * @param 说明
	 *            prefix 前缀标识,tableName 表名 idField 主键字段 suffixMixLen 后缀最小长度 不能小于4
	 * @return 说明
	 */
	public static String createId(String prefix, String tableName, String idField, int suffixMixLen) {
		if (IMSUtils.isEmpty(tableName)) {
			throw new IMSException("tableName不能为空，请指定表名标识。");
		}
		if (IMSUtils.isEmpty(idField)) {
			throw new IMSException("idField不能为空，请指定ID主键标识。");
		}
		
		if (suffixMixLen < 4) {
			suffixMixLen = 4;
		}
		prefix = IMSUtils.isEmpty(prefix) ? "1" : prefix;
		
		String id=null;
		String maxId=null;//当前最大的最大编号
		if (IMSCxt.isLive()) { // redis在线
			Jedis jedis = JedisUtil.getJedisClient();
			String key=IMSCons.APP_ID_KEY+tableName;
			maxId=jedis.get(key);
			if(IMSUtils.isEmpty(maxId)){
				maxId=getMaxId( tableName,  idField);
			}
			jedis.close();
		}else{
			maxId=getMaxId( tableName,  idField);
		}
		if(IMSUtils.isEmpty(maxId)){
			id=prefix+String.format("%" + (suffixMixLen) + "d",1).replace(" ", "0");
		}else{
			int pLen=prefix.length(); //前缀的长度
			String suffixStr=maxId.substring(pLen);//获取后缀
			long nextNum=Long.parseLong(suffixStr)+1;
			id=prefix+String.format("%" + suffixMixLen+ "d",nextNum).replace(" ", "0");
			
		}
		if (IMSCxt.isLive()) { // redis在线
			Jedis jedis = JedisUtil.getJedisClient();
			String key=IMSCons.APP_ID_KEY+tableName;
			jedis.set(key, id);
			jedis.close();
		}
		return id;

	}
	/**
	 * 
	 * 简要说明：创建当前日期编号
	 * 编写者：陈骑元
	 * 创建时间：2017年7月30日 下午1:32:55
	 * @param 说明
	 * @return 说明
	 */
	public static String createDateId( String tableName, String idField, int suffixMixLen) {
		if (IMSUtils.isEmpty(tableName)) {
			throw new IMSException("tableName不能为空，请指定表名标识。");
		}
		if (IMSUtils.isEmpty(idField)) {
			throw new IMSException("idField不能为空，请指定ID主键标识。");
		}
		
		if (suffixMixLen < 3) {
			suffixMixLen = 3;
		}
	   String	prefix = IMSUtils.getCurrentDate("yyyyMMdd");
		
		String id=null;
		String maxId=null;//当前最大的最大编号
		if (IMSCxt.isLive()) { // redis在线
			Jedis jedis = JedisUtil.getJedisClient();
			String key=IMSCons.APP_ID_KEY+tableName;
			maxId=jedis.get(key);
			if(IMSUtils.isEmpty(maxId)){
				maxId=getMaxId( tableName,  idField);
			}
			jedis.close();
		}else{
			maxId=getMaxId( tableName,  idField);
		}
		if(IMSUtils.isEmpty(maxId)){
			id=prefix+String.format("%" + (suffixMixLen) + "d",1).replace(" ", "0");
		}else{
			if(maxId.indexOf(prefix)>-1){  //说明是当期日期
				int pLen=prefix.length(); //前缀的长度
				String suffixStr=maxId.substring(pLen);//获取后缀
				long nextNum=Long.parseLong(suffixStr)+1;
				id=prefix+String.format("%" + suffixMixLen+ "d",nextNum).replace(" ", "0");
			}else{
				id=prefix+String.format("%" + (suffixMixLen) + "d",1).replace(" ", "0");
			}
			
		}
		if (IMSCxt.isLive()) { // redis在线
			Jedis jedis = JedisUtil.getJedisClient();
			String key=IMSCons.APP_ID_KEY+tableName;
			jedis.set(key, id);
			jedis.close();
		}
		return id;
		
	}

	/**
	 * 
	 * 简要说明：获取表中主键最大值 编写者：陈骑元 创建时间：2017年4月16日 下午10:08:00
	 * 
	 * @param 说明
	 *            tableName 表名 idField 数据库ID字段
	 * @return 说明
	 */
	public static String getMaxId(String tableName, String idField) {
		Dto pDto = Dtos.newCalcDto("MAX(" + idField + ")");
		pDto.put("tableName", tableName);
		SqlDao sqlDao = IMSCxt.getSqlDao();
		return (String) sqlDao.selectOne("System.calc", pDto);

	}

	/**
	 * *
	 * <p>
	 * 获取数值型规则序列号
	 * </p>
	 * 
	 * @param idType
	 * @return
	 */
	public static String appId(){
		return appId(IMSCons.SYSTEM);
	}
	/**
	 * *
	 * <p>
	 * 获取数值型规则序列号
	 * </p>
	 * 
	 * @param idType
	 * @return
	 */
	public static String appId(String idType) {
		return appId(idType, null, null);
	}

	/**
	 * 生成树路径ID，如：01.01.01
	 * 
	 * @param curMaxNode
	 *            本级当前最大节点ID，如果要生成本级第一个节点则传XX.XX.00(XX.XX为父节点ID)。
	 * @param maxValue
	 *            本级节点ID允许的最大值
	 * @return
	 */
	public static String treeId(String curMaxNode, int maxValue) {
		String prefix = StringUtils.substringBeforeLast(curMaxNode, ".");
		String last = StringUtils.substringAfterLast(curMaxNode, ".");
		if (IMSUtils.isEmpty(last)) {
			throw new IMSException(4);
		}
		int intLast = Integer.valueOf(last);
		if (intLast == maxValue || intLast > maxValue) {
			throw new IMSException(3);
		}
		String thisNode = String.valueOf(intLast + 1);
		thisNode = StringUtils.leftPad(thisNode, String.valueOf(maxValue).length(), "0");
		return prefix + "." + thisNode;
	}

	public static void main(String[] args) {
		System.out.println(createDateId("bis_business_order","order_id",3));
	}

}
