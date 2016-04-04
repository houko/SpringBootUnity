package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.exception.UserNotFoundException;
import info.xiaomo.core.model.UserModel;
import info.xiaomo.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("findById/{id}")
    public HashMap<String, Object> findUserById(@PathVariable("id") Long id) {
        HashMap<String, Object> result = new HashMap<>();
        UserModel userModel = service.findUserById(id);
        if (userModel == null) {
            result.put(code, notFound);
        } else {
            result.put(code, success);
            result.put("user", userModel);
        }
        return result;
    }


    @RequestMapping("findAll/{start}/{pageSize}")
    public HashMap<String, Object> getAll(@PathVariable("start") int start, @PathVariable("pageSize") int page) {
        HashMap<String, Object> result = new HashMap<>();
        Page<UserModel> pages = service.getUsers(new PageRequest(start - 1, page));
        result.put(code, success);
        result.put("users", pages);
        return result;
    }

    @RequestMapping("deleteById/{id}")
    public HashMap<String, Object> deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        HashMap<String, Object> result = new HashMap<>();
        UserModel userModel = service.deleteUserById(id);
        if (userModel == null) {
            result.put(code, notFound);
        } else {
            result.put(code, success);
            result.put("user", userModel);
        }
        return result;
    }
}
