package com.jyusun.origin.base.chatgpt.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChatMessage {

    @Schema(description = "角色（用户）")
    private String role;

    @Schema(description = "内容")
    private String content;

}
