package info.xiaomo.rabbitmq.config

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author : xiaomo
 */
@Component
class Sender @Autowired
constructor(private val rabbitTemplate: AmqpTemplate) {

    fun send() {
        val context = "hello " + Date()
        println("Sender : " + context)
        this.rabbitTemplate.convertAndSend("hello", context)
    }

}