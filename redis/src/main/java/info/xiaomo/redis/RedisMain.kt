package info.xiaomo.redis

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling

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
 * Date: 2016/4/1 15:38
 * Description: Redis启动器
 * Copyright(©) 2015 by xiaomo.
 */
@EnableAutoConfiguration
@ComponentScan("info.xiaomo")
@EnableCaching
@EnableScheduling
object RedisMain {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(RedisMain::class, *args)
    }

}
