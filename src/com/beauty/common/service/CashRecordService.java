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
import com.beauty.common.mapper.CashRecordMapper;
import com.beauty.common.po.CashRecordPO;

/**
 * 
 * 类描述：<b>现金流水[bis_cash_record业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-07 22:45:48
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class CashRecordService {
  
    @Autowired
	private  CashRecordMapper cashRecordMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param cashRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(CashRecordPO cashRecordPO){
	
	     return cashRecordMapper.insert(cashRecordPO);
	     
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
	   CashRecordPO cashRecordPO =new  CashRecordPO();
	   cashRecordPO.setRecord_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  cashRecordPO); 
	   int row=cashRecordMapper.insert(cashRecordPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，现金流水数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，现金流水数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param cashRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(CashRecordPO cashRecordPO){
	
	     return cashRecordMapper.insertAll(cashRecordPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param cashRecordPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(CashRecordPO cashRecordPO){
	
	     return cashRecordMapper.updateByKey(cashRecordPO);
	
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
	   CashRecordPO cashRecordPO =new  CashRecordPO();
	   IMSUtils.copyProperties(inDto,  cashRecordPO); 
	   int row=cashRecordMapper.updateByKey(cashRecordPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，现金流水数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，现金流水数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return CashRecordPO
	 */
	public CashRecordPO selectByKey( String record_id){
	
	    return cashRecordMapper.selectByKey(record_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return CashRecordPO
	 */
	public CashRecordPO selectOne(Dto pDto){
	
	    return cashRecordMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<CashRecordPO>
	 */
	public List<CashRecordPO> list(Dto pDto){
	
	     return cashRecordMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<CashRecordPO>
	 */
	public List<CashRecordPO> listPage(Dto pDto){
	
	     return cashRecordMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CashRecordPO>
	 */
	public List<CashRecordPO> like(Dto pDto){
	  
	      return cashRecordMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CashRecordPO>
	 */
	public List<CashRecordPO> likePage(Dto pDto){
	
	    return cashRecordMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String record_id){
	
	    return cashRecordMapper.deleteByKey(record_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String record_id){
	   Dto outDto = Dtos.newDto();
	   int row=cashRecordMapper.deleteByKey(record_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，现金流水数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，现金流水数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> record_idList){
	     
	     return cashRecordMapper.batchDeleteByKey(record_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> record_idList){
	    Dto outDto = Dtos.newDto();
	    int row=cashRecordMapper.batchDeleteByKey(record_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量现金流水数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量现金流水数据删除失败。");
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
	
	    return cashRecordMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return cashRecordMapper.calc(pDto);
	
	};
	
}
