package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSFormater;
import com.ims.common.core.matatype.Dtos;
import com.beauty.common.mapper.WechatRecordMapper;
import com.beauty.common.po.SmsRecordPO;
import com.beauty.common.po.WechatRecordPO;
import com.beauty.wechat.util.WechatCxt;
import com.google.common.collect.Lists;

/**
 * 
 * 类描述：<b>微信记录表[bis_wechat_record业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-07-23 10:05:06
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class WechatRecordService {
  
    @Autowired
	private  WechatRecordMapper wechatRecordMapper;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param wechatRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(WechatRecordPO wechatRecordPO){
	
	     return wechatRecordMapper.insert(wechatRecordPO);
	     
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
	   String sendUserId=inDto.getString("sendUserId");
	   String sendOpenid=inDto.getString("sendOpenid");
	   String content=inDto.getString("content");
	   List<String> openids=IMSFormater.separatStringToList(sendOpenid);
	   String[] sendUserArray=sendUserId.split(IMSCons.MARK_CSV); //分号分割
	   int row=0;
	   for(int i=0;i<sendUserArray.length;i++){
		   String send_user_id=sendUserArray[i];
		   WechatRecordPO wechatRecordPO=new WechatRecordPO();
		   wechatRecordPO.setRecord_id(IMSId.uuid());
		   wechatRecordPO.setSend_user_id(send_user_id);
		   wechatRecordPO.setSend_time(IMSUtils.getDateTime());
		   wechatRecordPO.setContent(content);
		   wechatRecordPO.setCreate_time(IMSUtils.getDateTime());
		   wechatRecordPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
		   wechatRecordPO.setStatus("1");
		   row+=wechatRecordMapper.insert(wechatRecordPO);
	   }
	   WechatCxt.sendTextMessage(openids, content);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，信息已发送成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，信息已发送失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param wechatRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(WechatRecordPO wechatRecordPO){
	
	     return wechatRecordMapper.insertAll(wechatRecordPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param wechatRecordPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(WechatRecordPO wechatRecordPO){
	
	     return wechatRecordMapper.updateByKey(wechatRecordPO);
	
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
	   WechatRecordPO wechatRecordPO =new  WechatRecordPO();
	   IMSUtils.copyProperties(inDto,  wechatRecordPO); 
	   int row=wechatRecordMapper.updateByKey(wechatRecordPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，微信记录表数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，微信记录表数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return WechatRecordPO
	 */
	public WechatRecordPO selectByKey( String record_id){
	
	    return wechatRecordMapper.selectByKey(record_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return WechatRecordPO
	 */
	public WechatRecordPO selectOne(Dto pDto){
	
	    return wechatRecordMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<WechatRecordPO>
	 */
	public List<WechatRecordPO> list(Dto pDto){
	
	     return wechatRecordMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<WechatRecordPO>
	 */
	public List<WechatRecordPO> listPage(Dto pDto){
	
	     return wechatRecordMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<WechatRecordPO>
	 */
	public List<WechatRecordPO> like(Dto pDto){
	  
	      return wechatRecordMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<WechatRecordPO>
	 */
	public List<WechatRecordPO> likePage(Dto pDto){
	
	    return wechatRecordMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String record_id){
	
	    return wechatRecordMapper.deleteByKey(record_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String record_id){
	   Dto outDto = Dtos.newDto();
	   int row=wechatRecordMapper.deleteByKey(record_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，微信记录表数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，微信记录表数据删除失败。");
	   }
	    return outDto;
	};
	
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> record_idList){
	     
	     return wechatRecordMapper.batchDeleteByKey(record_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> record_idList){
	    Dto outDto = Dtos.newDto();
	    int row=wechatRecordMapper.batchDeleteByKey(record_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，信息记录删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，信息记录删除失败。");
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
	
	    return wechatRecordMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return wechatRecordMapper.calc(pDto);
	
	};
	
}
