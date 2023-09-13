package com.jyusun.origin.base.wecom.client;

import com.jyusun.origin.base.wecom.config.props.WecomProperties;
import com.jyusun.origin.base.wecom.model.dto.result.AccessTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

/**
 * @author jyusun
 */
@RequiredArgsConstructor
public class WecomAccessTokenClient {

    private final RestTemplate restTemplate;
    private final WecomProperties wecomProperties;

    private static final String URL_GET_TOKEN = "/gettoken?corpid={corpid}&corpsecret={corpsecret}";

    private String getUrl(String path) {
        return wecomProperties.getEndpoint() + path;
    }

    /**
     * 获取Token信息
     *
     * @return token
     */
    public AccessTokenDTO getToken() {
        return restTemplate.getForObject(this.getUrl(URL_GET_TOKEN), AccessTokenDTO.class,
                wecomProperties.getCorpid(),
                wecomProperties.getCorpsecret());
    }

}
