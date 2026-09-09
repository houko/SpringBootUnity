package info.xiaomo.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : xiaomo
 * Description: 调用外部 HTTP 服务启动器
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.restclient")
public class RestClientMain {

    public static void main(String[] args) {
        SpringApplication.run(RestClientMain.class, args);
    }

}
