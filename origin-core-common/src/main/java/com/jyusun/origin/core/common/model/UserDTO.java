package com.jyusun.origin.core.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author jyusun at 17:19:42
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserDTO implements BaseDTO {

    /**
     * 租户ID
     */
    @Schema(description = "租户编号", hidden = true)
    private String tenantId;

    /**
     * 用户id
     */
    @Schema(description = "用户编号", hidden = true)
    private Long userId;

    /**
     * 用户编号（自定义的代码用于查询和展示）
     */
    @Schema(description = "用户代码", hidden = true)
    private String userCode;

    /**
     * 昵称
     */
    @Schema(description = "用户昵称", hidden = true)
    private String nickname;

}
