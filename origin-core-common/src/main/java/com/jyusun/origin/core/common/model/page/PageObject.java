package com.jyusun.origin.core.common.model.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 响应对象：分页数据对象
 *
 * @param <T> 泛型标记
 * @author jyusun at 2019/4/14 12:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public final class PageObject<T extends Serializable> implements Serializable {

    /** 响应数据行结果 */
    @Schema(description = "响应数据")
    private List<T> rows;

    /** 响应数据总条数 */
    @Schema(description = "总条数")
    private Long total;

    /** 响应数据总页数 */
    @Schema(description = "总页数")
    private Long pages;

    /** 当前页 */
    @Schema(description = "当前页")
    private Long pageIndex;

    /** 每页条数 */
    @Schema(description = "每页条数")
    private Long pageSize;

}