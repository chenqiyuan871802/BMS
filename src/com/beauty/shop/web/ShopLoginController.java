package com.beauty.shop.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beauty.common.constant.BeautyCons;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.service.ShopCommonService;
import com.beauty.common.service.ShopSystemService;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSJson;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.po.MenuPO;

@Controller
@RequestMapping("shop/login")
public class ShopLoginController {
	
	@Autowired
	private ShopSystemService shopSystemService;
	@Autowired
	private ShopCommonService shopCommonService;
	
	/**
	 * 跳到门店登陆界面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "goLogin")
	public  ModelAndView goLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/login.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：登陆
	 * 编写者：陈骑元
	 * 创建时间：2017年5月6日 下午11:59:40
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value="doLogin", method = RequestMethod.POST)
	public void doLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto = Dtos.newDto(request);
		Dto outDto =shopSystemService.checkLogin(inDto);
		if(IMSCons.SUCCESS==outDto.getAppCode()){
			ShopUserPO shopUserPO=(ShopUserPO)outDto.get("shopUserPO");
			session.setAttribute(BeautyCons.SHOP_USER_INFO_KEY, outDto.get("shopUserPO")); //存入Session
			String loginIp=IMSCxt.getClientIpAddr(request);
			String sessionId=session.getId();
			String explorer=IMSUtils.getClientExplorerType(request);
			shopSystemService.saveLoginLog(shopUserPO, sessionId, loginIp, explorer);
			String user_type=shopUserPO.getUser_type();
			outDto.put("user_type", user_type);
		}
		
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**
	 * 跳到门店登陆主页
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "goMain")
	public  ModelAndView goMain(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		List<MenuPO> grantMenuList=shopCommonService.listCardMenu(shopUserPO.getPost_id());
		modelAndView.addObject("shopUserPO", shopUserPO);
		modelAndView.addObject("grantMenuList", grantMenuList);
		
		modelAndView.setViewName("beauty/shop/main/main.jsp");
		return modelAndView;
	}
	/**
	 * 跳到门店登陆主页
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "goShop")
	public  ModelAndView goShop(String shop_id,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		shopUserPO.setShop_id(shop_id);
		session.setAttribute(BeautyCons.SHOP_USER_INFO_KEY, shopUserPO); //存入Session
		List<MenuPO> grantMenuList=shopCommonService.listCardMenu(shopUserPO.getPost_id());
		modelAndView.addObject("shopUserPO", shopUserPO);
		modelAndView.addObject("grantMenuList", grantMenuList);
		
		modelAndView.setViewName("beauty/shop/main/main.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：跳转到店主首页
	 * 编写者：陈骑元
	 * 创建时间：2017年7月12日 上午1:31:10
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping(value = "goOwnerMain")
	public  ModelAndView goOwnerMain(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/main/ownerMain.jsp");
		return modelAndView;
	}
	/**
	 * 跳到门店登陆主页
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "goShopOwnerIndex")
	public  ModelAndView goShopOwnerIndex(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto pDto=Dtos.newDto();
    	pDto.put("owner_id", shopUserPO.getShop_user_id());
    	pDto.setOrder(" a.sort_no DESC ");
    	List<Dto> shopCountList=shopCommonService.listShopCount(pDto);
    	modelAndView.addObject("shopCountList", shopCountList);
    	modelAndView.addObject("shopUserPO", shopUserPO);
		modelAndView.setViewName("beauty/shop/main/shopOwnerIndex.jsp");
		return modelAndView;
	}
	/**
	 * 跳到门店首页
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "goIndex")
	public  ModelAndView goIndex(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView= new ModelAndView();
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		modelAndView.addObject("shopUserPO", shopUserPO);
	    String post_code=shopUserPO.getPost_code();
	    if("01".equals(post_code)){ //店长
	    	Dto pDto=Dtos.newDto();
	    	pDto.put("shop_id", shopUserPO.getShop_id());
	    	pDto.setOrder(" a.sort_no DESC ");
	    	List<Dto> shopCountList=shopCommonService.listShopCount(pDto);
	    	modelAndView.addObject("shopCountList", shopCountList);
	    }
		modelAndView.setViewName("beauty/shop/main/index.jsp");
		return modelAndView;
	}
	/**
	 * 
	 * 简要说明：跳到用户修改密码界面
	 * 编写者：陈骑元
	 * 创建时间：2017年2月6日 上午11:03:35
	 * @param 说明
	 * @return 说明
	 */
	@RequestMapping("goModifyUserPassword")
	public ModelAndView goModifyUserPassword(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/main/modifyUserPassword.jsp");
		return modelAndView;
	}
	/**
	 * 更新当前用户密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateUserPassword")
	public void updateUserPassword(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Dto inDto = Dtos.newDto(request);
		ShopUserPO shopUserPO=IMSCxt.getShopUserInfo(session);
		Dto outDto =shopSystemService.updatePassword(inDto, shopUserPO);
		IMSCxt.write(response, IMSJson.toJson(outDto));
	}
	/**注销并安全退出系统
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "loginout")
	public  ModelAndView loginout(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String sessionId=session.getId();
		shopSystemService.updateLoginLog( sessionId,BeautyCons.EXIT_TYPE_AUTO);
		session.invalidate(); //注销退出
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("beauty/shop/login.jsp");
		return modelAndView;
	}

}
