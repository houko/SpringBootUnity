package info.xiaomo.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1 15:38
 * Description: WebSocket 在线聊天启动器
 * Copyright(©) 2015 by xiaomo.
 **/
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
public class ChatMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChatMain.class, args);
    }


    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
