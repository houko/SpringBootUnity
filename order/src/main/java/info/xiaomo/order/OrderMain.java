package info.xiaomo.order;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author xiaomo
 */
@Configuration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@Controller
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class OrderMain implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class, args);
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
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder()
                .group("order")
                .packagesToScan("info.xiaomo.order")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot中使用OpenAPI构建RESTful APIs")
                        .contact(new Contact().name("小莫"))
                        .version("1.0"));
    }
}
