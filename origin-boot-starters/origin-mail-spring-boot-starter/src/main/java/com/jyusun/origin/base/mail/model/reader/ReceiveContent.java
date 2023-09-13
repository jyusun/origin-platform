package com.jyusun.origin.base.mail.model.reader;

import com.jyusun.origin.base.mail.common.util.MailUtil;
import com.jyusun.origin.core.common.util.ArrayUtil;
import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.mail.Flags;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.SneakyThrows;

import java.time.LocalDateTime;

/**
 * 作用描述：内容处理
 *
 * @author jyusun at 2023/5/3 21:18
 * @since 1.0.0
 */
public class ReceiveContent {

    @Schema(description = "消息编号")
    private final String msgUid;

    @Schema(description = "邮件消息")
    private final Message message;

    @Schema(description = "多用途消息")
    private final MimeMessage mimeMessage;

    @Schema(description = "消息序号")
    private Integer messageNumber;

    @Schema(description = "邮件主题")
    private String subject;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "发件地址")
    private InternetAddress fromAddress;

    @Schema(description = "收件人")
    private InternetAddress[] toAddress;

    @Schema(description = "抄送人")
    private InternetAddress[] ccAddress;

    @Schema(description = "密送人")
    private InternetAddress[] bccAddress;

    @Schema(description = "发送时间")
    private LocalDateTime sentDate;

    @Schema(description = "收取时间")
    private LocalDateTime receiveDate;

    @Schema(description = "邮件大小")
    private int size;

    @Schema(description = "是否HTML")
    private boolean ishtml;

    @Schema(description = "是否有附件")
    private boolean hasAttachMent;

    @Schema(description = "是否已读")
    private boolean isseen;

    public ReceiveContent(String msgUid, Message message) {
        this.msgUid = msgUid;
        this.message = message;
        this.mimeMessage = (MimeMessage) this.message;

    }

    public Integer getMessageNumber() {
        if (ObjectUtil.isEmpty(this.messageNumber) || messageNumber == 0) {
            this.messageNumber = this.message.getMessageNumber();
        }
        return this.messageNumber;
    }

    @SneakyThrows
    public String getSubject() {
        if (StringUtil.notHasText(this.subject)) {
            this.subject = MimeUtility.decodeText(this.message.getSubject());
        }
        return this.subject;
    }

    @SneakyThrows
    public String getContent() {
        if (StringUtil.notHasText(this.content)) {
            this.content = MimeUtility.decodeText(this.message.getSubject());// todo
        }
        return this.content;
    }

    @SneakyThrows
    public InternetAddress getFromAddress() {
        if (ObjectUtil.isEmpty(this.fromAddress)) {
            this.fromAddress = (InternetAddress) this.message.getFrom()[0];
        }
        return this.fromAddress;
    }

    @SneakyThrows
    public InternetAddress[] getToAddress() {
        if (ArrayUtil.isEmpty(this.toAddress)) {
            this.toAddress = MailUtil.getRecipientAddress(this.message, Message.RecipientType.TO);
        }
        return this.toAddress;
    }

    @SneakyThrows
    public InternetAddress[] getCcAddress() {
        if (ArrayUtil.isEmpty(this.ccAddress)) {
            this.ccAddress = MailUtil.getRecipientAddress(this.message, Message.RecipientType.CC);
        }
        return this.ccAddress;
    }

    @SneakyThrows
    public InternetAddress[] getBccAddress() {
        if (ArrayUtil.isEmpty(this.bccAddress)) {
            this.bccAddress = MailUtil.getRecipientAddress(this.message, Message.RecipientType.BCC);
        }
        return this.bccAddress;
    }

    @SneakyThrows
    public LocalDateTime getSendDate() {
        if (ObjectUtil.isEmpty(this.sentDate)) {
            if (ObjectUtil.isEmpty(this.message.getReceivedDate())) {
                this.sentDate = DateUtil.toLocalDateTime(this.message.getSentDate());
            }
        }
        return this.sentDate;
    }

    @SneakyThrows
    public LocalDateTime getReceiveDate() {
        if (ObjectUtil.isEmpty(this.receiveDate)) {
            if (ObjectUtil.isEmpty(this.message.getReceivedDate())) {
                this.receiveDate = DateUtil.toLocalDateTime(this.message.getReceivedDate());
            }
        }
        return this.receiveDate;
    }

    @SneakyThrows
    public Integer getSize() {
        if (ObjectUtil.isEmpty(this.isseen)) {
            this.size = this.message.getSize();
        }
        return this.size;
    }

    @SneakyThrows
    public Boolean getIsseen() {
        if (ObjectUtil.isEmpty(this.isseen)) {
            this.isseen = this.message.getFlags().contains(Flags.Flag.SEEN);
        }
        return this.isseen;
    }

    public Boolean getHasAttachMent() {
        if (ObjectUtil.isEmpty(this.hasAttachMent)) {
            this.hasAttachMent = false; // todo
        }
        return this.hasAttachMent;
    }

    @SneakyThrows
    public Boolean getIshtml() {
        if (ObjectUtil.isEmpty(this.ishtml)) {
            this.ishtml = true;
        }
        return this.ishtml;
    }

}
