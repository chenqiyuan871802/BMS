package com.beauty.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.mapper.BagRecordMapper;
import com.beauty.common.mapper.BeautyRecordMapper;
import com.beauty.common.mapper.BusinessOrderMapper;
import com.beauty.common.mapper.CashRecordMapper;
import com.beauty.common.mapper.CustomUserMapper;
import com.beauty.common.mapper.NurseBagMapper;
import com.beauty.common.mapper.OrderDepositMapper;
import com.beauty.common.mapper.OrderManageMapper;
import com.beauty.common.mapper.OrderPayMapper;
import com.beauty.common.mapper.ProjectRecordMapper;
import com.beauty.common.mapper.ShopUserMapper;
import com.beauty.common.po.BagRecordPO;
import com.beauty.common.po.BeautyRecordPO;
import com.beauty.common.po.BusinessOrderPO;
import com.beauty.common.po.CashRecordPO;
import com.beauty.common.po.CustomUserPO;
import com.beauty.common.po.NurseBagPO;
import com.beauty.common.po.OrderDepositPO;
import com.beauty.common.po.OrderPayPO;
import com.beauty.common.po.ProjectRecordPO;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.utils.IdUtil;
import com.beauty.pay.util.PayUtil;
import com.beauty.wechat.util.WechatCxt;
import com.ims.common.core.asset.IMSCodec;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.core.support.redis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 
 * 类名:com.beauty.shop.service.OrderManageService 描述:订单业务逻辑管理 编写者:陈骑元
 * 创建时间:2017年5月7日 下午11:11:37 修改说明:
 */
@Service
public class OrderManageService {

	@Autowired
	private OrderManageMapper orderManageMapper;

	@Autowired
	private ShopUserMapper shopUserMapper;
	@Autowired
	private BusinessOrderMapper businessOrderMapper;
	/*
	 * @Autowired private NurseProjectMapper nurseProjectMapper;
	 */

	/**
	 * 消费者业务逻辑处理类
	 */
	@Autowired
	private CustomUserMapper customUserMapper;
	@Autowired
	private OrderPayMapper orderPayMapper;

	@Autowired
	private OrderDepositMapper orderDepositMapper;
	@Autowired
	private CashRecordMapper cashRecordMapper;
	@Autowired
	private  BagRecordMapper bagRecordMapper;
	@Autowired
	private  ProjectRecordMapper projectRecordMapper;
	@Autowired
	private  NurseBagMapper nurseBagMapper;
	@Autowired
	private  BeautyRecordMapper beautyRecordMapper;

	/**
	 * 
	 * 简要说明：查询营业记录 编写者：陈骑元 创建时间：2017年5月9日 上午1:16:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBusinessRecordPage(Dto pDto) {

		return orderManageMapper.listBusinessRecordPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询财务总况
	 * 编写者：陈骑元
	 * 创建时间：2017年6月14日 下午8:00:15
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryFinanceTotal(Dto pDto) {
		
		return orderManageMapper.queryFinanceTotal(pDto);
	}
	/**
	 * 
	 * 简要说明：查询财务总况
	 * 编写者：陈骑元
	 * 创建时间：2017年6月14日 下午8:00:15
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listFinancePage(Dto pDto) {
		
		return orderManageMapper.listFinancePage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询财务总况
	 * 编写者：陈骑元
	 * 创建时间：2017年6月14日 下午8:00:15
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listFinance(Dto pDto) {
		
		return orderManageMapper.listFinance(pDto);
	}

	/**
	 * 
	 * 简要说明：查询所有营业记录 编写者：陈骑元 创建时间：2017年5月9日 上午1:16:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listAllRecordPage(Dto pDto) {

		return orderManageMapper.listAllRecordPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询所有营业记录 编写者：陈骑元 创建时间：2017年5月9日 上午1:16:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listNoServerRecordPage(Dto pDto) {
		
		return orderManageMapper.listNoServerRecordPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询所有营业记录 编写者：陈骑元 创建时间：2017年5月9日 上午1:16:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listAllRecord(Dto pDto) {
		
		return orderManageMapper.listAllRecord(pDto);
	}
	/**
	 * 
	 * 简要说明：查询所有营业记录 编写者：陈骑元 创建时间：2017年5月9日 上午1:16:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopRecordPage(Dto pDto) {
		
		return orderManageMapper.listShopRecordPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询所有营业记录 编写者：陈骑元 创建时间：2017年5月9日 上午1:16:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopRecord(Dto pDto) {
		
		return orderManageMapper.listShopRecord(pDto);
	}

	/**
	 * 
	 * 简要说明：查询预约中的订单 编写者：陈骑元 创建时间：2017年5月9日 上午1:16:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listSubscribeOrderPage(Dto pDto) {

		return orderManageMapper.listSubscribeOrderPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询护理订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:35:21
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listNurseOrderPage(Dto pDto) {
		
		return orderManageMapper.listNurseOrderPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询购买礼包订单
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:35:21
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBagOrderPage(Dto pDto) {
		
		return orderManageMapper.listBagOrderPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询我的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:35:21
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listMyProjectPage(Dto pDto) {
		
		return orderManageMapper.listMyProjectPage(pDto);
	}
	/**
	 * 
	 * 简要说明：查询我的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:35:21
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listProject(Dto pDto) {
		
		return orderManageMapper.listProject(pDto);
	}
	/**
	 * 
	 * 简要说明：查询我的项目
	 * 编写者：陈骑元
	 * 创建时间：2017年6月11日 上午11:35:21
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listMyProject(Dto pDto) {
		
		return orderManageMapper.listMyProject(pDto);
	}
	/**
	 * 
	 * 简要说明：我的礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年6月20日 下午10:39:46
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listMyBagDto(Dto pDto) {
		
		return orderManageMapper.listMyBag(pDto);
	}
	/**
	 * 
	 * 简要说明：查询礼包项目
	 * 编写者：陈骑元
	 * 创建时间：2017年6月20日 下午10:39:46
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBagProject(String bag_id) {
		
		return orderManageMapper.listBagProject(bag_id);
	}

	/**
	 * 
	 * 简要说明：查询订单详情 编写者：陈骑元 创建时间：2017年5月10日 上午12:41:30
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryOrderDetail(String order_id) {

		return orderManageMapper.queryOrderDetail(order_id);
	}
	/**
	 * 
	 * 简要说明：查询礼包项目信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月24日 下午6:11:56
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryBagProject(Dto pDto) {
		
		return orderManageMapper.queryBagProject(pDto);
	}
	/**
	 * 
	 * 简要说明：查询订单详情 编写者：陈骑元 创建时间：2017年5月10日 上午12:41:30
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> queryPayProject(Dto pDto) {
		
		return orderManageMapper.queryPayProject(pDto);
	}
	/**
	 * 
	 * 简要说明：员工
	 * 编写者：陈骑元
	 * 创建时间：2017年7月16日 下午11:00:40
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopUserCountPage(Dto pDto) {
		
		return orderManageMapper.listShopUserCountPage(pDto);
	}
	/**
	 * 
	 * 简要说明：员工
	 * 编写者：陈骑元
	 * 创建时间：2017年7月16日 下午11:00:40
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopUserCount(Dto pDto) {
		
		return orderManageMapper.listShopUserCount(pDto);
	}
	/**
	 * 
	 * 简要说明：员工统计汇总
	 * 编写者：陈骑元
	 * 创建时间：2017年7月16日 下午11:00:40
	 * @param 说明
	 * @return 说明
	 */
	public  Dto queryShopSum(Dto pDto) {
		
		return orderManageMapper.queryShopSum(pDto);
	}

	/**
	 * 
	 * 简要说明：修改或保存订单信息 编写者：陈骑元 创建时间：2017年5月23日 下午11:33:55
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto modifyOrder(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String account = inDto.getString("account");
		Dto userDto = Dtos.newDto("account", account);
		userDto.put("is_del", IMSCons.IS.NO);
		ShopUserPO userPO = shopUserMapper.selectOne(userDto);
		if (IMSUtils.isEmpty(userPO)) {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("经手员工账号输入错误，请重新输入。");
			return outDto;
		}
		String status = userPO.getStatus();
		if (BeautyCons.SHOP_USER_STATUS_STOP.equals(status)) {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("经手员工账号已被禁用。");
			return outDto;
		}
		/*
		 * String password = inDto.getString("password"); String decryptPassword =
		 * IMSCodec.decrypt(userPO.getPassword(), IMSCons.PASSWORD_KEY); if
		 * (password.equals(decryptPassword)) { // 判断密码是否一致
		 */			String orderId=inDto.getString("order_id");
			BusinessOrderPO orderDB= businessOrderMapper.selectByKey(orderId);
			String operateWay = inDto.getString("operateWay"); // 订单操作方式
			BusinessOrderPO orderPO = new BusinessOrderPO();
			//String order_content = "消费护理项目(" + orderDB.getOrder_content() + ")";
			orderPO.setOrder_id(orderId);
			orderPO.setOrder_remark(inDto.getString("order_remark"));
			orderPO.setServer_user_id(inDto.getString("server_user_id"));
			orderPO.setHandle_user_id(userPO.getShop_user_id());
			if ("2".equals(operateWay)) { // 操作完成
				orderPO.setOrder_status(BeautyCons.ORDER_STATUS_COMPLETE);
				orderPO.setFinish_time(IMSUtils.getDateTime());
				OrderPayPO orderPayPO = new OrderPayPO();
				orderPayPO.setPay_id(IMSId.appId());
				orderPayPO.setOrder_id(orderId);
				orderPayPO.setPay_code("");
				orderPayPO.setPay_way(BeautyCons.PAY_WAY_OTHER);
				orderPayPO.setPay_status(BeautyCons.PAY_STATUS_YES);
				orderPayPO.setCreate_time(IMSUtils.getDateTime());
				orderPayPO.setPay_money(orderDB.getOrder_money());
				orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_PAY);
				orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES);
				orderPayPO.setPay_time(IMSUtils.getDateTime());
				orderPayMapper.insert(orderPayPO);

				// 增加消耗记录
				CashRecordPO cashRecordPO = new CashRecordPO();
				cashRecordPO.setCustom_user_id(orderDB.getCustom_user_id());
				cashRecordPO.setOrder_id(orderPayPO.getOrder_id());
				cashRecordPO.setMoney(orderPayPO.getPay_money());
				cashRecordPO.setPay_way(orderPayPO.getPay_way());
				cashRecordPO.setRecord_id(IMSId.appId());
				cashRecordPO.setPay_time(IMSUtils.getDateTime());
				cashRecordPO.setCash_type(BeautyCons.PAY_RECORD_TYPE_PAY); // 收入
				cashRecordPO.setRecord_type(BeautyCons.CASH_RECORD_TYPE_EXPENSE);
				cashRecordMapper.insert(cashRecordPO);
				orderPO.setPay_money(orderPayPO.getPay_money());
				orderPO.setPay_way(BeautyCons.PAY_WAY_OTHER);
				orderPO.setPay_time(IMSUtils.getDateTime());
				orderPO.setCash_income(orderPayPO.getPay_money());
				orderPO.setExtend_income(orderPayPO.getPay_money());
				
				
			}else{
				orderPO.setModify_status("1");
			}
			businessOrderMapper.updateByKey(orderPO);
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("信息保存成功。");
			return outDto;
	/*	} else {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("经手员工密码输入不正确。");
			return outDto;
		}*/
	}

	/**
	 * 
	 * 简要说明：修改订单项目 编写者：陈骑元 创建时间：2017年5月28日 下午11:19:50
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto modifyOrderProject(Dto inDto) {
		Dto outDto = Dtos.newDto();
		BusinessOrderPO orderPO = new BusinessOrderPO();
		orderPO.setOrder_id(inDto.getString("order_id"));
		orderPO.setOrder_money(inDto.getDouble("rmb_price"));
		orderPO.setProject_id(inDto.getString("project_id"));
		orderPO.setOrder_content(inDto.getString("order_content"));
		int row = businessOrderMapper.updateByKey(orderPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("修改项目成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("修改项目失败。");
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明：订单执行开始操作，同时进行退款处理 编写者：陈骑元 创建时间：2017年5月29日 上午12:28:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto modifyStartOrder(String order_id) {
		Dto outDto = Dtos.newDto();
		Dto pDto = Dtos.newDto();
		pDto.put("order_id", order_id);
		OrderDepositPO depositPO = orderDepositMapper.selectOne(pDto);
		if (IMSUtils.isNotEmpty(depositPO)) { // 存在定金退款
			String deposit_status = depositPO.getDeposit_status();
			if (BeautyCons.PAY_STATUS_YES.equals(deposit_status)) { // 支付成功的进行退款处理
				String pay_id = IMSId.appId();
				String out_refund_no = BeautyCons.PAY_TYPE_REFUND + pay_id;
				//String out_trade_no = BeautyCons.PAY_TYPE_DEPOSIT + depositPO.getDeposit_id();
				//Double refund_money = depositPO.getDeposit_money();
				/*
				 * Map<String, String> resultMap = PayUtil.appRefundOrder(out_trade_no,
				 * refund_money, out_refund_no); String payStatus = resultMap.get("status"); if
				 * ("500".equals(payStatus)) { // 定金预约退款失败 outDto.setAppCode(IMSCons.ERROR);
				 * outDto.setAppMsg("开始服务失败：预约定金退款失败"); return outDto; } String refund_id =
				 * resultMap.get("refund_id"); String out_transaction_id =
				 * resultMap.get("out_transaction_id")
				 */;
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
				orderPayPO.setRefund_id("");
				orderPayPO.setOut_transaction_id("");
				orderPayPO.setPay_time(IMSUtils.getDateTime());
				orderPayMapper.insert(orderPayPO);
				// 定金信息更新
				depositPO.setBack_time(IMSUtils.getDateTime());
				depositPO.setDeposit_status(BeautyCons.REFUND_STATUS_YES);
				orderDepositMapper.updateByKey(depositPO);

			}
		}
		BusinessOrderPO orderPO = new BusinessOrderPO();
		orderPO.setOrder_id(order_id);
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_SERVER);
		int row = businessOrderMapper.updateByKey(orderPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("信息保存成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("信息保存失败。");
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明：修改撤销的订单 编写者：陈骑元 创建时间：2017年5月29日 上午12:28:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto modifyCancelOrder(String order_id) {
		Dto outDto = Dtos.newDto();
		BusinessOrderPO orderPO = new BusinessOrderPO();
		orderPO.setOrder_id(order_id);
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_UNDO);
		orderPO.setCancel_time(IMSUtils.getDateTime());
		orderPO.setFinish_time(IMSUtils.getDateTime());
		int row = businessOrderMapper.updateByKey(orderPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("订单撤销成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("订单撤销失败。");
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明：保存预约订单信息 编写者：陈骑元 创建时间：2017年5月29日 上午12:28:00
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveSubscribeOrder(Dto inDto, ShopUserPO shopUserPO) {
		Dto outDto = Dtos.newDto();
		String mobile = inDto.getString("mobile");
		Dto userDto = Dtos.newDto("mobile", mobile);
		userDto.put("is_del", IMSCons.IS.NO);
		CustomUserPO customUserPO = customUserMapper.selectOne(userDto);
		if (IMSUtils.isEmpty(customUserPO)) {
			customUserPO = new CustomUserPO();
			customUserPO.setCustom_user_id(IdUtil.createCustomUserId());
			customUserPO.setMobile(mobile);
			customUserPO.setUsername(mobile);
			customUserPO.setIs_del(IMSCons.IS.NO);
			customUserPO.setEnroll_time(IMSUtils.getDateTime());
			customUserPO.setEnroll_mode(BeautyCons.ENROLL_MODE_BIS);
			customUserPO.setSex("0");
			customUserMapper.insert(customUserPO);
		}
		String project_id = inDto.getString("project_id");
		String custom_user_id = customUserPO.getCustom_user_id();
		String shop_id = shopUserPO.getShop_id();
		String subscribe_time = inDto.getString("subscribe_time");
		BusinessOrderPO orderPO = new BusinessOrderPO();
		String order_id=IdUtil.createOrderId();
		IMSUtils.copyProperties(inDto, orderPO);
		orderPO.setOrder_id(order_id);
		orderPO.setProject_id(project_id);
		orderPO.setShop_id(shop_id);
		orderPO.setSubscribe_time(IMSUtils.stringToDate(subscribe_time, IMSCons.DATETIMEMIN, IMSCons.DATETIME));
		orderPO.setCreate_time(IMSUtils.getDateTime());
		orderPO.setCustom_user_id(custom_user_id);
		orderPO.setOrder_status(BeautyCons.ORDER_STATUS_SUBSCRIBE);
		orderPO.setOrder_source(BeautyCons.ORDER_SOURCE_SHOP);
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
		//发送微信预约通知模板
		String openid=customUserPO.getOpenid();
		if(IMSUtils.isNotEmpty(openid)){
			WechatCxt.sendSubscribeOrderMessage(openid, order_id,orderPO.getOrder_content(), subscribe_time);
		}
	
		int row = businessOrderMapper.insert(orderPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("预约成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("预约失败。");
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明： 保存扫码支付信息 编写者：陈骑元 创建时间：2017年6月3日 下午9:33:10
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveScanCodePayOrder(String order_id, String pay_way) {
		Dto outDto = Dtos.newDto();
		BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
		String order_status = orderPO.getOrder_status();
		if (!BeautyCons.ORDER_STATUS_SERVER.equals(order_status)) {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("该订单已经支付过，不需要重新支付");
			return outDto;
		}
		Double order_money = orderPO.getOrder_money();
		String pay_id = IMSId.appId();
		String order_content = "消费护理项目(" + orderPO.getOrder_content() + ")";
		Map<String, String> resultMap = null;
		String out_trade_no = "";
		if (BeautyCons.PAY_WAY_WECHAT.equals(pay_way)) {
			out_trade_no = BeautyCons.PAY_TYPE_WECHAT + pay_id;
			resultMap = PayUtil.wechatScanCodePay(out_trade_no, order_money, order_content);
		} else {
			out_trade_no = BeautyCons.PAY_TYPE_ALIPAY + pay_id;
			resultMap = PayUtil.alipayScanCodePay(out_trade_no, order_money, order_content);
		}
		String payStatus = resultMap.get("status");
		if ("500".equals(payStatus)) { // 支付申请请求失败
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("扫码支付失败：请求生成支付订单出错," + resultMap.get("msg"));
			return outDto;
		}

		OrderPayPO orderPayPO = new OrderPayPO();
		orderPayPO.setPay_id(pay_id);
		orderPayPO.setOrder_id(order_id);
		orderPayPO.setPay_code(out_trade_no);
		orderPayPO.setPay_way(pay_way);
		orderPayPO.setPay_status(BeautyCons.PAY_STATUS_UNPAY);
		orderPayPO.setCreate_time(IMSUtils.getDateTime());
		orderPayPO.setPay_money(order_money);
		orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_PAY);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_NO);
		orderPayMapper.insert(orderPayPO);
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.put("resultMap", resultMap);
		outDto.setAppMsg("扫码支付申请订单成功，请尽快进行支付");
		return outDto;
	}

	/**
	 * 
	 * 简要说明：小额扫码枪扫码支付操作 编写者：陈骑元 创建时间：2017年6月8日 上午12:22:31
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveUnifiedPayOrder(String order_id, String auth_code) {
		Dto outDto = Dtos.newDto();
		BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
		String order_status = orderPO.getOrder_status();
		if (!BeautyCons.ORDER_STATUS_SERVER.equals(order_status)) {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("该订单已经支付过，不需要重新支付");
			return outDto;
		}
		Double order_money = orderPO.getOrder_money();
		String pay_id = IMSId.appId();
		String order_content = "消费护理项目(" + orderPO.getOrder_content() + ")";
		String out_trade_no = BeautyCons.PAY_TYPE_UNIFIED + pay_id;
		Map<String, String> resultMap = PayUtil.unifiedPay(out_trade_no, auth_code, order_money, order_content);

		String payStatus = resultMap.get("status");
		if ("500".equals(payStatus)) { // 支付申请请求失败
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("扫码支付失败：" + resultMap.get("msg"));
			return outDto;
		}
		String trade_type = resultMap.get("trade_type"); // 获取交易类型
		String pay_result = resultMap.get("pay_result");
		String openid = resultMap.get("openid");
		String transaction_id = resultMap.get("transaction_id");
		String out_transaction_id = resultMap.get("out_transaction_id");
		String pay_way = BeautyCons.PAY_WAY_ALIPAY;
		if ("pay.weixin.micropay".equals(trade_type)) { // 微信支付
			pay_way = BeautyCons.PAY_WAY_WECHAT;
		}

		OrderPayPO orderPayPO = new OrderPayPO();
		orderPayPO.setPay_id(pay_id);
		orderPayPO.setOrder_id(order_id);
		orderPayPO.setPay_code(out_trade_no);
		orderPayPO.setPay_way(pay_way);
		orderPayPO.setCreate_time(IMSUtils.getDateTime());
		orderPayPO.setPay_money(order_money);
		orderPayPO.setPay_type(BeautyCons.PAY_RECORD_TYPE_PAY);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES);
		orderPayPO.setTransaction_id(transaction_id);
		orderPayPO.setOut_transaction_id(out_transaction_id);
		orderPayPO.setNodify_time(IMSUtils.getDateTime()); // 通知时间
		orderPayPO.setBuy_account(openid);
		orderPayPO.setAuth_code(auth_code); // 填写授权码
		if ("0".equals(pay_result)) {
		    CustomUserPO customUserPO=customUserMapper.selectByKey(orderPO.getCustom_user_id());
		    String wechat_openid=customUserPO.getOpenid();
		    String money_content=orderPO.getPay_money()+"元";
		    if(IMSUtils.isNotEmpty(wechat_openid)){
		    	WechatCxt.sendPayOrderMessage(wechat_openid,order_id,orderPO.getOrder_content(),money_content);
		    }
			orderPayPO.setPay_status(BeautyCons.PAY_STATUS_YES);
			orderPayPO.setPay_time(IMSUtils.getDateTime());
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
			//更新订单信息
			orderPO.setPay_money(orderPayPO.getPay_money());
			orderPO.setPay_way(orderPayPO.getPay_way());
			orderPO.setOrder_status(BeautyCons.ORDER_STATUS_PAY); // 已支付
			orderPO.setPay_time(IMSUtils.getDateTime());
			orderPO.setCash_income(orderPayPO.getPay_money());
			orderPO.setExtend_income(orderPayPO.getPay_money());
			businessOrderMapper.updateByKey(orderPO);
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("支付成功");
		} else {
			String pay_info = resultMap.get("pay_info");
			orderPayPO.setPay_status(BeautyCons.PAY_STATUS_NO);
			orderPayPO.setError_message(pay_info);
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("支付失败："+pay_info);
		}
		orderPayMapper.insert(orderPayPO);
		return outDto;
	}

	/**
	 * 
	 * 简要说明：更新微信支付订单 编写者：陈骑元 创建时间：2017年6月4日 下午2:01:37
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public int updatePayOrder(String pay_id, String pay_status, String transaction_id, String out_transaction_id,
			String openid, String error_message) {
		int row = 0;
		OrderPayPO orderPayPO = orderPayMapper.selectByKey(pay_id);
		orderPayPO.setPay_back(BeautyCons.PAY_BACK_YES); // 已经收到回执
		orderPayPO.setTransaction_id(transaction_id);
		orderPayPO.setOut_transaction_id(out_transaction_id);
		orderPayPO.setNodify_time(IMSUtils.getDateTime()); // 通知时间
		orderPayPO.setBuy_account(openid);
		if (BeautyCons.PAY_STATUS_YES.equals(pay_status)) {
			String order_id = orderPayPO.getOrder_id();
			BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
			 CustomUserPO customUserPO=customUserMapper.selectByKey(orderPO.getCustom_user_id());
			    String wechat_openid=customUserPO.getOpenid();
			    String money_content=orderPO.getPay_money()+"元";
			    if(IMSUtils.isNotEmpty(wechat_openid)){
			    	WechatCxt.sendPayOrderMessage(wechat_openid,order_id,orderPO.getOrder_content(),money_content);
			    }
			
			orderPayPO.setPay_time(IMSUtils.getDateTime());
			
			orderPO.setPay_money(orderPayPO.getPay_money());
			orderPO.setPay_way(orderPayPO.getPay_way());
			orderPO.setOrder_status(BeautyCons.ORDER_STATUS_PAY); // 已支付
			orderPO.setPay_time(IMSUtils.getDateTime());
			orderPO.setCash_income(orderPayPO.getPay_money());
			orderPO.setExtend_income(orderPayPO.getPay_money());
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
	/**
	 * 
	 * 简要说明：保存礼包领取信息
	 * 编写者：陈骑元
	 * 创建时间：2017年6月21日 上午12:07:38
	 * @param 说明
	 * @return 说明
	 */
    public Dto saveReceiveBag(String  record_id,String custom_user_id){
    	Dto outDto=Dtos.newDto();
    	BagRecordPO bagRecordPO=bagRecordMapper.selectByKey(record_id);
    	int share_num=bagRecordPO.getShare_num();
    	String bag_id=bagRecordPO.getBag_id();
    	if(share_num==0){
    		outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("礼包已经领取完");
			return outDto;
    	}
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
    	bagRecordPO.setShare_num(share_num-1);
    	bagRecordPO.setReceive_status(BeautyCons.RECEIVE_STATUS_YES);
    	bagRecordPO.setBag_time(IMSUtils.getDateTime());
    	bagRecordPO.setShare_user_id(bagRecordPO.getCustom_user_id());
    	bagRecordMapper.updateByKey(bagRecordPO);
    	outDto.setAppCode(IMSCons.SUCCESS);
		outDto.setAppMsg("礼包领取成功");
    	return outDto;
    }
    /**
     * 
     * 简要说明：保存和检验分享的礼包
     * 编写者：陈骑元
     * 创建时间：2017年6月28日 下午9:39:07
     * @param 说明
     * @return 说明
     */
    @Transactional
    public Dto saveAndCheckBag(String record_id,String openid){
    	    Dto outDto=Dtos.newDto();
    	    Dto  userDto = Dtos.newDto("openid", openid);
			userDto.put("is_del", IMSCons.IS.NO);
	        CustomUserPO customUserPO = customUserMapper.selectOne(userDto);
	    
	        if(IMSUtils.isNotEmpty(customUserPO)){ //领取礼包
	        	BagRecordPO bagRecordPO=bagRecordMapper.selectByKey(record_id);
	        	 String mobileOld=customUserPO.getMobile();
		         if(IMSUtils.isEmpty(mobileOld)){
			        	String bag_id=bagRecordPO.getBag_id();
			        	CustomUserPO shareCustomUser=customUserMapper.selectByKey(bagRecordPO.getCustom_user_id());
			        	NurseBagPO bagPO=nurseBagMapper.selectByKey(bag_id);
			        	outDto.put("returnFlag", "1");  //用户没有注册过调到领取礼包页面
			        	outDto.put("openid", openid);
			        	outDto.put("record_id", record_id);
			        	outDto.put("msg", "您已获得"+shareCustomUser.getUsername()+"分享的"+bagPO.getBag_name());
			        	 return outDto;	
		         }
	        	if(customUserPO.getCustom_user_id().equals(bagRecordPO.getCustom_user_id())){
	        		outDto.put("returnFlag", "2");  //用户没有注册过调到领取礼包页面
	        		outDto.put("msg","分享人不能领取礼包");
	        		return outDto;
	        	}
	           
	        	String bag_id=bagRecordPO.getBag_id();
	        	CustomUserPO shareCustomUser=customUserMapper.selectByKey(bagRecordPO.getCustom_user_id());
	        	NurseBagPO bagPO=nurseBagMapper.selectByKey(bag_id);
	        	String shareUserName=shareCustomUser.getUsername();
	        	String bagName=bagPO.getBag_name();
	        	Dto checkDto=Dtos.newDto();
	        	checkDto.put("bag_id", bag_id);
	        	checkDto.put("custom_user_id", customUserPO.getCustom_user_id());
	        	checkDto.put("order_id", record_id);
	        	int checkCount=bagRecordMapper.rows(checkDto);
	        	if(checkCount>0){
	        		String msg="你已获得"+shareCustomUser.getUsername()+"分享的"+bagName;
	        	    String mobile=IMSUtils.repalceString(customUserPO.getMobile(), "****", 3, 7);
	        		outDto.put("returnFlag", "3");  //用户没有注册过调到领取礼包页面
	        		outDto.put("msg", msg);
	        		outDto.put("mobile", mobile);
	        		return outDto;
	        	}
	        	int share_num=bagRecordPO.getShare_num();
	        	if(share_num==0){
	        		String msg=shareUserName+"分享的"+bagName+"已经领取完";
	        		outDto.put("returnFlag", "2");  //用户没有注册过调到领取礼包页面
	        		outDto.put("msg", msg);
	        		return outDto;
	        	}
	        	int bag_num=customUserPO.getBag_num()+1;
	        	customUserPO.setBag_num(bag_num);
	        	customUserMapper.updateByKey(customUserPO); //更新个人礼包
	        	List<Dto> bagProjectList=orderManageMapper.listBagProject(bag_id);
	        	String record_id_new=IMSId.appId();
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
	        			projectRecordPO.setBag_record_id(record_id_new);
	        			projectRecordPO.setProject_id(project_id);
	        			projectRecordPO.setDraw_time(IMSUtils.getDateTime());
	        			projectRecordPO.setProject_status(BeautyCons.PROJECT_STATUS_NOUSE);
	        			projectRecordMapper.insert(projectRecordPO);
	        		}
	        	}
	        	BagRecordPO bagRecordPONew=new BagRecordPO();
	        	bagRecordPONew.setRecord_id(record_id_new);
	        	bagRecordPONew.setBag_id(bag_id);
	        	bagRecordPONew.setCustom_user_id(customUserPO.getCustom_user_id());
	        	bagRecordPONew.setValid_day(bagRecordPO.getValid_day());
	        	bagRecordPONew.setBuy_num(1);
	        	bagRecordPONew.setShare_num(0);
	        	bagRecordPONew.setOrder_id(record_id);
	        	bagRecordPONew.setReceive_status(BeautyCons.RECEIVE_STATUS_YES);
	        	bagRecordPONew.setCreate_time(bagRecordPO.getCreate_time());
	        	bagRecordPONew.setRecord_type(BeautyCons.BAG_RECORD_TYPE_FX);
	        	bagRecordPONew.setStatus(BeautyCons.VAILD_STATUS_YES);
	        	bagRecordPONew.setBag_time(IMSUtils.getDateTime());
	        	bagRecordPONew.setShare_user_id(bagRecordPO.getCustom_user_id());
	        	bagRecordMapper.insert(bagRecordPONew);
	        	bagRecordPO.setShare_num(share_num-1);
	        	bagRecordMapper.updateByKey(bagRecordPO);
	        	String username="";
	        	if(IMSUtils.isNotEmpty(shareCustomUser.getUsername())){
	        		username=shareCustomUser.getUsername();
	        	}else{
	        		username=shareCustomUser.getNikename();
	        	}
	        	if(IMSUtils.isEmpty(username)){
	        		username="好朋友";
	        	}
	        	String msg="你已获得"+username+"分享的"+bagName;
        	    String mobile=IMSUtils.repalceString(customUserPO.getMobile(), "****", 3, 7);
        		outDto.put("returnFlag", "3");  //用户没有注册过调到领取礼包页面
        		outDto.put("msg", msg);
        		outDto.put("mobile", mobile);
        		return outDto;
	        }else{
	        	BagRecordPO bagRecordPO=bagRecordMapper.selectByKey(record_id);
	        	String bag_id=bagRecordPO.getBag_id();
	        	CustomUserPO shareCustomUser=customUserMapper.selectByKey(bagRecordPO.getCustom_user_id());
	        	NurseBagPO bagPO=nurseBagMapper.selectByKey(bag_id);
	        	outDto.put("returnFlag", "1");  //用户没有注册过调到领取礼包页面
	        	outDto.put("openid", openid);
	        	outDto.put("record_id", record_id);
	        	String username="";
	        	if(IMSUtils.isNotEmpty(shareCustomUser.getUsername())){
	        		username=shareCustomUser.getUsername();
	        	}else{
	        		username=shareCustomUser.getNikename();
	        	}
	        	if(IMSUtils.isEmpty(username)){
	        		username="好朋友";
	        	}
	        	outDto.put("msg", "您已获得"+username  +"分享的"+bagPO.getBag_name());
	        	 return outDto;
	        }
	       
    }
    /**
     * 
     * 简要说明：保存验证和分享用户
     * 编写者：陈骑元
     * 创建时间：2017年6月28日 上午1:18:32
     * @param 说明
     * @return 说明
     */
    @Transactional
    public Dto saveCheckShareUser(Dto inDto){
    	Dto outDto=Dtos.newDto();
		String mobile=inDto.getString("mobile");
		String checkCode=inDto.getString("checkCode");
		String openid=inDto.getString("openid");
		Jedis jedis=JedisUtil.getJedisClient();
		String templateCode=IMSCxt.getParamValue(BeautyCons.BAG_CHECK_SMS_CODE);
		String checkKey = templateCode+BeautyCons.REDIS_CHECK_CODE_KEY + mobile;
		String checkCodeCache=jedis.get(checkKey);//获取验证码
		JedisUtil.close(jedis);
		if(!checkCode.equals(checkCodeCache)){
			 outDto.setAppCode(IMSCons.ERROR);
			 outDto.setAppMsg("验证码不正确");
			 return outDto;
		}
		Dto userDto = Dtos.newDto("mobile", mobile);
		userDto.put("is_del", IMSCons.IS.NO); 
		CustomUserPO customUserPO = customUserMapper.selectOne(userDto);
		if(IMSUtils.isNotEmpty(customUserPO)){
			String openidStr=customUserPO.getOpenid();
			if(IMSUtils.isNotEmpty(openidStr)){  //openid 不能为空
				if(!openidStr.equals(openid)){
					outDto.setAppCode(IMSCons.ERROR);
					outDto.setAppMsg("该手机号已经绑定其他微信号");
				    return outDto;
				}
			}else{
				customUserPO.setOpenid(openid); //绑定微信号
				customUserMapper.updateByKey(customUserPO);
		  }
		}else{
			Dto countDto=Dtos.newDto();
			countDto.put("openid", openid);
			countDto.put("is_del", IMSCons.IS.NO); 
			 customUserPO = customUserMapper.selectOne(countDto);
			 if(IMSUtils.isNotEmpty(customUserPO)){
				 customUserPO.setMobile(mobile);
				 customUserMapper.updateByKey(customUserPO);
			 }else{
				 customUserPO =new CustomUserPO();
				 String custom_user_id=IdUtil.createCustomUserId();
				 customUserPO.setCustom_user_id(custom_user_id);
				 customUserPO.setOpenid(openid);
				 customUserPO.setMobile(mobile);
				 customUserPO.setEnroll_mode(BeautyCons.ENROLL_MODE_APP);
			     customUserPO.setEnroll_time(IMSUtils.getDateTime());
				 customUserPO.setIs_del(IMSCons.IS.NO);
				 customUserPO.setWechat_status(BeautyCons.WECHAT_STATUS_OAUTH);
				 customUserMapper.insert(customUserPO);
			 }
			
			
		}
		outDto.setAppCode(IMSCons.SUCCESS);
		outDto.put("custom_user_id", customUserPO.getCustom_user_id());
    	return outDto;
    }
    /**
     * 
     * 简要说明：保存礼包分享信息
     * 编写者：陈骑元
     * 创建时间：2017年6月28日 下午11:15:27
     * @param 说明
     * @return 说明
     */
    @Transactional
    public Dto saveShareBag(String custom_user_id,String mobile,String record_id){
    	Dto dataDto=Dtos.newDto();
    	BagRecordPO bagRecordPO=bagRecordMapper.selectByKey(record_id);
    	CustomUserPO shareCustomUser=customUserMapper.selectByKey(bagRecordPO.getCustom_user_id());
    	int share_num=bagRecordPO.getShare_num();
    	String bag_id=bagRecordPO.getBag_id();
    	NurseBagPO bagPO=nurseBagMapper.selectByKey(bag_id);
    	String shareUserName=shareCustomUser.getUsername();
    	String bagName=bagPO.getBag_name();
    	if(share_num==0){
    		String msg=shareUserName+"分享的"+bagName+"已经领取完";
    		dataDto.put("returnFlag", "2");  //用户没有注册过调到领取礼包页面
    		dataDto.put("msg", msg);
    		return dataDto;
    	}
    	CustomUserPO customUserPO=customUserMapper.selectByKey(custom_user_id);
    	int bag_num=customUserPO.getBag_num()+1;
    	customUserPO.setBag_num(bag_num);
    	customUserMapper.updateByKey(customUserPO); //更新个人礼包
    	List<Dto> bagProjectList=orderManageMapper.listBagProject(bag_id);
    	String record_id_new=IMSId.appId();
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
    			projectRecordPO.setBag_record_id(record_id_new);
    			projectRecordPO.setProject_id(project_id);
    			projectRecordPO.setDraw_time(IMSUtils.getDateTime());
    			projectRecordPO.setProject_status(BeautyCons.PROJECT_STATUS_NOUSE);
    			projectRecordMapper.insert(projectRecordPO);
    		}
    	}
    	BagRecordPO bagRecordPONew=new BagRecordPO();
    	bagRecordPONew.setRecord_id(record_id_new);
    	bagRecordPONew.setBag_id(bag_id);
    	bagRecordPONew.setCustom_user_id(custom_user_id);
    	bagRecordPONew.setValid_day(bagRecordPO.getValid_day());
    	bagRecordPONew.setBuy_num(1);
    	bagRecordPONew.setShare_num(0);
    	bagRecordPONew.setOrder_id(record_id);
    	bagRecordPONew.setReceive_status(BeautyCons.RECEIVE_STATUS_YES);
    	bagRecordPONew.setCreate_time(bagRecordPO.getCreate_time());
    	bagRecordPONew.setRecord_type(BeautyCons.BAG_RECORD_TYPE_FX);
    	bagRecordPONew.setStatus(BeautyCons.VAILD_STATUS_YES);
    	bagRecordPONew.setBag_time(IMSUtils.getDateTime());
    	bagRecordPONew.setShare_user_id(bagRecordPO.getCustom_user_id());
    	bagRecordMapper.insert(bagRecordPONew);
    	bagRecordPO.setShare_num(share_num-1);
    	bagRecordMapper.updateByKey(bagRecordPO);
    	String username="";
    	if(IMSUtils.isNotEmpty(shareCustomUser.getUsername())){
    		username=shareCustomUser.getUsername();
    	}else{
    		username=shareCustomUser.getNikename();
    	}
    	if(IMSUtils.isNotEmpty(shareCustomUser.getUsername())){
    		username=shareCustomUser.getUsername();
    	}else{
    		username=shareCustomUser.getNikename();
    	}
    	if(IMSUtils.isEmpty(username)){
    		username="好朋友";
    	}
    	String msg="你已获得"+username+"分享的"+bagName;
	     mobile=IMSUtils.repalceString(mobile, "****", 3, 7);
		dataDto.put("returnFlag", "3");  //用户没有注册过调到领取礼包页面
		dataDto.put("msg", msg);
		dataDto.put("mobile", mobile);
		return dataDto;
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
    public Dto saveBeautyPayOrder(String order_id,int beauty_num,String mobile,String username,String checkCode){
    	Dto outDto=Dtos.newDto();
    	Jedis jedis=JedisUtil.getJedisClient();
		String templateCode=IMSCxt.getParamValue(BeautyCons.CHECK_SMS_CODE);
		String checkKey = templateCode+BeautyCons.REDIS_CHECK_CODE_KEY + mobile;
		String checkCodeCache=jedis.get(checkKey);//获取验证码
		JedisUtil.close(jedis);
		if(!checkCode.equals(checkCodeCache)){
			 outDto.setAppCode(IMSCons.ERROR);
			 outDto.setAppMsg("验证码错误，扣款失败");
			 return outDto;
		}
    	BusinessOrderPO orderPO = businessOrderMapper.selectByKey(order_id);
    	String custom_user_id=orderPO.getCustom_user_id();
    	String order_status = orderPO.getOrder_status();
    	if (!BeautyCons.ORDER_STATUS_SERVER.equals(order_status)) {
    		outDto.setAppCode(IMSCons.ERROR);
    		outDto.setAppMsg("该订单已经支付过，不需要重新支付");
    		return outDto;
    	}
    	CustomUserPO customUserPO=customUserMapper.selectByKey(custom_user_id);
    	int total_beauty=customUserPO.getBeauty_num();
    	if(beauty_num>total_beauty){
    		outDto.setAppCode(IMSCons.ERROR);
    		outDto.setAppMsg("支付失败，颜值不足");
    		return outDto;
    	}
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
        		total_momey+=singe_price*beauty_num_temp;
        		int num=remain_num-beauty_num_temp;
        		if(singe_price==0){
        			free_num+=beauty_num_temp;
        		}
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
		customUserPONew.setUsername(username);
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
    public Dto saveProjectPayOrder(String order_id,String record_id,String mobile,String username,String checkCode){
    	Dto outDto=Dtos.newDto();
    	Jedis jedis=JedisUtil.getJedisClient();
		String templateCode=IMSCxt.getParamValue(BeautyCons.CHECK_SMS_CODE);
		String checkKey = templateCode+BeautyCons.REDIS_CHECK_CODE_KEY + mobile;
		String checkCodeCache=jedis.get(checkKey);//获取验证码
		JedisUtil.close(jedis);
		if(!checkCode.equals(checkCodeCache)){
			 outDto.setAppCode(IMSCons.ERROR);
			 outDto.setAppMsg("验证码错误，扣款失败");
			 return outDto;
		}
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
    	String custom_user_id=orderPO.getCustom_user_id();
    	CustomUserPO customUserPO=customUserMapper.selectByKey(custom_user_id);
    	//发送消息模板
    	String openid=customUserPO.getOpenid();
        if(IMSUtils.isNotEmpty(openid)){  //如果微信号不为空，将发送微信模板
    	    String bag_content=orderPO.getOrder_content()+"项目礼包";
    	  	WechatCxt.sendProjectPayOrderMessage(openid, order_id,orderPO.getOrder_content(), bag_content);
    	 }
         //更新用户名信息
        CustomUserPO customUserPONew=new CustomUserPO();
        customUserPONew.setCustom_user_id(custom_user_id);
        customUserPONew.setUsername(username);
        customUserMapper.updateByKey(customUserPONew);
        
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
    
}
