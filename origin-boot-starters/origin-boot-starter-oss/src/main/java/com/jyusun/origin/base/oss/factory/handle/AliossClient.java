package com.jyusun.origin.base.oss.factory.handle;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.VoidResult;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.context.AliOssContext;
import com.jyusun.origin.base.oss.factory.OssFactory;
import com.jyusun.origin.base.oss.factory.OssRule;
import com.jyusun.origin.base.oss.model.UploadInfo;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * 对象存储处理
 * <p>
 * 作用描述：阿里云
 *
 * @author jyusun at 2021-7-31 10:48:21
 * @since 1.0.0
 */
public class AliossClient implements OssFactory {

    private final AliOssContext ossContext;

    public AliossClient(AliOssContext ossContext) {
        this.ossContext = ossContext;
    }

    @Override
    public AliOssContext getOssContext() {
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
     * 获取阿里云客户端
     *
     * @return {@link OSSClient}
     */
    private OSS ossClient() {
        return this.getOssContext().getOss();
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
        VoidResult voidResult = this.ossClient().deleteBucket(this.getBucket(bucketName));
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
        return this.ossClient().createBucket(this.getBucket(bucketName));
    }

    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        return this.ossClient().doesBucketExist(this.getBucket(bucketName));
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
     * @param bucketName   存储空间名称
     * @param inputStream  {@link InputStream} 输入流
     * @param fullPath     {@code String} 文件路径
     * @param originalName {@code String} 文件名称
     * @param cover        {@code Boolean} true-覆盖，false-不覆盖
     * @return {@link UploadInfo} 上传信息
     */
    @Override
    @SneakyThrows
    public UploadInfo put(InputStream inputStream, String bucketName, String fullPath, String originalName,
                          boolean cover) {
        this.makeBucket(bucketName);
        String thisBucketName = this.getBucket(bucketName);

        if (cover) {
            this.ossClient().putObject(thisBucketName, fullPath, inputStream);
        } else {
            PutObjectResult response = this.ossClient().putObject(thisBucketName, fullPath, inputStream);
            int retry = 0;
            int retryCount = 5;
            while (StringUtils.hasText(response.getETag()) && retry < retryCount) {
                response = this.ossClient().putObject(thisBucketName, fullPath, inputStream);
                retry++;
            }
        }
        UploadInfo file = new UploadInfo();
        file.setOriginalName(originalName);
        file.setName(fullPath);
//        file.setLink(fileLink(bucketName, fullPath));
        return file;
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


//    @Override
//    @SneakyThrows
//    public void removeFile(String fileName) {
//        this.removeFile(this.getOssProperties().getBucketName(), fileName);
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeFile(String bucketName, String fileName) {
//        getOssClient().deleteObject(getRule().bucketName(bucketName), fileName);
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeFiles(List<String> fileNames) {
//        this.removeFiles(this.getOssProperties().getBucketName(), fileNames);
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeFiles(String bucketName, List<String> fileNames) {
//        fileNames.forEach(fileName -> removeFile(bucketName, fileName));
//    }

//    /**
//     * 下载响应
//     *
//     * @param bucketName       存储空间名称
//     * @param responseFileName 响应文件名称
//     * @param remoteFilePath   远程文件路径
//     */
//    @Override
//    public void downloadWeb(String bucketName, String responseFileName, String remoteFilePath) {
//
//    }
//
//    /**
//     * 下载响应
//     *
//     * @param responseFileName 响应文件名称
//     * @param remoteFilePath   远程文件路径
//     */
//    @Override
//    public void downloadWeb(String responseFileName, String remoteFilePath) {
//
//    }


//    /**
//     * 获取上传凭证，普通上传
//     */
//    public String getUploadToken() {
//        return getUploadToken(this.getOssProperties().getBucketName());
//    }
//    /**
//     * <p>
//     * 获取上传凭证，普通上传
//     */
//    public String getUploadToken(String bucketName) {
//        // 默认过期时间2小时
//        return getUploadToken(bucketName, ossProperties.getArgs().get("expireTime", 3600L));
//    }


}
