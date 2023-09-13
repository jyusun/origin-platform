package com.jyusun.origin.base.oss.factory;

/**
 * 规则处理
 *
 * @author jyusun at 2021-10-9 09:51:05
 */
public interface OssRule {

    /**
     * 获取存储桶规则
     *
     * @param bucketName 存储桶名称
     * @return {@code String }
     */
    String bucketName(String bucketName);

    /**
     * path
     *
     * @param basePath         基础路径
     * @param originalFilename 原始文件名
     * @return String
     */
    String path(String basePath, String originalFilename);

    /**
     * 默认路径
     *
     * @param originalFilename 原始文件名
     * @return String
     */
    String defaultPath(String originalFilename);

}
