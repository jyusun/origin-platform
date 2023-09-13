package com.jyusun.origin.base.logger.model.value;

import com.jyusun.origin.core.common.model.domain.valueobject.BaseValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户代理信息
 *
 * @author jyusun
 * @date 2021-6-8 13:47:28
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class UserAgentValue implements BaseValue<UserAgentValue> {

    @Schema(description = "浏览器厂商")
    private String browserManufacturer;

    @Schema(description = "浏览器组")
    private String browserGroup;

    @Schema(description = "浏览器名称")
    private String browserName;

    @Schema(description = "浏览器类型")
    private String browserType;

    @Schema(description = "浏览器版本")
    private String browserVersion;

    @Schema(description = "设备厂商")
    private String deviceManufacturer;

    @Schema(description = "设备类型")
    private String deviceType;

    @Schema(description = "设备操作系统")
    private String os;

    @Schema(description = "操作系统名称")
    private String osName;

    @Schema(description = "操作系统版本号", example = "32或64")
    private String osVersion;

    @Schema(description = "操作系统浏览器渲染引擎")
    private String browserRenderingEngine;

    /**
     * 值对象通过属性比较，没有唯一ID
     * @param other 另外的值对象
     * @return <code>true</code> 属性比较一致时返回true
     */
    @Override
    public boolean sameValueAs(UserAgentValue other) {
        return false;
    }

}
