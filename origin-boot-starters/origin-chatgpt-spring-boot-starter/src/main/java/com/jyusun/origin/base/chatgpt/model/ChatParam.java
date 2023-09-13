package com.jyusun.origin.base.chatgpt.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT 请求参数
 *
 * @author jyusun at 2023-04-02 15:15:33
 */
@Data
public class ChatParam {

    @Schema(description = "模型")
    @NotBlank(message = "模型为必填项")
    private String model;

    private String prompt;

    @Schema(description = "文本后缀")
    private String suffix;

    @Schema(description = "最大令牌数")
    private Integer maxTokens;

    @Schema(description = "消息集合")
    private List<ChatMessage> messages;

    /**
     * 使用什么采样温度，介于 0 和 2 之间。较高的值（如 0.8）将使输出更加随机，而较低的值（如 0.2）将使输出更加集中和确定。
     * 我们通常建议改变这个或top_p但不是两者。
     */
    @Schema(description = "温度", defaultValue = "1")
    private Double temperature;

    /**
     * 一种替代温度采样的方法，称为核采样，其中模型考虑具有 top_p 概率质量的标记的结果。所以 0.1 意味着只考虑构成前 10% 概率质量的标记。
     */
    @Schema(description = "采样", defaultValue = "1")
    private Double topP;

    /**
     * 为每个提示生成多少完成。
     * <p>
     * 注意：因为这个参数会产生很多完成，它会很快消耗你的令牌配额。请谨慎使用并确保您对max_tokens和进行了合理的设置stop。
     */
    @Schema(description = "个数", defaultValue = "1")
    private Integer n;

    @Schema(description = "逻辑偏差")
    private Map<String, Object> logitBias;

    @Schema(description = "用户标识")
    private String user;

}
