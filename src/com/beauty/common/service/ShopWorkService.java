package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.mapper.ShopWorkMapper;
import com.beauty.common.po.ShopUserPO;
import com.beauty.common.po.ShopWorkPO;

/**
 * 
 * 类描述：<b>工作机[bis_shop_work业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-16 15:51:54
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class ShopWorkService {
  
    @Autowired
	private  ShopWorkMapper shopWorkMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param shopWorkPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(ShopWorkPO shopWorkPO){
	
	     return shopWorkMapper.insert(shopWorkPO);
	     
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
	   ShopWorkPO shopWorkPO =new  ShopWorkPO();
	   shopWorkPO.setWork_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  shopWorkPO); 
	   int row=shopWorkMapper.insert(shopWorkPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，工作机数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，工作机数据新增失败。");
	   }
	   return outDto;
	     
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
	public Dto save(Dto inDto,ShopUserPO shopUserPO){
		Dto outDto = Dtos.newDto();
		ShopWorkPO shopWorkPO =new  ShopWorkPO();
	
		IMSUtils.copyProperties(inDto,  shopWorkPO); 
		String whether_set=inDto.getString("whether_set");
		if(IMSUtils.isEmpty(whether_set)){
			shopWorkPO.setWhether_set(IMSCons.IS.NO);
		}
		shopWorkPO.setWork_id(IMSId.uuid());
		String shop_id=shopUserPO.getShop_id();
		shopWorkPO.setCreate_time(IMSUtils.getDateTime());
		shopWorkPO.setModify_time(IMSUtils.getDateTime());
		shopWorkPO.setShop_id(shop_id);
		shopWorkPO.setCreate_user_id(shopUserPO.getShop_user_id());
		shopWorkPO.setModify_user_id(shopUserPO.getShop_user_id());
		String work_sn=shop_id+System.currentTimeMillis();
		shopWorkPO.setWork_sn(work_sn);
		int row=shopWorkMapper.insert(shopWorkPO);
		if(row>0){
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.put("work_sn",work_sn);
			outDto.setAppMsg("操作完成，工作机设置成功。");
		}else{
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，工作机设置失败。");
		}
		return outDto;
		
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param shopWorkPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(ShopWorkPO shopWorkPO){
	
	     return shopWorkMapper.insertAll(shopWorkPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param shopWorkPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(ShopWorkPO shopWorkPO){
	
	     return shopWorkMapper.updateByKey(shopWorkPO);
	
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
	   ShopWorkPO shopWorkPO =new  ShopWorkPO();
	   IMSUtils.copyProperties(inDto,  shopWorkPO); 
	   int row=shopWorkMapper.updateByKey(shopWorkPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，工作机数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，工作机数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ShopWorkPO
	 */
	public ShopWorkPO selectByKey( String work_id){
	
	    return shopWorkMapper.selectByKey(work_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ShopWorkPO
	 */
	public ShopWorkPO selectOne(Dto pDto){
	
	    return shopWorkMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ShopWorkPO>
	 */
	public List<ShopWorkPO> list(Dto pDto){
	
	     return shopWorkMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ShopWorkPO>
	 */
	public List<ShopWorkPO> listPage(Dto pDto){
	
	     return shopWorkMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopWorkPO>
	 */
	public List<ShopWorkPO> like(Dto pDto){
	  
	      return shopWorkMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopWorkPO>
	 */
	public List<ShopWorkPO> likePage(Dto pDto){
	
	    return shopWorkMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String work_id){
	
	    return shopWorkMapper.deleteByKey(work_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( Dto inDto){
	   String whether_set=inDto.getString("whether_set");
	   String work_id=inDto.getString("work_id"); 
	   Dto outDto = Dtos.newDto();
	   if(whether_set.equals(IMSCons.IS.YES)){
		  ShopWorkPO shopWorkPO=shopWorkMapper.selectByKey(work_id);
		  String work_password=inDto.getString("work_password");
		  if(!shopWorkPO.getWork_password().equals(work_password)){
			   outDto.setAppCode(IMSCons.WARN);
			   outDto.setAppMsg("解除失败，输入本机密码不正确。");
			   return outDto;
		  }
	   }
	   
	 
	   int row=shopWorkMapper.deleteByKey(work_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，解除工作机成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，解除工作机失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> work_idList){
	     
	     return shopWorkMapper.batchDeleteByKey(work_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> work_idList){
	    Dto outDto = Dtos.newDto();
	    int row=shopWorkMapper.batchDeleteByKey(work_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量工作机数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量工作机数据删除失败。");
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
	
	    return shopWorkMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return shopWorkMapper.calc(pDto);
	
	};
	
}
