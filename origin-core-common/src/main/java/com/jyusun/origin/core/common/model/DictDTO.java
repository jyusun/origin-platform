package com.jyusun.origin.core.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 键值模型
 * <p>
 * 作用描述：Key Value 模型
 * </p>
 *
 * @author jyusun at 2020/8/25 14:50
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictDTO extends BaseTreeDTO<DictDTO> implements BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String dictCode;

    /**
     * 键值
     */
    @Schema(description = "键")
    private String dictValue;

    /**
     * 描述
     */
    @Schema(description = "值")
    private String dictDesc;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 默认值
     */
    @Schema(description = "默认值")
    private Boolean defMark;

}
