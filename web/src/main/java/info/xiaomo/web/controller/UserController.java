package info.xiaomo.web.controller;

import info.xiaomo.core.constant.GenderType;
import info.xiaomo.core.constant.Symbol;
import info.xiaomo.core.constant.WebDefaultValueConst;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.QQUserModel;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.QQUserService;
import info.xiaomo.core.service.UserService;
import info.xiaomo.core.untils.DateUtil;
import info.xiaomo.core.untils.FileUtil;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.MailUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
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
@RequestMapping("/web/user")
public class UserController extends BaseController {

    @Autowired
    private UserService service;

    @Autowired
    private QQUserService qqUserService;

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
            result.put(user, userModel);
            return result;
        }
        //密码不正确
        if (!MD5Util.encode(password).equals(userModel.getPassword())) {
            result.put(code, error);
            return result;
        }
        result.put(code, success);
        result.put(user, userModel);
        return result;
    }


    /**
     * 注册
     *
     * @param password password
     * @param email    email
     * @return result
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public HashMap<String, Object> register(
            @RequestParam String email
    ) throws Exception {
        UserModel userModel = service.findUserByEmail(email);
        //邮箱被占用
        if (userModel != null) {
            result.put(code, repeat);
            return result;
        }
        String redirectValidateUrl = MailUtil.redirectValidateUrl(email);
        MailUtil.send(email, redirectValidateUrl);
        result.put(code, success);
        return result;
    }


    /**
     * 检测帐号有没有被注册
     *
     * @param email email
     * @return result
     */
    @RequestMapping(value = "findByEmail", method = RequestMethod.GET)
    public Map<String, Object> findUserByEmail(@RequestParam("email") String email) {
        UserModel userModel = service.findUserByEmail(email);
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(user, userModel);
        return result;
    }


    /**
     * 检测帐号有没有被注册
     *
     * @param id id
     * @return result
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public HashMap<String, Object> findUserById(@RequestParam("id") Long id) {
        UserModel userModel = service.findUserById(id);
        if (userModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(user, userModel);
        return result;
    }

    /**
     * 修改个人信息
     *
     * @param nickName nickName
     * @param password password
     * @param email    email
     * @param imgUrl      imgUrl
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
            @RequestParam MultipartFile imgUrl,
            @RequestParam int gender,
            @RequestParam long phone,
            @RequestParam String address
    ) throws UserNotFoundException, IOException {
        UserModel userModel = service.findUserByEmail(email);
        //找不到用户
        if (userModel == null) {
            result.put(code, error);
            return result;
        }
        //判断是否是图片
        if (FileUtil.isImage(imgUrl.getOriginalFilename())) {
            result.put(code, notImg);
            return result;
        }
        //目标文件名
        String imgUrlUrl = FileUtil.upload(imgUrl, email);
        userModel = new UserModel();
        userModel.setNickName(nickName);
        userModel.setEmail(email);
        userModel.setGender(gender);
        userModel.setPhone(phone);
        userModel.setImgUrl(imgUrlUrl);
        userModel.setAddress(address);
        userModel.setPassword(MD5Util.encode(password));
        userModel = service.updateUser(userModel);
        if (userModel != null) {
            result.put(code, success);
            result.put(user, userModel);
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
            @RequestParam String validateCode,
            @RequestParam String password,
            @RequestParam Long time
    ) throws ServiceException, ParseException, UserNotFoundException {
        //数据访问层，通过email获取用户信息
        UserModel userModel = service.findUserByEmail(email);
        //验证用户是否存在
        if (userModel != null) {
            result.put(code, error);
        }
        //验证码是否过期
        if (time + DateUtil.ONE_DAY_IN_MILLISECONDS * 2 < DateUtil.getNowOfMills()) {
            LOGGER.info("用户{}使用己过期的激活码{}激活邮箱失败！", email, validateCode);
            result.put(code, expired);
            return result;
        }
        //激活
        userModel = new UserModel();
        userModel.setNickName(email);
        userModel.setEmail(email);
        userModel.setGender(GenderType.secret);
        userModel.setImgUrl(WebDefaultValueConst.defaultImage);//默认是个百度的LOGO，作测试用
        userModel.setValidateStatus(1);//状态:己激活
        userModel.setValidateCode(MD5Util.encode(email));
        userModel.setPhone(0L);
        userModel.setAddress("");
        userModel.setPassword(MD5Util.encode(password));
        userModel = service.addUser(userModel);
        LOGGER.info("用户{}使用激活码{}激活邮箱成功！", userModel.getEmail(), userModel.getValidateCode());
        result.put(user, userModel);
        return result;
    }


    @RequestMapping("/qq")
    public HashMap<String, Object> qqLogin(
            @RequestParam String openId,
            @RequestParam String nickName,
            @RequestParam String imgUrlUrl,
            @RequestParam String gender,
            @RequestParam int year,
            @RequestParam String province,
            @RequestParam String city
    ) {
        QQUserModel user = new QQUserModel();
        user.setOpenId(openId);
        user.setNickName(nickName);
        user.setYear(year);
        user.setAddress(province + Symbol.SPACE + city);
        user.setGender(gender);
        user.setImgUrl(imgUrlUrl);
        QQUserModel model = qqUserService.add(user);
        if (model == null) {
            result.put(code, repeat);
            return result;
        }
        result.put(code, success);
        result.put(qqUser, model);
        return result;
    }
}
