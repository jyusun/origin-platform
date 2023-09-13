package com.jyusun.origin.base.mybatis.common.enums;

import com.jyusun.origin.core.common.constant.ResultConstant;
import com.jyusun.origin.core.common.model.BaseKvEnum;
import lombok.AllArgsConstructor;

/**
 * 响应结果
 * <p>
 * 作用描述：数据库操作相关
 * </p>
 *
 * @author jyusun
 * @date 2020/11/24 15:47
 * @since 1.0.0
 */
@AllArgsConstructor
public enum DbResultEnum implements BaseKvEnum {

    /**
     * 单页数据响应
     */
    MORE_THAN_THE_MAXIMUM(ResultConstant.MESSAGE_INTERNAL_SERVER_ERROR, "单页数据超过500，请调整"),
    /**
     * 当前操作数据不存在（可能被删除了）
     */
    DATA_NOT_EXIST(ResultConstant.MESSAGE_BAD_REQUEST, "当前操作数据不存在，请刷新页面");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 响应消息
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
