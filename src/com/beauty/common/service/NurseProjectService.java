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
import com.beauty.common.mapper.NurseProjectMapper;
import com.beauty.common.po.NurseProjectPO;

/**
 * 
 * 类描述：<b>护理项目信息表[bis_nurse_project业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:28:47
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class NurseProjectService {
  
    @Autowired
	private  NurseProjectMapper nurseProjectMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param nurseProjectPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(NurseProjectPO nurseProjectPO){
	
	     return nurseProjectMapper.insert(nurseProjectPO);
	     
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
	   NurseProjectPO nurseProjectPO =new  NurseProjectPO();
	   nurseProjectPO.setProject_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  nurseProjectPO); 
	   nurseProjectPO.setIs_del(IMSCons.IS.NO);
	   nurseProjectPO.setCreate_time(IMSUtils.getDateTime());
	   nurseProjectPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   nurseProjectPO.setModify_time(IMSUtils.getDateTime());
	   nurseProjectPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=nurseProjectMapper.insert(nurseProjectPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，护理项目信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，护理项目信息新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param nurseProjectPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(NurseProjectPO nurseProjectPO){
	
	     return nurseProjectMapper.insertAll(nurseProjectPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param nurseProjectPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(NurseProjectPO nurseProjectPO){
	
	     return nurseProjectMapper.updateByKey(nurseProjectPO);
	
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
	   NurseProjectPO nurseProjectPO =new  NurseProjectPO();
	   IMSUtils.copyProperties(inDto,  nurseProjectPO); 
	   nurseProjectPO.setModify_time(IMSUtils.getDateTime());
	   nurseProjectPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=nurseProjectMapper.updateByKey(nurseProjectPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，护理项目信息修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，护理项目信息修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return NurseProjectPO
	 */
	public NurseProjectPO selectByKey( String project_id){
	
	    return nurseProjectMapper.selectByKey(project_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return NurseProjectPO
	 */
	public NurseProjectPO selectOne(Dto pDto){
	
	    return nurseProjectMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<NurseProjectPO>
	 */
	public List<NurseProjectPO> list(Dto pDto){
	
	     return nurseProjectMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<NurseProjectPO>
	 */
	public List<NurseProjectPO> listPage(Dto pDto){
	
	     return nurseProjectMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseProjectPO>
	 */
	public List<NurseProjectPO> like(Dto pDto){
	  
	      return nurseProjectMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseProjectPO>
	 */
	public List<NurseProjectPO> likePage(Dto pDto){
	
	    return nurseProjectMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String project_id){
	
	    return nurseProjectMapper.deleteByKey(project_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String project_id,String user_id){
	   Dto outDto = Dtos.newDto();
	   NurseProjectPO nurseProjectPO=new NurseProjectPO();
	   nurseProjectPO.setProject_id(project_id);
	   nurseProjectPO.setIs_del(IMSCons.IS.YES);
	   nurseProjectPO.setModify_time(IMSUtils.getDateTime());
	   nurseProjectPO.setModify_user_id(user_id);
	   int row=nurseProjectMapper.updateByKey(nurseProjectPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，护理项目信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，护理项目信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> project_idList){
	     
	     return nurseProjectMapper.batchDeleteByKey(project_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> project_idList){
	    Dto outDto = Dtos.newDto();
	    int row=nurseProjectMapper.batchDeleteByKey(project_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量护理项目信息表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量护理项目信息表数据删除失败。");
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
	
	    return nurseProjectMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return nurseProjectMapper.calc(pDto);
	
	};
	
}
