package info.xiaomo.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * GraphQL 启动器。
 *
 * <p>schema 定义在 resources/graphql/*.graphqls, 数据取值逻辑写在 @Controller 的 @QueryMapping 方法里,
 * spring-graphql 把它们拼成一个 /graphql 端点, 客户端一次请求可以按需取任意字段。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.graphql")
public class GraphqlMain {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlMain.class, args);
    }

}