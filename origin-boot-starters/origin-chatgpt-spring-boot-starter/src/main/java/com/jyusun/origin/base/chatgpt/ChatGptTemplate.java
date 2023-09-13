package com.jyusun.origin.base.chatgpt;

import com.jyusun.origin.base.chatgpt.api.ChatGptApi;
import com.jyusun.origin.base.chatgpt.model.ChatParam;
import com.jyusun.origin.base.chatgpt.model.resp.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * ChatGPT
 *
 * @author jyusun at 2023-04-02 15:22:34
 */
@Component
@RequiredArgsConstructor
public class ChatGptTemplate {

    private final ChatGptApi chatGptApi;

    /**
     * 文本消息
     * @param chatParam 请求参数
     * @return
     */
    @SneakyThrows
    public String create(ChatParam chatParam) {
        ChatResponse chatResponse = chatGptApi.create(chatParam);
        return chatResponse.getChoices().get(0).getText();
    }

}
