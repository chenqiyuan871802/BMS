package com.ims.common.system.modules.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.mapper.CatalogMapper;
import com.ims.common.system.modules.mapper.DictionaryIndexMapper;
import com.ims.common.system.modules.mapper.DictionaryMapper;
import com.ims.common.system.modules.po.CatalogPO;
import com.ims.common.system.modules.po.DictionaryIndexPO;
import com.ims.common.system.modules.po.DictionaryPO;
import com.ims.common.system.service.ResourceCacheService;

/**
 * 
 * 类描述：<b>数据字典索引表[sys_dictionary_index业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-10-02 22:26:06
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class DictionaryIndexService {
  
    @Autowired
	private  DictionaryIndexMapper dictionaryIndexMapper;
    @Autowired
    private  DictionaryMapper dictionaryMapper;
    @Autowired
    private  CatalogMapper catalogMapper;
    @Autowired
  	private ResourceCacheService resourceCacheService;
	
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param dictionaryIndexPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(DictionaryIndexPO dictionaryIndexPO){
	
	     return dictionaryIndexMapper.insert(dictionaryIndexPO);
	     
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
		countDto.put("dic_key", inDto.getString("dic_key"));
		int count=dictionaryIndexMapper.rows(countDto);
		if(count>0){
			outDto.setAppMsg("字典标识已被占用，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   DictionaryIndexPO dictionaryIndexPO =new  DictionaryIndexPO();
	   IMSUtils.copyProperties(inDto,  dictionaryIndexPO); 
	   dictionaryIndexPO.setDic_index_id(IMSId.uuid());
	   dictionaryIndexPO.setCreate_time(IMSUtils.getDateTime());
	   dictionaryIndexPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
	   dictionaryIndexPO.setModify_time(IMSUtils.getDateTime());
	   dictionaryIndexPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
	   CatalogPO catalogPO=catalogMapper.selectByKey(dictionaryIndexPO.getCatalog_id());
	   dictionaryIndexPO.setCatalog_cascade_id(catalogPO.getCascade_id()); //插入语义ID
	   int row=dictionaryIndexMapper.insert(dictionaryIndexPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，字典数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，字典数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param dictionaryIndexPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(DictionaryIndexPO dictionaryIndexPO){
	
	     return dictionaryIndexMapper.insertAll(dictionaryIndexPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param dictionaryIndexPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(DictionaryIndexPO dictionaryIndexPO){
	
	     return dictionaryIndexMapper.updateByKey(dictionaryIndexPO);
	
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
	   Dto countDto = Dtos.newDto();
	   String dic_key_old=inDto.getString("dic_key_old");
	   String dic_key=inDto.getString("dic_key");
	   if(!dic_key_old.equals(dic_key)){  //如果旧的字典标识键跟新的字典标识不一致，则要验证字典标识是否被占用
			countDto.put("dic_key", inDto.getString("dic_key"));
			int count=dictionaryIndexMapper.rows(countDto);
			if(count>0){
				outDto.setAppMsg("字典标识已被占用，请修改后再保存。");
				outDto.setAppCode(IMSCons.WARN);
				return outDto;
		   }
	   }
	   DictionaryIndexPO dictionaryIndexPO =new  DictionaryIndexPO();
	   IMSUtils.copyProperties(inDto,  dictionaryIndexPO); 
	   dictionaryIndexPO.setModify_time(IMSUtils.getDateTime());
	   CatalogPO catalogPO=catalogMapper.selectByKey(dictionaryIndexPO.getCatalog_id());
	   dictionaryIndexPO.setCatalog_cascade_id(catalogPO.getCascade_id()); //插入语义ID
	   int row=dictionaryIndexMapper.updateByKey(dictionaryIndexPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，字典数据修改成功。");
		  
		 //缓存新的数据字典
		   resourceCacheService.cacheDic(dic_key);
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，字典数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return DictionaryIndexPO
	 */
	public DictionaryIndexPO selectByKey( String dic_index_id){
	
	    return dictionaryIndexMapper.selectByKey(dic_index_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return DictionaryIndexPO
	 */
	public DictionaryIndexPO selectOne(Dto pDto){
	
	    return dictionaryIndexMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<DictionaryIndexPO>
	 */
	public List<DictionaryIndexPO> list(Dto pDto){
	
	     return dictionaryIndexMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<DictionaryIndexPO>
	 */
	public List<DictionaryIndexPO> listPage(Dto pDto){
	
	     return dictionaryIndexMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DictionaryIndexPO>
	 */
	public List<DictionaryIndexPO> like(Dto pDto){
	  
	      return dictionaryIndexMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DictionaryIndexPO>
	 */
	public List<DictionaryIndexPO> likePage(Dto pDto){
	
	    return dictionaryIndexMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String dic_index_id){
	
	    return dictionaryIndexMapper.deleteByKey(dic_index_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String dic_index_id){
	   Dto outDto = Dtos.newDto();
	   Dto countDto =Dtos.newDto();
	   countDto.put("dic_index_id", dic_index_id);
	   countDto.put("edit_mode", DicCons.EDITMODE_READ);
	   int count=dictionaryMapper.rows(countDto);
	   if(count>0){
		   outDto.setAppMsg("当前字典数据下存在只读的字典对照数据，只读的数据不允许修改和删除。");
		   outDto.setAppCode(IMSCons.WARN);
		   return outDto;
	   }
	   Dto pDto=Dtos.newDto();
	   pDto.put("dic_index_id", dic_index_id);
	   List<DictionaryPO> dictionaryPOList=dictionaryMapper.list(pDto);
	   for(DictionaryPO dictionaryPO:dictionaryPOList){
		   dictionaryMapper.deleteByKey(dictionaryPO.getDic_id());
	   }
	   DictionaryIndexPO dicIndexPO=dictionaryIndexMapper.selectByKey(dic_index_id);
 	   int row=dictionaryIndexMapper.deleteByKey(dic_index_id);
	   if(row>0){
		 
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，字典数据删除成功。");
		   //清除缓存
		   resourceCacheService.deleteCacheDic(dicIndexPO.getDic_key());
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，字典数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> dic_index_idList){
	     
	     return dictionaryIndexMapper.batchDeleteByKey(dic_index_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> dic_index_idList){
	    Dto outDto = Dtos.newDto();
	    int row=dictionaryIndexMapper.batchDeleteByKey(dic_index_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量数据字典索引表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量数据字典索引表数据删除失败。");
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
	
	    return dictionaryIndexMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return dictionaryIndexMapper.calc(pDto);
	
	};
	
}
