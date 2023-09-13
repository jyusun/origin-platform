package com.jyusun.origin.base.mail.common.util;

import lombok.experimental.UtilityClass;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * 收件工具
 *
 * @author jyusun at 2022-08-10 17:14:40
 */
@UtilityClass
public class MailReceiveUtil {

    /**
     * 获得邮件的优先级
     * @param msg 邮件内容
     * @return 1(High):紧急 3:普通(Normal) 5:低(Low)
     * @throws MessagingException
     */
    public static String getPriority(MimeMessage msg) throws MessagingException {
        String priority = "普通";
        String[] headers = msg.getHeader("X-Priority");
        if (headers != null) {
            String headerPriority = headers[0];
            if (headerPriority.contains("1") || headerPriority.contains("High")) {
                priority = "紧急";
            }
            else if (headerPriority.contains("5") || headerPriority.contains("Low")) {
                priority = "低";
            }
            else {
                priority = "普通";
            }
        }
        return priority;
    }

}
