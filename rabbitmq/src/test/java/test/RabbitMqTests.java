package test;

import info.xiaomo.rabbitmq.config.Sender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMqTests {

    @Autowired()
    private Sender sender;

    @Test
    public void hello() throws Exception {
        sender.send();
    }

}
