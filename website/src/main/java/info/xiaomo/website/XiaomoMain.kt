package info.xiaomo.website

import info.xiaomo.website.view.UserView
import io.swagger.annotations.ApiOperation
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import springfox.documentation.annotations.ApiIgnore
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 *
 * Date: 2016/4/1 15:38
 * Description: 后台管理启动器
 * Copyright(©) 2015 by xiaomo.
 */
@EnableAutoConfiguration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@EnableTransactionManagement
@EnableJpaRepositories("info.xiaomo.*.dao")
@EnableCaching
@EnableSwagger2
@Controller
class XiaomoMain : WebMvcConfigurerAdapter() {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun index(): String? {
        return UserView.INDEX.viewName
    }

    /**
     * 接口
     *
     * @return 接口
     */
    @RequestMapping(value = "/api", method = arrayOf(RequestMethod.GET))
    @ApiIgnore
    @ApiOperation(value = "重定向到api首页")
    fun api(): ModelAndView {
        return ModelAndView("redirect:/swagger-ui.html")
    }


    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("info.xiaomo.website"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("api根地址：http://api.xiaomo.info:8080/")
                .termsOfServiceUrl("https://xiaomo.info/")
                .contact("小莫")
                .version("1.0")
                .build()
    }

    companion object {

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(XiaomoMain::class, *args)
        }
    }

}
