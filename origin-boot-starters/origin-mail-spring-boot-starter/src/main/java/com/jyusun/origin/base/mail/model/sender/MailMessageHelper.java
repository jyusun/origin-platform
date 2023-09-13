package com.jyusun.origin.base.mail.model.sender;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.mail.internet.InternetAddress;
import lombok.Getter;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 作用描述：
 *
 * @author jyusun at 2023/5/11 11:06
 * @since 1.0.0
 */
@Getter
public class MailMessageHelper {

    @Schema(description = "邮件主题")
    private String subject;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "附件集合")
    private List<InputStream> attachments;

    @Schema(description = "发件人")
    private InternetAddress fromAddress;

    @Schema(description = "收件人")
    private List<InternetAddress> toAddress;

    @Schema(description = "抄送人")
    private List<InternetAddress> ccAddress;

    @Schema(description = "密送人")
    private List<InternetAddress> bccAddress;

    @Schema(description = "群发单显收件人")
    private List<InternetAddress> singleAddress;

    @Schema(description = "紧急程度", example = "1-紧急|3-普通|5-低")
    private Integer priority;

    @Schema(description = "群发单显")
    private Boolean singleDisplay;

    @Schema(description = "发送时间")
    private LocalDateTime sentDate;

    @Schema(description = "是否HTML")
    private boolean ishtml;

    @Schema(description = "是否有附件")
    private boolean hasAttachMent;

}