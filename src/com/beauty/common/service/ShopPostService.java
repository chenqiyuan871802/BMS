package com.beauty.common.service;

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
import com.beauty.common.mapper.ShopPostMapper;
import com.beauty.common.po.ShopPostPO;

/**
 * 
 * 类描述：<b>店铺职位信息表[bis_shop_post业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-17 12:10:12
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class ShopPostService {
  
    @Autowired
	private  ShopPostMapper shopPostMapper;
	@Autowired
	private  SqlDao sqlDao;
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param shopPostPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(ShopPostPO shopPostPO){
	
	     return shopPostMapper.insert(shopPostPO);
	     
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
	   countDto.put("post_code", inDto.getString("post_code"));
	   int count=shopPostMapper.rows(countDto);
	   if(count>0){
			outDto.setAppMsg("职位编码已被占用，请修改后再保存。");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
	   }
	   String post_id=IMSId.appId();
	   String menuids = inDto.getString("menuids");
	   String[] menuidArray = menuids.split(",");
	   for (int i = 0; i < menuidArray.length; i++) {
			Dto pDto = Dtos.newDto();
			pDto.put("post_id", post_id);
			pDto.put("menu_id", menuidArray[i]);
			pDto.put("create_user_id", inDto.getString(IMSCons.LOGIN_USER_ID));
			pDto.put("create_time", IMSUtils.getDateTime());
			 sqlDao.insert("ShopCommonMapper.savePostMenu", pDto);
		}
	   ShopPostPO shopPostPO =new  ShopPostPO();
	   IMSUtils.copyProperties(inDto,  shopPostPO); 
	   shopPostPO.setPost_id(post_id);
	   shopPostPO.setCreate_time(IMSUtils.getDateTime());
	   shopPostPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   shopPostPO.setModify_time(IMSUtils.getDateTime());
	   shopPostPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=shopPostMapper.insert(shopPostPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，职位信息新增成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，职位信息据增失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param shopPostPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(ShopPostPO shopPostPO){
	
	     return shopPostMapper.insertAll(shopPostPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param shopPostPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(ShopPostPO shopPostPO){
	
	     return shopPostMapper.updateByKey(shopPostPO);
	
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
       String old_post_code=inDto.getString("old_post_code");//旧的职位编码
       String post_code=inDto.getString("post_code"); //新的职位编码
       String post_id=inDto.getString("post_id");
	   Dto outDto = Dtos.newDto();
	   if(!old_post_code.equals(post_code)){  //如果不想等则进行校验
		   Dto countDto = Dtos.newDto();
		   countDto.put("post_code", post_code);
		   int count=shopPostMapper.rows(countDto);
		   if(count>0){
				outDto.setAppMsg("职位编码已被占用，请修改后再保存。");
				outDto.setAppCode(IMSCons.WARN);
				return outDto;
		   }
	   }
	   //先进行清除在进行插入
	   sqlDao.insert("ShopCommonMapper.deletePostMenu",post_id);
	   String menuids = inDto.getString("menuids");
	   String[] menuidArray = menuids.split(",");
	   for (int i = 0; i < menuidArray.length; i++) {
			Dto pDto = Dtos.newDto();
			pDto.put("post_id", post_id);
			pDto.put("menu_id", menuidArray[i]);
			pDto.put("create_user_id", inDto.getString(IMSCons.LOGIN_USER_ID));
			pDto.put("create_time", IMSUtils.getDateTime());
			 sqlDao.insert("ShopCommonMapper.savePostMenu", pDto);
		}
	   ShopPostPO shopPostPO =new  ShopPostPO();
	   IMSUtils.copyProperties(inDto,  shopPostPO); 
	   shopPostPO.setModify_time(IMSUtils.getDateTime());
	   shopPostPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=shopPostMapper.updateByKey(shopPostPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，职位信息修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，职位信息改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return ShopPostPO
	 */
	public ShopPostPO selectByKey( String post_id){
	
	    return shopPostMapper.selectByKey(post_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return ShopPostPO
	 */
	public ShopPostPO selectOne(Dto pDto){
	
	    return shopPostMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<ShopPostPO>
	 */
	public List<ShopPostPO> list(Dto pDto){
	
	     return shopPostMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<ShopPostPO>
	 */
	public List<ShopPostPO> listPage(Dto pDto){
	
	     return shopPostMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopPostPO>
	 */
	public List<ShopPostPO> like(Dto pDto){
	  
	      return shopPostMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<ShopPostPO>
	 */
	public List<ShopPostPO> likePage(Dto pDto){
	
	    return shopPostMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String post_id){
	
	    return shopPostMapper.deleteByKey(post_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String post_id){
	   Dto outDto = Dtos.newDto();
	   //删除关联的职位与菜单的关联信息
	   sqlDao.insert("ShopCommonMapper.deletePostMenu",post_id);
	   int row=shopPostMapper.deleteByKey(post_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，职位信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，职位信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> post_idList){
	     
	     return shopPostMapper.batchDeleteByKey(post_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> post_idList){
	    Dto outDto = Dtos.newDto();
	    int row=shopPostMapper.batchDeleteByKey(post_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量店铺职位信息表数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量店铺职位信息表数据删除失败。");
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
	
	    return shopPostMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return shopPostMapper.calc(pDto);
	
	};
	
}
