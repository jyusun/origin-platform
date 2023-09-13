package com.jyusun.origin.base.translate.desc;

import com.jyusun.origin.base.translate.annotation.Dict;
import lombok.RequiredArgsConstructor;

/**
 * 描述处理上下文
 *
 * @author jyusun at 2022-06-10 18:50:49
 */
@RequiredArgsConstructor
public class DescContext {

    private final DescHandler descHandler;

    public String getDesc(Dict dict, Object obj) {
        return descHandler.handle(dict, obj);
    }

}
