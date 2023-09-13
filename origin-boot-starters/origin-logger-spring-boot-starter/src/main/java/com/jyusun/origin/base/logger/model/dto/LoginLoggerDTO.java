package com.jyusun.origin.base.logger.model.dto;

import com.jyusun.origin.base.logger.model.value.UserAgentValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户登录日志数据传输对象
 * <p>
 * 作用描述：接口请求
 * </p>
 *
 * @author jyusun
 * @date 2020/12/16 21:10
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
public class LoginLoggerDTO extends AbstractLogger {

    /**
     * 操作IP地址
     */
    @Schema(description = "远程地址")
    private String remoteAddress;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

    /**
     * 执行耗时
     */
    @Schema(description = "运行耗时")
    private Long timeCost;

    /**
     * 用户代理信息
     */
    @Schema(description = "用户代理")
    private UserAgentValue userAgent;

}
