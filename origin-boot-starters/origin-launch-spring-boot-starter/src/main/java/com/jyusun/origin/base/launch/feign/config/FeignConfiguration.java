package com.jyusun.origin.base.launch.feign.config;

import com.jyusun.origin.base.launch.feign.core.handle.ResponseResultDecoderHandler;
import com.jyusun.origin.base.launch.feign.core.interceptor.FeignRequestInterceptor;
import com.jyusun.origin.core.common.constant.SystemConstant;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作用描述： - Feign 配置
 *
 * @author jyusun at 2020/1/29 12:54
 * @since 1.0.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableFeignClients(basePackages = SystemConstant.BASE_FEIGN)
@RequiredArgsConstructor
public class FeignConfiguration {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

    /**
     * 重写Decoder解决接口统一响应问题
     * @param messageConverters
     * @return
     */
    @Bean
    public Decoder decoder(ObjectProvider<HttpMessageConverters> messageConverters) {
        return new ResponseResultDecoderHandler(new SpringDecoder(messageConverters));
    }

}
