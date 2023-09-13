package com.jyusun.origin.base.launch.scan.config;

import com.jyusun.origin.core.common.constant.SystemConstant;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 作用描述：扫描范围
 *
 * @author jyusun at 2023/5/23 17:46
 * @since 1.0.0
 */
@AutoConfiguration
@ComponentScan(basePackages = SystemConstant.BASE_PACKAGE)
public class ScanCommAutoConfiguration {

}
