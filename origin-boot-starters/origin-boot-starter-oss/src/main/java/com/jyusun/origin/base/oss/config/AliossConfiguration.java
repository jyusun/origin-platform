package com.jyusun.origin.base.oss.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.AliOssContext;
import com.jyusun.origin.base.oss.factory.handle.AliossClient;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.factory.rule.DefaultOssRule;
import com.jyusun.origin.base.oss.factory.OssRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Alioss配置类
 * <p>
 * 作用描述：阿里云配置
 *
 * @author jyusun at 2021-10-18 15:23:28
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({OssProperties.class})
@ConditionalOnProperty(value = "origin-system.oss.type", havingValue = "ALI")
public class AliossConfiguration {

    private final OssProperties ossProperties;

    /**
     * 存储规则注册
     *
     * @return {@link OssRule}
     */
    @Bean
    @ConditionalOnMissingBean(OssRule.class)
    public OssRule ossRule() {
        return new DefaultOssRule();
    }

    /**
     * Oss 客户端信息
     *
     * @return {@link OSS}
     */
    @Bean
    @ConditionalOnMissingBean(OSS.class)
    public OSS oss() {
        // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        conf.setMaxConnections(1024);
        // 设置Socket层传输数据的超时时间，默认为50000毫秒。
        conf.setSocketTimeout(50000);
        // 设置建立连接的超时时间，默认为50000毫秒。
        conf.setConnectionTimeout(50000);
        // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        conf.setConnectionRequestTimeout(1000);
        // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        conf.setIdleConnectionTime(60000);
        // 设置失败请求重试次数，默认为3次。
        conf.setMaxErrorRetry(5);
        // 设置是否支持CNAME。CNAME用于将自定义域名绑定到目标Bucket。
//         conf.setSupportCname(true);
        // 创建OSSClient实例
        return new OSSClientBuilder().build(ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(), conf);
    }

    /**
     * @param oss {@link OSSClient} 阿里云对象存储客户端
     * @param ossRule   {@link OssRule }
     * @return
     */
    @Bean
    @ConditionalOnBean({OSS.class, OssRule.class})
    public OssFactory propsFactory(OSS oss, OssRule ossRule) {
        AliOssContext ossContext = AliOssContext.builder()
                .ossProperties(ossProperties)
                .ossRule(ossRule)
                .oss(oss)
                .build();
        return new AliossClient(ossContext);
    }

    /**
     * 阿里云处理
     *
     * @return {@link OssTemplate} Oss操作模板
     */
    @Bean
    public OssTemplate ossTemplate(OssFactory ossFactory) {
        log.info("================ Ali OSS Template ================");
        return new OssTemplate(ossFactory);
    }

}
