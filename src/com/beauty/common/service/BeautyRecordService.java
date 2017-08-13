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
import com.beauty.common.mapper.BeautyRecordMapper;
import com.beauty.common.po.BeautyRecordPO;

/**
 * 
 * 类描述：<b>颜值流水[bis_beauty_record业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-07 22:43:24
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class BeautyRecordService {
  
    @Autowired
	private  BeautyRecordMapper beautyRecordMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param beautyRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(BeautyRecordPO beautyRecordPO){
	
	     return beautyRecordMapper.insert(beautyRecordPO);
	     
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
	   BeautyRecordPO beautyRecordPO =new  BeautyRecordPO();
	   beautyRecordPO.setRecord_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  beautyRecordPO); 
	   int row=beautyRecordMapper.insert(beautyRecordPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，颜值流水数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，颜值流水数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param beautyRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(BeautyRecordPO beautyRecordPO){
	
	     return beautyRecordMapper.insertAll(beautyRecordPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param beautyRecordPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(BeautyRecordPO beautyRecordPO){
	
	     return beautyRecordMapper.updateByKey(beautyRecordPO);
	
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
	   BeautyRecordPO beautyRecordPO =new  BeautyRecordPO();
	   IMSUtils.copyProperties(inDto,  beautyRecordPO); 
	   int row=beautyRecordMapper.updateByKey(beautyRecordPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，颜值流水数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，颜值流水数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return BeautyRecordPO
	 */
	public BeautyRecordPO selectByKey( String record_id){
	
	    return beautyRecordMapper.selectByKey(record_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return BeautyRecordPO
	 */
	public BeautyRecordPO selectOne(Dto pDto){
	
	    return beautyRecordMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<BeautyRecordPO>
	 */
	public List<BeautyRecordPO> list(Dto pDto){
	
	     return beautyRecordMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<BeautyRecordPO>
	 */
	public List<BeautyRecordPO> listPage(Dto pDto){
	
	     return beautyRecordMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BeautyRecordPO>
	 */
	public List<BeautyRecordPO> like(Dto pDto){
	  
	      return beautyRecordMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BeautyRecordPO>
	 */
	public List<BeautyRecordPO> likePage(Dto pDto){
	
	    return beautyRecordMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String record_id){
	
	    return beautyRecordMapper.deleteByKey(record_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String record_id){
	   Dto outDto = Dtos.newDto();
	   int row=beautyRecordMapper.deleteByKey(record_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，颜值流水数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，颜值流水数据删除失败。");
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
	     
	     return beautyRecordMapper.batchDeleteByKey(record_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> record_idList){
	    Dto outDto = Dtos.newDto();
	    int row=beautyRecordMapper.batchDeleteByKey(record_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量颜值流水数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量颜值流水数据删除失败。");
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
	
	    return beautyRecordMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return beautyRecordMapper.calc(pDto);
	
	};
	
}
