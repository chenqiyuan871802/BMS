package com.beauty.common.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beauty.common.po.NurseProjectPO;
import com.beauty.common.po.ShopPostPO;
import com.beauty.common.po.ShopUserPO;
import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.dao.plugin.DBType;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.po.MenuPO;
import com.ims.common.system.modules.po.TreeModel;
/**
 * 
 * 类名:com.beauty.common.service.ShopCommonService
 * 描述:店铺通用管理
 * 编写者:陈骑元
 * 创建时间:2017年4月17日 下午10:40:33
 * 修改说明:
 */
@Service
public class ShopCommonService {
    
	@Autowired
	private SqlDao sqlDao;
	/**
	 * 
	 * 简要说明：查询店铺相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listShopPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询店铺相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShop(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listShop", pDto);
	}
	/**
	 * 
	 * 简要说明：查询店铺相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopUserPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listShopUserPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询店铺相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public  Integer queryShopCount(String shop_id){
		
		return (Integer)sqlDao.selectOne("ShopCommonMapper.queryShopCount", shop_id);
	}
	/**
	 * 
	 * 简要说明：查询店主相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<ShopUserPO> listShopOwnerPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listShopOwnerPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询店铺顾客的信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopCustomUserPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listShopCustomUserPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询顾客相关信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listCustomUserPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listCustomUserPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询护理项目列表
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listNurseProjectPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listNurseProjectPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询护理项目列表
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<NurseProjectPO> listNurseProject(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listNurseProject", pDto);
	}
	/**
	 * 
	 * 简要说明：查询护理项目详情信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public Dto queryNurseProjectDetail(String project_id){
		
		return sqlDao.selectDto("ShopCommonMapper.queryNurseProjectDetail",project_id);
	}
	/**
	 * 
	 * 简要说明：查询礼包信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listNurseBagPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listNurseBagPage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询活动礼包
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listActiveBag(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listActiveBag", pDto);
	}
	/**
	 * 
	 * 简要说明：查询礼包项目
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listBagProject(String bag_id){
		
		return sqlDao.list("ShopCommonMapper.listBagProject", bag_id);
	}
	/**
	 * 
	 * 简要说明：查询我的收藏
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopCollect(String custom_user_id){
		
		return sqlDao.list("ShopCommonMapper.listShopCollect",custom_user_id);
	}
	/**
	 * 
	 * 简要说明：分页查询活动信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月17日 下午10:50:01
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listCouponActivePage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listCouponActivePage", pDto);
	}
	/**
	 * 
	 * 简要说明：查询授权的菜单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月7日 上午11:23:00
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listGrantPostMenu(String post_id){
		
		return sqlDao.list("ShopCommonMapper.listGrantPostMenu",post_id);
	}
	/**
	 * 
	 * 简要说明：店铺统计
	 * 编写者：陈骑元
	 * 创建时间：2017年5月7日 上午11:23:00
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listShopCount(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listShopCount",pDto);
	}
	/**
	 * 查询授权菜单树
	 * 
	 * @param pDto
	 * @return
	 */
	public TreeModel loadGrantMenuTree(String post_id) {
		Dto pDto=Dtos.newDto();
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			pDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			pDto.setOrder("LENGTH(cascade_id) ASC,sort_no ASC ");
		}
		pDto.put("menu_type",DicCons.MENU_TYPE_BIS);
		List<MenuPO> menuPOList  =sqlDao.list("System.listEnabledMenu", pDto);
		List<Dto> grantMenuList =Lists.newArrayList();
		if(IMSUtils.isNotEmpty(post_id)){
			grantMenuList=listGrantPostMenu(post_id);
		}
		TreeModel rootModel = new TreeModel();
		rootModel.setText(IMSCons.MENU_ROOT_NAME);
		rootModel.setId(IMSCons.TREE_ROOT_ID);
		rootModel.setIconCls(IMSCons.MENU_ROOT_ICONCLS);
		for (int i = 0; i < menuPOList.size(); i++) {
			MenuPO menuPO = menuPOList.get(i);
			int child_count = menuPO.getChild_count();
			String parent_id = menuPO.getParent_id();
			String menu_id = menuPO.getMenu_id();
			String icon_name = menuPO.getIcon_name();
			if (IMSCons.TREE_ROOT_ID.equals(parent_id) && child_count == 0) { // 过滤一级菜单没有子菜单的数据
				continue;
			}
			TreeModel treeModel = new TreeModel();
			treeModel.setId(menu_id);
			treeModel.setParentId(parent_id);
			treeModel.setText(menuPO.getMenu_name());
			if (IMSUtils.isNotEmpty(icon_name)) {
				
				if(icon_name.indexOf(".png")>-1){
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}else{
					treeModel.setIconCls(icon_name);
				}
			} else {
				if(child_count==0){  //子节点替换子节点图标
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}
			}
			if (!IMSCons.TREE_ROOT_ID.equals(parent_id)) {
				if (checkGrantMenu(menu_id, grantMenuList)) {
					treeModel.setChecked("true");

				}
			}
			rootModel.add(treeModel);
		}
		return rootModel;
	}
	/**
	 * 检查是否授权菜单
	 * 
	 * @return
	 */
	private boolean checkGrantMenu(String menu_id, List<Dto> grantMenuList) {
		for (int i = 0; i < grantMenuList.size(); i++) {
			Dto grantMenuDto = grantMenuList.get(i);
			String grantMenuId = grantMenuDto.getString("menu_id");
			if (menu_id.equals(grantMenuId)) {
				return true;
			}
		}
		return false;

	}
	
	/**
	 * 
	 * 简要说明：查询商家用户的卡片菜单
	 * 编写者：陈骑元
	 * 创建时间：2017年5月7日 上午11:02:44
	 * @param 说明
	 * @return 说明
	 */
	public List<MenuPO> listCardMenu(String post_id){
		Dto pDto=Dtos.newDto();
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			pDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			pDto.setOrder("LENGTH(cascade_id) ASC,sort_no ASC ");
		}
		
		pDto.put("menu_type",DicCons.MENU_TYPE_BIS);
		List<MenuPO> menuPOList  =sqlDao.list("System.listEnabledMenu", pDto);
		if(IMSUtils.isEmpty(post_id)){  //如果岗位编号为空说明是店主，拥有超级权限
		    
			return menuPOList;
		}else{
			List<MenuPO>  grantMenuList=Lists.newArrayList();
			List<Dto> postMenuList=listGrantPostMenu(post_id);
			for(int i=0;i<menuPOList.size();i++){
				MenuPO menuPO = menuPOList.get(i);
				String menu_id=menuPO.getMenu_id();
				if (checkGrantMenu(menu_id, postMenuList)) {
					grantMenuList.add(menuPO);
				}
			}
			return grantMenuList;
		}
		
	}
	/**
	 * 
	 * 简要说明：
	 * 编写者：陈骑元
	 * 创建时间：2017年6月24日 下午12:25:57
	 * @param 说明
	 * @return 说明
	 */
	public int queryShopCollectCount(Dto pDto){
		
		return (Integer)sqlDao.selectOne("ShopCommonMapper.queryShopCollectCount", pDto);
	}
	
	@Transactional
	public Dto saveShopCollect(String shop_id,String custom_user_id){
	   Dto outDto = Dtos.newDto();
	   Dto pDto=Dtos.newDto();
	   pDto.put("shop_id", shop_id);
	   pDto.put("custom_user_id", custom_user_id);
	   int count=queryShopCollectCount(pDto);
	   if(count>0){
		   sqlDao.insert("ShopCommonMapper.deleteShopCollect", pDto);
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.put("showClass", 1);
		   outDto.setAppMsg("已取消该店铺收藏");  
		   return outDto;
	   }
	   Dto inDto=Dtos.newDto();
	   inDto.put("collect_id", IMSId.appId());
	   inDto.put("shop_id", shop_id);
	   inDto.put("custom_user_id", custom_user_id);
	   inDto.put("create_time", IMSUtils.getDateTime());
	   int row= sqlDao.insert("ShopCommonMapper.saveShopCollect", inDto);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.put("showClass", 0);
		   outDto.setAppMsg("店铺收藏成功");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("店铺收藏失败");
	   }
	   return outDto;
	     
	};
}
