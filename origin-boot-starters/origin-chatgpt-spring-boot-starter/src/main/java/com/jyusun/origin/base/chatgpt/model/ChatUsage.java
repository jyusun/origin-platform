package com.jyusun.origin.base.chatgpt.model;

import lombok.Data;

@Data
public class ChatUsage {

    private String promptTokens;

    private String completionTokens;

    private String totalTokens;

}
