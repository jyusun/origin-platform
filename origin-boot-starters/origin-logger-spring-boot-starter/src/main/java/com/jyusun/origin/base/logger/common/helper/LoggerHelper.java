package com.jyusun.origin.base.logger.common.helper;

import com.alibaba.fastjson.JSON;
import com.jyusun.origin.base.logger.model.LoggerDataFactory;
import com.jyusun.origin.base.logger.model.dto.UsualLoggerDTO;
import com.jyusun.origin.base.logger.publisher.LoggerPublisher;
import com.jyusun.origin.core.common.util.SecureUtil;
import com.jyusun.origin.core.common.util.ThrowableUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class LoggerHelper {

    /**
     * 常规日志记录
     * @param title 标题
     * @param bizId 业务编号
     * @param tableName 表名称
     * @param description 描述名称
     */
    public static void usual(String title, Long bizId, String tableName, String description) {
        // 获取调用者
        StackTraceElement stackTraceElement = ThrowableUtil.getStackElement(2);
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        Long userId = SecureUtil.getUser().getUserId();
        UsualLoggerDTO usual = LoggerDataFactory.buildUsual(className, methodName, title, bizId, tableName, description,
                userId);
        log.info(JSON.toJSONString(usual));
        LoggerPublisher.usualEvent(usual);
    }

}
