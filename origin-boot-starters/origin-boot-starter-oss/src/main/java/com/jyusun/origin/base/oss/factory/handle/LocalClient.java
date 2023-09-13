package com.jyusun.origin.base.oss.factory.handle;


import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.LocalContext;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.factory.OssRule;
import com.jyusun.origin.base.oss.model.UploadInfo;
import com.jyusun.origin.core.common.util.FileUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 对象存储处理
 * <p>
 * 作用描述：上传到本地文件夹
 *
 * @author jyusun at 2021-7-31 10:48:21
 * @since 1.0.0
 */
public class LocalClient implements OssFactory {

    private final LocalContext ossContext;

    public LocalClient(LocalContext ossContext) {
        this.ossContext = ossContext;
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


    @Override
    public LocalContext getOssContext() {
        return ossContext;
    }


    private File tempBucket(String bucketName) {
        return new File(FileUtil.getUserDirectoryPath()
                + StringUtil.SLASH
                + ossProperties().getBasePath()
                + StringUtil.SLASH + bucketName);
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     * @return {@link String}
     */
    @SneakyThrows
    private String createBucket(String bucketName) {
        File fileDir = this.tempBucket(bucketName);
        FileUtils.forceMkdir(fileDir);
        return fileDir.getPath();
    }

    /**
     * 校验是否存在
     *
     * @param bucketName 存储空间名称
     * @return
     */
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return this.tempBucket(bucketName).exists();
    }


    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     */
    @SneakyThrows
    @Override
    public void makeBucket(String bucketName) {
        if (!this.bucketExists(bucketName)) {
            this.createBucket(bucketName);
        }
    }


    /**
     * 获取存储空间
     *
     * @param bucketName 存储空间名称
     * @return 存储空间名称
     */
    @Override
    public String getBucket(String bucketName) {
        return this.tempBucket(bucketName).getPath();
    }

    /**
     * 删除存储空间
     *
     * @param bucketName 存储空间名称
     * @return true-成功，false-失败
     */
    @Override
    public boolean removeBucket(String bucketName) {
        return false;
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
    @SneakyThrows
    @Override
    public UploadInfo put(InputStream inputStream, String bucketName, String fullPath, String originalName,
                          boolean cover) {
        this.makeBucket(bucketName);
        File thisFile = new File(fullPath);
        try {
            try (FileOutputStream fos = new FileOutputStream(thisFile)) {
                IOUtils.copy(inputStream, fos);
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return new UploadInfo()
                .setOriginalName(originalName);
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
