package info.xiaomo.web.controller;

import info.xiaomo.core.constant.WebDefaultValueConst;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import info.xiaomo.core.untils.MD5;
import info.xiaomo.core.untils.TimeUtil;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("api/web/user")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;


    /**
     * 登录
     *
     * @param email    email
     * @param password password
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public HashMap<String, Object> login(@RequestParam String email, @RequestParam String password) {
        UserModel userModel = service.findUserByEmail(email);
        //找不到用户
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        //邮箱未验证
        if (userModel.getValidateStatus() == 0) {
            result.put(code, notActivated);
            result.put("user", userModel);
            return result;
        }
        //密码不正确
        if (!MD5.encode(password).equals(userModel.getPassword())) {
            result.put(code, error);
            return result;
        }
        result.put(code, success);
        result.put("user", userModel);
        return result;
    }


    /**
     * 注册
     *
     * @param nickName nickName
     * @param password password
     * @param email    email
     * @param gender   gender
     * @param phone    phone
     * @param address  address
     * @return result
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public HashMap<String, Object> register(
            @RequestParam String nickName,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam int gender,
            @RequestParam long phone,
            @RequestParam String address
    ) {
        UserModel userModel = service.findUserByEmail(email);
        //邮箱被占用
        if (userModel != null) {
            result.put(code, notFound);
            return result;
        }
        userModel = new UserModel();
        userModel.setNickName(nickName);
        userModel.setEmail(email);
        userModel.setGender(gender);
        userModel.setValidateStatus(0);//默认未验证
        userModel.setValidateCode(MD5.encode(email));
        userModel.setPhone(phone);
        userModel.setImgUrl(WebDefaultValueConst.defaultImage);
        userModel.setAddress(address);
        userModel.setPassword(MD5.encode(password));
        userModel = service.addUser(userModel);
        if (userModel != null) {
            result.put(code, success);
            result.put("user", userModel);
        } else {
            result.put(code, error);
        }
        return result;
    }


    /**
     * 检测帐号有没有被注册
     *
     * @param email email
     * @return result
     */
    @RequestMapping(value = "findById/{email}", method = RequestMethod.GET)
    public Map<String, Object> findUserByEmail(@PathVariable("email") String email) {
        UserModel userModel = service.findUserByEmail(email);
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put("user", userModel);
        return result;
    }


    /**
     * 检测帐号有没有被注册
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public HashMap<String, Object> findUserById(@PathVariable("id") Long id) {
        UserModel userModel = service.findUserById(id);
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put("user", userModel);
        return result;
    }

    /**
     * 修改个人信息
     *
     * @param nickName nickName
     * @param password password
     * @param email    email
     * @param imgUrl   imgUrl
     * @param gender   gender
     * @param phone    phone
     * @param address  address
     * @return result
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap<String, Object> update(
            @RequestParam String nickName,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String imgUrl,
            @RequestParam int gender,
            @RequestParam long phone,
            @RequestParam String address
    ) throws UserNotFoundException {
        UserModel userModel = service.findUserByEmail(email);
        //找不到用户
        if (userModel != null) {
            result.put(code, error);
            return result;
        }
        userModel = new UserModel();
        userModel.setNickName(nickName);
        userModel.setEmail(email);
        userModel.setGender(gender);
        userModel.setPhone(phone);
        userModel.setImgUrl(imgUrl);
        userModel.setAddress(address);
        userModel.setPassword(MD5.encode(password));
        userModel = service.updateUser(userModel);
        if (userModel != null) {
            result.put(code, success);
            result.put("user", userModel);
        } else {
            result.put(code, error);
        }
        return result;
    }

    /**
     * 处理激活
     *
     * @throws ParseException
     */
    @RequestMapping(value = "validateEmail", method = RequestMethod.GET)
    public HashMap<String, Object> validateEmail(
            @RequestParam String email,
            @RequestParam String validateCode
    ) throws ServiceException, ParseException, UserNotFoundException {
        //数据访问层，通过email获取用户信息
        UserModel user = service.findUserByEmail(email);
        //验证用户是否存在
        if (user == null) {
            result.put(code, notFound);
            return result;
        }
        //验证用户激活状态
        if (user.getValidateStatus() == 1) {
            result.put(code, activated);
            return result;
        }
        //验证码是否过期
        Date lastDate = TimeUtil.getDateAfter(new Date(), 2);//获取激活码过期时间
        if (!lastDate.after(user.getUpdateTime())) {
            LOGGER.info("用户{}使用己过期的激活码{}激活邮箱失败！", user.getEmail(), user.getValidateCode());
            result.put(code, expired);
            return result;
        }
        //验证码是否正确
        if (!validateCode.equals(user.getValidateCode())) {
            result.put(code, error);
            return result;
        }
        //激活
        user.setValidateStatus(1);//把状态改为激活
        UserModel userModel = service.updateUser(user);
        LOGGER.info("用户{}使用激活码{}激活邮箱成功！", user.getEmail(), user.getValidateCode());
        result.put("user", userModel);
        return result;
    }


}
