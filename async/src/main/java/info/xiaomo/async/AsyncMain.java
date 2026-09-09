package info.xiaomo.async;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

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
 * Description: 异步任务启动器
 * Copyright(©) 2015 by xiaomo.
 **/
@Configuration
@EnableAsync
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan("info.xiaomo")
public class AsyncMain {
    public static void main(String[] args) {
        SpringApplication.run(AsyncMain.class, args);
    }

}
