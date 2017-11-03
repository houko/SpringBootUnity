package info.xiaomo.multiplesource.controller

import info.xiaomo.core.base.Result
import info.xiaomo.multiplesource.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
 * Date: 2016/11/16 10:45
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

@RestController
class MultipleSourceController @Autowired
constructor(@param:Qualifier("primaryJdbcTemplate") private val jdbcTemplate1: JdbcTemplate, @param:Qualifier("secondaryJdbcTemplate") private val jdbcTemplate2: JdbcTemplate) {

    init {
        this.jdbcTemplate1.update(Sql.dropUser)
        this.jdbcTemplate2.update(Sql.dropUser)
    }


    @RequestMapping("/")
    fun index(): Result<*> {
        // 往第一个数据源中插入两条数据
        jdbcTemplate1.update(Sql.addUser, "xiaomo", 20)
        jdbcTemplate2.update(Sql.addUser, "xiaoming", 30)

        val count1 = jdbcTemplate1.queryForObject(Sql.selectUser, Int::class.java)
        val count2 = jdbcTemplate2.queryForObject(Sql.selectUser, Int::class.java)
        return Result(arrayOf<Any>(count1, count2))
    }
}
