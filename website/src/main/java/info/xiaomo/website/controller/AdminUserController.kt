package info.xiaomo.website.controller

import info.xiaomo.core.base.Result
import info.xiaomo.core.constant.CodeConst
import info.xiaomo.core.exception.UserNotFoundException
import info.xiaomo.core.untils.Md5Util
import info.xiaomo.core.untils.RandomUtil
import info.xiaomo.website.model.AdminModel
import info.xiaomo.website.service.AdminUserService
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 *
 * Date: 16/4/2 12:47
 * Description: 后台用户控制器
 * Copyright(©) 2015 by xiaomo.
 */
@RestController
@RequestMapping("/adminUser")
@Api(value = "后台用户相关api", description = "后台用户相关api")
class AdminUserController @Autowired
constructor(private val service: AdminUserService) {


    /**
     * 返回所有
     *
     * @return 不分页
     */
    val all: Result<*>
        @RequestMapping(value = "findAll", method = arrayOf(RequestMethod.GET))
        @ApiOperation(value = "返回所有用户信息", notes = "不分页", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
        @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
        get() {
            val pages = service.getAdminUsers()
            return if (pages.isEmpty()) {
                Result(pages)
            } else Result(pages)
        }

    /**
     * 后台账户登录
     *
     * @return Result
     */
    @RequestMapping(value = "login/{userName}/{password}", method = arrayOf(RequestMethod.POST))
    @ApiOperation(value = "获取用户信息", notes = "根据用户帐号和密码登录后台", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "Result", paramType = "path"), ApiImplicitParam(name = "password", value = "用户名", required = true, dataType = "Result", paramType = "path"))
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    fun login(@PathVariable("userName") userName: String, @PathVariable("password") password: String): Result<*> {
        val adminModel = service.findAdminUserByUserName(userName)
        return if (Md5Util.encode(password, adminModel.salt!!) != adminModel.password) {
            Result<Any>(CodeConst.AUTH_FAILED.resultCode, CodeConst.AUTH_FAILED.message!!)
        } else Result(adminModel)
    }


    /**
     * 添加用户
     *
     * @return Result
     */
    @ApiOperation(value = "添加后台用户", notes = "传一个管理员用户模型过来然后保存到数据库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "add", method = arrayOf(RequestMethod.POST))
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    fun add(@RequestBody model: AdminModel): Result<*> {
        val salt = RandomUtil.createSalt()
        model.salt = salt
        model.password = Md5Util.encode(model.password!!, salt)
        val saveModel = service.addAdminUser(model)
        return Result(saveModel)
    }

    /**
     * 根据id查找
     *
     * @param id id
     * @return Result
     */
    @ApiOperation(value = "查找用户", notes = "根据传来的id查找用户并返回", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findById/{id}", method = arrayOf(RequestMethod.GET))
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "后台用户唯一id", required = true, dataType = "Long", paramType = "path"))
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    fun findUserById(@PathVariable("id") id: Long?): Result<*> {
        val adminModel = service.findAdminUserById(id)
        return Result(adminModel)
    }


    /**
     * 根据名字查找
     *
     * @param userName userName
     * @return Result
     */
    @ApiOperation(value = "查找用户", notes = "根据传来的用户名查找用户并返回", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findByName/{userName}", method = arrayOf(RequestMethod.GET))
    @ApiImplicitParams(ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path"))
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    fun findByName(@PathVariable("userName") userName: String): Result<*> {
        val adminModel = service.findAdminUserByUserName(userName)
        return Result(adminModel)
    }


    /**
     * 修改密码
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "changePassword", method = arrayOf(RequestMethod.POST))
    @ApiOperation(value = "修改用户密码", notes = "传来模型验证并修改密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    @Throws(UserNotFoundException::class)
    fun changePassword(@RequestBody model: AdminModel): Result<*> {
        val adminModel = service.findAdminUserByUserName(model.userName!!)
        val salt = RandomUtil.createSalt()
        adminModel.salt = salt
        adminModel.password = Md5Util.encode(model.password!!, salt)
        service.updateAdminUser(adminModel)
        return Result(adminModel)
    }

    /**
     * 根据id删除数据
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "delete/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "删除用户", notes = "根据传入的id删除对应的用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "用户唯一id", required = true, dataType = "Long", paramType = "path"))
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    @Throws(UserNotFoundException::class)
    fun deleteUserById(@PathVariable("id") id: Long?): Result<*> {
        val adminModel = service.findAdminUserById(id)
        service.deleteAdminUserById(id)
        return Result(adminModel)
    }

    /**
     * 更新
     *
     * @param userName userName
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update/{userName}", method = arrayOf(RequestMethod.POST))
    @ApiOperation(value = "更新用户信息", notes = "根据传入的模型更新用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path"))
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    @Throws(UserNotFoundException::class)
    fun update(@PathVariable("userName") userName: String): Result<*>? {
        val adminModel = service.findAdminUserByUserName(userName)
        adminModel.userName = userName
        service.updateAdminUser(adminModel)
        return Result(adminModel)
    }

    /**
     * 封号
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "forbid/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "封号", notes = "根据传入的id对修改对应帐号状态", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "后台用户唯一id", required = true, dataType = "Long", paramType = "path"))
    @ApiResponses(value = *arrayOf(ApiResponse(code = 404, message = "Not Found"), ApiResponse(code = 400, message = "No Name Provided")))
    @Throws(UserNotFoundException::class)
    fun forbid(@PathVariable("id") id: Long?): Result<*> {
        var model: AdminModel?
        model = service.forbidAdminUserById(id)
        return Result(model)
    }
}

