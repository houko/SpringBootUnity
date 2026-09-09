package info.xiaomo.mongodb;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
 * Description: RabbitMq启动器
 * Copyright(©) 2015 by xiaomo.
 **/
@Configuration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MongodbMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MongodbMain.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Hidden
    @Operation(summary = "重定向到api首页")
    public ModelAndView index() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @Bean
    public GroupedOpenApi mongodbApi() {
        return GroupedOpenApi.builder()
                .group("mongodb")
                .packagesToScan("info.xiaomo.mongodb")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot中使用OpenAPI构建RESTful APIs")
                        .description("使用mongodb构建api接口")
                        .termsOfService("https://xiaomo.info/")
                        .contact(new Contact().name("小莫"))
                        .version("1.0"));
    }
}
