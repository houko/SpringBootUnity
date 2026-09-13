package info.xiaomo.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @EmbeddedKafka 会在测试里起一个真实的 Kafka broker(内嵌、随机端口)，不依赖外部服务。
 * 测试里的监听器用独立的 groupId, 与 main 里的消费者互不抢消息(不同消费组各自收到一份)。
 */
@SpringBootTest(classes = KafkaMain.class)
@EmbeddedKafka(partitions = 1, topics = "order-events")
class OrderKafkaTest {

    static final BlockingQueue<String> received = new LinkedBlockingQueue<>();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void 生产者发送的消息能被监听器收到() throws Exception {
        kafkaTemplate.send("order-events", "order-1001").get(5, TimeUnit.SECONDS);

        String orderId = received.poll(10, TimeUnit.SECONDS);
        assertThat(orderId).isEqualTo("order-1001");
    }

    @TestConfiguration
    static class TestConsumerConfig {

        @KafkaListener(topics = "order-events", groupId = "order-kafka-test")
        void listen(String orderId) {
            received.add(orderId);
        }
    }

}