package com.jyusun.origin.base.chatgpt.api;

import com.jyusun.origin.base.chatgpt.model.ChatModel;
import com.jyusun.origin.base.chatgpt.model.ChatParam;
import com.jyusun.origin.base.chatgpt.model.resp.ChatResponse;
import com.jyusun.origin.base.chatgpt.model.resp.ModelResponse;

public interface ChatGptApi {

    /**
     * 模型列表
     * @return
     */
    ModelResponse<ChatModel> findModels();

    /**
     * 模型查询
     * @return ModelResponse<ChatModel>
     */
    ModelResponse<ChatModel> findModel(String model);

    /**
     * 发送消息
     * @param chatParam {@link ChatParam} 文本消息
     * @return ChatResponse
     */
    ChatResponse create(ChatParam chatParam);

}
