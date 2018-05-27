package com.tonggu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tonggu.entity.MenuEntity;
import com.tonggu.exception.JJException;
import com.tonggu.repository.MenuRepository;
import com.tonggu.repository.RoleMenuRepository;
import com.tonggu.repository.custom.MenuRepositoryCustom;
import com.tonggu.service.MenuService;
import com.tonggu.util.BeanUtil;
import com.tonggu.util.Constant;
import com.tonggu.vo.ResultVo;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Autowired
	private MenuRepositoryCustom menuRepositoryCustom;
	
	@Override
	public ResultVo queryByRoleId(String roleId) {
		List<MenuEntity> menuList = roleMenuRepository.findByRoleId(roleId);
		return new ResultVo(true, "查询菜单成功", menuList);
	}

	@Override
	public ResultVo queryByUserId(String userId) {
		List<MenuEntity> menuList = roleMenuRepository.findByUserId(userId);
		return new ResultVo(true, "查询菜单成功", menuList);
	}

	@Transactional(readOnly = false, rollbackFor = JJException.class)
	@Override
	public ResultVo saveMenu(Map<String, Object> params)  throws JJException {
		if(!StringUtils.isEmpty((String)params.get("id"))) {
			return updateMenu(params);
		}
		MenuEntity menuEntity = BeanUtil.toBean(params, MenuEntity.class);
		if(StringUtils.isEmpty(menuEntity.getParentId()) || Constant.MENU_ROOT_ID.equals(menuEntity.getParentId())) { // 父级是根目录
			menuEntity.setMenuLevel(1);
			menuEntity.setParentId("0");
		}
		else {
			MenuEntity parentMenuEntity = menuRepository.findOne(menuEntity.getParentId());
			if(parentMenuEntity == null) {
				return new ResultVo(false, "所选父级菜单不存在");
			}
			menuEntity.setMenuLevel(parentMenuEntity.getMenuLevel() + 1);
		}
		menuEntity.setMenuType(Constant.MENU_TYPE_01);
		menuRepository.save(menuEntity);
		return new ResultVo(true, "保存成功");
	}

	@Transactional(readOnly = false, rollbackFor = JJException.class)
	@Override
	public ResultVo updateMenu(Map<String, Object> params)  throws JJException {
		MenuEntity menuEntity = menuRepository.findOne((String)params.get("id"));
		if(menuEntity == null) {
			return new ResultVo(false, "该菜单不存在");
		}
		MenuEntity newMenuEntity = BeanUtil.toBean(params, MenuEntity.class);
		if(StringUtils.isEmpty(newMenuEntity.getParentId()) || Constant.MENU_ROOT_ID.equals(newMenuEntity.getParentId())) { // 父级是根目录
			newMenuEntity.setMenuLevel(1);
			newMenuEntity.setParentId("0");
		}
		else {
			MenuEntity parentMenuEntity = menuRepository.findOne(newMenuEntity.getParentId());
			if(parentMenuEntity == null) {
				return new ResultVo(false, "所选父级菜单不存在");
			}
			newMenuEntity.setMenuLevel(parentMenuEntity.getMenuLevel() + 1);
		}
		BeanUtil.copyProperties(menuEntity, newMenuEntity);
		return new ResultVo(true, "更新成功");
	}

	@Transactional(readOnly = false, rollbackFor = JJException.class)
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo deleteMenu(Map<String, Object> params)  throws JJException {
		List<Map<String, Object>> delList = (List<Map<String, Object>>)params.get("delList");
		Set<String> set = Sets.newConcurrentHashSet();
		for(Map<String, Object> m : delList) {
			set.add((String)m.get("id"));
		}
		roleMenuRepository.batchDeleteByMenuId(set);
		menuRepository.batchDelete(set);
		
		return new ResultVo(true);
	}

	@Override
	public MenuEntity queryById(String id) {
		
		return menuRepository.findOne(id);
	}

	@Override
	public ResultVo queryAll(Map<String, Object> params) throws JJException {
		Map<String, Object> result = Maps.newConcurrentMap();
		Page<Map<String, Object>> page = menuRepositoryCustom.queryAll(params);
		result.put("iTotalDisplayRecords", page.getTotalElements());
		result.put("data", page.getContent());
		return new ResultVo(true, "查询菜单列表成功", result);
	}

}
