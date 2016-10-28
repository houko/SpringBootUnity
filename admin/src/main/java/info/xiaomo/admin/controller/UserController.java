package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/admin/user")
public class UserController extends BaseController {

    @Autowired
    private UserService service;

    /**
     * 根据id 查找用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public HashMap<String, Object> findUserById(@RequestParam("id") Long id) {
        result = new HashMap<>();
        UserModel userModel = service.findUserById(id);
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(user, userModel);
        return result;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public HashMap<String, Object> addUser(
            @RequestParam(name = "email", defaultValue = "null") String email,
            @RequestParam(name = "password", defaultValue = "123456") String password,
            @RequestParam(name = "nickName", defaultValue = "新用户") String nickName,
            @RequestParam(name = "phone", defaultValue = "0") Long phone,
            @RequestParam(name = "address", defaultValue = "保密") String address,
            @RequestParam(name = "gender", defaultValue = "0") int gender
    ) {
        result = new HashMap<>();
        UserModel userModel = service.findUserByEmail(email);
        if (userModel != null) {
            result.put(code, repeat);
            return result;
        }
        String salt = RandomUtil.createSalt();
        userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setNickName(nickName);
        userModel.setPhone(phone);
        userModel.setAddress(address);
        userModel.setGender(gender);
        userModel.setPassword(MD5Util.encode(password, salt));
        userModel.setValidateCode(MD5Util.encode(email, ""));
        userModel.setSalt(salt);
        service.addUser(userModel);
        result.put(user, userModel);
        result.put(code, success);
        return result;
    }


    /**
     * 修改密码
     *
     * @param email
     * @param password
     * @return
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public HashMap<String, Object> changePassword(
            @RequestParam String email,
            @RequestParam String nickName,
            @RequestParam String password
    ) throws UserNotFoundException {
        result = new HashMap<>();
        UserModel userByEmail = service.findUserByEmail(email);
        if (userByEmail == null) {
            result.put(code, notFound);
            return result;
        }
        String salt = RandomUtil.createSalt();
        userByEmail.setPassword(MD5Util.encode(password, salt));
        userByEmail.setNickName(nickName);
        userByEmail.setSalt(salt);
        UserModel userModel = service.updateUser(userByEmail);
        result.put(code, success);
        result.put(user, userModel);
        return result;
    }

    /**
     *
     * @param email
     * @param nickName
     * @param phone
     * @param address
     * @param gender
     * @return
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap<String, Object> update(
            @RequestParam(name = "email", defaultValue = "null") String email,
            @RequestParam(name = "nickName", defaultValue = "新用户") String nickName,
            @RequestParam(name = "phone", defaultValue = "0") Long phone,
            @RequestParam(name = "address", defaultValue = "保密") String address,
            @RequestParam(name = "gender", defaultValue = "0") int gender
    ) throws UserNotFoundException {
        result = new HashMap<>();
        UserModel userModel = service.findUserByEmail(email);
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setNickName(nickName);
        userModel.setPhone(phone);
        userModel.setAddress(address);
        userModel.setGender(gender);
        userModel.setValidateCode(MD5Util.encode(email, ""));
        service.updateUser(userModel);
        result.put(user, userModel);
        result.put(code, success);
        return result;
    }

    /**
     * 分页查询用户
     *
     * @param start start
     * @param pageSize  page
     * @return result
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public HashMap<String, Object> getAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        result = new HashMap<>();
        Page<UserModel> pages = service.findAll(start, pageSize);
        result.put(code, success);
        result.put(users, pages);
        return result;
    }

    /**
     * 根据id 删除用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public HashMap<String, Object> deleteUserById(@RequestParam("id") Long id) throws UserNotFoundException {
        result = new HashMap<>();
        UserModel userModel = service.deleteUserById(id);
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(user, userModel);
        return result;
    }
}
