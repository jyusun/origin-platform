package com.jyusun.origin.base.translate.desc;

import com.jyusun.origin.base.translate.annotation.Dict;

/**
 * 数值翻译
 *
 * @author jyusun at 2022-06-09 18:46:24
 */
public interface DescHandler {

    /**
     * 字典翻译
     * @param dict 字典注解
     * @param obj 数据值
     * @return 翻译标签
     */
    String handle(Dict dict, Object obj);

}
