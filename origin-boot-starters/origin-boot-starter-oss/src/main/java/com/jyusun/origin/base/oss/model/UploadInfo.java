package com.jyusun.origin.base.oss.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件上传信息
 *
 * @author sun at 2021-8-23 22:02:13
 */
@Data
@Accessors(chain = true)
public class UploadInfo {
    /**
     * 文件地址
     */
    private String link;
    /**
     * 文件名
     */
    private String name;
    /**
     * 原始文件名
     */
    private String originalName;
}
