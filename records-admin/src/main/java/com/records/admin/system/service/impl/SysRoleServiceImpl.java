package com.records.admin.system.service.impl;

import com.records.admin.system.entiey.SysMenu;
import com.records.admin.system.entiey.SysResource;
import com.records.admin.system.entiey.SysRole;
import com.records.admin.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author changfa
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-03-02 15:02:43
*/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Override
    public int create(SysRole role) {
        return 0;
    }

    @Override
    public int update(Long id, SysRole role) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<SysRole> list() {
        return null;
    }

    @Override
    public List<SysRole> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<SysMenu> getMenuList(Long adminId) {
        return null;
    }

    @Override
    public List<SysMenu> listMenu(Long roleId) {
        return null;
    }

    @Override
    public List<SysResource> listResource(Long roleId) {
        return null;
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        return 0;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        return 0;
    }
}




