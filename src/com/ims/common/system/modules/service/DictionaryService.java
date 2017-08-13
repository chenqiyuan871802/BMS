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
import com.ims.common.system.modules.mapper.DictionaryIndexMapper;
import com.ims.common.system.modules.mapper.DictionaryMapper;
import com.ims.common.system.modules.po.DictionaryIndexPO;
import com.ims.common.system.modules.po.DictionaryPO;
import com.ims.common.system.service.ResourceCacheService;

/**
 * 
 * 类描述：<b>数据字典对照[sys_dictionary业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-10-02 22:31:27
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class DictionaryService {
  
    @Autowired
	private  DictionaryMapper dictionaryMapper;
    @Autowired
	private  DictionaryIndexMapper dictionaryIndexMapper;
    @Autowired
  	private ResourceCacheService resourceCacheService;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param dictionaryPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(DictionaryPO dictionaryPO){
	
	     return dictionaryMapper.insert(dictionaryPO);
	     
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
	   String dic_index_id= inDto.getString("dic_index_id");
		countDto.put("dic_code", inDto.getString("dic_code"));
		countDto.put("dic_index_id",dic_index_id);
		int count=dictionaryMapper.rows(countDto);
		if(count>0){
			outDto.setAppMsg("当前字典标识下字典对照码已被占用，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   DictionaryPO dictionaryPO =new  DictionaryPO();
	   IMSUtils.copyProperties(inDto,  dictionaryPO); 
	   dictionaryPO.setDic_id(IMSId.uuid());
	   dictionaryPO.setCreate_time(IMSUtils.getDateTime());
	   dictionaryPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
	   dictionaryPO.setModify_time(IMSUtils.getDateTime());
	   dictionaryPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
	   int row=dictionaryMapper.insert(dictionaryPO);
	   if(row>0){
		   
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，数据字典对照数据新增成功。");
		   //清除缓存
		   DictionaryIndexPO dicIndexPO=dictionaryIndexMapper.selectByKey(dic_index_id);
		   resourceCacheService.cacheDic(dicIndexPO.getDic_key());
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，数据字典对照数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param dictionaryPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(DictionaryPO dictionaryPO){
	
	     return dictionaryMapper.insertAll(dictionaryPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param dictionaryPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(DictionaryPO dictionaryPO){
	
	     return dictionaryMapper.updateByKey(dictionaryPO);
	
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
	   String dic_code_old=inDto.getString("dic_code_old");
	   String dic_code=inDto.getString("dic_code");
	   String dic_index_id= inDto.getString("dic_index_id");
	   if(!dic_code_old.equals(dic_code)){ //如果旧的字典对照码跟新的字典对照码不一致，则要验证字典对照码是否被占用
		   Dto countDto = Dtos.newDto();
			countDto.put("dic_code", inDto.getString("dic_code"));
			countDto.put("dic_index_id",dic_index_id);
			int count=dictionaryMapper.rows(countDto);
			if(count>0){
				outDto.setAppMsg("当前字典标识下字典对照码已被占用，请修改后再保存。");
				outDto.setAppCode(IMSCons.WARN);
				return outDto;
		   }
	   }
	  
	   DictionaryPO dictionaryPO =new  DictionaryPO();
	   IMSUtils.copyProperties(inDto,  dictionaryPO); 
	   dictionaryPO.setModify_time(IMSUtils.getDateTime());
	   dictionaryPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
	   int row=dictionaryMapper.updateByKey(dictionaryPO);
	   if(row>0){
		  
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，数据字典对照数据修改成功。");
		 //更新缓存
		   DictionaryIndexPO dicIndexPO=dictionaryIndexMapper.selectByKey(dic_index_id);
		   resourceCacheService.cacheDic(dicIndexPO.getDic_key());
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，数据字典对照数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return DictionaryPO
	 */
	public DictionaryPO selectByKey( String dic_id){
	
	    return dictionaryMapper.selectByKey(dic_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return DictionaryPO
	 */
	public DictionaryPO selectOne(Dto pDto){
	
	    return dictionaryMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<DictionaryPO>
	 */
	public List<DictionaryPO> list(Dto pDto){
	
	     return dictionaryMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<DictionaryPO>
	 */
	public List<DictionaryPO> listPage(Dto pDto){
	
	     return dictionaryMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DictionaryPO>
	 */
	public List<DictionaryPO> like(Dto pDto){
	  
	      return dictionaryMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DictionaryPO>
	 */
	public List<DictionaryPO> likePage(Dto pDto){
	
	    return dictionaryMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String dic_id){
	
	    return dictionaryMapper.deleteByKey(dic_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String dic_id){
	   Dto outDto = Dtos.newDto();
	   int row=dictionaryMapper.deleteByKey(dic_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，数据字典对照数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，数据字典对照数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> dic_idList){
	     
	     return dictionaryMapper.batchDeleteByKey(dic_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> dic_idList){
	    Dto outDto = Dtos.newDto();
	    String dic_key="";
	    if(dic_idList.size()>0){
	    	String dic_id=dic_idList.get(0);
	    	DictionaryPO dicPO=dictionaryMapper.selectByKey(dic_id);
	    	DictionaryIndexPO dicIndexPO=dictionaryIndexMapper.selectByKey(dicPO.getDic_index_id());
	    	dic_key=dicIndexPO.getDic_key();
	    	
	    }
	    
	    int row=dictionaryMapper.batchDeleteByKey(dic_idList);
	    if(row>0){
	    	
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，数据字典对照数据删除成功。");
		   //先清除在刷新
		   resourceCacheService.cacheDic(dic_key);
		   
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，数据字典对照数据删除失败。");
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
	
	    return dictionaryMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return dictionaryMapper.calc(pDto);
	
	};
	
}
