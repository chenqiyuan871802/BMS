package com.beauty.common.utils;

import java.util.HashSet;
import java.util.Set;
import com.ims.common.core.id.IMSId;

public class IdUtil {
   
	/**
	 * 
	 * 简要说明：生成店铺编号  1开头 最少8位
	 * 编写者：陈骑元
	 * 创建时间：2017年4月22日 上午10:55:28
	 * @param 说明
	 * @return 说明
	 */
	public static String createShopId(){
		
		return IMSId.createId("1", "bis_shop", "shop_id", 7);
	}
	/**
	 * 
	 * 简要说明：创建消费者编号 2开头 最少8位
	 * 编写者：陈骑元 
	 * 创建时间：2017年4月22日 上午10:56:31
	 * @param 说明 
	 * @return 说明
	 */
	public static String createCustomUserId(){
		
		return IMSId.createId("2", "bis_custom_user", "custom_user_id", 7);
	}
	/**
	 * 
	 * 简要说明：创建护理项目 3开头 最少8位
	 * 编写者：陈骑元 
	 * 创建时间：2017年4月22日 上午10:56:31
	 * @param 说明 
	 * @return 说明
	 */
	public static String createNurseProjectId(){
		
		return IMSId.createId("3", "bis_nurse_project", "project_id", 7);
	}
	/**
	 * 
	 * 简要说明：礼包项目编号  4开头 最少8位
	 * 编写者：陈骑元 
	 * 创建时间：2017年4月22日 上午10:56:31
	 * @param 说明 
	 * @return 说明
	 */
	public static String createNurseBagId(){
		
		return IMSId.createId("4", "bis_nurse_bag", "bag_id", 7);
	}
	/**
	 * 
	 * 简要说明：生成美研券编号 5开头 最少8位
	 * 编写者：陈骑元
	 * 创建时间：2017年4月28日 上午12:05:01
	 * @param 说明
	 * @return 说明
	 */
	public static String createCouponActiveId(){
		
		return IMSId.createId("5", "bis_coupon_active", "active_id", 7);
	}
	/**
	 * 
	 * 简要说明：生成订单编号 8开头 最少8位
	 * 编写者：陈骑元
	 * 创建时间：2017年4月28日 上午12:05:01
	 * @param 说明
	 * @return 说明
	 */
	public static String createOrderId(){
		
		return IMSId.createDateId("bis_business_order", "order_id*1", 3);
	}
	/**
	 * 
	 * 简要说明：创建礼券编号
	 * 编写者：陈骑元
	 * 创建时间：2017年4月28日 上午12:28:29
	 * @param 说明
	 * @return 说明
	 */
	public static Set<String> createCdkey(String active_id,int num,int length){
		Set<String> dataSet=new HashSet<String>();
		String suffixStr=active_id.substring(1);//获取后缀
		long currentNum=Long.parseLong(suffixStr); //获取当前编号
		int  len=(currentNum+"").length(); //获取当前编号的长度
		if(len>length){
			throw new RuntimeException("前缀长度不能大于生成的长度");
		}
		int suffixLen=length-len; //获取后缀的长度
		int i=0;
		while(i<num){
			String cdKey=currentNum+createRadomNum(suffixLen);
			if(!dataSet.contains(cdKey)){
				dataSet.add(cdKey);
				i++;
			}
		}
		return dataSet;
	}
	/**
	 * 
	 * 简要说明：创建一定长度的随机大写字母和数字
	 * 编写者：陈骑元
	 * 创建时间：2017年4月28日 上午12:18:49
	 * @param 说明 lenght长度
	 * @return 说明
	 */
	public static String createRadomNum(int lenght){
		String str = "";
		for(int i=0;i < lenght;i++){  
			int intVal=(int)(Math.random()*26+65);
			if(intVal%2==0){
				str += (char)intVal;  
			}else{
				str += (int)(Math.random()*10);
			}
		}  
		return str;
	}
	
}

