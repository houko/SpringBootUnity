package info.xiaomo.core.untils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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

    /**
     * 返回激活链接
     *
     * @param email email
     * @return 有3个参数 email password  time
     */
    public static String redirectValidateUrl(String email, String password) {
        Long now = TimeUtil.getNowOfMills();
        StringBuilder sb = new StringBuilder("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8080/user/validate?email=");
        sb.append(email);
        sb.append("&password=");
        sb.append(password);
        sb.append("&time=");
        sb.append(now);
        sb.append("\">");
        sb.append("http://localhost:8080/user/validate?email=");
        sb.append(email);
        sb.append("&password=");
        sb.append(password);
        sb.append("&time=");
        sb.append(now);
        sb.append("</a><br/>");
        sb.append("<span style='float:right;padding-right:4%'>小莫</span></br>");
        sb.append("<span style='float:right'>");
        sb.append(TimeUtil.getFormatDate());
        sb.append("</span></br>");
        return sb.toString();
    }
}
