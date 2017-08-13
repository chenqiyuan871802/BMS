package com.ims.common.system.modules.service;

import java.util.ArrayList;
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
import com.ims.common.system.modules.po.CatalogPO;
import com.ims.common.system.modules.po.TreeModel;

/**
 * 
 * 类描述：<b>分类科目[sys_catalog业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-09-25 09:25:54
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class CatalogService {
  
    @Autowired
	private  CatalogMapper catalogMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param catalogPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(CatalogPO catalogPO){
	
	     return catalogMapper.insert(catalogPO);
	     
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
		String flag=inDto.getString("flag");
		if ("1".equals(flag)) {
			Dto countDto = Dtos.newDto();
			countDto.put("root_key", inDto.getString("root_key"));
			int count=catalogMapper.rows(countDto);
			if(count>0){
				outDto.setAppMsg("科目标识键已被占用，请修改后再保存。");
				outDto.setAppCode(IMSCons.WARN);
				return outDto;
			}
		}
		
		CatalogPO catalogPO =new  CatalogPO();
		IMSUtils.copyProperties(inDto,  catalogPO); 
		catalogPO.setCatalog_id(IMSId.uuid());//添加科目ID
		catalogPO.setCreate_time(IMSUtils.getDateTime());
		catalogPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
		catalogPO.setModify_time(IMSUtils.getDateTime());
		catalogPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
		Dto calcDto = Dtos.newCalcDto("MAX(cascade_id)");
		calcDto.put("parent_id", catalogPO.getParent_id());
		String maxCascadeId = catalogMapper.calc(calcDto);
		if(IMSUtils.isEmpty(maxCascadeId)){
			CatalogPO parentCatalogPO=catalogMapper.selectByKey(catalogPO.getParent_id());
			if(IMSUtils.isEmpty(parentCatalogPO)){
				maxCascadeId="0.000";
			}else{
				maxCascadeId=parentCatalogPO.getCascade_id()+".000";
			}
			
		}
		String cascade_id=IMSId.treeId(maxCascadeId,999);
		catalogPO.setCascade_id(cascade_id);
		int row=catalogMapper.insert(catalogPO);
		if(row>0){
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，分类科目数据新增成功。");
		}else{
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，分类科目数据新增失败。");
		}
		return outDto;
		
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param catalogPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(CatalogPO catalogPO){
	
	     return catalogMapper.insertAll(catalogPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param catalogPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(CatalogPO catalogPO){
	
	     return catalogMapper.updateByKey(catalogPO);
	
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
	   CatalogPO catalogPO =new  CatalogPO();
	   IMSUtils.copyProperties(inDto,  catalogPO); 
	   catalogPO.setModify_time(IMSUtils.getDateTime());
	   catalogPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));	
	   int row=catalogMapper.updateByKey(catalogPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，分类科目数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，分类科目数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return CatalogPO
	 */
	public CatalogPO selectByKey( String catalog_id){
	
	    return catalogMapper.selectByKey(catalog_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return CatalogPO
	 */
	public CatalogPO selectOne(Dto pDto){
	
	    return catalogMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<CatalogPO>
	 */
	public List<CatalogPO> list(Dto pDto){
	
	     return catalogMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<CatalogPO>
	 */
	public List<CatalogPO> listPage(Dto pDto){
	
	     return catalogMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CatalogPO>
	 */
	public List<CatalogPO> like(Dto pDto){
	  
	      return catalogMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CatalogPO>
	 */
	public List<CatalogPO> likePage(Dto pDto){
	
	    return catalogMapper.likePage( pDto);
	
	};
	/**
	 * 查询分类科目信息并转化为树网格节点数据
	 * @param pDto
	 * @return
	 */
	public List<Dto> listCatalog(Dto pDto){
		pDto.setOrder("cascade_id ASC,sort_no ASC ");
		List<CatalogPO> catalogPOList=catalogMapper.list(pDto);
		List<Dto> treeNodes = new ArrayList<Dto>();
		for(int i=0;i<catalogPOList.size();i++){
			CatalogPO catalogPO=catalogPOList.get(i);
			int child_count=catalogPO.getChild_count();
			Dto treeNode = Dtos.newDto();
			IMSUtils.copyProperties(catalogPO, treeNode);
			treeNode.put("create_time",IMSUtils.date2String(catalogPO.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
			treeNode.put("modify_time",IMSUtils.date2String(catalogPO.getModify_time(), "yyyy-MM-dd HH:mm:ss"));
			String icon_name=catalogPO.getIcon_name();
			if (IMSUtils.isNotEmpty(icon_name)) {
				treeNode.put("iconCls", icon_name);
			}else{
				if(child_count==0){  //子节点替换子节点图标
					treeNode.put("iconCls", IMSCons.TREE_LEAF_INCONCLS);
				}
			}
			String is_auto_expand = catalogPO.getIs_auto_expand();
			if(child_count>0){  //只有父节点才有闭合功能
				if(IMSCons.IS.NO.equals(is_auto_expand)){
					treeNode.put("state",IMSCons.TREE_STATE_CLOSED);
				}
			}
			if(!IMSCons.TREE_ROOT_ID.equals( catalogPO.getParent_id())){  //不等于0的时候添加父类ID因为根节点不需要这个父类ID
				treeNode.put("_parentId", catalogPO.getParent_id());  //添加父类ID
			}
		
			treeNodes.add(treeNode);
		}
		
		return treeNodes;
	}
	/**
	 * 加载分类科目树
	 * @param pDto
	 * @return
	 */
	public List<TreeModel> loadCatalogTree(Dto pDto){
		pDto.setOrder("cascade_id ASC,sort_no ASC ");
		List<CatalogPO> catalogPOList=catalogMapper.list(pDto);
		TreeModel rootModel=new TreeModel();
		rootModel.setId(IMSCons.TREE_ROOT_ID);
		for(int i=0;i<catalogPOList.size();i++){
			CatalogPO catalogPO=catalogPOList.get(i);
			int child_count=catalogPO.getChild_count();
			String parent_id=catalogPO.getParent_id();
			String icon_name=catalogPO.getIcon_name();
			TreeModel treeModel=new TreeModel();
			treeModel.setId(catalogPO.getCatalog_id());
			treeModel.setParentId(parent_id);
			treeModel.setCascade_id(catalogPO.getCascade_id());
			if(IMSCons.TREE_ROOT_ID.equals(parent_id)){
				treeModel.setText(IMSCons.TREE_ROOT_NAME);
			}else{
				treeModel.setText(catalogPO.getCatalog_name());
			}
			if (IMSUtils.isNotEmpty(icon_name)) {
				treeModel.setIconCls(icon_name);
			}else{
				if(child_count==0){  //子节点替换子节点图标
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}
			}
			String is_auto_expand = catalogPO.getIs_auto_expand();
			if(child_count>0){  //只有父节点才有闭合功能
				if(IMSCons.IS.NO.equals(is_auto_expand)){
					if(!IMSCons.TREE_ROOT_ID.equals(parent_id)){ //根节点展开
						treeModel.setState(IMSCons.TREE_STATE_CLOSED);
					}
					
				}
			}
			rootModel.add(treeModel);
		}
		List<TreeModel> treeModelList=rootModel.getChildren();
		return treeModelList;
			  
	}
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
    public int  deleteByKey( String catalog_id){
	    
	    return catalogMapper.deleteByKey(catalog_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String catalog_id){
	   Dto outDto = Dtos.newDto();
	   Dto pDto=Dtos.newDto();
	   pDto.put("parent_id",catalog_id) ;
	   int count=catalogMapper.rows(pDto);
	   if(count>0){
		   outDto.setAppCode(IMSCons.WARN);
		   outDto.setAppMsg("操作失败，分类科目存在子科目，不允许删除，请先删除子科目然后再删除。");
		   
	   }else{
		   int row=catalogMapper.deleteByKey(catalog_id);
		   if(row>0){
			   outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("操作完成，分类科目数据删除成功。");
		   }else{
			   outDto.setAppCode(IMSCons.ERROR);
			   outDto.setAppMsg("操作失败，分类科目数据删除失败。");
		   }
	   }
	  
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> catalog_idList){
	     
	     return catalogMapper.batchDeleteByKey(catalog_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> catalog_idList){
	    Dto outDto = Dtos.newDto();
	    int row=catalogMapper.batchDeleteByKey(catalog_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量分类科目数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量分类科目数据删除失败。");
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
	
	    return catalogMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return catalogMapper.calc(pDto);
	
	};
	
}
