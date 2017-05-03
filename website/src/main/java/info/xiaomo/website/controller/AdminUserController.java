package info.xiaomo.website.controller;

import info.xiaomo.core.constant.Code;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.untils.MD5Util;
import info.xiaomo.core.untils.RandomUtil;
import info.xiaomo.website.model.AdminModel;
import info.xiaomo.website.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
 * author: xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info

 * Date: 16/4/2 12:47
 * Description: 后台用户控制器
 * Copyright(©) 2015 by xiaomo.
 */
@Controller
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
     * @return Result
     */
    @RequestMapping(value = "login/{userName}/{password}", method = RequestMethod.POST)
    public Result login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return new Result(Code.USER_NOT_FOUND.getResultCode(), Code.USER_NOT_FOUND.getMessage());
        }
        if (!MD5Util.encode(password, adminModel.getSalt()).equals(adminModel.getPassword())) {
            return new Result(Code.AUTH_FAILED.getResultCode(), Code.AUTH_FAILED.getMessage());
        }
        return new Result<>(adminModel);
    }


    /**
     * 添加用户
     *
     * @return Result
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@RequestBody AdminModel model) {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel != null) {
            return new Result(Code.ADMIN_USER_REPEAT.getResultCode(), Code.ADMIN_USER_REPEAT.getMessage());
        }
        String salt = RandomUtil.createSalt();
        model.setSalt(salt);
        model.setPassword(MD5Util.encode(model.getPassword(), salt));
        AdminModel saveModel = service.addAdminUser(model);
        return new Result<>(saveModel);
    }

    /**
     * 根据id查找
     *
     * @param id id
     * @return Result
     */
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    public Result findUserById(@PathVariable("id") Long id) {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            return new Result(Code.NULL_DATA.getResultCode(), Code.NULL_DATA.getMessage());
        }
        return new Result<>(adminModel);
    }

    /**
     * 查找所有(不带分页)
     *
     * @return result
     */
    @Override
    public Result<List> findAll() {
        return null;
    }

    @Override
    public Result<Page> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    /**
     * 根据名字查找
     *
     * @param userName userName
     * @return Result
     */
    @RequestMapping(value = "findByName/{userName}", method = RequestMethod.GET)
    public Result findByName(@PathVariable("userName") String userName) {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return new Result(Code.NULL_DATA.getResultCode(), Code.NULL_DATA.getMessage());
        }
        return new Result<>(adminModel);
    }

    /**
     * 根据名字删除模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据id删除模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加模型
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> add(@RequestBody Object model) {
        return null;
    }

    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> update(@RequestBody Object model) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List ids) {
        return null;
    }


    /**
     * 修改密码
     *
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public Result changePassword(@RequestBody AdminModel model) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserByUserName(model.getUserName());
        if (adminModel == null) {
            return new Result(Code.NULL_DATA.getResultCode(), Code.NULL_DATA.getMessage());
        }
        String salt = RandomUtil.createSalt();
        adminModel.setSalt(salt);
        adminModel.setPassword(MD5Util.encode(model.getPassword(), salt));
        service.updateAdminUser(adminModel);
        return new Result<>(adminModel);
    }


    /**
     * 返回所有
     *
     * @return 不分页
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Result getAll() {
        List<AdminModel> pages = service.getAdminUsers();
        if (pages == null || pages.size() <= 0) {
            return new Result<>(pages);
        }
        return new Result<>(pages);
    }

    /**
     * 根据id删除数据
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public Result deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserById(id);
        if (adminModel == null) {
            return new Result(Code.NULL_DATA.getResultCode(), Code.NULL_DATA.getMessage());
        }
        service.deleteAdminUserById(id);
        return new Result<>(adminModel);
    }

    /**
     * 更新
     *
     * @param userName userName
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "update/{userName}", method = RequestMethod.POST)
    public Result update(@PathVariable("userName") String userName) throws UserNotFoundException {
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            return null;
        }
        adminModel.setUserName(userName);
        service.updateAdminUser(adminModel);
        return new Result<>(adminModel);
    }

    /**
     * 封号
     *
     * @param id id
     * @return model
     * @throws UserNotFoundException UserNotFoundException
     */
    @RequestMapping(value = "forbid/{id}", method = RequestMethod.GET)
    public Result forbid(@PathVariable("id") Long id) throws UserNotFoundException {
        AdminModel model = service.findAdminUserById(id);
        if (model == null) {
            return new Result(Code.NULL_DATA.getResultCode(), Code.NULL_DATA.getMessage());
        }
        model = service.forbidAdminUserById(id);
        return new Result<>(model);
    }
}

