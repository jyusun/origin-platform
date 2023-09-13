package com.jyusun.origin.base.mybatis.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jyusun.origin.core.common.model.page.PageObject;
import com.jyusun.origin.core.common.util.BeanUtil;
import com.jyusun.origin.core.common.util.CollectionUtil;
import lombok.experimental.UtilityClass;
import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 操作Page工具类
 * </p>
 *
 * @author Sun
 */
@UtilityClass
public class PageUtil {

    /**
     * 获取分页返回值，传入处理后的集合数据
     * @param page 分页信息
     * @param <T> 分页限定类型
     * @return <T, R> PageDTO<R>
     */
    public static <T extends Serializable> PageObject<T> dataInfo(IPage<T> page) {
        PageObject<T> pageObject = new PageObject<>();
        pageObject.setRows(CollectionUtil.isEmpty(page.getRecords()) ? Collections.emptyList() : page.getRecords());
        pageObject.setPageSize(page.getSize());
        pageObject.setPageIndex(page.getCurrent());
        pageObject.setTotal(page.getTotal());
        pageObject.setPages(page.getPages());
        return pageObject;
    }

    /**
     * 获取分页返回值，传入处理后的集合数据
     * @param page 分页信息
     * @param <T> 分页限定类型
     * @return <T, R> PageDTO<R>
     */
    public static <T extends Serializable, R extends Serializable> PageObject<R> dataInfo(IPage<T> page,
            Class<R> clazz) {
        PageObject<R> pageObject = new PageObject<>();
        pageObject.setRows(CollectionUtil.isEmpty(page.getRecords()) ? Collections.emptyList()
                : BeanUtil.copyList(page.getRecords(), clazz));
        pageObject.setPageSize(page.getSize());
        pageObject.setPageIndex(page.getCurrent());
        pageObject.setTotal(page.getTotal());
        pageObject.setPages(page.getPages());

        return pageObject;
    }

    /**
     * 获取分页返回值，传入处理后的集合数据
     * @param page 分页信息
     * @param <T> 分页限定类型
     * @return <T, R> PageDTO<R>
     */
    public static <D extends Serializable, T extends Converter<D, T> & Serializable> PageObject<T> dataConvert(
            IPage<D> page, Class<T> clazz) {
        PageObject<T> pageObject = new PageObject<>();
        List<T> dataConvers = Optional.ofNullable(page.getRecords())
            .orElse(Collections.emptyList())
            .stream()
            .map(datas -> BeanUtil.newInstance(clazz).convert(datas))
            .collect(Collectors.toList());

        pageObject.setRows(dataConvers);
        pageObject.setPageSize(page.getSize());
        pageObject.setPageIndex(page.getCurrent());
        pageObject.setTotal(page.getTotal());
        pageObject.setPages(page.getPages());
        return pageObject;
    }

}
