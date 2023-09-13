package com.jyusun.origin.base.mail.common.enums;

import com.jyusun.origin.core.common.model.BaseKvEnum;
import com.jyusun.origin.core.common.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 作用描述：发送模式
 *
 * @author jyusun at 2023/5/2 19:14
 * @since 1.0.0
 */
@AllArgsConstructor
public enum ReceiveModeEnum implements BaseKvEnum {

    /**
     * 发送模式：
     */
    UNKNOWN("-1", "未知"),
    /**
     * 1-全部读取
     */
    RECEIVE_ALL("1", "全部读取"),
    /**
     * 2-部分读取
     */
    RECEIVE_PART("2", "部分读取");

    @Getter
    private final String code;

    private final String desc;

    public static ReceiveModeEnum getEnum(String strEnum) {
        for (ReceiveModeEnum modeEnum : ReceiveModeEnum.values()) {
            if (StringUtil.pathEquals(strEnum, modeEnum.code)) {
                return modeEnum;
            }
        }
        return UNKNOWN;
    }

    /**
     * 值
     * @return str
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 描述标签
     * @return str
     */
    @Override
    public String desc() {
        return this.desc;
    }

}
