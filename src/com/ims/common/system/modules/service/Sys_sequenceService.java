package com.ims.common.system.modules.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.mapper.Sys_sequenceMapper;
import com.ims.common.system.modules.po.Sys_sequencePO;

/**
 * 
 * 类描述：<b>ID配置表[sys_sequence业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-24 00:39:05
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class Sys_sequenceService {
  
    @Autowired
	private  Sys_sequenceMapper sys_sequenceMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param sys_sequencePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(Sys_sequencePO sys_sequencePO){
	
	     return sys_sequenceMapper.insert(sys_sequencePO);
	     
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
	   Sys_sequencePO sys_sequencePO =new  Sys_sequencePO();
	   IMSUtils.copyProperties(inDto,  sys_sequencePO); 
	   int row=sys_sequenceMapper.insert(sys_sequencePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，ID配置表数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，ID配置表数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param sys_sequencePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(Sys_sequencePO sys_sequencePO){
	
	     return sys_sequenceMapper.insertAll(sys_sequencePO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param sys_sequencePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(Sys_sequencePO sys_sequencePO){
	
	     return sys_sequenceMapper.updateByKey(sys_sequencePO);
	
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
	   Sys_sequencePO sys_sequencePO =new  Sys_sequencePO();
	   IMSUtils.copyProperties(inDto,  sys_sequencePO); 
	   int row=sys_sequenceMapper.updateByKey(sys_sequencePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，ID配置表数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，ID配置表数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return Sys_sequencePO
	 */
	public Sys_sequencePO selectByKey( String seq_id){
	
	    return sys_sequenceMapper.selectByKey(seq_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return Sys_sequencePO
	 */
	public Sys_sequencePO selectOne(Dto pDto){
	
	    return sys_sequenceMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<Sys_sequencePO>
	 */
	public List<Sys_sequencePO> list(Dto pDto){
	
	     return sys_sequenceMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<Sys_sequencePO>
	 */
	public List<Sys_sequencePO> listPage(Dto pDto){
	
	     return sys_sequenceMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<Sys_sequencePO>
	 */
	public List<Sys_sequencePO> like(Dto pDto){
	  
	      return sys_sequenceMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<Sys_sequencePO>
	 */
	public List<Sys_sequencePO> likePage(Dto pDto){
	
	    return sys_sequenceMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
    public int  deleteByKey( String seq_id){
	
	    return sys_sequenceMapper.deleteByKey(seq_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String seq_id){
	   Dto outDto = Dtos.newDto();
	   int row=sys_sequenceMapper.deleteByKey(seq_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，ID配置表数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，ID配置表数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> seq_idList){
	     
	     return sys_sequenceMapper.batchDeleteByKey(seq_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> seq_idList){
	    Dto outDto = Dtos.newDto();
	    int row=sys_sequenceMapper.batchDeleteByKey(seq_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量ID配置表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量ID配置表数据删除失败。");
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
	
	    return sys_sequenceMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return sys_sequenceMapper.calc(pDto);
	
	};
	
}
