package com.ims.common.system.modules.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.mapper.CatalogMapper;
import com.ims.common.system.modules.mapper.ParamMapper;
import com.ims.common.system.modules.po.CatalogPO;
import com.ims.common.system.modules.po.ParamPO;
import com.ims.common.system.service.ResourceCacheService;

/**
 * 
 * 类描述：<b>键值参数[sys_param业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-25 10:21:40
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class ParamService {
  
    @Autowired
	private  ParamMapper paramMapper;
    @Autowired
    private  CatalogMapper catalogMapper;
    @Autowired
	private ResourceCacheService resourceCacheService;
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param paramPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(ParamPO paramPO){
		int row=paramMapper.insert(paramPO);
		if(row>0){
			resourceCacheService.cacheParamOption(paramPO.getParam_key());
		}
		return row;
	     
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
	   Dto countDto = Dtos.newDto();
	   String param_key=inDto.getString("param_key");
		countDto.put("param_key", param_key);
		int count=paramMapper.rows(countDto);
		if(count>0){
			outDto.setAppMsg("参数键已被占用，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   ParamPO paramPO =new  ParamPO();
	   IMSUtils.copyProperties(inDto,  paramPO); 
	   paramPO.setParam_id(IMSId.uuid());
	   paramPO.setCreate_time(IMSUtils.getDateTime());
	   paramPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   paramPO.setModify_time(IMSUtils.getDateTime());
	   paramPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   CatalogPO catalogPO=catalogMapper.selectByKey(paramPO.getCatalog_id());
	   paramPO.setCatalog_cascade_id(catalogPO.getCascade_id()); //插入语义ID
	   int row=paramMapper.insert(paramPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，键值参数数据新增成功。");
		   //缓存键值参数
		   resourceCacheService.cacheParamOption(param_key);
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，键值参数数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param paramPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(ParamPO paramPO){
	
	     return paramMapper.insertAll(paramPO);
	     
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
	   String param_key_old=inDto.getString("param_key_old");
	   String param_key=inDto.getString("param_key");
	   if(!param_key_old.equals(param_key)){  //如果旧的参数键跟新的参数键不一致，则要验证参数键是否被占用
		   Dto countDto = Dtos.newDto();
			countDto.put("param_key", inDto.getString("param_key"));
			int count=paramMapper.rows(countDto);
			if(count>0){
				
				outDto.setAppMsg("参数键已被占用，请修改后再保存。");
				outDto.setAppCode(IMSCons.WARN);
				return outDto;
		   }
	   }
		  
	   ParamPO paramPO =new  ParamPO();
	   IMSUtils.copyProperties(inDto,  paramPO); 
	   CatalogPO catalogPO=catalogMapper.selectByKey(paramPO.getCatalog_id());
	   paramPO.setCatalog_cascade_id(catalogPO.getCascade_id()); //插入语义ID
	   paramPO.setModify_time(IMSUtils.getDateTime());
	   paramPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=paramMapper.updateByKey(paramPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，键值参数数据修改成功。");
		   //清除旧的缓存
		   resourceCacheService.deleteCacheParam(param_key_old);
		   //插入新的缓存
		   resourceCacheService.cacheParamOption(param_key);
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，键值参数数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param inDto传入参数
	 *            要修改的数据持久化对象
	 * @return Dto 返回影Dto对象
	 */
	@Transactional
	public boolean update(String param_key,String param_value,String user_id){
	    Dto pDto=Dtos.newDto();
	    pDto.put("param_key", param_key);
		ParamPO paramPO =paramMapper.selectOne(pDto);
		paramPO.setParam_key(param_key);
		paramPO.setParam_value(param_value);
		paramPO.setModify_time(IMSUtils.getDateTime());
		paramPO.setModify_user_id(user_id);
		int row=paramMapper.updateByKey(paramPO);
		if(row>0){
			resourceCacheService.deleteCacheParam(param_key);
			//插入新的缓存
			resourceCacheService.cacheParamOption(param_key);
			return true;
		}else{
			return false;
		}
		
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ParamPO
	 */
	public ParamPO selectByKey( String param_id){
	
	    return paramMapper.selectByKey(param_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ParamPO
	 */
	public ParamPO selectOne(Dto pDto){
	
	    return paramMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ParamPO>
	 */
	public List<ParamPO> list(Dto pDto){
	
	     return paramMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ParamPO>
	 */
	public List<ParamPO> listPage(Dto pDto){
	
	     return paramMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ParamPO>
	 */
	public List<ParamPO> like(Dto pDto){
	  
	      return paramMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ParamPO>
	 */
	public List<ParamPO> likePage(Dto pDto){
	
	    return paramMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
    public int  deleteByKey( String param_id){
	
	    return paramMapper.deleteByKey(param_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String param_id){
	   Dto outDto = Dtos.newDto();
	   int row=paramMapper.deleteByKey(param_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，键值参数数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，键值参数数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> param_idList){
	     
	     return paramMapper.batchDeleteByKey(param_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> param_idList){
	    Dto outDto = Dtos.newDto();
	    Dto pDto=Dtos.newDto("param_idList",param_idList);
	    List<ParamPO> paramList=resourceCacheService.getParams(pDto);
	    int row=paramMapper.batchDeleteByKey(param_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，删除键值参数数据成功。");
		   for(ParamPO paramPO:paramList){  //清除缓存
			   resourceCacheService.deleteCacheParam(paramPO.getParam_key());
		   }
		   //清除缓存键值参数
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，删除键值参数数据失败。");
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
	
	    return paramMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return paramMapper.calc(pDto);
	
	};
	
}
