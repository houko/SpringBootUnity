package info.xiaomo.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AOP 切面编程启动器。
 *
 * <p>classpath 上存在 aspectj 时, spring boot 会通过 AopAutoConfiguration 自动开启
 * {@code @EnableAspectJAutoProxy}, 因此把切面声明成 {@code @Aspect} 的 {@code @Component} 即可生效,
 * 无需再手动加开关。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.aop")
public class AopMain {

    public static void main(String[] args) {
        SpringApplication.run(AopMain.class, args);
    }

}