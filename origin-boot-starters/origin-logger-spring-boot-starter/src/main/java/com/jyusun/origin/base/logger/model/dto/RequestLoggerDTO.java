package com.jyusun.origin.base.logger.model.dto;

import com.jyusun.origin.base.logger.model.value.RequestValue;
import com.jyusun.origin.base.logger.model.value.ServerValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 日志数据传输对象
 * <p>
 * 作用描述：接口请求
 *
 * @author jyusun
 * @date 2020/12/16 21:10
 * @since 1.0.0
 */

@Data
@Accessors(chain = true)
public class RequestLoggerDTO extends AbstractLogger {

    /**
     * 操作类型
     */
    @Schema(description = "操作类型")
    private String operType;

    /**
     * 请求时间
     */
    @Schema(description = "请求时间")
    private LocalDateTime requestTime;

    /**
     * 执行耗时
     */
    @Schema(description = "运行耗时")
    private Long timeCost;

    /**
     * 请求信息
     */
    @Schema(description = "请求信息")
    private RequestValue requestInfo;

    /**
     * 服务信息
     */
    @Schema(description = "服务信息")
    private ServerValue serverInfo;

}
