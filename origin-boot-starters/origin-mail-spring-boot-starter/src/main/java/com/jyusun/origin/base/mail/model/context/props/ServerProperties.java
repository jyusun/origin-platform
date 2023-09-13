package com.jyusun.origin.base.mail.model.context.props;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/5/2 16:09
 * @since 1.0.0
 */
@Data
public class ServerProperties {

    @Schema(description = "服务编号")
    private Long serverId;

    @Schema(description = "服务名称")
    private String serverName;

    @Schema(description = "服务地址")
    private String host;

    @Schema(description = "服务端口")
    private Integer port;

    @Schema(description = "SSL端口")
    private String sslPort;

}
