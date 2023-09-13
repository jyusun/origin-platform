package com.jyusun.origin.base.logger.model.value;

import com.jyusun.origin.core.common.model.domain.valueobject.BaseValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 服务信息
 *
 * @author jyusun at 10:32:39
 */
@Data
public class ServerValue implements BaseValue<ServerValue> {

    /**
     * 服务器名
     */
    @Schema(description = "服务器名")
    private String serverHost;

    /**
     * 服务器 ip
     */
    @Schema(description = "服务IP")
    private String serverIp;

    /**
     * 服务器端口
     */
    @Schema(description = "服务端口")
    private Integer serverPort;

    /**
     * 环境
     */
    @Schema(description = "运行环境")
    private String env;

    /**
     * 值对象通过属性比较，没有唯一ID
     * @param other 另外的值对象
     * @return <code>true</code> 属性比较一致时返回true
     */
    @Override
    public boolean sameValueAs(ServerValue other) {
        return false;
    }

}
