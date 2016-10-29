package info.xiaomo.website.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.AdminModel;
import info.xiaomo.core.service.AdminUserService;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/adminUser")
public class AdminUserController extends BaseController {

    private final AdminUserService service;

    @Autowired
    public AdminUserController(AdminUserService service) {
        this.service = service;
    }

    /**
     * 后台账户登录
     *
     * @param userName userName
     * @param password password
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AdminModel login(@RequestParam String userName, @RequestParam String password) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        if (MD5Util.encode(password, adminModel.getSalt()).equals(adminModel.getPassword())) {
            return null;
        }
        return adminModel;
    }

    /**
     * 添加用户
     *
     * @param operator  operator
     * @param userName  userName
     * @param password  password
     * @param authLevel authLevel
     * @return model
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AdminModel add(
            @RequestParam(name = "operator", defaultValue = "website") String operator,
            @RequestParam(name = "userName", defaultValue = "null") String userName,
            @RequestParam(name = "password", defaultValue = "123456") String password,
            @RequestParam(name = "authLevel", defaultValue = "0") int authLevel
    ) {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            return null;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            return null;
        }

        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel != null) {
            return null;
        }
        String salt = RandomUtil.createSalt();
        adminModel = new AdminModel();
        adminModel.setUserName(userName);
        adminModel.setSalt(salt);
        adminModel.setPassword(MD5Util.encode(password, salt));
        adminModel.setAuthLevel(authLevel);
        adminModel.setOperator(operator);
        AdminModel res = service.addAdminUser(adminModel);
        if (res != null) {
            return null;
        }
        return adminModel;
    }

    /**
     * 根据id查找
     *
     * @param id id
     * @return model
     */
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public AdminModel findUserById(@RequestParam("id") Long id) {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            return null;
        }
        return adminModel;
    }

    /**
     * 根据名字查找
     *
     * @param userName userName
     * @return model
     */
    @RequestMapping(value = "findByName", method = RequestMethod.GET)
    public AdminModel findByName(@RequestParam("userName") String userName) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        return adminModel;
    }

    /**
     * 修改密码
     *
     * @param userName userName
     * @param password password
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public AdminModel changePassword(@RequestParam("userName") String userName, @RequestParam("password") String password) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        String salt = RandomUtil.createSalt();
        adminModel.setSalt(salt);
        adminModel.setPassword(MD5Util.encode(password, salt));
        service.updateAdminUser(adminModel);
        return adminModel;
    }


    /**
     * 返回所有
     * @param start start
     * @param page page
     * @return 分页
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Page<AdminModel> getAll(@RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "pageSize", defaultValue = "10") int page) {
        return service.getAdminUsers(start, page);
    }

    /**
     * 根据id删除数据
     *
     * @param id       id
     * @param operator operator
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public AdminModel deleteUserById(@RequestParam("id") Long id, @RequestParam String operator) throws UserNotFoundException {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            return null;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            return null;
        }
        AdminModel adminModel = service.deleteAdminUserById(id);
        if (adminModel == null) {
            return null;
        }
        return adminModel;
    }

    /**
     * 更新
     *
     * @param operator  operator
     * @param userName  userName
     * @param authLevel authLevel
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public AdminModel update(
            @RequestParam("operator") String operator,
            @RequestParam("userName") String userName,
            @RequestParam("authLevel") int authLevel
    ) throws UserNotFoundException {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            return null;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            return null;
        }
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        adminModel.setUserName(userName);
        adminModel.setAuthLevel(authLevel);
        return service.updateAdminUser(adminModel);
    }

    /**
     * 封号
     *
     * @param id       id
     * @param operator operator
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "forbid", method = RequestMethod.GET)
    public AdminModel forbid(@RequestParam("id") Long id, @RequestParam("operator") String operator) throws UserNotFoundException {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            return null;
        }
        if (operatorModel.getAuthLevel() <= 0) {
            return null;
        }
        AdminModel model = service.findAdminUserById(id);
        if (model == null) {
            return null;
        }
        model = service.forbidAdminUserById(id);
        return model;
    }
}

