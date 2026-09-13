package info.xiaomo.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Kafka 启动器。
 *
 * <p>KafkaTemplate 负责发消息, @KafkaListener 负责收消息, 二者之间靠 topic 解耦。
 * 测试用 @EmbeddedKafka 起一个内嵌 broker, 所以单测不依赖任何外部服务。
 *
 * @author : xiaomo
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo.kafka")
public class KafkaMain {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMain.class, args);
    }

}