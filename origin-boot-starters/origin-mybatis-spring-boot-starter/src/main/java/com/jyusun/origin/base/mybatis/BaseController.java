package com.jyusun.origin.base.mybatis;

import com.jyusun.origin.base.mybatis.data.BaseData;
import com.jyusun.origin.core.common.model.page.PageObject;
import com.jyusun.origin.core.common.model.page.PageQuery;
import com.jyusun.origin.core.common.model.result.AbstractResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

/**
 * 请求控制
 * <p>
 * 作用描述：通用请求控制，提供了基础的CRUD操作
 * </p>
 *
 * @author jyusun
 * @date 2020/10/15 17:24
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class BaseController<R extends CoreRepository<Domain>, Domain extends BaseData> {

    private final R originRepository;

    /**
     * 列表分页查询
     * @param pageQuery {@link PageQuery} 分页参数查询对象
     * @param domain {@link Domain} 系统参数查询对象
     * @return {@link AbstractResult} 响应结果
     */
    @Operation(summary = "分页查询：分页条件")
    @GetMapping
    public PageObject<Domain> pageQuery(PageQuery pageQuery, Domain domain) {
        return this.originRepository.pageQuery(pageQuery, domain);
    }

    /**
     * 根据ID查询
     * @param sid {@code Serializable } 主键编号
     * @return {@link AbstractResult} 响应结果
     */
    @Operation(summary = "数据查询：主键编号")
    @Parameter(name = "sid", description = "主键编号", in = ParameterIn.PATH)
    @GetMapping("{sid}")
    public Domain findById(@PathVariable Serializable sid) {
        return this.originRepository.getById(sid);
    }

    /**
     * 删除数据
     * @param sid {@code Serializable} 主键编号
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    @Operation(summary = "数据删除：主键编号")
    @Parameter(name = "sid", description = "主键编号", in = ParameterIn.PATH)
    @DeleteMapping("{sid}")
    public Boolean removeById(@PathVariable("sid") Serializable sid) {
        return this.originRepository.removeById(sid);
    }

    /**
     * 新增数据
     * @param domain 数据对象
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    @Operation(summary = "数据新增")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Boolean save(@Validated @RequestBody Domain domain) {
        return this.originRepository.save(domain);
    }

    /**
     * 更新数据
     * <p>
     * 全量更新数据
     * </p>
     * @param domain 数据对象
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    @Operation(summary = "数据更新：主键编号")
    @PutMapping
    public Boolean updateById(@Validated @RequestBody Domain domain) {
        return this.originRepository.updateById(domain);
    }

}
