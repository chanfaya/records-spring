package com.records.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.records.admin.system.entiey.SysAdminRole;
import com.records.admin.system.entiey.SysResource;
import com.records.admin.system.entiey.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author changfa
* @description 针对表【sys_admin_role】的数据库操作Mapper
* @createDate 2023-03-02 17:20:55
* @Entity com.records.admin.system.SysAdminRole
*/
@Mapper
public interface SysAdminRoleMapper extends BaseMapper<SysAdminRole> {

    List<Long> getAdminIdList(Long resourceId);

    List<SysResource> getResourceList(Long adminId);

    List<SysRole> getRoleList(Long adminId);

    void insertList(List<SysAdminRole> list);
}




