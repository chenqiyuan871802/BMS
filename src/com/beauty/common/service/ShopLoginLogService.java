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
import com.beauty.common.mapper.ShopLoginLogMapper;
import com.beauty.common.po.ShopLoginLogPO;

/**
 * 
 * 类描述：<b>商家登陆日志[bis_shop_loginlog业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-07 01:03:07
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class ShopLoginLogService {
  
    @Autowired
	private  ShopLoginLogMapper shopLoginLogMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param shopLoginLogPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(ShopLoginLogPO shopLoginLogPO){
	
	     return shopLoginLogMapper.insert(shopLoginLogPO);
	     
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
	   ShopLoginLogPO shopLoginLogPO =new  ShopLoginLogPO();
	   shopLoginLogPO.setLog_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  shopLoginLogPO); 
	   int row=shopLoginLogMapper.insert(shopLoginLogPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，商家登陆日志数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，商家登陆日志数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param shopLoginLogPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(ShopLoginLogPO shopLoginLogPO){
	
	     return shopLoginLogMapper.insertAll(shopLoginLogPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param shopLoginLogPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(ShopLoginLogPO shopLoginLogPO){
	
	     return shopLoginLogMapper.updateByKey(shopLoginLogPO);
	
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
	   ShopLoginLogPO shopLoginLogPO =new  ShopLoginLogPO();
	   IMSUtils.copyProperties(inDto,  shopLoginLogPO); 
	   int row=shopLoginLogMapper.updateByKey(shopLoginLogPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，商家登陆日志数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，商家登陆日志数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ShopLoginLogPO
	 */
	public ShopLoginLogPO selectByKey( String log_id){
	
	    return shopLoginLogMapper.selectByKey(log_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ShopLoginLogPO
	 */
	public ShopLoginLogPO selectOne(Dto pDto){
	
	    return shopLoginLogMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ShopLoginLogPO>
	 */
	public List<ShopLoginLogPO> list(Dto pDto){
	
	     return shopLoginLogMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ShopLoginLogPO>
	 */
	public List<ShopLoginLogPO> listPage(Dto pDto){
	
	     return shopLoginLogMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopLoginLogPO>
	 */
	public List<ShopLoginLogPO> like(Dto pDto){
	  
	      return shopLoginLogMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopLoginLogPO>
	 */
	public List<ShopLoginLogPO> likePage(Dto pDto){
	
	    return shopLoginLogMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String log_id){
	
	    return shopLoginLogMapper.deleteByKey(log_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String log_id){
	   Dto outDto = Dtos.newDto();
	   int row=shopLoginLogMapper.deleteByKey(log_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，商家登陆日志数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，商家登陆日志数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> log_idList){
	     
	     return shopLoginLogMapper.batchDeleteByKey(log_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> log_idList){
	    Dto outDto = Dtos.newDto();
	    int row=shopLoginLogMapper.batchDeleteByKey(log_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量商家登陆日志数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量商家登陆日志数据删除失败。");
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
	
	    return shopLoginLogMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return shopLoginLogMapper.calc(pDto);
	
	};
	
}
