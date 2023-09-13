package com.jyusun.origin.base.translate.desc.handler;

import com.jyusun.origin.base.translate.TranslateProvider;
import com.jyusun.origin.base.translate.annotation.Dict;
import com.jyusun.origin.base.translate.desc.DescHandler;
import com.jyusun.origin.core.common.model.DictDTO;
import com.jyusun.origin.core.common.util.ObjectUtil;
import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 外部字典扩展处理
 *
 * @author sun
 */
@Component
public class DescExtendStrategy implements DescHandler {

    /**
     * 字典翻译
     * @param param 字典注解
     * @param propertyValue 数据值
     * @return 翻译标签
     */
    @Override
    public String handle(Dict param, Object propertyValue) {
        String split = param.split();
        TranslateProvider translateProvider = SpringUtil.getBean(param.customSource());

        String desc;
        if (!StringUtils.hasText(split)) {
            DictDTO dict = translateProvider.findDict(param.dictCode(), String.valueOf(propertyValue));
            desc = ObjectUtil.isNotEmpty(dict) ? dict.getDictDesc() : param.defValue();
        }
        else {
            List<DictDTO> items = translateProvider.findItems(param.dictCode());
            Map<String, String> dictMap = items.stream()
                .collect(Collectors.toMap(DictDTO::getDictValue, DictDTO::getDictDesc));
            String[] values = String.valueOf(propertyValue).split(split);
            desc = Arrays.stream(values)
                .map(obj -> StringUtils.hasText(dictMap.get(obj)) ? dictMap.get(obj) : param.defValue())
                .collect(Collectors.joining(StringUtil.COMMA));
        }
        return desc;
    }

}
