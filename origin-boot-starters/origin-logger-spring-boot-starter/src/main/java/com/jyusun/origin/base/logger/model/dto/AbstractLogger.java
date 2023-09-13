package com.jyusun.origin.base.logger.model.dto;

import com.jyusun.origin.core.common.model.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 作用描述：日志基础数据传输对象
 *
 * @author jyusun
 * @date 2020/5/18 12:01
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class AbstractLogger implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 应用名称
     */
    @Schema(description = "服务名称")
    private String serviceCode;

    /**
     * 日志主题
     */
    @Schema(description = "日志主题")
    private String title;

    /**
     * 方法类
     */
    @Schema(description = "方法类")
    private String className;

    /**
     * 方法名
     */
    @Schema(description = "方法名称")
    private String methodName;

    /**
     * 操作人ID
     */
    @Schema(description = "操作人ID")
    private Long operator;

}
