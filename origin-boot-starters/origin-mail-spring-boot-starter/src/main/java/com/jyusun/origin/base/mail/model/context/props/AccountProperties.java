package com.jyusun.origin.base.mail.model.context.props;

import lombok.Data;

/**
 * 作用描述：账户信息
 *
 * @author jyusun at 2023/5/2 16:06
 * @since 1.0.0
 */
@Data
public class AccountProperties {

    private Long acctId;

    private String nickname;

    private String address;

    private String password;

}
