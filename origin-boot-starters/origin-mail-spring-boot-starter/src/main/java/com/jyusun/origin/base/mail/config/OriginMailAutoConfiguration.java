package com.jyusun.origin.base.mail.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

/**
 * 邮件配置
 *
 * @author jyusun at 2022-04-14 17:39:02
 */
@Configuration
@RequiredArgsConstructor
public class OriginMailAutoConfiguration {

    /**
     * 模板引擎解析对象，用于解析模板
     */
    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    // @Bean
    // public OriginMailTemplate mailTemplate() {
    // return new OriginMailTemplate(mailSender, templateEngine);
    // }

}
