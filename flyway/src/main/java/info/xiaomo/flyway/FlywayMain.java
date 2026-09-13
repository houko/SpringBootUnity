package info.xiaomo.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.data.jpa.autoconfigure.DataJpaRepositoriesAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Flyway 启动器。
 *
 * <p>注意这里和其他模块不一样: 没有排除 DataSourceAutoConfiguration —— Flyway 需要一个
 * DataSource 来执行 db/migration 下的脚本。因为示例用 JdbcTemplate 而非 JPA 读数据,
 * 所以只需排除 JPA 相关的自动配置: 有了 DataSource 之后, 若不排除会触发 core 传递引入的
 * Spring Data JPA 去建 entityManagerFactory。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataJpaRepositoriesAutoConfiguration.class})
@ComponentScan("info.xiaomo.flyway")
public class FlywayMain {

    public static void main(String[] args) {
        SpringApplication.run(FlywayMain.class, args);
    }

}