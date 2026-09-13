package info.xiaomo.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件发送启动器。
 *
 * <p>spring-boot-starter-mail 用 JavaMailSender 统一收发邮件, SMTP 地址在 application.properties 配置。
 * 测试用 GreenMail 在内嵌 SMTP 服务器上接收邮件, 因此不依赖真实邮箱。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.mail")
public class MailMain {

    public static void main(String[] args) {
        SpringApplication.run(MailMain.class, args);
    }

}