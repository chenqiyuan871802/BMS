package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.mapper.ShopMapper;
import com.beauty.common.po.ShopPO;

/**
 * 
 * 类描述：<b>店铺信息表[bis_shop业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:08:32
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class ShopService {
  
    @Autowired
	private  ShopMapper shopMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param shopPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(ShopPO shopPO){
	
	     return shopMapper.insert(shopPO);
	     
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
	   ShopPO shopPO =new  ShopPO();
	   IMSUtils.copyProperties(inDto,  shopPO); 
	   shopPO.setIs_del(IMSCons.IS.NO);
	   shopPO.setCreate_time(IMSUtils.getDateTime());
	   shopPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   shopPO.setModify_time(IMSUtils.getDateTime());
	   shopPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=shopMapper.insert(shopPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，店铺信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，店铺信息新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param shopPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(ShopPO shopPO){
	
	     return shopMapper.insertAll(shopPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param shopPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(ShopPO shopPO){
	
	     return shopMapper.updateByKey(shopPO);
	
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
	   ShopPO shopPO =new  ShopPO();
	   IMSUtils.copyProperties(inDto,  shopPO); 
	   shopPO.setModify_time(IMSUtils.getDateTime());
	   shopPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=shopMapper.updateByKey(shopPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，店铺信息改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，店铺信息修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ShopPO
	 */
	public ShopPO selectByKey( String shop_id){
	
	    return shopMapper.selectByKey(shop_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ShopPO
	 */
	public ShopPO selectOne(Dto pDto){
	
	    return shopMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ShopPO>
	 */
	public List<ShopPO> list(Dto pDto){
	
	     return shopMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ShopPO>
	 */
	public List<ShopPO> listPage(Dto pDto){
	
	     return shopMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopPO>
	 */
	public List<ShopPO> like(Dto pDto){
	  
	      return shopMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopPO>
	 */
	public List<ShopPO> likePage(Dto pDto){
	
	    return shopMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String shop_id){
	
	    return shopMapper.deleteByKey(shop_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String shop_id,String user_id){
	   Dto outDto = Dtos.newDto();
	   ShopPO shopPO=new ShopPO();
	   shopPO.setShop_id(shop_id);
	   shopPO.setIs_del(IMSCons.IS.YES);
	   shopPO.setModify_time(IMSUtils.getDateTime());
	   shopPO.setModify_user_id(user_id);
		int row=shopMapper.updateByKey(shopPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，店铺信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，店铺信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> shop_idList){
	     
	     return shopMapper.batchDeleteByKey(shop_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> shop_idList){
	    Dto outDto = Dtos.newDto();
	    int row=shopMapper.batchDeleteByKey(shop_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量店铺信息表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量店铺信息表数据删除失败。");
	    }
	    return outDto;
	
	};
	
	/**
	 * 根据Dto统计行数
	 * 
	 * @param pDto
	 * @return
	 */
	public int rows(Dto pDto){
	
	    return shopMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return shopMapper.calc(pDto);
	
	};
	
}
