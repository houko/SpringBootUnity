package info.xiaomo.website.controller

import info.xiaomo.core.base.Result
import info.xiaomo.core.constant.CodeConst
import info.xiaomo.website.model.ChangeLogModel
import info.xiaomo.website.service.ChangeLogService
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
 * Date: 2016/4/1117:40
 * Description: 更新日志控制器
 * Copyright(©) 2015 by xiaomo.
 */
@RestController
@RequestMapping("/changeLog")
@Api(value = "更新日志相关api", description = "更新日志相关api")
class ChangeLogController @Autowired
constructor(private val service: ChangeLogService) {

    /**
     * 通过id查找
     *
     * @param id id
     * @return model
     */
    @RequestMapping(value = "findById/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "通过id查找", notes = "通过id查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun findById(@PathVariable("id") id: Long?): Result<*> {
        val changeLogModel = service.findById(id)
        return Result(changeLogModel)
    }

    /**
     * findByName
     *
     * @return result
     */
    @RequestMapping(value = "findByName/{name}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "通过名字查找", notes = "通过名字查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "name", value = "更新日志内容", required = true, dataType = "String", paramType = "path"))
    fun findByName(@PathVariable("name") name: String): Result<*> {
        val model = service.findByName(name)
        return Result(model)
    }


    /**
     * 分页查询更新日志
     *
     * @return 分页
     */
    @RequestMapping(value = "findAll", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "分页查询更新日志", notes = "分页查询更新日志", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    fun findAll(): Result<*> {
        val pages = service.findAll()
        return if (pages.isEmpty()) {
            Result<Any>(CodeConst.NULL_DATA.resultCode, CodeConst.NULL_DATA.message!!)
        } else Result(pages)
    }

    /**
     * 增加更新日志
     *
     * @return result
     */
    @ApiOperation(value = "增加更新日志", notes = "增加更新日志", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "add", method = arrayOf(RequestMethod.POST))
    fun add(@RequestBody model: ChangeLogModel): Result<*> {
        var changeLogModel: ChangeLogModel? = service.findByName(model.name!!)
        if (changeLogModel != null) {
            return Result<Any>(CodeConst.REPEAT.resultCode, CodeConst.REPEAT.message!!)
        }
        changeLogModel = ChangeLogModel()
        changeLogModel.name = model.name
        changeLogModel.onlineTime = model.onlineTime
        val addModel = service.add(changeLogModel)
        return Result(addModel)
    }


    /**
     * 修改更新日志
     *
     * @return result
     */
    @ApiOperation(value = "修改更新日志", notes = "修改更新日志", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "update", method = arrayOf(RequestMethod.POST))
    fun update(@RequestBody model: ChangeLogModel): Result<*> {
        val changeLogModel = service.findByName(model.name!!)
        changeLogModel.name = model.name
        changeLogModel.onlineTime = model.onlineTime
        val updateModel = service.update(changeLogModel)
        return Result(updateModel)
    }


    /**
     * 删除更新日志
     */
    @RequestMapping(value = "delete/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "删除更新日志", notes = "删除更新日志", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun deleteById(@PathVariable("id") id: Long?): Result<*> {
        service.findById(id)
        val delModel = service.delete(id)
        return Result(delModel)
    }


}
