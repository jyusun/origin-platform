package com.jyusun.origin.base.wecom.common.result;

import lombok.Data;

/**
 * 企业微信响应结果
 *
 * @author jyusun at 2022-4-12 23:07:18
 */
@Data
public class WocomResult {

    /**
     * 0-成功|其他-失败
     */
    private String errcode;

    /**
     * 描述信息
     */
    private String errmsg;


}
