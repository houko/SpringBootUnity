package info.xiaomo.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 声明式创建 topic。生产环境通常由运维或 KafkaAdmin 统一创建, 这里只为示例方便。
 *
 * @author : xiaomo
 */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderEventsTopic() {
        return new NewTopic("order-events", 1, (short) 1);
    }

}