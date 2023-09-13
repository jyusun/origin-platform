package com.jyusun.origin.base.security.handler;

import com.jyusun.origin.core.common.util.ArrayUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;

import java.util.Collection;

/**
 * 权限处理
 */
public class PermissionHandler {

    /**
     * 判断接口是否有任意xxx，xxx权限
     * @param permissions 权限
     * @return {boolean}
     */
    public boolean hasPermission(String... permissions) {
        if (ArrayUtil.isEmpty(permissions)) {
            return false;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .filter(StringUtil::hasText)
            .anyMatch(x -> PatternMatchUtils.simpleMatch(permissions, x));
    }

}
