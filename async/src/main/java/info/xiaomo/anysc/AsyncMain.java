package info.xiaomo.anysc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/4/1 15:38
 * @Description: RabbitMq启动器
 * @Copyright(©) 2015 by xiaomo.
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@EnableJpaRepositories("info.xiaomo.*.dao")
public class AsyncMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AsyncMain.class, args);
    }



}
