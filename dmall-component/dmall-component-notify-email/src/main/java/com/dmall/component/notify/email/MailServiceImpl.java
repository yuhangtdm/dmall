package com.dmall.component.notify.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    private String from;

    public MailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine, String from) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.from = from;
    }

    /**
     * 简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendTextMail(String to, String subject, String content) {
        // 创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件发送人
        message.setFrom(from);
        // 邮件接收人
        message.setTo(to);
        // 邮件主题
        message.setSubject(subject);
        // 邮件内容
        message.setText(content);
        // 发送邮件
        mailSender.send(message);
    }

    /**
     * 简单html邮件
     *
     * @param to          收件人
     * @param subject     主题
     * @param htmlContent 内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String htmlContent) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            // 邮件发送人
            messageHelper.setFrom(from);
            // 邮件接收人
            messageHelper.setTo(subject);
            // 邮件主题
            message.setSubject(subject);
            // 邮件内容，html格式
            messageHelper.setText(htmlContent, true);
            //发送
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("send mail failed,", e);
            throw new SendEmailException();
        }
    }

    /**
     * 动态html邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param template 模板内容
     * @param valueMap 填充内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String template, Map<String, Object> valueMap) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            // 邮件发送人
            helper.setFrom(from);
            // 邮件接收人
            helper.setTo(to);
            // 设置邮件标题
            helper.setSubject(subject);
            // 添加正文
            Context context = new Context();
            context.setVariables(valueMap);
            String content = this.templateEngine.process(template, context);
            helper.setText(content, true);
            // 发送邮件
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("send mail failed,", e);
            throw new SendEmailException();
        }
    }

    /**
     * 带附件的邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @param files   附件
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, List<File> files) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            for (File file : files) {
                helper.addAttachment(file.getName(), file);
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("send mail failed,", e);
            throw new SendEmailException();
        }

    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String template, Map<String, Object> valueMap, List<File> files) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            // 邮件发送人
            helper.setFrom(from);
            // 邮件接收人
            helper.setTo(to);
            // 设置邮件标题
            helper.setSubject(subject);
            // 添加正文
            Context context = new Context();
            context.setVariables(valueMap);
            String content = this.templateEngine.process(template, context);
            helper.setText(content, true);
            for (File file : files) {
                helper.addAttachment(file.getName(), file);
            }
            // 发送邮件
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("send mail failed,", e);
            throw new SendEmailException();
        }
    }
}
