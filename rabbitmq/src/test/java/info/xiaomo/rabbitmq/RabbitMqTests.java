package info.xiaomo.rabbitmq;

import info.xiaomo.rabbitmq.config.Sender;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 该测试需要一台可连接的 RabbitMQ, 连接信息见 config/application.properties。
 * 本地起好 broker 后去掉 @Disabled 即可运行。
 *
 * @author : xiaomo
 */
@SpringBootTest(classes = RabbitMqMain.class)
@Disabled("需要一个运行中的 RabbitMQ 实例")
class RabbitMqTests {

    @Autowired
    private Sender sender;

    @Test
    void hello() {
        sender.send();
    }

}
