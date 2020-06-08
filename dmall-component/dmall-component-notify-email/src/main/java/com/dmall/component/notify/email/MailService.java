package com.dmall.component.notify.email;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @description: 发送邮件服务
 * @author: created by hang.yu on 2019/03/12 23:42
 */
public interface MailService {

    /**
     * 发送文本邮件
     *
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendTextMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param to 收件人
     * @param subject 主题
     * @param htmlContent 内容
     */
    void sendHtmlMail(String to, String subject, String htmlContent);

    /**
     * 发送HTML邮件(带模板)
     *
     * @param to 收件人
     * @param subject 主题
     * @param template 模板
     * @param valueMap 填充内容
     */
    void sendHtmlMail(String to, String subject, String template, Map<String, Object> valueMap);

    /**
     * 发送带附件的邮件(简单html)
     *
     * @param to 收件人
     * @param subject 主题
     * @param htmlContent 内容
     * @param files 附件
     */
    void sendAttachmentsMail(String to, String subject, String htmlContent, List<File> files);

    /**
     * 发送带附件的邮件(复杂html)
     *
     * @param to 收件人
     * @param subject 主题
     * @param template 模板
     * @param valueMap 填充内容
     * @param files 附件
     */
    void sendAttachmentsMail(String to, String subject, String template, Map<String, Object> valueMap,
        List<File> files);
}