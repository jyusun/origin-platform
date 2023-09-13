package com.jyusun.origin.base.logger.common.enums;

import com.jyusun.origin.core.common.model.BaseKvEnum;
import lombok.AllArgsConstructor;

/**
 * 日志操作 类型
 *
 * @author jyusun
 */
@AllArgsConstructor
public enum OperTypeEnum implements BaseKvEnum {

    /**
     * -1-未知类型
     */
    DEFAULT("-1", "未知类型"), PAGE("1", "数据分页"), DATAS("2", "数据集合"), DETAIL("3", "数据详情"), SAVE("4", "数据新增"),
    EDIT("5", "数据编辑"), DELETE("6", "数据删除"), FILE("7", "文件操作"), LOGIN("9", "用户登录"), EXTEND("0", "扩展类型");

    private final String code;

    private final String desc;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String desc() {
        return this.desc;
    }

}
