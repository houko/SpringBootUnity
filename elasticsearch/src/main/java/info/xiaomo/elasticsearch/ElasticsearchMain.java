package info.xiaomo.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Elasticsearch 启动器。
 *
 * <p>和 Mongo 一样走 Spring Data 家族: 定义 @Document 实体 + ElasticsearchRepository 接口,
 * 框架自动生成实现。服务地址在 application.properties 里配置(spring.elasticsearch.uris)。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableElasticsearchRepositories
@ComponentScan("info.xiaomo.elasticsearch")
public class ElasticsearchMain {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchMain.class, args);
    }

}