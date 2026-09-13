package info.xiaomo.ratelimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * 接口限流启动器。
 *
 * <p>把 {@link Clock} 抽成一个 bean, 是这里最值得注意的地方: 生产环境用系统时钟,
 * 测试里再用 {@code @Primary} 注入一个可拨动的时钟, 就能不靠 sleep 稳定地验证"窗口滚动后放行"。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.ratelimit")
public class RateLimitMain {

    public static void main(String[] args) {
        SpringApplication.run(RateLimitMain.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

}