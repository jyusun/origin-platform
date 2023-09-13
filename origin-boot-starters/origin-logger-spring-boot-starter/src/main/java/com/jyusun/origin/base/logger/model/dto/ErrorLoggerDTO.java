package com.jyusun.origin.base.logger.model.dto;

import com.jyusun.origin.base.logger.model.value.ErrorValue;
import com.jyusun.origin.base.logger.model.value.RequestValue;
import com.jyusun.origin.base.logger.model.value.ServerValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 日志数据传输对象
 * <p>
 * 作用描述：异常信息
 * </p>
 *
 * @author jyusun
 * @date 2020/12/16 21:10
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ErrorLoggerDTO extends AbstractLogger {

    private static final long serialVersionUID = 1L;

    /**
     * 请求时间
     */
    @Schema(description = "请求时间")
    private LocalDateTime requestTime;

    /**
     * 请求信息
     */
    @Schema(description = "请求信息")
    private RequestValue requestInfo;

    /**
     * 错误信息
     */
    @Schema(description = "错误信息")
    private ErrorValue errorInfo;

    /**
     * 服务信息
     */
    @Schema(description = "服务信息")
    private ServerValue serverInfo;

}
