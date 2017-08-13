package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.mapper.OpinionMapper;
import com.beauty.common.po.OpinionPO;

/**
 * 
 * 类描述：<b>返回意见[bis_opinion业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-06-17 11:27:21
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class OpinionService {
  
    @Autowired
	private  OpinionMapper opinionMapper;
    @Autowired
    private  SqlDao sqlDao;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param opinionPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(OpinionPO opinionPO){
	
	     return opinionMapper.insert(opinionPO);
	     
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
	   OpinionPO opinionPO =new  OpinionPO();
	   opinionPO.setOpinion_id(IMSId.appId());
	   IMSUtils.copyProperties(inDto,  opinionPO); 
	   opinionPO.setCreate_time(IMSUtils.getDateTime());
	   int row=opinionMapper.insert(opinionPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("反馈意见成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("反馈意见失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param opinionPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(OpinionPO opinionPO){
	
	     return opinionMapper.insertAll(opinionPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param opinionPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(OpinionPO opinionPO){
	
	     return opinionMapper.updateByKey(opinionPO);
	
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
	   OpinionPO opinionPO =new  OpinionPO();
	   IMSUtils.copyProperties(inDto,  opinionPO); 
	   int row=opinionMapper.updateByKey(opinionPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，返回意见数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，返回意见数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return OpinionPO
	 */
	public OpinionPO selectByKey( String opinion_id){
	
	    return opinionMapper.selectByKey(opinion_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return OpinionPO
	 */
	public OpinionPO selectOne(Dto pDto){
	
	    return opinionMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<OpinionPO>
	 */
	public List<OpinionPO> list(Dto pDto){
	
	     return opinionMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<OpinionPO>
	 */
	public List<OpinionPO> listPage(Dto pDto){
	
	     return opinionMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OpinionPO>
	 */
	public List<OpinionPO> like(Dto pDto){
	  
	      return opinionMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OpinionPO>
	 */
	public List<OpinionPO> likePage(Dto pDto){
	
	    return opinionMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OpinionPO>
	 */
	public List<Dto> listOpinionPage(Dto pDto){
		
		return sqlDao.list("ShopCommonMapper.listOpinionPage", pDto);
		
	};
	
	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<OpinionPO>
	 */
	public Dto queryOpinionDetail(String opinion_id){
		
		return sqlDao.selectDto("ShopCommonMapper.queryOpinionDetail", opinion_id);
		
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String opinion_id){
	
	    return opinionMapper.deleteByKey(opinion_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String opinion_id){
	   Dto outDto = Dtos.newDto();
	   int row=opinionMapper.deleteByKey(opinion_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，返回意见数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，返回意见数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> opinion_idList){
	     
	     return opinionMapper.batchDeleteByKey(opinion_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> opinion_idList){
	    Dto outDto = Dtos.newDto();
	    int row=opinionMapper.batchDeleteByKey(opinion_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量删除意见数据成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量删除意见数据失败。");
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
	
	    return opinionMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return opinionMapper.calc(pDto);
	
	};
	
}
