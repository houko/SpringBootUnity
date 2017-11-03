package info.xiaomo.website.controller

import info.xiaomo.core.base.Result
import info.xiaomo.core.constant.CodeConst
import info.xiaomo.website.model.WorksModel
import info.xiaomo.website.service.WorksService
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


@RequestMapping("/works")
@RestController
@Api(value = "作品相关api", description = "作品相关api")
class WorksController @Autowired
constructor(private val service: WorksService) {


    @RequestMapping(value = "/findById/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "根据id查找作品", notes = "根据id查找作品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun findById(@PathVariable id: Long?): Result<*> {
        val model = service.findById(id)
        return Result(model)
    }

    @RequestMapping(value = "/findAll", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "查找所有", notes = "查找所有", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    fun findAll(): Result<*> {
        val all = service.findAll()
        return if (all.isEmpty()) {
            Result<Any>(resultCode = CodeConst.NULL_DATA.resultCode, message = CodeConst.NULL_DATA.message!!)
        } else Result(all)
    }

    @RequestMapping(value = "/findByName/{name}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "根据名字查找作品", notes = "根据名字查找作品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "name", value = "作品名字", required = true, dataType = "String", paramType = "path"))
    fun findByName(@PathVariable name: String): Result<*> {
        val model = service.findByName(name)
        return Result(model)
    }

    @ApiOperation(value = "添加作品", notes = "添加作品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/add", method = arrayOf(RequestMethod.POST))
    fun add(@RequestBody model: WorksModel): Result<*> {
        var addModel: WorksModel? = service.findByName(model.name!!)
        if (addModel != null) {
            return Result<Any>(resultCode = CodeConst.REPEAT.resultCode, message = CodeConst.REPEAT.message!!)
        }
        addModel = service.add(model)
        return Result(addModel)
    }

    @ApiOperation(value = "更新作品", notes = "更新作品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/update", method = arrayOf(RequestMethod.POST))
    fun update(@RequestBody model: WorksModel): Result<*> {
        var worksModel: WorksModel? = service.findById(model.id)
        worksModel = service.update(worksModel!!)
        return Result<WorksModel>(worksModel!!)
    }


    @ApiOperation(value = "根据id删除作品", notes = "根据id删除作品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/delete/{id}", method = arrayOf(RequestMethod.GET))
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun delete(@PathVariable id: Long?): Result<*> {
        val model = service.findById(id)
        service.del(id)
        return Result(model)
    }

}
