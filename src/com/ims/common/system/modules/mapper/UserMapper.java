package com.ims.common.system.modules.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ims.common.core.annotation.Mapper;
import com.ims.common.core.matatype.Dto;
import com.ims.common.system.modules.po.UserPO;

/**
 * 
 * 类描述：<b>用户基本信息表[aos_user]数据访问接口</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2016-12-18 08:25:38
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Mapper
public interface UserMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param aosUserPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(UserPO aosUserPO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param aosUserPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(UserPO aosUserPO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param aosUserPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(UserPO aosUserPO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return AosUserPO
	 */
	UserPO selectByKey(@Param(value = "user_id") String user_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return AosUserPO
	 */
	UserPO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<AosUserPO>
	 */
	List<UserPO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<AosUserPO>
	 */
	List<UserPO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<AosUserPO>
	 */
	List<UserPO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<AosUserPO>
	 */
	List<UserPO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "user_id") String user_id);
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
    int batchDeleteByKey(List<String> user_idList);
	
	/**
	 * 根据Dto统计行数
	 * 
	 * @param pDto
	 * @return
	 */
	int rows(Dto pDto);
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	String calc(Dto pDto);
	/**
	 * 
	 * 简要说明：分页查询用户信息
	 * 编写者：陈骑元
	 * 创建时间：2016年12月19日 下午12:04:48
	 * @param 说明
	 * @return 说明
	 */
	List<UserPO> queryPage(Dto pDto);
	
}
