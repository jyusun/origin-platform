package com.jyusun.origin.core.common.util;

import com.jyusun.origin.core.common.model.UserDTO;
import lombok.experimental.UtilityClass;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 权限工具
 *
 * @author jyusun
 */
@UtilityClass
public class SecureUtil {

    /**
     * 获取用户信息
     * @param request {@link HttpServletRequest}
     * @return {@link UserDTO}
     */
    public UserDTO getUser(HttpServletRequest request) {
        String tenantId = "1001";
        long userId = 100001;
        String userCode = "100001";
        String account = "admin";
        String nickName = "管理员阿呆";
        return new UserDTO().setTenantId(tenantId).setUserId(userId).setUserCode(userCode).setNickname(nickName);
    }

    /**
     * 获取用户信息
     * @return {@link UserDTO}
     */
    public UserDTO getUser() {
        return getUser(WebUtil.getRequest());
    }

    /**
     * 获取用户信息ID信息
     * @return {@link UserDTO}
     */
    public Long getUserId() {
        return getUser().getUserId();
    }

}
