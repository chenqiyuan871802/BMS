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
import com.beauty.common.mapper.BeautyConfigMapper;
import com.beauty.common.po.BeautyConfigPO;

/**
 * 
 * 类描述：<b>颜值配置[bis_beauty_config业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-26 21:46:53
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class BeautyConfigService {
  
    @Autowired
	private  BeautyConfigMapper beautyConfigMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param beautyConfigPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(BeautyConfigPO beautyConfigPO){
	
	     return beautyConfigMapper.insert(beautyConfigPO);
	     
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
	   BeautyConfigPO beautyConfigPO =new  BeautyConfigPO();
	   
	   IMSUtils.copyProperties(inDto,  beautyConfigPO); 
	   beautyConfigPO.setConfig_id(IMSId.appId());
	   beautyConfigPO.setIs_del(IMSCons.IS.NO);
	   beautyConfigPO.setCreate_time(IMSUtils.getDateTime());
	   beautyConfigPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   beautyConfigPO.setModify_time(IMSUtils.getDateTime());
	   beautyConfigPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=beautyConfigMapper.insert(beautyConfigPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，颜值基本设置信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，颜值基本设置信息新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param beautyConfigPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(BeautyConfigPO beautyConfigPO){
	
	     return beautyConfigMapper.insertAll(beautyConfigPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param beautyConfigPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(BeautyConfigPO beautyConfigPO){
	
	     return beautyConfigMapper.updateByKey(beautyConfigPO);
	
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
	   BeautyConfigPO beautyConfigPO =new  BeautyConfigPO();
	   IMSUtils.copyProperties(inDto,  beautyConfigPO); 
	   beautyConfigPO.setModify_time(IMSUtils.getDateTime());
	   beautyConfigPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=beautyConfigMapper.updateByKey(beautyConfigPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，颜值基本设置信息修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，颜值基本设置信息修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return BeautyConfigPO
	 */
	public BeautyConfigPO selectByKey( String config_id){
	
	    return beautyConfigMapper.selectByKey(config_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return BeautyConfigPO
	 */
	public BeautyConfigPO selectOne(Dto pDto){
	
	    return beautyConfigMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<BeautyConfigPO>
	 */
	public List<BeautyConfigPO> list(Dto pDto){
	
	     return beautyConfigMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<BeautyConfigPO>
	 */
	public List<BeautyConfigPO> listPage(Dto pDto){
	
	     return beautyConfigMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BeautyConfigPO>
	 */
	public List<BeautyConfigPO> like(Dto pDto){
	  
	      return beautyConfigMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<BeautyConfigPO>
	 */
	public List<BeautyConfigPO> likePage(Dto pDto){
	
	    return beautyConfigMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String config_id){
	
	    return beautyConfigMapper.deleteByKey(config_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String config_id,String user_id){
	   Dto outDto = Dtos.newDto();
	   BeautyConfigPO beautyConfigPO =new  BeautyConfigPO();
	   beautyConfigPO.setConfig_id(config_id);
	   beautyConfigPO.setIs_del(IMSCons.IS.YES);
	   beautyConfigPO.setModify_time(IMSUtils.getDateTime());
	   beautyConfigPO.setModify_user_id(user_id);
	   int row=beautyConfigMapper.updateByKey(beautyConfigPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，颜值基本设置信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，颜值基本设置信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> config_idList){
	     
	     return beautyConfigMapper.batchDeleteByKey(config_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> config_idList){
	    Dto outDto = Dtos.newDto();
	    int row=beautyConfigMapper.batchDeleteByKey(config_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量颜值配置数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量颜值配置数据删除失败。");
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
	
	    return beautyConfigMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return beautyConfigMapper.calc(pDto);
	
	};
	
}
