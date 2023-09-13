package com.jyusun.origin.base.oss.context;

import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.OssRule;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 七牛对象存储属性工厂
 *
 * @author jyusun at 2021-10-8 15:19:28
 */
@Getter
@Accessors(chain = true)
public class QiniuOssContext extends OssContext {

    /**
     * 七牛云鉴权
     */
    private final Auth auth;
    /**
     * 七牛云上传管理
     */
    private final UploadManager uploadManager;
    /**
     * 七牛云管理
     */
    private final BucketManager bucketManager;

    public QiniuOssContext(OssProperties ossProperties,
                           OssRule ossRule,
                           Auth auth,
                           UploadManager uploadManager,
                           BucketManager bucketManager) {
        super(ossProperties,ossRule);
        this.auth = auth;
        this.uploadManager = uploadManager;
        this.bucketManager = bucketManager;
    }

    public static QiniuOssContextBuilder builder() {
        return new QiniuOssContextBuilder();
    }

    public static class QiniuOssContextBuilder {

        private OssProperties ossProperties;
        private OssRule ossRule;
        private Auth auth;
        private UploadManager uploadManager;
        private BucketManager bucketManager;

        public QiniuOssContextBuilder ossProperties(OssProperties ossProperties) {
            this.ossProperties = ossProperties;
            return this;
        }

        public QiniuOssContextBuilder ossRule(OssRule ossRule) {
            this.ossRule = ossRule;
            return this;
        }

        public QiniuOssContextBuilder uploadManager(Auth auth) {
            this.auth = auth;
            return this;
        }

        public QiniuOssContextBuilder uploadManager(UploadManager uploadManager) {
            this.uploadManager = uploadManager;
            return this;
        }

        public QiniuOssContextBuilder bucketManager(BucketManager bucketManager) {
            this.bucketManager = bucketManager;
            return this;
        }

        public QiniuOssContext build() {
            return new QiniuOssContext(ossProperties, ossRule, auth, uploadManager, bucketManager);
        }
    }
}
