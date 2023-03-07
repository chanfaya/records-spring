package com.records.admin.system.service;

import com.records.admin.system.dto.SysMenuNode;
import com.records.admin.system.entiey.SysMenu;

import java.util.List;

/**
* @author changfa
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-03-02 15:02:43
*/
public interface SysMenuService {

    /**
     * 创建后台菜单
     */
    int create(SysMenu SysMenu);

    /**
     * 修改后台菜单
     */
    int update(Long id, SysMenu SysMenu);

    /**
     * 根据ID获取菜单详情
     */
    SysMenu getItem(Long id);

    /**
     * 根据ID删除菜单
     */
    int delete(Long id);

    /**
     * 分页查询后台菜单
     */
    List<SysMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     */
    List<SysMenuNode> treeList();

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Long id, Integer hidden);

}
