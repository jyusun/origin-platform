package com.jyusun.origin.base.chatgpt.model;

import lombok.Data;

/**
 * ChatGPT 模型
 *
 * @author jyusun at 2023-04-02 12:22:14
 */
@Data
public class ChatModel {

    private String id;

    private String object;

    private String ownedBy;

    private String[] permission;

}
