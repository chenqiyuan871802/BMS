package com.beauty.wechat.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.mapper.BagRecordMapper;
import com.beauty.common.mapper.BeautyConfigMapper;
import com.beauty.common.mapper.BeautyRecordMapper;
import com.beauty.common.mapper.BusinessOrderMapper;
import com.beauty.common.mapper.CashRecordMapper;
import com.beauty.common.mapper.CustomUserMapper;
import com.beauty.common.mapper.NurseBagMapper;
import com.beauty.common.mapper.NurseProjectMapper;
import com.beauty.common.mapper.OrderDepositMapper;
import com.beauty.common.mapper.OrderManageMapper;
import com.beauty.common.mapper.OrderPayMapper;
import com.beauty.common.mapper.ProjectRecordMapper;
import com.beauty.common.mapper.ShopMapper;
import com.beauty.common.po.BagRecordPO;
import com.beauty.common.po.BeautyConfigPO;
import com.beauty.common.po.BeautyRecordPO;
import com.beauty.common.po.BusinessOrderPO;
import com.beauty.common.po.CashRecordPO;
import com.beauty.common.po.CouponActivePO;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.NurseBagPO;
import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.OrderDepositPO;
import com.beauty.common.po.OrderPayPO;
import com.beauty.common.po.ProjectRecordPO;
import com.beauty.common.po.ShopPO;
import com.beauty.common.service.CustomUserService;
import com.beauty.common.utils.IdUtil;
import com.beauty.pay.util.PayUtil;
import com.beauty.wechat.util.WechatCxt;
import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;

/**
 * 
 * 类名:com.beauty.wechat.service.WechatOrderService 描述: 微信订单处理 编写者:陈骑元
 * 创建时间:2017年6月4日 上午10:26:42 修改说明:
 */
@Service
public class WechatOrderService {

	@Autowired
	private NurseProjectMapper nurseProjectMapper;
	@Autowired
	private NurseBagMapper nurseBagMapper;
	@Autowired
	private OrderPayMapper orderPayMapper;

	@Autowired
	private OrderDepositMapper orderDepositMapper;
	@Autowired
	private BusinessOrderMapper businessOrderMapper;

	@Autowired
	private OrderManageMapper orderManageMapper;
	@Autowired
	private CashRecordMapper cashRecordMapper;
	@Autowired
	private BeautyConfigMapper beautyConfigMapper;
	@Autowired
	private BeautyRecordMapper beautyRecordMapper;
	@Autowired
	private BagRecordMapper bagRecordMapper;
	@Autowired
	private ProjectRecordMapper projectRecordMapper;
	@Autowired
	private ShopMapper shopMapper;
	/**
	 * 消费者业务逻辑处理类
	 */
	@Autowired
	private CustomUserMapper customUserMapper;
	
	 /**
	  * 
	  * 简要说明：查询预约时间分组统计
	  * 编写者：陈骑元
	  * 创建时间：2017年6月29日 下午10:29:18
	  * @param 说明
	  * @return 说明
	  */
	 public  List<Dto> queryGroupCountTime(Dto pDto){
		 
		  return orderManageMapper.queryGroupCountTime(pDto);
	 }
	
	 /**
	  * 
	  * 简要说明：检查当前时间是否能预约
	  * 编写者：陈骑元
	  * 创建时间：2017年6月29日 下午10:32:25
	  * @param 说明 返回ture 可以预约，false不可以预约
	  * @return 说明
	  */
	 public String  checkSubscribeTime(String startTime,List<Dto> dataList,int maxCount){
		if(IMSUtils.isNotEmpty(dataList)){
			String endTime=IMSUtils.addTime(startTime, 120);
			int maxTime=Integer.parseInt(endTime.replace(":", ""));
			int minTime=Integer.parseInt(startTime.replace(":", ""));
			int totalCount=0;
			for(int i=0;i<dataList.size();i++){
				Dto dataDto=dataList.get(i);
				int countTime=dataDto.getInteger("count_time");
				int count=dataDto.getInteger("count");
			    if(countTime>=minTime&&countTime<=maxTime){
			    	totalCount+=count;
			    }
			}
			if(totalCount>=maxCount){
				return "2";  //约满2
			}
		}
		return "1"; //可预约1
	 }
	/**
	 * 
	 * 简要说明：查询可预约的时间
	 * 编写者：陈骑元
	 * 创建时间：2017年6月27日 下午10:54:59
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> queryShopSubscibeTime(String shop_id,String subscribe_date){
		String current_date=IMSUtils.getDateStr("YYYYMd");
		String sub_date=subscribe_date.replaceAll("-", "");
		int currTimeValue=0;
		if(current_date.equals(sub_date)){
			String currTime=IMSUtils.getDateStr("HH:mm");
			currTimeValue=Integer.parseInt(currTime.replace(":", ""));
		}
		Dto pDto=Dtos.newDto();
		pDto.put("subscribe_date", subscribe_date);
		pDto.put("shop_id", shop_id);
		List<Dto> countList=queryGroupCountTime(pDto);
		List<Dto> listDto=Lists.newArrayList();
		ShopPO shopPO=shopMapper.selectByKey(shop_id);
		String startTime=shopPO.getBegin_time();
		String endTime=shopPO.getEnd_time();
		int maxTime=Integer.parseInt(endTime.replace(":", ""));
		int minTime=Integer.parseInt(startTime.replace(":", ""));
		String subscribeCountStr=IMSCxt.getParamValue(BeautyCons.EVERY_TWO_LIMIT);
		int maxCount=Integer.parseInt(subscribeCountStr);
		int currValue=0;
		int i=0;
		while(currValue<maxTime){
			
			Dto dataDto=Dtos.newDto();
			String showtime=IMSUtils.addTime(startTime, 30*i);
			currValue=Integer.parseInt(showtime.replace(":", ""));
			if(minTime<currTimeValue){
				if(maxTime>currTimeValue){
					if(currValue>maxTime){
						String status=checkSubscribeTime(endTime,countList,maxCount);
						dataDto.put("showtime", endTime);
						dataDto.put("status", status);
						
					}else{
						dataDto.put("showtime", showtime);
						if(currValue>=currTimeValue){
							String  status=checkSubscribeTime(showtime,countList,maxCount);
							dataDto.put("status", status);
						}else{
							dataDto.put("status", "3");

						}
					}
				}else{
					dataDto.put("showtime", showtime);
					dataDto.put("status", "3");
				
				}
				
			}else{
				
				if(currValue>maxTime){
					String status=checkSubscribeTime( endTime,countList,maxCount);
					dataDto.put("showtime", endTime);
					dataDto.put("status", status);
				}else{
					String status=checkSubscribeTime( showtime,countList,maxCount);
					dataDto.put("showtime", showtime);
					dataDto.put("status", status);
				}
				
			}
			listDto.add(dataDto);
			i++;
		}
		return listDto;
	}

	/**
	 * 
	 * 简要说明： 保存app预约订单 编写者：陈骑元 创建时间：2017年6月3日 下午9:33:10
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveAppSubscribeOrder(Dto inDto, CustomUserPO customUserPO) {
		
		Dto outDto = Dtos.newDto();
		String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
		String back_url=request_url+"/wechat/order/goMyOrder.jhtml?index=1";
		String project_id = inDto.getString("project_id");
		NurseProjectPO nurseProjectPO = nurseProjectMapper.selectByKey(project_id);
		String pay_id = IMSId.appId();
		String outTradeNo = BeautyCons.PAY_TYPE_DEPOSIT + pay_id;
		String depositStr = IMSCxt.getParamValue(BeautyCons.SUBSCRIBE_DEPOSIT);
		Double deposit = Double.parseDouble(depositStr); // 等到定金
		String order_content = "预约护理项目(" + nurseProjectPO.getProject_name() + ")定金";
		String openid = customUserPO.getOpenid();
		Map<String, String> resultMap = PayUtil.appPayOrder(outTradeNo, deposit, order_content, openid, back_url);
		String payStatus = resultMap.get("status");
		if ("500".equals(payStatus)) { // 定金预约申请支付失败
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("预约支付失败：请确认是否关注了商家服务号");
			return outDto;
		}
		String shop_id = inDto.getString("shop_id");
		String custom_user_id = customUserPO.getCustom_user_id();
		String order_id = IdUtil.createOrderId();
		// 保存支付记录信息
		OrderPayPO orderPayPO = new OrderPayPO();
		orderPayPO.setPay_id(pay_id);
		orderPayPO.setOrder_id(order_id);
		orderPayPO.setBuy_account(openid);
		orderPayPO.setPay_code(outTradeNo);
		orderPayPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
		orderPayPO.setPay_status(BeautyCons.PAY_STATUS_UNPAY);
		orderPayPO.setCreate_time(IMSUtils.getDateTime());
		orderPayPO.setPay_money(deposit);
		orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_PAY);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_NO);
		orderPayMapper.insert(orderPayPO);
		// 保存定金信息
		OrderDepositPO depositPO = new OrderDepositPO(); // 定金支付信息
		depositPO.setDeposit_id(pay_id);
		depositPO.setDeposit_money(deposit);
		depositPO.setOrder_id(order_id);
		depositPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
		depositPO.setPay_account(openid);
		depositPO.setCustom_user_id(custom_user_id);
		depositPO.setDeposit_status(BeautyCons.PAY_STATUS_UNPAY);
		orderDepositMapper.insert(depositPO);
		// 保存订单信息
		BusinessOrderPO orderPO = new BusinessOrderPO();
		orderPO.setOrder_id(order_id);
		orderPO.setProject_id(project_id);
		orderPO.setCustom_user_id(custom_user_id);
		orderPO.setOrder_content(nurseProjectPO.getProject_name());
		orderPO.setOrder_money(nurseProjectPO.getRmb_price());
		orderPO.setShop_id(shop_id);
		orderPO.setSubscribe_time(inDto.getTimestamp("subscribe_time")); //暂时的
		orderPO.setCreate_time(IMSUtils.getDateTime());
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_DRAFT);
		orderPO.setOrder_source(BeautyCons.ORDER_SOURCE_SYSTEM);
		orderPO.setOrder_type(BeautyCons.ORDER_TYPE_PROJECT);
		orderPO.setBuy_num(1);
		Dto scDto = Dtos.newDto();
		scDto.put("shop_id", shop_id);
		scDto.put("custom_user_id", custom_user_id);
		Dto shopCustomDto = orderManageMapper.queryShopCustom(scDto);
		scDto.put("recent_time", IMSUtils.getDateTime());
		if (IMSUtils.isEmpty(shopCustomDto)) {
			scDto.put("create_time", IMSUtils.getDateTime());
			orderManageMapper.saveShopCustom(scDto);
		} else {
			orderManageMapper.updateShopCustom(scDto);
		}
		int row = businessOrderMapper.insert(orderPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.put("token_id", resultMap.get("token_id"));
			outDto.setAppMsg("操作完成,请进行支付。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作完成，预约申请失败，预约订单无法保存成功。");
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明： 保存app预约订单 编写者：陈骑元 创建时间：2017年6月3日 下午9:33:10
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveBuyBeautyOrder(Dto inDto, CustomUserPO customUserPO) {
		String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
		String back_url=request_url+"/wechat/home/goMyBeauty.jhtml";
		Dto outDto = Dtos.newDto();
		String config_id = inDto.getString("config_id");
		String shop_id=inDto.getString("shop_id");
		BeautyConfigPO config = beautyConfigMapper.selectByKey(config_id);
		String pay_id = IMSId.appId();
		String outTradeNo = BeautyCons.PAY_TYPE_BEAUTY + pay_id;
		String exchange_beauty_str = IMSCxt.getParamValue(BeautyCons.EXCHANGE_BEAUTY_KEY);
		int buy_num = config.getBuy_num();
		int give_num = config.getGive_num();
		BigDecimal b1 = new BigDecimal(Double.toString(buy_num));
		BigDecimal b2 = new BigDecimal(exchange_beauty_str);
		double total_price = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if(total_price==0){
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("颜值购买失败：价格不能为 零");
			return outDto;
		}
		String order_content = "购买颜值" + buy_num + "个(赠送" + give_num + "个)";
		String openid = customUserPO.getOpenid();
		Map<String, String> resultMap = PayUtil.appPayOrder(outTradeNo, total_price, order_content, openid, back_url);
		String payStatus = resultMap.get("status");
		if ("500".equals(payStatus)) { // 定金预约申请支付失败
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("颜值购买失败：发起申请支付请求失败");
			return outDto;
		}
		String custom_user_id = customUserPO.getCustom_user_id();
		String order_id = IdUtil.createOrderId();
		// 保存支付记录信息
		OrderPayPO orderPayPO = new OrderPayPO();
		
		orderPayPO.setPay_id(pay_id);
		orderPayPO.setOrder_id(order_id);
		orderPayPO.setBuy_account(openid);
		orderPayPO.setPay_code(outTradeNo);
		orderPayPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
		orderPayPO.setPay_status(BeautyCons.PAY_STATUS_UNPAY);
		orderPayPO.setCreate_time(IMSUtils.getDateTime());
		orderPayPO.setPay_money(total_price);
		orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_PAY);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_NO);
		orderPayPO.setBuy_account(openid);
		orderPayMapper.insert(orderPayPO);

		// 保存订单信息
		BusinessOrderPO orderPO = new BusinessOrderPO();
		orderPO.setOrder_id(order_id);
		orderPO.setProject_id(config_id);
		orderPO.setCustom_user_id(custom_user_id);
		orderPO.setOrder_content(order_content);
		orderPO.setOrder_money(total_price);
		orderPO.setCreate_time(IMSUtils.getDateTime());
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_DRAFT);
		orderPO.setOrder_source(BeautyCons.ORDER_SOURCE_SYSTEM);
		orderPO.setOrder_type(BeautyCons.ORDER_TYPE_BEAUTY);
		orderPO.setBuy_num(buy_num);
		orderPO.setShop_id(shop_id);
		int row = businessOrderMapper.insert(orderPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.put("token_id", resultMap.get("token_id"));
			outDto.setAppMsg("操作完成,请进行支付。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作完成，购买颜值失败，颜值订单无法保存成功。");
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明：更新预约订单信息 编写者：陈骑元 创建时间：2017年6月4日 下午2:01:37
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public int updateAppSubscribeOrder(String pay_id, String pay_status, String transaction_id,
			String out_transaction_id, String error_message) {
		int row = 0;
		OrderDepositPO depositPO = orderDepositMapper.selectByKey(pay_id);
		OrderPayPO orderPayPO = new OrderPayPO();
		orderPayPO.setPay_id(pay_id);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES); // 已经收到回执
		orderPayPO.setTransaction_id(transaction_id);
		orderPayPO.setOut_transaction_id(out_transaction_id);
		orderPayPO.setNodify_time(IMSUtils.getDateTime()); // 通知时间
       
		if (BeautyCons.PAY_STATUS_YES.equals(pay_status)) {
			String order_id=depositPO.getOrder_id();
			BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
			//发送微信预约通知模板
			String subscribe_time=IMSUtils.date2String(orderPO.getSubscribe_time(), IMSCons.DATETIMEMIN	);
			WechatCxt.sendSubscribeOrderMessage(depositPO.getPay_account(), order_id,orderPO.getOrder_content(), subscribe_time);
			orderPayPO.setPay_time(IMSUtils.getDateTime());
			depositPO.setPay_time(IMSUtils.getDateTime());
			// 增加消耗记录
			CashRecordPO cashRecordPO = new CashRecordPO();
			cashRecordPO.setCustom_user_id(depositPO.getCustom_user_id());
			cashRecordPO.setOrder_id(depositPO.getOrder_id());
			cashRecordPO.setMoney(depositPO.getDeposit_money());
			cashRecordPO.setPay_way(depositPO.getPay_way());
			cashRecordPO.setRecord_id(IMSId.appId());
			cashRecordPO.setPay_time(IMSUtils.getDateTime());
			cashRecordPO.setCash_type(BeautyCons.PAY_RECORD_TYPE_PAY); // 收入
			cashRecordPO.setRecord_type(BeautyCons.CASH_RECORD_TYPE_SUBSCRIBE);
			cashRecordMapper.insert(cashRecordPO);
		
			orderPO.setOrder_status(BeautyCons.ORDER_STATUS_SUBSCRIBE);
			businessOrderMapper.updateByKey(orderPO);
			
			
			
		}
		orderPayPO.setPay_status(pay_status);
		orderPayPO.setError_message(error_message);
		depositPO.setDeposit_status(pay_status);

		orderDepositMapper.updateByKey(depositPO); // 更新定金信息
	    row = orderPayMapper.updateByKey(orderPayPO);// 更新订单支付信息
       
		return row;
	}

	/**
     * 
     * 简要说明：更新美丽币订单
     * 编写者：陈骑元
     * 创建时间：2017年6月4日 下午2:01:37
     * @param 说明
     * @return 说明
     */
    @Transactional
    public int  updateBuyBeautyOrder(String pay_id,String pay_status,String transaction_id,String out_transaction_id,String error_message ){
    	int row=0;
    	
    	OrderPayPO orderPayPO=orderPayMapper.selectByKey(pay_id);
    	orderPayPO.setPay_id(pay_id);
    	orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES); //已经收到回执
    	orderPayPO.setTransaction_id(transaction_id);
    	orderPayPO.setOut_transaction_id(out_transaction_id);
    	orderPayPO.setNodify_time(IMSUtils.getDateTime());  //通知时间
    	
    	if(BeautyCons.PAY_STATUS_YES.equals(pay_status)){
    		//更新订单信息
    		String order_id=orderPayPO.getOrder_id();
    		BusinessOrderPO	 orderPO=businessOrderMapper.selectByKey(order_id);
    		double order_money=orderPO.getOrder_money();
    		String custom_user_id=orderPO.getCustom_user_id();
    		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_PAY);
    		orderPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
    		orderPO.setPay_time(IMSUtils.getDateTime());
    		orderPO.setPay_money(order_money);
    		orderPO.setCash_income(order_money); //现金收入
    		orderPO.setFinish_time(IMSUtils.getDateTime());
    		businessOrderMapper.updateByKey(orderPO);
    		String config_id=orderPO.getProject_id(); //获取基础配置
    		//增加颜值记录
    		BeautyConfigPO config=beautyConfigMapper.selectByKey(config_id); //跟新颜值数量
    		int totalNum=config.getBuy_num()+config.getGive_num();
			BeautyRecordPO beautyRecordPO=new BeautyRecordPO();
			beautyRecordPO.setRecord_id(IMSId.appId());
			beautyRecordPO.setCustom_user_id(custom_user_id);
			beautyRecordPO.setOrder_id(order_id);
			beautyRecordPO.setBeauty_num(config.getBuy_num()); //颜值个数
			beautyRecordPO.setGive_num(config.getGive_num());
			beautyRecordPO.setRecord_type(BeautyCons.BEAUTY_RECORD_TYPE_GM);
			beautyRecordPO.setVaild_status(BeautyCons.VAILD_STATUS_YES);
			beautyRecordPO.setPay_time(IMSUtils.getDateTime());
			beautyRecordPO.setTotal_money(order_money);
			beautyRecordPO.setRemain_num(totalNum);
			BigDecimal b1 = new BigDecimal(order_money);
			BigDecimal b2 = new BigDecimal(Double.toString(totalNum));
			double singe_price = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
			beautyRecordPO.setSinge_price(singe_price);
			beautyRecordMapper.insert(beautyRecordPO);
    		//增加消耗记录
    		CashRecordPO cashRecordPO =new  CashRecordPO();
    		cashRecordPO.setCustom_user_id(custom_user_id);
    		cashRecordPO.setBeauty_num(totalNum);
    		cashRecordPO.setOrder_id(order_id);
    		cashRecordPO.setMoney(order_money);
    		cashRecordPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
    		cashRecordPO.setRecord_id(IMSId.appId());
    		cashRecordPO.setPay_time(IMSUtils.getDateTime());
    		cashRecordPO.setCash_type(BeautyCons.PAY_RECORD_TYPE_PAY); //收入
    		cashRecordPO.setRecord_type(BeautyCons.CASH_RECORD_TYPE_BEAUTY);
    		cashRecordMapper.insert(cashRecordPO);
    		//更新个人颜值总量
    	    CustomUserPO customUserPO=customUserMapper.selectByKey(custom_user_id);
    	    int beautyNum= customUserPO.getBeauty_num()+totalNum;
    	    customUserPO.setBeauty_num(beautyNum);
    	    customUserMapper.updateByKey(customUserPO);
    		orderPayPO.setPay_time(IMSUtils.getDateTime());
    		
    	}
    	orderPayPO.setPay_status(pay_status);
    	orderPayPO.setError_message(error_message);
    	row=orderPayMapper.updateByKey(orderPayPO);//更新订单支付信息
    	return row;
    }
    /**
     * 
     * 简要说明：取消订单
     * 编写者：陈骑元
     * 创建时间：2017年6月11日 下午3:18:15
     * @param 说明
     * @return 说明
     */
    @Transactional
    public Dto cancelOrder(String order_id){
    	Dto outDto=Dtos.newDto();
    	BusinessOrderPO orderPO=businessOrderMapper.selectByKey(order_id);
    	String order_status=orderPO.getOrder_status();
    	if(!BeautyCons.ORDER_STATUS_SUBSCRIBE.equals(order_status)){
    		outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作完成，该订单状态不是预约状态不能取消。");
			return outDto;
    	}
        String cancel_time_str=IMSCxt.getParamValue(BeautyCons.CANCEL_TIME);
        int cancel_time=Integer.parseInt(cancel_time_str);  //预约时间
        Date current_time=IMSUtils.getDateTime();
        int minute=IMSUtils.minutesBetween(current_time, orderPO.getSubscribe_time());
        if(minute<cancel_time){
        	outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作完成，订单不能取消，超过了系统规定预约时间前"+cancel_time+"分钟取消");
			return outDto;
        }
        //定金退款
        Dto pDto = Dtos.newDto();
		pDto.put("order_id", order_id);
		OrderDepositPO depositPO = orderDepositMapper.selectOne(pDto);
		if (IMSUtils.isNotEmpty(depositPO)) { // 存在定金退款
			String deposit_status = depositPO.getDeposit_status();
			if (BeautyCons.PAY_STATUS_YES.equals(deposit_status)) { // 支付成功的进行退款处理
				String pay_id = IMSId.appId();
				String out_refund_no = BeautyCons.PAY_TYPE_REFUND + pay_id;
				String out_trade_no = BeautyCons.PAY_TYPE_DEPOSIT + depositPO.getDeposit_id();
				Double refund_money = depositPO.getDeposit_money();
				Map<String, String> resultMap = PayUtil.appRefundOrder(out_trade_no, refund_money, out_refund_no);
				String payStatus = resultMap.get("status");
				if ("500".equals(payStatus)) { // 定金预约退款失败
					outDto.setAppCode(IMSCons.ERROR);
					outDto.setAppMsg("取消订单失败：预约定金退款没有成功");
					return outDto;
				}
				String refund_id = resultMap.get("refund_id");
				String out_transaction_id = resultMap.get("out_transaction_id");
				// 增加消耗记录
				CashRecordPO cashRecordPO = new CashRecordPO();
				cashRecordPO.setCustom_user_id(depositPO.getCustom_user_id());
				cashRecordPO.setOrder_id(depositPO.getOrder_id());
				cashRecordPO.setMoney(depositPO.getDeposit_money());
				cashRecordPO.setPay_way(depositPO.getPay_way());
				cashRecordPO.setRecord_id(IMSId.appId());
				cashRecordPO.setPay_time(IMSUtils.getDateTime());
				cashRecordPO.setCash_type(BeautyCons.PAY_RECORD_TYPE_REFUND); // 支出
				cashRecordPO.setRecord_type(BeautyCons.CASH_RECORD_TYPE_SUBSCRIBE);
				cashRecordMapper.insert(cashRecordPO);
				// 保存支付记录信息
				OrderPayPO orderPayPO = new OrderPayPO();
				orderPayPO.setBuy_account(depositPO.getPay_account());
				orderPayPO.setPay_id(pay_id);
				orderPayPO.setOrder_id(order_id);
				orderPayPO.setPay_code(out_refund_no);
				orderPayPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
				orderPayPO.setPay_status(BeautyCons.REFUND_STATUS_YES);
				orderPayPO.setCreate_time(IMSUtils.getDateTime());
				orderPayPO.setPay_money(depositPO.getDeposit_money());
				orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_REFUND);
				orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES);
				orderPayPO.setRefund_id(refund_id);
				orderPayPO.setOut_transaction_id(out_transaction_id);
				orderPayPO.setPay_time(IMSUtils.getDateTime());
				orderPayMapper.insert(orderPayPO);
				// 定金信息更新
				depositPO.setBack_time(IMSUtils.getDateTime());
				depositPO.setDeposit_status(BeautyCons.REFUND_STATUS_YES);
				orderDepositMapper.updateByKey(depositPO);

			}
		}
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_UNDO);
		orderPO.setCancel_time(IMSUtils.getDateTime());
		orderPO.setCancel_type(BeautyCons.CANCEL_TYPE_CUSTOM) ;//消费者自己取消
		businessOrderMapper.updateByKey(orderPO);
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.setAppMsg("订单取消成功");
    	return outDto;
    }
    /**
     * 
     * 简要说明：保存支付订单
     * 编写者：陈骑元
     * 创建时间：2017年6月11日 下午5:05:55
     * @param 说明
     * @return 说明
     */
    @Transactional
    public Dto payOrder(String order_id	,CustomUserPO customUserPO){
    	String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
		String back_url=request_url+"/wechat/order/goMyOrder.jhtml";
    	Dto outDto=Dtos.newDto();
    	BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
		String order_status = orderPO.getOrder_status();
		if (!BeautyCons.ORDER_STATUS_SERVER.equals(order_status)) {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("该订单已经支付过，不需要重新支付");
			return outDto;
		}
		String openid=customUserPO.getOpenid();
		Double order_money = orderPO.getOrder_money();
		String pay_id = IMSId.appId();
		String order_content = "消费护理项目(" + orderPO.getOrder_content() + ")";
		String out_trade_no = BeautyCons.PAY_TYPE_ORDER + pay_id;
		Map<String, String> resultMap = PayUtil.appPayOrder(out_trade_no, order_money, order_content, openid, back_url);
        
		String payStatus = resultMap.get("status");
		if ("500".equals(payStatus)) { // 支付申请请求失败
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("支付失败：" + resultMap.get("msg"));
			return outDto;
		}
		OrderPayPO orderPayPO = new OrderPayPO();
		orderPayPO.setPay_id(pay_id);
		orderPayPO.setBuy_account(openid);
		orderPayPO.setOrder_id(order_id);
		orderPayPO.setPay_code(out_trade_no);
		orderPayPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
		orderPayPO.setPay_status(BeautyCons.PAY_STATUS_UNPAY);
		orderPayPO.setCreate_time(IMSUtils.getDateTime());
		orderPayPO.setPay_money(order_money);
		orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_PAY);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_NO);
		orderPayMapper.insert(orderPayPO);
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.put("token_id", resultMap.get("token_id"));
		outDto.setAppMsg("支付申请订单成功，请尽快进行支付");
		return outDto;
    }
    /**
     * 
     * 简要说明：保存颜值支付订单
     * 编写者：陈骑元
     * 创建时间：2017年6月11日 下午5:05:55
     * @param 说明
     * @return 说明
     */
    @Transactional
    public Dto saveBeautyPayOrder(String order_id,int beauty_num,CustomUserPO customUserPO){
    	Dto outDto=Dtos.newDto();
    	BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
    	String order_status = orderPO.getOrder_status();
    	if (!BeautyCons.ORDER_STATUS_SERVER.equals(order_status)) {
    		outDto.setAppCode(IMSCons.ERROR);
    		outDto.setAppMsg("该订单已经支付过，不需要重新支付");
    		return outDto;
    	}
    	int total_beauty=customUserPO.getBeauty_num();
    	if(beauty_num>total_beauty){
    		outDto.setAppCode(IMSCons.ERROR);
    		outDto.setAppMsg("支付失败，颜值不足");
    		return outDto;
    	}
    	String  custom_user_id=customUserPO.getCustom_user_id();
    	Dto pDto=Dtos.newDto();
        pDto.put("custom_user_id",custom_user_id );
        pDto.setOrder(" a.pay_time ASC ");//快到期的先支付
        List<BeautyRecordPO>  customBeautyList=orderManageMapper.listVaildBeauty(pDto);
        int beauty_num_temp=beauty_num;  //
        int free_num=0;
        double total_momey=0;
        for(int i=0;i<customBeautyList.size();i++){
        	BeautyRecordPO beautyRecordPO=customBeautyList.get(i);
        	int remain_num=beautyRecordPO.getRemain_num();  //剩余颜值
        	double singe_price=beautyRecordPO.getSinge_price(); //单个颜值的价值
        	if(remain_num>=beauty_num_temp){  //颜值够支付
        		if(singe_price==0){
        			free_num+=beauty_num_temp;
        		}
        		total_momey+=singe_price*beauty_num_temp;
        		int num=remain_num-beauty_num_temp;
        		beautyRecordPO.setRemain_num(num); //更新剩余颜值\
        		beautyRecordMapper.updateByKey(beautyRecordPO);
        		break;  //并中断循环；
        		
        	}else{  //不够支付
        		if(singe_price==0){
        			free_num+=remain_num;
        		}
        		beauty_num_temp=beauty_num_temp-remain_num;
        		total_momey+=singe_price*remain_num;
        		beautyRecordPO.setRemain_num(0);
        		beautyRecordMapper.updateByKey(beautyRecordPO);
        	}
        	
        	
        }
         //增加消耗记录
        BeautyRecordPO beautyRecordPO=new BeautyRecordPO();
		beautyRecordPO.setRecord_id(IMSId.appId());
		beautyRecordPO.setCustom_user_id(custom_user_id);
		beautyRecordPO.setOrder_id(order_id);
		beautyRecordPO.setBeauty_num(beauty_num); //颜值个数
		beautyRecordPO.setGive_num(0);
		beautyRecordPO.setRecord_type(BeautyCons.BEAUTY_RECORD_TYPE_XF);
		beautyRecordPO.setVaild_status(BeautyCons.VAILD_STATUS_YES);
		beautyRecordPO.setPay_time(IMSUtils.getDateTime());
		beautyRecordPO.setTotal_money(total_momey);
		beautyRecordPO.setRemain_num(0);
		beautyRecordPO.setSinge_price(0d);
		beautyRecordPO.setShop_id(orderPO.getShop_id());
		beautyRecordMapper.insert(beautyRecordPO);
		//个人颜值数量
		CustomUserPO customUserPONew=new CustomUserPO();
		int remainNum=total_beauty-beauty_num;
		customUserPONew.setCustom_user_id(custom_user_id);;
		customUserPONew.setBeauty_num(remainNum);
		customUserMapper.updateByKey(customUserPONew);//更新颜值的总量
		//发送消息模板
		String openid=customUserPO.getOpenid();
		if(IMSUtils.isNotEmpty(openid)){  //如果微信号不为空，将发送微信模板
			String money_content=beauty_num+"颜值";
			WechatCxt.sendPayOrderMessage(openid, order_id,orderPO.getOrder_content(), money_content);
		}
		
		//更新订单信息
		orderPO.setFree_num(free_num);
		orderPO.setPay_money(total_momey);
		orderPO.setPay_way(BeautyCons.PAY_WAY_BEAUTY);
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_PAY); // 已支付
		orderPO.setPay_time(IMSUtils.getDateTime());
		orderPO.setExtend_income(total_momey);
		orderPO.setExtend_beauty_num(beauty_num);  //消耗颜值
		businessOrderMapper.updateByKey(orderPO);
    	outDto.setAppCode(IMSCons.SUCCESS);
    	outDto.setAppMsg("支付成功，消费"+beauty_num+"个颜值");
    	return outDto;
    }
    /**
     * 
     * 简要说明：保存项目支付订单
     * 编写者：陈骑元
     * 创建时间：2017年6月11日 下午5:05:55
     * @param 说明
     * @return 说明
     */
    @Transactional
    public Dto saveProjectPayOrder(String order_id,String record_id,CustomUserPO customUserPO){
    	Dto outDto=Dtos.newDto();
    	BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
    	String order_status = orderPO.getOrder_status();
    	if (!BeautyCons.ORDER_STATUS_SERVER.equals(order_status)) {
    		outDto.setAppCode(IMSCons.ERROR);
    		outDto.setAppMsg("该订单已经支付过，不需要重新支付");
    		return outDto;
    	}
    	ProjectRecordPO projectRecordPO=projectRecordMapper.selectByKey(record_id);
    	String project_status=projectRecordPO.getProject_status();
    	if(BeautyCons.PROJECT_STATUS_USED.equals(project_status)){  //该项目已经使用
    		outDto.setAppCode(IMSCons.ERROR);
    		outDto.setAppMsg("该项目已使用，不能进行支付");
    		return outDto;
    	}
    	
    	//发送消息模板
		String openid=customUserPO.getOpenid();
		if(IMSUtils.isNotEmpty(openid)){  //如果微信号不为空，将发送微信模板
			String bag_content=orderPO.getOrder_content()+"项目礼包";
			WechatCxt.sendProjectPayOrderMessage(openid, order_id,orderPO.getOrder_content(), bag_content);
		}
    	projectRecordPO.setUse_time(IMSUtils.getDateTime()); //使用时间
    	projectRecordPO.setShop_id(orderPO.getShop_id()); //使用时间
    	projectRecordPO.setProject_status(BeautyCons.PROJECT_STATUS_USED);
    	projectRecordMapper.updateByKey(projectRecordPO);
        BagRecordPO bagRecordPO=bagRecordMapper.selectByKey(projectRecordPO.getBag_record_id());
        String bag_id=bagRecordPO.getBag_id();
        Dto pDto=Dtos.newDto();
        pDto.put("project_id", projectRecordPO.getProject_id());
        pDto.put("bag_id", bag_id);
        Dto bagProjectDto=orderManageMapper.queryBagProject(pDto);
    	Double money=bagProjectDto.getDouble("project_new_price");
    	orderPO.setPay_money(money);
    	orderPO.setPay_way(BeautyCons.PAY_WAY_BAG);
    	orderPO.setOrder_status(BeautyCons.ORDER_STATUS_PAY); // 已支付
    	orderPO.setPay_time(IMSUtils.getDateTime());
    	orderPO.setExtend_income(money);
    	businessOrderMapper.updateByKey(orderPO);
    	outDto.setAppCode(IMSCons.SUCCESS);
    	outDto.setAppMsg("支付成功");
    	return outDto;
    }
    /**
     * 
     * 简要说明：更新微信支付APP信息
     * 编写者：陈骑元
     * 创建时间：2017年6月11日 下午5:32:02
     * @param 说明
     * @return 说明
     */
	@Transactional
	public int updateAppPayOrder(String pay_id, String pay_status, String transaction_id, String out_transaction_id,
			 String error_message) {
		int row = 0;
		OrderPayPO orderPayPO = orderPayMapper.selectByKey(pay_id);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES); // 已经收到回执
		orderPayPO.setTransaction_id(transaction_id);
		orderPayPO.setOut_transaction_id(out_transaction_id);
		orderPayPO.setNodify_time(IMSUtils.getDateTime()); // 通知时间
		
		if (BeautyCons.PAY_STATUS_YES.equals(pay_status)) {
			orderPayPO.setPay_time(IMSUtils.getDateTime());
			String order_id = orderPayPO.getOrder_id();
			BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
			//发送微信通知模板
			orderPO.setPay_money(orderPayPO.getPay_money());
			 CustomUserPO customUserPO=customUserMapper.selectByKey(orderPO.getCustom_user_id());
			 String wechat_openid=customUserPO.getOpenid();
			 String money_content=orderPO.getPay_money()+"元";
			 if(IMSUtils.isNotEmpty(wechat_openid)){
			    WechatCxt.sendPayOrderMessage(orderPayPO.getBuy_account(),order_id,orderPO.getOrder_content(),money_content);
			 }
			
			orderPO.setPay_way(orderPayPO.getPay_way());
			orderPO.setOrder_status(BeautyCons.ORDER_STATUS_PAY); // 已支付
			orderPO.setPay_time(IMSUtils.getDateTime());
			orderPO.setCash_income(orderPayPO.getPay_money());
			orderPO.setExtend_income(orderPO.getOrder_money());
			businessOrderMapper.updateByKey(orderPO);
			// 增加消耗记录
			CashRecordPO cashRecordPO = new CashRecordPO();
			cashRecordPO.setCustom_user_id(orderPO.getCustom_user_id());
			cashRecordPO.setOrder_id(orderPayPO.getOrder_id());
			cashRecordPO.setMoney(orderPayPO.getPay_money());
			cashRecordPO.setPay_way(orderPayPO.getPay_way());
			cashRecordPO.setRecord_id(IMSId.appId());
			cashRecordPO.setPay_time(IMSUtils.getDateTime());
			cashRecordPO.setCash_type(BeautyCons.PAY_RECORD_TYPE_PAY); // 收入
			cashRecordPO.setRecord_type(BeautyCons.CASH_RECORD_TYPE_EXPENSE);
			cashRecordMapper.insert(cashRecordPO);
		}
		orderPayPO.setPay_status(pay_status);
		orderPayPO.setError_message(error_message);
		row = orderPayMapper.updateByKey(orderPayPO);// 更新订单支付信息

		return row;
	}

	public Dto checkRobBag(String bag_id, CustomUserPO customUserPO){
		Dto outDto = Dtos.newDto();
		Dto countDto=Dtos.newDto();
		countDto.put("custom_user_id", customUserPO.getCustom_user_id());
		countDto.put("bag_id", bag_id);
		countDto.put("record_type", BeautyCons.BAG_RECORD_TYPE_GM);
		NurseBagPO nurseBagPO=nurseBagMapper.selectByKey(bag_id);
		int buy_count=nurseBagPO.getBuy_count();
		int count=bagRecordMapper.rows(countDto);
		if(count>=buy_count){
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("该礼包已抢到最大次数！");
		}else{
			outDto.setAppCode(IMSCons.SUCCESS);
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明：保存礼包支付订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月18日 下午1:18:19
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveBuyBagOrder(Dto inDto, CustomUserPO customUserPO) {
		Dto outDto = Dtos.newDto();
		String bag_id = inDto.getString("bag_id");
		Dto countDto=Dtos.newDto();
		countDto.put("custom_user_id", customUserPO.getCustom_user_id());
		countDto.put("bag_id", bag_id);
		countDto.put("record_type", BeautyCons.BAG_RECORD_TYPE_GM);
		NurseBagPO nurseBagPO = nurseBagMapper.selectByKey(bag_id);
		int buy_count=nurseBagPO.getBuy_count();
		int count=bagRecordMapper.rows(countDto);
		if(count>=buy_count){
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("该礼包已抢到最大次数！");
			return outDto;
		}
	
		
		int buy_num=inDto.getInteger("buy_num");
		Double order_money=inDto.getDouble("order_money");
		
		int remain_num=nurseBagPO.getRemain_num();
		if(buy_num>remain_num){
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("购买礼包失败：礼包余量不足");
			return outDto;
		}
		String pay_id = IMSId.appId();
		String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
		String back_url=request_url+"/wechat/order/goMyOrder.jhtml?index=2";
		
		
		String outTradeNo = BeautyCons.PAY_TYPE_BAG + pay_id;
		String order_content = "购买"+buy_num+"份"+nurseBagPO.getBag_name();
		String openid = customUserPO.getOpenid();
		Map<String, String> resultMap = PayUtil.appPayOrder(outTradeNo, order_money, order_content, openid, back_url);
		String payStatus = resultMap.get("status");
		if ("500".equals(payStatus)) { // 购买礼包失败
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("购买礼包失败：发起申请支付请求失败");
			return outDto;
		}
		String custom_user_id = customUserPO.getCustom_user_id();
		String order_id = IdUtil.createOrderId();
		// 保存支付记录信息
		OrderPayPO orderPayPO = new OrderPayPO();
		
		orderPayPO.setPay_id(pay_id);
		orderPayPO.setOrder_id(order_id);
		orderPayPO.setBuy_account(openid);
		orderPayPO.setPay_code(outTradeNo);
		orderPayPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
		orderPayPO.setPay_status(BeautyCons.PAY_STATUS_UNPAY);
		orderPayPO.setCreate_time(IMSUtils.getDateTime());
		orderPayPO.setPay_money(order_money);
		orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_PAY);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_NO);
		orderPayPO.setBuy_account(openid);
		orderPayMapper.insert(orderPayPO);

		// 保存订单信息
		BusinessOrderPO orderPO = new BusinessOrderPO();
		orderPO.setOrder_id(order_id);
		orderPO.setProject_id(bag_id);
		orderPO.setCustom_user_id(custom_user_id);
		orderPO.setOrder_content(order_content);
		orderPO.setOrder_money(order_money);
		orderPO.setCreate_time(IMSUtils.getDateTime());
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_DRAFT);
		orderPO.setOrder_source(BeautyCons.ORDER_SOURCE_SYSTEM);
		orderPO.setOrder_type(BeautyCons.ORDER_TYPE_BAG);
		orderPO.setBuy_num(buy_num);
		int row = businessOrderMapper.insert(orderPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.put("token_id", resultMap.get("token_id"));
			outDto.setAppMsg("操作完成,请进行支付。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作完成，购买礼包失败，礼包订单无法保存成功。");
		}
		return outDto;
	}
	
	/**
     * 
     * 简要说明：更新购买礼包订单信息
     * 编写者：陈骑元
     * 创建时间：2017年6月4日 下午2:01:37
     * @param 说明
     * @return 说明
     */
    @Transactional
    public int  updateBuyBagOrder(String pay_id,String pay_status,String transaction_id,String out_transaction_id,String error_message ){
    	int row=0;
    	
    	OrderPayPO orderPayPO=orderPayMapper.selectByKey(pay_id);
    	orderPayPO.setPay_id(pay_id);
    	orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES); //已经收到回执
    	orderPayPO.setTransaction_id(transaction_id);
    	orderPayPO.setOut_transaction_id(out_transaction_id);
    	orderPayPO.setNodify_time(IMSUtils.getDateTime());  //通知时间
    	
    	if(BeautyCons.PAY_STATUS_YES.equals(pay_status)){
    		//更新订单信息
    		String order_id=orderPayPO.getOrder_id();
    		BusinessOrderPO	 orderPO=businessOrderMapper.selectByKey(order_id);
    		double order_money=orderPO.getOrder_money();
    		String custom_user_id=orderPO.getCustom_user_id();
    		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_PAY);
    		orderPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
    		orderPO.setPay_time(IMSUtils.getDateTime());
    		orderPO.setPay_money(order_money);
    		orderPO.setCash_income(order_money); //现金收入
    		orderPO.setFinish_time(IMSUtils.getDateTime());
    		businessOrderMapper.updateByKey(orderPO);
    		String bag_id=orderPO.getProject_id(); //获取基础配置
    	    int buy_num=orderPO.getBuy_num();
    		//增加礼包记录
    		NurseBagPO nurseBagPO=nurseBagMapper.selectByKey(bag_id); //跟新颜值数量
    		String record_id=pay_id;
    	    BagRecordPO bagRecordPO=new BagRecordPO();
		    bagRecordPO.setRecord_id(record_id);
		    bagRecordPO.setBag_id(nurseBagPO.getBag_id());
		    bagRecordPO.setValid_day(nurseBagPO.getOverdue_time());
		    bagRecordPO.setCustom_user_id(custom_user_id);
		    bagRecordPO.setBuy_num(buy_num);
		    bagRecordPO.setShare_num(buy_num-1);
		    bagRecordPO.setCreate_time(IMSUtils.getDateTime());
		    bagRecordPO.setOrder_id(order_id);
		    bagRecordPO.setRecord_type(BeautyCons.BAG_RECORD_TYPE_GM);
		    bagRecordPO.setStatus(BeautyCons.VAILD_STATUS_YES);
		    bagRecordPO.setReceive_status(BeautyCons.RECEIVE_STATUS_YES);
	    	bagRecordPO.setBag_time(IMSUtils.getDateTime());
	    	bagRecordPO.setShare_user_id(bagRecordPO.getCustom_user_id());
		    bagRecordMapper.insert(bagRecordPO);
		    int remain_num=nurseBagPO.getRemain_num()-buy_num;
		    nurseBagPO.setRemain_num(remain_num);
		    nurseBagMapper.updateByKey(nurseBagPO);
    		//增加消耗记录
    		CashRecordPO cashRecordPO =new  CashRecordPO();
    		cashRecordPO.setCustom_user_id(custom_user_id);
    		cashRecordPO.setOrder_id(order_id);
    		cashRecordPO.setMoney(order_money);
    		cashRecordPO.setPay_way(BeautyCons.PAY_WAY_WECHAT);
    		cashRecordPO.setRecord_id(IMSId.appId());
    		cashRecordPO.setPay_time(IMSUtils.getDateTime());
    		cashRecordPO.setCash_type(BeautyCons.PAY_RECORD_TYPE_PAY); //收入
    		cashRecordPO.setRecord_type(BeautyCons.CASH_RECORD_TYPE_BAG);
    		cashRecordMapper.insert(cashRecordPO);
    		//自动领取礼包
    		CustomUserPO customUserPO=customUserMapper.selectByKey(custom_user_id);
        	int bag_num=customUserPO.getBag_num()+1;
        	customUserPO.setBag_num(bag_num);
        	customUserMapper.updateByKey(customUserPO); //更新个人礼包
        	List<Dto> bagProjectList=orderManageMapper.listBagProject(bag_id);
        	for(int i=0;i<bagProjectList.size();i++){
        		Dto bagProjectDto=bagProjectList.get(i);
        		String project_num=bagProjectDto.getString("project_num");
        		String project_id=bagProjectDto.getString("project_id");
        		int num=1;
        		if(IMSUtils.isNotEmpty(project_num)){
        			num=Integer.parseInt(project_num);
        		}
        		for(int j=0;j<num;j++){
        			ProjectRecordPO projectRecordPO=new ProjectRecordPO();
        			projectRecordPO.setRecord_id(IMSId.appId());
        			projectRecordPO.setBag_record_id(record_id);
        			projectRecordPO.setProject_id(project_id);
        			projectRecordPO.setDraw_time(IMSUtils.getDateTime());
        			projectRecordPO.setProject_status(BeautyCons.PROJECT_STATUS_NOUSE);
        			projectRecordMapper.insert(projectRecordPO);
        		}
        	}
    		
    	}
    	orderPayPO.setPay_status(pay_status);
    	orderPayPO.setError_message(error_message);
    	row=orderPayMapper.updateByKey(orderPayPO);//更新订单支付信息
    	return row;
    }


}
