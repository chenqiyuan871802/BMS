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
import com.beauty.common.mapper.OrderDepositMapper;
import com.beauty.common.po.OrderDepositPO;

/**
 * 
 * 类描述：<b>定金信息[bis_order_deposit业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-07 22:47:13
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class OrderDepositService {
  
    @Autowired
	private  OrderDepositMapper orderDepositMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param orderDepositPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(OrderDepositPO orderDepositPO){
	
	     return orderDepositMapper.insert(orderDepositPO);
	     
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
	   OrderDepositPO orderDepositPO =new  OrderDepositPO();
	   orderDepositPO.setDeposit_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  orderDepositPO); 
	   int row=orderDepositMapper.insert(orderDepositPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，定金信息数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，定金信息数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param orderDepositPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(OrderDepositPO orderDepositPO){
	
	     return orderDepositMapper.insertAll(orderDepositPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param orderDepositPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(OrderDepositPO orderDepositPO){
	
	     return orderDepositMapper.updateByKey(orderDepositPO);
	
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
	   OrderDepositPO orderDepositPO =new  OrderDepositPO();
	   IMSUtils.copyProperties(inDto,  orderDepositPO); 
	   int row=orderDepositMapper.updateByKey(orderDepositPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，定金信息数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，定金信息数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return OrderDepositPO
	 */
	public OrderDepositPO selectByKey( String deposit_id){
	
	    return orderDepositMapper.selectByKey(deposit_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return OrderDepositPO
	 */
	public OrderDepositPO selectOne(Dto pDto){
	
	    return orderDepositMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<OrderDepositPO>
	 */
	public List<OrderDepositPO> list(Dto pDto){
	
	     return orderDepositMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<OrderDepositPO>
	 */
	public List<OrderDepositPO> listPage(Dto pDto){
	
	     return orderDepositMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OrderDepositPO>
	 */
	public List<OrderDepositPO> like(Dto pDto){
	  
	      return orderDepositMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OrderDepositPO>
	 */
	public List<OrderDepositPO> likePage(Dto pDto){
	
	    return orderDepositMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String deposit_id){
	
	    return orderDepositMapper.deleteByKey(deposit_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String deposit_id){
	   Dto outDto = Dtos.newDto();
	   int row=orderDepositMapper.deleteByKey(deposit_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，定金信息数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，定金信息数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> deposit_idList){
	     
	     return orderDepositMapper.batchDeleteByKey(deposit_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> deposit_idList){
	    Dto outDto = Dtos.newDto();
	    int row=orderDepositMapper.batchDeleteByKey(deposit_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量定金信息数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量定金信息数据删除失败。");
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
	
	    return orderDepositMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return orderDepositMapper.calc(pDto);
	
	};
	
}
