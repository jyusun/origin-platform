package com.jyusun.origin.base.mail.common.util;

import com.jyusun.origin.core.common.util.CollectionUtil;
import com.jyusun.origin.core.common.util.DateUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class MailUtil {

    /**
     * 获取收件地址
     * @param message 邮件消息
     * @param type 收件人类型
     * @return
     */
    @SneakyThrows
    public static InternetAddress[] getRecipientAddress(Message message, Message.RecipientType type) {
        return (InternetAddress[]) message.getRecipients(type);
    }

    @SneakyThrows
    public static SimpleMailMessage createSimpleTextInfo(String subject, String content, String from,
            List<String> toaddrs, List<String> ccaddrs, List<String> bccaddrs, LocalDateTime sendTime) {
        SimpleMailMessage simpleMsg = new SimpleMailMessage();
        simpleMsg.setFrom(from);
        simpleMsg.setTo(CollectionUtil.toArray(toaddrs));
        simpleMsg.setCc(CollectionUtil.toArray(ccaddrs));
        simpleMsg.setBcc(CollectionUtil.toArray(bccaddrs));
        simpleMsg.setSubject(subject);
        simpleMsg.setText(content);
        simpleMsg.setSentDate(DateUtil.toDate(sendTime));
        return simpleMsg;
    }

    @SneakyThrows
    public void setBasicInfo(MimeMessageHelper mimeMessageHelper, String subject, String content, String from,
            String nickname, List<String> toaddrs, List<String> ccaddrs, List<String> bccaddrs, LocalDateTime sendTime,
            boolean isHtml) {
        // 设置必要的邮件元素
        // 设置发件人
        mimeMessageHelper.setFrom(new InternetAddress(from, nickname));
        // 设置邮件的主题
        mimeMessageHelper.setSubject(subject);
        // 设置邮件的内容，区别是否是HTML邮件
        mimeMessageHelper.setText(content, isHtml);
        // 设置邮件的收件人
        mimeMessageHelper.setTo(CollectionUtil.toArray(toaddrs));
        // 设置发送时间
        mimeMessageHelper.setSentDate(DateUtil.toDate(sendTime));
        // 设置非必要的邮件元素，在使用helper进行封装时，这些数据都不能够为空
        if (CollectionUtil.isNotEmpty(ccaddrs)) {
            // 设置邮件的抄送人：MimeMessageHelper # Assert.notNull(cc, "Cc address array must not
            // be null");
            mimeMessageHelper.setCc(CollectionUtil.toArray(ccaddrs));
        }
        if (CollectionUtil.isNotEmpty(bccaddrs)) {
            // 设置邮件的密送人：MimeMessageHelper # Assert.notNull(bcc, "Bcc address array must
            // not be null");
            mimeMessageHelper.setBcc(CollectionUtil.toArray(bccaddrs));
        }
    }

    @SneakyThrows
    public static void setAttachment(MimeMessageHelper mimeMessageHelper, List<String> filePaths) {
        // 判断是否需要处理邮件的附件
        if (CollectionUtil.isNotEmpty(filePaths)) {
            return;
        }
        FileSystemResource resource;
        // 循环处理邮件的附件
        for (String attachmentFilePath : filePaths) {
            // 获取该路径所对应的文件资源对象
            resource = new FileSystemResource(new File(attachmentFilePath));
            // 判断该资源是否存在，当不存在时仅仅会打印一条警告日志，不会中断处理程序。
            // 也就是说在附件出现异常的情况下，邮件是可以正常发送的，所以请确定你发送的邮件附件在本机存在
            if (!resource.exists()) {
                // 开启下一个资源的处理
                continue;
            }
            // 获取资源的名称
            String fileName = resource.getFilename();
            // 添加附件
            mimeMessageHelper.addAttachment(Optional.ofNullable(fileName).orElse(StringUtil.EMPTY), resource);
        }
    }

}
