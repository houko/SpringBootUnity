package info.xiaomo.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * 生产者。send 默认是异步的, 返回的 CompletableFuture 上可以挂回调, 示例里保持最简。
 *
 * @author : xiaomo
 */
@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String orderId) {
        kafkaTemplate.send("order-events", orderId);
    }

}