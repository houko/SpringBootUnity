package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Error;
import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.controller.Result;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.website.AdminModel;
import info.xiaomo.core.service.website.AdminUserService;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController {

    private final AdminUserService service;

    @Autowired
    public AdminUserController(AdminUserService service) {
        this.service = service;
    }

    /**
     * 后台账户登录
     *
     * @return result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody AdminModel model) {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel == null) {
            return new Result(Error.USER_NOT_FOUND.getErrorCode(), Error.USER_NOT_FOUND.getErrorMsg());
        }
        if (MD5Util.encode(model.getPassword(), adminModel.getSalt()).equals(adminModel.getPassword())) {
            return new Result(Error.AUTH_FAILED.getErrorCode(), Error.AUTH_FAILED.getErrorMsg());
        }
        return new Result(adminModel);
    }


    /**
     * 添加用户
     *
     * @return model
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AdminModel add(@RequestBody AdminModel model
    ) {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel != null) {
            return null;
        }
        String salt = RandomUtil.createSalt();
        model.setSalt(salt);
        model.setPassword(MD5Util.encode(model.getPassword(), salt));
        AdminModel res = service.addAdminUser(model);
        if (res != null) {
            return null;
        }
        return res;
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
     *
     * @param start start
     * @param page  page
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
        AdminModel adminModel = service.deleteAdminUserById(id);
        if (adminModel == null) {
            return null;
        }
        return adminModel;
    }

    /**
     * 更新
     *
     * @param operator operator
     * @param userName userName
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public AdminModel update(
            @RequestParam("operator") String operator,
            @RequestParam("userName") String userName
    ) throws UserNotFoundException {
        AdminModel operatorModel = service.findAdminUserByUserName(operator);
        if (operator == null) {
            return null;
        }
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        adminModel.setUserName(userName);
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
        AdminModel model = service.findAdminUserById(id);
        if (model == null) {
            return null;
        }
        model = service.forbidAdminUserById(id);
        return model;
    }
}

