package com.jyusun.origin.base.translate.desc.handler;

import com.jyusun.origin.base.translate.desc.DescHandler;
import com.jyusun.origin.base.translate.annotation.Dict;
import com.jyusun.origin.core.common.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 布尔类型自定义转义
 *
 * @author jyusun at 2022-06-01 14:12:12
 */
@Component
public class DescBooleanStrategy implements DescHandler {

    /**
     * 字典翻译
     * @param dict 字典注解
     * @param obj 数据值
     * @return 翻译标签
     */
    @Override
    public String handle(Dict dict, Object obj) {
        Map<Boolean, String> labelMap = Arrays.stream(dict.customKv())
            .map(elem -> elem.split(StringUtil.DASHED))
            .filter(elem -> elem.length == 2)
            .collect(Collectors.toMap(e -> Boolean.parseBoolean(e[0]), e -> String.valueOf(e[1])));
        return labelMap.get(Boolean.parseBoolean(String.valueOf(obj)));

    }

}
