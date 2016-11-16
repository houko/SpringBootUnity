package info.xiaomo.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * @Description: 后台管理启动器
 * @Copyright(©) 2015 by xiaomo.
 **/
@Configuration
@EnableAutoConfiguration
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
@EnableTransactionManagement
@EnableJpaRepositories("info.xiaomo.*.dao")
@EnableCaching
@Controller
public class XiaomoMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(XiaomoMain.class, args);
    }

    /**
     * 后台
     *
     * @return 后台
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin/index";
    }

    /**
     * 前台
     *
     * @return 前台
     */
    @RequestMapping(value = "/web", method = RequestMethod.GET)
    public String web() {
        return "web/index";
    }

}
