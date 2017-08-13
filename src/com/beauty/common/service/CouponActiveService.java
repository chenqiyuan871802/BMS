package com.beauty.common.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.constant.BeautyCons;
import com.beauty.common.mapper.CouponActiveMapper;
import com.beauty.common.mapper.CouponRecordMapper;
import com.beauty.common.po.CouponActivePO;
import com.beauty.common.po.CouponRecordPO;
import com.beauty.common.utils.IdUtil;

/**
 * 
 * 类描述：<b>bis_coupon_active[bis_coupon_active业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-04-26 21:47:23
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class CouponActiveService {
  
    @Autowired
	private  CouponActiveMapper couponActiveMapper;
    @Autowired
  	private  CouponRecordMapper couponRecordMapper;
    @Autowired
    private  SqlDao sqlDao;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param couponActivePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(CouponActivePO couponActivePO){
	
	     return couponActiveMapper.insert(couponActivePO);
	     
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
	   String active_id=IdUtil.createCouponActiveId();
	   CouponActivePO couponActivePO =new  CouponActivePO();
	   IMSUtils.copyProperties(inDto,  couponActivePO); 
	   String cdkey_num=IMSCxt.getParamValue(BeautyCons.CDKEY_NUM);
	   int num=8;
	   if(IMSUtils.isNotEmpty(cdkey_num)){
		   num=Integer.parseInt(cdkey_num);
	   }
	   Set<String> cdKeySet=IdUtil.createCdkey(active_id,couponActivePO.getBeauty_num(), num);
	   Iterator<String> it = cdKeySet.iterator();//先迭代出来  
       while(it.hasNext()){//遍历  
    	   CouponRecordPO couponRecordPO=new CouponRecordPO();
    	   couponRecordPO.setRecord_id(IMSId.appId());
    	   couponRecordPO.setActive_id(active_id);
    	   couponRecordPO.setCdkey(it.next());
    	   couponRecordPO.setStatus(BeautyCons.BOND_STATUS_NO); //未兑换
    	   couponRecordPO.setCreate_time(IMSUtils.getDateTime());
    	   couponRecordMapper.insert(couponRecordPO);//批量生成美研券
       }  
	   couponActivePO.setActive_id(active_id);
	   couponActivePO.setIs_del(IMSCons.IS.NO);
	   couponActivePO.setCreate_time(IMSUtils.getDateTime());
	   couponActivePO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   couponActivePO.setModify_time(IMSUtils.getDateTime());
	   couponActivePO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
	   int row=couponActiveMapper.insert(couponActivePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，生成美研权信息成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，生成美研权信息失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param couponActivePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(CouponActivePO couponActivePO){
	
	     return couponActiveMapper.insertAll(couponActivePO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param couponActivePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(CouponActivePO couponActivePO){
	
	     return couponActiveMapper.updateByKey(couponActivePO);
	
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
	   CouponActivePO couponActivePO =new  CouponActivePO();
	   IMSUtils.copyProperties(inDto,  couponActivePO); 
	   int row=couponActiveMapper.updateByKey(couponActivePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，bis_coupon_active数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，bis_coupon_active数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return CouponActivePO
	 */
	public CouponActivePO selectByKey( String active_id){
	
	    return couponActiveMapper.selectByKey(active_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return CouponActivePO
	 */
	public CouponActivePO selectOne(Dto pDto){
	
	    return couponActiveMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<CouponActivePO>
	 */
	public List<CouponActivePO> list(Dto pDto){
	
	     return couponActiveMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<CouponActivePO>
	 */
	public List<CouponActivePO> listPage(Dto pDto){
	
	     return couponActiveMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CouponActivePO>
	 */
	public List<CouponActivePO> like(Dto pDto){
	  
	      return couponActiveMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<CouponActivePO>
	 */
	public List<CouponActivePO> likePage(Dto pDto){
	
	    return couponActiveMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String active_id){
	
	    return couponActiveMapper.deleteByKey(active_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String active_id,String user_id){
	   Dto outDto = Dtos.newDto();
	   Dto delDto=Dtos.newDto();
	   delDto.put("active_id", active_id);
	   delDto.put("whereStatus", BeautyCons.BOND_STATUS_NO); //只有未兑换的失效
	   delDto.put("status", BeautyCons.BOND_STATUS_INVALID);
	   sqlDao.delete("ShopCommonMapper.updateCouponStatus", delDto);
	   CouponActivePO couponActivePO =new  CouponActivePO();
	   couponActivePO.setActive_id(active_id);
	   couponActivePO.setIs_del(IMSCons.IS.YES);
	   couponActivePO.setModify_time(IMSUtils.getDateTime());
	   couponActivePO.setModify_user_id(user_id);
	   int row=couponActiveMapper.updateByKey(couponActivePO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，礼券信息删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，礼券信息删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> active_idList){
	     
	     return couponActiveMapper.batchDeleteByKey(active_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> active_idList){
	    Dto outDto = Dtos.newDto();
	    int row=couponActiveMapper.batchDeleteByKey(active_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，批量bis_coupon_active数据删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，批量bis_coupon_active数据删除失败。");
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
	
	    return couponActiveMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return couponActiveMapper.calc(pDto);
	
	};
	
}
