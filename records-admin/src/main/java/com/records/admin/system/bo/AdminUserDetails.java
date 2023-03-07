package com.records.admin.system.bo;

import com.records.admin.system.entiey.SysAdmin;
import com.records.admin.system.entiey.SysResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * SpringSecurity需要的用户信息封装类
 */
public class AdminUserDetails implements UserDetails {
    //后台用户
    private final SysAdmin SysAdmin;
    //拥有资源列表
    private final List<SysResource> resourceList;

    public AdminUserDetails(SysAdmin SysAdmin, List<SysResource> resourceList) {
        this.SysAdmin = SysAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return SysAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return SysAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SysAdmin.getStatus().equals(1);
    }
}

