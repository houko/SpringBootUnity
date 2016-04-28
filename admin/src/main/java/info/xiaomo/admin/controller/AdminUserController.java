package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.AdminModel;
import info.xiaomo.core.service.AdminUserService;
import info.xiaomo.core.untils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * │＼＿＿╭╭╭╭╭＿＿／│
 * │　　　　　　　　　│
 * │　　　　　　　　　│
 * │　－　　　　　　－│
 * │≡　　　　ｏ　≡   │
 * │　　　　　　　　　│
 * ╰——┬Ｏ◤▽◥Ｏ┬——╯
 * ｜　　ｏ　　｜
 * ｜╭－－－╮把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com
 * @QQ_NO: 83387856
 * @Date: 16/4/2 12:47
 * @Description: 后台用户控制器
 * @Copyright(©) 2015 by xiaomo.
 */
@RestController
@RequestMapping("/admin/adminUser")
public class AdminUserController extends BaseController {

    @Autowired
    AdminUserService service;

    /**
     * 后台账户登录
     *
     * @param userName userName
     * @param password password
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam String userName, @RequestParam String password) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            result.put(code, notFound);
            return result;
        }
        if (MD5Util.encode(password).equals(adminModel.getPassword())) {
            result.put(code, success);
            result.put(adminUser, adminModel);
        } else {
            result.put(code, error);
        }
        return result;
    }

    /**
     * 添加用户
     *
     * @param operator
     * @param userName
     * @param password
     * @param authLevel
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public HashMap<String, Object> add(
            @RequestParam String operator,
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam int authLevel
    ) {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            result.put(code, notFound);
            return result;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            result.put(code, authError);
            return result;
        }

        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel != null) {
            result.put(code, error);
            return result;
        }
        adminModel = new AdminModel();
        adminModel.setUserName(userName);
        adminModel.setPassword(MD5Util.encode(password));
        adminModel.setStatus(authLevel);
        adminModel.setOperator(operator);
        AdminModel res = service.addAdminUser(adminModel);
        if (res != null) {
            result.put(code, success);
            result.put("user", adminModel);
        } else {
            result.put(code, error);
        }
        return result;
    }

    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public HashMap<String, Object> findUserById(@RequestParam("id") Long id) {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            result.put(code, notFound);
            result.put(code, success);
        }
        result.put(code, success);
        result.put(adminUser, adminModel);
        return result;
    }


    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public HashMap<String, Object> getAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int page) {
        Page<AdminModel> pages = service.getAdminUsers(start, page);
        result.put(code, success);
        result.put(adminUsers, pages);
        return result;
    }

    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public HashMap<String, Object> deleteUserById(@RequestParam("id") Long id, @RequestParam String operator) throws UserNotFoundException {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            result.put(code, notFound);
            return result;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            result.put(code, authError);
            return result;
        }
        AdminModel adminModel = service.deleteAdminUserById(id);
        if (adminModel == null) {
            result.put(code, notFound);
            return result;
        }
        result.put(code, success);
        result.put(adminUser, adminModel);
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public HashMap<String, Object> update(
            @RequestParam("operator") String operator,
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("authLevel") int authLevel
    ) throws UserNotFoundException {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            result.put(code, notFound);
            return result;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            result.put(code, authError);
            return result;
        }
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            result.put(code, notFound);
            return result;
        }
        adminModel.setUserName(userName);
        adminModel.setPassword(password);
        adminModel.setAuthLevel(authLevel);
        service.updateAdminUser(adminModel);
        result.put(code, success);
        result.put(adminUser, adminModel);
        return result;
    }

    @RequestMapping(value = "forbid", method = RequestMethod.GET)
    public HashMap<String, Object> forbid(@RequestParam("id") Long id, @RequestParam("operator") String operator) throws UserNotFoundException {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            result.put(code, notFound);
            return result;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            result.put(code, authError);
            return result;
        }
        AdminModel model = service.findAdminUserById(id);
        if (model == null) {
            result.put(code, notFound);
            return result;
        }
        model = service.forbidAdminUserById(id);
        result.put(code, success);
        result.put(adminUser, model);
        return result;
    }
}

