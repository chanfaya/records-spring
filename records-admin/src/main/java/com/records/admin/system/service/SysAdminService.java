package com.records.admin.system.service;

import com.records.admin.system.dto.SysAdminParam;
import com.records.admin.system.dto.UpdateAdminPasswordParam;
import com.records.admin.system.entiey.SysAdmin;
import com.records.admin.system.entiey.SysResource;
import com.records.admin.system.entiey.SysRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author changfa
* @description 针对表【sys_admin(后台用户表，定义了后台用户的一些基本信息。)】的数据库操作Service
* @createDate 2023-03-02 15:01:11
*/
public interface SysAdminService {

    /**
     * 根据用户名获取后台管理员
     */
    SysAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    SysAdmin register(SysAdminParam SysAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    SysAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<SysAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, SysAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对应角色
     */
    List<SysRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<SysResource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取缓存服务
     */
    SysAdminCacheService getCacheService();

}
