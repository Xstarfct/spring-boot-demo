package com.fct.domain.bo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fct.core.domain.UmsAdminDO;
import com.fct.core.domain.UmsPermissionDO;

/**
 * SpringSecurity需要的用户详情
 *
 * @author xstarfct
 * @version 2019/1/11 3:37 PM
 */
public class ApiUserDetailBO implements UserDetails {
    private static final long serialVersionUID = -7251856634719164955L;

    private UmsAdminDO            umsAdminDO;
    private List<UmsPermissionDO> permissionList;

    public ApiUserDetailBO(UmsAdminDO umsAdminDO, List<UmsPermissionDO> permissionList) {
        this.umsAdminDO = umsAdminDO;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdminDO.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdminDO.getUsername();
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
        // 状态为1的用户是可用
        return new Integer(1).equals(umsAdminDO.getStatus());
    }

    @Override
    public String toString() {
        return "ApiUserDetailBO{" +
                "umsAdminDO=" + umsAdminDO +
                ", permissionList=" + permissionList +
                '}';
    }
}
