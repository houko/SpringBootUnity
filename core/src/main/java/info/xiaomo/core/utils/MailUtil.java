package info.xiaomo.core.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/511:00
 * Description: 发送邮件
 * Copyright(©) 2015 by xiaomo.
 **/
public class MailUtil {
    private static String USERNAME;
    private static String PASSWORD;

    /**
     * 获取Session
     */
    private static Session getSession() throws IOException {
        Properties props = new Properties();
        // 从 classpath 读取配置, 不再依赖进程工作目录(user.dir)下的绝对路径
        try (InputStream is = MailUtil.class.getClassLoader().getResourceAsStream("config/application.properties")) {
            if (is == null) {
                throw new IOException("未找到 classpath 下的 config/application.properties");
            }
            props.load(is);
        }
        USERNAME = Objects.toString(props.get("mail.username"), "");
        PASSWORD = Objects.toString(props.get("mail.password"), "");
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };
        return Session.getDefaultInstance(props, authenticator);
    }

    public static void send(String toEmail, String subject, String content) {
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
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
