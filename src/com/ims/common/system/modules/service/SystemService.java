package com.ims.common.system.modules.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCodec;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.dao.SqlDao;
import com.ims.common.core.dao.plugin.DBType;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.asset.DicCons;
import com.ims.common.system.modules.mapper.UserMapper;
import com.ims.common.system.modules.mapper.MenuMapper;
import com.ims.common.system.modules.po.UserPO;
import com.ims.common.system.modules.po.CardMenuPO;
import com.ims.common.system.modules.po.DictionaryPO;
import com.ims.common.system.modules.po.MenuPO;
import com.ims.common.system.modules.po.ParamPO;
import com.ims.common.system.modules.po.RolePO;
import com.ims.common.system.modules.po.TreeModel;

/**
 * 
 * 类描述： 公用业务逻辑 创建人：陈骑元 邮箱：240823329@qq.com 创建时间：Oct 9, 2016 10:53:28 PM 修改人：
 * 修改时间： 修改备注：
 * 
 * @version 1.0
 */
@Service
public class SystemService {
	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;

	/**
	 * 
	 * 简要说明：查询待选的用户 编写者：陈骑元 创建时间：2017年1月13日 上午12:04:27
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<UserPO> listUserPage(Dto pDto) {

		return sqlDao.list("System.listUserPage", pDto);
	}

	/**
	 * 
	 * 简要说明：查询已经选择角色用户 编写者：陈骑元 创建时间：2017年1月13日 上午12:04:27
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<UserPO> listSelectUserPage(Dto pDto) {

		return sqlDao.list("System.listSelectUserPage", pDto);
	}

	/**
	 * 查询授权菜单树
	 * 
	 * @param pDto
	 * @return
	 */
	public TreeModel loadGrantMenuTree(Dto pDto,UserPO userPO) {
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			pDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			pDto.setOrder("LENGTH(cascade_id) ASC,sort_no ASC ");
		}
		List<MenuPO> menuPOList = Lists.newArrayList();
		pDto.put("menu_type",DicCons.MENU_TYPE_SYS);
		if(IMSCons.SUPER_ADMIN.equals(userPO.getAccount())){
			menuPOList =sqlDao.list("System.listEnabledMenu", pDto);
		}else{
			pDto.put("user_id", userPO.getUser_id());
			menuPOList =sqlDao.list("System.listUserRoleMenu", pDto);
		}
		
		List<Dto> grantMenuList = sqlDao.list("System.listGrantMenuOfRoleId", pDto);
		TreeModel rootModel = new TreeModel();
		rootModel.setText(IMSCons.MENU_ROOT_NAME);
		rootModel.setId(IMSCons.TREE_ROOT_ID);
		rootModel.setIconCls(IMSCons.MENU_ROOT_ICONCLS);
		for (int i = 0; i < menuPOList.size(); i++) {
			MenuPO menuPO = menuPOList.get(i);
			int child_count = menuPO.getChild_count();
			String parent_id = menuPO.getParent_id();
			String menu_id = menuPO.getMenu_id();
			String icon_name = menuPO.getIcon_name();
			if (IMSCons.TREE_ROOT_ID.equals(parent_id) && child_count == 0) { // 过滤一级菜单没有子菜单的数据
				continue;
			}
			TreeModel treeModel = new TreeModel();
			treeModel.setId(menu_id);
			treeModel.setParentId(parent_id);
			treeModel.setText(menuPO.getMenu_name());
			if (IMSUtils.isNotEmpty(icon_name)) {
				treeModel.setIconCls(icon_name);
			} else {
				if(child_count==0){  //子节点替换子节点图标
					treeModel.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
				}
			}
			if (!IMSCons.TREE_ROOT_ID.equals(parent_id)) {
				if (checkGrantMenu(menu_id, grantMenuList)) {
					treeModel.setChecked("true");

				}
			}
			rootModel.add(treeModel);
		}
		return rootModel;
	}
    

	/**
	 * 检查是否授权菜单
	 * 
	 * @return
	 */
	private boolean checkGrantMenu(String menuid, List<Dto> grantMenuList) {
		for (int i = 0; i < grantMenuList.size(); i++) {
			Dto grantMenuDto = grantMenuList.get(i);
			String grantMenuid = grantMenuDto.getString("menu_id");
			if (menuid.equals(grantMenuid)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 保存授权菜单
	 * 
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto saveRoleMenu(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String role_id = inDto.getString("role_id");
		String menuids = inDto.getString("menuids");
		String[] menuidArray = menuids.split(",");
		// 先清空才批量插入
		sqlDao.delete("System.deleteRoleMenu", role_id);
		int row=0;
		for (int i = 0; i < menuidArray.length; i++) {
			Dto pDto = Dtos.newDto();
			pDto.put("role_id", role_id);
			// 默认设置授权的类型
			pDto.put("grant_type", DicCons.GRANT_TYPE_BIZ);
			pDto.put("menu_id", menuidArray[i]);
			pDto.put("create_user_id", inDto.getString(IMSCons.LOGIN_USER_ID));
			pDto.put("create_time", IMSUtils.getDateTime());
			 row += sqlDao.insert("System.saveRoleMenu", pDto);
		}
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，角色授权菜单成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，角色授权菜单失败。");
		}
		return outDto;

	};

	/**
	 * 
	 * 简要说明：保存角色与用户的关联信息 
	 * 编写者：陈骑元 
	 * 创建时间：2017年1月13日 下午2:22:54
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto saveRoleUser(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String role_id = inDto.getString("role_id");
		String user_ids = inDto.getString("user_ids");
		String[] userIdArray = user_ids.split(",");
		int row=0;
		for (int i = 0; i < userIdArray.length; i++) {
			String user_id = userIdArray[i];
			Dto pDto = Dtos.newDto();
			pDto.put("role_id", role_id);
			pDto.put("user_id", user_id);
			pDto.put("create_user_id", inDto.getString(IMSCons.LOGIN_USER_ID));
			pDto.put("create_time", IMSUtils.getDateTime());
			// 每次新增用户授权 都去删除历史用户授权，防止一个用户出现多个角色的情况
			sqlDao.delete("System.deleteRoleUserByUserId", user_id);
			 row+= sqlDao.insert("System.saveRoleUser",pDto);
			
		}
		
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，用户授权角色成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作完成，用户授权角色失败。");
		}
		return outDto;
	}

	/**
	 * 
	 * 简要说明：删除角色用户信息 
	 * 编写者：陈骑元
	 *  创建时间：2017年1月13日 下午4:05:55
	 * 
	 * @param 说明
	 * @return 说明
	 */
	@Transactional
	public Dto deleteRoleUser(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String user_ids = inDto.getString("user_ids");
		String[] userIdArray = user_ids.split(",");
		int row = 0;
		for (int i = 0; i < userIdArray.length; i++) {
			String user_id = userIdArray[i];
			row += sqlDao.delete("System.deleteRoleUserByUserId", user_id);
		}
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，撤销用户授权角色成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，撤销用户授权角色失败。");
		}
		return outDto;
	}

	

	
	/**
	 * 根据用户编号去查询关联的角色
	 * 
	 * @param user_id
	 * @return
	 */
	public RolePO queryRoleByUserId(String user_id) {
		return (RolePO) sqlDao.selectOne("System.queryRoleByUserId", user_id);
	}

	/**
	 * 验证用户登陆
	 * 
	 * @param inDto
	 * @return
	 */
	public Dto checkLogin(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String account = inDto.getString("account");
		Dto userDto = Dtos.newDto("account", account);
		userDto.put("is_del", IMSCons.IS.NO);
		UserPO userPO = userMapper.selectOne(userDto);
		if (IMSUtils.isEmpty(userPO)) {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("用户账号输入错误，请重新输入。");
			return outDto;
		}
		String status = userPO.getStatus();
		if (DicCons.USER_STATUS_LOCK.equals(status)) {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("该用户帐号已被锁定，系统拒绝登录，请联系管理员。");
			return outDto;
		}
		if (DicCons.USER_STATUS_STOP.equals(status)) {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("该用户帐号人员已停用，系统拒绝登录，请联系管理员。");
			return outDto;
		}
		String password = inDto.getString("password");
		String decryptPassword = IMSCodec.decrypt(userPO.getPassword(), IMSCons.PASSWORD_KEY);
		String user_id = userPO.getUser_id();
		if (password.equals(decryptPassword)) { // 判断密码是否一致
			UserPO errorUser = new UserPO();
			errorUser.setError_num(0);
			errorUser.setUser_id(user_id);
			errorUser.setModify_user_id(user_id);
			errorUser.setModify_time(IMSUtils.getDateTime());
			userMapper.updateByKey(errorUser);
			// 超级用户没有角色
			if (IMSCons.SUPER_ADMIN.equals(account)) {
				outDto.setAppCode(IMSCons.SUCCESS);
				outDto.put("userPO", userPO);
				outDto.setAppMsg("登陆成功。");
			} else {
				RolePO rolePO = queryRoleByUserId(user_id);
				if (IMSUtils.isEmpty(rolePO)) { // 判断该角色是否授予角色权限
					outDto.setAppCode(IMSCons.WARN);
					outDto.setAppMsg("该用户帐号未授予角色权限，系统拒绝登录，请联系管理员。");
				} else {
					int count =queryCountByRoleId(rolePO.getRole_id());
					if(count>0){
						outDto.setAppCode(IMSCons.SUCCESS);
						outDto.put("userPO", userPO);
						outDto.setAppMsg("登陆成功。");
					}else{
						outDto.setAppCode(IMSCons.WARN);
						outDto.put("userPO", userPO);
						outDto.setAppMsg("该用户所在的角色未授予菜单权限，系统拒绝登录，请联系管理员。");
					}
					
				}

			}

		} else {
			// 当前错误次数=错误次数+1;
			Integer current_error_num = userPO.getError_num() + 1;
			// 锁定次数
			Integer lock_num = userPO.getLock_num();
			// 更新错误次数
			UserPO errorUser = new UserPO();
			errorUser.setError_num(current_error_num);
			errorUser.setUser_id(user_id);
			errorUser.setModify_user_id(user_id);
			errorUser.setModify_time(IMSUtils.getDateTime());
			if (current_error_num >= lock_num) {
				errorUser.setStatus(DicCons.USER_STATUS_LOCK);
				outDto.setAppCode(IMSCons.WARN);
				outDto.setAppMsg("你已经连续输错密码" + current_error_num + "次，超过系统错误次数最大限制，系统自动锁定改账号，请联系管理员");
			} else {
				// 错误次数到达三次以上开始提醒
				if (current_error_num >= 3) {
					int end_num = lock_num - current_error_num;
					outDto.setAppCode(IMSCons.WARN);
					outDto.setAppMsg("你已经连续输错密码" + current_error_num + "次，如果再输错" + end_num + "次，系统自动锁定该账号，请慎重");
				} else {
					outDto.setAppCode(IMSCons.WARN);
					outDto.setAppMsg("用户密码输入错误，请输入正确密码");
				}

			}

			userMapper.updateByKey(errorUser);

		}

		return outDto;

	}

	/**
	 * 查询用户角色权限菜单
	 * 
	 * @param pDto
	 * @return
	 */
	public List<MenuPO> listUserRoleUser(Dto pDto) {
		pDto.put("menu_type",DicCons.MENU_TYPE_SYS);
		return sqlDao.list("System.listUserRoleMenu", pDto);

	}

	/**
	 * 获取用户授权的卡片菜单
	 * 
	 * @return
	 */
	public List<CardMenuPO> getGrantCardMenu(String user_id) {
		Dto firstDto = Dtos.newDto();
		firstDto.put("menu_type",DicCons.MENU_TYPE_SYS);
		firstDto.put("parent_id", IMSCons.TREE_ROOT_ID);
		firstDto.put("user_id", user_id);
		List<MenuPO> firstMenuList = listUserRoleUser(firstDto);
		Dto allDto = Dtos.newDto("user_id", user_id);
		// 获取用户所有所在角色所有菜单
		List<MenuPO> allRoleMenu = listUserRoleUser(allDto);
		List<CardMenuPO> cardMenus = new ArrayList<CardMenuPO>();
		for (int i = 0; i < firstMenuList.size(); i++) {
			MenuPO firstMenuPO = firstMenuList.get(i);
			String menu_id = firstMenuPO.getMenu_id();
			if (firstMenuPO.getChild_count() == 0) { // 过滤一级菜单没有子菜单的数据
				continue;
			}
			CardMenuPO cardMenuPO = new CardMenuPO();
			cardMenuPO.setMenuName(firstMenuPO.getMenu_name());
			String icon_name = firstMenuPO.getIcon_name();
			if (IMSUtils.isEmpty(icon_name)) {
				cardMenuPO.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
			} else {
				cardMenuPO.setIconCls(icon_name);
			}
			List<MenuPO> subMenu = new ArrayList<MenuPO>();
			for (int j = 0; j < allRoleMenu.size(); j++) {
				MenuPO roleMenu = allRoleMenu.get(j);
				if (menu_id.equals(roleMenu.getParent_id())) { // 判断是否属于他的子菜单
					if (IMSUtils.isEmpty(roleMenu.getIcon_name())) {
						roleMenu.setIcon_name(IMSCons.TREE_LEAF_INCONCLS);
					}
					subMenu.add(roleMenu);
				}
			}
			cardMenuPO.setSubMenu(subMenu);
			cardMenus.add(cardMenuPO);
		}
		return cardMenus;
	}

	/**
	 * 
	 * 简要说明：超级用户获取所有菜单权限 编写者：陈骑元 创建时间：2017年1月15日 下午1:41:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<CardMenuPO> getSuperCardMenu(String user_id) {
		Dto firstDto = Dtos.newDto();
		firstDto.put("menu_type",DicCons.MENU_TYPE_SYS);
		firstDto.put("parent_id", IMSCons.TREE_ROOT_ID);
		firstDto.setOrder("sort_no ASC ");
		firstDto.put("status", DicCons.ENABLED_YES);
		List<MenuPO> firstMenuList = menuMapper.list(firstDto);
		Dto allDto = Dtos.newDto("status", DicCons.ENABLED_YES);
		allDto.put("menu_type",DicCons.MENU_TYPE_SYS);
		if(StringUtils.equalsIgnoreCase(IMSCxt.getDbType(), DBType.SQLSERVER)){
			allDto.setOrder("LEN(cascade_id) ASC,sort_no ASC ");
		}else{
			allDto.setOrder("LENGTH(cascade_id) ASC,sort_no ASC ");
		}
		// 获取用户所有所在角色所有菜单
		List<MenuPO> allMenu = menuMapper.list(allDto);
		List<CardMenuPO> cardMenus = new ArrayList<CardMenuPO>();
		for (int i = 0; i < firstMenuList.size(); i++) {
			MenuPO firstMenuPO = firstMenuList.get(i);
			String menu_id = firstMenuPO.getMenu_id();
			if (firstMenuPO.getChild_count() == 0) { // 过滤一级菜单没有子菜单的数据
				continue;
			}
			CardMenuPO cardMenuPO = new CardMenuPO();
			cardMenuPO.setMenuName(firstMenuPO.getMenu_name());
			String icon_name = firstMenuPO.getIcon_name();
			if (IMSUtils.isEmpty(icon_name)) {
				cardMenuPO.setIconCls(IMSCons.TREE_LEAF_INCONCLS);
			} else {
				cardMenuPO.setIconCls(icon_name);
			}
			List<MenuPO> subMenu = new ArrayList<MenuPO>();
			for (int j = 0; j < allMenu.size(); j++) {
				MenuPO roleMenu = allMenu.get(j);
				if (menu_id.equals(roleMenu.getParent_id())) { // 判断是否属于他的子菜单
					if (IMSUtils.isEmpty(roleMenu.getIcon_name())) {
						roleMenu.setIcon_name(IMSCons.TREE_LEAF_INCONCLS);
					}
					subMenu.add(roleMenu);
				}
			}
			cardMenuPO.setSubMenu(subMenu);
			cardMenus.add(cardMenuPO);
		}
		return cardMenus;
	}

	/**
	 * 更新当前用户密码
	 * 
	 * @param inDto
	 * @return
	 */
	@Transactional
	public Dto updateUserPassword(Dto inDto, UserPO userPO) {
		Dto outDto = Dtos.newDto();
		String oldpwd = inDto.getString("oldpwd");
		String oldpassword = IMSCodec.encrypt(oldpwd, IMSCons.PASSWORD_KEY);
		if (userPO.getPassword().equals(oldpassword)) {
			String password = inDto.getString("password");
			String encryptPassword = IMSCodec.encrypt(password, IMSCons.PASSWORD_KEY);
			inDto.put("password", encryptPassword);
			inDto.put("user_id", userPO.getUser_id());
			int row = sqlDao.update("System.updatePassword", inDto);
			if (row > 0) {
				outDto.setAppCode(IMSCons.SUCCESS);
				outDto.setAppMsg("操作完成，密码修改成功。");
			} else {
				outDto.setAppCode(IMSCons.ERROR);
				outDto.setAppMsg("操作失败，密码修改失败。");
			}
		} else {
			outDto.setAppCode(IMSCons.WARN);
			outDto.setAppMsg("操作失败，原始密码不一致。");
		}

		return outDto;
	}

	/**
	 * 
	 * 简要说明：根据相应参数查询字典数据 编写者：陈骑元 创建时间：2016年12月20日 下午2:20:25
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<DictionaryPO> listDic(Dto pDto) {

		return sqlDao.list("System.listDic", pDto);
	}

	/**
	 * 
	 * 简要说明：根据查询获取键值参数信息 编写者：陈骑元 创建时间：2016年12月20日 下午7:48:17
	 * 
	 * @param 说明
	 * @return 说明
	 */
	public List<ParamPO> listParam(Dto pDto) {

		return sqlDao.list("System.listParam", pDto);
	}
	/**
	 * 
	 * 简要说明：查询角色授予菜单的数量
	 * 编写者：陈骑元
	 * 创建时间：2017年1月16日 下午6:04:50
	 * @param 说明
	 * @return 说明
	 */
	public int queryCountByRoleId(String role_id){
		
		return (Integer)sqlDao.selectOne("System.queryCountByRoleId", role_id);
		
	}
}
