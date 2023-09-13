package com.jyusun.origin.core.common.model.event;

import com.jyusun.origin.core.common.model.BaseDTO;
import org.springframework.context.ApplicationListener;

/**
 * 基础监听
 *
 * @param <T> 泛型标记数据传输对象
 * @author jyusun at 2022-06-01 00:01:26
 */
public interface CoreListener<T extends BaseDTO> extends ApplicationListener<CoreEvent<T>> {

}
