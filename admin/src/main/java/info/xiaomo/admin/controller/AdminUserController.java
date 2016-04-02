package info.xiaomo.admin.controller;

import info.xiaomo.core.controller.BaseController;
import info.xiaomo.core.model.AdminModel;
import info.xiaomo.core.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("admin")
public class AdminUserController extends BaseController {

    @Autowired
    AdminUserService service;

    @RequestMapping("login")
    public Map<String, Object> login(@RequestParam String userName, @RequestParam String password) {
        HashMap<String, java.lang.Object> result = new HashMap<>();
        AdminModel adminModel = service.findAdminUserByUserName(userName);
        if (adminModel == null) {
            result.put(code, notFound);
        } else {
            if (password.equals(adminModel.getPassword())) {
                result.put(code, success);
                result.put("adminUser", adminModel);
            } else {
                result.put(code, error);
            }
        }
        return result;
    }

}
