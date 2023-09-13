package com.jyusun.origin.base.mail.model.sender;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jyusun.origin.core.common.model.BaseDTO;
import com.jyusun.origin.core.common.util.StringUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 数据传输对象
 * <p>
 * 作用描述：邮件发送
 *
 * @author jyusun
 * @date 2021/3/29 11:29
 * @since 1.0.0
 */
@Getter
@ToString
@Validated
public class SendTemplateMailDTO implements BaseDTO {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "邮件主题不允许为空")
    @Schema(description = "邮件主题")
    private final String subject;

    @Schema(description = "模板文件")
    private final InputStream contentTemplate;

    @Schema(description = "模板参数")
    private final Map<String, Object> params;

    @Schema(description = "邮件来源")
    private final MailFrom mailFrom;

    @Size(min = 1, message = "最少输入一个收件人")
    @Schema(description = "收件地址")
    private final List<String> toaddrs;

    @Schema(description = "抄送地址")
    private final List<String> ccaddrs;

    @Schema(description = "密送地址")
    private final List<String> bccaddrs;

    @Schema(description = "邮件附件")
    private final List<String> attachmentPaths;

    @Schema(description = "发送时间")
    private final LocalDateTime sendTime;

    private SendTemplateMailDTO(String subject, InputStream contentTemplate, Map<String, Object> params,
            MailFrom mailFrom, List<String> toaddrs, List<String> ccaddrs, List<String> bccaddrs,
            List<String> attachmentPaths, LocalDateTime sendTime) {
        this.subject = subject;
        this.contentTemplate = contentTemplate;
        this.params = params;
        this.mailFrom = mailFrom;
        this.toaddrs = toaddrs;
        this.ccaddrs = ccaddrs;
        this.bccaddrs = bccaddrs;
        this.attachmentPaths = attachmentPaths;
        this.sendTime = sendTime;
    }

    public static SendMailBuilder builder() {
        return new SendMailBuilder();
    }

    public static class SendMailBuilder {

        @Schema(description = "邮件主题")
        private String subject;

        @Schema(description = "内容模板")
        private InputStream contentTemplate;

        @Schema(description = "模板参数")
        private final Map<String, Object> params = Maps.newHashMap();

        @Schema(description = "发件人")
        private String from;

        @Schema(description = "发件人昵称")
        private String nickname = StringUtil.EMPTY;

        @Schema(description = "收件人地址")
        private final List<String> toaddrs = Lists.newArrayList();

        @Schema(description = "抄送人地址")
        private final List<String> ccaddrs = Lists.newArrayList();

        @Schema(description = "密送地址")
        private final List<String> bccaddrs = Lists.newArrayList();

        @Schema(description = "邮件附件")
        private final List<String> attachments = Lists.newArrayList();

        @Schema(description = "发送时间")
        private LocalDateTime sendTime = LocalDateTime.now();

        public SendMailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public SendMailBuilder contentTemplate(InputStream contentTemplate) {
            this.contentTemplate = contentTemplate;
            return this;
        }

        public SendMailBuilder putParam(String key, Object value) {
            this.params.put(key, value);
            return this;
        }

        public SendMailBuilder params(Map<String, Object> params) {
            this.params.putAll(params);
            return this;
        }

        public SendMailBuilder from(String from) {
            this.from = from;
            return this;
        }

        public SendMailBuilder fromNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public SendMailBuilder addToaddr(String toaddr) {
            this.toaddrs.add(toaddr);
            return this;
        }

        public SendMailBuilder toaddrs(List<String> toaddrs) {
            this.toaddrs.addAll(toaddrs);
            return this;
        }

        public SendMailBuilder addCcaddr(String ccaddr) {
            this.ccaddrs.add(ccaddr);
            return this;
        }

        public SendMailBuilder ccaddrs(List<String> ccaddrs) {
            this.ccaddrs.addAll(ccaddrs);
            return this;
        }

        public SendMailBuilder addBbcaddr(String bccaddr) {
            this.ccaddrs.add(bccaddr);
            return this;
        }

        public SendMailBuilder bccaddrs(List<String> bccaddrs) {
            this.bccaddrs.addAll(bccaddrs);
            return this;
        }

        public SendMailBuilder attachments(List<String> attachments) {
            this.attachments.addAll(attachments);
            return this;
        }

        public SendMailBuilder sendTime(LocalDateTime sendTime) {
            this.sendTime = sendTime;
            return this;
        }

        public SendTemplateMailDTO build() {
            return new SendTemplateMailDTO(subject, contentTemplate, params, new MailFrom(from, nickname), toaddrs,
                    ccaddrs, bccaddrs, attachments, sendTime);
        }

    }

}
