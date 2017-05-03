package info.xiaomo.aries;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * author 小莫 (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/11 16:34
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@RestController
@EnableSwagger2
public class AriesMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AriesMain.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiIgnore()
    @ApiOperation(value = "重定向到api首页")
    public ModelAndView index() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("info.xiaomo.aries"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("校园APP项目")
                .description("api接口文档")
                .termsOfServiceUrl("http://xiaomo.info/")
                .contact("小莫")
                .version("1.0")
                .build();
    }
}
