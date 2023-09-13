package com.jyusun.origin.base.mail;

import com.jyusun.origin.base.mail.common.enums.ReceiveModeEnum;
import com.jyusun.origin.base.mail.model.context.MailConfigContext;
import com.jyusun.origin.base.mail.model.context.props.AccountProperties;
import com.jyusun.origin.base.mail.model.context.props.ReceiveModeProperties;
import com.jyusun.origin.base.mail.model.reader.ReceiveConnect;
import com.jyusun.origin.base.mail.model.reader.ReceiveContent;
import com.jyusun.origin.base.mail.model.reader.ReceiveFolder;
import com.jyusun.origin.base.mail.model.sender.MailMessageHelper;
import com.jyusun.origin.core.common.util.CollectionUtil;
import com.jyusun.origin.core.common.util.ObjectUtil;
import com.jyusun.origin.core.common.util.StringUtil;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 邮件发送模板
 *
 * @author jyusun at 2022-04-14 09:32:05
 */
@RequiredArgsConstructor
public class OriginMailTemplate {

    /**
     * 模板引擎解析对象，用于解析模板
     */
    // private final JavaMailSender mailSender;
    // private final TemplateEngine springTemplateEngine;

    /**
     * 全部读取
     * @param configContext
     * @return
     */
    @SneakyThrows
    public List<ReceiveContent> receiveAll(MailConfigContext configContext) {

        ReceiveConnect connect = new ReceiveConnect(configContext);
        ReceiveFolder folder = new ReceiveFolder(connect.getDefaultFolder());

        List<ReceiveContent> contents = folder.findMailContents();

        connect.stop();
        return contents;
    }

    /**
     * 部分读取
     * @param configContext
     * @return
     */
    @SneakyThrows
    public List<ReceiveContent> receivePart(MailConfigContext configContext) {

        ReceiveModeProperties props = configContext.getReadModeProps();
        int startIndex = props.getStartIndex();
        int endIndex = props.getEndIndex();

        ReceiveConnect connect = new ReceiveConnect(configContext);
        ReceiveFolder folder = new ReceiveFolder(connect.getDefaultFolder());

        List<ReceiveContent> contents = folder.findMailContents(startIndex, endIndex);

        connect.stop();
        return contents;
    }

    /**
     * 根据模式读取
     * @param configContext
     * @return
     */
    @SneakyThrows
    public List<ReceiveContent> receiveMode(MailConfigContext configContext) {
        ReceiveModeProperties props = configContext.getReadModeProps();
        String readMode = props.getReceiveMode();
        List<ReceiveContent> contents;
        if (StringUtil.pathEquals(readMode, ReceiveModeEnum.RECEIVE_ALL.code())) {
            contents = this.receiveAll(configContext);
        }
        else {
            contents = this.receivePart(configContext);
        }
        return contents;
    }

    /**
     * 构建发送对象
     * @param configContext
     * @return
     */
    public JavaMailSender buildJavaMailSender(MailConfigContext configContext) {
        if (ObjectUtil.isEmpty(configContext.getMailSender())) {
            AccountProperties accountProps = configContext.getAccountProps();
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setUsername(accountProps.getAddress());
            sender.setPassword(accountProps.getPassword());
            sender.setDefaultEncoding(configContext.getDefaultEncoding().name());
            sender.setJavaMailProperties(configContext.getJavaMailProps());
            configContext.setMailSender(sender);
            return sender;
        }
        else {
            return configContext.getMailSender();
        }
    }

    /**
     * 构建消息
     * @param mailSender
     * @param mailMessageHelper
     * @return
     */
    @SneakyThrows
    public MimeMessage buildMimeMessage(JavaMailSender mailSender, MailMessageHelper mailMessageHelper) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
        boolean singleDisplay = mailMessageHelper.getSingleDisplay();
        helper.setFrom(mailMessageHelper.getFromAddress());
        if (singleDisplay) {
            helper.setBcc(CollectionUtil.toArray(mailMessageHelper.getSingleAddress()));
        }
        else {
            helper.setTo(CollectionUtil.toArray(mailMessageHelper.getToAddress()));
            helper.setCc(CollectionUtil.toArray(mailMessageHelper.getCcAddress()));
            helper.setBcc(CollectionUtil.toArray(mailMessageHelper.getBccAddress()));
        }
        helper.setSubject(mailMessageHelper.getSubject());
        helper.setText(mailMessageHelper.getContent());

        return mimeMessage;
    }

    @SneakyThrows
    public String sendMessage(JavaMailSender mailSender, MimeMessage message) {
        mailSender.send(message);
        return message.getMessageID();
    }

    public String sendMessage(MailConfigContext configContext, MimeMessage message) {
        JavaMailSender mailSender = this.buildJavaMailSender(configContext);
        return this.sendMessage(mailSender, message);
    }

    public String sendMessage(MailConfigContext configContext, MailMessageHelper mailMessageHelper) {
        JavaMailSender mailSender = this.buildJavaMailSender(configContext);
        MimeMessage message = this.buildMimeMessage(mailSender, mailMessageHelper);
        return this.sendMessage(mailSender, message);
    }

    // /**
    // * 简单文本消息
    // *
    // * @param sendMail
    // */
    // public void sendSimpleText(SendSimpleMailDTO sendMail) {
    // SimpleMailMessage simpleTextInfo =
    // MailUtil.createSimpleTextInfo(sendMail.getSubject(), sendMail.getContent(),
    // sendMail.getMailFrom().getFrom(),
    // sendMail.getToaddrs(), sendMail.getCcaddrs(), sendMail.getBccaddrs(),
    // sendMail.getSendTime());
    // mailSender.send(simpleTextInfo);
    // }
    //
    // /**
    // * 批量发送简单文本消息
    // *
    // * @return
    // */
    // private boolean sendBatchSimpleText(SimpleMailMessage[] simpleTexts) {
    // mailSender.send(simpleTexts);
    // return true;
    // }
    //
    // /**
    // * 附件处理，需要处理附件时，需要使用二进制信息，使用 MimeMessage 类来进行处理
    // *
    // * @param toaddrs
    // * @param ccaddrs
    // * @param bccaddrs
    // * @param subject
    // * @param content
    // * @param filePaths 附件地址
    // * @param sendTime
    // * @return
    // */
    // @SneakyThrows
    // private boolean sendMail(String subject,
    // String content,
    // String from, String nickname,
    // List<String> toaddrs,
    // List<String> ccaddrs,
    // List<String> bccaddrs,
    // List<String> filePaths,
    // LocalDateTime sendTime,
    // boolean isHtml) {
    //
    // //附件处理需要进行二进制传输
    // MimeMessage mimeMessage = mailSender.createMimeMessage();
    // MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
    // StandardCharsets.UTF_8.name());
    // //设置邮件的基本信息：这些函数都会在后面列出来
    // MailUtil.setBasicInfo(helper, subject, content, from, nickname,
    // toaddrs, ccaddrs, bccaddrs, sendTime,
    // isHtml);
    // //处理附件
    // MailUtil.setAttachment(helper, filePaths);
    // //发送该邮件
    // mailSender.send(mimeMessage);
    // return true;
    // }
    //
    //
    // /**
    // * 常规消息
    // *
    // * @param mailDTO
    // * @return
    // */
    // public boolean sendMail(SendMailDTO mailDTO) {
    // this.sendMail(mailDTO.getSubject(), mailDTO.getContent(),
    // mailDTO.getMailFrom().getFrom(), mailDTO.getMailFrom().getNickname(),
    // mailDTO.getToaddrs(),
    // mailDTO.getCcaddrs(),
    // mailDTO.getBccaddrs(),
    // mailDTO.getAttachmentPaths(),
    // mailDTO.getSendTime(),
    // mailDTO.getIshtml());
    // return true;
    // }
    //
    // /**
    // * 模板内容发送
    // *
    // * @param templateMail
    // * @return
    // */
    // @SneakyThrows
    // public boolean sendTemplate(SendTemplateMailDTO templateMail) {
    // Map<String, Object> params = templateMail.getParams();
    // InputStream contentTemplate = templateMail.getContentTemplate();
    // List<String> strings = IOUtils.readLines(contentTemplate, StandardCharsets.UTF_8);
    // Context context = new Context();
    // params.forEach(context::setVariable);
    // String templateText = String.join(StringUtil.CRLF, strings);
    // String content = springTemplateEngine.process(templateText, context);
    // return this.sendMail(templateMail.getSubject(), content,
    // templateMail.getMailFrom().getFrom(), templateMail.getMailFrom().getNickname(),
    // templateMail.getToaddrs(),
    // templateMail.getCcaddrs(),
    // templateMail.getBccaddrs(),
    // templateMail.getAttachmentPaths(),
    // templateMail.getSendTime(),
    // true);
    // }

}
