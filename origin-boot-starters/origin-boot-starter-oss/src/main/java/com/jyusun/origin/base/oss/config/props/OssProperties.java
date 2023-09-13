package com.jyusun.origin.base.oss.config.props;

import com.jyusun.origin.base.oss.common.constant.OssConstant;
import com.jyusun.origin.base.oss.common.enums.TypeEnum;
import com.jyusun.origin.core.common.constant.SystemConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS属性
 *
 * @author jyusun
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "origin-system.oss")
public class OssProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = true;
    /**
     * 对象存储类型
     */
    private TypeEnum type = TypeEnum.LOCAL;
    /**
     * 端点（访问域名）
     */
    private String endpoint;
    /**
     * 默认存储空间名称
     */
    private String bucketName = SystemConstant.NAME;
    /**
     * 基础路径
     */
    private String basePath = OssConstant.DEFAULT_UPLOAD_PATH;
    /**
     * 访问ID
     */
    private String accessKeyId;
    /**
     * 访问密钥
     */
    private String accessKeySecret;
}
