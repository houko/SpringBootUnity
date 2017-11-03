package test

import info.xiaomo.rabbitmq.config.Sender
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class RabbitMqTests {

    @Autowired
    private val sender: Sender? = null

    @Test
    @Throws(Exception::class)
    fun hello() {
        sender!!.send()
    }

}
