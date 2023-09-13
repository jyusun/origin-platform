package com.jyusun.origin.core.common.enums;

import com.jyusun.origin.core.common.model.BaseKvEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultTypeEnum implements BaseKvEnum {

    /**
     * succ-操作成功，warn-警告信息，err-错误信息
     */
    SUCCESS("succ", "成功信息"), WARN("warn", "警告信息"), ERROR("err", "错误信息");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 消息描述
     */
    private final String desc;

    /**
     * 返回代码
     * @return int
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 返回消息
     * @return str
     */
    @Override
    public String desc() {
        return this.desc;
    }

}
