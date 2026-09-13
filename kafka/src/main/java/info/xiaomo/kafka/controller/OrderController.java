package info.xiaomo.kafka.controller;

import info.xiaomo.core.base.Result;
import info.xiaomo.kafka.service.OrderEventProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xiaomo
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderEventProducer producer;

    public OrderController(OrderEventProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public Result<String> create(@RequestParam String orderId) {
        producer.publish(orderId);
        return new Result<>("已发布订单事件: " + orderId);
    }

}