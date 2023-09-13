package com.jyusun.origin.base.chatgpt.model;

import lombok.Data;

/**
 * 作用描述：错误信息
 *
 * @author jyusun at 2023/4/11 17:20
 * @since 1.0.0
 */
@Data
public class ChatError {

    private String message;

    private String type;

    private Object param;

    private String code;

}
