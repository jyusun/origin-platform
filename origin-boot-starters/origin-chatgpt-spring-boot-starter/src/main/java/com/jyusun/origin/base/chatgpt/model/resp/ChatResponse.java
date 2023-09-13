package com.jyusun.origin.base.chatgpt.model.resp;

import com.jyusun.origin.base.chatgpt.model.ChatUsage;
import com.jyusun.origin.base.chatgpt.model.choice.BaseChoice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * ChatGPT 响应
 *
 * @author jyusun at 2023-04-02 15:12:15
 */
@Data
public class ChatResponse {

    @Schema(description = "使用模型")
    private String id;

    @Schema(description = "使用模型")
    private String object;

    @Schema(description = "使用模型")
    private LocalDate created;

    @Schema(description = "使用模型")
    private String model;

    @Schema(description = "使用模型")
    private ChatUsage usage;

    @Schema(description = "使用模型")
    private List<BaseChoice> choices;

}
