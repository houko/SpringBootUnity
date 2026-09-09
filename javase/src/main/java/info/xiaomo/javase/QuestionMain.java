package info.xiaomo.javase;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
 * Description: Java SE 题库启动器
 * Copyright(©) 2015 by xiaomo.
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@EnableJpaRepositories("info.xiaomo.*.dao")
@RestController
public class QuestionMain implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(QuestionMain.class, args);
    }

    /**
     * 接口
     *
     * @return 接口
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Hidden
    @Operation(summary = "重定向到api首页")
    public ModelAndView api() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }


    @Bean
    public GroupedOpenApi javaseApi() {
        return GroupedOpenApi.builder()
                .group("javase")
                .packagesToScan("info.xiaomo.javase")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot中使用OpenAPI构建RESTful APIs")
                        .description("java se题库api")
                        .termsOfService("https://question.xiaomo.info/")
                        .contact(new Contact().name("小莫"))
                        .version("1.0"));
    }
}
