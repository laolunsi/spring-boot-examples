package com.example.maildemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.function.Function;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/12/9 10:46
 */
@RestController
@RequestMapping(value = "email")
public class EmailAction {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sendName;
    @Autowired
    private TemplateEngine templateEngine;

    public EmailAction(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping(value = "simple")
    public String sendSimpleMsg(String msg, String email) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(email)) {
            return "请输入要发送消息和目标邮箱";
        }

        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(sendName);
            mail.setTo(email);
            mail.setSubject("这是一封简单邮件");
            mail.setText(msg);
            mailSender.send(mail);
            return "发送成功";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "发送失败:" + ex.getMessage();
        }
    }

    @PostMapping(value = "html")
    public String sendHtmlMsg(String msg, String email) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(email)) {
            return "请输入要发送消息和目标邮箱";
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sendName);
            messageHelper.setTo(email);
            messageHelper.setSubject("HTML邮件");
            String html = "<div><h1><a name=\"hello\"></a><span>Hello</span></h1><blockquote><p><span>this is a html email.</span></p></blockquote><p>&nbsp;</p><p><span>"
                    + msg + "</span></p></div>";
            messageHelper.setText(html, true);
            mailSender.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送失败：" + e.getMessage();
        }
    }

    @PostMapping(value = "mime_with_file")
    public String sendWithFile(String msg, String email) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(email)) {
            return "请输入要发送消息和目标邮箱";
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sendName);
            messageHelper.setTo(email);
            messageHelper.setSubject("一封包含附件的邮件");
            messageHelper.setText(msg);
            // 该文件位于resources目录下
            // 文件路径不能直接写文件名，系统会报错找不到路径，而IDEA却能直接映射过去
            // 文件路径可以写成相对路径src/main/resources/x.pdf，也可以用绝对路径：System.getProperty("user.dir") + "/src/main/resources/x.pdf"
            File file = new File("src/main/resources/SpringBoot日志处理之Logback.pdf");
            //File file = new File(System.getProperty("user.dir") + "/src/main/resources/SpringBoot日志处理之Logback.pdf");
            System.out.println("文件是否存在：" + file.exists());
            messageHelper.addAttachment(file.getName(), file);
            mailSender.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送失败：" + e.getMessage();
        }
    }

    @PostMapping(value = "html_with_img")
    public String sendHtmlWithImg(String msg, String email) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(email)) {
            return "请输入要发送消息和目标邮箱";
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sendName);
            messageHelper.setTo(email);
            messageHelper.setSubject("带静态资源图片的HTML邮件");
            String html = "<div><h1><a name=\"hello\"></a><span>Hello</span></h1><blockquote><p><span>this is a html email.</span></p></blockquote><p>&nbsp;</p><p><span>"
                    + msg + "</span></p><img src='cid:myImg' /></div>";
            messageHelper.setText(html, true);
            File file = new File("src/main/resources/wei.jpg");
            messageHelper.addInline("myImg", file);
            mailSender.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送失败：" + e.getMessage();
        }
    }

    @PostMapping(value = "html_with_template")
    public String sendHtmlByTemplate(String msg, String email) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(email)) {
            return "请输入要发送消息和目标邮箱";
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sendName);
            messageHelper.setTo(email);
            messageHelper.setSubject("使用HTML模板文件发送邮件");

            Context context = new Context();
            context.setVariable("msg", msg);
            messageHelper.setText(templateEngine.process("EmailTemplate", context), true);
            mailSender.send(message);
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送失败：" + e.getMessage();
        }
    }



}
