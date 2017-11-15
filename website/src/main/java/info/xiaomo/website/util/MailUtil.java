package info.xiaomo.website.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import info.xiaomo.core.untils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/511:00
 * Description: 发送邮件
 * Copyright(©) 2015 by xiaomo.
 **/
public class MailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

    private static String USERNAME;
    private static String PASSWORD;

    /**
     * 获取Session
     */
    private static Session getSession() throws IOException {
        Properties props = new Properties();
        String dir = System.getProperty("user.dir");
        FileInputStream is = new FileInputStream(dir + "/website/src/main/resources/config/application.properties");
        props.load(is);
        USERNAME = String.valueOf(props.get("mail.username"));
        PASSWORD = String.valueOf(props.get("mail.password"));
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };
        return Session.getDefaultInstance(props, authenticator);
    }

    public static boolean send(String toEmail, String subject, String content) {
        Session session;
        try {
            session = getSession();
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(USERNAME));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");
            Transport.send(msg);
            LOGGER.debug("给用户:{}发送注册帐号邮件", toEmail);
        } catch (Exception e) {
            LOGGER.debug("给用户:{}发送注册帐号邮件失败", toEmail);
            return false;
        }
        return true;
    }

    /**
     * 返回激活链接
     *
     * @param email email
     * @return 有3个参数 email password
     */
    public static String getContent(String email, String password, Configuration configuration) {
        Long now = TimeUtil.getNowOfMills();
        Map<String, Object> data = new HashMap<>(10);
        StringBuilder sb = new StringBuilder("http://localhost:8080/user/validate?email=");
        sb.append(email);
        sb.append("&password=");
        sb.append(password);
        sb.append("&time=");
        sb.append(now);
        data.put("email", email);
        data.put("url", sb.toString());
        data.put("now", TimeUtil.getFormatDate(now, TimeUtil.DEFAULT_FORMAT));
        Template template;
        String readyParsedTemplate = null;
        try {
            template = configuration.getTemplate("email.ftl");
            readyParsedTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readyParsedTemplate;
    }
}
