package info.xiaomo.rabbitmq.config

import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

/**
 * @author : xiaomo
 */
@Component
@RabbitListener(queues = arrayOf("hello"))
class Receiver {

    @RabbitHandler
    fun process(hello: String) {
        println("Receiver : " + hello)
    }

}
