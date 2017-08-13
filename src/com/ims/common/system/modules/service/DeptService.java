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
import com.ims.common.system.modules.mapper.DeptMapper;
import com.ims.common.system.modules.mapper.UserMapper;
import com.ims.common.system.modules.po.DeptPO;
import com.ims.common.system.modules.po.TreeModel;
import com.ims.common.system.modules.po.UserPO;

/**
 * 
 * 类描述：<b>组织机构[sys_dept业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-10 10:00:30
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class DeptService {
  
    @Autowired
	private  DeptMapper deptMapper;
    @Autowired
    private  UserMapper userMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param deptPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(DeptPO deptPO){
	
	     return deptMapper.insert(deptPO);
	     
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
	   DeptPO deptPO =new  DeptPO();
	   deptPO.setDept_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  deptPO); 
	   Dto calcDto = Dtos.newCalcDto("MAX(cascade_id)");
		calcDto.put("parent_id", deptPO.getParent_id());
		String maxCascadeId =deptMapper.calc(calcDto);
		if(IMSUtils.isEmpty(maxCascadeId)){
			DeptPO parentDeptPO=deptMapper.selectByKey(deptPO.getParent_id());
			if(IMSUtils.isEmpty(parentDeptPO)){
				maxCascadeId="0.0000";
			}else{
				maxCascadeId=parentDeptPO.getCascade_id()+".0000";
			}
			
		}
		String currentUserId=inDto.getString(IMSCons.LOGIN_USER_ID);
		String cascade_id=IMSId.treeId(maxCascadeId,9999);
		deptPO.setIs_del(IMSCons.IS.NO);
		deptPO.setCascade_id(cascade_id);
		deptPO.setCreate_time(IMSUtils.getDateTime());
		deptPO.setCreate_user_id(currentUserId);
		deptPO.setModify_user_id(currentUserId);
		deptPO.setModify_time(IMSUtils.getDateTime());
	   int row=deptMapper.insert(deptPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，组织机构数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，组织机构数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param deptPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(DeptPO deptPO){
	
	     return deptMapper.insertAll(deptPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param deptPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(DeptPO deptPO){
	
	     return deptMapper.updateByKey(deptPO);
	
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
	  
	   String dept_id=inDto.getString("dept_id");
	   //根节点的父节点是不存在的，因此默认为-1
	   if(IMSCons.TREE_ROOT_ID.equals(dept_id)){
		   inDto.put("parent_id", -1);
	   }
	   String parent_id=inDto.getString("parent_id");
	   String currentUserId=inDto.getString(IMSCons.LOGIN_USER_ID);
	   String parent_id_old=inDto.getString("parent_id_old");
	  
	   DeptPO deptPO =new  DeptPO();
	   IMSUtils.copyProperties(inDto,  deptPO); 
	   deptPO.setModify_time(IMSUtils.getDateTime());
	   deptPO.setModify_user_id(currentUserId);
	   if(!parent_id.equals(parent_id_old)){  //如果机构发生改变了，则更新子节点的语义ID
		   //查询当前父节点下面是否存在最大的语义ID
		   Dto calcDto = Dtos.newCalcDto("MAX(cascade_id)");
			calcDto.put("parent_id", deptPO.getParent_id());
			String maxCascadeId =deptMapper.calc(calcDto);
			//如果当前父节点不存在最大的语义ID，则初始化生成
			if(IMSUtils.isEmpty(maxCascadeId)){
				DeptPO parentDeptPO=deptMapper.selectByKey(deptPO.getParent_id());
				if(IMSUtils.isEmpty(parentDeptPO)){
					maxCascadeId="0.0000";
				}else{
					maxCascadeId=parentDeptPO.getCascade_id()+".0000";
				}
			}
			//生成新的语义ID
			String cascade_id=IMSId.treeId(maxCascadeId,9999);
			deptPO.setCascade_id(cascade_id);
			//原始的语义ID
			String cascade_id_old=inDto.getString("cascade_id");
			Dto pDto=Dtos.newDto("cascade_id",cascade_id_old );
			//查询所有子节点进行更新
			List<DeptPO> childDeptList=deptMapper.like(pDto);  
			for(DeptPO childDept:childDeptList){
				String cascade_id_temp=childDept.getCascade_id();
				cascade_id_temp=cascade_id_temp.replace(cascade_id_old, cascade_id);
				childDept.setCascade_id(cascade_id_temp);
				childDept.setModify_time(IMSUtils.getDateTime());
				childDept.setModify_user_id(currentUserId);
				deptMapper.updateByKey(childDept);
				
			}
	   }
	   int row=deptMapper.updateByKey(deptPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，组织机构数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，组织机构数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return DeptPO
	 */
	public DeptPO selectByKey( String dept_id){
	
	    return deptMapper.selectByKey(dept_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return DeptPO
	 */
	public DeptPO selectOne(Dto pDto){
	
	    return deptMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<DeptPO>
	 */
	public List<DeptPO> list(Dto pDto){
	
	     return deptMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<DeptPO>
	 */
	public List<DeptPO> listPage(Dto pDto){
	
	     return deptMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DeptPO>
	 */
	public List<DeptPO> like(Dto pDto){
	  
	      return deptMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DeptPO>
	 */
	public List<DeptPO> likePage(Dto pDto){
		
	    return deptMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String dept_id){
	
	    return deptMapper.deleteByKey(dept_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String dept_id,String login_user_id){
	   Dto outDto = Dtos.newDto();
	   Dto pDto=Dtos.newDto();
	   pDto.put("parent_id",dept_id) ;
	   pDto.put("is_del", IMSCons.IS.NO);
	   int count=deptMapper.rows(pDto);
	   if(count>0){
		   outDto.setAppCode(IMSCons.WARN);
		   outDto.setAppMsg("操作失败，当前组织机构下存在子机构，不允许删除，请先删除子机构然后再删除。");
	   }else{
		   Dto userDto=Dtos.newDto();
		   userDto.put("dept_id", dept_id);
		   userDto.put("is_del", IMSCons.IS.NO);
		   //查询机构下面的人员参数，用来循环删除人员信息
		   List<UserPO> userList=userMapper.list(userDto);
		   for(UserPO userPO:userList){
			   userPO.setIs_del(IMSCons.IS.YES);
			   userPO.setModify_time(IMSUtils.getDateTime());
			   userPO.setModify_user_id(login_user_id);
			   userMapper.updateByKey(userPO);
		   }
		   DeptPO deptPO=new DeptPO();
		   deptPO.setDept_id(dept_id);
		   deptPO.setIs_del(IMSCons.IS.YES);
		   deptPO.setModify_time(IMSUtils.getDateTime());
		   deptPO.setModify_user_id(login_user_id);
		   int row=deptMapper.updateByKey(deptPO);
		   if(row>0){
			   outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("操作完成，组织机构数据删除成功。");
		   }else{
			   outDto.setAppCode(IMSCons.ERROR);
			   outDto.setAppMsg("操作失败，组织机构数据删除失败。");
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
	public int batchDeleteByKey(List<String> dept_idList){
	     
	     return deptMapper.batchDeleteByKey(dept_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> dept_idList){
	    Dto outDto = Dtos.newDto();
	    int row=deptMapper.batchDeleteByKey(dept_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量组织机构数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量组织机构数据删除失败。");
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
	
	    return deptMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return deptMapper.calc(pDto);
	
	};
	/**
	 * 加载组织机构树
	 * @param pDto
	 * @return
	 */
	public TreeModel loadDeptTree(Dto pDto){
		//查询 根节点 dept=0;
	    DeptPO rootDept=deptMapper.selectByKey(IMSCons.TREE_ROOT_ID);
	    //如果数据库没有根节点 则创建一个根节点,并保存到数据库中
	    if(IMSUtils.isEmpty(rootDept)){
	    	rootDept=new DeptPO();
	    	rootDept.setDept_id(IMSCons.TREE_ROOT_ID);
	    	rootDept.setParent_id("-1");
	    	rootDept.setDept_name(IMSCons.DEPT_ROOT_NAME);
	    	rootDept.setIcon_name(IMSCons.DEPT_ROOT_ICONCLS);
	    	rootDept.setCascade_id(IMSCons.TREE_ROOT_CASCADE_ID);
	    	rootDept.setIs_del( IMSCons.IS.NO);
	    	rootDept.setIs_auto_expand(IMSCons.IS.YES);
	    	rootDept.setSort_no(1);
	    	rootDept.setRemark("顶级机构不能进行移动和删除操作，只能进行修改");
	    	rootDept.setCreate_time(IMSUtils.getDateTime());
	    	deptMapper.insert(rootDept);
	    	
	    }
		pDto.put("is_del",  IMSCons.IS.NO);  //查询有效的组织机构信息
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			pDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			pDto.setOrder(" LENGTH(cascade_id) ASC,sort_no ASC ");
		}
		
		List<DeptPO> deptPOList=deptMapper.list(pDto);
		TreeModel rootModel=new TreeModel();
		rootModel.setText(rootDept.getDept_name());
		rootModel.setId(rootDept.getDept_id());
		if(IMSUtils.isNotEmpty(rootDept.getIcon_name())){
			rootModel.setIconCls(rootDept.getIcon_name());
		}else{
			rootModel.setIconCls(IMSCons.DEPT_ROOT_ICONCLS);
		}
		rootModel.setCascade_id(rootDept.getCascade_id());
		if(IMSCons.IS.NO.equals(rootDept.getIs_auto_expand())){
			rootModel.setState(IMSCons.TREE_STATE_CLOSED);
			
		}
		for(int i=0;i<deptPOList.size();i++){
		    DeptPO deptPO=deptPOList.get(i);
			int child_count=deptPO.getChild_count();
			String parent_id=deptPO.getParent_id();
			String icon_name=deptPO.getIcon_name();
			TreeModel treeModel=new TreeModel();
			treeModel.setId(deptPO.getDept_id());
			treeModel.setParentId(parent_id);
			treeModel.setCascade_id(deptPO.getCascade_id());
			treeModel.setText(deptPO.getDept_name());
			if (IMSUtils.isNotEmpty(icon_name)) {
				treeModel.setIconCls(icon_name);
			}else{
				if(child_count==0){  //子节点替换子节点图标
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}
			}
			String is_auto_expand = deptPO.getIs_auto_expand();
			if(child_count>0){  //只有父节点才有闭合功能
				if(IMSCons.IS.NO.equals(is_auto_expand)){
					treeModel.setState(IMSCons.TREE_STATE_CLOSED);
					
				}
			}
			rootModel.add(treeModel);
		}
		return rootModel;
			  
	}
	/**
	 * 
	 * 简要说明：移动机构
	 * 编写者：陈骑元
	 * 创建时间：2016年12月29日 上午9:38:08
	 * @param 说明
	 * @return 说明
	 */
    @Transactional
	public Dto moveDept(Dto inDto){
	   Dto outDto = Dtos.newDto();
	   String currentUserId=inDto.getString(IMSCons.LOGIN_USER_ID);
	   String parent_id_old=inDto.getString("parent_id_old");
	   String parent_id=inDto.getString("parent_id");
	   DeptPO deptPO =new  DeptPO();
	   deptPO.setDept_id(inDto.getString("dept_id"));
	   deptPO.setParent_id(parent_id);
	   
	   deptPO.setModify_time(IMSUtils.getDateTime());
	   deptPO.setModify_user_id(currentUserId);
	   if(!parent_id.equals(parent_id_old)){  //如果机构发生改变了，则更新子节点的语义ID
		   //查询当前父节点下面是否存在最大的语义ID
		   Dto calcDto = Dtos.newCalcDto("MAX(cascade_id)");
			calcDto.put("parent_id", deptPO.getParent_id());
			String maxCascadeId =deptMapper.calc(calcDto);
			//如果当前父节点不存在最大的语义ID，则初始化生成
			if(IMSUtils.isEmpty(maxCascadeId)){
				DeptPO parentDeptPO=deptMapper.selectByKey(deptPO.getParent_id());
				if(IMSUtils.isEmpty(parentDeptPO)){
					maxCascadeId="0.0000";
				}else{
					maxCascadeId=parentDeptPO.getCascade_id()+".0000";
				}
			}
			//生成新的语义ID
			String cascade_id=IMSId.treeId(maxCascadeId,9999);
			deptPO.setCascade_id(cascade_id);
			//原始的语义ID
			String cascade_id_old=inDto.getString("cascade_id");
			Dto pDto=Dtos.newDto("cascade_id",cascade_id_old );
			//查询所有子节点进行更新
			List<DeptPO> childDeptList=deptMapper.like(pDto);  
			for(DeptPO childDept:childDeptList){
				String cascade_id_temp=childDept.getCascade_id();
				cascade_id_temp=cascade_id_temp.replace(cascade_id_old, cascade_id);
				childDept.setCascade_id(cascade_id_temp);
				childDept.setModify_time(IMSUtils.getDateTime());
				childDept.setModify_user_id(currentUserId);
				deptMapper.updateByKey(childDept);
				
			}
			 int row=deptMapper.updateByKey(deptPO);
			   if(row>0){
				   outDto.setAppCode(IMSCons.SUCCESS);
				   outDto.setAppMsg("操作完成，移动机构成功。");
			   }else{
				   outDto.setAppCode(IMSCons.ERROR);
				   outDto.setAppMsg("操作失败，移动机构失败。");
			   }
	   }else{
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，组织机构数据修改成功。");
		  
	   }
	  
	   return outDto;
	     
	};
}
