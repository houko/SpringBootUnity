package info.xiaomo.website.controller

import info.xiaomo.core.base.Result
import info.xiaomo.core.constant.CodeConst
import info.xiaomo.website.model.LinkModel
import info.xiaomo.website.service.LinkService
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
 * Date: 2016/4/1119:55
 * Description: 友情连接控制器
 * Copyright(©) 2015 by xiaomo.
 */
@RestController
@RequestMapping("/link")
@Api(value = "友情链接相关api", description = "友情链接相关api")
class LinkController @Autowired
constructor(private val service: LinkService) {

    /**
     * 根据id查找
     *
     * @param id id
     * @return model
     */
    @RequestMapping(value = "findById/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "通过id查找", notes = "通过id查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun findLinkById(@PathVariable("id") id: Long?): Result<*> {
        val model = service.findById(id)
        return Result(model)
    }

    /**
     * 根据名字查找
     *
     * @param name name
     * @return model
     */
    @RequestMapping(value = "findByName/{name}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "根据名字查找", notes = "根据名字查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "name", value = "友情链接名字", required = true, dataType = "String", paramType = "path"))
    fun findByName(@PathVariable("name") name: String): Result<*> {
        val model = service.findByName(name)
        return Result(model)
    }


    /**
     * 返回所有数据
     *
     * @return 所有
     */
    @RequestMapping(value = "findAll", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "返回所有数据", notes = "返回所有数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    fun findAll(): Result<*> {
        val pages = service.findAll()
        return if (pages.isEmpty()) {
            Result<Any>(CodeConst.NULL_DATA.resultCode, CodeConst.NULL_DATA.message!!)
        } else Result(pages)
    }

    /**
     * 添加链接
     *
     * @return model
     */
    @RequestMapping(value = "add", method = arrayOf(RequestMethod.POST))
    @ApiOperation(value = "添加链接", notes = "添加链接", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    fun add(@RequestBody model: LinkModel): Result<*> {
        var linkModel: LinkModel? = service.findByName(model.name!!)
        if (linkModel != null) {
            return Result<Any>(CodeConst.REPEAT.resultCode, CodeConst.REPEAT.message!!)
        }
        linkModel = LinkModel()
        linkModel.name = model.name
        linkModel.url = model.url
        val addModel = service.add(linkModel)
        return Result(addModel)
    }

    /**
     * 更新链接
     *
     * @return model
     */
    @ApiOperation(value = "更新链接", notes = "更新链接", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "update", method = arrayOf(RequestMethod.POST))
    fun update(@RequestBody model: LinkModel): Result<*> {
        val linkModel = service.findById(model.id)
        linkModel.name = model.name
        linkModel.url = model.url
        val updateModel = service.update(linkModel)
        return Result(updateModel)
    }

    /**
     * 删除链接
     *
     * @param id id
     * @return model
     */
    @RequestMapping(value = "delete/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "删除链接", notes = "删除链接", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun delete(@PathVariable("id") id: Long?): Result<*> {
        val delModel = service.delete(id)
        return Result(delModel)
    }
}
