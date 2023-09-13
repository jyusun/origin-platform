package com.jyusun.origin.base.openapi.config.knife4j;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j Config
 *
 * @author jyusun
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "origin-system.swagger", name = "enabled", matchIfMissing = true)
@RequiredArgsConstructor
public class SwaggerConfiguration {

    //
    private final SwaggerProperties swaggerProperties;

    //
    @Value("${spring.application.name:unknown}")
    private String appName;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().specVersion(SpecVersion.V31)
            .openapi(appName)
            .info(swaggerProperties.buildApiInfo().license(new License().name("MIT").url("mit")));
    }

    /**
     * 根据@Tag 上的排序，写入x-order
     * @return the global open api customizer
     */
    // @Bean
    // public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
    // return openApi -> {
    // if (openApi.getTags()!=null){
    // openApi.getTags().forEach(tag -> {
    // Map<String,Object> map=new HashMap<>();
    // map.put("x-order", RandomStringUtils.randomInt(0,100));
    // tag.setExtensions(map);
    // });
    // }
    // if(openApi.getPaths()!=null){
    // openApi.addExtension("x-test123","333");
    // openApi.getPaths().addExtension("x-abb",RandomUtils.randomInt(1,100));
    // }
    //
    // };
    // }

}