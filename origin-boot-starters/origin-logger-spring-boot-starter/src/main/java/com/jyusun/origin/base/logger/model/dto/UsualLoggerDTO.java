package com.jyusun.origin.base.logger.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 日志数据传输对象
 * <p>
 * 作用描述：常规日志
 * </p>
 *
 * @author jyusun
 * @date 2020/12/16 21:10
 * @since 1.0.0
 */

@Data
public class UsualLoggerDTO extends AbstractLogger {

    /**
     * 业务编号
     */
    @Schema(description = "业务编号")
    private Long bizId;

    /**
     * 数据库表
     */
    @Schema(description = "数据库表")
    private String tableName;

    /**
     * 详情描述
     */
    @Schema(description = "详情描述")
    private String description;

}
