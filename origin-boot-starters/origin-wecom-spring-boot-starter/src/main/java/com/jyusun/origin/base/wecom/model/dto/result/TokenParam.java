package com.jyusun.origin.base.wecom.model.dto.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


@Data
public class TokenParam {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private Long expiresIn;
}
