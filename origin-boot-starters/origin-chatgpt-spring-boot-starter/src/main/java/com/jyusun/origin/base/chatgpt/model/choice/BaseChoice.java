package com.jyusun.origin.base.chatgpt.model.choice;

import lombok.Data;

@Data
public class BaseChoice {

    public String text;

    private Integer index;

    private String logprons;

    private String finishReason;

}
