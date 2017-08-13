package com.ims.common.system.modules.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ims.common.core.asset.IMSUtils;
import com.ims.common.core.matatype.Dto;
import com.ims.common.core.id.IMSId;
import com.beauty.common.constant.BeautyCons;
import com.google.common.collect.Lists;
import com.ims.common.core.asset.IMSCons;
import com.ims.common.core.asset.IMSCxt;
import com.ims.common.core.matatype.Dtos;
import com.ims.common.system.modules.mapper.WechatMenuMapper;
import com.ims.common.system.modules.po.WechatMenuPO;

/**
 * 
 * 类描述：<b>微信菜单信息[wechat_menu业务逻辑</b> 创建人：陈骑元 邮箱：240823329@qq.com 创建时间：2017-05-02
 * 12:29:51 修改人： 修改时间： 修改备注：
 * 
 * @version 1.0
 */
@Service
public class WechatMenuService {

	@Autowired
	private WechatMenuMapper wechatMenuMapper;

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p>
	 * 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param wechatMenuPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insert(WechatMenuPO wechatMenuPO) {

		return wechatMenuMapper.insert(wechatMenuPO);

	};

	/**
	 * 保存一个持久化对象 并返回一个Dto对象
	 * <p>
	 * 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param inDto
	 *            Dto参数对象
	 * 
	 * @return 返回Dto对象
	 */
	@Transactional
	public Dto save(Dto inDto) {
		Dto outDto = Dtos.newDto();
		String parent_id = inDto.getString("parent_id");
		int max = IMSCons.WECHAT_SECOND_MENU_MAX;
		String warnMsg = "微信二级菜单最多允许" + max + "个";
		if (IMSCons.TREE_ROOT_ID.equals(parent_id)) {
			max = IMSCons.WECHAT_FIRST_MENU_MAX;
			warnMsg = "微信一级菜单最多允许" + max + "个";
		}
		Dto countDto = Dtos.newDto();
		countDto.put("parent_id", parent_id);
		int count = wechatMenuMapper.rows(countDto);
		if (count >= max) {
			outDto.setAppMsg(warnMsg + "，请先删除其他无用的菜单再创建");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
		}
		WechatMenuPO wechatMenuPO = new WechatMenuPO();
		wechatMenuPO.setMenu_id(IMSId.uuid());
		IMSUtils.copyProperties(inDto, wechatMenuPO);
		wechatMenuPO.setCreate_time(IMSUtils.getDateTime());
		wechatMenuPO.setCreate_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
		wechatMenuPO.setModify_time(IMSUtils.getDateTime());
		wechatMenuPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
		int row = wechatMenuMapper.insert(wechatMenuPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，微信菜单信息新增成功,请点击<同步微信>按钮进行菜单更新");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，微信菜单信息新增失败。");
		}
		return outDto;

	};

	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param wechatMenuPO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	@Transactional
	public int insertAll(WechatMenuPO wechatMenuPO) {

		return wechatMenuMapper.insertAll(wechatMenuPO);

	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param wechatMenuPO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	@Transactional
	public int updateByKey(WechatMenuPO wechatMenuPO) {

		return wechatMenuMapper.updateByKey(wechatMenuPO);

	};

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param inDto传入参数
	 *            要修改的数据持久化对象
	 * @return Dto 返回影Dto对象
	 */
	@Transactional
	public Dto update(Dto inDto) {
		Dto outDto = Dtos.newDto();
		WechatMenuPO wechatMenuPO = new WechatMenuPO();
		IMSUtils.copyProperties(inDto, wechatMenuPO);
		wechatMenuPO.setModify_time(IMSUtils.getDateTime());
		wechatMenuPO.setModify_user_id(inDto.getString(IMSCons.LOGIN_USER_ID));
		int row = wechatMenuMapper.updateByKey(wechatMenuPO);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，微信菜单信息修改成功，请点击<同步微信>按钮进行菜单更新。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，微信菜单信息修改失败。");
		}
		return outDto;

	};

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return WechatMenuPO
	 */
	public WechatMenuPO selectByKey(String menu_id) {

		return wechatMenuMapper.selectByKey(menu_id);

	};

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return WechatMenuPO
	 */
	public WechatMenuPO selectOne(Dto pDto) {

		return wechatMenuMapper.selectOne(pDto);

	};

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<WechatMenuPO>
	 */
	public List<WechatMenuPO> list(Dto pDto) {

		return wechatMenuMapper.list(pDto);

	};

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<WechatMenuPO>
	 */
	public List<WechatMenuPO> listPage(Dto pDto) {

		return wechatMenuMapper.listPage(pDto);

	};

	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<WechatMenuPO>
	 */
	public List<WechatMenuPO> like(Dto pDto) {

		return wechatMenuMapper.like(pDto);

	};

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<WechatMenuPO>
	 */
	public List<WechatMenuPO> likePage(Dto pDto) {

		return wechatMenuMapper.likePage(pDto);

	};

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int deleteByKey(String menu_id) {

		return wechatMenuMapper.deleteByKey(menu_id);
	};

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto delete(String menu_id) {
		Dto outDto = Dtos.newDto();
		Dto countDto = Dtos.newDto();
		countDto.put("parent_id", menu_id);
		int count = wechatMenuMapper.rows(countDto);
		if (count > 0) {
			outDto.setAppMsg("该菜单存在二级菜单，请先删除二级菜单再允许删除");
			outDto.setAppCode(IMSCons.WARN);
			return outDto;
		}
		int row = wechatMenuMapper.deleteByKey(menu_id);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，微信菜单信息删除成功，请点击<同步微信>按钮进行菜单更新。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，微信菜单信息数据删除失败。");
		}
		return outDto;
	};

	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	@Transactional
	public int batchDeleteByKey(List<String> menu_idList) {

		return wechatMenuMapper.batchDeleteByKey(menu_idList);

	};

	/**
	 * 批量根据主键删除数据持久化对象
	 *
	 * @return 返回一个Dto对象
	 */
	@Transactional
	public Dto batchDelete(List<String> menu_idList) {
		Dto outDto = Dtos.newDto();
		int row = wechatMenuMapper.batchDeleteByKey(menu_idList);
		if (row > 0) {
			outDto.setAppCode(IMSCons.SUCCESS);
			outDto.setAppMsg("操作完成，批量微信菜单信息数据删除成功。");
		} else {
			outDto.setAppCode(IMSCons.ERROR);
			outDto.setAppMsg("操作失败，批量微信菜单信息数据删除失败。");
		}
		return outDto;

	};

	/**
	 * 根据Dto统计行数
	 * 
	 * @param pDto
	 * @return
	 */
	public int rows(Dto pDto) {

		return wechatMenuMapper.rows(pDto);

	};

	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	public String calc(Dto pDto) {

		return wechatMenuMapper.calc(pDto);

	};

	/**
	 * 查询分类科目信息并转化为树网格节点数据
	 * 
	 * @param pDto
	 * @return
	 */
	public List<Dto> listWechatMenu(Dto pDto) {
		pDto.setOrder(" sort_no ASC ");
		List<WechatMenuPO> menuPOList = wechatMenuMapper.list(pDto);
		List<Dto> treeNodes = new ArrayList<Dto>();
		for (int i = 0; i < menuPOList.size(); i++) {
			WechatMenuPO menuPO = menuPOList.get(i);
			int child_count = menuPO.getChild_count();
			Dto treeNode = Dtos.newDto();
			IMSUtils.copyProperties(menuPO, treeNode);
			treeNode.put("create_time", IMSUtils.date2String(menuPO.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
			treeNode.put("modify_time", IMSUtils.date2String(menuPO.getModify_time(), "yyyy-MM-dd HH:mm:ss"));
			if (child_count == 0) { // 子节点替换子节点图标
				treeNode.put("iconCls", IMSCons.TREE_LEAF_INCONCLS);
			}
			String is_auto_expand = menuPO.getIs_auto_expand();
			if (child_count > 0) { // 只有父节点才有闭合功能
				if (IMSCons.IS.NO.equals(is_auto_expand)) {
					treeNode.put("state", IMSCons.TREE_STATE_CLOSED);
				}
			}
			if (!IMSCons.TREE_ROOT_ID.equals(menuPO.getParent_id())) { // 不等于0的时候添加父类ID因为根节点不需要这个父类ID
				treeNode.put("_parentId", menuPO.getParent_id()); // 添加父类ID
			}

			treeNodes.add(treeNode);
		}

		return treeNodes;
	}

	/**
	 * 组合微信菜单结构
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto groupWechatMenu() {
		Dto returnDto = Dtos.newDto();
		Dto pDto = Dtos.newDto();
		pDto.put("parent_id", IMSCons.TREE_ROOT_ID);
		pDto.setOrder(" sort_no ASC ");
		List<WechatMenuPO> menuPOList = wechatMenuMapper.list(pDto);
		List<Dto> menuList = new ArrayList<Dto>();
		for (int i = 0; i < menuPOList.size(); i++) {
			Dto menuDto = Dtos.newDto();
			WechatMenuPO menuPO = menuPOList.get(i);
			int child_count = menuPO.getChild_count();
			menuDto.put("name", menuPO.getMenu_name());
			if (child_count > 0) {
				List<Dto> subMenuList = Lists.newArrayList();
				Dto childDto = Dtos.newDto();
				childDto.put("parent_id", menuPO.getMenu_id());
				childDto.setOrder(" sort_no ASC ");
				List<WechatMenuPO> childMenuList = wechatMenuMapper.list(childDto);
				for (int j = 0; j < childMenuList.size(); j++) {
					Dto subMenuDto = Dtos.newDto();
					WechatMenuPO childMenu = childMenuList.get(j);
					subMenuDto.put("name", childMenu.getMenu_name());
					subMenuDto.put("type", childMenu.getMenu_type());
					subMenuDto.put("key", childMenu.getMenu_type());
					String url = childMenu.getUrl();
					String media_id = childMenu.getMedia_id();
					if (IMSUtils.isNotEmpty(url)) {
						if (url.indexOf("http") == -1) {
							String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
							url = request_url + "/" + url;
						}
						subMenuDto.put("url", url);
					}
					if (IMSUtils.isNotEmpty(media_id)) {
						subMenuDto.put("media_id", media_id);
					}
					subMenuList.add(subMenuDto);
				}
				menuDto.put("sub_button", subMenuList);
			} else {
				menuDto.put("type", menuPO.getMenu_type());
				menuDto.put("key", menuPO.getMenu_type());
				String url = menuPO.getUrl();
				String media_id = menuPO.getMedia_id();
				if (IMSUtils.isNotEmpty(url)) {
					if (url.indexOf("http") == -1) {
						String request_url = IMSCxt.getParamValue(BeautyCons.REQUEST_URL_KEY); // 系统请求的地址
						url = request_url + "/" + url;
					}
					menuDto.put("url", url);
				}
				if (IMSUtils.isNotEmpty(media_id)) {
					menuDto.put("media_id", media_id);
				}
			}
			menuList.add(menuDto);
		}
		returnDto.put("button", menuList);
		return returnDto;
	}
}
