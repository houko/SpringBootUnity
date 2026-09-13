package info.xiaomo.httpinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 声明式 HTTP 客户端启动器。
 *
 * <p>{@code @HttpExchange} 是 Spring 6 引入的声明式 HTTP 接口, 用来替代 OpenFeign 这类第三方
 * 注解客户端: 只需要写一个接口描述"调什么", Spring 生成代理去执行真正的 HTTP 调用,
 * 业务代码里不再拼 URL、不再手写 RestTemplate / RestClient 的样板。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.httpinterface")
public class HttpInterfaceMain {

    public static void main(String[] args) {
        SpringApplication.run(HttpInterfaceMain.class, args);
    }

}