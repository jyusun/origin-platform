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
public class ReceiveModeProperties {

    /**
     * 读取模式：1-全部读取|2-索引读取
     */
    @Schema(description = "读取模式")
    private String receiveMode;

    @Schema(description = "起始索引")
    private Integer startIndex;

    @Schema(description = "结束索引")
    private Integer endIndex;

}
