package com.ims.common.system.modules.service;

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
import com.ims.common.system.modules.mapper.RoleMapper;
import com.ims.common.system.modules.po.RolePO;

/**
 * 
 * 类描述：<b>角色表[sys_role业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-31 09:38:33
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class RoleService {
  
    @Autowired
	private  RoleMapper roleMapper;
    @Autowired
    private  SqlDao sqlDao;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param rolePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(RolePO rolePO){
	
	     return roleMapper.insert(rolePO);
	     
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
	   RolePO  rolePO =new  RolePO();
	   rolePO.setRole_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  rolePO); 
	   rolePO.setCreate_time(IMSUtils.getDateTime());
	   rolePO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   rolePO.setModify_time(IMSUtils.getDateTime());
       rolePO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=roleMapper.insert(rolePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，角色信息数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，角色信息数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param rolePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(RolePO rolePO){
	
	     return roleMapper.insertAll(rolePO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param rolePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(RolePO rolePO){
	
	     return roleMapper.updateByKey(rolePO);
	
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
	   RolePO rolePO =new  RolePO();
	   IMSUtils.copyProperties(inDto,  rolePO); 
	   rolePO.setModify_time(IMSUtils.getDateTime());
       rolePO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=roleMapper.updateByKey(rolePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，角色数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，角色数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return RolePO
	 */
	public RolePO selectByKey( String role_id){
	
	    return roleMapper.selectByKey(role_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return RolePO
	 */
	public RolePO selectOne(Dto pDto){
	
	    return roleMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<RolePO>
	 */
	public List<RolePO> list(Dto pDto){
	
	     return roleMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<RolePO>
	 */
	public List<RolePO> listPage(Dto pDto){
	
	     return roleMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<RolePO>
	 */
	public List<RolePO> like(Dto pDto){
	  
	      return roleMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<RolePO>
	 */
	public List<RolePO> likePage(Dto pDto){
	
	    return roleMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String role_id){
	
	    return roleMapper.deleteByKey(role_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String role_id){
	   Dto outDto = Dtos.newDto();
	   //删除角色关联的菜单
	   sqlDao.delete("System.deleteRoleMenu", role_id);
	   //删除角色关联的用户
	   sqlDao.delete("System.deleteRoleUserByRoleId", role_id);
	   int row=roleMapper.deleteByKey(role_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，角色数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，角色数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> role_idList){
	     
	     return roleMapper.batchDeleteByKey(role_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> role_idList){
	    Dto outDto = Dtos.newDto();
	    int row=roleMapper.batchDeleteByKey(role_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量角色表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量角色表数据删除失败。");
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
	
	    return roleMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return roleMapper.calc(pDto);
	
	};
	
}
