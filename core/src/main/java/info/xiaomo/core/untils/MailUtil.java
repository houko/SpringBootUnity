package info.xiaomo.core.untils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

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
 * @Date: 2016/4/511:00
 * @Description: 发送邮件
 * @Copyright(©) 2015 by xiaomo.
 **/
public class MailUtil {
    private static final String HOST = "smtp.xiaomo.info";
    private static final String PROTOCOL = "smtp";
    private static final int PORT = 25;
    private static final String FROM = "admin@xiaomo.info";//发件人的email
    private static final String PWD = "Xiaomo123";//发件人密码

    /**
     * 获取Session
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol", PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.user", FROM);
        props.put("mail.password", PWD);
        props.put("mail.smtp.auth", true);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        return Session.getDefaultInstance(props, authenticator);
    }

    public static void send(String toEmail, String content) {
        Session session = getSession();
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    /**
     * 返回激活链接
     *
     * @param email email
     * @return
     * 有4个参数 email password validateCode  time
     */
    public static String redirectValidateUrl(String email,String password) {
        Long now = DateUtil.getNowOfMills();
        StringBuilder sb = new StringBuilder("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://xiaomo.info/valiate.html?email=");
        sb.append(email);
        sb.append("&password=");
        sb.append(password);
        sb.append("&validateCode=");
        sb.append(MD5Util.encode(email, String.valueOf(now)));//邮箱加上当前时间戳，以保证每个验证码都是不一样的
        sb.append("&time=");
        sb.append(now);
        sb.append("\">");
        sb.append("http://xiaomo.info/valiate.html?email=");
        sb.append(email);
        sb.append("&password=");
        sb.append(password);
        sb.append("&validateCode=");
        sb.append(MD5Util.encode(email, String.valueOf(now)));//邮箱加上当前时间戳，以保证每个验证码都是不一样的
        sb.append("&time=");
        sb.append(now);
        sb.append("</a><br/>");
        sb.append("<span style='float:right;padding-right:4%'>小莫</span></br>");
        sb.append("<span style='float:right'>");
        sb.append(DateUtil.getFormatDate());
        sb.append("</span></br>");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(redirectValidateUrl("83387856@qq.com","123456"));
    }
}
