package info.xiaomo.aries.controller;

import info.xiaomo.aries.model.UserModel;
import info.xiaomo.aries.service.UserService;
import info.xiaomo.core.controller.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:40
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @RequestMapping("findAll")
    public Result<List<UserModel>> findAll() {
        List<UserModel> all = service.findAll();
        return new Result<>(all);
    }

}
