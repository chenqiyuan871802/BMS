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
import com.beauty.common.mapper.BusinessOrderMapper;
import com.beauty.common.po.BusinessOrderPO;

/**
 * 
 * 类描述：<b>营业订单[bis_business_order业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-08 23:48:18
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class BusinessOrderService {
  
    @Autowired
	private  BusinessOrderMapper businessOrderMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param businessOrderPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(BusinessOrderPO businessOrderPO){
	
	     return businessOrderMapper.insert(businessOrderPO);
	     
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
	   BusinessOrderPO businessOrderPO =new  BusinessOrderPO();
	   businessOrderPO.setOrder_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  businessOrderPO); 
	   int row=businessOrderMapper.insert(businessOrderPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，营业订单数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，营业订单数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param businessOrderPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(BusinessOrderPO businessOrderPO){
	
	     return businessOrderMapper.insertAll(businessOrderPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param businessOrderPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(BusinessOrderPO businessOrderPO){
	
	     return businessOrderMapper.updateByKey(businessOrderPO);
	
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
	   BusinessOrderPO businessOrderPO =new  BusinessOrderPO();
	   IMSUtils.copyProperties(inDto,  businessOrderPO); 
	   int row=businessOrderMapper.updateByKey(businessOrderPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，营业订单数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，营业订单数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return BusinessOrderPO
	 */
	public BusinessOrderPO selectByKey( String order_id){
	
	    return businessOrderMapper.selectByKey(order_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return BusinessOrderPO
	 */
	public BusinessOrderPO selectOne(Dto pDto){
	
	    return businessOrderMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<BusinessOrderPO>
	 */
	public List<BusinessOrderPO> list(Dto pDto){
	
	     return businessOrderMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<BusinessOrderPO>
	 */
	public List<BusinessOrderPO> listPage(Dto pDto){
	
	     return businessOrderMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BusinessOrderPO>
	 */
	public List<BusinessOrderPO> like(Dto pDto){
	  
	      return businessOrderMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BusinessOrderPO>
	 */
	public List<BusinessOrderPO> likePage(Dto pDto){
	
	    return businessOrderMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String order_id){
	
	    return businessOrderMapper.deleteByKey(order_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String order_id){
	   Dto outDto = Dtos.newDto();
	   int row=businessOrderMapper.deleteByKey(order_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，营业订单数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，营业订单数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> order_idList){
	     
	     return businessOrderMapper.batchDeleteByKey(order_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> order_idList){
	    Dto outDto = Dtos.newDto();
	    int row=businessOrderMapper.batchDeleteByKey(order_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量营业订单数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量营业订单数据删除失败。");
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
	
	    return businessOrderMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return businessOrderMapper.calc(pDto);
	
	};
	
}
