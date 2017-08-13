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
import com.beauty.common.mapper.NurseTypeMapper;
import com.beauty.common.po.NurseTypePO;

/**
 * 
 * 类描述：<b>护理类型信息表[bis_nurse_type业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:04:13
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class NurseTypeService {
  
    @Autowired
	private  NurseTypeMapper nurseTypeMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param nurseTypePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(NurseTypePO nurseTypePO){
	
	     return nurseTypeMapper.insert(nurseTypePO);
	     
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
	   NurseTypePO nurseTypePO =new  NurseTypePO();
	   nurseTypePO.setType_id(IMSId.appId());
	   IMSUtils.copyProperties(inDto,  nurseTypePO); 
	   nurseTypePO.setCreate_time(IMSUtils.getDateTime());
	   nurseTypePO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   nurseTypePO.setModify_time(IMSUtils.getDateTime());
	   nurseTypePO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=nurseTypeMapper.insert(nurseTypePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，分类信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，分类信息新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param nurseTypePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(NurseTypePO nurseTypePO){
	
	     return nurseTypeMapper.insertAll(nurseTypePO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param nurseTypePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(NurseTypePO nurseTypePO){
	
	     return nurseTypeMapper.updateByKey(nurseTypePO);
	
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
	   NurseTypePO nurseTypePO =new  NurseTypePO();
	   IMSUtils.copyProperties(inDto,  nurseTypePO); 
	   nurseTypePO.setModify_time(IMSUtils.getDateTime());
	   nurseTypePO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=nurseTypeMapper.updateByKey(nurseTypePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成， 分类信息修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，分类信息修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return NurseTypePO
	 */
	public NurseTypePO selectByKey( String type_id){
	
	    return nurseTypeMapper.selectByKey(type_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return NurseTypePO
	 */
	public NurseTypePO selectOne(Dto pDto){
	
	    return nurseTypeMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<NurseTypePO>
	 */
	public List<NurseTypePO> list(Dto pDto){
	
	     return nurseTypeMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<NurseTypePO>
	 */
	public List<NurseTypePO> listPage(Dto pDto){
	
	     return nurseTypeMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseTypePO>
	 */
	public List<NurseTypePO> like(Dto pDto){
	  
	      return nurseTypeMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseTypePO>
	 */
	public List<NurseTypePO> likePage(Dto pDto){
	
	    return nurseTypeMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String type_id){
	
	    return nurseTypeMapper.deleteByKey(type_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String type_id){
	   Dto outDto = Dtos.newDto();
	   int row=nurseTypeMapper.deleteByKey(type_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，分类信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，分类删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> type_idList){
	     
	     return nurseTypeMapper.batchDeleteByKey(type_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> type_idList){
	    Dto outDto = Dtos.newDto();
	    int row=nurseTypeMapper.batchDeleteByKey(type_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量护理类型信息表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量护理类型信息表数据删除失败。");
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
	
	    return nurseTypeMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return nurseTypeMapper.calc(pDto);
	
	};
	
}
