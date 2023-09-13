package com.jyusun.origin.base.translate.config;

import com.jyusun.origin.base.translate.desc.TranslateHandler;
import com.jyusun.origin.base.translate.aspect.TranslateAspect;
import com.jyusun.origin.base.translate.desc.handler.DescBooleanStrategy;
import com.jyusun.origin.base.translate.desc.handler.DescCustomStrategy;
import com.jyusun.origin.base.translate.desc.handler.DescExtendStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 翻译注册
 *
 * @author jyusun at 2022-1-30 11:24:43
 */
@Slf4j
@RequiredArgsConstructor
public class TranslateAutoConfiguration {

    private final DescBooleanStrategy descBooleanStrategy;

    private final DescCustomStrategy descCustomStrategy;

    private final DescExtendStrategy descExtendStrategy;

    @Bean
    public TranslateHandler translateHandler() {
        return new TranslateHandler(descBooleanStrategy, descCustomStrategy, descExtendStrategy);
    }

    @Bean
    @ConditionalOnMissingBean(TranslateHandler.class)
    public TranslateAspect translateAspect() {
        return new TranslateAspect();
    }

}
