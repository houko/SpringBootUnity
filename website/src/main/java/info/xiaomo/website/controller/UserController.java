package info.xiaomo.website.controller;

import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.core.constant.GenderConst;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.untils.MailUtil;
import info.xiaomo.core.untils.Md5Util;
import info.xiaomo.core.untils.RandomUtil;
import info.xiaomo.core.untils.TimeUtil;
import info.xiaomo.website.model.UserModel;
import info.xiaomo.website.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!

 *
 * @author : xiaomo
 * github: https://github.com/houko
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/4/1 17:51
 * Description: 用户控制器
 * Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/user")
@Api(value = "用户相关api", description = "用户相关api")
public class UserController extends BaseController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 根据id 查找用户
     *
     * @param id id
     * @return result
     */
    @ApiOperation(value = "查找用户", notes = "查找用户", httpMethod = "GET")
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"),
    })
    @SuppressWarnings("unchecked")
    public Result findUserById(@PathVariable("id") Long id) {
        Optional<UserModel> optional = service.findUserById(id);
        return optional.map(Result::new).orElseGet(() -> new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage()));
    }

    /**
     * 添加用户
     */
    @ApiOperation(value = "添加用户", notes = "添加用户", httpMethod = "POST")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Result addUser(@RequestBody UserModel user) {
        UserModel userModel = service.findUserByEmail(user.getEmail());
        if (userModel != null) {
            return new Result<>(CodeConst.USER_REPEAT.getResultCode(), CodeConst.USER_REPEAT.getMessage());
        }
        String salt = RandomUtil.createSalt();
        user.setPassword(Md5Util.encode(user.getPassword(), salt));
        user.setValidateCode(Md5Util.encode(user.getEmail(), ""));
        user.setSalt(salt);
        service.addUser(user);
        return new Result<>(user);
    }

    /**
     * 注册
     *
     * @return result
     */
    @ApiOperation(value = "注册", notes = "注册用户并发送验证链接到邮箱", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "密码", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "register/{email}/{password}", method = RequestMethod.POST)
    public Result register(@PathVariable("email") String email, @PathVariable("password") String password) {
        UserModel userModel = service.findUserByEmail(email);
        //邮箱被占用
        if (userModel != null) {
            return new Result<>(CodeConst.USER_REPEAT.getResultCode(), CodeConst.USER_REPEAT.getMessage());
        }
        String redirectValidateUrl = MailUtil.redirectValidateUrl(email, password);
        MailUtil.send(email, "帐号激活邮件", redirectValidateUrl);
        return new Result<>(redirectValidateUrl);
    }


    /**
     * 登录
     *
     * @return result
     */
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "login/{email}/{password}", method = RequestMethod.POST)
    public Result login(@PathVariable("email") String email, @PathVariable("password") String password) {
        UserModel userModel = service.findUserByEmail(email);
        //找不到用户
        if (userModel == null) {
            return new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
        //密码不正确
        if (!Md5Util.encode(password, userModel.getSalt()).equals(userModel.getPassword())) {
            return new Result<>(CodeConst.AUTH_FAILED.getResultCode(), CodeConst.AUTH_FAILED.getMessage());
        }
        return new Result<>(userModel);
    }


    /**
     * 修改密码
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST")
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public Result changePassword(@RequestBody UserModel user) throws UserNotFoundException {
        UserModel userByEmail = service.findUserByEmail(user.getEmail());
        if (userByEmail == null) {
            return new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
        String salt = RandomUtil.createSalt();
        userByEmail.setPassword(Md5Util.encode(user.getPassword(), salt));
        userByEmail.setNickName(user.getNickName());
        userByEmail.setSalt(salt);
        UserModel updateUser = service.updateUser(userByEmail);
        return new Result<>(updateUser);
    }

    /**
     * 更新用户信息
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody UserModel user) throws UserNotFoundException {
        UserModel userModel = service.findUserByEmail(user.getEmail());
        if (userModel == null) {
            return new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
        userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setNickName(user.getNickName());
        userModel.setPhone(user.getPhone());
        userModel.setAddress(user.getAddress());
        userModel.setGender(user.getGender());
        userModel.setValidateCode(Md5Util.encode(user.getEmail(), ""));
        UserModel updateUser = service.updateUser(userModel);
        return new Result<>(updateUser);
    }

    /**
     * 返回所有用户数据
     *
     * @return result
     */
    @ApiOperation(value = "返回所有用户数据", notes = "返回所有用户数据", httpMethod = "GET")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result getAll() {
        List<UserModel> pages = service.findAll();
        if (pages == null || pages.size() <= 0) {
            return new Result<>(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(pages);
    }


    /**
     * 根据id删除用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"),
    })
    public Result deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        UserModel userModel = service.deleteUserById(id);
        if (userModel == null) {
            return new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
        return new Result<>(userModel);
    }

    /**
     * 处理激活
     */
    @ApiOperation(value = "处理激活", notes = "处理激活", httpMethod = "POST")
    @RequestMapping(value = "validateEmail", method = RequestMethod.POST)
    public Result validateEmail(@RequestBody UserModel user
    ) throws ServiceException {
        //数据访问层，通过email获取用户信息
        UserModel userModel = service.findUserByEmail(user.getEmail());
        if (userModel != null) {
            return new Result<>(CodeConst.USER_REPEAT.getResultCode(), CodeConst.USER_REPEAT.getMessage());
        }
        //验证码是否过期
        if (user.getRegisterTime() + TimeUtil.ONE_DAY_IN_MILLISECONDS < TimeUtil.getNowOfMills()) {
            LOGGER.info("用户{}使用己过期的激活码{}激活邮箱失败！", user.getEmail(), user.getEmail());
            return new Result<>(CodeConst.TIME_PASSED.getResultCode(), CodeConst.TIME_PASSED.getMessage());
        }
        //激活
        String salt = RandomUtil.createSalt();
        userModel = new UserModel();
        userModel.setNickName(user.getNickName());
        userModel.setEmail(user.getEmail());
        userModel.setGender(GenderConst.SECRET);
        userModel.setValidateCode(Md5Util.encode(user.getEmail(), salt));
        userModel.setPhone(0L);
        userModel.setSalt(salt);
        userModel.setAddress("");
        userModel.setPassword(Md5Util.encode(user.getPassword(), salt));
        userModel = service.addUser(userModel);
        LOGGER.info("用户{}使用激活码{}激活邮箱成功！", userModel.getEmail(), userModel.getValidateCode());
        return new Result<>(userModel);
    }

    /**
     * 查找所有(不带分页)
     *
     * @return result
     */
    @Override
    public Result<List> findAll() {
        return null;
    }

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页码数
     * @return result
     */
    @Override
    public Result<Page> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    /**
     * 根据id查看模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    /**
     * 根据名字查找模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result findByName(@PathVariable String name) {
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
     * 添加模型
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> add(@RequestBody Object model) {
        return null;
    }

    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> update(@RequestBody Object model) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List ids) {
        return null;
    }
}
