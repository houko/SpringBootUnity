package info.xiaomo.mail.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.mail.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xiaomo
 */
@RestController
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/simple")
    public Result<String> sendSimple(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        mailService.sendSimple(to, subject, text);
        return new Result<>("已发送纯文本邮件");
    }

    @PostMapping("/html")
    public Result<String> sendHtml(@RequestParam String to, @RequestParam String subject, @RequestParam String html)
            throws MessagingException {
        mailService.sendHtml(to, subject, html);
        return new Result<>("已发送 HTML 邮件");
    }

}