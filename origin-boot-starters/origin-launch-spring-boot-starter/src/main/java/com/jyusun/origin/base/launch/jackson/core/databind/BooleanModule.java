package com.jyusun.origin.base.launch.jackson.core.databind;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 前后端交互Boolean格式化
 *
 * @author jyusun at 2021-10-27 18:01:41
 * @since 1.0.0
 */
public class BooleanModule extends SimpleModule {

    /**
     * JDK8时间格式默认配置
     */
    public BooleanModule() {
        super(PackageVersion.VERSION);
        this.addSerializer(Boolean.class, new BooleanNumericSerializer());
    }

}