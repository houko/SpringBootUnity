package info.xiaomo.mongodb.controller

import info.xiaomo.core.base.Result
import info.xiaomo.core.constant.CodeConst
import info.xiaomo.mongodb.model.MongoUser
import info.xiaomo.mongodb.service.MongoUserService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
 * Date: 2016/11/15 15:49
 * Copyright(©) 2015 by xiaomo.
 */

@RestController
@RequestMapping("mongodb")
@Api("mongodb測試")
class MongoUserController @Autowired
constructor(private val service: MongoUserService) {

    @RequestMapping(value = "get/{id}", method = arrayOf(RequestMethod.GET))
    operator fun get(@PathVariable("id") id: Long?): Result<*> {
        val mongoUser = service.findById(id)
        return Result(mongoUser)
    }

    @RequestMapping(value = "findAll", method = arrayOf(RequestMethod.GET))
    fun findAll(): Result<*> {
        return Result(service.findAll())
    }


    @RequestMapping(value = "add", method = arrayOf(RequestMethod.POST))
    fun add(@RequestBody user: MongoUser): Result<*> {
        return Result(service.add(user))
    }

    @RequestMapping(value = "delete/{id}", method = arrayOf(RequestMethod.GET))
    fun delete(@PathVariable("id") id: Long?): Result<*> {
        service.delete(id)
        return Result<Any>(CodeConst.SUCCESS.resultCode, CodeConst.SUCCESS.message!!)
    }

}
