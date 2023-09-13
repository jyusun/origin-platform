package com.jyusun.origin.base.mybatis.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyusun.origin.base.mybatis.common.enums.DbResultEnum;
import com.jyusun.origin.core.common.exception.ExceptionFactory;
import com.jyusun.origin.core.common.model.page.PageQuery;
import com.jyusun.origin.core.common.util.ObjectUtil;
import lombok.experimental.UtilityClass;

/**
 * 作用描述： - 条件处理
 *
 * @author jyusun at 2020/5/21 17:05
 * @since 1.0.0
 */
@UtilityClass
public class ConditionUtil {

    private static final Integer MAX_LIMIT = 500;

    public static <T> QueryWrapper<T> queryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 作用描述： - 获取分页参数转换
     * @param query {@link PageQuery} 查询参数
     * @return Page<T>
     */
    public static <T> Page<T> pageInfo(PageQuery query) {
        if (query.getPageSize() > MAX_LIMIT) {
            throw ExceptionFactory.createBiz(DbResultEnum.MORE_THAN_THE_MAXIMUM);
        }
        Page<T> page = new Page<>(query.getPageIndex(), query.getPageSize());
        String[] arrayAsc = query.getAscs();
        if (ObjectUtil.isNotEmpty(arrayAsc)) {
            page.addOrder(OrderItem.ascs(arrayAsc));
        }
        String[] arrayDesc = query.getDescs();
        if (ObjectUtil.isNotEmpty(arrayDesc)) {
            page.addOrder(OrderItem.descs(arrayDesc));
        }
        return page;
    }

}
