package com.beauty.common.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beauty.common.po.ShopUserPO;
import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.dao.plugin.DBType;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.po.MenuPO;
import com.ims.common.system.modules.po.TreeModel;
/**
 * 
 * 
 * 描述:美容管理后台通用业务逻辑
 * 编写者:陈骑元
 * 创建时间:2017年4月17日 下午10:40:33
 * 修改说明:
 */
@Service
public class SystemCommonService {
    
	@Autowired
	private SqlDao sqlDao;
	/**
	 * 
	 * 简要说明：分页查询短信记录
	 * 编写者：陈骑元
	 * 创建时间：2017年5月12日 上午12:53:46
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listSmsRecordPage(Dto pDto){
		
		return sqlDao.list("SystemCommonMapper.listSmsRecordPage", pDto);
	}
	/**
	 * 
	 * 简要说明：分页查询站内信息
	 * 编写者：陈骑元
	 * 创建时间：2017年7月23日 上午10:11:39
	 * @param 说明
	 * @return 说明
	 */
	public List<Dto> listWechatRecordPage(Dto pDto){
		
		return sqlDao.list("SystemCommonMapper.listWechatRecordPage", pDto);
	}
	
}
