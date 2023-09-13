package com.jyusun.origin.base.wecom.model.dto.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Access Token
 *
 * @author jyusun at 2022-4-12 22:56:15
 */
@Data
public class AccessTokenDTO {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private Long expiresIn;
}
