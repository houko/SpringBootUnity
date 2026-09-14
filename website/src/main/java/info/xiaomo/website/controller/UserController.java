package info.xiaomo.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.CodeConst;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.utils.Md5Util;
import info.xiaomo.core.utils.RandomUtil;
import info.xiaomo.core.utils.TimeUtil;
import info.xiaomo.website.model.UserModel;
import info.xiaomo.website.service.UserService;
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
@Tag(name = "用户相关api", description = "用户相关api")
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
    @Operation(summary = "查找用户", description = "查找用户")
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @Parameters({
            @Parameter(name = "id", description = "唯一id", required = true, in = ParameterIn.PATH),
    })
    @SuppressWarnings("unchecked")
    public Result findUserById(@PathVariable("id") Long id) {
        Optional<UserModel> optional = service.findUserById(id);
        return optional.map(Result::new).orElseGet(() -> new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage()));
    }

    /**
     * 添加用户
     */
    @Operation(summary = "添加用户", description = "添加用户")
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
    @Operation(summary = "注册", description = "注册用户")
    @Parameters({
            @Parameter(name = "email", description = "邮箱", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY)
    })
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@RequestParam("email") String email, @RequestParam("password") String password) {
        UserModel userModel = service.findUserByEmail(email);
        //邮箱被占用
        if (userModel != null) {
            return new Result<>(CodeConst.USER_REPEAT.getResultCode(), CodeConst.USER_REPEAT.getMessage());
        }
        // 直接创建账号, 密码加盐 MD5 哈希后入库, 表单密码只经 POST body 传输, 不再通过邮件回传明文密码
        String salt = RandomUtil.createSalt();
        UserModel newUser = new UserModel();
        newUser.setEmail(email);
        newUser.setPassword(Md5Util.encode(password, salt));
        newUser.setSalt(salt);
        newUser.setValidateCode(Md5Util.encode(email, ""));
        newUser.setRegisterTime(TimeUtil.getNowOfMills());
        service.addUser(newUser);
        return new Result<>(newUser);
    }


    /**
     * 登录
     *
     * @return result
     */
    @Operation(summary = "登录", description = "登录")
    @Parameters({
            @Parameter(name = "email", description = "邮箱", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "password", description = "密码", required = true, in = ParameterIn.QUERY)
    })
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestParam("email") String email, @RequestParam("password") String password) {
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
    @Operation(summary = "修改密码", description = "修改密码")
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
    @Operation(summary = "更新用户信息", description = "更新用户信息")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody UserModel user) throws UserNotFoundException {
        UserModel userModel = service.findUserByEmail(user.getEmail());
        if (userModel == null) {
            return new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
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
    @Operation(summary = "返回所有用户数据", description = "返回所有用户数据")
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
    @Operation(summary = "根据id删除用户", description = "根据id删除用户")
    @Parameters({
            @Parameter(name = "id", description = "唯一id", required = true, in = ParameterIn.PATH),
    })
    public Result deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        UserModel userModel = service.deleteUserById(id);
        if (userModel == null) {
            return new Result<>(CodeConst.USER_NOT_FOUND.getResultCode(), CodeConst.USER_NOT_FOUND.getMessage());
        }
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
