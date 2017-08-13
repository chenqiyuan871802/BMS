package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCodec;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.mapper.ShopUserMapper;
import com.beauty.common.po.ShopUserPO;

/**
 * 
 * 类描述：<b>店铺员工信息表[bis_shop_user业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:09:21
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class ShopUserService {
  
    @Autowired
	private  ShopUserMapper shopUserMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param shopUserPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(ShopUserPO shopUserPO){
	
	     return shopUserMapper.insert(shopUserPO);
	     
	};
	/**
	 * 保存一个持久化对象 并返回一个Dto对象
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param inDto Dto参数对象
	 *            
	 * @return 返回Dto对象
	 */
	@Transactional
	public Dto save(Dto inDto){
	   Dto outDto = Dtos.newDto();
	   Dto countDto = Dtos.newDto();
	   countDto.put("account", inDto.getString("account"));
	   countDto.put("is_del", IMSCons.IS.NO);
	   int count=shopUserMapper.rows(countDto);
	   if(count>0){
			outDto.setAppMsg("员工账号已被占用，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   ShopUserPO shopUserPO =new  ShopUserPO();
	   IMSUtils.copyProperties(inDto,  shopUserPO); 
	   String password=inDto.getString("password");
	   String encryptPassword=IMSCodec.encrypt(password,IMSCons.PASSWORD_KEY);
	   shopUserPO.setPassword(encryptPassword);
	   shopUserPO.setIs_del(IMSCons.IS.NO);
	   shopUserPO.setUser_type(BeautyCons.USER_TYPE_STAFF);
	   shopUserPO.setCreate_time(IMSUtils.getDateTime());
	   shopUserPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   shopUserPO.setModify_time(IMSUtils.getDateTime());
	   shopUserPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=shopUserMapper.insert(shopUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，员工信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败， 员工信息新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param shopUserPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(ShopUserPO shopUserPO){
	
	     return shopUserMapper.insertAll(shopUserPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param shopUserPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(ShopUserPO shopUserPO){
	
	     return shopUserMapper.updateByKey(shopUserPO);
	
	};
	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param inDto传入参数
	 *            要修改的数据持久化对象
	 * @return Dto 返回影Dto对象
	 */
    @Transactional
	public Dto update(Dto inDto){
	   Dto outDto = Dtos.newDto();
	   ShopUserPO shopUserPO =new  ShopUserPO();
	   IMSUtils.copyProperties(inDto,  shopUserPO); 
	   shopUserPO.setModify_time(IMSUtils.getDateTime());
	   shopUserPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=shopUserMapper.updateByKey(shopUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，店铺员工信息表数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，店铺员工信息表数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ShopUserPO
	 */
	public ShopUserPO selectByKey( String shop_user_id){
	
	    return shopUserMapper.selectByKey(shop_user_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ShopUserPO
	 */
	public ShopUserPO selectOne(Dto pDto){
	
	    return shopUserMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ShopUserPO>
	 */
	public List<ShopUserPO> list(Dto pDto){
	
	     return shopUserMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ShopUserPO>
	 */
	public List<ShopUserPO> listPage(Dto pDto){
	
	     return shopUserMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopUserPO>
	 */
	public List<ShopUserPO> like(Dto pDto){
	  
	      return shopUserMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopUserPO>
	 */
	public List<ShopUserPO> likePage(Dto pDto){
	
	    return shopUserMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String shop_user_id){
	
	    return shopUserMapper.deleteByKey(shop_user_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String shop_user_id,String user_id){
		Dto outDto = Dtos.newDto();
		ShopUserPO shopUserPO =new  ShopUserPO();
		shopUserPO.setShop_user_id(shop_user_id);
		shopUserPO.setIs_del(IMSCons.IS.YES);
		shopUserPO.setModify_time(IMSUtils.getDateTime());
		shopUserPO.setModify_user_id(user_id);
		int row=shopUserMapper.updateByKey(shopUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，员工信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，员工信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> shop_user_idList){
	     
	     return shopUserMapper.batchDeleteByKey(shop_user_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> shop_user_idList){
	    Dto outDto = Dtos.newDto();
	    int row=shopUserMapper.batchDeleteByKey(shop_user_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量店铺员工信息表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量店铺员工信息表数据删除失败。");
	    }
	    return outDto;
	
	};
	/**
	 * 
	 * 简要说明：更新用户密码
	 * 编写者：陈骑元
	 * 创建时间：2016年12月19日 下午7:59:10
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto updatePassword(Dto inDto){
		Dto outDto=Dtos.newDto();
		ShopUserPO shopUserPO=new ShopUserPO();
		String password=inDto.getString("password");
		String encryptPassword=IMSCodec.encrypt(password,IMSCons.PASSWORD_KEY);
		shopUserPO.setPassword(encryptPassword);
		shopUserPO.setShop_user_id(inDto.getString("shop_user_id"));
		shopUserPO.setModify_time(IMSUtils.getDateTime());
		shopUserPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
		 int row=shopUserMapper.updateByKey(shopUserPO);
		  if(row>0){
		       outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("操作完成，密码重置成功。");
		  }else{
		       outDto.setAppCode(IMSCons.ERROR);
			   outDto.setAppMsg("操作失败，密码重置失败。");
		  }
		return outDto;
	}
	/**
	 * 根据Dto统计行数
	 * 
	 * @param pDto
	 * @return
	 */
	public int rows(Dto pDto){
	
	    return shopUserMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return shopUserMapper.calc(pDto);
	
	};
	/**
	 * 
	 * 简要说明：保存店主信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月18日 上午12:13:06
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveShopOwner(Dto inDto){
	   Dto outDto = Dtos.newDto();
	   Dto countDto = Dtos.newDto();
	   countDto.put("account", inDto.getString("account"));
	   countDto.put("is_del", IMSCons.IS.NO);
	   int count=shopUserMapper.rows(countDto);
	   if(count>0){
			outDto.setAppMsg("店铺账号已被占用，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   ShopUserPO shopUserPO =new  ShopUserPO();
	   String password=inDto.getString("password");
	   String encryptPassword=IMSCodec.encrypt(password,IMSCons.PASSWORD_KEY);
	   shopUserPO.setPassword(encryptPassword);
	   shopUserPO.setUser_type(BeautyCons.USER_TYPE_OWNER);
	   shopUserPO.setIs_del(IMSCons.IS.NO);
	   shopUserPO.setShop_user_id(IMSId.appId());
	   shopUserPO.setCreate_time(IMSUtils.getDateTime());
	   shopUserPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   shopUserPO.setModify_time(IMSUtils.getDateTime());
	   shopUserPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   IMSUtils.copyProperties(inDto,  shopUserPO); 
	   int row=shopUserMapper.insert(shopUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，店主信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，店主信息新增失败。");
	   }
	   return outDto;
	};
	/**
	 * 
	 * 简要说明：修改店主信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月18日 上午12:40:41
	 * @param 说明
	 * @return 说明
	 */
    @Transactional
	public Dto updateShopOwner(Dto inDto){
	   Dto outDto = Dtos.newDto();
	   ShopUserPO shopUserPO =new  ShopUserPO();
	   IMSUtils.copyProperties(inDto,  shopUserPO); 
	   shopUserPO.setModify_time(IMSUtils.getDateTime());
	   shopUserPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=shopUserMapper.updateByKey(shopUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，店主信息修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，店主信息修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 
	 * 简要说明：删除店主信息
	 * 编写者：陈骑元
	 * 创建时间：2017年4月18日 上午12:40:41
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto deleteShopOwner(String shop_user_id,String user_id){
		Dto outDto = Dtos.newDto();
		ShopUserPO shopUserPO =new  ShopUserPO();
		shopUserPO.setShop_user_id(shop_user_id);
		shopUserPO.setIs_del(IMSCons.IS.YES);
		shopUserPO.setModify_time(IMSUtils.getDateTime());
		shopUserPO.setModify_user_id(user_id);
		int row=shopUserMapper.updateByKey(shopUserPO);
		if(row>0){
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，店主信息删除成功。");
		}else{
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，店主信息删除失败。");
		}
		return outDto;
		
	};
}
