package com.jyusun.origin.core.common.model;

import org.springframework.core.convert.converter.Converter;

/**
 * 数据装配 转换接口
 *
 * @param <S> 数据源
 * @param <T> 目标类型
 * @author jyusun at 2021-11-21 18:57:35
 * @since 1.0.0
 */
public interface BaseAssembler<S, T> extends Converter<S, T> {

}
