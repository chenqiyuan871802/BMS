package com.beauty.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.matatype.Dtos;
import com.beauty.asyncTask.SmsAsyncTask;
import com.beauty.common.mapper.SmsRecordMapper;
import com.beauty.common.po.SmsRecordPO;
import com.google.common.collect.Lists;

/**
 * 
 * 类描述：<b>短信记录[bis_sms_record业务逻辑</b>
 * 创建人：陈骑元
 * 邮箱：240823329@qq.com
 * 创建时间：2017-05-12 00:26:48
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 1.0
 */
@Service
public class SmsRecordService {
  
    @Autowired
	private  SmsRecordMapper smsRecordMapper;
    @Autowired
    private SmsAsyncTask smsAsyncTask;
	
	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param smsRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(SmsRecordPO smsRecordPO){
	
	     return smsRecordMapper.insert(smsRecordPO);
	     
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
	   String mobile=inDto.getString("mobile");
	   String content=inDto.getString("content");
	   String[] mobileArray=mobile.split(IMSCons.MARK_CSV); //分号分割
	   List<SmsRecordPO> smsRecordList=Lists.newArrayList(); //要发送合法的短信
	   int row=0;
	   for(int i=0;i<mobileArray.length;i++){
		   String mobileStr=mobileArray[i];
		   SmsRecordPO smsRecordPO =new  SmsRecordPO();
		   smsRecordPO.setRecord_id(IMSId.uuid());
		   smsRecordPO.setMobile(mobileStr);
		   smsRecordPO.setContent(content);
		   smsRecordPO.setStatus(IMSCons.SMS_STATUS_FAILURE);
		   smsRecordPO.setCreate_time(IMSUtils.getDateTime());
		   smsRecordPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
		   smsRecordPO.setSms_type(IMSCons.SMS_TYPE_NORMAL);
		   if(IMSUtils.checkMobile(mobileStr)){
			   smsRecordList.add(smsRecordPO);
		   }else{
			 
			   smsRecordPO.setFailure_cause("手机号码不合法");
		   }
		   row+=smsRecordMapper.insert(smsRecordPO);
	   }
	   smsAsyncTask.sendMoreSms(smsRecordList); //异步发送短信
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，信息成功保存到发送列表。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，信息发送失败。");
	   }
	   return outDto;
	     
	};
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param smsRecordPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
    @Transactional
	public int insertAll(SmsRecordPO smsRecordPO){
	
	     return smsRecordMapper.insertAll(smsRecordPO);
	     
	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param smsRecordPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	 @Transactional
	public int updateByKey(SmsRecordPO smsRecordPO){
	
	     return smsRecordMapper.updateByKey(smsRecordPO);
	
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
	   SmsRecordPO smsRecordPO =new  SmsRecordPO();
	   IMSUtils.copyProperties(inDto,  smsRecordPO); 
	   int row=smsRecordMapper.updateByKey(smsRecordPO);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，短信记录数据修改成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，短信记录数据修改失败。");
	   }
	   return outDto;
	     
	};
	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return SmsRecordPO
	 */
	public SmsRecordPO selectByKey( String record_id){
	
	    return smsRecordMapper.selectByKey(record_id);
	   
	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return SmsRecordPO
	 */
	public SmsRecordPO selectOne(Dto pDto){
	
	    return smsRecordMapper.selectOne(pDto);
	
	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<SmsRecordPO>
	 */
	public List<SmsRecordPO> list(Dto pDto){
	
	     return smsRecordMapper.list(pDto);
	
	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<SmsRecordPO>
	 */
	public List<SmsRecordPO> listPage(Dto pDto){
	
	     return smsRecordMapper.listPage( pDto);
	
	};
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<SmsRecordPO>
	 */
	public List<SmsRecordPO> like(Dto pDto){
	  
	      return smsRecordMapper.like( pDto);
	
	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<SmsRecordPO>
	 */
	public List<SmsRecordPO> likePage(Dto pDto){
	
	    return smsRecordMapper.likePage( pDto);
	
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
   @Transactional
    public int  deleteByKey( String record_id){
	
	    return smsRecordMapper.deleteByKey(record_id);
	};
	
	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete( String record_id){
	   Dto outDto = Dtos.newDto();
	   int row=smsRecordMapper.deleteByKey(record_id);
	   if(row>0){
		   outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，短信记录数据删除成功。");
	   }else{
		   outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，短信记录数据删除失败。");
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
	     
	     return smsRecordMapper.batchDeleteByKey(record_idList);
	
	};
	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> record_idList){
	    Dto outDto = Dtos.newDto();
	    int row=smsRecordMapper.batchDeleteByKey(record_idList);
	    if(row>0){
	       outDto.setAppCode(IMSCons.SUCCESS);
		   outDto.setAppMsg("操作完成，短信记录删除成功。");
	    }else{
	       outDto.setAppCode(IMSCons.ERROR);
		   outDto.setAppMsg("操作失败，短信记录删除失败。");
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
	
	    return smsRecordMapper.rows( pDto);
	
	};
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto){
	 
	    return smsRecordMapper.calc(pDto);
	
	};
	
}
