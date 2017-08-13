package com.ims.common.core.asset;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * 类描述： 系列化工具类
 * 创建人：陈骑元
 * 创建时间：2016-6-1 上午01:26:21
 * 修改人：蓝枫 
 * 修改时间：2016-6-1 上午01:26:21
 * 修改备注： 
 * @version
 */
public class IMSSerializeUtil {
	
	static Log log = LogFactory.getLog(IMSSerializeUtil.class);
	
	/**
	 * 序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj){
		try{
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(byteOut);
			oos.writeObject(obj);
			byte[] bytes = byteOut.toByteArray();
			oos.close();
			byteOut.close();
			return bytes;
		}catch (Exception e) {
			log.error("对象序列化失败");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unSerialize(byte[] bytes){
		ByteArrayInputStream in = null;
		try{
			in = new ByteArrayInputStream(bytes);
			ObjectInputStream objIn = new ObjectInputStream(in);
			return objIn.readObject();
		}catch (Exception e) {
			log.error("反序列化失败");
			e.printStackTrace();
		}
		return null;
	}
}
