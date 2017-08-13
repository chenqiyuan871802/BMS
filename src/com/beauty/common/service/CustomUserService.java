package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.asset.IMSCodec;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.mapper.CustomUserMapper;
import com.beauty.common.po.CustomUserPO;

/**
 * 
 * 类描述：<b>顾客用户信息表[bis_custom_user业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:07:27
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class CustomUserService {
  
    @Autowired
	private  CustomUserMapper customUserMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param customUserPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(CustomUserPO customUserPO){
	
	     return customUserMapper.insert(customUserPO);
	     
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
	   countDto.put("mobile", inDto.getString("mobile"));
	   countDto.put("is_del", IMSCons.IS.NO);
	   int count=customUserMapper.rows(countDto);
	   if(count>0){
			outDto.setAppMsg("该手机已被注册，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   CustomUserPO customUserPO =new  CustomUserPO();
	   IMSUtils.copyProperties(inDto,  customUserPO);
	 /*  String password=inDto.getString("password");
	   String encryptPassword=IMSCodec.encrypt(password,IMSCons.PASSWORD_KEY);
	   customUserPO.setPassword(encryptPassword);*/
	   customUserPO.setEnroll_time(IMSUtils.getDateTime());
	   customUserPO.setIs_del(IMSCons.IS.NO);
	   customUserPO.setWechat_status(BeautyCons.WECHAT_STATUS_NO);
	   int row=customUserMapper.insert(customUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，顾客信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，顾客信息新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param customUserPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(CustomUserPO customUserPO){
	
	     return customUserMapper.insertAll(customUserPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param customUserPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(CustomUserPO customUserPO){
	
	     return customUserMapper.updateByKey(customUserPO);
	
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
	   String mobile_old=inDto.getString("mobile_old");
	   String mobile=inDto.getString("mobile");
	   if(!mobile_old.equals(mobile)){
		   Dto countDto = Dtos.newDto();
		   countDto.put("mobile", mobile);
		   countDto.put("is_del", IMSCons.IS.NO);
		   int count=customUserMapper.rows(countDto);
		   if(count>0){
				outDto.setAppMsg("该手机已被注册，请修改后再保存。");
				outDto.setAppCode(IMSCons.WARN);
				return outDto;
		   }
	   }
	   CustomUserPO customUserPO =new  CustomUserPO();
	   IMSUtils.copyProperties(inDto,  customUserPO); 
	   int row=customUserMapper.updateByKey(customUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，顾客用户信息修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，顾客用户信息修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return CustomUserPO
	 */
	public CustomUserPO selectByKey( String custom_user_id){
	
	    return customUserMapper.selectByKey(custom_user_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return CustomUserPO
	 */
	public CustomUserPO selectOne(Dto pDto){
	
	    return customUserMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<CustomUserPO>
	 */
	public List<CustomUserPO> list(Dto pDto){
	
	     return customUserMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<CustomUserPO>
	 */
	public List<CustomUserPO> listPage(Dto pDto){
	
	     return customUserMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CustomUserPO>
	 */
	public List<CustomUserPO> like(Dto pDto){
	  
	      return customUserMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CustomUserPO>
	 */
	public List<CustomUserPO> likePage(Dto pDto){
	
	    return customUserMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String custom_user_id){
	
	    return customUserMapper.deleteByKey(custom_user_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String custom_user_id){
	   Dto outDto = Dtos.newDto();
	   CustomUserPO customUserPO=new CustomUserPO();
	   customUserPO.setCustom_user_id(custom_user_id);;
	   customUserPO.setIs_del(IMSCons.IS.YES);
	   int row=customUserMapper.updateByKey(customUserPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，顾客信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，顾客信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> custom_user_idList){
	     
	     return customUserMapper.batchDeleteByKey(custom_user_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> custom_user_idList){
	    Dto outDto = Dtos.newDto();
	    int row=customUserMapper.batchDeleteByKey(custom_user_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量顾客用户信息表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量顾客用户信息表数据删除失败。");
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
	
	    return customUserMapper.rows( pDto);
	
	};
	
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return customUserMapper.calc(pDto);
	
	};
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
		CustomUserPO customUserPO=new CustomUserPO();
		String password=inDto.getString("password");
		String encryptPassword=IMSCodec.encrypt(password,IMSCons.PASSWORD_KEY);
		customUserPO.setPassword(encryptPassword);
		customUserPO.setCustom_user_id(inDto.getString("custom_user_id"));
		 int row=customUserMapper.updateByKey(customUserPO);
		  if(row>0){
		       outDto.setAppCode(IMSCons.SUCCESS);
			   outDto.setAppMsg("操作完成，密码重置成功。");
		  }else{
		       outDto.setAppCode(IMSCons.ERROR);
			   outDto.setAppMsg("操作失败，密码重置失败。");
		  }
		return outDto;
	}
	
}
