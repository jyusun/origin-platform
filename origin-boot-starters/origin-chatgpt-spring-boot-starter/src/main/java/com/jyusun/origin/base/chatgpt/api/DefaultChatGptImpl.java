package com.jyusun.origin.base.chatgpt.api;

import com.jyusun.origin.base.chatgpt.common.ChatGptConstant;
import com.jyusun.origin.base.chatgpt.config.props.ChatGptProperties;
import com.jyusun.origin.base.chatgpt.model.ChatModel;
import com.jyusun.origin.base.chatgpt.model.ChatParam;
import com.jyusun.origin.base.chatgpt.model.resp.ChatResponse;
import com.jyusun.origin.base.chatgpt.model.resp.ModelResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

/**
 * ChatGpt 实现
 *
 * @author jyusun at 2023-04-02 15:16:12
 */
@Component
@RequiredArgsConstructor
public class DefaultChatGptImpl implements ChatGptApi {

    private final RestTemplate restTemplate;

    private final ChatGptProperties chatGptProperties;

    /**
     * 模型列表
     * @return ModelResponse<ChatModel>
     */
    @Override
    public ModelResponse<ChatModel> findModels() {
        String url = chatGptProperties.getEndpoint() + ChatGptConstant.URL_MODELS;
        // 设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(chatGptProperties.getApiKey());
        // 设置组织
        // httpHeaders.set("OpenAI-Organization", "");

        ModelResponse<ChatModel> forObject = restTemplate.getForObject(url, ModelResponse.class);
        return forObject;
    }

    /**
     * 模型列表
     * @return ModelResponse<ChatModel>
     */
    @Override
    public ModelResponse<ChatModel> findModel(@NotBlank(message = "模型为必填项") String model) {
        String url = chatGptProperties.getEndpoint() + ChatGptConstant.URL_MODELS + "/" + model;
        // 设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(chatGptProperties.getApiKey());
        // 设置组织
        // httpHeaders.set("OpenAI-Organization", "");

        ModelResponse<ChatModel> forObject = restTemplate.getForObject(url, ModelResponse.class);
        return forObject;
    }

    /**
     * 创建完成
     * @param chatParam {@link ChatParam} 文本消息
     * @return ChatResponse
     */
    @Override
    @SneakyThrows
    public ChatResponse create(ChatParam chatParam) {

        String url = chatGptProperties.getEndpoint();

        // 设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(chatGptProperties.getApiKey());

        HttpEntity<ChatParam> httpEntity = new HttpEntity<>(chatParam, httpHeaders);

        ResponseEntity<ChatResponse> response = restTemplate.postForEntity(new URI(url), httpEntity,
                ChatResponse.class);

        System.out.println(Objects.requireNonNull(response.getBody()).getId());
        return response.getBody();
    }

}
