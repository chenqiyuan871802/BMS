package com.beauty.asyncTask;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.service.OrderManageService;
import com.beauty.wechat.service.WechatOrderService;
import com.ims.common.core.asset.IMSUtils;
/**
 * 
 * 类名:com.beauty.asyncTask.PayNodifyTask
 * 描述:支付通知任务异步处理
 * 编写者:陈骑元
 * 创建时间:2017年6月4日 下午1:21:48
 * 修改说明:
 */
@Component
public class PayNodifyTask{
	private static Logger log = Logger.getLogger(PayNodifyTask.class);
	@Autowired
	private WechatOrderService wechatOrderService;
	@Autowired
	private OrderManageService orderManageService;
	/**
	 * 
	 * 简要说明：处理APP支付通知
	 * 编写者：陈骑元
	 * 创建时间：2017年6月4日 下午2:00:18
	 * @param 说明
	 * @return 说明
	 */
	@Async
	public void handleAppNodify(Map<String,String> resultMap){
		log.info("异步处理微信支付通知开始......................");
		String outTradeNo=resultMap.get("out_trade_no");
		if(IMSUtils.isNotEmpty(outTradeNo)){
			String transaction_id=resultMap.get("transaction_id");
			String out_transaction_id=resultMap.get("out_transaction_id");
			
			String pay_type=outTradeNo.substring(0, 1);
			String pay_id=outTradeNo.substring(1);
			String pay_result=resultMap.get("pay_result");
			String pay_info=resultMap.get("pay_info");
			String pay_status=BeautyCons.PAY_STATUS_NO;
			if("0".equals(pay_result)){  //支付成功
				pay_status=BeautyCons.PAY_STATUS_YES;
			}
			if(BeautyCons.PAY_TYPE_DEPOSIT.equals(pay_type)){
				int row=wechatOrderService.updateAppSubscribeOrder(pay_id, pay_status, transaction_id,out_transaction_id, pay_info);
			    if(row>0){
			    	log.info("异步处理微信支付定金支付信息成功：订单记录编号："+pay_id);
			    }else{
			    	log.info("异步处理微信支付定金支付信息失败：订单记录编号："+pay_id);
			    }
			}else if(BeautyCons.PAY_TYPE_WECHAT.equals(pay_type)){//微信支付项目
				String openid=resultMap.get("openid");
				int row=orderManageService.updatePayOrder(pay_id, pay_status, transaction_id,out_transaction_id, openid,pay_info);
			    if(row>0){
			    	log.info("异步处理微信支付订单支付信息成功：订单记录编号："+pay_id);
			    }else{
			    	log.info("异步处理微信支付订单支付信息失败：订单记录编号："+pay_id);
			    }

			}else if(BeautyCons.PAY_TYPE_ALIPAY.equals(pay_type)){//微信支付项目
				String openid=resultMap.get("openid");
				int row=orderManageService.updatePayOrder(pay_id, pay_status, transaction_id,out_transaction_id, openid,pay_info);
			    if(row>0){
			    	log.info("异步处理支付宝支付订单支付信息成功：订单记录编号："+pay_id);
			    }else{
			    	log.info("异步处理支付宝支付订单支付信息失败：订单记录编号："+pay_id);
			    }

			}else if(BeautyCons.PAY_TYPE_BEAUTY.equals(pay_type)){
				int row=wechatOrderService.updateBuyBeautyOrder(pay_id, pay_status, transaction_id, out_transaction_id, pay_info);
			    if(row>0){
			    	log.info("异步处理购买颜值订单微信支付信息成功：订单记录编号："+pay_id);
			    }else{
			    	log.info("异步处理购买颜值订单微信支付信息失败：订单记录编号："+pay_id);
			    }
			}else if(BeautyCons.PAY_TYPE_ORDER.equals(pay_type)){
				int row=wechatOrderService.updateAppPayOrder(pay_id, pay_status, transaction_id, out_transaction_id, pay_info);
			    if(row>0){
			    	log.info("异步处理护理项目订单微信支付信息成功：订单记录编号："+pay_id);
			    }else{
			    	log.info("异步处理护理项目订单微信支付信息失败：订单记录编号："+pay_id);
			    }
			}else if(BeautyCons.PAY_TYPE_BAG.equals(pay_type)){
				int row=wechatOrderService.updateBuyBagOrder(pay_id, pay_status, transaction_id, out_transaction_id, pay_info);
			    if(row>0){
			    	log.info("异步处理购买礼包订单微信支付信息成功：订单记录编号："+pay_id);
			    }else{
			    	log.info("异步处理购买礼包订单微信支付信息失败：订单记录编号："+pay_id);
			    }
			}
				
			
		}
		log.info("异步处理微信支付通知结束......................");
	}
	
}
