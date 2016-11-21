package info.xiaomo.website.controller;

import freemarker.template.Configuration;
import info.xiaomo.core.constant.Err;
import info.xiaomo.core.constant.GenderType;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.RandomUtil;
import info.xiaomo.core.untils.TimeUtil;
import info.xiaomo.website.model.UserModel;
import info.xiaomo.website.service.UserService;
import info.xiaomo.website.util.MailUtil;
import info.xiaomo.website.view.UserView;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

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
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService service;

    private final Configuration configuration;

    @Autowired
    public UserController(UserService service, Configuration configuration) {
        this.service = service;
        this.configuration = configuration;
    }


    @RequestMapping(value = "toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return UserView.LOGIN.getName();
    }

    @RequestMapping(value = "toRegister", method = RequestMethod.GET)
    public String toRegister() {
        return UserView.REGISTER.getName();
    }

    /**
     * 登录
     *
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        ModelMap map) {
        UserModel userModel = service.findUserByEmail(email);
        //找不到用户
        if (userModel == null) {
            map.put("errMsg", "找不到用户");
            return UserView.LOGIN.getName();
        }
        //密码不正确
        if (!MD5Util.encode(password, userModel.getSalt()).equals(userModel.getPassword())) {
            map.put("errMsg", "密码不正确");
            return UserView.LOGIN.getName();
        }
        session.setAttribute("currentUser", userModel);
        return UserView.INDEX.getName();
    }

    /**
     * 根据id 查找用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public Result findUserById(@PathVariable("id") Long id) {
        UserModel userModel = service.findUserById(id);
        if (userModel == null) {
            return new Result(Err.USER_NOT_FOUND.getCode(), Err.USER_NOT_FOUND.getMessage());
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
            return new Result(Err.USER_REPEAT.getCode(), Err.USER_REPEAT.getMessage());
        }
        String salt = RandomUtil.createSalt();
        user.setPassword(MD5Util.encode(user.getPassword(), salt));
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
    public String register(@RequestParam("email") String email,
                           @RequestParam("password") String password,
                           ModelMap map) throws Exception {
        UserModel userModel = service.findUserByEmail(email);
        //邮箱被占用
        if (userModel != null) {
            map.put("errMsg", "邮箱被占用！");
            return UserView.REGISTER.getName();
        }
        String content = MailUtil.getContent(email, password, configuration);
        MailUtil.send(email, "帐号激活邮件", content);
        return UserView.REGISTER_INFO.getName();
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
            return new Result(Err.USER_NOT_FOUND.getCode(), Err.USER_NOT_FOUND.getMessage());
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
            return new Result(Err.USER_NOT_FOUND.getCode(), Err.USER_NOT_FOUND.getMessage());
        }
        userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setNickName(user.getNickName());
        userModel.setPhone(user.getPhone());
        userModel.setAddress(user.getAddress());
        userModel.setGender(user.getGender());
        UserModel updateUser = service.updateUser(userModel);
        return new Result(updateUser);
    }

    /**
     * 返回所有用户数据
     *
     * @return result
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result getAll() {
        List<UserModel> pages = service.findAll();
        if (pages == null || pages.size() <= 0) {
            return new Result(Err.NULL_DATA.getCode(), Err.NULL_DATA.getMessage());
        }
        return new Result(pages);
    }


    /**
     * 根据id删除用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public Result deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        UserModel userModel = service.deleteUserById(id);
        if (userModel == null) {
            return new Result(Err.USER_NOT_FOUND.getCode(), Err.USER_NOT_FOUND.getMessage());
        }
        return new Result(userModel);
    }

    /**
     * 处理激活
     */
    @RequestMapping(value = "validate", method = RequestMethod.GET)
    public String validateEmail(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Long time,
            ModelMap map,
            HttpSession session
    ) throws ServiceException, ParseException, UserNotFoundException {
        //数据访问层，通过email获取用户信息
        UserModel userModel = service.findUserByEmail(email);
        if (userModel != null) {
            map.put("errMsg", "邮箱己被占用");
            return UserView.REGISTER.getName();
        }
        //验证码是否过期
        if (time + TimeUtil.ONE_DAY_IN_MILLISECONDS * 2 < TimeUtil.getNowOfMills()) {
            LOGGER.info("用户{}使用己过期时间{}激活邮箱失败！", email, time);
            map.put("errMsg", "时间己过期，请重新注册");
            return UserView.REGISTER.getName();
        }
        //激活
        String salt = RandomUtil.createSalt();
        userModel = new UserModel();
        userModel.setNickName(email);
        userModel.setEmail(email);
        userModel.setGender(GenderType.secret);
        userModel.setPhone(0L);
        userModel.setSalt(salt);
        userModel.setAddress("");
        userModel.setPassword(MD5Util.encode(password, salt));
        userModel = service.addUser(userModel);
        LOGGER.info("用户{}激活邮箱成功！", userModel.getEmail());
        session.setAttribute("currentUser", userModel);
        return UserView.INDEX.getName();
    }
}
