package com.jyusun.origin.base.mail.common.constant;

import lombok.experimental.UtilityClass;

/**
 * 作用描述：JavaMail 属性
 *
 * @author jyusun at 2023/5/2 19:50
 * @since 1.0.0
 */
@UtilityClass
public class MailPropConstant {

    /**
     * 用户名
     */
    public static final String MAIL_USER = "mail.user";

    /**
     * 发送协议
     */
    public static final String PROTOCOL_TRANSPORT = "mail.transport.protocol";

    /**
     * 读取协议
     */
    public static final String PROTOCOL_STORE = "mail.store.protocol";

    /**
     * POP3 主机
     */
    public static final String POP3_HOST = "mail.pop3.host";

    /**
     * POP3 端口
     */
    public static final String POP3_PORT = "mail.pop3.port";

    /**
     * 收件协议：POP3
     */
    public static final String POP3_SSL_AUTH = "mail.pop3.auth";

    public static final String POP3_SSL_AUTH_VALUE = "true";

    public static final String POP3_SOCKET_FACTORY_CLASS = "mail.pop3.socketFactory.class";

    public static final String POP3_SOCKET_FACTORY_CLASS_VALUE = "java.net.ssl.SSLSocketFactory";

    public static final String POP3_SOCKET_FACTORY_PORT = "mail.pop3.socketFactory.port";

    public static final String POP3_SOCKET_FACTORY_FALLBACK = "mail.pop3.socketFactory.fallback";

    public static final String POP3_SOCKET_FACTORY_FALLBACK_VALUE = "false";

    /**
     * Imap 主机
     */
    public static final String IMAP_HOST = "mail.imap.host";

    /**
     * Imap 端口
     */
    public static final String IMAP_PORT = "mail.imap.port";

    /**
     * 收件协议：Imap
     */
    public static final String IMAP_SSL_AUTH = "mail.imap.auth";

    public static final String IMAP_SSL_AUTH_VALUE = "true";

    public static final String IMAP_SOCKET_FACTORY_CLASS = "mail.imap.socketFactory.class";

    public static final String IMAP_SOCKET_FACTORY_CLASS_VALUE = "java.net.ssl.SSLSocketFactory";

    public static final String IMAP_SOCKET_FACTORY_PORT = "mail.imap.socketFactory.port";

    public static final String IMAP_SOCKET_FACTORY_FALLBACK = "mail.imap.socketFactory.fallback";

    public static final String IMAP_SOCKET_FACTORY_FALLBACK_VALUE = "false";

    /**
     * smtp 主机
     */
    public static final String SMTP_HOST = "mail.smtp.host";

    /**
     * smtp 端口
     */
    public static final String SMTP_PORT = "mail.smtp.port";

    /**
     * 发件协议：Smtp
     */
    public static final String SMTP_SSL_AUTH = "mail.smtp.auth";

    public static final String SMTP_SSL_AUTH_VALUE = "true";

    public static final String SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";

    public static final String SMTP_SOCKET_FACTORY_CLASS_VALUE = "java.net.ssl.SSLSocketFactory";

    public static final String SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";

    public static final String SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";

    public static final String SMTP_SOCKET_FACTORY_FALLBACK_VALUE = "false";

}
