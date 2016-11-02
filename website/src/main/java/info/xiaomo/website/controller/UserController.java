package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Err;
import info.xiaomo.core.constant.GenderType;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.website.UserModel;
import info.xiaomo.core.service.website.UserService;
import info.xiaomo.core.untils.DateUtil;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.MailUtil;
import info.xiaomo.core.untils.RandomUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 2016/4/1 17:51
 * @Description: 用户控制器
 * @Copyright(©) 2015 by xiaomo.
 **/
@RestController
@RequestMapping("/user")
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
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Result findUserById(@RequestParam("id") Long id) {
        UserModel userModel = service.findUserById(id);
        if (userModel == null) {
            return new Result(Err.USER_NOT_FOUND.getErrorCode(), Err.USER_NOT_FOUND.getErrorMsg());
        }
        return new Result(userModel);
    }

    /**
     * 添加用户
     */
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Result addUser(@RequestBody UserModel user) {
        UserModel userModel = service.findUserByEmail(user.getEmail());
        if (userModel != null) {
            return new Result(Err.USER_REPEAT.getErrorCode(), Err.USER_REPEAT.getErrorMsg());
        }
        String salt = RandomUtil.createSalt();
        user.setPassword(MD5Util.encode(user.getPassword(), salt));
        user.setValidateCode(MD5Util.encode(user.getEmail(), ""));
        user.setSalt(salt);
        service.addUser(user);
        return new Result(user);
    }

    /**
     * 注册
     *
     * @return result
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@RequestBody UserModel user) throws Exception {
        UserModel userModel = service.findUserByEmail(user.getEmail());
        //邮箱被占用
        if (userModel != null) {
            return new Result(Err.USER_REPEAT.getErrorCode(), Err.USER_REPEAT.getErrorMsg());
        }
        String redirectValidateUrl = MailUtil.redirectValidateUrl(user.getEmail(), user.getPassword());
        MailUtil.send(user.getEmail(), redirectValidateUrl);
        return new Result(redirectValidateUrl);
    }


    /**
     * 登录
     *
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody UserModel user) {
        UserModel userModel = service.findUserByEmail(user.getEmail());
        //找不到用户
        if (userModel == null) {
            return new Result(Err.USER_NOT_FOUND.getErrorCode(), Err.USER_NOT_FOUND.getErrorMsg());
        }
        //密码不正确
        if (!MD5Util.encode(user.getPassword(), userModel.getSalt()).equals(userModel.getPassword())) {
            return new Result(Err.AUTH_FAILED.getErrorCode(), Err.AUTH_FAILED.getErrorMsg());
        }
        return new Result(userModel);
    }


    /**
     * 修改密码
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public Result changePassword(@RequestBody UserModel user) throws UserNotFoundException {
        UserModel userByEmail = service.findUserByEmail(user.getEmail());
        if (userByEmail == null) {
            return new Result(Err.USER_NOT_FOUND.getErrorCode(), Err.USER_NOT_FOUND.getErrorMsg());
        }
        String salt = RandomUtil.createSalt();
        userByEmail.setPassword(MD5Util.encode(user.getPassword(), salt));
        userByEmail.setNickName(user.getNickName());
        userByEmail.setSalt(salt);
        UserModel updateUser = service.updateUser(userByEmail);
        return new Result(updateUser);
    }

    /**
     * 更新用户信息
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody UserModel user) throws UserNotFoundException {
        UserModel userModel = service.findUserByEmail(user.getEmail());
        if (userModel == null) {
            return new Result(Err.USER_NOT_FOUND.getErrorCode(), Err.USER_NOT_FOUND.getErrorMsg());
        }
        userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setNickName(user.getNickName());
        userModel.setPhone(user.getPhone());
        userModel.setAddress(user.getAddress());
        userModel.setGender(user.getGender());
        userModel.setValidateCode(MD5Util.encode(user.getEmail(), ""));
        UserModel updateUser = service.updateUser(userModel);
        return new Result(updateUser);
    }

    /**
     * 分页查询用户
     *
     * @param start    start
     * @param pageSize page
     * @return result
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result getAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<UserModel> pages = service.findAll(start, pageSize);
        if (pages == null || pages.getSize() <= 0) {
            return new Result(Err.NULL_DATA.getErrorCode(), Err.NULL_DATA.getErrorMsg());
        }
        return new Result(pages);
    }

    /**
     * 根据id 删除用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public Result deleteUserById(@RequestParam("id") Long id) throws UserNotFoundException {
        UserModel userModel = service.deleteUserById(id);
        if (userModel == null) {
            return new Result(Err.USER_NOT_FOUND.getErrorCode(), Err.USER_NOT_FOUND.getErrorMsg());
        }
        return new Result(userModel);
    }

    /**
     * 处理激活
     */
    @RequestMapping(value = "validateEmail", method = RequestMethod.POST)
    public Result validateEmail(@RequestBody UserModel user
    ) throws ServiceException, ParseException, UserNotFoundException {
        //数据访问层，通过email获取用户信息
        UserModel userModel = service.findUserByEmail(user.getEmail());
        if (userModel != null) {
            return new Result(Err.USER_REPEAT.getErrorCode(), Err.USER_REPEAT.getErrorMsg());
        }
        //验证码是否过期
        if (user.getRegisterTime() + DateUtil.ONE_DAY_IN_MILLISECONDS * 2 < DateUtil.getNowOfMills()) {
            LOGGER.info("用户{}使用己过期的激活码{}激活邮箱失败！", user.getEmail(), user.getEmail());
            return new Result(Err.TIME_PASSED.getErrorCode(), Err.TIME_PASSED.getErrorMsg());
        }
        //激活
        String salt = RandomUtil.createSalt();
        userModel = new UserModel();
        userModel.setNickName(user.getNickName());
        userModel.setEmail(user.getEmail());
        userModel.setGender(GenderType.secret);
        userModel.setValidateCode(MD5Util.encode(user.getEmail(), salt));
        userModel.setPhone(0L);
        userModel.setSalt(salt);
        userModel.setAddress("");
        userModel.setPassword(MD5Util.encode(user.getPassword(), salt));
        userModel = service.addUser(userModel);
        LOGGER.info("用户{}使用激活码{}激活邮箱成功！", userModel.getEmail(), userModel.getValidateCode());
        return new Result(userModel);
    }

}
