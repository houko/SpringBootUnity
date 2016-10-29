package info.xiaomo.admin.controller;

import info.xiaomo.core.constant.ErrorCode;
import info.xiaomo.core.constant.GenderType;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import info.xiaomo.core.untils.DateUtil;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.RandomUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/user")
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
    public UserModel findUserById(@RequestParam("id") Long id) {
        UserModel userModel = service.findUserById(id);
        if (userModel == null) {
            return null;
        }
        return userModel;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public UserModel addUser(
            @RequestParam(name = "email", defaultValue = "null") String email,
            @RequestParam(name = "password", defaultValue = "123456") String password,
            @RequestParam(name = "nickName", defaultValue = "新用户") String nickName,
            @RequestParam(name = "phone", defaultValue = "0") Long phone,
            @RequestParam(name = "address", defaultValue = "保密") String address,
            @RequestParam(name = "gender", defaultValue = "0") int gender
    ) {
        UserModel userModel = service.findUserByEmail(email);
        if (userModel != null) {
            return null;
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
        return userModel;
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
    public UserModel changePassword(
            @RequestParam String email,
            @RequestParam String nickName,
            @RequestParam String password
    ) throws UserNotFoundException {
        UserModel userByEmail = service.findUserByEmail(email);
        if (userByEmail == null) {
            return null;
        }
        String salt = RandomUtil.createSalt();
        userByEmail.setPassword(MD5Util.encode(password, salt));
        userByEmail.setNickName(nickName);
        userByEmail.setSalt(salt);
        return service.updateUser(userByEmail);
    }

    /**
     * 更新用户信息
     * @param email email
     * @param nickName nickName
     * @param phone phone
     * @param address address
     * @param gender gender
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public UserModel update(
            @RequestParam(name = "email", defaultValue = "null") String email,
            @RequestParam(name = "nickName", defaultValue = "新用户") String nickName,
            @RequestParam(name = "phone", defaultValue = "0") Long phone,
            @RequestParam(name = "address", defaultValue = "保密") String address,
            @RequestParam(name = "gender", defaultValue = "0") int gender
    ) throws UserNotFoundException {
        UserModel userModel = service.findUserByEmail(email);
        if (userModel == null) {
            return null;
        }
        userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setNickName(nickName);
        userModel.setPhone(phone);
        userModel.setAddress(address);
        userModel.setGender(gender);
        userModel.setValidateCode(MD5Util.encode(email, ""));
        service.updateUser(userModel);
        return userModel;
    }

    /**
     * 分页查询用户
     *
     * @param start    start
     * @param pageSize page
     * @return result
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Page<UserModel> getAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return service.findAll(start, pageSize);
    }

    /**
     * 根据id 删除用户
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public UserModel deleteUserById(@RequestParam("id") Long id) throws UserNotFoundException {
        UserModel userModel = service.deleteUserById(id);
        if (userModel == null) {
            return null;
        }
        return userModel;
    }

    /**
     * 处理激活
     */
    @RequestMapping(value = "validateEmail", method = RequestMethod.POST)
    public UserModel validateEmail(
            @RequestParam(name = "email", defaultValue = "null") String email,
            @RequestParam String validateCode,
            @RequestParam String password,
            @RequestParam(name = "time", defaultValue = "0") Long time
    ) throws ServiceException, ParseException, UserNotFoundException {
        //数据访问层，通过email获取用户信息
        UserModel userModel = service.findUserByEmail(email);
        if (userModel != null) {
            userModel = new UserModel();
            userModel.setResultCode(ErrorCode.USER_REPEAT);
            return userModel;
        }
        //验证码是否过期
        if (time + DateUtil.ONE_DAY_IN_MILLISECONDS * 2 < DateUtil.getNowOfMills()) {
            LOGGER.info("用户{}使用己过期的激活码{}激活邮箱失败！", email, validateCode);
            userModel = new UserModel();
            userModel.setResultCode(ErrorCode.USER_DATA_PASSED);
            return userModel;
        }
        //激活
        String salt = RandomUtil.createSalt();
        userModel = new UserModel();
        userModel.setNickName(email);
        userModel.setEmail(email);
        userModel.setGender(GenderType.secret);
        userModel.setValidateCode(MD5Util.encode(email, salt));
        userModel.setPhone(0L);
        userModel.setSalt(salt);
        userModel.setAddress("");
        userModel.setPassword(MD5Util.encode(password, salt));
        userModel = service.addUser(userModel);
        LOGGER.info("用户{}使用激活码{}激活邮箱成功！", userModel.getEmail(), userModel.getValidateCode());
        return userModel;
    }

}
