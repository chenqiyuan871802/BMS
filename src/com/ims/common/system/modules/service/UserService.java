package com.ims.common.system.modules.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCodec;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSFormater;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.mapper.UserMapper;
import com.ims.common.system.modules.po.UserPO;

/**
 * 
 * 类描述：<b>用户基本信息表[aos_user业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-18 08:25:38
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class UserService {
  
    @Autowired
	private  UserMapper userMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param userPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(UserPO userPO){
	
	     return userMapper.insert(userPO);
	     
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
	   countDto.put("account", inDto.getString("account"));
	   countDto.put("is_del", IMSCons.IS.NO);
	   int count=userMapper.rows(countDto);
	   if(count>0){
			outDto.setAppMsg("用户账号已被占用，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   UserPO userPO =new  UserPO();
	   userPO.setUser_id(IMSId.uuid());
	   IMSUtils.copyProperties(inDto,  userPO); 
	   String password=inDto.getString("password");
	   String encryptPassword=IMSCodec.encrypt(password,IMSCons.PASSWORD_KEY);
	   userPO.setPassword(encryptPassword);
	   userPO.setIs_del(IMSCons.IS.NO);
	   userPO.setStyle(IMSCons.STYLE_CLASSIC); //默认经典风格
	   userPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   userPO.setCreate_time(IMSUtils.getDateTime());
	   userPO.setUser_type(DicCons.USER_STATUS_NORMAL);
	   userPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   userPO.setModify_time(IMSUtils.getDateTime());
	   int row=userMapper.insert(userPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，用户信息数据新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，用户信息数据新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param userPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(UserPO userPO){
	
	     return userMapper.insertAll(userPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param userPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(UserPO userPO){
	
	     return userMapper.updateByKey(userPO);
	
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
	   UserPO userPO =new  UserPO();
	   IMSUtils.copyProperties(inDto,  userPO); 
	   userPO.setModify_time(IMSUtils.getDateTime());
	   userPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=userMapper.updateByKey(userPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，用户信息数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，用户信息数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return userPO
	 */
	public UserPO selectByKey( String user_id){
	
	    return userMapper.selectByKey(user_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return userPO
	 */
	public UserPO selectOne(Dto pDto){
	
	    return userMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<userPO>
	 */
	public List<UserPO> list(Dto pDto){
	
	     return userMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<userPO>
	 */
	public List<UserPO> listPage(Dto pDto){
	
	     return userMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<userPO>
	 */
	public List<UserPO> like(Dto pDto){
	  
	      return userMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<userPO>
	 */
	public List<UserPO> likePage(Dto pDto){
	
	    return userMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String user_id){
	
	    return userMapper.deleteByKey(user_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String user_id){
	   Dto outDto = Dtos.newDto();
	   int row=userMapper.deleteByKey(user_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，用户基本信息表数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，用户基本信息表数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> user_idList){
	     
	     return userMapper.batchDeleteByKey(user_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(Dto inDto){
	    Dto outDto = Dtos.newDto();
	    String user_ids=inDto.getString("user_ids");
	    List<String> user_idList=IMSFormater.separatStringToList(user_ids);
	    Date currentTime=IMSUtils.getDateTime();
	    int row=0;
	    for(String user_id:user_idList){
	    	UserPO userPO=new UserPO();
			userPO.setUser_id(user_id);
			userPO.setIs_del(IMSCons.IS.YES);
			userPO.setModify_time(currentTime);
			userPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	        row+=userMapper.updateByKey(userPO);
	    }
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，用户信息删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，用户信息删除失败。");
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
	
	    return userMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return userMapper.calc(pDto);
	
	};
	/**
	 * 
	 * 简要说明：查询用户信息
	 * 编写者：陈骑元
	 * 创建时间：2016年12月19日 下午12:06:18
	 * @param 说明
	 * @return 说明
	 */
	public List<UserPO> queryPage(Dto pDto){
		
		return userMapper.queryPage(pDto);
	}
	/**
	 * 
	 * 简要说明：更新用户密码
	 * 编写者：陈骑元
	 * 创建时间：2016年12月19日 下午7:59:10
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto updatePassword(Dto inDto){
		Dto outDto=Dtos.newDto();
		UserPO userPO=new UserPO();
		String password=inDto.getString("password");
		String encryptPassword=IMSCodec.encrypt(password,IMSCons.PASSWORD_KEY);
		userPO.setPassword(encryptPassword);
		userPO.setUser_id(inDto.getString("user_id"));
		userPO.setModify_time(IMSUtils.getDateTime());
		userPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
		 int row=userMapper.updateByKey(userPO);
		  if(row>0){
		       outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("操作完成，用户密码重置成功。");
		  }else{
		       outDto.setAppCode(IMSCons.ERROR);
			   outDto.setAppMsg("操作失败，用户密码重置失败。");
		  }
		return outDto;
	}
	/**
	 * 
	 * 简要说明：移动用户
	 * 编写者：陈骑元
	 * 创建时间：2016年12月22日 上午9:15:28
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto moveUser(Dto inDto){
		Dto outDto=Dtos.newDto();
		String user_ids=inDto.getString("user_ids");
		String dept_id=inDto.getString("dept_id");
		Date currentTime=IMSUtils.getDateTime();
		List<String> user_idList=IMSFormater.separatStringToList(user_ids);
		int row=0;
		for(String user_id:user_idList){
			UserPO userPO=new UserPO();
			userPO.setUser_id(user_id);
			userPO.setModify_time(currentTime);
			userPO.setDept_id(dept_id);
			row+=userMapper.updateByKey(userPO);
		}
		if(row>0){
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，用户密码重置成功。");
		}else{
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，用户密码重置失败。");
		}
		return outDto;
	}
	
}
