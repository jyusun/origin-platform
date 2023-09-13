package com.jyusun.origin.base.chatgpt.model.resp;

import com.jyusun.origin.base.chatgpt.model.ChatError;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * ChatGPT 模型响应
 *
 * @param <T>
 */
@Data
public class ModelResponse<T> {

    @Schema(description = "数据类型")
    private String object;

    @Schema(description = "承载数据")
    private List<T> data;

    @Schema(description = "错误模型")
    private ChatError error;

}
