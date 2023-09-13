package com.jyusun.origin.core.common.model.result;

import com.jyusun.origin.core.common.enums.ResultTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 作用描述： - 错误信息响应
 *
 * @author jyusun at 2020/2/20 17:36
 * @since 1.0.0
 */
@Getter
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "错误对象")
public class ErrorResult extends AbstractResult<Object> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "错误标题")
    private String title;

    @Schema(description = "错误明细")
    private String detail;

    public ErrorResult(String code, String message, String title, String detail) {
        super(code, message, ResultTypeEnum.ERROR.code(), false);
        this.init(title, detail);
    }

    /**
     * 错误结果初始化
     * @param title {@code String} 响应标题
     * @param detail {@code String} 响应明细
     */
    private void init(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

}
