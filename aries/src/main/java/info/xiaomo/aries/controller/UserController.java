package info.xiaomo.aries.controller;

import info.xiaomo.aries.model.UserModel;
import info.xiaomo.aries.service.UserService;
import info.xiaomo.core.base.BaseController;
import info.xiaomo.core.base.Result;
import info.xiaomo.core.constant.Code;
import info.xiaomo.core.untils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * author 小莫 (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/11 16:40
 */
@RequestMapping("/user")
@Api(value = "UserController", description = "用户相关api")
public class UserController extends BaseController<UserModel> {

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

    @RequestMapping(value = "findAll/{start}/{pageSize}", method = RequestMethod.GET)
    @ApiOperation(value = "返回所有用户数据,带分页", notes = "返回所有用户数据,传入页码和分页数。", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<Page<UserModel>> findAll(@PathVariable int start, @PathVariable int pageSize) {
        Page<UserModel> all = service.findAll(start, pageSize);
        return new Result<>(all);
    }

    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id查找数据", notes = "根据Id查找数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<UserModel> findById(@PathVariable Long id) {
        UserModel model = service.findById(id);
        return new Result<>(model);
    }

    @RequestMapping(value = "findByName/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "根据名字查找数据", notes = "根据名字查找数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<UserModel> findByName(@PathVariable String name) {
        UserModel model = service.findByName(name);
        return new Result<>(model);
    }

    @RequestMapping(value = "delByName/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "根据名字删除数据", notes = "根据名字删除数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        boolean b = service.delByName(name);
        return new Result<>(b);
    }

    @RequestMapping(value = "delById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id删除数据", notes = "根据id删除数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        boolean b = service.delById(id);
        return new Result<>(b);
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiOperation(value = "添加数据", notes = "添加数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<Boolean> add(@RequestBody UserModel model) {
        boolean b = service.add(model);
        return new Result<>(b);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "更新数据", notes = "添加数据", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<Boolean> update(@RequestBody UserModel model) {
        boolean update = service.update(model);
        return new Result<>(update);
    }

    @RequestMapping(value = "delByIds/{ids}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ids批量删除数据", notes = "根据ids批量删除数据", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<Boolean> delByIds(@PathVariable List<Long> ids) {
        boolean b = service.delByIds(ids);
        return new Result<>(b);
    }


    @RequestMapping(value = "login/{name}/{password}", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<Boolean> login(@PathVariable String name, @PathVariable String password) {
        UserModel userModel = service.findByName(name);
        String md5Password = MD5Util.encode(password, userModel.getSalt());
        if (!md5Password.equals(userModel.getPassword())) {
            return new Result<>(Code.AUTH_FAILED.getResultCode(), Code.AUTH_FAILED.getMessage(), false);
        }
        return new Result<>(true);
    }

}
