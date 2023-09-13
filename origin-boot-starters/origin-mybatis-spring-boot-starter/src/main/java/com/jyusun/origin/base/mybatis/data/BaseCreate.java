package com.jyusun.origin.base.mybatis.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据对象
 * <p>
 * 作用描述： 基础数据对象仅ID
 * </p>
 *
 * @author JyuSun at 2019/4/2 16:21
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class BaseCreate extends Model<BaseCreate> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键编号")
    @TableId(value = "sid", type = IdType.ASSIGN_ID)
    protected Long sid;

    /**
     * 创建人ID
     */
    @Schema(description = "创建人ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

}
