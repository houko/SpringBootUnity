package info.xiaomo.website.controller

import info.xiaomo.core.base.Result
import info.xiaomo.core.constant.CodeConst
import info.xiaomo.core.constant.GenderConst
import info.xiaomo.core.exception.UserNotFoundException
import info.xiaomo.core.untils.MailUtil
import info.xiaomo.core.untils.Md5Util
import info.xiaomo.core.untils.RandomUtil
import info.xiaomo.core.untils.TimeUtil
import info.xiaomo.website.model.UserModel
import info.xiaomo.website.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.hibernate.service.spi.ServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.text.ParseException

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
 * Date: 2016/4/1 17:51
 * Description: 用户控制器
 * Copyright(©) 2015 by xiaomo.
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户相关api", description = "用户相关api")
class UserController @Autowired
constructor(private val service: UserService) {

    /**
     * 根据id 查找用户
     *
     * @param id id
     * @return result
     */
    @ApiOperation(value = "查找用户", notes = "查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findById/{id}", method = arrayOf(RequestMethod.GET))
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    fun findUserById(@PathVariable("id") id: Long?): Result<*> {
        val userModel = service.findUserById(id)
        return Result(userModel)
    }

    /**
     * 添加用户
     */
    @ApiOperation(value = "添加用户", notes = "添加用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "addUser", method = arrayOf(RequestMethod.POST))
    fun addUser(@RequestBody user: UserModel): Result<*> {
        val salt = RandomUtil.createSalt()
        user.password = Md5Util.encode(user.password!!, salt)
        user.validateCode = Md5Util.encode(user.email!!, "")!!
        user.salt = salt
        service.addUser(user)
        return Result(user)
    }

    /**
     * 注册
     *
     * @return result
     */
    @ApiOperation(value = "注册", notes = "注册用户并发送验证链接到邮箱", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "用户名", required = true, dataType = "String", paramType = "path"), ApiImplicitParam(name = "密码", required = true, dataType = "String", paramType = "path"))
    @RequestMapping(value = "register/{email}/{password}", method = arrayOf(RequestMethod.POST))
    @Throws(Exception::class)
    fun register(@PathVariable("email") email: String, @PathVariable("password") password: String): Result<*> {
        val redirectValidateUrl = MailUtil.redirectValidateUrl(email, password)
        MailUtil.send(email, "帐号激活邮件", redirectValidateUrl)
        return Result(redirectValidateUrl)
    }


    /**
     * 登录
     *
     * @return result
     */
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "path"), ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "path"))
    @RequestMapping(value = "login/{email}/{password}", method = arrayOf(RequestMethod.POST))
    fun login(@PathVariable("email") email: String, @PathVariable("password") password: String): Result<*> {
        val userModel = service.findUserByEmail(email)
        //找不到用户
        //密码不正确
        return if (Md5Util.encode(password, userModel.salt!!) != userModel.password) {
            val result = Result<Any>(CodeConst.AUTH_FAILED.resultCode, CodeConst.AUTH_FAILED.message!!)
            result
        } else Result(userModel)
    }


    /**
     * 修改密码
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "changePassword", method = arrayOf(RequestMethod.POST))
    @Throws(UserNotFoundException::class)
    fun changePassword(@RequestBody user: UserModel): Result<*> {
        val userByEmail = service.findUserByEmail(user.email!!)
        val salt = RandomUtil.createSalt()
        userByEmail.password = Md5Util.encode(user.password!!, salt)
        userByEmail.nickName = user.nickName
        userByEmail.salt = salt
        val updateUser = service.updateUser(userByEmail)
        return Result(updateUser)
    }

    /**
     * 更新用户信息
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "update", method = arrayOf(RequestMethod.POST))
    @Throws(UserNotFoundException::class)
    fun update(@RequestBody user: UserModel): Result<*> {
        val userModel: UserModel?
        userModel = UserModel()
        userModel.email = user.email
        userModel.nickName = user.nickName
        userModel.phone = user.phone
        userModel.address = user.address
        userModel.gender = user.gender
        userModel.validateCode = Md5Util.encode(user.email!!, "")!!
        val updateUser = service.updateUser(userModel)
        return Result(updateUser)
    }


    /**
     * 根据id删除用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "delete/{id}", method = arrayOf(RequestMethod.GET))
    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams(ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"))
    @Throws(UserNotFoundException::class)
    fun deleteUserById(@PathVariable("id") id: Long?): Result<*> {
        val userModel = service.deleteUserById(id)
        return Result(userModel)
    }

    /**
     * 处理激活
     */
    @ApiOperation(value = "处理激活", notes = "处理激活", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "validateEmail", method = arrayOf(RequestMethod.POST))
    @Throws(ServiceException::class, ParseException::class, UserNotFoundException::class)
    fun validateEmail(@RequestBody user: UserModel?
    ): Result<*> {
        if (user == null) {
            return Result(CodeConst.NULL_DATA)
        }
        //数据访问层，通过email获取用户信息
        var userModel: UserModel? = service.findUserByEmail(user.email!!)
        if (userModel != null) {
            return Result<Any>(resultCode = CodeConst.USER_REPEAT.resultCode, message = CodeConst.USER_REPEAT.message!!)
        }
        //验证码是否过期
        val registerTime = user.registerTime
        if (registerTime!! + TimeUtil.ONE_DAY_IN_MILLISECONDS < TimeUtil.nowOfMills) {
            return Result<Any>(CodeConst.TIME_PASSED.resultCode, CodeConst.TIME_PASSED.message!!)
        }
        //激活
        val salt = RandomUtil.createSalt()
        userModel = UserModel()
        userModel.nickName = user.nickName
        userModel.email = user.email
        userModel.gender = GenderConst.SECRET
        userModel.validateCode = Md5Util.encode(user.email!!, salt)!!
        userModel.phone = 0L
        userModel.salt = salt
        userModel.address = ""
        userModel.password = Md5Util.encode(user.password!!, salt)
        userModel = service.addUser(userModel)
        return Result(userModel)
    }

}
