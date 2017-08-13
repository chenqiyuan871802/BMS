package com.beauty.wechat.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beauty.asyncTask.PayNodifyTask;
import com.beauty.pay.util.PayUtil;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;

/**
 * 
 * 类名:com.beauty.pay.wechatpay.web.AppNodifyController
 * 描述:通知业务控制类处理
 * 编写者:陈骑元
 * 创建时间:2017年6月3日 上午9:12:11
 * 修改说明:
 */
@Controller
@RequestMapping("nodify")
public class NodifyController {
	
	@Autowired
	private PayNodifyTask payNodifyTask;
	/**
	 * 
	 * 简要说明：app支付通知业务处理
	 * 编写者：陈骑元
	 * 创建时间：2017年6月3日 上午9:15:23
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="appPayNodify",method = RequestMethod.POST)
	public void appPayNodify(HttpServletRequest request, HttpServletResponse response){
		  Map<String,String> resultMap=PayUtil.handleAppNodify(request);
		  String handleStatus=resultMap.get("handleStatus"); //处理状态
		  if(IMSCons.REQUEST_SUCCESS.equals(handleStatus)){  //处理成功
			  payNodifyTask.handleAppNodify(resultMap);
		  }
		  IMSCxt.writeRaw(response, handleStatus);
	}

}
