package com.jyusun.origin.core.common.model.result;

import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UriUtil;
import com.jyusun.origin.core.common.util.WebUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

/**
 * 响应对象
 * <p>
 * 作用描述： - 基础响应对象
 *
 * @author jyusun at 2019/12/21 19:32
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@Schema(description = "响应结果")
@NoArgsConstructor
public class AbstractResult<T> implements Serializable {

    @Schema(description = "消息编号")
    protected String code;

    @Schema(description = "消息描述")
    protected String message;

    @Schema(description = "操作标记", example = "succ|warn|err")
    protected String type;

    @Schema(description = "承载数据")
    private T data;

    @Schema(description = "链接对象")
    private Links links;

    protected AbstractResult(String code, String message, String type, T data, String self) {
        this.init(code, message, type, data, self);
    }

    protected AbstractResult(String code, String message, String type, T data) {
        this.init(code, message, type, data, null);
    }

    protected AbstractResult(String code, String message, String type) {
        this(code, message, type, null);
    }

    /**
     * HttpServletRequest 中 获取请求链接
     * @return {@link Links} 链接信息
     */
    private static Links links(String self) {
        if (StringUtil.notHasText(self)) {
            self = Optional.of(WebUtil.getRequest())
                .map(request -> UriUtil.getPath(request.getRequestURI()))
                .orElse(StringUtil.EMPTY);
        }
        return Links.builder().self(self).build();
    }

    /**
     * 数据初始化
     * @param code {@code String } 响应编码
     * @param message {@code String } 响应消息
     * @param type {@code Boolean } 操作标记
     */
    private void init(String code, String message, String type, T data, String self) {
        this.code = code;
        this.message = message;
        this.type = type;
        this.data = data;
        this.links = links(self);

    }

}
