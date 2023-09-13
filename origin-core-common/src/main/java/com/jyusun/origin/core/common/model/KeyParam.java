package com.jyusun.origin.core.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Key主键集合
 *
 * @author jyusun at 2023-01-23 17:21:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KeyParam {

    @Schema(description = "主键集合")
    private List<Long> ids;

}
