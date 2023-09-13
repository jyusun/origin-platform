package com.jyusun.origin.base.oss.factory;


import com.jyusun.origin.base.oss.context.OssContext;
import com.jyusun.origin.base.oss.model.UploadInfo;

import java.io.InputStream;

/**
 * 抽象工厂
 *
 * @author jyusun at 2021-10-8 16:58:54
 * @since 1.0.0
 */
public interface OssFactory {

    OssContext getOssContext();

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名称
     */
    void makeBucket(String bucketName);

    /**
     * 获取存储空间
     *
     * @param bucketName 存储空间名称
     * @return 存储空间名称
     */
    String getBucket(String bucketName);

    /**
     * 删除存储空间
     *
     * @param bucketName 存储空间名称
     * @return true-成功，false-失败
     */
    boolean removeBucket(String bucketName);

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param bucketName   存储空间名称
     * @param fullPath     文件路径
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    UploadInfo put(InputStream inputStream, String bucketName, String fullPath, String originalName, boolean cover);

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param bucketName   存储空间名称
     * @param basePath     基础路径
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    UploadInfo put(InputStream inputStream, String bucketName, String basePath, String originalName);

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param bucketName   存储空间名称
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    UploadInfo put(InputStream inputStream, String bucketName, String originalName);

    /**
     * 文件上传
     *
     * @param inputStream  输入流
     * @param originalName 原始文件名称
     * @return {@link UploadInfo }
     */
    UploadInfo put(InputStream inputStream, String originalName);
}
