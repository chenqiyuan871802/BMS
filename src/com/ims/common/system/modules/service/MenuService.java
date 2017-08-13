package com.ims.common.system.modules.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.plugin.DBType;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.mapper.MenuMapper;
import com.ims.common.system.modules.po.MenuPO;
import com.ims.common.system.modules.po.TreeModel;

/**
 * 
 * 类描述：<b>菜单配置[sys_menu业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-10-07 16:06:01
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class MenuService {
  
    @Autowired
	private  MenuMapper menuMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param menuPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(MenuPO menuPO){
	
	     return menuMapper.insert(menuPO);
	     
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
	   MenuPO menuPO =new  MenuPO();
	   IMSUtils.copyProperties(inDto,  menuPO); 
	   menuPO.setMenu_id(IMSId.uuid());
       menuPO.setCreate_time(IMSUtils.getDateTime());
       menuPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
       menuPO.setModify_time(IMSUtils.getDateTime());
       menuPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
       Dto calcDto = Dtos.newCalcDto("MAX(cascade_id)");
		calcDto.put("parent_id", menuPO.getParent_id());
		String maxCascadeId =menuMapper.calc(calcDto);
		if(IMSUtils.isEmpty(maxCascadeId)){
			MenuPO parentMenuPO=menuMapper.selectByKey(menuPO.getParent_id());
			if(IMSUtils.isEmpty(parentMenuPO)){
				maxCascadeId="0.000";
			}else{
				maxCascadeId=parentMenuPO.getCascade_id()+".000";
			}
			
		}
		String cascade_id=IMSId.treeId(maxCascadeId,999);
		menuPO.setCascade_id(cascade_id);
	   int row=menuMapper.insert(menuPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，菜单配置数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，菜单配置数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param menuPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(MenuPO menuPO){
	
	     return menuMapper.insertAll(menuPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param menuPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(MenuPO menuPO){
	
	     return menuMapper.updateByKey(menuPO);
	
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
	   MenuPO menuPO =new  MenuPO();
	   IMSUtils.copyProperties(inDto,  menuPO); 
	   menuPO.setModify_time(IMSUtils.getDateTime());
	   menuPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=menuMapper.updateByKey(menuPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，菜单配置数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，菜单配置数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return MenuPO
	 */
	public MenuPO selectByKey( String menu_id){
	
	    return menuMapper.selectByKey(menu_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return MenuPO
	 */
	public MenuPO selectOne(Dto pDto){
	
	    return menuMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<MenuPO>
	 */
	public List<MenuPO> list(Dto pDto){
	
	     return menuMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<MenuPO>
	 */
	public List<MenuPO> listPage(Dto pDto){
	
	     return menuMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<MenuPO>
	 */
	public List<MenuPO> like(Dto pDto){
	  
	      return menuMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<MenuPO>
	 */
	public List<MenuPO> likePage(Dto pDto){
		pDto.setOrder("parent_id ASC,sort_no ASC ");
	    return menuMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String menu_id){
	
	    return menuMapper.deleteByKey(menu_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String menu_id){
	   Dto outDto = Dtos.newDto();
	   Dto pDto=Dtos.newDto();
	   pDto.put("parent_id",menu_id) ;
	   int count=menuMapper.rows(pDto);
	   if(count>0){
		   outDto.setAppCode(IMSCons.WARN);
		   outDto.setAppMsg("操作失败，当前菜单下存在子菜单，不允许删除，请先删除子菜单然后再删除。");
	   }else{
		   int row=menuMapper.deleteByKey(menu_id);
		   if(row>0){
			   outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("操作完成，菜单配置数据删除成功。");
		   }else{
			   outDto.setAppCode(IMSCons.ERROR);
			   outDto.setAppMsg("操作失败，菜单配置数据删除失败。");
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
	public int batchDeleteByKey(List<String> menu_idList){
	     
	     return menuMapper.batchDeleteByKey(menu_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> menu_idList){
	    Dto outDto = Dtos.newDto();
	    int row=menuMapper.batchDeleteByKey(menu_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量菜单配置数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量菜单配置数据删除失败。");
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
	
	    return menuMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return menuMapper.calc(pDto);
	
	};
	/**
	 * 加载菜单树
	 * @param pDto
	 * @return
	 */
	public TreeModel loadMenuTree(Dto pDto){
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			pDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			pDto.setOrder("LENGTH(cascade_id) ASC,sort_no ASC ");
		}
		List<MenuPO> menuPOList=menuMapper.list(pDto);
		TreeModel rootModel=new TreeModel();
		rootModel.setText(IMSCons.MENU_ROOT_NAME);
		rootModel.setId(IMSCons.TREE_ROOT_ID);
		rootModel.setIconCls(IMSCons.MENU_ROOT_ICONCLS);
		rootModel.setCascade_id(IMSCons.TREE_ROOT_CASCADE_ID);
		for(int i=0;i<menuPOList.size();i++){
			MenuPO menuPO=menuPOList.get(i);
			int child_count=menuPO.getChild_count();
			String parent_id=menuPO.getParent_id();
			String icon_name=menuPO.getIcon_name();
			TreeModel treeModel=new TreeModel();
			treeModel.setId(menuPO.getMenu_id());
			treeModel.setParentId(parent_id);
			treeModel.setCascade_id(menuPO.getCascade_id());
			treeModel.setText(menuPO.getMenu_name());
			if (IMSUtils.isNotEmpty(icon_name)) {
				if(icon_name.indexOf(".png")>-1){
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}else{
					treeModel.setIconCls(icon_name);
				}
			}else{
				if(child_count==0){  //子节点替换子节点图标
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}
			}
			String is_auto_expand = menuPO.getIs_auto_expand();
			if(child_count>0){  //只有父节点才有闭合功能
				if(IMSCons.IS.NO.equals(is_auto_expand)){
					if(!IMSCons.TREE_ROOT_ID.equals(parent_id)){
						treeModel.setState(IMSCons.TREE_STATE_CLOSED);
					}
					
				}
			}
			rootModel.add(treeModel);
		}
		return rootModel;
			  
	}
	/**
	 * 加载一级菜单
	 * @param pDto
	 * @return
	 */
	public TreeModel loadFirstMenuTree(Dto pDto){
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			pDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			pDto.setOrder("LENGTH(cascade_id) ASC,sort_no ASC ");
		}
		List<MenuPO> menuPOList=menuMapper.list(pDto);
		TreeModel rootModel=new TreeModel();
		rootModel.setText(IMSCons.MENU_ROOT_NAME);
		rootModel.setId(IMSCons.TREE_ROOT_ID);
		rootModel.setIconCls(IMSCons.MENU_ROOT_ICONCLS);
		rootModel.setCascade_id(IMSCons.TREE_ROOT_CASCADE_ID);
		for(int i=0;i<menuPOList.size();i++){
			MenuPO menuPO=menuPOList.get(i);
			String parent_id=menuPO.getParent_id();
			String icon_name=menuPO.getIcon_name();
			TreeModel treeModel=new TreeModel();
			treeModel.setId(menuPO.getMenu_id());
			treeModel.setParentId(parent_id);
			treeModel.setCascade_id(menuPO.getCascade_id());
			treeModel.setText(menuPO.getMenu_name());
			if (IMSUtils.isNotEmpty(icon_name)) {
				if(icon_name.indexOf(".png")>-1){
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}else{
					treeModel.setIconCls(icon_name);
				}
				
			}else{
				
			 treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				
			}
		
			rootModel.add(treeModel);
		}
		return rootModel;
		
	}
	
}
