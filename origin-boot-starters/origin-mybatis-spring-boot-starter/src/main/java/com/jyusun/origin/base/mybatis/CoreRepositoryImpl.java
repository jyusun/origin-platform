package com.jyusun.origin.base.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jyusun.origin.base.mybatis.common.enums.DbResultEnum;
import com.jyusun.origin.base.mybatis.common.util.ConditionUtil;
import com.jyusun.origin.base.mybatis.common.util.PageUtil;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.exception.ValidateException;
import com.jyusun.origin.core.common.model.BaseKvEnum;
import com.jyusun.origin.core.common.model.page.PageObject;
import com.jyusun.origin.core.common.model.page.PageQuery;
import com.jyusun.origin.core.common.util.AssemblerUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;
import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;

/**
 * 作用描述： - 数据仓储基础服务实现
 *
 * @author JyuSun at 2019/3/29 11:15
 * @version 1.0.0
 */
public abstract class CoreRepositoryImpl<M extends BaseMapper<T>, T extends Model<?>> extends ServiceImpl<M, T>
        implements CoreRepository<T> {

    @Override
    public Boolean unique(Wrapper<T> wrapper, BaseKvEnum baseKvEnum) {
        if (this.count(wrapper) > 0) {
            throw new BusinessException(baseKvEnum.code(), baseKvEnum.desc());
        }
        return false;
    }

    /**
     * 分页查询
     * @param data 数据对象
     * @param pageQuery 分页条件对象
     * @return <T>
     */
    @Override
    public IPage<T> page(PageQuery pageQuery, T data) {
        return this.page(ConditionUtil.pageInfo(pageQuery), Wrappers.lambdaQuery(data));
    }

    /**
     * 按分页条件查询
     * @param data 数据对象
     * @param pageQuery 条件构造器
     * @return {@link PageObject} 目标类型
     */
    @Override
    public PageObject<T> pageQuery(PageQuery pageQuery, T data) {
        return PageUtil.dataInfo(this.page(pageQuery, data));
    }

    /**
     * 按分页条件查询
     * @param queryWrapper 条件构造器
     * @param pageQuery 条件构造器
     * @return IPage<D>
     */
    @Override
    public IPage<T> page(PageQuery pageQuery, Wrapper<T> queryWrapper) {
        return this.page(ConditionUtil.pageInfo(pageQuery), queryWrapper);
    }

    /**
     * 按分页条件查询
     * @param queryWrapper 条件构造器
     * @param pageQuery 条件构造器
     * @return {@link PageObject} 目标类型
     */
    @Override
    public PageObject<T> pageQuery(PageQuery pageQuery, Wrapper<T> queryWrapper) {
        return PageUtil.dataInfo(this.page(pageQuery, queryWrapper));
    }

    /**
     * 按分页条件查询
     * @param queryWrapper 条件构造器
     * @param pageQuery 条件构造器
     * @return {@link PageObject} 目标类型
     */
    @Override
    public <V extends Serializable> PageObject<V> pageQuery(PageQuery pageQuery, Wrapper<T> queryWrapper,
            Class<V> target) {
        return PageUtil.dataInfo(this.page(ConditionUtil.pageInfo(pageQuery), queryWrapper), target);
    }

    /**
     * 按分页条件查询
     * @param pageQuery 条件构造器
     * @param queryWrapper 条件构造器
     * @param target
     * @return {@link PageObject} 目标类型
     */
    @Override
    public <V extends Converter<T, V> & Serializable> PageObject<V> pageConvert(PageQuery pageQuery,
            Wrapper<T> queryWrapper, Class<V> target) {
        return PageUtil.dataConvert(this.page(ConditionUtil.pageInfo(pageQuery), queryWrapper), target);
    }

    /**
     * 数据查询
     * @param sid 主键编号
     * @param target 目标转换
     * @return <T>
     */
    @Override
    public <V> V getById(Serializable sid, Class<V> target) {
        T obj = this.getById(sid);
        if (ObjectUtil.isEmpty(obj)) {
            DbResultEnum resultEnum = DbResultEnum.DATA_NOT_EXIST;
            throw new ValidateException(resultEnum.code(), resultEnum.desc());
        }
        return AssemblerUtil.convert(obj, target);
    }

    @Override
    public boolean save(Object obj, Class<T> data) {
        return this.save(AssemblerUtil.convert(obj, data));
    }

    @Override
    public boolean updateById(Object obj, Class<T> data) {
        return this.updateById(AssemblerUtil.convert(obj, data));
    }

}
