package com.jyusun.origin.base.mybatis.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据对象
 * <p>
 * 作用描述： 基础数据对象 一般用于后台管理
 * </p>
 *
 * @author JyuSun at 2019/4/2 16:21
 * @version 1.0.0
 */
@Data
@Schema(description = "基础数据模型")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class BaseData extends Model<BaseData> {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @TableField("remarks")
    protected String remarks;

    /**
     * 创建人ID
     */
    @Schema(description = "创建人ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected Long createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 最后更新人ID
     */
    @Schema(description = "更新人ID")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    protected Long updateBy;

    /**
     * 最后更新时间
     */
    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    /**
     * 逻辑删除标识（逻辑删除即更新操作） 0-false-未删除,1-true-已删除
     */
    @Schema(description = "删除标记", example = "0-未删除|1-已删除")
    @TableLogic(value = "0", delval = "1")
    @TableField("deleted")
    protected Boolean deleted;

    /**
     * 乐观锁
     */
    @Schema(description = "乐观锁")
    @Version
    @TableField("revision")
    protected Integer revision;

}
