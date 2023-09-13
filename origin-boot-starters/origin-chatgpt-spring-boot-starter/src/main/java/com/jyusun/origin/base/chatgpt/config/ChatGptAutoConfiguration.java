package com.jyusun.origin.base.chatgpt.config;

import com.jyusun.origin.base.chatgpt.ChatGptTemplate;
import com.jyusun.origin.base.chatgpt.api.ChatGptApi;
import com.jyusun.origin.base.chatgpt.config.props.ChatGptProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * ChatGPT 自动配置
 *
 * @author jyusun at 2023-04-02 13:14:12
 */
@AutoConfiguration
@EnableConfigurationProperties(ChatGptProperties.class)
@RequiredArgsConstructor
public class ChatGptAutoConfiguration {

    private final ChatGptApi chatGptApi;

    public ChatGptTemplate chatGptTemplate() {
        return new ChatGptTemplate(chatGptApi);
    }

}
