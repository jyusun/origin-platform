package com.jyusun.origin.base.mail.common.enums;

import com.jyusun.origin.core.common.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 作用描述：协议类型
 *
 * @author jyusun at 2023/5/2 19:14
 * @since 1.0.0
 */
@AllArgsConstructor
public enum ProtocolEnum {

    EMPTY(StringUtil.EMPTY), POP3("pop3"), IMAP("imap"), SMTP("smtp");

    @Getter
    private final String protocol;

    public static ProtocolEnum getEnum(String strEnum) {
        for (ProtocolEnum penum : ProtocolEnum.values()) {
            if (StringUtil.pathEquals(strEnum, penum.protocol)) {
                return penum;
            }
        }
        return EMPTY;
    }

}
