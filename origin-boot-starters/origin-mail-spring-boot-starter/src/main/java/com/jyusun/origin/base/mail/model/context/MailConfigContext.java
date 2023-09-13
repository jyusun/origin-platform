package com.jyusun.origin.base.mail.model.context;

import com.jyusun.origin.base.mail.common.enums.ProtocolEnum;
import com.jyusun.origin.base.mail.model.context.props.AccountProperties;
import com.jyusun.origin.base.mail.model.context.props.ReceiveModeProperties;
import com.jyusun.origin.base.mail.model.context.props.ServerProperties;
import com.jyusun.origin.base.mail.model.context.protocol.AbstractProtocol;
import com.jyusun.origin.base.mail.model.context.protocol.ProtocolFactory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

/**
 * 作用描述：配置上下文
 *
 * @author jyusun at 2023/5/2 15:57
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public class MailConfigContext {

    @Schema(description = "协议属性")
    private final String protocol;

    @Schema(description = "账户属性")
    private final AccountProperties accountProps;

    @Schema(description = "服务属性")
    private final ServerProperties serverProps;

    @Schema(description = "读取模式")
    private final ReceiveModeProperties readModeProps;

    @Schema(description = "邮件属性")
    private final Properties javaMailProps;

    @Schema(description = "默认字符集")
    private final Charset defaultEncoding;

    @Schema(description = "SSL验证")
    private final Boolean sslAuth;

    @Schema(description = "测试链接")
    private final Boolean testConnection;

    @Setter
    @Schema(description = "发件链接")
    private JavaMailSender mailSender;

    public static ConfigContextBuilder builder() {
        return new ConfigContextBuilder();
    }

    /**
     *
     */
    public static class ConfigContextBuilder {

        @Schema(description = "协议属性")
        private String protocol;

        @Schema(description = "账户属性")
        private AccountProperties accountProps;

        @Schema(description = "服务属性")
        private ServerProperties serverProps;

        @Schema(description = "读取模式")
        private ReceiveModeProperties readModeProps;

        @Schema(description = "邮件属性")
        private Properties javaMailProps;

        @Schema(description = "邮箱连接")
        private JavaMailSender mailSender;

        @Schema(description = "扩展邮件属性")
        private Map<String, String> configExtProps;

        @Schema(description = "默认字符集")
        private Charset defaultEncoding;

        @Schema(description = "SSL验证")
        private Boolean sslAuth;

        @Schema(description = "测试链接")
        private Boolean testConnection;

        public ConfigContextBuilder() {
            this.init();
        }

        public ConfigContextBuilder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public ConfigContextBuilder accountProps(AccountProperties accountProps) {
            this.accountProps = accountProps;
            return this;
        }

        public ConfigContextBuilder serverProps(ServerProperties serverProps) {
            this.serverProps = serverProps;
            return this;
        }

        public ConfigContextBuilder readModeProps(ReceiveModeProperties readModeProps) {
            this.readModeProps = readModeProps;
            return this;
        }

        public ConfigContextBuilder extProps(Map<String, String> configExtProps) {
            this.configExtProps = configExtProps;
            return this;
        }

        public ConfigContextBuilder defaultEncoding(Charset defaultEncoding) {
            this.defaultEncoding = defaultEncoding;
            return this;
        }

        public ConfigContextBuilder sslAuth(Boolean sslAuth) {
            this.sslAuth = sslAuth;
            return this;
        }

        public ConfigContextBuilder testConnection(Boolean testConnection) {
            this.testConnection = testConnection;
            return this;
        }

        private void init() {
            this.defaultEncoding = StandardCharsets.UTF_8;
            this.testConnection = false;
            this.sslAuth = false;
        }

        private void buildJavaMailProperties(AccountProperties accountProps, ServerProperties serverProps,
                Map<String, String> extProps) {
            ProtocolFactory factory = new ProtocolFactory();
            AbstractProtocol handler = factory.getHandler(ProtocolEnum.getEnum(this.protocol));
            this.javaMailProps = handler.initProps(accountProps, serverProps, extProps);
        }

        public MailConfigContext build() {
            this.buildJavaMailProperties(this.accountProps, this.serverProps, configExtProps);
            return new MailConfigContext(this.protocol, this.accountProps, this.serverProps, this.readModeProps,
                    this.javaMailProps, this.defaultEncoding, this.sslAuth, this.testConnection, this.mailSender);
        }

    }

}
