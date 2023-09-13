package com.jyusun.origin.base.oss.factory.handle;

import com.aliyun.oss.model.Bucket;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.QiniuOssContext;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.factory.OssRule;
import com.jyusun.origin.base.oss.model.UploadInfo;
import lombok.SneakyThrows;

import java.io.InputStream;

/**
 * 七牛客户端
 *
 * @author jyusun at 2022-04-01 16:21:12
 */
public class QiniuClient implements OssFactory {

    private final QiniuOssContext ossContext;

    public QiniuClient(QiniuOssContext ossContext) {
        this.ossContext = ossContext;
    }

    @Override
    public QiniuOssContext getOssContext() {
        return ossContext;
    }

    /**
     * 获取oss存取规则
     *
     * @return {@link OssRule} 存取规则
     */
    public OssRule getRule() {
        return this.getOssContext().getOssRule();
    }


    /**
     * 获取oss配置属性
     *
     * @return {@link OssProperties}
     */
    public OssProperties ossProperties() {
        return this.getOssContext().getOssProperties();
    }

    /**
     * 获取存储空间
     *
     * @param bucketName 存储空间名称
     * @return
     */
    @Override
    public String getBucket(String bucketName) {
        return this.getRule().bucketName(bucketName);
    }

    /**
     * 删除存储空间
     *
     * @param bucketName 存储空间名称
     * @return true-成功，false-失败
     */
    @Override
    public boolean removeBucket(String bucketName) {
        return true;
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     * @return {@link Bucket}
     */
    @SneakyThrows
    private Bucket createBucket(String bucketName) {
        return null;
    }

    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return true;
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     */
    @Override
    @SneakyThrows
    public void makeBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            this.createBucket(bucketName);
        }
    }


    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param bucketName   存储空间名称
     * @param fullPath     文件路径
     * @param originalName 原始文件名称
     * @param cover
     * @return {@link UploadInfo }
     */
    @Override
    public UploadInfo put(InputStream inputStream, String bucketName, String fullPath, String originalName,
                          boolean cover) {
        return null;
    }

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param bucketName   存储桶
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    @Override
    public UploadInfo put(InputStream inputStream, String bucketName, String basePath, String originalName) {
        return this.put(inputStream, bucketName, this.getRule().path(basePath, originalName),
                originalName, true);
    }

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param bucketName   存储空间名称
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    @Override
    public UploadInfo put(InputStream inputStream, String bucketName, String originalName) {
        return this.put(inputStream, bucketName, this.getRule().defaultPath(originalName),
                originalName, true);
    }

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param originalName 原始文件名称
     * @return {@link UploadInfo}
     */
    @Override
    public UploadInfo put(InputStream inputStream, String originalName) {
        return this.put(inputStream, this.getBucket(ossProperties().getBucketName()),
                this.getRule().defaultPath(originalName), originalName, true);
    }
}
