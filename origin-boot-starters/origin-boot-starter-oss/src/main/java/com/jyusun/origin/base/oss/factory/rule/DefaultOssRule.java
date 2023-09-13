package com.jyusun.origin.base.oss.factory.rule;

import com.jyusun.origin.base.oss.common.constant.OssConstant;
import com.jyusun.origin.base.oss.factory.OssRule;
import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.FileUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import com.jyusun.origin.core.common.util.UuidUtil;

/**
 * 默认Oss规则
 *
 * @author jyusun at 2022-04-01 14:17:15
 */
public class DefaultOssRule implements OssRule {


    @Override
    public String bucketName(String bucketName) {
        return bucketName;
    }

    /**
     * path
     *
     * @param basePath         基础路径
     * @param originalFilename 原始文件名
     * @return String
     */
    @Override
    public String path(String basePath, String originalFilename) {
        return FileUtil.pathEndSlash(basePath) + originalFilename;
    }

    /**
     * 默认路径
     *
     * @param originalFilename 原始文件名
     * @return String
     */
    @Override
    public String defaultPath(String originalFilename) {
        return FileUtil.pathEndSlash(OssConstant.DEFAULT_UPLOAD_PATH) + DateUtil.thisDateToStrYmd() + StringUtil.SLASH +
                UuidUtil.generateUuidStr32() + FileUtil.getExtension(originalFilename);
    }


}
