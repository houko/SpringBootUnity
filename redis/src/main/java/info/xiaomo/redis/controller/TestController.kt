package info.xiaomo.redis.controller

import info.xiaomo.core.base.Result
import info.xiaomo.redis.dao.CommonRedisDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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
 * Date: 2016/11/14 17:25
 * Copyright(©) 2015 by xiaomo.
 */

@RestController
@RequestMapping("/redis")
class TestController @Autowired
constructor(private val dao: CommonRedisDao) {

    @RequestMapping(value = "get/{key}", method = arrayOf(RequestMethod.GET))
    fun find(@PathVariable("key") key: String): Result<String> {
        val value = dao.getValue(key)
        return Result(value)
    }

    @RequestMapping(value = "add/{key}/{value}", method = arrayOf(RequestMethod.GET))
    fun add(@PathVariable("value") value: String, @PathVariable("key") key: String): Result<Boolean> {
        return Result(dao.cacheValue(key, value))
    }

    @RequestMapping(value = "del/{key}", method = arrayOf(RequestMethod.GET))
    fun del(@PathVariable("key") key: String): Result<Boolean> {
        return Result(dao.removeValue(key))
    }

    @RequestMapping(value = "count/{key}", method = arrayOf(RequestMethod.GET))
    fun count(@PathVariable("key") key: String): Result<Long> {
        return Result(dao.getListSize(key))
    }


}
