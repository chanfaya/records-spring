package com.records.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.records.admin.system.bo.AdminUserDetails;
import com.records.admin.system.dto.SysAdminParam;
import com.records.admin.system.entiey.*;
import com.records.admin.system.dto.UpdateAdminPasswordParam;
import com.records.admin.system.mapper.SysAdminLoginLogMapper;
import com.records.admin.system.mapper.SysAdminMapper;
import com.records.admin.system.mapper.SysAdminRoleMapper;
import com.records.admin.system.service.SysAdminCacheService;
import com.records.admin.system.service.SysAdminService;
import com.records.common.util.RequestUtil;
import com.records.common.util.SpringUtil;
import com.records.security.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

/**
* @author changfa
* @description 针对表【sys_admin(后台用户表，定义了后台用户的一些基本信息。)】的数据库操作Service实现
* @createDate 2023-03-02 15:01:11
*/
@Slf4j
@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysAdminMapper adminMapper;
    @Autowired
    private SysAdminRoleMapper adminRoleRelationDao;
    @Autowired
    private SysAdminLoginLogMapper loginLogMapper;

    @Override
    public SysAdmin getAdminByUsername(String username) {
        SysAdmin admin = getCacheService().getAdmin(username);
        if(admin!=null) return  admin;
        List<SysAdmin> adminList = adminMapper.selectByMap(Map.of("username", username));
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            getCacheService().setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public SysAdmin register(SysAdminParam SysAdminParam) {
        SysAdmin sysAdmin = new SysAdmin();
        BeanUtils.copyProperties(SysAdminParam, sysAdmin);
        sysAdmin.setCreateTime(new Date());
        sysAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        List<SysAdmin> SysAdminList = adminMapper.selectByMap(Map.of("username", sysAdmin.getUsername()));
        if (SysAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysAdmin.getPassword());
        sysAdmin.setPassword(encodePassword);
        adminMapper.insert(sysAdmin);
        return sysAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
//                throw new A("密码不正确");
                log.info("密码不正确");
            }
            if(!userDetails.isEnabled()){
//                Asserts.fail("帐号已被禁用");
                log.error("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        SysAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        SysAdminLoginLog loginLog = new SysAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("username", username);
        wrapper.set("create_time", new Date());
        adminMapper.update(new SysAdmin(),wrapper);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshToken(oldToken);
    }

    @Override
    public SysAdmin getItem(Long id) {
        return adminMapper.selectById(id);
    }

    @Override
    public List<SysAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!keyword.isEmpty()) {
            queryWrapper.like("nick_name", keyword);
        }
        return adminMapper.selectList(queryWrapper);
    }

    @Override
    public int update(Long id, SysAdmin admin) {
        admin.setId(id);
        SysAdmin rawAdmin = adminMapper.selectById(id);
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{

            //与原加密密码不同的需要加密修改
            if(admin.getPassword().isEmpty()){
                admin.setPassword(null);
            }else{
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        int count = adminMapper.updateById(admin);
        getCacheService().delAdmin(id);
        return count;
    }

    @Override
    public int delete(Long id) {
        getCacheService().delAdmin(id);
        int count = adminMapper.deleteById(id);
        getCacheService().delResourceList(id);
        return count;
    }

     @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        adminRoleRelationDao.deleteByMap(Map.of("admin_id",adminId));
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysAdminRole> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                SysAdminRole roleRelation = new SysAdminRole();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        getCacheService().delResourceList(adminId);
        return count;
    }

    @Override
    public List<SysRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<SysResource> getResourceList(Long adminId) {
        List<SysResource> resourceList = getCacheService().getResourceList(adminId);
        if(!CollectionUtils.isEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if(!CollectionUtils.isEmpty(resourceList)){
            getCacheService().setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if(param.getUsername().isEmpty()
                ||param.getOldPassword().isEmpty()
                ||param.getNewPassword().isEmpty()){
            return -1;
        }
        List<SysAdmin> adminList = adminMapper.selectByMap(Map.of("username",param.getUsername()));
        if(CollectionUtils.isEmpty(adminList)){
            return -2;
        }
        SysAdmin SysAdmin = adminList.get(0);
        if(!passwordEncoder.matches(param.getOldPassword(),SysAdmin.getPassword())){
            return -3;
        }
        SysAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        adminMapper.updateById(SysAdmin);
        getCacheService().delAdmin(SysAdmin.getId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        SysAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<SysResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public SysAdminCacheService getCacheService() {
        return SpringUtil.getBean(SysAdminCacheService.class);
    }

}




