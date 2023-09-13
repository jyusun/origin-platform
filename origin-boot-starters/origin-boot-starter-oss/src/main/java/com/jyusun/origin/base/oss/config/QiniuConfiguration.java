package com.jyusun.origin.base.oss.config;//package com.jyusun.origin.base.oss.config;

import com.jyusun.origin.base.oss.OssTemplate;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.QiniuOssContext;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.factory.handle.QiniuClient;
import com.jyusun.origin.base.oss.factory.rule.DefaultOssRule;
import com.jyusun.origin.base.oss.factory.OssRule;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Oss配置类
 *
 * @author jyusun
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnProperty(value = "origin-system.oss.type", havingValue = "QINIU")
public class QiniuConfiguration {

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

    @Bean
    public com.qiniu.storage.Configuration qiniuConfiguration() {
        return new com.qiniu.storage.Configuration(Zone.autoZone());
    }

    @Bean
    public Auth auth() {
        return Auth.create(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }

    @Bean
    @ConditionalOnBean(com.qiniu.storage.Configuration.class)
    public UploadManager uploadManager(com.qiniu.storage.Configuration cfg) {
        return new UploadManager(cfg);
    }

    @Bean
    @ConditionalOnBean(com.qiniu.storage.Configuration.class)
    public BucketManager bucketManager(com.qiniu.storage.Configuration cfg) {
        return new BucketManager(Auth.create(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret()),
                cfg);
    }

    /**
     * @param ossRule
     * @return
     */
    @Bean
    @ConditionalOnBean(OssRule.class)
    public OssFactory propsFactory(Auth auth, UploadManager uploadManager, BucketManager bucketManager,
                                   OssRule ossRule) {
        QiniuOssContext ossContext = QiniuOssContext.builder()
                .ossProperties(ossProperties)
                .ossRule(ossRule)
                .uploadManager(auth)
                .uploadManager(uploadManager)
                .bucketManager(bucketManager)
                .build();
        return new QiniuClient(ossContext);
    }

    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    public OssTemplate qiniuTemplate(OssFactory ossFactory) {
        return new OssTemplate(ossFactory);
    }


}
