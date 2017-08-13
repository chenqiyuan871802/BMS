package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.mapper.NurseBagMapper;
import com.beauty.common.po.NurseBagPO;

/**
 * 
 * 类描述：<b>礼包信息[bis_nurse_bag业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-23 18:08:32
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class NurseBagService {
  
    @Autowired
	private  NurseBagMapper nurseBagMapper;
    @Autowired
    private  SqlDao sqlDao;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param nurseBagPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(NurseBagPO nurseBagPO){
	
	     return nurseBagMapper.insert(nurseBagPO);
	     
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
	   String bag_id=inDto.getString("bag_id");
	   String project_ids=inDto.getString("project_id");
	   String project_new_prices=inDto.getString("project_new_price");
	   String project_old_prices=inDto.getString("project_old_price");
	   String project_nums=inDto.getString("project_num");
	   String[] project_idArray=project_ids.split(",");
	   String[] project_new_priceArray=project_new_prices.split(",");
	   String[] project_old_priceArray=project_old_prices.split(",");
	   String[] project_numArray=project_nums.split(",");
	   for(int i=0;i<project_idArray.length;i++){
		   Dto addDto=Dtos.newDto();
		   addDto.put("project_id", project_idArray[i]);
		   addDto.put("project_new_price", project_new_priceArray[i]);
		   addDto.put("project_old_price", project_old_priceArray[i]);
		   addDto.put("project_num", project_numArray[i]);
		   addDto.put("bag_id", bag_id);
		   addDto.put("create_user_id",inDto.getString(IMSCons.LOGIN_USER_ID));
		   addDto.put("create_time", IMSUtils.getDateTime());
		   sqlDao.insert("ShopCommonMapper.saveBagProject", addDto);
	   }
	   NurseBagPO nurseBagPO =new  NurseBagPO();
	   IMSUtils.copyProperties(inDto,  nurseBagPO); 
	   nurseBagPO.setRemain_num(nurseBagPO.getOpen_card_num());
	   nurseBagPO.setIs_del(IMSCons.IS.NO);
	   nurseBagPO.setCreate_time(IMSUtils.getDateTime());
	   nurseBagPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   nurseBagPO.setModify_time(IMSUtils.getDateTime());
	   nurseBagPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=nurseBagMapper.insert(nurseBagPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，礼包信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，礼包信息新增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param nurseBagPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(NurseBagPO nurseBagPO){
	
	     return nurseBagMapper.insertAll(nurseBagPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param nurseBagPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(NurseBagPO nurseBagPO){
	
	     return nurseBagMapper.updateByKey(nurseBagPO);
	
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
	   
	   String bag_id=inDto.getString("bag_id");
	   //先删除礼包信息再插入
	   sqlDao.delete("ShopCommonMapper.deleteBagProject", bag_id);
	   String project_ids=inDto.getString("project_id");
	   String project_new_prices=inDto.getString("project_new_price");
	   String project_old_prices=inDto.getString("project_old_price");
	   String project_nums=inDto.getString("project_num");
	   String[] project_idArray=project_ids.split(",");
	   String[] project_new_priceArray=project_new_prices.split(",");
	   String[] project_old_priceArray=project_old_prices.split(",");
	   String[] project_numArray=project_nums.split(",");
	   for(int i=0;i<project_idArray.length;i++){
		   Dto addDto=Dtos.newDto();
		   addDto.put("project_id", project_idArray[i]);
		   addDto.put("project_new_price", project_new_priceArray[i]);
		   addDto.put("project_old_price", project_old_priceArray[i]);
		   addDto.put("project_num", project_numArray[i]);
		   addDto.put("bag_id", bag_id);
		   addDto.put("create_user_id",inDto.getString(IMSCons.LOGIN_USER_ID));
		   addDto.put("create_time", IMSUtils.getDateTime());
		   sqlDao.insert("ShopCommonMapper.saveBagProject", addDto);
	   }
	   NurseBagPO nurseBagPO =new  NurseBagPO();
	   IMSUtils.copyProperties(inDto,  nurseBagPO); 
	   nurseBagPO.setRemain_num(nurseBagPO.getOpen_card_num());
	   nurseBagPO.setModify_time(IMSUtils.getDateTime());
	   nurseBagPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=nurseBagMapper.updateByKey(nurseBagPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，礼包信息数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，礼包信息数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return NurseBagPO
	 */
	public NurseBagPO selectByKey( String bag_id){
	
	    return nurseBagMapper.selectByKey(bag_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return NurseBagPO
	 */
	public NurseBagPO selectOne(Dto pDto){
	
	    return nurseBagMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<NurseBagPO>
	 */
	public List<NurseBagPO> list(Dto pDto){
	
	     return nurseBagMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<NurseBagPO>
	 */
	public List<NurseBagPO> listPage(Dto pDto){
	
	     return nurseBagMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseBagPO>
	 */
	public List<NurseBagPO> like(Dto pDto){
	  
	      return nurseBagMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<NurseBagPO>
	 */
	public List<NurseBagPO> likePage(Dto pDto){
	
	    return nurseBagMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String bag_id){
	
	    return nurseBagMapper.deleteByKey(bag_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String bag_id,String user_id){
	   Dto outDto = Dtos.newDto();
	   NurseBagPO nurseBagPO =new  NurseBagPO();
	   nurseBagPO.setBag_id(bag_id);
	   nurseBagPO.setIs_del(IMSCons.IS.YES);
	   nurseBagPO.setModify_time(IMSUtils.getDateTime());
	   nurseBagPO.setModify_user_id(user_id);
	   int row=nurseBagMapper.updateByKey(nurseBagPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，礼包信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，礼包信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> bag_idList){
	     
	     return nurseBagMapper.batchDeleteByKey(bag_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> bag_idList){
	    Dto outDto = Dtos.newDto();
	    int row=nurseBagMapper.batchDeleteByKey(bag_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量礼包信息数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量礼包信息数据删除失败。");
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
	
	    return nurseBagMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return nurseBagMapper.calc(pDto);
	
	};
	
}
