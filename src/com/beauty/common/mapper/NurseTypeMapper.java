package com.beauty.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ims.common.core.annotation.Mapper;
import com.ims.common.core.matatype.Dto;
import com.beauty.common.po.NurseTypePO;

/**
 * 
 * 类描述：<b>护理类型信息表[bis_nurse_type]数据访问接口</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:04:13
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Mapper
public interface NurseTypeMapper {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param nurseTypePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(NurseTypePO nurseTypePO);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param nurseTypePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(NurseTypePO nurseTypePO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param nurseTypePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(NurseTypePO nurseTypePO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return NurseTypePO
	 */
	NurseTypePO selectByKey(@Param(value = "type_id") String type_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return NurseTypePO
	 */
	NurseTypePO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<NurseTypePO>
	 */
	List<NurseTypePO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<NurseTypePO>
	 */
	List<NurseTypePO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseTypePO>
	 */
	List<NurseTypePO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseTypePO>
	 */
	List<NurseTypePO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "type_id") String type_id);
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
    int batchDeleteByKey(List<String> type_idList);
	
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
	
}
