package com.jyusun.origin.base.logger.model.value;

import com.jyusun.origin.core.common.model.domain.valueobject.BaseValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * SQL日志值对象
 *
 * @author jyusun at 2021-12-4 14:08:42
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class SqlValue implements BaseValue<SqlValue> {

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
     * 堆栈信息
     */
    @Schema(description = "运行SQL")
    private String execSql;

    /**
     * 请求的参数
     */
    private Map<String, Object> params;

    /**
     * 值对象通过属性比较，没有唯一ID
     * @param other 另外的值对象
     * @return <code>true</code> 属性比较一致时返回true
     */
    @Override
    public boolean sameValueAs(SqlValue other) {
        return false;
    }

}
