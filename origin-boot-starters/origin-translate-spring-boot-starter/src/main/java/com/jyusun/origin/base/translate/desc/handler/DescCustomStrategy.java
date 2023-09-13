package com.jyusun.origin.base.translate.desc.handler;

import com.jyusun.origin.base.translate.desc.DescHandler;
import com.jyusun.origin.base.translate.annotation.Dict;
import com.jyusun.origin.core.common.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义值
 *
 * @author jyusun at 2022-06-01 18:14:29
 */
@Component
public class DescCustomStrategy implements DescHandler {

    /**
     * 字典翻译
     * @param dict 字典注解
     * @param propertyValue 数据值
     * @return 翻译标签
     */
    @Override
    public String handle(Dict dict, Object propertyValue) {
        Map<String, String> labelMap = Arrays.stream(dict.customKv())
            .map(elem -> elem.split(StringUtil.DASHED))
            .collect(Collectors.toMap(e -> String.valueOf(e[0]), e -> String.valueOf(e[1])));
        return labelMap.get(String.valueOf(propertyValue));
    }

}
