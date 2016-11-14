package info.xiaomo.website.controller;

import info.xiaomo.core.controller.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/11/14 11:06
 * @Description: 用户实体类
 * @Copyright(©) 2015 by xiaomo.
 **/

@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailSender mailSender;

    @Autowired
    public MailController(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Result sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("83387856@qq.com");
        message.setTo("83387856@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
        return new Result(message);
    }

}
