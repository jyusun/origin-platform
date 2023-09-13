package com.jyusun.origin.base.logger.model.value;

import com.jyusun.origin.core.common.model.domain.valueobject.BaseValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 错误日志值对象
 *
 * @author jyusun at 2021-12-4 14:08:42
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class ErrorValue implements BaseValue<ErrorValue> {

    /**
     * 堆栈信息
     */
    @Schema(description = "堆栈信息")
    private String stackTrace;

    /**
     * 异常类型
     */
    @Schema(description = "异常类型")
    private String exceptionName;

    /**
     * 异常消息
     */
    @Schema(description = "异常消息")
    private String message;

    /**
     * 错误文件类
     */
    @Schema(description = "错误文件")
    private String fileName;

    /**
     * 错误文件 行数
     */
    @Schema(description = "错误行数")
    private Integer lineNumber;

    /**
     * 值对象通过属性比较，没有唯一ID
     * @param other 另外的值对象
     * @return <code>true</code> 属性比较一致时返回true
     */
    @Override
    public boolean sameValueAs(ErrorValue other) {
        return false;
    }

}
