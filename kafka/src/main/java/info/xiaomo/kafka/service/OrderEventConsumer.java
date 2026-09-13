package info.xiaomo.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者。@KafkaListener 的方法参数会按配置的 value 反序列化器直接转成对应类型。
 *
 * @author : xiaomo
 */
@Component
public class OrderEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);

    @KafkaListener(topics = "order-events", groupId = "order-service")
    public void onOrder(String orderId) {
        log.info("收到订单事件: {}", orderId);
    }

}