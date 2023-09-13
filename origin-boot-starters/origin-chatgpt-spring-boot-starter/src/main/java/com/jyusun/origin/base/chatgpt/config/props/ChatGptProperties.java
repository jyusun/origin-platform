package com.jyusun.origin.base.chatgpt.config.props;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ChatGPT 配置
 *
 * @author jyusun at 2023-04-02 14:15:18
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.chatgpt")
public class ChatGptProperties {

    private static final String DEF_ENDPOINT = "https://api.openai.com";

    private static final String DEF_URL = "/v1/completions";

    private static final String DEF_MODEL = "gpt-3.5-turbo";

    private static final Charset DEF_CHARSET = StandardCharsets.UTF_8;

    @Schema(description = "端点")
    private String endpoint;

    @Schema(description = "请求路径")
    private String url;

    @Schema(description = "请求密钥")
    private String apiKey;

    @Schema(description = "使用模型")
    private String model;

    @Schema(description = "字符集")
    private Charset charSet;

    public ChatGptProperties() {
        this.init();
    }

    public void init() {
        this.endpoint = DEF_ENDPOINT;
        this.url = DEF_URL;
        this.model = DEF_MODEL;
        this.charSet = DEF_CHARSET;

    }

}
