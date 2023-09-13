package com.jyusun.origin.base.oss.context;

import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssRule;
import lombok.Getter;

/**
 * 开发环境
 *
 * @author jyusun at 2022-04-01 15:23:43
 */
@Getter
public class LocalContext extends OssContext {

    public LocalContext(OssProperties ossProperties, OssRule ossRule) {
        super(ossProperties, ossRule);
    }

    public static LocalContextBuilder builder() {
        return new LocalContextBuilder();
    }

    public static class LocalContextBuilder {

        private OssProperties ossProperties;
        private OssRule ossRule;

        public LocalContextBuilder ossProperties(OssProperties ossProperties) {
            this.ossProperties = ossProperties;
            return this;
        }

        public LocalContextBuilder ossRule(OssRule ossRule) {
            this.ossRule = ossRule;
            return this;
        }

        public LocalContext build() {
            return new LocalContext(ossProperties, ossRule);
        }
    }
}