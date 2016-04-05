package info.xiaomo.web.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import info.xiaomo.core.untils.MD5;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

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

    @Autowired
    private UserService service;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public HashMap<String, Object> login(@RequestParam String email, @RequestParam String password) {
        HashMap<String, Object> result = new HashMap<>();
        UserModel userModel = service.findUserByEmail(email);
        if (userModel == null) {
            result.put(code, notFound);
        } else {
            if (MD5.encode(password).equals(userModel.getPassword())) {
                result.put(code, success);
                result.put("user", userModel);
            } else {
                result.put(code, error);
            }
        }
        return result;
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    public HashMap<String, Object> register(
            @RequestParam String nickName,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam int gender,
            @RequestParam long phone,
            @RequestParam String address
    ) {
        HashMap<String, Object> result = new HashMap<>();
        UserModel userModel = service.findUserByEmail(email);
        if (userModel != null) {
            result.put(code, error);
        } else {
            userModel = new UserModel();
            userModel.setNickName(nickName);
            userModel.setEmail(email);
            userModel.setGender(gender);
            userModel.setValidateStatus(0);//默认未验证
            userModel.setValidateCode(MD5.encode(email));
            userModel.setPhone(phone);
            userModel.setAddress(address);
            userModel.setPassword(MD5.encode(password));
            UserModel res = service.addUser(userModel);
            if (res != null) {
                result.put(code, success);
                result.put("user", userModel);
            } else {
                result.put(code, error);
            }
        }
        return result;
    }


    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public UserModel findUserById(@PathVariable("id") Long id) {
        return service.findUserById(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap<String, Object> update(
            @RequestParam String nickName,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam int gender,
            @RequestParam long phone,
            @RequestParam String address
    ) throws UserNotFoundException {
        HashMap<String, Object> result = new HashMap<>();
        UserModel userModel = service.findUserByEmail(email);
        if (userModel != null) {
            result.put(code, error);
        } else {
            userModel = new UserModel();
            userModel.setNickName(nickName);
            userModel.setEmail(email);
            userModel.setGender(gender);
            userModel.setPhone(phone);
            userModel.setAddress(address);
            userModel.setPassword(MD5.encode(password));
            UserModel res = service.updateUser(userModel);
            if (res != null) {
                result.put(code, success);
                result.put("user", userModel);
            } else {
                result.put(code, error);
            }
        }
        return result;
    }

    /**
     * 处理激活
     *
     * @throws ParseException
     */
    @RequestMapping(value = "validateEmail", method = RequestMethod.GET)
    public void validateEmail(@RequestParam String email, @RequestParam String validateCode) throws ServiceException, ParseException, UserNotFoundException {
        //数据访问层，通过email获取用户信息
        UserModel user = service.findUserByEmail(email);
        //验证用户是否存在
        if (user != null) {
            //验证用户激活状态
            if (user.getValidateStatus() == 0) {
                ///没激活
                Date currentTime = new Date();//获取当前时间
                //验证链接是否过期
                currentTime.before(user.getCreateTime());
                if (currentTime.before(user.getUpdateTime())) {
                    //验证激活码是否正确
                    if (validateCode.equals(user.getValidateCode())) {
                        //激活成功， //并更新用户的激活状态，为已激活
                        System.out.println("==sq===" + user.getValidateStatus());
                        user.setValidateStatus(1);//把状态改为激活
                        System.out.println("==sh===" + user.getValidateStatus());
                        service.updateUser(user);
                    } else {
                        throw new ServiceException("激活码不正确");
                    }
                } else {
                    throw new ServiceException("激活码已过期！");
                }
            } else {
                throw new ServiceException("邮箱已激活，请登录！");
            }
        } else {
            throw new ServiceException("该邮箱未注册（邮箱地址不存在）！");
        }

    }


}
