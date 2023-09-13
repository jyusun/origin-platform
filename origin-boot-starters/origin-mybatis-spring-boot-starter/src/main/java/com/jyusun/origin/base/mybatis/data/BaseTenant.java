package com.jyusun.origin.base.mybatis.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 数据对象
 * <p>
 * 作用描述： 基础租户数据对象
 * </p>
 *
 * @author JyuSun at 2019/4/2 16:21
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public abstract class BaseTenant extends BaseData {

    private static final long serialVersionUID = 1L;

    /**
     * 租户编号: 5位 10001
     */
    @TableField(value = "tenant_id", fill = FieldFill.INSERT)
    protected String tenantId;

}
