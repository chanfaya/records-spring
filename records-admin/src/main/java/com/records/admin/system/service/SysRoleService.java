package com.records.admin.system.service;

import com.records.admin.system.entiey.SysMenu;
import com.records.admin.system.entiey.SysResource;
import com.records.admin.system.entiey.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author changfa
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2023-03-02 15:02:43
*/
public interface SysRoleService {

    /**
     * 添加角色
     */
    int create(SysRole role);

    /**
     * 修改角色信息
     */
    int update(Long id, SysRole role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List<SysRole> list();

    /**
     * 分页获取角色列表
     */
    List<SysRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<SysMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<SysMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<SysResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

}
