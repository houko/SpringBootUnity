package info.xiaomo.thymeleaf

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.context.annotation.ComponentScan

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
 * Date: 2016/12/1 15:38
 * Copyright(©) 2015 by xiaomo.
 */
@EnableAutoConfiguration(exclude = arrayOf(DataSourceAutoConfiguration::class, HibernateJpaAutoConfiguration::class))
@ComponentScan("info.xiaomo")
@EntityScan("info.xiaomo.*.model")
class ThymeleafMain

@Throws(Exception::class)
fun main(args: Array<String>) {
    SpringApplication.run(ThymeleafMain::class, *args)


}
