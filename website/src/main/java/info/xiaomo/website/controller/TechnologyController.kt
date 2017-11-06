package info.xiaomo.website.controller

import info.xiaomo.core.base.Result
import info.xiaomo.core.constant.CodeConst
import info.xiaomo.website.model.TechnologyModel
import info.xiaomo.website.service.TechnologyService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
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
 *
 * Date: 2016/11/3 14:36
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */


@RestController
@RequestMapping("/technology")
@Api(value = "技术中心相关api", description = "技术中心相关api")
class TechnologyController @Autowired
constructor(private val service: TechnologyService) {


    @ApiOperation(value = "根据id查找技术", notes = "根据id查找技术", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/findById/{id}", method = arrayOf(RequestMethod.GET))
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一Id", required = true, dataType = "Long", paramType = "path"))
    fun findById(@PathVariable id: Long?): Result<*> {
        val model = service.findById(id)
        return Result(model)
    }

    @ApiOperation(value = "根据名字查找技术", notes = "根据名字查找技术", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/findByName/{name}", method = arrayOf(RequestMethod.GET))
    @ApiImplicitParams(ApiImplicitParam(name = "name", value = "技术名字", required = true, dataType = "name", paramType = "path"))
    fun findByName(@PathVariable name: String): Result<*> {
        val model = service.findByName(name)
        return Result(model)
    }


    @ApiOperation(value = "查找所有", notes = "查找所有", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/findAll", method = arrayOf(RequestMethod.GET))
    fun findAll(): Result<*> {
        val all = service.findAll()
        return if (all.isEmpty()) {
            Result<Any>(CodeConst.NULL_DATA.resultCode, CodeConst.NULL_DATA.message!!)
        } else Result(all)
    }


    @ApiOperation(value = "添加链接", notes = "添加链接", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun add(@RequestBody model: TechnologyModel): Result<*> {
        var addModel: TechnologyModel? = service.findByName(model.name!!)
        if (addModel != null) {
            return Result<Any>(CodeConst.REPEAT.resultCode, CodeConst.REPEAT.message!!)
        }
        addModel = service.add(model)
        return Result(addModel)
    }

    @ApiOperation(value = "更新链接", notes = "更新链接", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/update", method = arrayOf(RequestMethod.POST))
    fun update(@RequestBody model: TechnologyModel): Result<*> {
        val update: TechnologyModel?
        update = service.update(model)
        return Result(update)
    }


    @RequestMapping(value = "/delete/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "删除链接", notes = "删除链接", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun delete(@PathVariable id: Long?): Result<*> {
        val model = service.findById(id)
        service.del(id)
        return Result(model)
    }

}
