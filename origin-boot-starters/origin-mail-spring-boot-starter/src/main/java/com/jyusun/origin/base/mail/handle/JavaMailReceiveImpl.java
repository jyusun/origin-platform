package com.jyusun.origin.base.mail.handle;

import org.springframework.lang.Nullable;

import jakarta.activation.FileTypeMap;
import jakarta.mail.Session;
import java.util.Properties;

/**
 * 邮件接收
 *
 * @author jyusun at 2022-08-22 17:44:59
 */
public class JavaMailReceiveImpl implements JavaMailReceive {

    /** The default protocol: 'smtp'. */
    public static final String DEFAULT_PROTOCOL = "smtp";

    /** The default port: -1. */
    public static final int DEFAULT_PORT = -1;

    private static final String HEADER_MESSAGE_ID = "Message-ID";

    private Properties javaMailProperties = new Properties();

    @Nullable
    private Session session;

    @Nullable
    private String protocol;

    @Nullable
    private String host;

    private int port = DEFAULT_PORT;

    @Nullable
    private String username;

    @Nullable
    private String password;

    @Nullable
    private String defaultEncoding;

    @Nullable
    private FileTypeMap defaultFileTypeMap;

}
