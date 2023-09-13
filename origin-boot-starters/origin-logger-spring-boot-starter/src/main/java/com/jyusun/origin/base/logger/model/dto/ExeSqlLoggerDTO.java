package com.jyusun.origin.base.logger.model.dto;

import com.jyusun.origin.base.logger.model.value.ServerValue;
import com.jyusun.origin.base.logger.model.value.SqlValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 日志数据传输对象
 * <p>
 * 作用描述：SQL 执行日志
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
public class ExeSqlLoggerDTO extends AbstractLogger {

    private static final long serialVersionUID = 1L;

    /**
     * SQL值对象
     */
    private SqlValue sqlValue;

    /**
     * 服务信息
     */
    @Schema(description = "服务信息")
    private ServerValue serverValue;

}
