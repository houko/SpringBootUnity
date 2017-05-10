package info.xiaomo.reader.controller;

import info.xiaomo.reader.model.UserModel;
import info.xiaomo.reader.service.UserService;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.Code;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.MailUtil;
import info.xiaomo.core.untils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * author: xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 2016/4/1 17:51
 * Description: 用户控制器
 * Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "用户相关api")
public class UserController extends BaseController<UserModel> {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 根据id 查找用户
     *
     * @param id id
     * @return Result<>
     */
    @ApiOperation(value = "查找用户", notes = "查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"),
    })
    public Result<UserModel> findById(@PathVariable("id") Long id) {
        UserModel userModel = service.findById(id);
        if (userModel == null) {
            return new Result<>(Code.USER_NOT_FOUND.getResultCode(), Code.USER_NOT_FOUND.getMessage());
        }
        return new Result<>(userModel);
    }

    /**
     * 根据名字查找模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<UserModel> findByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据名字删除模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据id删除模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加用户
     */
    @ApiOperation(value = "添加用户", notes = "添加用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Result<Boolean> add(@RequestBody UserModel user) {
        UserModel userModel = service.findByName(user.getEmail());
        if (userModel != null) {
            return new Result<>(Code.USER_REPEAT.getResultCode(), Code.USER_REPEAT.getMessage());
        }
        String salt = RandomUtil.createSalt();
        user.setPassword(MD5Util.encode(user.getPassword(), salt));
        user.setSalt(salt);
        Boolean add = service.add(user);
        return new Result<>(add);
    }

    /**
     * 注册
     *
     * @return Result<>
     */
    @ApiOperation(value = "注册", notes = "注册用户并发送验证链接到邮箱", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "密码", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "register/{email}/{password}", method = RequestMethod.POST)
    public Result<String> register(@PathVariable("email") String email, @PathVariable("password") String password) throws Exception {
        UserModel userModel = service.findByName(email);
        //邮箱被占用
        if (userModel != null) {
            return new Result<>(Code.USER_REPEAT.getResultCode(), Code.USER_REPEAT.getMessage());
        }
        String redirectValidateUrl = MailUtil.redirectValidateUrl(email, password);
        MailUtil.send(email, "帐号激活邮件", redirectValidateUrl);
        return new Result<>(redirectValidateUrl);
    }


    /**
     * 登录
     *
     * @return Result<>
     */
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "login/{email}/{password}", method = RequestMethod.GET)
    public Result<UserModel> login(@PathVariable("email") String email, @PathVariable("password") String password) {
        UserModel userModel = service.findByName(email);
        //找不到用户
        if (userModel == null) {
            return new Result<>(Code.USER_NOT_FOUND.getResultCode(), Code.USER_NOT_FOUND.getMessage());
        }
        //密码不正确
        if (!MD5Util.encode(password, userModel.getSalt()).equals(userModel.getPassword())) {
            return new Result<>(Code.AUTH_FAILED.getResultCode(), Code.AUTH_FAILED.getMessage());
        }
        return new Result<>(userModel);
    }


    /**
     * 修改密码
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public Result<Boolean> changePassword(@RequestBody UserModel user) throws UserNotFoundException {
        UserModel userByEmail = service.findByName(user.getEmail());
        if (userByEmail == null) {
            return new Result<>(Code.USER_NOT_FOUND.getResultCode(), Code.USER_NOT_FOUND.getMessage());
        }
        String salt = RandomUtil.createSalt();
        userByEmail.setPassword(MD5Util.encode(user.getPassword(), salt));
        userByEmail.setName(user.getName());
        userByEmail.setSalt(salt);
        boolean updateUser = service.update(userByEmail);
        return new Result<>(updateUser);
    }

    /**
     * 更新用户信息
     *
     * @return model
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result<Boolean> update(@RequestBody UserModel user) {
        UserModel userModel = service.findByName(user.getEmail());
        if (userModel == null) {
            return new Result<>(Code.USER_NOT_FOUND.getResultCode(), Code.USER_NOT_FOUND.getMessage());
        }
        userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setName(user.getName());
        boolean updateUser = service.update(userModel);
        return new Result<>(updateUser);
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List<Long> ids) {
        Boolean aBoolean = service.delByIds(ids);
        return new Result<>(aBoolean);
    }

    /**
     * 返回所有用户数据
     *
     * @return Result<>
     */
    @ApiOperation(value = "返回所有用户数据", notes = "返回所有用户数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result<List<UserModel>> findAll() {
        List<UserModel> pages = service.findAll();
        if (pages == null || pages.size() <= 0) {
            return new Result<>(Code.NULL_DATA.getResultCode(), Code.NULL_DATA.getMessage());
        }
        return new Result<>(pages);
    }

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页码数
     * @return result
     */
    @Override
    public Result<Page<UserModel>> findAll(@PathVariable int start, @PathVariable int pageSize) {
        Page<UserModel> all = service.findAll(start, pageSize);
        return new Result<>(all);
    }


    /**
     * 根据id删除用户
     *
     * @param id id
     * @return Result<>
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"),
    })
    public Result<Boolean> deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        boolean userModel = service.delById(id);
        if (!userModel) {
            return new Result<>(Code.USER_NOT_FOUND.getResultCode(), Code.USER_NOT_FOUND.getMessage());
        }
        return new Result<>(true);
    }


}
