package com.jyusun.origin.base.security.config;

import com.jyusun.origin.base.security.handler.PermissionHandler;
import org.springframework.context.annotation.Bean;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/6/5 16:55
 * @since 1.0.0
 */
public class AuthAutoConfiguration {

    /**
     * 权限处理
     * @return
     */
    @Bean("pmh")
    public PermissionHandler permissionHandler() {
        return new PermissionHandler();
    }

}
