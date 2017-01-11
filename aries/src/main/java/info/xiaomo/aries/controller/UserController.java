package info.xiaomo.aries.controller;

import info.xiaomo.aries.base.BaseController;
import info.xiaomo.aries.model.UserModel;
import info.xiaomo.aries.service.UserService;
import info.xiaomo.core.controller.Result;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 小莫 (https://xiaomo.info) (https://github.com/syoubaku)
 * @version : 2017/1/11 16:40
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ApiOperation(value = "返回所有用户数据", notes = "返回所有用户数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<UserModel>> findAll() {
        List<UserModel> all = service.findAll();
        return new Result<>(all);
    }

    @Override
    public Result<List> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    @Override
    public Result findByName(@PathVariable String name) {
        return null;
    }

    @Override
    public Result<String> del(@PathVariable String name) {
        return null;
    }

    @Override
    public Result<String> del(@PathVariable Long id) {
        return null;
    }

    @Override
    public Result add(@RequestBody Object model) {
        return null;
    }

}
