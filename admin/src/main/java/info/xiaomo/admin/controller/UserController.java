package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("api/admin/user")
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
     * 分页查询用户
     *
     * @param start start
     * @param page  page
     * @return result
     */
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public HashMap<String, Object> getAll(@RequestParam("start") int start, @RequestParam("pageSize") int page) {
        Page<UserModel> pages = service.findAll(new PageRequest(start - 1, page));
        result.put(code, success);
        result.put(users, pages);
        return result;
    }

    /**
     * 根据id 删除用户
     *
     * @param id id
     * @return result
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    public HashMap<String, Object> deleteUserById(@RequestParam("id") Long id) throws UserNotFoundException {
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
