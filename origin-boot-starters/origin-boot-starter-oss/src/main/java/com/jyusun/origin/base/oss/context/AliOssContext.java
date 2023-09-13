package com.jyusun.origin.base.oss.context;

import com.aliyun.oss.OSS;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssRule;
import lombok.Getter;

/**
 * 阿里OSS
 *
 * @author jyusun at 2022-04-01 15:25:22
 */
@Getter
public class AliOssContext extends OssContext {

    private final OSS oss;

    public AliOssContext(OssProperties ossProperties, OssRule ossRule, OSS oss) {
        super(ossProperties, ossRule);
        this.oss = oss;
    }

    public static AliOssContextBuilder builder() {
        return new AliOssContextBuilder();
    }

    public static class AliOssContextBuilder {

        private OSS oss;
        private OssProperties ossProperties;
        private OssRule ossRule;

        public AliOssContextBuilder ossProperties(OssProperties ossProperties) {
            this.ossProperties = ossProperties;
            return this;
        }

        public AliOssContextBuilder ossRule(OssRule ossRule) {
            this.ossRule = ossRule;
            return this;
        }

        public AliOssContextBuilder oss(OSS oss) {
            this.oss = oss;
            return this;
        }

        public AliOssContext build() {
            return new AliOssContext(ossProperties, ossRule, oss);
        }
    }
}